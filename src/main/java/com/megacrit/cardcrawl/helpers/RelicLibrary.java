package com.megacrit.cardcrawl.helpers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.metrics.BotDataUploader;
import com.megacrit.cardcrawl.relics.Abacus;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Akabeko;
import com.megacrit.cardcrawl.relics.Anchor;
import com.megacrit.cardcrawl.relics.AncientTeaSet;
import com.megacrit.cardcrawl.relics.ArtOfWar;
import com.megacrit.cardcrawl.relics.Astrolabe;
import com.megacrit.cardcrawl.relics.BagOfMarbles;
import com.megacrit.cardcrawl.relics.BagOfPreparation;
import com.megacrit.cardcrawl.relics.BirdFacedUrn;
import com.megacrit.cardcrawl.relics.BlackBlood;
import com.megacrit.cardcrawl.relics.BlackStar;
import com.megacrit.cardcrawl.relics.BloodVial;
import com.megacrit.cardcrawl.relics.BloodyIdol;
import com.megacrit.cardcrawl.relics.BlueCandle;
import com.megacrit.cardcrawl.relics.Boot;
import com.megacrit.cardcrawl.relics.BottledFlame;
import com.megacrit.cardcrawl.relics.BottledLightning;
import com.megacrit.cardcrawl.relics.BottledTornado;
import com.megacrit.cardcrawl.relics.Brimstone;
import com.megacrit.cardcrawl.relics.BronzeScales;
import com.megacrit.cardcrawl.relics.BurningBlood;
import com.megacrit.cardcrawl.relics.BustedCrown;
import com.megacrit.cardcrawl.relics.Calipers;
import com.megacrit.cardcrawl.relics.CallingBell;
import com.megacrit.cardcrawl.relics.CaptainsWheel;
import com.megacrit.cardcrawl.relics.Cauldron;
import com.megacrit.cardcrawl.relics.CentennialPuzzle;
import com.megacrit.cardcrawl.relics.CeramicFish;
import com.megacrit.cardcrawl.relics.ChampionsBelt;
import com.megacrit.cardcrawl.relics.CharonsAshes;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.relics.CloakClasp;
import com.megacrit.cardcrawl.relics.ClockworkSouvenir;
import com.megacrit.cardcrawl.relics.CoffeeDripper;
import com.megacrit.cardcrawl.relics.Courier;
import com.megacrit.cardcrawl.relics.CrackedCore;
import com.megacrit.cardcrawl.relics.CultistMask;
import com.megacrit.cardcrawl.relics.CursedKey;
import com.megacrit.cardcrawl.relics.Damaru;
import com.megacrit.cardcrawl.relics.DarkstonePeriapt;
import com.megacrit.cardcrawl.relics.DataDisk;
import com.megacrit.cardcrawl.relics.DeadBranch;
import com.megacrit.cardcrawl.relics.DollysMirror;
import com.megacrit.cardcrawl.relics.DreamCatcher;
import com.megacrit.cardcrawl.relics.DuVuDoll;
import com.megacrit.cardcrawl.relics.Duality;
import com.megacrit.cardcrawl.relics.Ectoplasm;
import com.megacrit.cardcrawl.relics.EmotionChip;
import com.megacrit.cardcrawl.relics.EmptyCage;
import com.megacrit.cardcrawl.relics.Enchiridion;
import com.megacrit.cardcrawl.relics.EternalFeather;
import com.megacrit.cardcrawl.relics.FaceOfCleric;
import com.megacrit.cardcrawl.relics.FossilizedHelix;
import com.megacrit.cardcrawl.relics.FrozenCore;
import com.megacrit.cardcrawl.relics.FrozenEgg2;
import com.megacrit.cardcrawl.relics.FrozenEye;
import com.megacrit.cardcrawl.relics.FusionHammer;
import com.megacrit.cardcrawl.relics.GamblingChip;
import com.megacrit.cardcrawl.relics.Ginger;
import com.megacrit.cardcrawl.relics.Girya;
import com.megacrit.cardcrawl.relics.GoldPlatedCables;
import com.megacrit.cardcrawl.relics.GoldenEye;
import com.megacrit.cardcrawl.relics.GoldenIdol;
import com.megacrit.cardcrawl.relics.GremlinHorn;
import com.megacrit.cardcrawl.relics.GremlinMask;
import com.megacrit.cardcrawl.relics.HandDrill;
import com.megacrit.cardcrawl.relics.HappyFlower;
import com.megacrit.cardcrawl.relics.HolyWater;
import com.megacrit.cardcrawl.relics.HornCleat;
import com.megacrit.cardcrawl.relics.HoveringKite;
import com.megacrit.cardcrawl.relics.IceCream;
import com.megacrit.cardcrawl.relics.IncenseBurner;
import com.megacrit.cardcrawl.relics.InkBottle;
import com.megacrit.cardcrawl.relics.Inserter;
import com.megacrit.cardcrawl.relics.JuzuBracelet;
import com.megacrit.cardcrawl.relics.Kunai;
import com.megacrit.cardcrawl.relics.Lantern;
import com.megacrit.cardcrawl.relics.LetterOpener;
import com.megacrit.cardcrawl.relics.LizardTail;
import com.megacrit.cardcrawl.relics.MagicFlower;
import com.megacrit.cardcrawl.relics.Mango;
import com.megacrit.cardcrawl.relics.MarkOfPain;
import com.megacrit.cardcrawl.relics.MarkOfTheBloom;
import com.megacrit.cardcrawl.relics.Matryoshka;
import com.megacrit.cardcrawl.relics.MawBank;
import com.megacrit.cardcrawl.relics.MealTicket;
import com.megacrit.cardcrawl.relics.MeatOnTheBone;
import com.megacrit.cardcrawl.relics.MedicalKit;
import com.megacrit.cardcrawl.relics.Melange;
import com.megacrit.cardcrawl.relics.MembershipCard;
import com.megacrit.cardcrawl.relics.MercuryHourglass;
import com.megacrit.cardcrawl.relics.MoltenEgg2;
import com.megacrit.cardcrawl.relics.MummifiedHand;
import com.megacrit.cardcrawl.relics.MutagenicStrength;
import com.megacrit.cardcrawl.relics.Necronomicon;
import com.megacrit.cardcrawl.relics.NeowsLament;
import com.megacrit.cardcrawl.relics.NilrysCodex;
import com.megacrit.cardcrawl.relics.NinjaScroll;
import com.megacrit.cardcrawl.relics.NlothsGift;
import com.megacrit.cardcrawl.relics.NlothsMask;
import com.megacrit.cardcrawl.relics.NuclearBattery;
import com.megacrit.cardcrawl.relics.Nunchaku;
import com.megacrit.cardcrawl.relics.OddMushroom;
import com.megacrit.cardcrawl.relics.OddlySmoothStone;
import com.megacrit.cardcrawl.relics.OldCoin;
import com.megacrit.cardcrawl.relics.Omamori;
import com.megacrit.cardcrawl.relics.OrangePellets;
import com.megacrit.cardcrawl.relics.Orichalcum;
import com.megacrit.cardcrawl.relics.OrnamentalFan;
import com.megacrit.cardcrawl.relics.Orrery;
import com.megacrit.cardcrawl.relics.PandorasBox;
import com.megacrit.cardcrawl.relics.Pantograph;
import com.megacrit.cardcrawl.relics.PaperCrane;
import com.megacrit.cardcrawl.relics.PaperFrog;
import com.megacrit.cardcrawl.relics.PeacePipe;
import com.megacrit.cardcrawl.relics.Pear;
import com.megacrit.cardcrawl.relics.PenNib;
import com.megacrit.cardcrawl.relics.PhilosopherStone;
import com.megacrit.cardcrawl.relics.Pocketwatch;
import com.megacrit.cardcrawl.relics.PotionBelt;
import com.megacrit.cardcrawl.relics.PrayerWheel;
import com.megacrit.cardcrawl.relics.PreservedInsect;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.relics.PureWater;
import com.megacrit.cardcrawl.relics.QuestionCard;
import com.megacrit.cardcrawl.relics.RedMask;
import com.megacrit.cardcrawl.relics.RedSkull;
import com.megacrit.cardcrawl.relics.RegalPillow;
import com.megacrit.cardcrawl.relics.RingOfTheSerpent;
import com.megacrit.cardcrawl.relics.RunicCapacitor;
import com.megacrit.cardcrawl.relics.RunicCube;
import com.megacrit.cardcrawl.relics.RunicDome;
import com.megacrit.cardcrawl.relics.RunicPyramid;
import com.megacrit.cardcrawl.relics.SacredBark;
import com.megacrit.cardcrawl.relics.SelfFormingClay;
import com.megacrit.cardcrawl.relics.Shovel;
import com.megacrit.cardcrawl.relics.Shuriken;
import com.megacrit.cardcrawl.relics.SingingBowl;
import com.megacrit.cardcrawl.relics.SlaversCollar;
import com.megacrit.cardcrawl.relics.Sling;
import com.megacrit.cardcrawl.relics.SmilingMask;
import com.megacrit.cardcrawl.relics.SnakeRing;
import com.megacrit.cardcrawl.relics.SneckoEye;
import com.megacrit.cardcrawl.relics.SneckoSkull;
import com.megacrit.cardcrawl.relics.Sozu;
import com.megacrit.cardcrawl.relics.SpiritPoop;
import com.megacrit.cardcrawl.relics.SsserpentHead;
import com.megacrit.cardcrawl.relics.StoneCalendar;
import com.megacrit.cardcrawl.relics.StrangeSpoon;
import com.megacrit.cardcrawl.relics.Strawberry;
import com.megacrit.cardcrawl.relics.StrikeDummy;
import com.megacrit.cardcrawl.relics.Sundial;
import com.megacrit.cardcrawl.relics.SymbioticVirus;
import com.megacrit.cardcrawl.relics.TeardropLocket;
import com.megacrit.cardcrawl.relics.TheSpecimen;
import com.megacrit.cardcrawl.relics.ThreadAndNeedle;
import com.megacrit.cardcrawl.relics.Tingsha;
import com.megacrit.cardcrawl.relics.TinyChest;
import com.megacrit.cardcrawl.relics.TinyHouse;
import com.megacrit.cardcrawl.relics.Toolbox;
import com.megacrit.cardcrawl.relics.Torii;
import com.megacrit.cardcrawl.relics.ToughBandages;
import com.megacrit.cardcrawl.relics.ToxicEgg2;
import com.megacrit.cardcrawl.relics.ToyOrnithopter;
import com.megacrit.cardcrawl.relics.TungstenRod;
import com.megacrit.cardcrawl.relics.Turnip;
import com.megacrit.cardcrawl.relics.TwistedFunnel;
import com.megacrit.cardcrawl.relics.UnceasingTop;
import com.megacrit.cardcrawl.relics.Vajra;
import com.megacrit.cardcrawl.relics.VelvetChoker;
import com.megacrit.cardcrawl.relics.VioletLotus;
import com.megacrit.cardcrawl.relics.Waffle;
import com.megacrit.cardcrawl.relics.WarPaint;
import com.megacrit.cardcrawl.relics.WarpedTongs;
import com.megacrit.cardcrawl.relics.Whetstone;
import com.megacrit.cardcrawl.relics.WhiteBeast;
import com.megacrit.cardcrawl.relics.WingBoots;
import com.megacrit.cardcrawl.relics.WristBlade;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/helpers/RelicLibrary.class */
public class RelicLibrary {
    private static final Logger logger = LogManager.getLogger(RelicLibrary.class.getName());
    public static int totalRelicCount = 0;
    public static int seenRelics = 0;
    private static HashMap<String, AbstractRelic> sharedRelics = new HashMap<>();
    private static HashMap<String, AbstractRelic> redRelics = new HashMap<>();
    private static HashMap<String, AbstractRelic> greenRelics = new HashMap<>();
    private static HashMap<String, AbstractRelic> blueRelics = new HashMap<>();
    private static HashMap<String, AbstractRelic> purpleRelics = new HashMap<>();
    public static ArrayList<AbstractRelic> starterList = new ArrayList<>();
    public static ArrayList<AbstractRelic> commonList = new ArrayList<>();
    public static ArrayList<AbstractRelic> uncommonList = new ArrayList<>();
    public static ArrayList<AbstractRelic> rareList = new ArrayList<>();
    public static ArrayList<AbstractRelic> bossList = new ArrayList<>();
    public static ArrayList<AbstractRelic> specialList = new ArrayList<>();
    public static ArrayList<AbstractRelic> shopList = new ArrayList<>();
    public static ArrayList<AbstractRelic> redList = new ArrayList<>();
    public static ArrayList<AbstractRelic> greenList = new ArrayList<>();
    public static ArrayList<AbstractRelic> blueList = new ArrayList<>();
    public static ArrayList<AbstractRelic> whiteList = new ArrayList<>();

    public static void initialize() {
        long startTime = System.currentTimeMillis();
        add(new Abacus());
        add(new Akabeko());
        add(new Anchor());
        add(new AncientTeaSet());
        add(new ArtOfWar());
        add(new Astrolabe());
        add(new BagOfMarbles());
        add(new BagOfPreparation());
        add(new BirdFacedUrn());
        add(new BlackStar());
        add(new BloodVial());
        add(new BloodyIdol());
        add(new BlueCandle());
        add(new Boot());
        add(new BottledFlame());
        add(new BottledLightning());
        add(new BottledTornado());
        add(new BronzeScales());
        add(new BustedCrown());
        add(new Calipers());
        add(new CallingBell());
        add(new CaptainsWheel());
        add(new Cauldron());
        add(new CentennialPuzzle());
        add(new CeramicFish());
        add(new ChemicalX());
        add(new ClockworkSouvenir());
        add(new CoffeeDripper());
        add(new Courier());
        add(new CultistMask());
        add(new CursedKey());
        add(new DarkstonePeriapt());
        add(new DeadBranch());
        add(new DollysMirror());
        add(new DreamCatcher());
        add(new DuVuDoll());
        add(new Ectoplasm());
        add(new EmptyCage());
        add(new Enchiridion());
        add(new EternalFeather());
        add(new FaceOfCleric());
        add(new FossilizedHelix());
        add(new FrozenEgg2());
        add(new FrozenEye());
        add(new FusionHammer());
        add(new GamblingChip());
        add(new Ginger());
        add(new Girya());
        add(new GoldenIdol());
        add(new GremlinHorn());
        add(new GremlinMask());
        add(new HandDrill());
        add(new HappyFlower());
        add(new HornCleat());
        add(new IceCream());
        add(new IncenseBurner());
        add(new InkBottle());
        add(new JuzuBracelet());
        add(new Kunai());
        add(new Lantern());
        add(new LetterOpener());
        add(new LizardTail());
        add(new Mango());
        add(new MarkOfTheBloom());
        add(new Matryoshka());
        add(new MawBank());
        add(new MealTicket());
        add(new MeatOnTheBone());
        add(new MedicalKit());
        add(new MembershipCard());
        add(new MercuryHourglass());
        add(new MoltenEgg2());
        add(new MummifiedHand());
        add(new MutagenicStrength());
        add(new Necronomicon());
        add(new NeowsLament());
        add(new NilrysCodex());
        add(new NlothsGift());
        add(new NlothsMask());
        add(new Nunchaku());
        add(new OddlySmoothStone());
        add(new OddMushroom());
        add(new OldCoin());
        add(new Omamori());
        add(new OrangePellets());
        add(new Orichalcum());
        add(new OrnamentalFan());
        add(new Orrery());
        add(new PandorasBox());
        add(new Pantograph());
        add(new PeacePipe());
        add(new Pear());
        add(new PenNib());
        add(new PhilosopherStone());
        add(new Pocketwatch());
        add(new PotionBelt());
        add(new PrayerWheel());
        add(new PreservedInsect());
        add(new PrismaticShard());
        add(new QuestionCard());
        add(new RedMask());
        add(new RegalPillow());
        add(new RunicDome());
        add(new RunicPyramid());
        add(new SacredBark());
        add(new Shovel());
        add(new Shuriken());
        add(new SingingBowl());
        add(new SlaversCollar());
        add(new Sling());
        add(new SmilingMask());
        add(new SneckoEye());
        add(new Sozu());
        add(new SpiritPoop());
        add(new SsserpentHead());
        add(new StoneCalendar());
        add(new StrangeSpoon());
        add(new Strawberry());
        add(new StrikeDummy());
        add(new Sundial());
        add(new ThreadAndNeedle());
        add(new TinyChest());
        add(new TinyHouse());
        add(new Toolbox());
        add(new Torii());
        add(new ToxicEgg2());
        add(new ToyOrnithopter());
        add(new TungstenRod());
        add(new Turnip());
        add(new UnceasingTop());
        add(new Vajra());
        add(new VelvetChoker());
        add(new Waffle());
        add(new WarPaint());
        add(new WarpedTongs());
        add(new Whetstone());
        add(new WhiteBeast());
        add(new WingBoots());
        addGreen(new HoveringKite());
        addGreen(new NinjaScroll());
        addGreen(new PaperCrane());
        addGreen(new RingOfTheSerpent());
        addGreen(new SnakeRing());
        addGreen(new SneckoSkull());
        addGreen(new TheSpecimen());
        addGreen(new Tingsha());
        addGreen(new ToughBandages());
        addGreen(new TwistedFunnel());
        addGreen(new WristBlade());
        addRed(new BlackBlood());
        addRed(new Brimstone());
        addRed(new BurningBlood());
        addRed(new ChampionsBelt());
        addRed(new CharonsAshes());
        addRed(new MagicFlower());
        addRed(new MarkOfPain());
        addRed(new PaperFrog());
        addRed(new RedSkull());
        addRed(new RunicCube());
        addRed(new SelfFormingClay());
        addBlue(new CrackedCore());
        addBlue(new DataDisk());
        addBlue(new EmotionChip());
        addBlue(new FrozenCore());
        addBlue(new GoldPlatedCables());
        addBlue(new Inserter());
        addBlue(new NuclearBattery());
        addBlue(new RunicCapacitor());
        addBlue(new SymbioticVirus());
        addPurple(new CloakClasp());
        addPurple(new Damaru());
        addPurple(new GoldenEye());
        addPurple(new HolyWater());
        addPurple(new Melange());
        addPurple(new PureWater());
        addPurple(new VioletLotus());
        addPurple(new TeardropLocket());
        addPurple(new Duality());
        if (Settings.isBeta) {
        }
        logger.info("Relic load time: " + (System.currentTimeMillis() - startTime) + "ms" + "with 177 RelicL");
        sortLists();
    }

    public static void resetForReload() {
        totalRelicCount = 0;
        seenRelics = 0;
        sharedRelics.clear();
        redRelics.clear();
        greenRelics.clear();
        blueRelics.clear();
        purpleRelics.clear();
        starterList.clear();
        commonList.clear();
        uncommonList.clear();
        rareList.clear();
        bossList.clear();
        specialList.clear();
        shopList.clear();
        redList.clear();
        greenList.clear();
        blueList.clear();
        whiteList.clear();
    }

    private static void sortLists() {
        Collections.sort(starterList);
        Collections.sort(commonList);
        Collections.sort(uncommonList);
        Collections.sort(rareList);
        Collections.sort(bossList);
        Collections.sort(specialList);
        Collections.sort(shopList);
        if (Settings.isDev) {
            logger.info(starterList);
            logger.info(commonList);
            logger.info(uncommonList);
            logger.info(rareList);
            logger.info(bossList);
        }
    }

    private static void printRelicsMissingLargeArt() {
        logger.info("[ART] START DISPLAYING RELICS WITH MISSING HIGH RES ART");
        for (Map.Entry<String, AbstractRelic> r : sharedRelics.entrySet()) {
            AbstractRelic relic = r.getValue();
            if (ImageMaster.loadImage("images/largeRelics/" + relic.imgUrl) == null) {
                logger.info(relic.name);
            }
        }
    }

    private static void printRelicCount() {
        int common = 0;
        int uncommon = 0;
        int rare = 0;
        int boss = 0;
        int shop = 0;
        int other = 0;
        for (Map.Entry<String, AbstractRelic> r : sharedRelics.entrySet()) {
            switch (r.getValue().tier) {
                case COMMON:
                    common++;
                    break;
                case UNCOMMON:
                    uncommon++;
                    break;
                case RARE:
                    rare++;
                    break;
                case BOSS:
                    boss++;
                    break;
                case SHOP:
                    shop++;
                    break;
                default:
                    other++;
                    break;
            }
        }
        if (Settings.isDev) {
            logger.info("RELIC COUNTS");
            logger.info("Common: " + common);
            logger.info("Uncommon: " + uncommon);
            logger.info("Rare: " + rare);
            logger.info("Boss: " + boss);
            logger.info("Shop: " + shop);
            logger.info("Other: " + other);
            logger.info("Red: " + redRelics.size());
            logger.info("Green: " + greenRelics.size());
            logger.info("Blue: " + blueRelics.size());
            logger.info("Purple: " + purpleRelics.size());
        }
    }

    public static void add(AbstractRelic relic) {
        if (UnlockTracker.isRelicSeen(relic.relicId)) {
            seenRelics++;
        }
        relic.isSeen = UnlockTracker.isRelicSeen(relic.relicId);
        sharedRelics.put(relic.relicId, relic);
        addToTierList(relic);
        totalRelicCount++;
    }

    public static void addRed(AbstractRelic relic) {
        if (UnlockTracker.isRelicSeen(relic.relicId)) {
            seenRelics++;
        }
        relic.isSeen = UnlockTracker.isRelicSeen(relic.relicId);
        redRelics.put(relic.relicId, relic);
        addToTierList(relic);
        redList.add(relic);
        totalRelicCount++;
    }

    public static void addGreen(AbstractRelic relic) {
        if (UnlockTracker.isRelicSeen(relic.relicId)) {
            seenRelics++;
        }
        relic.isSeen = UnlockTracker.isRelicSeen(relic.relicId);
        greenRelics.put(relic.relicId, relic);
        addToTierList(relic);
        greenList.add(relic);
        totalRelicCount++;
    }

    public static void addBlue(AbstractRelic relic) {
        if (UnlockTracker.isRelicSeen(relic.relicId)) {
            seenRelics++;
        }
        relic.isSeen = UnlockTracker.isRelicSeen(relic.relicId);
        blueRelics.put(relic.relicId, relic);
        addToTierList(relic);
        blueList.add(relic);
        totalRelicCount++;
    }

    public static void addPurple(AbstractRelic relic) {
        if (UnlockTracker.isRelicSeen(relic.relicId)) {
            seenRelics++;
        }
        relic.isSeen = UnlockTracker.isRelicSeen(relic.relicId);
        purpleRelics.put(relic.relicId, relic);
        addToTierList(relic);
        whiteList.add(relic);
        totalRelicCount++;
    }

    public static void addToTierList(AbstractRelic relic) {
        switch (relic.tier) {
            case COMMON:
                commonList.add(relic);
                return;
            case UNCOMMON:
                uncommonList.add(relic);
                return;
            case RARE:
                rareList.add(relic);
                return;
            case BOSS:
                bossList.add(relic);
                return;
            case SHOP:
                shopList.add(relic);
                return;
            case STARTER:
                starterList.add(relic);
                return;
            case SPECIAL:
                specialList.add(relic);
                return;
            case DEPRECATED:
                logger.info(relic.relicId + " is deprecated.");
                return;
            default:
                logger.info(relic.relicId + " is undefined tier.");
                return;
        }
    }

    public static AbstractRelic getRelic(String key) {
        if (sharedRelics.containsKey(key)) {
            return sharedRelics.get(key);
        }
        if (redRelics.containsKey(key)) {
            return redRelics.get(key);
        }
        if (greenRelics.containsKey(key)) {
            return greenRelics.get(key);
        }
        if (blueRelics.containsKey(key)) {
            return blueRelics.get(key);
        }
        if (purpleRelics.containsKey(key)) {
            return purpleRelics.get(key);
        }
        return new Circlet();
    }

    public static boolean isARelic(String key) {
        return sharedRelics.containsKey(key) || redRelics.containsKey(key) || greenRelics.containsKey(key) || blueRelics.containsKey(key) || purpleRelics.containsKey(key);
    }

    public static void populateRelicPool(ArrayList<String> pool, AbstractRelic.RelicTier tier, AbstractPlayer.PlayerClass c) {
        for (Map.Entry<String, AbstractRelic> r : sharedRelics.entrySet()) {
            if (r.getValue().tier == tier && (!UnlockTracker.isRelicLocked(r.getKey()) || Settings.treatEverythingAsUnlocked())) {
                pool.add(r.getKey());
            }
        }
        switch (c) {
            case IRONCLAD:
                for (Map.Entry<String, AbstractRelic> r2 : redRelics.entrySet()) {
                    if (r2.getValue().tier == tier && (!UnlockTracker.isRelicLocked(r2.getKey()) || Settings.treatEverythingAsUnlocked())) {
                        pool.add(r2.getKey());
                    }
                }
                return;
            case THE_SILENT:
                for (Map.Entry<String, AbstractRelic> r3 : greenRelics.entrySet()) {
                    if (r3.getValue().tier == tier && (!UnlockTracker.isRelicLocked(r3.getKey()) || Settings.treatEverythingAsUnlocked())) {
                        pool.add(r3.getKey());
                    }
                }
                return;
            case DEFECT:
                for (Map.Entry<String, AbstractRelic> r4 : blueRelics.entrySet()) {
                    if (r4.getValue().tier == tier && (!UnlockTracker.isRelicLocked(r4.getKey()) || Settings.treatEverythingAsUnlocked())) {
                        pool.add(r4.getKey());
                    }
                }
                return;
            case WATCHER:
                for (Map.Entry<String, AbstractRelic> r5 : purpleRelics.entrySet()) {
                    if (r5.getValue().tier == tier && (!UnlockTracker.isRelicLocked(r5.getKey()) || Settings.treatEverythingAsUnlocked())) {
                        pool.add(r5.getKey());
                    }
                }
                return;
            default:
                return;
        }
    }

    public static void addSharedRelics(ArrayList<AbstractRelic> relicPool) {
        if (Settings.isDev) {
            logger.info("[RELIC] Adding " + sharedRelics.size() + " shared relics...");
        }
        for (Map.Entry<String, AbstractRelic> r : sharedRelics.entrySet()) {
            relicPool.add(r.getValue());
        }
    }

    public static void addClassSpecificRelics(ArrayList<AbstractRelic> relicPool) {
        switch (AbstractDungeon.player.chosenClass) {
            case IRONCLAD:
                if (Settings.isDev) {
                    logger.info("[RELIC] Adding " + redRelics.size() + " red relics...");
                }
                for (Map.Entry<String, AbstractRelic> r : redRelics.entrySet()) {
                    relicPool.add(r.getValue());
                }
                return;
            case THE_SILENT:
                if (Settings.isDev) {
                    logger.info("[RELIC] Adding " + greenRelics.size() + " green relics...");
                }
                for (Map.Entry<String, AbstractRelic> r2 : greenRelics.entrySet()) {
                    relicPool.add(r2.getValue());
                }
                return;
            case DEFECT:
                if (Settings.isDev) {
                    logger.info("[RELIC] Adding " + blueRelics.size() + " blue relics...");
                }
                for (Map.Entry<String, AbstractRelic> r3 : blueRelics.entrySet()) {
                    relicPool.add(r3.getValue());
                }
                return;
            case WATCHER:
                if (Settings.isDev) {
                    logger.info("[RELIC] Adding " + purpleRelics.size() + " purple relics...");
                }
                for (Map.Entry<String, AbstractRelic> r4 : purpleRelics.entrySet()) {
                    relicPool.add(r4.getValue());
                }
                return;
            default:
                return;
        }
    }

    public static void uploadRelicData() {
        ArrayList<String> data = new ArrayList<>();
        for (Map.Entry<String, AbstractRelic> r : sharedRelics.entrySet()) {
            data.add(r.getValue().gameDataUploadData("All"));
        }
        for (Map.Entry<String, AbstractRelic> r2 : redRelics.entrySet()) {
            data.add(r2.getValue().gameDataUploadData("Red"));
        }
        for (Map.Entry<String, AbstractRelic> r3 : greenRelics.entrySet()) {
            data.add(r3.getValue().gameDataUploadData("Green"));
        }
        for (Map.Entry<String, AbstractRelic> r4 : blueRelics.entrySet()) {
            data.add(r4.getValue().gameDataUploadData("Blue"));
        }
        for (Map.Entry<String, AbstractRelic> r5 : purpleRelics.entrySet()) {
            data.add(r5.getValue().gameDataUploadData("Purple"));
        }
        BotDataUploader.uploadDataAsync(BotDataUploader.GameDataType.RELIC_DATA, AbstractRelic.gameDataUploadHeader(), data);
    }

    public static ArrayList<AbstractRelic> sortByName(ArrayList<AbstractRelic> group, boolean ascending) {
        ArrayList<AbstractRelic> tmp = new ArrayList<>();
        Iterator<AbstractRelic> it = group.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            int addIndex = 0;
            Iterator<AbstractRelic> it2 = tmp.iterator();
            while (it2.hasNext()) {
                AbstractRelic r2 = it2.next();
                if (!ascending) {
                    if (r.name.compareTo(r2.name) < 0) {
                        break;
                    }
                    addIndex++;
                } else if (r.name.compareTo(r2.name) > 0) {
                    break;
                } else {
                    addIndex++;
                }
            }
            tmp.add(addIndex, r);
        }
        return tmp;
    }

    public static ArrayList<AbstractRelic> sortByStatus(ArrayList<AbstractRelic> group, boolean ascending) {
        String a;
        String b;
        String a2;
        String b2;
        ArrayList<AbstractRelic> tmp = new ArrayList<>();
        Iterator<AbstractRelic> it = group.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            int addIndex = 0;
            Iterator<AbstractRelic> it2 = tmp.iterator();
            while (it2.hasNext()) {
                AbstractRelic r2 = it2.next();
                if (!ascending) {
                    if (UnlockTracker.isRelicLocked(r.relicId)) {
                        a2 = "LOCKED";
                    } else if (UnlockTracker.isRelicSeen(r.relicId)) {
                        a2 = "UNSEEN";
                    } else {
                        a2 = "SEEN";
                    }
                    if (UnlockTracker.isRelicLocked(r2.relicId)) {
                        b2 = "LOCKED";
                    } else if (UnlockTracker.isRelicSeen(r2.relicId)) {
                        b2 = "UNSEEN";
                    } else {
                        b2 = "SEEN";
                    }
                    if (a2.compareTo(b2) > 0) {
                        break;
                    }
                    addIndex++;
                } else {
                    if (UnlockTracker.isRelicLocked(r.relicId)) {
                        a = "LOCKED";
                    } else if (UnlockTracker.isRelicSeen(r.relicId)) {
                        a = "UNSEEN";
                    } else {
                        a = "SEEN";
                    }
                    if (UnlockTracker.isRelicLocked(r2.relicId)) {
                        b = "LOCKED";
                    } else if (UnlockTracker.isRelicSeen(r2.relicId)) {
                        b = "UNSEEN";
                    } else {
                        b = "SEEN";
                    }
                    if (a.compareTo(b) < 0) {
                        break;
                    }
                    addIndex++;
                }
            }
            tmp.add(addIndex, r);
        }
        return tmp;
    }

    public static void unlockAndSeeAllRelics() {
        Iterator<String> it = UnlockTracker.lockedRelics.iterator();
        while (it.hasNext()) {
            String s = it.next();
            UnlockTracker.hardUnlockOverride(s);
        }
        for (Map.Entry<String, AbstractRelic> r : sharedRelics.entrySet()) {
            UnlockTracker.markRelicAsSeen(r.getKey());
        }
        for (Map.Entry<String, AbstractRelic> r2 : redRelics.entrySet()) {
            UnlockTracker.markRelicAsSeen(r2.getKey());
        }
        for (Map.Entry<String, AbstractRelic> r3 : greenRelics.entrySet()) {
            UnlockTracker.markRelicAsSeen(r3.getKey());
        }
        for (Map.Entry<String, AbstractRelic> r4 : blueRelics.entrySet()) {
            UnlockTracker.markRelicAsSeen(r4.getKey());
        }
        for (Map.Entry<String, AbstractRelic> r5 : purpleRelics.entrySet()) {
            UnlockTracker.markRelicAsSeen(r5.getKey());
        }
    }
}