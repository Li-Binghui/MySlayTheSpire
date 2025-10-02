package com.megacrit.cardcrawl.ui.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.events.beyond.MindBloom;
import com.megacrit.cardcrawl.events.beyond.MysteriousSphere;
import com.megacrit.cardcrawl.events.city.Colosseum;
import com.megacrit.cardcrawl.events.city.MaskedBandits;
import com.megacrit.cardcrawl.events.exordium.DeadAdventurer;
import com.megacrit.cardcrawl.events.exordium.Mushrooms;
import com.megacrit.cardcrawl.events.shrines.Lab;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.neow.NeowRoom;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
import com.megacrit.cardcrawl.rooms.TrueVictoryRoom;
import com.megacrit.cardcrawl.rooms.VictoryRoom;
import com.megacrit.cardcrawl.ui.FtueTip;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/ui/buttons/ProceedButton.class */
public class ProceedButton {
    private static final int W = 512;
    private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Rewards Tip");
    public static final String[] MSG = tutorialStrings.TEXT;
    public static final String[] LABEL = tutorialStrings.LABEL;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Proceed Button");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final Color HOVER_BLEND_COLOR = new Color(1.0f, 1.0f, 1.0f, 0.3f);
    private static final float SHOW_X = 1670.0f * Settings.xScale;
    private static final float DRAW_Y = 320.0f * Settings.scale;
    private static final float HIDE_X = SHOW_X + (500.0f * Settings.scale);
    private static final float HITBOX_W = 280.0f * Settings.scale;
    private static final float HITBOX_H = 156.0f * Settings.scale;
    private static final float CLICKABLE_DIST = 25.0f * Settings.scale;
    private float current_x = HIDE_X;
    private float current_y = DRAW_Y;
    private float target_x = this.current_x;
    private float wavyTimer = 0.0f;
    private boolean isHidden = true;
    private String label = TEXT[0];
    private BitmapFont font = FontHelper.buttonLabelFont;
    private boolean callingBellCheck = true;
    private Hitbox hb = new Hitbox(SHOW_X, this.current_y, HITBOX_W, HITBOX_H);
    public boolean isHovered = false;

    public ProceedButton() {
        this.hb.move(SHOW_X, this.current_y);
    }

    public void setLabel(String newLabel) {
        this.label = newLabel;
        if (FontHelper.getSmartWidth(FontHelper.buttonLabelFont, this.label, 9999.0f, 0.0f) > 160.0f * Settings.scale) {
            this.font = FontHelper.topPanelInfoFont;
        } else {
            this.font = FontHelper.buttonLabelFont;
        }
    }

    public void update() {
        if (!this.isHidden) {
            this.wavyTimer += Gdx.graphics.getDeltaTime() * 3.0f;
            if (this.current_x - SHOW_X < CLICKABLE_DIST) {
                this.hb.update();
            }
            this.isHovered = this.hb.hovered;
            if (this.hb.hovered && InputHelper.justClickedLeft) {
                CardCrawlGame.sound.play("UI_CLICK_1");
                this.hb.clickStarted = true;
            }
            if (this.hb.justHovered && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
                Iterator<RewardItem> it = AbstractDungeon.combatRewardScreen.rewards.iterator();
                while (it.hasNext()) {
                    RewardItem i = it.next();
                    i.flash();
                }
            }
            if (this.hb.clicked || CInputActionSet.proceed.isJustPressed()) {
                this.hb.clicked = false;
                AbstractRoom currentRoom = AbstractDungeon.getCurrRoom();
                if (currentRoom instanceof MonsterRoomBoss) {
                    if (AbstractDungeon.id.equals(TheBeyond.ID)) {
                        if (AbstractDungeon.ascensionLevel >= 20 && AbstractDungeon.bossList.size() == 2) {
                            goToDoubleBoss();
                        } else if (!Settings.isEndless) {
                            goToVictoryRoomOrTheDoor();
                        }
                    } else if (AbstractDungeon.id.equals(TheEnding.ID)) {
                        goToTrueVictoryRoom();
                    }
                }
                if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD && !(AbstractDungeon.getCurrRoom() instanceof TreasureRoomBoss)) {
                    if (currentRoom instanceof MonsterRoomBoss) {
                        goToTreasureRoom();
                    } else if (currentRoom instanceof EventRoom) {
                        if (!(currentRoom.event instanceof Mushrooms) && !(currentRoom.event instanceof MaskedBandits) && !(currentRoom.event instanceof DeadAdventurer) && !(currentRoom.event instanceof Lab) && !(currentRoom.event instanceof Colosseum) && !(currentRoom.event instanceof MysteriousSphere) && !(currentRoom.event instanceof MindBloom)) {
                            AbstractDungeon.closeCurrentScreen();
                            hide();
                        } else {
                            AbstractDungeon.closeCurrentScreen();
                            AbstractDungeon.dungeonMapScreen.open(false);
                            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
                        }
                    } else {
                        if (!TipTracker.tips.get(TipTracker.CARD_REWARD_TIP).booleanValue() && !AbstractDungeon.combatRewardScreen.rewards.isEmpty()) {
                            if (Settings.isConsoleBuild) {
                                AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[1], Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, FtueTip.TipType.CARD_REWARD);
                                TipTracker.neverShowAgain(TipTracker.CARD_REWARD_TIP);
                                return;
                            } else {
                                AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[0], Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, FtueTip.TipType.CARD_REWARD);
                                TipTracker.neverShowAgain(TipTracker.CARD_REWARD_TIP);
                                return;
                            }
                        }
                        int relicCount = 0;
                        Iterator<RewardItem> it2 = AbstractDungeon.combatRewardScreen.rewards.iterator();
                        while (it2.hasNext()) {
                            RewardItem i2 = it2.next();
                            if (i2.type == RewardItem.RewardType.RELIC) {
                                relicCount++;
                            }
                        }
                        if (relicCount == 3 && this.callingBellCheck) {
                            this.callingBellCheck = false;
                            if (!AbstractDungeon.combatRewardScreen.rewards.isEmpty()) {
                                AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[0], Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, FtueTip.TipType.CARD_REWARD);
                                return;
                            }
                        }
                        if (AbstractDungeon.getCurrRoom() instanceof NeowRoom) {
                            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                        }
                        if (!AbstractDungeon.combatRewardScreen.hasTakenAll) {
                            Iterator<RewardItem> it3 = AbstractDungeon.combatRewardScreen.rewards.iterator();
                            while (it3.hasNext()) {
                                RewardItem item = it3.next();
                                
                                if (item.type == RewardItem.RewardType.CARD) {
                                    item.recordCardSkipMetrics();
                                }
                            }
                        }
                        AbstractDungeon.closeCurrentScreen();
                        AbstractDungeon.dungeonMapScreen.open(false);
                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
                    }
                } else if (currentRoom instanceof TreasureRoomBoss) {
                    if (Settings.isDemo) {
                        goToDemoVictoryRoom();
                    } else {
                        goToNextDungeon(currentRoom);
                    }
                } else if (!(currentRoom instanceof MonsterRoomBoss)) {
                    AbstractDungeon.dungeonMapScreen.open(false);
                    hide();
                }
            }
        }
        if (this.current_x != this.target_x) {
            this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0f);
            if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
                this.current_x = this.target_x;
            }
        }
    }

    private void goToTreasureRoom() {
        CardCrawlGame.music.fadeOutTempBGM();
        MapRoomNode node = new MapRoomNode(-1, 15);
        node.room = new TreasureRoomBoss();
        AbstractDungeon.nextRoom = node;
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.nextRoomTransitionStart();
        hide();
    }

    private void goToTrueVictoryRoom() {
        CardCrawlGame.music.fadeOutBGM();
        MapRoomNode node = new MapRoomNode(3, 4);
        node.room = new TrueVictoryRoom();
        AbstractDungeon.nextRoom = node;
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.nextRoomTransitionStart();
        hide();
    }

    private void goToVictoryRoomOrTheDoor() {
        CardCrawlGame.music.fadeOutBGM();
        CardCrawlGame.music.fadeOutTempBGM();
        MapRoomNode node = new MapRoomNode(-1, 15);
        node.room = new VictoryRoom(VictoryRoom.EventType.HEART);
        AbstractDungeon.nextRoom = node;
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.nextRoomTransitionStart();
        hide();
    }

    private void goToDoubleBoss() {
        AbstractDungeon.bossKey = AbstractDungeon.bossList.get(0);
        CardCrawlGame.music.fadeOutBGM();
        CardCrawlGame.music.fadeOutTempBGM();
        MapRoomNode node = new MapRoomNode(-1, 15);
        node.room = new MonsterRoomBoss();
        AbstractDungeon.nextRoom = node;
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.nextRoomTransitionStart();
        hide();
    }

    private void goToDemoVictoryRoom() {
        MapRoomNode node = new MapRoomNode(-1, 15);
        node.room = new VictoryRoom(VictoryRoom.EventType.NONE);
        AbstractDungeon.nextRoom = node;
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.nextRoomTransitionStart();
        hide();
    }

    private void goToNextDungeon(AbstractRoom room) {
        if (!((TreasureRoomBoss) room).choseRelic) {
            AbstractDungeon.bossRelicScreen.noPick();
        }
        int relicCount = 0;
        Iterator<RewardItem> it = AbstractDungeon.combatRewardScreen.rewards.iterator();
        while (it.hasNext()) {
            RewardItem i = it.next();
            if (i.type == RewardItem.RewardType.RELIC) {
                relicCount++;
            }
        }
        if (relicCount == 3 && this.callingBellCheck) {
            this.callingBellCheck = false;
            if (!AbstractDungeon.combatRewardScreen.rewards.isEmpty()) {
                AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[0], Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, FtueTip.TipType.CARD_REWARD);
                return;
            }
        }
        CardCrawlGame.music.fadeOutBGM();
        CardCrawlGame.music.fadeOutTempBGM();
        AbstractDungeon.fadeOut();
        AbstractDungeon.isDungeonBeaten = true;
        hide();
    }

    public void hideInstantly() {
        this.current_x = HIDE_X;
        this.target_x = HIDE_X;
        this.isHidden = true;
    }

    public void hide() {
        if (!this.isHidden) {
            this.target_x = HIDE_X;
            this.isHidden = true;
        }
    }

    public void show() {
        if (this.isHidden) {
            this.target_x = SHOW_X;
            this.isHidden = false;
        }
    }

    public void render(SpriteBatch sb) {
        if (Settings.hideEndTurn) {
            return;
        }
        sb.setColor(Color.WHITE);
        renderShadow(sb);
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            sb.setColor(new Color(1.0f, 0.9f, 0.2f, (MathUtils.cos(this.wavyTimer) / 5.0f) + 0.6f));
        } else {
            sb.setColor(Settings.QUARTER_TRANSPARENT_BLACK_COLOR);
        }
        sb.draw(ImageMaster.PROCEED_BUTTON_OUTLINE, this.current_x - 256.0f, this.current_y - 256.0f, 256.0f, 256.0f, 512.0f, 512.0f, (Settings.scale * 1.1f) + (MathUtils.cos(this.wavyTimer) / 50.0f), (Settings.scale * 1.1f) + (MathUtils.cos(this.wavyTimer) / 50.0f), 0.0f, 0, 0, 512, 512, false, false);
        sb.setColor(Color.WHITE);
        renderButton(sb);
        if (Settings.isControllerMode) {
            sb.setColor(Color.WHITE);
            sb.draw(CInputActionSet.proceed.getKeyImg(), ((this.current_x - 32.0f) - (38.0f * Settings.scale)) - (FontHelper.getSmartWidth(this.font, this.label, 99999.0f, 0.0f) / 2.0f), this.current_y - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
        }
        if (this.hb.hovered) {
            sb.setBlendFunction(770, 1);
            sb.setColor(HOVER_BLEND_COLOR);
            renderButton(sb);
            sb.setBlendFunction(770, 771);
        }
        if (this.hb.hovered && !this.hb.clickStarted) {
            FontHelper.renderFontCentered(sb, this.font, this.label, this.current_x, this.current_y, Settings.CREAM_COLOR);
        } else if (this.hb.clickStarted) {
            FontHelper.renderFontCentered(sb, this.font, this.label, this.current_x, this.current_y, Color.LIGHT_GRAY);
        } else {
            FontHelper.renderFontCentered(sb, this.font, this.label, this.current_x, this.current_y, Settings.LIGHT_YELLOW_COLOR);
        }
        if (!this.isHidden) {
            this.hb.render(sb);
        }
    }

    private void renderShadow(SpriteBatch sb) {
        sb.draw(ImageMaster.PROCEED_BUTTON_SHADOW, this.current_x - 256.0f, this.current_y - 256.0f, 256.0f, 256.0f, 512.0f, 512.0f, (Settings.scale * 1.1f) + (MathUtils.cos(this.wavyTimer) / 50.0f), (Settings.scale * 1.1f) + (MathUtils.cos(this.wavyTimer) / 50.0f), 0.0f, 0, 0, 512, 512, false, false);
    }

    private void renderButton(SpriteBatch sb) {
        sb.draw(ImageMaster.PROCEED_BUTTON, this.current_x - 256.0f, this.current_y - 256.0f, 256.0f, 256.0f, 512.0f, 512.0f, (Settings.scale * 1.1f) + (MathUtils.cos(this.wavyTimer) / 50.0f), (Settings.scale * 1.1f) + (MathUtils.cos(this.wavyTimer) / 50.0f), 0.0f, 0, 0, 512, 512, false, false);
    }
}
