package com.megacrit.cardcrawl.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.DungeonMap;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.WingBoots;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.MapCircleEffect;
import com.megacrit.cardcrawl.vfx.scene.LevelTransitionTextOverlayEffect;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/screens/DungeonMapScreen.class */
public class DungeonMapScreen {
    private float mapScrollUpperLimit;
    private float clickStartX;
    private float clickStartY;
    private static final float SCROLL_WAIT_TIME = 1.0f;
    private static final float SPECIAL_ANIMATE_TIME = 3.0f;
    private static final int RETICLE_W = 36;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DungeonMapScreen");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final float MAP_UPPER_SCROLL_DEFAULT = (-2300.0f) * Settings.scale;
    private static final float MAP_UPPER_SCROLL_FINAL_ACT = (-300.0f) * Settings.scale;
    private static final float MAP_SCROLL_LOWER = 190.0f * Settings.scale;
    public static final float ICON_SPACING_Y = 120.0f * Settings.scale;
    public static float offsetY = (-100.0f) * Settings.scale;
    private static final float RETICLE_DIST = 20.0f * Settings.scale;
    public DungeonMap map = new DungeonMap();
    private ArrayList<MapRoomNode> visibleMapNodes = new ArrayList<>();
    public boolean dismissable = false;
    private float targetOffsetY = offsetY;
    private float grabStartY = 0.0f;
    private boolean grabbedScreen = false;
    public boolean clicked = false;
    public float clickTimer = 0.0f;
    private float scrollWaitTimer = 0.0f;
    private Color oscillatingColor = Settings.GOLD_COLOR.cpy();
    private float scrollBackTimer = 0.0f;
    public Hitbox mapNodeHb = null;
    private float oscillatingFader = 0.0f;
    private float oscillatingTimer = 0.0f;

    public DungeonMapScreen() {
        this.oscillatingColor.a = 0.0f;
    }

    public void update() {
        if (this.scrollWaitTimer < 0.0f && Settings.isControllerMode && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !this.map.legend.isLegendHighlighted && this.scrollBackTimer > 0.0f) {
            this.scrollBackTimer -= Gdx.graphics.getDeltaTime();
            if (Gdx.input.getY() > Settings.HEIGHT * 0.85f) {
                this.targetOffsetY += Settings.SCROLL_SPEED * 2.0f;
            } else if (Gdx.input.getY() < Settings.HEIGHT * 0.15f) {
                this.targetOffsetY -= Settings.SCROLL_SPEED * 2.0f;
            }
            if (this.targetOffsetY > MAP_SCROLL_LOWER) {
                this.targetOffsetY = MAP_SCROLL_LOWER;
            } else if (this.targetOffsetY < this.mapScrollUpperLimit) {
                this.targetOffsetY = this.mapScrollUpperLimit;
            }
            offsetY = MathUtils.lerp(offsetY, this.targetOffsetY, Gdx.graphics.getDeltaTime() * 12.0f);
        }
        this.map.update();
        if (AbstractDungeon.isScreenUp) {
            Iterator<MapRoomNode> it = this.visibleMapNodes.iterator();
            while (it.hasNext()) {
                MapRoomNode n = it.next();
                n.update();
            }
        }
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !this.dismissable && this.scrollWaitTimer < 0.0f) {
            oscillateColor();
        }
        Iterator<AbstractGameEffect> it2 = AbstractDungeon.topLevelEffects.iterator();
        while (it2.hasNext()) {
            AbstractGameEffect e = it2.next();
            if (e instanceof MapCircleEffect) {
                return;
            }
        }
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
            updateYOffset();
        }
        updateMouse();
        updateControllerInput();
        if (Settings.isControllerMode && this.mapNodeHb != null && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
            int tmpY = (int) (Settings.HEIGHT - this.mapNodeHb.cY);
            if (tmpY < 1) {
                tmpY = 1;
            } else if (tmpY > Settings.HEIGHT - 1) {
                tmpY = Settings.HEIGHT - 1;
            }
            Gdx.input.setCursorPosition((int) this.mapNodeHb.cX, tmpY);
        }
    }

    private void updateMouse() {
        if (this.clicked) {
            this.clicked = false;
        }
        if (InputHelper.justReleasedClickLeft && this.clickTimer < 0.4f && Vector2.dst(this.clickStartX, this.clickStartY, InputHelper.mX, InputHelper.mY) < Settings.CLICK_DIST_THRESHOLD) {
            this.clicked = true;
        }
        if (InputHelper.justClickedLeft || (CInputActionSet.select.isJustPressed() && AbstractDungeon.topPanel.potionUi.isHidden && !AbstractDungeon.topPanel.selectPotionMode)) {
            this.clickTimer = 0.0f;
            this.clickStartX = InputHelper.mX;
            this.clickStartY = InputHelper.mY;
        } else if (InputHelper.isMouseDown) {
            this.clickTimer += Gdx.graphics.getDeltaTime();
        }
        if (CInputActionSet.select.isJustPressed() && this.clickTimer < 0.4f && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
            this.clicked = true;
        }
    }

    private void updateControllerInput() {
        if (this.scrollWaitTimer > 0.0f || !Settings.isControllerMode || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || this.map.legend.isLegendHighlighted || AbstractDungeon.player.viewingRelics) {
            this.mapNodeHb = null;
        } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
            this.targetOffsetY += Settings.SCROLL_SPEED * 4.0f;
        } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
            this.targetOffsetY -= Settings.SCROLL_SPEED * 4.0f;
        } else {
            if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
                this.scrollBackTimer = 0.1f;
            }
            if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
                ArrayList<MapRoomNode> nodes = new ArrayList<>();
                if (!AbstractDungeon.firstRoomChosen) {
                    Iterator<MapRoomNode> it = this.visibleMapNodes.iterator();
                    while (it.hasNext()) {
                        MapRoomNode n = it.next();
                        if (n.y == 0) {
                            nodes.add(n);
                        }
                    }
                } else {
                    Iterator<MapRoomNode> it2 = this.visibleMapNodes.iterator();
                    while (it2.hasNext()) {
                        MapRoomNode n2 = it2.next();
                        boolean flightMatters = AbstractDungeon.player.hasRelic(WingBoots.ID) || ModHelper.isModEnabled("Flight");
                        if (AbstractDungeon.currMapNode.isConnectedTo(n2) || (flightMatters && AbstractDungeon.currMapNode.wingedIsConnectedTo(n2))) {
                            nodes.add(n2);
                        }
                    }
                }
                boolean anyHovered = false;
                int index = 0;
                Iterator<MapRoomNode> it3 = nodes.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    } else if (it3.next().hb.hovered) {
                        anyHovered = true;
                        break;
                    } else {
                        index++;
                    }
                }
                if (!anyHovered && this.mapNodeHb == null && !nodes.isEmpty()) {
                    Gdx.input.setCursorPosition((int) nodes.get(nodes.size() / 2).hb.cX, Settings.HEIGHT - ((int) nodes.get(nodes.size() / 2).hb.cY));
                    this.mapNodeHb = nodes.get(nodes.size() / 2).hb;
                } else if (!anyHovered && nodes.isEmpty()) {
                    Gdx.input.setCursorPosition((int) AbstractDungeon.dungeonMapScreen.map.bossHb.cX, Settings.HEIGHT - ((int) AbstractDungeon.dungeonMapScreen.map.bossHb.cY));
                    this.mapNodeHb = null;
                } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
                    int index2 = index - 1;
                    if (index2 < 0) {
                        index2 = nodes.size() - 1;
                    }
                    Gdx.input.setCursorPosition((int) nodes.get(index2).hb.cX, Settings.HEIGHT - ((int) nodes.get(index2).hb.cY));
                    this.mapNodeHb = nodes.get(index2).hb;
                } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
                    int index3 = index + 1;
                    if (index3 > nodes.size() - 1) {
                        index3 = 0;
                    }
                    Gdx.input.setCursorPosition((int) nodes.get(index3).hb.cX, Settings.HEIGHT - ((int) nodes.get(index3).hb.cY));
                    this.mapNodeHb = nodes.get(index3).hb;
                }
            }
        }
    }

    private void updateYOffset() {
        if (this.grabbedScreen) {
            if (InputHelper.isMouseDown) {
                this.targetOffsetY = InputHelper.mY - this.grabStartY;
            } else {
                this.grabbedScreen = false;
            }
        } else if (this.scrollWaitTimer < 0.0f) {
            if (InputHelper.scrolledDown) {
                this.targetOffsetY += Settings.MAP_SCROLL_SPEED;
            } else if (InputHelper.scrolledUp) {
                this.targetOffsetY -= Settings.MAP_SCROLL_SPEED;
            }
            if (InputHelper.justClickedLeft && this.scrollWaitTimer < 0.0f) {
                this.grabbedScreen = true;
                this.grabStartY = InputHelper.mY - this.targetOffsetY;
            }
        }
        resetScrolling();
        updateAnimation();
    }

    private void resetScrolling() {
        if (this.targetOffsetY < this.mapScrollUpperLimit) {
            this.targetOffsetY = MathHelper.scrollSnapLerpSpeed(this.targetOffsetY, this.mapScrollUpperLimit);
        } else if (this.targetOffsetY > MAP_SCROLL_LOWER) {
            this.targetOffsetY = MathHelper.scrollSnapLerpSpeed(this.targetOffsetY, MAP_SCROLL_LOWER);
        }
    }

    private void updateAnimation() {
        this.scrollWaitTimer -= Gdx.graphics.getDeltaTime();
        if (this.scrollWaitTimer < 0.0f) {
            offsetY = MathUtils.lerp(offsetY, this.targetOffsetY, Gdx.graphics.getDeltaTime() * 12.0f);
        } else if (this.scrollWaitTimer < 3.0f) {
            offsetY = Interpolation.exp10.apply(MAP_SCROLL_LOWER, this.mapScrollUpperLimit, this.scrollWaitTimer / 3.0f);
        }
    }

    public void updateImage() {
        this.visibleMapNodes.clear();
        Iterator<ArrayList<MapRoomNode>> it = CardCrawlGame.dungeon.getMap().iterator();
        while (it.hasNext()) {
            ArrayList<MapRoomNode> rows = it.next();
            Iterator<MapRoomNode> it2 = rows.iterator();
            while (it2.hasNext()) {
                MapRoomNode node = it2.next();
                if (node.hasEdges()) {
                    this.visibleMapNodes.add(node);
                }
            }
        }
    }

    public void render(SpriteBatch sb) {
        this.map.render(sb);
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
            Iterator<MapRoomNode> it = this.visibleMapNodes.iterator();
            while (it.hasNext()) {
                MapRoomNode n = it.next();
                n.render(sb);
            }
        }
        this.map.renderBossIcon(sb);
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !this.dismissable && this.scrollWaitTimer < 0.0f) {
            FontHelper.renderDeckViewTip(sb, TEXT[0], 80.0f * Settings.scale, this.oscillatingColor);
        }
        renderControllerUi(sb);
    }

    private void renderControllerUi(SpriteBatch sb) {
        if (Settings.isControllerMode && this.mapNodeHb != null) {
            renderReticle(sb, this.mapNodeHb);
        }
    }

    private void oscillateColor() {
        this.oscillatingFader += Gdx.graphics.getDeltaTime();
        if (this.oscillatingFader > 1.0f) {
            this.oscillatingFader = 1.0f;
            this.oscillatingTimer += Gdx.graphics.getDeltaTime() * 5.0f;
        }
        this.oscillatingColor.a = (0.33f + ((MathUtils.cos(this.oscillatingTimer) + 1.0f) / 3.0f)) * this.oscillatingFader;
    }

    public void open(boolean doScrollingAnimation) {
        this.mapNodeHb = null;
        if (!AbstractDungeon.id.equals(TheEnding.ID)) {
            this.mapScrollUpperLimit = MAP_UPPER_SCROLL_DEFAULT;
        } else {
            this.mapScrollUpperLimit = MAP_UPPER_SCROLL_FINAL_ACT;
        }
        AbstractDungeon.player.releaseCard();
        this.map.legend.isLegendHighlighted = false;
        if (Settings.isDebug) {
            doScrollingAnimation = false;
        }
        InputHelper.justClickedLeft = false;
        this.clicked = false;
        this.clickTimer = 999.0f;
        this.grabbedScreen = false;
        AbstractDungeon.topPanel.unhoverHitboxes();
        this.map.show();
        this.dismissable = !doScrollingAnimation;
        if (MathUtils.randomBoolean()) {
            CardCrawlGame.sound.play("MAP_OPEN", 0.1f);
        } else {
            CardCrawlGame.sound.play("MAP_OPEN_2", 0.1f);
        }
        if (doScrollingAnimation) {
            this.mapNodeHb = null;
            AbstractDungeon.topLevelEffects.add(new LevelTransitionTextOverlayEffect(AbstractDungeon.name, AbstractDungeon.levelNum));
            this.scrollWaitTimer = 4.0f;
            offsetY = this.mapScrollUpperLimit;
            this.targetOffsetY = MAP_SCROLL_LOWER;
        } else {
            this.scrollWaitTimer = 0.0f;
            AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
            if (AbstractDungeon.getCurrMapNode() == null) {
                offsetY = this.mapScrollUpperLimit;
            } else {
                offsetY = ((-50.0f) * Settings.scale) + (AbstractDungeon.getCurrMapNode().y * (-ICON_SPACING_Y));
            }
            this.targetOffsetY = offsetY;
        }
        AbstractDungeon.dynamicBanner.hide();
        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.MAP;
        AbstractDungeon.isScreenUp = true;
        this.grabStartY = 0.0f;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.hideCombatPanels();
        AbstractDungeon.overlayMenu.endTurnButton.hide();
        AbstractDungeon.overlayMenu.showBlackScreen();
        updateImage();
    }

    public void close() {
        this.map.hide();
        AbstractDungeon.overlayMenu.cancelButton.hide();
        this.clicked = false;
    }

    public void closeInstantly() {
        this.map.hideInstantly();
        if (AbstractDungeon.overlayMenu != null) {
            AbstractDungeon.overlayMenu.cancelButton.hideInstantly();
        }
        this.clicked = false;
    }

    public void renderReticle(SpriteBatch sb, Hitbox hb) {
        renderReticleCorner(sb, ((-hb.width) / 2.0f) - RETICLE_DIST, (hb.height / 2.0f) + RETICLE_DIST, hb, false, false);
        renderReticleCorner(sb, (hb.width / 2.0f) + RETICLE_DIST, (hb.height / 2.0f) + RETICLE_DIST, hb, true, false);
        renderReticleCorner(sb, ((-hb.width) / 2.0f) - RETICLE_DIST, ((-hb.height) / 2.0f) - RETICLE_DIST, hb, false, true);
        renderReticleCorner(sb, (hb.width / 2.0f) + RETICLE_DIST, ((-hb.height) / 2.0f) - RETICLE_DIST, hb, true, true);
    }

    private void renderReticleCorner(SpriteBatch sb, float x, float y, Hitbox hb, boolean flipX, boolean flipY) {
        sb.setColor(new Color(0.0f, 0.0f, 0.0f, this.map.targetAlpha / 4.0f));
        sb.draw(ImageMaster.RETICLE_CORNER, ((hb.cX + x) - 18.0f) + (4.0f * Settings.scale), ((hb.cY + y) - 18.0f) - (4.0f * Settings.scale), 18.0f, 18.0f, 36.0f, 36.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 36, 36, flipX, flipY);
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.map.targetAlpha));
        sb.draw(ImageMaster.RETICLE_CORNER, (hb.cX + x) - 18.0f, (hb.cY + y) - 18.0f, 18.0f, 18.0f, 36.0f, 36.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 36, 36, flipX, flipY);
    }
}