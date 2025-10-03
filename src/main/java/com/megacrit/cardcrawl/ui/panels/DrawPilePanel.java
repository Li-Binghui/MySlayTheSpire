package com.megacrit.cardcrawl.ui.panels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.FrozenEye;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.GameDeckGlowEffect;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/ui/panels/DrawPilePanel.class */
public class DrawPilePanel extends AbstractPanel {
    private static final int RAW_W = 128;
    private float scale;
    private Color glowColor;
    private float glowAlpha;
    private GlyphLayout gl;
    private BobEffect bob;
    private ArrayList<GameDeckGlowEffect> vfxBelow;
    private Hitbox hb;
    private Hitbox bannerHb;
    private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Draw Tip");
    public static final String[] MSG = tutorialStrings.TEXT;
    public static final String[] LABEL = tutorialStrings.LABEL;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DrawPilePanel");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final float COUNT_CIRCLE_W = 128.0f * Settings.scale;
    private static final float DECK_X = (76.0f * Settings.scale) - 64.0f;
    private static final float DECK_Y = (74.0f * Settings.scale) - 64.0f;
    private static final float COUNT_X = 118.0f * Settings.scale;
    private static final float COUNT_Y = 48.0f * Settings.scale;
    private static final float COUNT_OFFSET_X = 54.0f * Settings.scale;
    private static final float COUNT_OFFSET_Y = (-18.0f) * Settings.scale;
    private static final float DECK_TIP_X = 50.0f * Settings.scale;
    private static final float DECK_TIP_Y = 470.0f * Settings.scale;
    private static final float HITBOX_W = 120.0f * Settings.scale;
    private static final float HITBOX_W2 = 450.0f * Settings.scale;

    public DrawPilePanel() {
        super(0.0f, 0.0f, (-300.0f) * Settings.scale, (-300.0f) * Settings.scale, null, true);
        this.scale = 1.0f;
        this.glowColor = Color.WHITE.cpy();
        this.glowAlpha = 0.0f;
        this.gl = new GlyphLayout();
        this.bob = new BobEffect(1.0f);
        this.vfxBelow = new ArrayList<>();
        this.hb = new Hitbox(0.0f, 0.0f, HITBOX_W, HITBOX_W);
        this.bannerHb = new Hitbox(0.0f, 0.0f, HITBOX_W2, HITBOX_W);
    }

    @Override // com.megacrit.cardcrawl.ui.panels.AbstractPanel
    public void updatePositions() {
        super.updatePositions();
        this.bob.update();
        updateVfx();
        if (!this.isHidden) {
            this.hb.update();
            this.bannerHb.update();
            updatePop();
        }
        if (this.hb.hovered && (!AbstractDungeon.isScreenUp || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GAME_DECK_VIEW || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT || (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT))) {
            AbstractDungeon.overlayMenu.hoveredTip = true;
            if (InputHelper.justClickedLeft) {
                this.hb.clickStarted = true;
            }
        }
        if ((this.hb.clicked || InputActionSet.drawPile.isJustPressed() || CInputActionSet.drawPile.isJustPressed()) && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GAME_DECK_VIEW) {
            this.hb.clicked = false;
            this.hb.hovered = false;
            this.bannerHb.hovered = false;
            CardCrawlGame.sound.play("DECK_CLOSE");
            if (AbstractDungeon.previousScreen == AbstractDungeon.CurrentScreen.GAME_DECK_VIEW) {
                AbstractDungeon.previousScreen = null;
            }
            AbstractDungeon.closeCurrentScreen();
            return;
        }
        this.glowAlpha += Gdx.graphics.getDeltaTime() * 3.0f;
        if (this.glowAlpha < 0.0f) {
            this.glowAlpha *= -1.0f;
        }
        float tmp = MathUtils.cos(this.glowAlpha);
        if (tmp < 0.0f) {
            this.glowColor.a = (-tmp) / 2.0f;
        } else {
            this.glowColor.a = tmp / 2.0f;
        }
        if ((this.hb.clicked || InputActionSet.drawPile.isJustPressed() || CInputActionSet.drawPile.isJustPressed()) && AbstractDungeon.overlayMenu.combatPanelsShown && AbstractDungeon.getMonsters() != null && !AbstractDungeon.getMonsters().areMonstersDead() && !AbstractDungeon.player.isDead) {
            this.hb.clicked = false;
            this.hb.hovered = false;
            this.bannerHb.hovered = false;
            AbstractDungeon.dynamicBanner.hide();
            if (AbstractDungeon.player.hoveredCard != null) {
                AbstractDungeon.player.releaseCard();
            }
            if (AbstractDungeon.isScreenUp) {
                if (AbstractDungeon.previousScreen == null) {
                    AbstractDungeon.previousScreen = AbstractDungeon.screen;
                }
            } else {
                AbstractDungeon.previousScreen = null;
            }
            openDrawPile();
        }
    }

    private void openDrawPile() {
        AbstractPlayer p = AbstractDungeon.player;
        if (!p.drawPile.isEmpty()) {
            AbstractDungeon.gameDeckViewScreen.open();
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0f, TEXT[0], true));
        }
        this.hb.hovered = false;
        InputHelper.justClickedLeft = false;
    }

    private void updateVfx() {
        Iterator<GameDeckGlowEffect> i = this.vfxBelow.iterator();
        while (i.hasNext()) {
            AbstractGameEffect e = i.next();
            e.update();
            if (e.isDone) {
                i.remove();
            }
        }
        if (this.vfxBelow.size() < 25 && !Settings.DISABLE_EFFECTS) {
            this.vfxBelow.add(new GameDeckGlowEffect(false));
        }
    }

    private void updatePop() {
        this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale);
    }

    public void pop() {
        this.scale = 1.75f * Settings.scale;
    }

    @Override // com.megacrit.cardcrawl.ui.panels.AbstractPanel
    public void render(SpriteBatch sb) {
        if (this.hb.hovered || (this.bannerHb.hovered && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GAME_DECK_VIEW)) {
            this.scale = 1.2f * Settings.scale;
        }
        Iterator<GameDeckGlowEffect> it = this.vfxBelow.iterator();
        while (it.hasNext()) {
            GameDeckGlowEffect e = it.next();
            e.render(sb, this.current_x, this.current_y + (this.bob.y * 0.5f));
        }
        sb.setColor(Color.WHITE);
        sb.draw(ImageMaster.DECK_BTN_BASE, this.current_x + DECK_X, this.current_y + DECK_Y + (this.bob.y / 2.0f), 64.0f, 64.0f, 128.0f, 128.0f, this.scale, this.scale, 0.0f, 0, 0, 128, 128, false, false);
        String msg = Integer.toString(AbstractDungeon.player.drawPile.size());
        this.gl.setText(FontHelper.turnNumFont, msg);
        sb.setColor(Color.WHITE);
        sb.draw(ImageMaster.DECK_COUNT_CIRCLE, this.current_x + COUNT_OFFSET_X, this.current_y + COUNT_OFFSET_Y, COUNT_CIRCLE_W, COUNT_CIRCLE_W);
        if (Settings.isControllerMode) {
            sb.draw(CInputActionSet.drawPile.getKeyImg(), (this.current_x - 32.0f) + (30.0f * Settings.scale), (this.current_y - 32.0f) + (40.0f * Settings.scale), 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale * 0.75f, Settings.scale * 0.75f, 0.0f, 0, 0, 64, 64, false, false);
        }
        FontHelper.renderFontCentered(sb, FontHelper.turnNumFont, msg, this.current_x + COUNT_X, this.current_y + COUNT_Y);
        if (!this.isHidden) {
            this.hb.render(sb);
            if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GAME_DECK_VIEW) {
                this.bannerHb.render(sb);
            }
        }
        if (this.hb.hovered && !AbstractDungeon.isScreenUp && AbstractDungeon.getMonsters() != null && !AbstractDungeon.getMonsters().areMonstersDead()) {
            if (Settings.isConsoleBuild) {
                if (!AbstractDungeon.player.hasRelic(FrozenEye.ID)) {
                    TipHelper.renderGenericTip(DECK_TIP_X, DECK_TIP_Y, LABEL[0] + " (" + InputActionSet.drawPile.getKeyString() + ")", MSG[0] + AbstractDungeon.player.gameHandSize + MSG[3]);
                    return;
                } else {
                    TipHelper.renderGenericTip(DECK_TIP_X, DECK_TIP_Y, LABEL[0] + " (" + InputActionSet.drawPile.getKeyString() + ")", MSG[0] + AbstractDungeon.player.gameHandSize + MSG[4]);
                    return;
                }
            }
            if (!AbstractDungeon.player.hasRelic(FrozenEye.ID)) {
                TipHelper.renderGenericTip(DECK_TIP_X, DECK_TIP_Y, LABEL[0] + " (" + InputActionSet.drawPile.getKeyString() + ")", MSG[0] + AbstractDungeon.player.gameHandSize + MSG[1]);
            } else {
                TipHelper.renderGenericTip(DECK_TIP_X, DECK_TIP_Y, LABEL[0] + " (" + InputActionSet.drawPile.getKeyString() + ")", MSG[0] + AbstractDungeon.player.gameHandSize + MSG[2]);
            }
        }
    }
}
