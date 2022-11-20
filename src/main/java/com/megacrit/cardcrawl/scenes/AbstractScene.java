package com.megacrit.cardcrawl.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class AbstractScene {
    private static final Logger logger = LogManager.getLogger(AbstractScene.class.getName());
    private Color bgOverlayColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
    private float bgOverlayTarget = 0.0F;
    protected final TextureAtlas atlas;
    protected final TextureAtlas.AtlasRegion bg;
    protected final TextureAtlas.AtlasRegion campfireBg;
    private float vertY = 0.0F;
    protected final TextureAtlas.AtlasRegion campfireGlow;
    protected final TextureAtlas.AtlasRegion campfireKindling;
    protected long ambianceSoundId = 0L;
    protected final TextureAtlas.AtlasRegion event;
    protected boolean isCamp = false;
    protected String ambianceSoundKey = null;
    protected String ambianceName;

    public AbstractScene(String atlasUrl) {
        this.atlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
        this.bg = this.atlas.findRegion("bg");
        this.campfireBg = this.atlas.findRegion("campfire");
        this.campfireGlow = this.atlas.findRegion("mod/campfireGlow");
        this.campfireKindling = this.atlas.findRegion("mod/campfireKindling");
        this.event = this.atlas.findRegion("event");
    }


    public void update() {
        updateBgOverlay();
        if (this.vertY != 0.0F) {
            this.vertY = MathHelper.uiLerpSnap(this.vertY, 0.0F);
        }
    }

    protected void updateBgOverlay() {
        if (this.bgOverlayColor.a != this.bgOverlayTarget) {
            this.bgOverlayColor.a = MathUtils.lerp(this.bgOverlayColor.a, this.bgOverlayTarget, Gdx.graphics.getDeltaTime() * 2.0F);
            if (Math.abs(this.bgOverlayColor.a - this.bgOverlayTarget) < 0.01F) {
                this.bgOverlayColor.a = this.bgOverlayTarget;
            }
        }
    }


    public void nextRoom(AbstractRoom room) {
        this.bgOverlayColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);
        this.bgOverlayTarget = 0.5F;
    }

    public void changeOverlay(float target) {
        this.bgOverlayTarget = target;
    }

    public abstract void renderCombatRoomBg(SpriteBatch paramSpriteBatch);

    public abstract void renderCombatRoomFg(SpriteBatch paramSpriteBatch);

    public abstract void renderCampfireRoom(SpriteBatch paramSpriteBatch);

    public abstract void randomizeScene();

    public void renderEventRoom(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        if (Settings.isFourByThree) {
            sb.draw(this.event
                    .getTexture(), this.event.offsetX * Settings.scale, this.event.offsetY * Settings.scale, 0.0F, 0.0F, this.event.packedWidth, this.event.packedHeight, Settings.yScale, Settings.yScale, 0.0F, this.event
                    .getRegionX(), this.event
                    .getRegionY(), this.event
                    .getRegionWidth(), this.event
                    .getRegionHeight(), false, false);
        } else {
            sb.draw(this.event
                    .getTexture(), this.event.offsetX * Settings.scale, this.event.offsetY * Settings.scale, 0.0F, 0.0F, this.event.packedWidth, this.event.packedHeight, Settings.xScale, Settings.xScale, 0.0F, this.event
                    .getRegionX(), this.event
                    .getRegionY(), this.event
                    .getRegionWidth(), this.event
                    .getRegionHeight(), false, false);
        }
    }


    public void dispose() {
        this.atlas.dispose();
    }

    public void fadeOutAmbiance() {
        if (this.ambianceSoundKey != null) {
            logger.info("Fading out ambiance: " + this.ambianceSoundKey);
            CardCrawlGame.sound.fadeOut(this.ambianceSoundKey, this.ambianceSoundId);
            this.ambianceSoundKey = null;
        }
    }

    public void fadeInAmbiance() {
        if (this.ambianceSoundKey == null) {
            logger.info("Fading in ambiance: " + this.ambianceName);
            this.ambianceSoundKey = this.ambianceName;
            this.ambianceSoundId = CardCrawlGame.sound.playAndLoop(this.ambianceName);
            updateAmbienceVolume();
        }
    }

    public void muteAmbienceVolume() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.adjustVolume(this.ambianceName, this.ambianceSoundId, 0.0F);
        }
    }

    public void updateAmbienceVolume() {
        if (this.ambianceSoundId != 0L) {
            if (Settings.AMBIANCE_ON) {
                CardCrawlGame.sound.adjustVolume(this.ambianceName, this.ambianceSoundId);
            } else {
                CardCrawlGame.sound.adjustVolume(this.ambianceName, this.ambianceSoundId, 0.0F);
            }
        }
    }
    protected void renderAtlasRegionIf(SpriteBatch sb, TextureAtlas.AtlasRegion region, boolean condition) {
        if (condition) {
            if (Settings.isFourByThree) {
                sb.draw(region
                        .getTexture(), region.offsetX * Settings.scale, region.offsetY * Settings.yScale, 0.0F, 0.0F, region.packedWidth, region.packedHeight, Settings.scale, Settings.yScale, 0.0F, region
                        .getRegionX(), region
                        .getRegionY(), region
                        .getRegionWidth(), region
                        .getRegionHeight(), false, false);
            } else if (Settings.isLetterbox) {
                sb.draw(region
                        .getTexture(), region.offsetX * Settings.xScale, region.offsetY * Settings.xScale, 0.0F, 0.0F, region.packedWidth, region.packedHeight, Settings.xScale, Settings.xScale, 0.0F, region
                        .getRegionX(), region
                        .getRegionY(), region
                        .getRegionWidth(), region
                        .getRegionHeight(), false, false);
            } else {
                sb.draw(region
                        .getTexture(), region.offsetX * Settings.scale, region.offsetY * Settings.scale, 0.0F, 0.0F, region.packedWidth, region.packedHeight, Settings.scale, Settings.scale, 0.0F, region
                        .getRegionX(), region
                        .getRegionY(), region
                        .getRegionWidth(), region
                        .getRegionHeight(), false, false);
            }
        }
    }
    protected void renderAtlasRegionIf(SpriteBatch sb, float x, float y, float angle, TextureAtlas.AtlasRegion region, boolean condition) {
        if (condition) {
            sb.draw(region
                    .getTexture(), region.offsetX * Settings.scale + x, region.offsetY * Settings.scale + y, region.packedWidth / 2.0F, region.packedHeight / 2.0F, region.packedWidth, region.packedHeight, Settings.scale, Settings.scale, angle, region
                    .getRegionX(), region
                    .getRegionY(), region
                    .getRegionWidth(), region
                    .getRegionHeight(), false, false);
        }
    }
    protected void renderQuadrupleSize(SpriteBatch sb, TextureAtlas.AtlasRegion region, boolean condition) {
        if (condition)
            if (Settings.isFourByThree) {
                sb.draw(region
                        .getTexture(), region.offsetX * Settings.scale * 2.0F, region.offsetY * Settings.yScale, 0.0F, 0.0F, (region.packedWidth * 2), (region.packedHeight * 2), Settings.scale, Settings.yScale, 0.0F, region
                        .getRegionX(), region
                        .getRegionY(), region
                        .getRegionWidth(), region
                        .getRegionHeight(), false, false);
            } else {
                sb.draw(region
                        .getTexture(), region.offsetX * Settings.xScale * 2.0F, region.offsetY * Settings.scale, 0.0F, 0.0F, (region.packedWidth * 2), (region.packedHeight * 2), Settings.xScale, Settings.scale, 0.0F, region
                        .getRegionX(), region
                        .getRegionY(), region
                        .getRegionWidth(), region
                        .getRegionHeight(), false, false);
            }
    }
}

