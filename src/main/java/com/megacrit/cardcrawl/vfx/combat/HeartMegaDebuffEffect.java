package com.megacrit.cardcrawl.vfx.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/vfx/combat/HeartMegaDebuffEffect.class */
public class HeartMegaDebuffEffect extends AbstractGameEffect {
    public HeartMegaDebuffEffect() {
        this.startingDuration = 4.0f;
        this.duration = this.startingDuration;
        this.color = new Color(0.9f, 0.7f, 1.0f, 0.0f);
        this.renderBehind = true;
    }

    @Override // com.megacrit.cardcrawl.vfx.AbstractGameEffect
    public void update() {
        if (this.duration == this.startingDuration) {
            CardCrawlGame.sound.playA("GHOST_ORB_IGNITE_1", -0.6f);
        }
        if (this.duration > this.startingDuration / 2.0f) {
            this.color.a = Interpolation.bounceIn.apply(1.0f, 0.0f, (this.duration - (this.startingDuration / 2.0f)) / (this.startingDuration / 2.0f));
        } else {
            this.color.a = Interpolation.bounceOut.apply(this.duration * (this.startingDuration / 2.0f));
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0f) {
            this.isDone = true;
        }
    }

    @Override // com.megacrit.cardcrawl.vfx.AbstractGameEffect
    public void render(SpriteBatch sb) {
        sb.setColor(new Color(0.0f, 0.0f, 0.0f, this.color.a * 0.8f));
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0f, 0.0f, Settings.WIDTH, Settings.HEIGHT);
        sb.setColor(this.color);
        sb.draw(ImageMaster.BORDER_GLOW_2, 0.0f, 0.0f, Settings.WIDTH, Settings.HEIGHT);
    }

    @Override // com.megacrit.cardcrawl.vfx.AbstractGameEffect, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}
