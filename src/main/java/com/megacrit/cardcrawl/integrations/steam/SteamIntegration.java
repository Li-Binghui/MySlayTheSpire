package com.megacrit.cardcrawl.integrations.steam;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamApps;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamFriends;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamRemoteStorage;
import com.codedisaster.steamworks.SteamUser;
import com.codedisaster.steamworks.SteamUserStats;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.TimeHelper;
import com.megacrit.cardcrawl.integrations.DistributorFactory;
import com.megacrit.cardcrawl.integrations.PublisherIntegration;
import com.megacrit.cardcrawl.screens.leaderboards.FilterButton;
import com.megacrit.cardcrawl.screens.stats.AchievementGrid;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/integrations/steam/SteamIntegration.class */
public class SteamIntegration implements PublisherIntegration {
    static SteamUserStats steamStats;
    private static SteamUser steamUser;
    private static SteamApps steamApps;
    static SteamFriends steamFriends;
    private static Thread thread;
    private static final Logger logger = LogManager.getLogger(SteamIntegration.class.getName());
    private static String[] TEXT = null;
    static int accountId = -1;
    static SteamLeaderboardHandle lbHandle = null;
    static LeaderboardTask task = null;
    private static boolean retrieveGlobal = true;
    static boolean gettingTime = false;
    private static int lbScore = 0;
    private static int startIndex = 0;
    private static int endIndex = 0;
    private static boolean isUploadingScore = false;
    private static Queue<StatTuple> statsToUpload = new LinkedList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/integrations/steam/SteamIntegration$LeaderboardTask.class */
    public enum LeaderboardTask {
        RETRIEVE,
        RETRIEVE_DAILY,
        UPLOAD,
        UPLOAD_DAILY
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/integrations/steam/SteamIntegration$StatTuple.class */
    public static class StatTuple {
        String stat;
        int score;

        StatTuple(String statName, int scoreVal) {
            this.stat = statName;
            this.score = scoreVal;
        }
    }

    public SteamIntegration() {
        if (!Settings.isDev || Settings.isModded) {
            try {
                SteamAPI.loadLibraries();
                if (SteamAPI.init()) {
                    logger.info("[SUCCESS] Steam API initialized successfully.");
                    steamStats = new SteamUserStats(new SSCallback(this));
                    steamUser = new SteamUser(new SUCallback());
                    steamApps = new SteamApps();
                    steamFriends = new SteamFriends(new SFCallback());
                    logger.info("BUILD ID: " + steamApps.getAppBuildId());
                    logger.info("CURRENT LANG: " + steamApps.getCurrentGameLanguage());
                    SteamID id = steamApps.getAppOwner();
                    accountId = id.getAccountID();
                    logger.info("ACCOUNT ID: " + accountId);
                    thread = new Thread(new SteamTicker());
                    thread.setName("SteamTicker");
                    thread.start();
                } else {
                    logger.info("[FAILURE] Steam API failed to initialize correctly.");
                }
            } catch (SteamException e) {
                e.printStackTrace();
            }
        }
        if (SteamAPI.isSteamRunning()) {
            requestGlobalStats(365);
        }
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public boolean isInitialized() {
        return (steamUser == null || steamStats == null) ? false : true;
    }

    public ArrayList<String> getAllCloudFiles() {
        SteamRemoteStorage remoteStorage = new SteamRemoteStorage(new SRCallback());
        int numFiles = remoteStorage.getFileCount();
        logger.info("Num of files: " + numFiles);
        ArrayList<String> files = new ArrayList<>();
        for (int i = 0; i < numFiles; i++) {
            int[] sizes = new int[1];
            String file = remoteStorage.getFileNameAndSize(i, sizes);
            boolean exists = remoteStorage.fileExists(file);
            if (exists) {
                files.add(file);
            }
            logger.info("# " + i + " : name=" + file + ", size=" + sizes[0] + ", exists=" + (exists ? "yes" : "no"));
        }
        remoteStorage.dispose();
        return files;
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public void deleteAllCloudFiles() {
        deleteCloudFiles(getAllCloudFiles());
        logger.info("Deleted all Cloud Files");
    }

    private void deleteCloudFiles(ArrayList<String> files) {
        SteamRemoteStorage remoteStorage = new SteamRemoteStorage(new SRCallback());
        Iterator<String> it = files.iterator();
        while (it.hasNext()) {
            String file = it.next();
            logger.info("Deleting file: " + file);
            remoteStorage.fileDelete(file);
        }
        remoteStorage.dispose();
    }

    public static String basename(String path) {
        Path p = Paths.get(path, new String[0]);
        return p.getFileName().toString();
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public void unlockAchievement(String id) {
        logger.info("unlockAchievement: " + id);
        if (steamStats == null) {
            return;
        }
        if (steamStats.setAchievement(id)) {
            steamStats.storeStats();
        } else {
            logger.info("[ERROR] Could not find achievement " + id);
        }
    }

    public static void removeAllAchievementsBeCarefulNotToPush() {
        if (Settings.isDev && Settings.isBeta && steamStats != null && steamStats.resetAllStats(true)) {
            steamStats.storeStats();
        }
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public boolean incrementStat(String id, int incrementAmt) {
        logger.info("incrementStat: " + id);
        if (steamStats == null) {
            logger.info("[ERROR] Could not find stat " + id);
            return false;
        } else if (steamStats.setStatI(id, getStat(id) + incrementAmt)) {
            return true;
        } else {
            logger.info("Stat: " + id + " not found.");
            return false;
        }
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public int getStat(String id) {
        logger.info("getStat: " + id);
        if (steamStats != null) {
            return steamStats.getStatI(id, 0);
        }
        return -1;
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public boolean setStat(String id, int value) {
        logger.info("setStat: " + id);
        if (steamStats == null) {
            logger.info("[ERROR] Could not find stat " + id);
            return false;
        } else if (steamStats.setStatI(id, value)) {
            logger.info(id + " stat set to " + value);
            return true;
        } else {
            logger.info("Stat: " + id + " not found.");
            return false;
        }
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public long getGlobalStat(String id) {
        logger.info("getGlobalStat");
        if (steamStats != null) {
            return steamStats.getGlobalStat(id, 0L);
        }
        return -1L;
    }

    private static void requestGlobalStats(int i) {
        logger.info("requestGlobalStats");
        if (steamStats != null) {
            steamStats.requestGlobalStats(i);
        }
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public void getLeaderboardEntries(AbstractPlayer.PlayerClass pClass, FilterButton.RegionSetting rSetting, FilterButton.LeaderboardType lType, int start, int end) {
        task = LeaderboardTask.RETRIEVE;
        startIndex = start;
        endIndex = end;
        if (lType == FilterButton.LeaderboardType.FASTEST_WIN) {
            gettingTime = true;
        } else {
            gettingTime = false;
        }
        if (rSetting == FilterButton.RegionSetting.GLOBAL) {
            retrieveGlobal = true;
        } else {
            retrieveGlobal = false;
        }
        if (steamStats != null) {
            steamStats.findLeaderboard(createGetLeaderboardString(pClass, lType));
        }
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public void getDailyLeaderboard(long date, int start, int end) {
        task = LeaderboardTask.RETRIEVE_DAILY;
        startIndex = start;
        endIndex = end;
        retrieveGlobal = true;
        gettingTime = false;
        if (steamStats != null) {
            StringBuilder leaderboardRetrieveString = new StringBuilder("DAILY_");
            leaderboardRetrieveString.append(Long.toString(date));
            if (Settings.isBeta) {
                leaderboardRetrieveString.append("_BETA");
            }
            steamStats.findOrCreateLeaderboard(leaderboardRetrieveString.toString(), SteamUserStats.LeaderboardSortMethod.Descending, SteamUserStats.LeaderboardDisplayType.Numeric);
        }
    }

    private static String createGetLeaderboardString(AbstractPlayer.PlayerClass pClass, FilterButton.LeaderboardType lType) {
        String retVal = "";
        switch (pClass) {
            case IRONCLAD:
                retVal = retVal + "IRONCLAD";
                break;
            case THE_SILENT:
                retVal = retVal + "SILENT";
                break;
            case DEFECT:
                retVal = retVal + "DEFECT";
                break;
            case WATCHER:
                retVal = retVal + "WATCHER";
                break;
        }
        switch (lType) {
            case AVG_FLOOR:
                retVal = retVal + "_AVG_FLOOR";
                break;
            case AVG_SCORE:
                retVal = retVal + "_AVG_SCORE";
                break;
            case CONSECUTIVE_WINS:
                retVal = retVal + "_CONSECUTIVE_WINS";
                break;
            case FASTEST_WIN:
                retVal = retVal + "_FASTEST_WIN";
                break;
            case HIGH_SCORE:
                retVal = retVal + "_HIGH_SCORE";
                break;
            case SPIRE_LEVEL:
                retVal = retVal + "_SPIRE_LEVEL";
                break;
        }
        if (Settings.isBeta) {
            retVal = retVal + "_BETA";
        }
        return retVal;
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public void uploadLeaderboardScore(String name, int score) {
        if (steamUser != null && steamStats != null) {
            if (isUploadingScore) {
                statsToUpload.add(new StatTuple(name, score));
                return;
            }
            logger.info(String.format("Uploading Steam Leaderboard score (%s: %d)", name, Integer.valueOf(score)));
            isUploadingScore = true;
            task = LeaderboardTask.UPLOAD;
            lbScore = score;
            steamStats.findLeaderboard(name);
        }
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public void uploadDailyLeaderboardScore(String name, int score) {
        if (TimeHelper.isOfflineMode()) {
            return;
        }
        if (steamUser == null || steamStats == null) {
            logger.info("User is NOT connected to Steam, unable to upload daily score.");
        } else if (isUploadingScore) {
            statsToUpload.add(new StatTuple(name, score));
        } else {
            logger.info(String.format("Uploading [DAILY] Steam Leaderboard score (%s: %d)", name, Integer.valueOf(score)));
            isUploadingScore = true;
            task = LeaderboardTask.UPLOAD_DAILY;
            lbScore = score;
            steamStats.findOrCreateLeaderboard(name, SteamUserStats.LeaderboardSortMethod.Descending, SteamUserStats.LeaderboardDisplayType.Numeric);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void didCompleteCallback(boolean success) {
        logger.info("didCompleteCallback");
        isUploadingScore = false;
        if (statsToUpload.size() > 0) {
            StatTuple uploadMe = statsToUpload.remove();
            uploadLeaderboardScore(uploadMe.stat, uploadMe.score);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void uploadLeaderboardHelper() {
        logger.info("uploadLeaderboardHelper");
        steamStats.uploadLeaderboardScore(lbHandle, SteamUserStats.LeaderboardUploadScoreMethod.KeepBest, lbScore, new int[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void uploadDailyLeaderboardHelper() {
        logger.info("uploadDailyLeaderboardHelper");
        steamStats.uploadLeaderboardScore(lbHandle, SteamUserStats.LeaderboardUploadScoreMethod.KeepBest, lbScore, new int[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void getLeaderboardEntryHelper() {
        if (task == LeaderboardTask.RETRIEVE) {
            if (retrieveGlobal) {
                logger.info("Downloading GLOBAL entries: " + startIndex + " - " + endIndex);
                if (CardCrawlGame.mainMenuScreen.leaderboardsScreen.viewMyScore) {
                    steamStats.downloadLeaderboardEntries(lbHandle, SteamUserStats.LeaderboardDataRequest.GlobalAroundUser, -9, 10);
                    CardCrawlGame.mainMenuScreen.leaderboardsScreen.viewMyScore = false;
                    return;
                }
                steamStats.downloadLeaderboardEntries(lbHandle, SteamUserStats.LeaderboardDataRequest.Global, startIndex, endIndex);
                return;
            }
            logger.info("Downloading FRIEND entries: " + startIndex + " - " + endIndex);
            steamStats.downloadLeaderboardEntries(lbHandle, SteamUserStats.LeaderboardDataRequest.Friends, startIndex, endIndex);
        } else if (task != LeaderboardTask.RETRIEVE_DAILY) {
        } else {
            if (CardCrawlGame.mainMenuScreen.dailyScreen.viewMyScore) {
                steamStats.downloadLeaderboardEntries(lbHandle, SteamUserStats.LeaderboardDataRequest.GlobalAroundUser, -9, 10);
                CardCrawlGame.mainMenuScreen.dailyScreen.viewMyScore = false;
                return;
            }
            logger.info("Downloading GLOBAL entries: " + startIndex + " - " + endIndex);
            steamStats.downloadLeaderboardEntries(lbHandle, SteamUserStats.LeaderboardDataRequest.Global, startIndex, endIndex);
        }
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public void setRichPresenceDisplayPlaying(int floor, int ascension, String character) {
        if (TEXT == null) {
            TEXT = CardCrawlGame.languagePack.getUIString("RichPresence").TEXT;
        }
        if (Settings.isDailyRun) {
            String msg = String.format(TEXT[0], Integer.valueOf(floor));
            logger.debug("Setting Rich Presence: " + msg);
            setRichPresenceData("status", msg);
        } else if (Settings.isTrial) {
            String msg2 = String.format(TEXT[1], Integer.valueOf(floor));
            logger.debug("Setting Rich Presence: " + msg2);
            setRichPresenceData("status", msg2);
        } else if (Settings.language == Settings.GameLanguage.ENG || Settings.language == Settings.GameLanguage.DEU || Settings.language == Settings.GameLanguage.THA || Settings.language == Settings.GameLanguage.TUR || Settings.language == Settings.GameLanguage.KOR || Settings.language == Settings.GameLanguage.RUS || Settings.language == Settings.GameLanguage.SPA || Settings.language == Settings.GameLanguage.DUT) {
            String msg3 = String.format(TEXT[4] + character + TEXT[2], Integer.valueOf(ascension), Integer.valueOf(floor));
            logger.debug("Setting Rich Presence: " + msg3);
            setRichPresenceData("status", msg3);
        } else {
            String msg4 = String.format(character + TEXT[2] + TEXT[4], Integer.valueOf(floor), Integer.valueOf(ascension));
            logger.debug("Setting Rich Presence: " + msg4);
            setRichPresenceData("status", msg4);
        }
        setRichPresenceData("steam_display", "#Status");
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public void setRichPresenceDisplayPlaying(int floor, String character) {
        if (TEXT == null) {
            TEXT = CardCrawlGame.languagePack.getUIString("RichPresence").TEXT;
        }
        if (Settings.isDailyRun) {
            String msg = String.format(TEXT[0], Integer.valueOf(floor));
            logger.debug("Setting Rich Presence: " + msg);
            setRichPresenceData("status", msg);
        } else if (Settings.isTrial) {
            String msg2 = String.format(TEXT[1], Integer.valueOf(floor));
            logger.debug("Setting Rich Presence: " + msg2);
            setRichPresenceData("status", msg2);
        } else {
            String msg3 = String.format(character + TEXT[2], Integer.valueOf(floor));
            logger.debug("Setting Rich Presence: " + msg3);
            setRichPresenceData("status", msg3);
        }
        setRichPresenceData("steam_display", "#Status");
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public void setRichPresenceDisplayInMenu() {
        if (TEXT == null) {
            TEXT = CardCrawlGame.languagePack.getUIString("RichPresence").TEXT;
        }
        logger.debug("Setting Rich Presence: " + String.format(TEXT[3], new Object[0]));
        setRichPresenceData("status", TEXT[3]);
        setRichPresenceData("steam_display", "#Status");
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public int getNumUnlockedAchievements() {
        int retVal = 0;
        ArrayList<String> keys = new ArrayList<>();
        keys.add(AchievementGrid.ADRENALINE_KEY);
        keys.add(AchievementGrid.ASCEND_0_KEY);
        keys.add(AchievementGrid.ASCEND_10_KEY);
        keys.add(AchievementGrid.ASCEND_20_KEY);
        keys.add(AchievementGrid.AUTOMATON_KEY);
        keys.add(AchievementGrid.BARRICADED_KEY);
        keys.add(AchievementGrid.CATALYST_KEY);
        keys.add(AchievementGrid.CHAMP_KEY);
        keys.add(AchievementGrid.COLLECTOR_KEY);
        keys.add(AchievementGrid.COME_AT_ME_KEY);
        keys.add(AchievementGrid.COMMON_SENSE_KEY);
        keys.add(AchievementGrid.CROW_KEY);
        keys.add(AchievementGrid.DONUT_KEY);
        keys.add(AchievementGrid.EMERALD_KEY);
        keys.add(AchievementGrid.EMERALD_PLUS_KEY);
        keys.add(AchievementGrid.FOCUSED_KEY);
        keys.add(AchievementGrid.GHOST_GUARDIAN_KEY);
        keys.add(AchievementGrid.GUARDIAN_KEY);
        keys.add(AchievementGrid.IMPERVIOUS_KEY);
        keys.add(AchievementGrid.INFINITY_KEY);
        keys.add(AchievementGrid.JAXXED_KEY);
        keys.add(AchievementGrid.LUCKY_DAY_KEY);
        keys.add(AchievementGrid.MINMALIST_KEY);
        keys.add(AchievementGrid.NEON_KEY);
        keys.add(AchievementGrid.NINJA_KEY);
        keys.add(AchievementGrid.ONE_RELIC_KEY);
        keys.add(AchievementGrid.PERFECT_KEY);
        keys.add(AchievementGrid.PLAGUE_KEY);
        keys.add(AchievementGrid.POWERFUL_KEY);
        keys.add(AchievementGrid.PURITY_KEY);
        keys.add(AchievementGrid.RUBY_KEY);
        keys.add(AchievementGrid.RUBY_PLUS_KEY);
        keys.add(AchievementGrid.SAPPHIRE_KEY);
        keys.add(AchievementGrid.SAPPHIRE_PLUS_KEY);
        keys.add(AchievementGrid.AMETHYST_KEY);
        keys.add(AchievementGrid.AMETHYST_PLUS_KEY);
        keys.add(AchievementGrid.SHAPES_KEY);
        keys.add(AchievementGrid.SHRUG_KEY);
        keys.add(AchievementGrid.SLIME_BOSS_KEY);
        keys.add(AchievementGrid.SPEED_CLIMBER_KEY);
        keys.add(AchievementGrid.THE_ENDING_KEY);
        keys.add(AchievementGrid.THE_PACT_KEY);
        keys.add(AchievementGrid.TIME_EATER_KEY);
        keys.add(AchievementGrid.TRANSIENT_KEY);
        keys.add(AchievementGrid.YOU_ARE_NOTHING_KEY);
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (steamStats.isAchieved(s, false)) {
                retVal++;
            }
        }
        return retVal;
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public DistributorFactory.Distributor getType() {
        return DistributorFactory.Distributor.STEAM;
    }

    private void setRichPresenceData(String key, String value) {
        if (steamFriends != null && !steamFriends.setRichPresence(key, value)) {
            logger.info("Failed to set Steam Rich Presence: key=" + key + " value=" + value);
        }
    }

    @Override // com.megacrit.cardcrawl.integrations.PublisherIntegration
    public void dispose() {
        if (isInitialized()) {
            SteamAPI.shutdown();
        }
    }
}