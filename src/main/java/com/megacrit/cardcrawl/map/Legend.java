package com.megacrit.cardcrawl.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/map/Legend.class */
public class Legend {
    private static final int LW = 512;
    private static final int LH = 800;
    public Color c = new Color(1.0f, 1.0f, 1.0f, 0.0f);
    public ArrayList<LegendItem> items = new ArrayList<>();
    public boolean isLegendHighlighted = false;
    public static final float X = 1670.0f * Settings.xScale;
    public static final float Y = 600.0f * Settings.yScale;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Legend");
    public static final String[] TEXT = uiStrings.TEXT;
    private static Texture img = null;

    public Legend() {
        this.items.add(new LegendItem(TEXT[0], ImageMaster.MAP_NODE_EVENT, TEXT[1], TEXT[2], 0));
        this.items.add(new LegendItem(TEXT[3], ImageMaster.MAP_NODE_MERCHANT, TEXT[4], TEXT[5], 1));
        this.items.add(new LegendItem(TEXT[6], ImageMaster.MAP_NODE_TREASURE, TEXT[7], TEXT[8], 2));
        this.items.add(new LegendItem(TEXT[9], ImageMaster.MAP_NODE_REST, TEXT[10], TEXT[11], 3));
        this.items.add(new LegendItem(TEXT[12], ImageMaster.MAP_NODE_ENEMY, TEXT[13], TEXT[14], 4));
        this.items.add(new LegendItem(TEXT[15], ImageMaster.MAP_NODE_ELITE, TEXT[16], TEXT[17], 5));
        if (img == null) {
            img = ImageMaster.loadImage("images/ui/map/selectBox.png");
        }
    }

    public boolean isIconHovered(String nodeHovered) {
        char c = 65535;
        switch (nodeHovered.hashCode()) {
            case 36:
                if (nodeHovered.equals("$")) {
                    c = 1;
                    break;
                }
                break;
            case 63:
                if (nodeHovered.equals("?")) {
                    c = 0;
                    break;
                }
                break;
            case 69:
                if (nodeHovered.equals("E")) {
                    c = 5;
                    break;
                }
                break;
            case 77:
                if (nodeHovered.equals("M")) {
                    c = 4;
                    break;
                }
                break;
            case 82:
                if (nodeHovered.equals("R")) {
                    c = 3;
                    break;
                }
                break;
            case 84:
                if (nodeHovered.equals("T")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (this.items.get(0).hb.hovered) {
                    return true;
                }
                return false;
            case 1:
                if (this.items.get(1).hb.hovered) {
                    return true;
                }
                return false;
            case 2:
                if (this.items.get(2).hb.hovered) {
                    return true;
                }
                return false;
            case 3:
                if (this.items.get(3).hb.hovered) {
                    return true;
                }
                return false;
            case 4:
                if (this.items.get(4).hb.hovered) {
                    return true;
                }
                return false;
            case 5:
                if (this.items.get(5).hb.hovered) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public void update(float mapAlpha, boolean isMapScreen) {
        if (mapAlpha < 0.8f || !isMapScreen) {
            this.c.a = MathHelper.fadeLerpSnap(this.c.a, 0.0f);
            return;
        }
        updateControllerInput();
        this.c.a = MathHelper.fadeLerpSnap(this.c.a, 1.0f);
        Iterator<LegendItem> it = this.items.iterator();
        while (it.hasNext()) {
            LegendItem i = it.next();
            i.update();
        }
    }

    private void updateControllerInput() {
        if (Settings.isControllerMode) {
            if (this.isLegendHighlighted) {
                if (CInputActionSet.proceed.isJustPressed() || CInputActionSet.cancel.isJustPressed() || CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
                    CInputActionSet.cancel.unpress();
                    this.isLegendHighlighted = false;
                    return;
                }
            } else if (CInputActionSet.proceed.isJustPressed()) {
                this.isLegendHighlighted = true;
                return;
            }
            if (this.isLegendHighlighted) {
                boolean anyHovered = false;
                int index = 0;
                Iterator<LegendItem> it = this.items.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    LegendItem i = it.next();
                    if (i.hb.hovered) {
                        anyHovered = true;
                        break;
                    }
                    index++;
                }
                if (!anyHovered) {
                    Gdx.input.setCursorPosition((int) this.items.get(0).hb.cX, Settings.HEIGHT - ((int) this.items.get(0).hb.cY));
                } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
                    int index2 = index + 1;
                    if (index2 > this.items.size() - 1) {
                        index2 = 0;
                    }
                    Gdx.input.setCursorPosition((int) this.items.get(index2).hb.cX, Settings.HEIGHT - ((int) this.items.get(index2).hb.cY));
                } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
                    int index3 = index - 1;
                    if (index3 < 0) {
                        index3 = this.items.size() - 1;
                    }
                    Gdx.input.setCursorPosition((int) this.items.get(index3).hb.cX, Settings.HEIGHT - ((int) this.items.get(index3).hb.cY));
                }
            }
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.c);
        if (!Settings.isMobile) {
            sb.draw(ImageMaster.MAP_LEGEND, X - 256.0f, Y - 400.0f, 256.0f, 400.0f, 512.0f, 800.0f, Settings.scale, Settings.yScale, 0.0f, 0, 0, 512, LH, false, false);
        } else {
            sb.draw(ImageMaster.MAP_LEGEND, X - 256.0f, Y - 400.0f, 256.0f, 400.0f, 512.0f, 800.0f, Settings.scale * 1.1f, Settings.yScale * 1.1f, 0.0f, 0, 0, 512, LH, false, false);
        }
        Color c2 = new Color(MapRoomNode.AVAILABLE_COLOR.r, MapRoomNode.AVAILABLE_COLOR.g, MapRoomNode.AVAILABLE_COLOR.b, this.c.a);
        if (Settings.isMobile) {
            FontHelper.renderFontCentered(sb, FontHelper.menuBannerFont, TEXT[18], X, Y + (190.0f * Settings.yScale), c2, 1.4f);
        } else {
            FontHelper.renderFontCentered(sb, FontHelper.menuBannerFont, TEXT[18], X, Y + (170.0f * Settings.yScale), c2);
        }
        sb.setColor(c2);
        Iterator<LegendItem> it = this.items.iterator();
        while (it.hasNext()) {
            LegendItem i = it.next();
            i.render(sb, c2);
        }
        if (Settings.isControllerMode) {
            sb.setColor(new Color(1.0f, 1.0f, 1.0f, c2.a));
            sb.draw(CInputActionSet.proceed.getKeyImg(), (1570.0f * Settings.xScale) - 32.0f, (Y + (170.0f * Settings.yScale)) - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
            if (this.isLegendHighlighted) {
                sb.setColor(new Color(1.0f, 0.9f, 0.5f, 0.6f + (MathUtils.cosDeg((float) ((System.currentTimeMillis() / 2) % 360)) / 5.0f)));
                float doop = 1.0f + ((1.0f + MathUtils.cosDeg((float) ((System.currentTimeMillis() / 2) % 360))) / 50.0f);
                sb.draw(img, (1670.0f * Settings.scale) - 160.0f, ((Settings.HEIGHT - Gdx.input.getY()) - 52.0f) + (4.0f * Settings.scale), 160.0f, 52.0f, 320.0f, 104.0f, Settings.scale * doop, Settings.scale * doop, 0.0f, 0, 0, 320, 104, false, false);
            }
        }
    }
}