package com.megacrit.cardcrawl.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/scenes/TheBeyondScene.class */
public class TheBeyondScene extends AbstractScene {
    private boolean renderAltBg;
    private boolean renderM1;
    private boolean renderM2;
    private boolean renderM3;
    private boolean renderM4;
    private boolean renderF1;
    private boolean renderF2;
    private boolean renderF3;
    private boolean renderF4;
    private boolean renderF5;
    private boolean renderIce;
    private boolean renderI1;
    private boolean renderI2;
    private boolean renderI3;
    private boolean renderI4;
    private boolean renderI5;
    private boolean renderStalactites;
    private boolean renderS1;
    private boolean renderS2;
    private boolean renderS3;
    private boolean renderS4;
    private boolean renderS5;
    private ColumnConfig columnConfig = ColumnConfig.OPEN;
    private Color overlayColor = new Color(1.0f, 1.0f, 1.0f, 0.2f);
    private Color tmpColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    private Color whiteColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    private final TextureAtlas.AtlasRegion bg1 = this.atlas.findRegion("mod/bg1");
    private final TextureAtlas.AtlasRegion bg2 = this.atlas.findRegion("mod/bg2");
    private final TextureAtlas.AtlasRegion floor = this.atlas.findRegion("mod/floor");
    private final TextureAtlas.AtlasRegion ceiling = this.atlas.findRegion("mod/ceiling");
    private final TextureAtlas.AtlasRegion fg = this.atlas.findRegion("mod/fg");
    private final TextureAtlas.AtlasRegion mg1 = this.atlas.findRegion("mod/mod1");
    private final TextureAtlas.AtlasRegion mg2 = this.atlas.findRegion("mod/mod2");
    private final TextureAtlas.AtlasRegion mg3 = this.atlas.findRegion("mod/mod3");
    private final TextureAtlas.AtlasRegion mg4 = this.atlas.findRegion("mod/mod4");
    private final TextureAtlas.AtlasRegion c1 = this.atlas.findRegion("mod/c1");
    private final TextureAtlas.AtlasRegion c2 = this.atlas.findRegion("mod/c2");
    private final TextureAtlas.AtlasRegion c3 = this.atlas.findRegion("mod/c3");
    private final TextureAtlas.AtlasRegion c4 = this.atlas.findRegion("mod/c4");
    private final TextureAtlas.AtlasRegion f1 = this.atlas.findRegion("mod/f1");
    private final TextureAtlas.AtlasRegion f2 = this.atlas.findRegion("mod/f2");
    private final TextureAtlas.AtlasRegion f3 = this.atlas.findRegion("mod/f3");
    private final TextureAtlas.AtlasRegion f4 = this.atlas.findRegion("mod/f4");
    private final TextureAtlas.AtlasRegion f5 = this.atlas.findRegion("mod/f5");
    private final TextureAtlas.AtlasRegion i1 = this.atlas.findRegion("mod/i1");
    private final TextureAtlas.AtlasRegion i2 = this.atlas.findRegion("mod/i2");
    private final TextureAtlas.AtlasRegion i3 = this.atlas.findRegion("mod/i3");
    private final TextureAtlas.AtlasRegion i4 = this.atlas.findRegion("mod/i4");
    private final TextureAtlas.AtlasRegion i5 = this.atlas.findRegion("mod/i5");
    private final TextureAtlas.AtlasRegion s1 = this.atlas.findRegion("mod/s1");
    private final TextureAtlas.AtlasRegion s2 = this.atlas.findRegion("mod/s2");
    private final TextureAtlas.AtlasRegion s3 = this.atlas.findRegion("mod/s3");
    private final TextureAtlas.AtlasRegion s4 = this.atlas.findRegion("mod/s4");
    private final TextureAtlas.AtlasRegion s5 = this.atlas.findRegion("mod/s5");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/scenes/TheBeyondScene$ColumnConfig.class */
    public enum ColumnConfig {
        OPEN,
        SMALL_ONLY,
        SMALL_PLUS_LEFT,
        SMALL_PLUS_RIGHT
    }

    public TheBeyondScene() {
        super("beyondScene/scene.atlas");
        this.ambianceName = "AMBIANCE_BEYOND";
        fadeInAmbiance();
    }

    @Override // com.megacrit.cardcrawl.scenes.AbstractScene
    public void randomizeScene() {
        this.overlayColor.r = MathUtils.random(0.7f, 0.9f);
        this.overlayColor.g = MathUtils.random(0.7f, 0.9f);
        this.overlayColor.b = MathUtils.random(0.7f, 1.0f);
        this.overlayColor.a = MathUtils.random(0.0f, 0.2f);
        this.renderAltBg = MathUtils.randomBoolean(0.2f);
        this.renderM1 = false;
        this.renderM2 = false;
        this.renderM3 = false;
        this.renderM4 = false;
        if (!this.renderAltBg && MathUtils.randomBoolean(0.8f)) {
            this.renderM1 = MathUtils.randomBoolean();
            this.renderM2 = MathUtils.randomBoolean();
            this.renderM3 = MathUtils.randomBoolean();
            if (!this.renderM3) {
                this.renderM4 = MathUtils.randomBoolean();
            }
        }
        if (MathUtils.randomBoolean(0.6f)) {
            this.columnConfig = ColumnConfig.OPEN;
        } else if (MathUtils.randomBoolean()) {
            this.columnConfig = ColumnConfig.SMALL_ONLY;
        } else if (MathUtils.randomBoolean()) {
            this.columnConfig = ColumnConfig.SMALL_PLUS_LEFT;
        } else {
            this.columnConfig = ColumnConfig.SMALL_PLUS_RIGHT;
        }
        this.renderF1 = false;
        this.renderF2 = false;
        this.renderF3 = false;
        this.renderF4 = false;
        this.renderF5 = false;
        int floaterCount = 0;
        this.renderF1 = MathUtils.randomBoolean(0.25f);
        if (this.renderF1) {
            floaterCount = 0 + 1;
        }
        this.renderF2 = MathUtils.randomBoolean(0.25f);
        if (this.renderF2) {
            floaterCount++;
        }
        if (floaterCount < 2) {
            this.renderF3 = MathUtils.randomBoolean(0.25f);
            if (this.renderF3) {
                floaterCount++;
            }
        }
        if (floaterCount < 2) {
            this.renderF4 = MathUtils.randomBoolean(0.25f);
            if (this.renderF4) {
                floaterCount++;
            }
        }
        if (floaterCount < 2) {
            this.renderF5 = MathUtils.randomBoolean(0.25f);
        }
        if (MathUtils.randomBoolean(0.3f) || Settings.DISABLE_EFFECTS) {
            this.renderF1 = false;
            this.renderF2 = false;
            this.renderF3 = false;
            this.renderF4 = false;
            this.renderF5 = false;
        }
        this.renderIce = MathUtils.randomBoolean();
        if (this.renderIce) {
            this.renderIce = true;
            this.renderI1 = MathUtils.randomBoolean();
            this.renderI2 = MathUtils.randomBoolean();
            this.renderI3 = MathUtils.randomBoolean();
            this.renderI4 = MathUtils.randomBoolean();
            this.renderI5 = MathUtils.randomBoolean();
        } else {
            this.renderI1 = false;
            this.renderI2 = false;
            this.renderI3 = false;
            this.renderI4 = false;
            this.renderI5 = false;
        }
        this.renderStalactites = MathUtils.randomBoolean();
        if (this.renderStalactites) {
            this.renderStalactites = true;
            this.renderS1 = MathUtils.randomBoolean();
            this.renderS2 = MathUtils.randomBoolean();
            this.renderS3 = MathUtils.randomBoolean();
            this.renderS4 = MathUtils.randomBoolean();
            this.renderS5 = MathUtils.randomBoolean();
            return;
        }
        this.renderS1 = false;
        this.renderS2 = false;
        this.renderS3 = false;
        this.renderS4 = false;
        this.renderS5 = false;
    }

    @Override // com.megacrit.cardcrawl.scenes.AbstractScene
    public void nextRoom(AbstractRoom room) {
        super.nextRoom(room);
        randomizeScene();
        if (room instanceof MonsterRoomBoss) {
            CardCrawlGame.music.silenceBGM();
        }
        fadeInAmbiance();
    }

    @Override // com.megacrit.cardcrawl.scenes.AbstractScene
    public void renderCombatRoomBg(SpriteBatch sb) {
        float prevAlpha = this.overlayColor.a;
        this.overlayColor.a = 1.0f;
        sb.setColor(this.overlayColor);
        this.overlayColor.a = prevAlpha;
        renderAtlasRegionIf(sb, this.floor, true);
        renderAtlasRegionIf(sb, this.ceiling, true);
        renderAtlasRegionIf(sb, this.bg1, true);
        renderAtlasRegionIf(sb, this.bg2, this.renderAltBg);
        renderAtlasRegionIf(sb, this.mg2, this.renderM2);
        renderAtlasRegionIf(sb, this.mg1, this.renderM1);
        renderAtlasRegionIf(sb, this.mg3, this.renderM3);
        renderAtlasRegionIf(sb, this.mg4, this.renderM4);
        switch (this.columnConfig) {
            case SMALL_ONLY:
                renderAtlasRegionIf(sb, this.c1, true);
                renderAtlasRegionIf(sb, this.c4, true);
                break;
            case SMALL_PLUS_LEFT:
                renderAtlasRegionIf(sb, this.c1, true);
                renderAtlasRegionIf(sb, this.c2, true);
                renderAtlasRegionIf(sb, this.c4, true);
                break;
            case SMALL_PLUS_RIGHT:
                renderAtlasRegionIf(sb, this.c1, true);
                renderAtlasRegionIf(sb, this.c3, true);
                renderAtlasRegionIf(sb, this.c4, true);
                break;
        }
        renderAtlasRegionIf(sb, this.s1, this.renderS1);
        renderAtlasRegionIf(sb, this.s2, this.renderS2);
        renderAtlasRegionIf(sb, this.s3, this.renderS3);
        renderAtlasRegionIf(sb, this.s4, this.renderS4);
        renderAtlasRegionIf(sb, this.s5, this.renderS5);
        sb.setColor(this.overlayColor);
        sb.setBlendFunction(770, 1);
        renderAtlasRegionIf(sb, this.bg1, true);
        renderAtlasRegionIf(sb, this.bg2, this.renderAltBg);
        renderAtlasRegionIf(sb, this.mg2, this.renderM2);
        renderAtlasRegionIf(sb, this.mg1, this.renderM1);
        renderAtlasRegionIf(sb, this.mg3, this.renderM3);
        renderAtlasRegionIf(sb, this.mg4, this.renderM4);
        switch (this.columnConfig) {
            case SMALL_ONLY:
                renderAtlasRegionIf(sb, this.c1, true);
                renderAtlasRegionIf(sb, this.c4, true);
                break;
            case SMALL_PLUS_LEFT:
                renderAtlasRegionIf(sb, this.c1, true);
                renderAtlasRegionIf(sb, this.c2, true);
                renderAtlasRegionIf(sb, this.c4, true);
                break;
            case SMALL_PLUS_RIGHT:
                renderAtlasRegionIf(sb, this.c1, true);
                renderAtlasRegionIf(sb, this.c3, true);
                renderAtlasRegionIf(sb, this.c4, true);
                break;
        }
        renderAtlasRegionIf(sb, this.s1, this.renderS1);
        renderAtlasRegionIf(sb, this.s2, this.renderS2);
        renderAtlasRegionIf(sb, this.s3, this.renderS3);
        renderAtlasRegionIf(sb, this.s4, this.renderS4);
        renderAtlasRegionIf(sb, this.s5, this.renderS5);
        sb.setBlendFunction(770, 771);
        this.overlayColor.a = 1.0f;
        sb.setColor(this.overlayColor);
        this.overlayColor.a = prevAlpha;
        renderAtlasRegionIf(sb, this.i1, this.renderI1);
        renderAtlasRegionIf(sb, this.i2, this.renderI2);
        renderAtlasRegionIf(sb, this.i3, this.renderI3);
        renderAtlasRegionIf(sb, this.i4, this.renderI4);
        renderAtlasRegionIf(sb, this.i5, this.renderI5);
        this.tmpColor.r = (1.0f + this.overlayColor.r) / 2.0f;
        this.tmpColor.g = (1.0f + this.overlayColor.g) / 2.0f;
        this.tmpColor.b = (1.0f + this.overlayColor.b) / 2.0f;
        sb.setColor(this.tmpColor);
        renderAtlasRegionIf(sb, MathUtils.cosDeg((float) (((System.currentTimeMillis() + 180) / 180) % 360)) * 40.0f * Settings.xScale, MathUtils.cosDeg((float) (((System.currentTimeMillis() + 500) / 72) % 360)) * 20.0f * Settings.scale, MathUtils.cosDeg((float) (((System.currentTimeMillis() + 180) / 180) % 360)), this.f1, this.renderF1);
        renderAtlasRegionIf(sb, MathUtils.cosDeg((float) (((System.currentTimeMillis() + 91723) / 72) % 360)) * 20.0f, 0.0f, (float) ((System.currentTimeMillis() / 120) % 360), this.f2, this.renderF2);
        renderAtlasRegionIf(sb, (-80.0f) * Settings.scale, (MathUtils.cosDeg((float) (System.currentTimeMillis() + 73)) * 10.0f) - (90.0f * Settings.scale), ((float) ((System.currentTimeMillis() / 1000) % 360)) * 2.0f, this.f3, this.renderF3);
        renderAtlasRegionIf(sb, 0.0f, MathUtils.cosDeg((float) (((System.currentTimeMillis() + 4442) / 20) % 360)) * 30.0f * Settings.scale, MathUtils.cosDeg((float) (((System.currentTimeMillis() + 4442) / 10) % 360)) * 20.0f, this.f4, this.renderF4);
        renderAtlasRegionIf(sb, 0.0f, MathUtils.cosDeg((float) ((System.currentTimeMillis() / 48) % 360)) * 20.0f, 0.0f, this.f5, this.renderF5);
    }

    @Override // com.megacrit.cardcrawl.scenes.AbstractScene
    public void renderCombatRoomFg(SpriteBatch sb) {
        sb.setColor(this.tmpColor);
        renderAtlasRegionIf(sb, this.fg, true);
    }

    @Override // com.megacrit.cardcrawl.scenes.AbstractScene
    public void renderCampfireRoom(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        renderAtlasRegionIf(sb, this.campfireBg, true);
        sb.setBlendFunction(770, 1);
        this.whiteColor.a = (MathUtils.cosDeg((float) ((System.currentTimeMillis() / 3) % 360)) / 10.0f) + 0.8f;
        sb.setColor(this.whiteColor);
        renderQuadrupleSize(sb, this.campfireGlow, !CampfireUI.hidden);
        sb.setBlendFunction(770, 771);
        sb.setColor(Color.WHITE);
        renderAtlasRegionIf(sb, this.campfireKindling, true);
    }
}