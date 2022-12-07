package com.megacrit.cardcrawl.ui.campfire;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.ShaderHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/ui/campfire/AbstractCampfireOption.class */
public abstract class AbstractCampfireOption {
    protected String label;
    protected String description;
    protected Texture img;
    private static final int W = 256;
    private Color color = Color.WHITE.cpy();
    private Color descriptionColor = Settings.CREAM_COLOR.cpy();
    private float scale = NORM_SCALE;
    public Hitbox hb = new Hitbox(216.0f * Settings.scale, 140.0f * Settings.scale);
    public boolean usable = true;
    private static final float SHDW_X = 11.0f * Settings.scale;
    private static final float SHDW_Y = (-8.0f) * Settings.scale;
    private static final float NORM_SCALE = 0.9f * Settings.scale;
    private static final float HOVER_SCALE = Settings.scale;

    public abstract void useOption();

    public void setPosition(float x, float y) {
        this.hb.move(x, y);
    }

    public void update() {
        this.hb.update();
        boolean canClick = !((RestRoom) AbstractDungeon.getCurrRoom()).campfireUI.somethingSelected && this.usable;
        if (!this.hb.hovered || !canClick) {
            this.scale = MathHelper.scaleLerpSnap(this.scale, NORM_SCALE);
        } else {
            if (this.hb.justHovered) {
                CardCrawlGame.sound.play("UI_HOVER");
            }
            if (InputHelper.justClickedLeft) {
                CardCrawlGame.sound.play("UI_CLICK_1");
                this.hb.clickStarted = true;
            }
            if (!this.hb.clickStarted) {
                this.scale = MathHelper.scaleLerpSnap(this.scale, HOVER_SCALE);
                this.scale = MathHelper.scaleLerpSnap(this.scale, HOVER_SCALE);
            } else {
                this.scale = MathHelper.scaleLerpSnap(this.scale, NORM_SCALE);
            }
        }
        if (this.hb.clicked || (CInputActionSet.select.isJustPressed() && canClick && this.hb.hovered)) {
            this.hb.clicked = false;
            if (!Settings.isTouchScreen) {
                useOption();
                ((RestRoom) AbstractDungeon.getCurrRoom()).campfireUI.somethingSelected = true;
                return;
            }
            CampfireUI campfire = ((RestRoom) AbstractDungeon.getCurrRoom()).campfireUI;
            if (campfire.touchOption != this) {
                campfire.touchOption = this;
                campfire.confirmButton.hideInstantly();
                campfire.confirmButton.isDisabled = false;
                campfire.confirmButton.show();
            }
        }
    }

    public void render(SpriteBatch sb) {
        float scaler = ((this.scale - NORM_SCALE) * 10.0f) / Settings.scale;
        sb.setColor(new Color(0.0f, 0.0f, 0.0f, this.color.a / 5.0f));
        sb.draw(this.img, (this.hb.cX - 128.0f) + SHDW_X, (this.hb.cY - 128.0f) + SHDW_Y, 128.0f, 128.0f, 256.0f, 256.0f, this.scale, this.scale, 0.0f, 0, 0, 256, 256, false, false);
        sb.setColor(new Color(1.0f, 0.93f, 0.45f, scaler));
        sb.draw(ImageMaster.CAMPFIRE_HOVER_BUTTON, this.hb.cX - 128.0f, this.hb.cY - 128.0f, 128.0f, 128.0f, 256.0f, 256.0f, this.scale * 1.075f, this.scale * 1.075f, 0.0f, 0, 0, 256, 256, false, false);
        sb.setColor(this.color);
        if (!this.usable) {
            ShaderHelper.setShader(sb, ShaderHelper.Shader.GRAYSCALE);
        }
        sb.draw(this.img, this.hb.cX - 128.0f, this.hb.cY - 128.0f, 128.0f, 128.0f, 256.0f, 256.0f, this.scale, this.scale, 0.0f, 0, 0, 256, 256, false, false);
        if (!this.usable) {
            ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
        }
        if (!this.usable) {
            FontHelper.renderFontCenteredTopAligned(sb, FontHelper.topPanelInfoFont, this.label, this.hb.cX, (this.hb.cY - (60.0f * Settings.scale)) - ((50.0f * Settings.scale) * (this.scale / Settings.scale)), Color.LIGHT_GRAY);
        } else {
            FontHelper.renderFontCenteredTopAligned(sb, FontHelper.topPanelInfoFont, this.label, this.hb.cX, (this.hb.cY - (60.0f * Settings.scale)) - ((50.0f * Settings.scale) * (this.scale / Settings.scale)), Settings.GOLD_COLOR);
        }
        this.descriptionColor.a = scaler;
        FontHelper.renderFontCenteredTopAligned(sb, FontHelper.topPanelInfoFont, this.description, 950.0f * Settings.xScale, (Settings.HEIGHT / 2.0f) + (20.0f * Settings.scale), this.descriptionColor);
        this.hb.render(sb);
    }
}