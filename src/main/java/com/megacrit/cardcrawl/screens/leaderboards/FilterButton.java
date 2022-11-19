package com.megacrit.cardcrawl.screens.leaderboards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/screens/leaderboards/FilterButton.class */
public class FilterButton {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("LeaderboardFilters");
    public static final String[] TEXT = uiStrings.TEXT;
    public LeaderboardType lType;
    public RegionSetting rType;
    public boolean active;
    public AbstractPlayer.PlayerClass pClass;
    private Texture img;
    private static final int W = 128;
    public Hitbox hb;
    public String label;

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/screens/leaderboards/FilterButton$LeaderboardType.class */
    public enum LeaderboardType {
        HIGH_SCORE,
        FASTEST_WIN,
        CONSECUTIVE_WINS,
        AVG_FLOOR,
        AVG_SCORE,
        SPIRE_LEVEL
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/screens/leaderboards/FilterButton$RegionSetting.class */
    public enum RegionSetting {
        GLOBAL,
        FRIEND
    }

    public FilterButton(String imgUrl, boolean active, AbstractPlayer.PlayerClass pClass, LeaderboardType lType, RegionSetting rType) {
        this.lType = null;
        this.rType = null;
        this.active = false;
        this.pClass = null;
        this.hb = new Hitbox(100.0f * Settings.scale, 100.0f * Settings.scale);
        if (pClass == null) {
            if (lType == null) {
                if (rType != null) {
                    switch (rType) {
                        case FRIEND:
                            this.img = ImageMaster.FILTER_FRIENDS;
                            break;
                        case GLOBAL:
                        default:
                            this.img = ImageMaster.FILTER_GLOBAL;
                            break;
                    }
                }
            } else {
                switch (lType) {
                    case CONSECUTIVE_WINS:
                        this.img = ImageMaster.FILTER_CHAIN;
                        break;
                    case FASTEST_WIN:
                        this.img = ImageMaster.FILTER_TIME;
                        break;
                    case HIGH_SCORE:
                        this.img = ImageMaster.FILTER_SCORE;
                        break;
                    case SPIRE_LEVEL:
                    case AVG_FLOOR:
                    case AVG_SCORE:
                    default:
                        this.img = ImageMaster.FILTER_CHAIN;
                        break;
                }
            }
        } else {
            switch (pClass) {
                case IRONCLAD:
                    this.img = ImageMaster.FILTER_IRONCLAD;
                    break;
                case THE_SILENT:
                    this.img = ImageMaster.FILTER_SILENT;
                    break;
                case DEFECT:
                    this.img = ImageMaster.FILTER_DEFECT;
                    break;
                case WATCHER:
                    this.img = ImageMaster.FILTER_WATCHER;
                    break;
                default:
                    this.img = ImageMaster.FILTER_IRONCLAD;
                    break;
            }
        }
        this.lType = lType;
        this.rType = rType;
        this.active = active;
        this.pClass = pClass;
    }

    public FilterButton(String imgUrl, boolean active, AbstractPlayer.PlayerClass pClass) {
        this(imgUrl, active, pClass, null, null);
        switch (pClass) {
            case IRONCLAD:
                this.label = TEXT[0];
                break;
            case THE_SILENT:
                this.label = TEXT[1];
                break;
            case DEFECT:
                this.label = TEXT[2];
                break;
            case WATCHER:
                this.label = TEXT[11];
                break;
            default:
                this.label = TEXT[0];
                break;
        }
        if (active) {
            CardCrawlGame.mainMenuScreen.leaderboardsScreen.charLabel = LeaderboardScreen.TEXT[2] + ":  " + this.label;
        }
    }

    public FilterButton(String imgUrl, boolean active, LeaderboardType lType) {
        this(imgUrl, active, null, lType, null);
        switch (lType) {
            case CONSECUTIVE_WINS:
                this.label = TEXT[5];
                break;
            case FASTEST_WIN:
                this.label = TEXT[6];
                break;
            case HIGH_SCORE:
                this.label = TEXT[7];
                break;
            case SPIRE_LEVEL:
                this.label = TEXT[8];
                break;
            case AVG_FLOOR:
                this.label = TEXT[3];
                break;
            case AVG_SCORE:
                this.label = TEXT[4];
                break;
            default:
                this.label = TEXT[7];
                break;
        }
        if (active) {
            CardCrawlGame.mainMenuScreen.leaderboardsScreen.typeLabel = LeaderboardScreen.TEXT[4] + ":  " + this.label;
        }
    }

    public FilterButton(String imgUrl, boolean active, RegionSetting rType) {
        this(imgUrl, active, null, null, rType);
        switch (rType) {
            case FRIEND:
                this.label = TEXT[9];
                break;
            case GLOBAL:
                this.label = TEXT[10];
                break;
            default:
                this.label = TEXT[9];
                break;
        }
        if (active) {
            CardCrawlGame.mainMenuScreen.leaderboardsScreen.regionLabel = LeaderboardScreen.TEXT[3] + ":  " + this.label;
        }
    }

    public void update() {
        this.hb.update();
        if (this.hb.justHovered && !this.active) {
            CardCrawlGame.sound.play("UI_HOVER");
        }
        if (Settings.isControllerMode) {
            if (!this.active && this.hb.hovered && CInputActionSet.select.isJustPressed()) {
                CInputActionSet.select.unpress();
                this.hb.clicked = true;
            }
        } else if (!this.active && this.hb.hovered && InputHelper.justClickedLeft && !CardCrawlGame.mainMenuScreen.leaderboardsScreen.waiting) {
            CardCrawlGame.sound.playA("UI_CLICK_1", -0.4f);
            this.hb.clickStarted = true;
        }
        if (this.hb.clicked) {
            this.hb.clicked = false;
            if (!this.active) {
                toggle(true);
            }
        }
    }

    private void toggle(boolean refresh) {
        this.active = true;
        CardCrawlGame.mainMenuScreen.leaderboardsScreen.refresh = true;
        if (this.pClass != null) {
            Iterator<FilterButton> it = CardCrawlGame.mainMenuScreen.leaderboardsScreen.charButtons.iterator();
            while (it.hasNext()) {
                FilterButton b = it.next();
                if (b != this) {
                    b.active = false;
                }
            }
            CardCrawlGame.mainMenuScreen.leaderboardsScreen.charLabel = LeaderboardScreen.TEXT[2] + ":  " + this.label;
        } else if (this.rType != null) {
            Iterator<FilterButton> it2 = CardCrawlGame.mainMenuScreen.leaderboardsScreen.regionButtons.iterator();
            while (it2.hasNext()) {
                FilterButton b2 = it2.next();
                if (b2 != this) {
                    b2.active = false;
                }
            }
            CardCrawlGame.mainMenuScreen.leaderboardsScreen.regionLabel = LeaderboardScreen.TEXT[3] + ":  " + this.label;
        } else if (this.lType != null) {
            Iterator<FilterButton> it3 = CardCrawlGame.mainMenuScreen.leaderboardsScreen.typeButtons.iterator();
            while (it3.hasNext()) {
                FilterButton b3 = it3.next();
                if (b3 != this) {
                    b3.active = false;
                }
            }
            CardCrawlGame.mainMenuScreen.leaderboardsScreen.typeLabel = LeaderboardScreen.TEXT[4] + ":  " + this.label;
        }
    }

    public void render(SpriteBatch sb, float x, float y) {
        if (this.active) {
            sb.setColor(new Color(1.0f, 0.8f, 0.2f, 0.5f + ((MathUtils.cosDeg((float) ((System.currentTimeMillis() / 4) % 360)) + 1.25f) / 5.0f)));
            sb.draw(ImageMaster.FILTER_GLOW_BG, x - 64.0f, y - 64.0f, 64.0f, 64.0f, 128.0f, 128.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 128, 128, false, false);
        }
        if (this.hb.hovered || this.active) {
            sb.setColor(Color.WHITE);
            sb.draw(this.img, x - 64.0f, y - 64.0f, 64.0f, 64.0f, 128.0f, 128.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 128, 128, false, false);
        } else {
            sb.setColor(Color.GRAY);
            sb.draw(this.img, x - 64.0f, y - 64.0f, 64.0f, 64.0f, 128.0f, 128.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 128, 128, false, false);
        }
        if (this.hb.hovered) {
            sb.setBlendFunction(770, 1);
            sb.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
            sb.draw(this.img, x - 64.0f, y - 64.0f, 64.0f, 64.0f, 128.0f, 128.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 128, 128, false, false);
            sb.setBlendFunction(770, 771);
        }
        this.hb.move(x, y);
        this.hb.render(sb);
    }
}