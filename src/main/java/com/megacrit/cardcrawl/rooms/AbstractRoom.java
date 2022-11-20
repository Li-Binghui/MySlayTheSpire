package com.megacrit.cardcrawl.rooms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAtEndOfTurnAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EnableEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.common.EndTurnAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAndEnableControlsAction;
import com.megacrit.cardcrawl.actions.common.MonsterStartTurnAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.mods.Careless;
import com.megacrit.cardcrawl.daily.mods.ControlledChaos;
import com.megacrit.cardcrawl.daily.mods.CursedRun;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.DevInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.BlessingOfTheForge;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.relics.MeatOnTheBone;
import com.megacrit.cardcrawl.relics.WhiteBeast;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.screens.stats.AchievementGrid;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.ui.buttons.CancelButton;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.GameSavedEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.combat.BattleStartEffect;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/AbstractRoom.class */
public abstract class AbstractRoom implements Disposable {
    public RoomPhase phase;
    public MonsterGroup monsters;
    private static final float END_TURN_WAIT_DURATION = 1.2f;
    protected String mapSymbol;
    protected Texture mapImg;
    protected Texture mapImgOutline;
    private static final int BLIZZARD_POTION_MOD_AMT = 10;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbstractRoom");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final Logger logger = LogManager.getLogger(AbstractRoom.class.getName());
    public static int blizzardPotionMod = 0;
    public static float waitTimer = 0.0f;
    public ArrayList<AbstractPotion> potions = new ArrayList<>();
    public ArrayList<AbstractRelic> relics = new ArrayList<>();
    public ArrayList<RewardItem> rewards = new ArrayList<>();
    public SoulGroup souls = new SoulGroup();
    public AbstractEvent event = null;
    private float endBattleTimer = 0.0f;
    public float rewardPopOutTimer = 1.0f;
    public boolean isBattleOver = false;
    public boolean cannotLose = false;
    public boolean eliteTrigger = false;
    public boolean mugged = false;
    public boolean smoked = false;
    public boolean combatEvent = false;
    public boolean rewardAllowed = true;
    public boolean rewardTime = false;
    public boolean skipMonsterTurn = false;
    public int baseRareCardChance = 3;
    public int baseUncommonCardChance = 37;
    public int rareCardChance = this.baseRareCardChance;
    public int uncommonCardChance = this.baseUncommonCardChance;

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/AbstractRoom$RoomPhase.class */
    public enum RoomPhase {
        COMBAT,
        EVENT,
        COMPLETE,
        INCOMPLETE
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/AbstractRoom$RoomType.class */
    public enum RoomType {
        SHOP,
        MONSTER,
        SHRINE,
        TREASURE,
        EVENT,
        BOSS
    }

    public abstract void onPlayerEntry();

    public final Texture getMapImg() {
        //todo 直接加载图片 event
        return ImageMaster.loadImage("images/ui/map/event.png");
    }

    public final Texture getMapImgOutline() {
        //todo 直接加载图片 eventOutline
        return ImageMaster.loadImage("images/ui/map/eventOutline.png");
    }

    public final void setMapImg(Texture img, Texture imgOutline) {
        this.mapImg = img;
        this.mapImgOutline = imgOutline;
    }

    public void applyEmeraldEliteBuff() {
    }

    public void playBGM(String key) {
        CardCrawlGame.music.playTempBGM(key);
    }

    public void playBgmInstantly(String key) {
        CardCrawlGame.music.playTempBgmInstantly(key);
    }

    public final String getMapSymbol() {
        return this.mapSymbol;
    }

    public final void setMapSymbol(String newSymbol) {
        this.mapSymbol = newSymbol;
    }

    public AbstractCard.CardRarity getCardRarity(int roll) {
        return getCardRarity(roll, true);
    }

    public AbstractCard.CardRarity getCardRarity(int roll, boolean useAlternation) {
        this.rareCardChance = this.baseRareCardChance;
        this.uncommonCardChance = this.baseUncommonCardChance;
        if (useAlternation) {
            alterCardRarityProbabilities();
        }
        if (roll < this.rareCardChance) {
            if (roll >= this.baseRareCardChance) {
                Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
                while (it.hasNext()) {
                    AbstractRelic r = it.next();
                    if (r.changeRareCardRewardChance(this.baseRareCardChance) > this.baseRareCardChance) {
                        r.flash();
                    }
                }
            }
            return AbstractCard.CardRarity.RARE;
        } else if (roll >= this.rareCardChance + this.uncommonCardChance) {
            return AbstractCard.CardRarity.COMMON;
        } else {
            if (roll >= this.baseRareCardChance + this.baseUncommonCardChance) {
                Iterator<AbstractRelic> it2 = AbstractDungeon.player.relics.iterator();
                while (it2.hasNext()) {
                    AbstractRelic r2 = it2.next();
                    if (r2.changeUncommonCardRewardChance(this.baseUncommonCardChance) > this.baseUncommonCardChance) {
                        r2.flash();
                    }
                }
            }
            return AbstractCard.CardRarity.UNCOMMON;
        }
    }

    public void alterCardRarityProbabilities() {
        Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            this.rareCardChance = r.changeRareCardRewardChance(this.rareCardChance);
        }
        Iterator<AbstractRelic> it2 = AbstractDungeon.player.relics.iterator();
        while (it2.hasNext()) {
            AbstractRelic r2 = it2.next();
            this.uncommonCardChance = r2.changeUncommonCardRewardChance(this.uncommonCardChance);
        }
    }

    public void updateObjects() {
        this.souls.update();
        Iterator<AbstractPotion> i = this.potions.iterator();
        while (i.hasNext()) {
            AbstractPotion tmpPotion = i.next();
            tmpPotion.update();
            if (tmpPotion.isObtained) {
                i.remove();
            }
        }
        Iterator<AbstractRelic> i2 = this.relics.iterator();
        while (i2.hasNext()) {
            AbstractRelic relic = i2.next();
            relic.update();
            if (relic.isDone) {
                i2.remove();
            }
        }
    }

    public void update() {
        if (!AbstractDungeon.isScreenUp && InputHelper.pressedEscape && AbstractDungeon.overlayMenu.cancelButton.current_x == CancelButton.HIDE_X) {
            AbstractDungeon.settingsScreen.open();
        }
        if (Settings.isDebug) {
            if (InputHelper.justClickedRight) {
                AbstractDungeon.player.obtainPotion(new BlessingOfTheForge());
                AbstractDungeon.scene.randomizeScene();
            }
            if (Gdx.input.isKeyJustPressed(49)) {
                AbstractDungeon.player.increaseMaxOrbSlots(1, true);
            }
            if (DevInputActionSet.gainGold.isJustPressed()) {
                AbstractDungeon.player.gainGold(100);
            }
        }
        switch (this.phase) {
            case EVENT:
                this.event.updateDialog();
                break;
            case COMBAT:
                this.monsters.update();
                if (waitTimer > 0.0f) {
                    if (AbstractDungeon.actionManager.currentAction != null || !AbstractDungeon.actionManager.isEmpty()) {
                        AbstractDungeon.actionManager.update();
                    } else {
                        waitTimer -= Gdx.graphics.getDeltaTime();
                    }
                    if (waitTimer <= 0.0f) {
                        AbstractDungeon.actionManager.turnHasEnded = true;
                        if (!AbstractDungeon.isScreenUp) {
                            AbstractDungeon.topLevelEffects.add(new BattleStartEffect(false));
                        }
                        AbstractDungeon.actionManager.addToBottom(new GainEnergyAndEnableControlsAction(AbstractDungeon.player.energy.energyMaster));
                        AbstractDungeon.player.applyStartOfCombatPreDrawLogic();
                        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, AbstractDungeon.player.gameHandSize));
                        AbstractDungeon.actionManager.addToBottom(new EnableEndTurnButtonAction());
                        AbstractDungeon.overlayMenu.showCombatPanels();
                        AbstractDungeon.player.applyStartOfCombatLogic();
                        if (ModHelper.isModEnabled(Careless.ID)) {
                            Careless.modAction();
                        }
                        if (ModHelper.isModEnabled(ControlledChaos.ID)) {
                            ControlledChaos.modAction();
                        }
                        this.skipMonsterTurn = false;
                        AbstractDungeon.player.applyStartOfTurnRelics();
                        AbstractDungeon.player.applyStartOfTurnPostDrawRelics();
                        AbstractDungeon.player.applyStartOfTurnCards();
                        AbstractDungeon.player.applyStartOfTurnPowers();
                        AbstractDungeon.player.applyStartOfTurnOrbs();
                        AbstractDungeon.actionManager.useNextCombatActions();
                    }
                } else {
                    if (Settings.isDebug && DevInputActionSet.drawCard.isJustPressed()) {
                        AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
                    }
                    if (!AbstractDungeon.isScreenUp) {
                        AbstractDungeon.actionManager.update();
                        if (!this.monsters.areMonstersBasicallyDead() && AbstractDungeon.player.currentHealth > 0) {
                            AbstractDungeon.player.updateInput();
                        }
                    }
                    if (!AbstractDungeon.screen.equals(AbstractDungeon.CurrentScreen.HAND_SELECT)) {
                        AbstractDungeon.player.combatUpdate();
                    }
                    if (AbstractDungeon.player.isEndingTurn) {
                        endTurn();
                    }
                }
                if (this.isBattleOver && AbstractDungeon.actionManager.actions.isEmpty()) {
                    this.skipMonsterTurn = false;
                    this.endBattleTimer -= Gdx.graphics.getDeltaTime();
                    if (this.endBattleTimer < 0.0f) {
                        this.phase = RoomPhase.COMPLETE;
                        if (!(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) || !(CardCrawlGame.dungeon instanceof TheBeyond) || Settings.isEndless) {
                            CardCrawlGame.sound.play("VICTORY");
                        }
                        this.endBattleTimer = 0.0f;
                        if ((this instanceof MonsterRoomBoss) && !AbstractDungeon.loading_post_combat) {
                            if (!CardCrawlGame.loadingSave) {
                                if (Settings.isDailyRun) {
                                    addGoldToRewards(100);
                                } else {
                                    int tmp = 100 + AbstractDungeon.miscRng.random(-5, 5);
                                    if (AbstractDungeon.ascensionLevel >= 13) {
                                        addGoldToRewards(MathUtils.round(tmp * 0.75f));
                                    } else {
                                        addGoldToRewards(tmp);
                                    }
                                }
                            }
                            if (ModHelper.isModEnabled(CursedRun.ID)) {
                                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(AbstractDungeon.returnRandomCurse(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                            }
                        } else if ((this instanceof MonsterRoomElite) && !AbstractDungeon.loading_post_combat) {
                            if (CardCrawlGame.dungeon instanceof Exordium) {
                                CardCrawlGame.elites1Slain++;
                                logger.info("ELITES SLAIN " + CardCrawlGame.elites1Slain);
                            } else if (CardCrawlGame.dungeon instanceof TheCity) {
                                CardCrawlGame.elites2Slain++;
                                logger.info("ELITES SLAIN " + CardCrawlGame.elites2Slain);
                            } else if (CardCrawlGame.dungeon instanceof TheBeyond) {
                                CardCrawlGame.elites3Slain++;
                                logger.info("ELITES SLAIN " + CardCrawlGame.elites3Slain);
                            } else {
                                CardCrawlGame.elitesModdedSlain++;
                                logger.info("ELITES SLAIN " + CardCrawlGame.elitesModdedSlain);
                            }
                            if (!CardCrawlGame.loadingSave) {
                                if (Settings.isDailyRun) {
                                    addGoldToRewards(30);
                                } else {
                                    addGoldToRewards(AbstractDungeon.treasureRng.random(25, 35));
                                }
                            }
                        } else if ((this instanceof MonsterRoom) && !AbstractDungeon.getMonsters().haveMonstersEscaped()) {
                            CardCrawlGame.monstersSlain++;
                            logger.info("MONSTERS SLAIN " + CardCrawlGame.monstersSlain);
                            if (Settings.isDailyRun) {
                                addGoldToRewards(15);
                            } else {
                                addGoldToRewards(AbstractDungeon.treasureRng.random(10, 20));
                            }
                        }
                        if (!(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) || ((!(CardCrawlGame.dungeon instanceof TheBeyond) && !(CardCrawlGame.dungeon instanceof TheEnding)) || Settings.isEndless)) {
                            if (!AbstractDungeon.loading_post_combat) {
                                dropReward();
                                addPotionToRewards();
                            }
                            int card_seed_before_roll = AbstractDungeon.cardRng.counter;
                            int card_randomizer_before_roll = AbstractDungeon.cardBlizzRandomizer;
                            if (this.rewardAllowed) {
                                if (this.mugged) {
                                    AbstractDungeon.combatRewardScreen.openCombat(TEXT[0]);
                                } else if (this.smoked) {
                                    AbstractDungeon.combatRewardScreen.openCombat(TEXT[1], true);
                                } else {
                                    AbstractDungeon.combatRewardScreen.open();
                                }
                                if (CardCrawlGame.loadingSave || AbstractDungeon.loading_post_combat) {
                                    CardCrawlGame.loadingSave = false;
                                } else {
                                    SaveFile saveFile = new SaveFile(SaveFile.SaveType.POST_COMBAT);
                                    saveFile.card_seed_count = card_seed_before_roll;
                                    saveFile.card_random_seed_randomizer = card_randomizer_before_roll;
                                    if (this.combatEvent) {
                                        saveFile.event_seed_count--;
                                    }
                                    SaveAndContinue.save(saveFile);
                                    AbstractDungeon.effectList.add(new GameSavedEffect());
                                }
                                AbstractDungeon.loading_post_combat = false;
                            }
                        }
                    }
                }
                this.monsters.updateAnimations();
                break;
            case COMPLETE:
                if (!AbstractDungeon.isScreenUp) {
                    AbstractDungeon.actionManager.update();
                    if (this.event != null) {
                        this.event.updateDialog();
                    }
                    if (AbstractDungeon.actionManager.isEmpty() && !AbstractDungeon.isFadingOut) {
                        if (this.rewardPopOutTimer > 1.0f) {
                            this.rewardPopOutTimer = 1.0f;
                        }
                        this.rewardPopOutTimer -= Gdx.graphics.getDeltaTime();
                        if (this.rewardPopOutTimer < 0.0f) {
                            if (this.event != null) {
                                if (!(this.event instanceof AbstractImageEvent) && !this.event.hasFocus) {
                                    AbstractDungeon.overlayMenu.proceedButton.show();
                                    break;
                                }
                            } else {
                                AbstractDungeon.overlayMenu.proceedButton.show();
                                break;
                            }
                        }
                    }
                }
                break;
            case INCOMPLETE:
                break;
            default:
                logger.info("MISSING PHASE, bro");
                break;
        }
        AbstractDungeon.player.update();
        AbstractDungeon.player.updateAnimations();
    }

    public void endTurn() {
        AbstractDungeon.player.applyEndOfTurnTriggers();
        AbstractDungeon.actionManager.addToBottom(new ClearCardQueueAction());
        AbstractDungeon.actionManager.addToBottom(new DiscardAtEndOfTurnAction());
        Iterator<AbstractCard> it = AbstractDungeon.player.drawPile.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            c.resetAttributes();
        }
        Iterator<AbstractCard> it2 = AbstractDungeon.player.discardPile.group.iterator();
        while (it2.hasNext()) {
            AbstractCard c2 = it2.next();
            c2.resetAttributes();
        }
        Iterator<AbstractCard> it3 = AbstractDungeon.player.hand.group.iterator();
        while (it3.hasNext()) {
            AbstractCard c3 = it3.next();
            c3.resetAttributes();
        }
        if (AbstractDungeon.player.hoveredCard != null) {
            AbstractDungeon.player.hoveredCard.resetAttributes();
        }
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() { // from class: com.megacrit.cardcrawl.rooms.AbstractRoom.1
            @Override // com.megacrit.cardcrawl.actions.AbstractGameAction
            public void update() {
                addToBot(new EndTurnAction());
                addToBot(new WaitAction(AbstractRoom.END_TURN_WAIT_DURATION));
                if (!AbstractRoom.this.skipMonsterTurn) {
                    addToBot(new MonsterStartTurnAction());
                }
                AbstractDungeon.actionManager.monsterAttacksQueued = false;
                this.isDone = true;
            }
        });
        AbstractDungeon.player.isEndingTurn = false;
    }

    public void endBattle() {
        this.isBattleOver = true;
        if (AbstractDungeon.player.currentHealth == 1) {
            UnlockTracker.unlockAchievement(AchievementGrid.SHRUG_KEY);
        }
        if (AbstractDungeon.player.hasRelic(MeatOnTheBone.ID)) {
            AbstractDungeon.player.getRelic(MeatOnTheBone.ID).onTrigger();
        }
        AbstractDungeon.player.onVictory();
        this.endBattleTimer = 0.25f;
        int attackCount = 0;
        int skillCount = 0;
        Iterator<AbstractCard> it = AbstractDungeon.actionManager.cardsPlayedThisCombat.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AbstractCard c = it.next();
            if (c.type == AbstractCard.CardType.ATTACK) {
                attackCount = 0 + 1;
                break;
            } else if (c.type == AbstractCard.CardType.SKILL) {
                skillCount++;
            }
        }
        if (attackCount == 0 && !this.smoked) {
            UnlockTracker.unlockAchievement(AchievementGrid.COME_AT_ME_KEY);
        }
        if (skillCount == 0) {
        }
        if (!this.smoked && GameActionManager.damageReceivedThisCombat - GameActionManager.hpLossThisCombat <= 0 && (this instanceof MonsterRoomElite)) {
            CardCrawlGame.champion++;
        }
        CardCrawlGame.metricData.addEncounterData();
        AbstractDungeon.actionManager.clear();
        AbstractDungeon.player.inSingleTargetMode = false;
        AbstractDungeon.player.releaseCard();
        AbstractDungeon.player.hand.refreshHandLayout();
        AbstractDungeon.player.resetControllerValues();
        AbstractDungeon.overlayMenu.hideCombatPanels();
        if (!AbstractDungeon.player.stance.ID.equals(NeutralStance.STANCE_ID) && AbstractDungeon.player.stance != null) {
            AbstractDungeon.player.stance.stopIdleSfx();
        }
    }

    public void dropReward() {
    }

    public void render(SpriteBatch sb) {
        if ((this instanceof EventRoom) || (this instanceof VictoryRoom)) {
            if (this.event != null && (!(this.event instanceof AbstractImageEvent) || this.event.combatTime)) {
                this.event.renderRoomEventPanel(sb);
                if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.VICTORY) {
                    AbstractDungeon.player.render(sb);
                }
            }
        } else if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.BOSS_REWARD) {
            AbstractDungeon.player.render(sb);
        }
        if (!(AbstractDungeon.getCurrRoom() instanceof RestRoom)) {
            if (!(this.monsters == null || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH)) {
                this.monsters.render(sb);
            }
            if (this.phase == RoomPhase.COMBAT) {
                AbstractDungeon.player.renderPlayerBattleUi(sb);
            }
            Iterator<AbstractPotion> it = this.potions.iterator();
            while (it.hasNext()) {
                AbstractPotion i = it.next();
                if (!i.isObtained) {
                    i.render(sb);
                }
            }
        }
        Iterator<AbstractRelic> it2 = this.relics.iterator();
        while (it2.hasNext()) {
            AbstractRelic r = it2.next();
            r.render(sb);
        }
        renderTips(sb);
    }

    public void renderAboveTopPanel(SpriteBatch sb) {
        Iterator<AbstractPotion> it = this.potions.iterator();
        while (it.hasNext()) {
            AbstractPotion i = it.next();
            if (i.isObtained) {
                i.render(sb);
            }
        }
        this.souls.render(sb);
        if (Settings.isInfo) {
            String msg = "[GAME MODE DATA]\n isDaily: " + Settings.isDailyRun + "\n isSpecialSeed: " + Settings.isTrial + "\n isAscension: " + AbstractDungeon.isAscensionMode + "\n\n[CARDGROUPS]\n Deck: " + AbstractDungeon.player.masterDeck.size() + "\n Draw Pile: " + AbstractDungeon.player.drawPile.size() + "\n Discard Pile: " + AbstractDungeon.player.discardPile.size() + "\n Exhaust Pile: " + AbstractDungeon.player.exhaustPile.size() + "\n\n[ACTION MANAGER]\n Phase: " + AbstractDungeon.actionManager.phase.name() + "\n turnEnded: " + AbstractDungeon.actionManager.turnHasEnded + "\n numTurns: " + GameActionManager.turn + "\n\n[Misc]\n Publisher Connection: " + CardCrawlGame.publisherIntegration.isInitialized() + "\n CUR_SCREEN: " + AbstractDungeon.screen.name() + "\n Controller Mode: " + Settings.isControllerMode + "\n isFadingOut: " + AbstractDungeon.isFadingOut + "\n isScreenUp: " + AbstractDungeon.isScreenUp + "\n Particle Count: " + AbstractDungeon.effectList.size();
            FontHelper.renderFontCenteredHeight(sb, FontHelper.tipBodyFont, msg, 30.0f, Settings.HEIGHT * 0.5f, Color.WHITE);
        }
    }

    public void renderTips(SpriteBatch sb) {
    }

    public void spawnRelicAndObtain(float x, float y, AbstractRelic relic) {
        if (relic.relicId != Circlet.ID || !AbstractDungeon.player.hasRelic(Circlet.ID)) {
            relic.spawn(x, y);
            this.relics.add(relic);
            relic.obtain();
            relic.isObtained = true;
            relic.isAnimating = false;
            relic.isDone = false;
            relic.flash();
            return;
        }
        AbstractRelic circ = AbstractDungeon.player.getRelic(Circlet.ID);
        circ.counter++;
        circ.flash();
    }

    public void spawnBlightAndObtain(float x, float y, AbstractBlight blight) {
        blight.spawn(x, y);
        blight.obtain();
        blight.isObtained = true;
        blight.isAnimating = false;
        blight.isDone = false;
        blight.flash();
    }

    public void applyEndOfTurnRelics() {
        Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            r.onPlayerEndTurn();
        }
        Iterator<AbstractBlight> it2 = AbstractDungeon.player.blights.iterator();
        while (it2.hasNext()) {
            AbstractBlight b = it2.next();
            b.onPlayerEndTurn();
        }
    }

    public void applyEndOfTurnPreCardPowers() {
        Iterator<AbstractPower> it = AbstractDungeon.player.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            p.atEndOfTurnPreEndTurnCards(true);
        }
    }

    public void addRelicToRewards(AbstractRelic.RelicTier tier) {
        this.rewards.add(new RewardItem(AbstractDungeon.returnRandomRelic(tier)));
    }

    public void addSapphireKey(RewardItem item) {
        this.rewards.add(new RewardItem(item, RewardItem.RewardType.SAPPHIRE_KEY));
    }

    public void removeOneRelicFromRewards() {
        Iterator<RewardItem> i = this.rewards.iterator();
        while (i.hasNext()) {
            RewardItem rewardItem = i.next();
            if (rewardItem.type == RewardItem.RewardType.RELIC) {
                i.remove();
                if (i.hasNext() && rewardItem.relicLink == i.next()) {
                    i.remove();
                    return;
                }
                return;
            }
        }
    }

    public void addNoncampRelicToRewards(AbstractRelic.RelicTier tier) {
        this.rewards.add(new RewardItem(AbstractDungeon.returnRandomNonCampfireRelic(tier)));
    }

    public void addRelicToRewards(AbstractRelic relic) {
        this.rewards.add(new RewardItem(relic));
    }

    public void addPotionToRewards(AbstractPotion potion) {
        this.rewards.add(new RewardItem(potion));
    }

    public void addCardToRewards() {
        RewardItem cardReward = new RewardItem();
        if (cardReward.cards.size() > 0) {
            this.rewards.add(cardReward);
        }
    }

    public void addPotionToRewards() {
        int chance = 0;
        if (this instanceof MonsterRoomElite) {
            chance = 40 + blizzardPotionMod;
        } else if (this instanceof MonsterRoom) {
            if (!AbstractDungeon.getMonsters().haveMonstersEscaped()) {
                chance = 40 + blizzardPotionMod;
            }
        } else if (this instanceof EventRoom) {
            chance = 40 + blizzardPotionMod;
        }
        if (AbstractDungeon.player.hasRelic(WhiteBeast.ID)) {
            chance = 100;
        }
        if (this.rewards.size() >= 4) {
            chance = 0;
        }
        logger.info("POTION CHANCE: " + chance);
        if (AbstractDungeon.potionRng.random(0, 99) < chance || Settings.isDebug) {
            CardCrawlGame.metricData.potions_floor_spawned.add(Integer.valueOf(AbstractDungeon.floorNum));
            this.rewards.add(new RewardItem(AbstractDungeon.returnRandomPotion()));
            blizzardPotionMod -= 10;
            return;
        }
        blizzardPotionMod += 10;
    }

    public void addGoldToRewards(int gold) {
        Iterator<RewardItem> it = this.rewards.iterator();
        while (it.hasNext()) {
            RewardItem i = it.next();
            if (i.type == RewardItem.RewardType.GOLD) {
                i.incrementGold(gold);
                return;
            }
        }
        this.rewards.add(new RewardItem(gold));
    }

    public void addStolenGoldToRewards(int gold) {
        Iterator<RewardItem> it = this.rewards.iterator();
        while (it.hasNext()) {
            RewardItem i = it.next();
            if (i.type == RewardItem.RewardType.STOLEN_GOLD) {
                i.incrementGold(gold);
                return;
            }
        }
        this.rewards.add(new RewardItem(gold, true));
    }

    public boolean isBattleEnding() {
        if (this.isBattleOver) {
            return true;
        }
        if (this.monsters != null) {
            return this.monsters.areMonstersBasicallyDead();
        }
        return false;
    }

    public void renderEventTexts(SpriteBatch sb) {
        if (this.event != null) {
            this.event.renderText(sb);
        }
    }

    public void clearEvent() {
        if (this.event != null) {
            this.event.imageEventText.clear();
            this.event.roomEventText.clear();
        }
    }

    public void eventControllerInput() {
        if (!Settings.isControllerMode || AbstractDungeon.getCurrRoom().event == null || AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || AbstractDungeon.topPanel.potionUi.targetMode || AbstractDungeon.player.viewingRelics) {
            return;
        }
        if (!RoomEventDialog.optionList.isEmpty()) {
            boolean anyHovered = false;
            int index = 0;
            Iterator<LargeDialogOptionButton> it = RoomEventDialog.optionList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                LargeDialogOptionButton o = it.next();
                if (o.hb.hovered) {
                    anyHovered = true;
                    break;
                }
                index++;
            }
            if (!anyHovered) {
                Gdx.input.setCursorPosition((int) RoomEventDialog.optionList.get(0).hb.cX, Settings.HEIGHT - ((int) RoomEventDialog.optionList.get(0).hb.cY));
            } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
                int index2 = index + 1;
                if (index2 > RoomEventDialog.optionList.size() - 1) {
                    index2 = 0;
                }
                Gdx.input.setCursorPosition((int) RoomEventDialog.optionList.get(index2).hb.cX, Settings.HEIGHT - ((int) RoomEventDialog.optionList.get(index2).hb.cY));
            } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
                int index3 = index - 1;
                if (index3 < 0) {
                    index3 = RoomEventDialog.optionList.size() - 1;
                }
                Gdx.input.setCursorPosition((int) RoomEventDialog.optionList.get(index3).hb.cX, Settings.HEIGHT - ((int) RoomEventDialog.optionList.get(index3).hb.cY));
            }
        } else if (!this.event.imageEventText.optionList.isEmpty()) {
            boolean anyHovered2 = false;
            int index4 = 0;
            Iterator<LargeDialogOptionButton> it2 = this.event.imageEventText.optionList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                LargeDialogOptionButton o2 = it2.next();
                if (o2.hb.hovered) {
                    anyHovered2 = true;
                    break;
                }
                index4++;
            }
            if (!anyHovered2) {
                Gdx.input.setCursorPosition((int) this.event.imageEventText.optionList.get(0).hb.cX, Settings.HEIGHT - ((int) this.event.imageEventText.optionList.get(0).hb.cY));
            } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
                int index5 = index4 + 1;
                if (index5 > this.event.imageEventText.optionList.size() - 1) {
                    index5 = 0;
                }
                Gdx.input.setCursorPosition((int) this.event.imageEventText.optionList.get(index5).hb.cX, Settings.HEIGHT - ((int) this.event.imageEventText.optionList.get(index5).hb.cY));
            } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
                int index6 = index4 - 1;
                if (index6 < 0) {
                    index6 = this.event.imageEventText.optionList.size() - 1;
                }
                Gdx.input.setCursorPosition((int) this.event.imageEventText.optionList.get(index6).hb.cX, Settings.HEIGHT - ((int) this.event.imageEventText.optionList.get(index6).hb.cY));
            }
        }
    }

    public void addCardReward(RewardItem rewardItem) {
        if (!rewardItem.cards.isEmpty()) {
            this.rewards.add(rewardItem);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.event != null) {
            this.event.dispose();
        }
        if (this.monsters != null) {
            Iterator<AbstractMonster> it = this.monsters.monsters.iterator();
            while (it.hasNext()) {
                AbstractMonster m = it.next();
                m.dispose();
            }
        }
    }
}