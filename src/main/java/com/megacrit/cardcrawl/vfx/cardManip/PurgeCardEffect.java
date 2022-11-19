package com.megacrit.cardcrawl.vfx.cardManip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactCurvyEffect;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/vfx/cardManip/PurgeCardEffect.class */
public class PurgeCardEffect extends AbstractGameEffect {
    private AbstractCard card;
    private static final float PADDING = 30.0f * Settings.scale;
    private float scaleY;
    private Color rarityColor;

    public PurgeCardEffect(AbstractCard card) {
        this(card, Settings.WIDTH - (96.0f * Settings.scale), Settings.HEIGHT - (32.0f * Settings.scale));
    }

    public PurgeCardEffect(AbstractCard card, float x, float y) {
        this.card = card;
        this.startingDuration = 2.0f;
        this.duration = this.startingDuration;
        identifySpawnLocation(x, y);
        card.drawScale = 0.01f;
        card.targetDrawScale = 1.0f;
        CardCrawlGame.sound.play("CARD_BURN");
        initializeVfx();
    }

    private void initializeVfx() {
        switch (this.card.rarity) {
            case UNCOMMON:
                this.rarityColor = new Color(0.2f, 0.8f, 0.8f, 0.01f);
                break;
            case RARE:
                this.rarityColor = new Color(0.8f, 0.7f, 0.2f, 0.01f);
                break;
            case BASIC:
            case COMMON:
            case CURSE:
            case SPECIAL:
            default:
                this.rarityColor = new Color(0.6f, 0.6f, 0.6f, 0.01f);
                break;
        }
        switch (this.card.color) {
            case BLUE:
                this.color = new Color(0.1f, 0.4f, 0.7f, 0.01f);
                break;
            case COLORLESS:
                this.color = new Color(0.4f, 0.4f, 0.4f, 0.01f);
                break;
            case GREEN:
                this.color = new Color(0.2f, 0.7f, 0.2f, 0.01f);
                break;
            case RED:
                this.color = new Color(0.9f, 0.3f, 0.2f, 0.01f);
                break;
            case CURSE:
            default:
                this.color = new Color(0.2f, 0.15f, 0.2f, 0.01f);
                break;
        }
        this.scale = Settings.scale;
        this.scaleY = Settings.scale;
    }

    private void identifySpawnLocation(float x, float y) {
        int effectCount = 0;
        Iterator<AbstractGameEffect> it = AbstractDungeon.effectList.iterator();
        while (it.hasNext()) {
            AbstractGameEffect e = it.next();
            if (e instanceof PurgeCardEffect) {
                effectCount++;
            }
        }
        Iterator<AbstractGameEffect> it2 = AbstractDungeon.topLevelEffects.iterator();
        while (it2.hasNext()) {
            AbstractGameEffect e2 = it2.next();
            if (e2 instanceof PurgeCardEffect) {
                effectCount++;
            }
        }
        this.card.current_x = x;
        this.card.current_y = y;
        this.card.target_y = Settings.HEIGHT * 0.5f;
        switch (effectCount) {
            case 0:
                this.card.target_x = Settings.WIDTH * 0.5f;
                return;
            case 1:
                this.card.target_x = ((Settings.WIDTH * 0.5f) - PADDING) - AbstractCard.IMG_WIDTH;
                return;
            case 2:
                this.card.target_x = (Settings.WIDTH * 0.5f) + PADDING + AbstractCard.IMG_WIDTH;
                return;
            case 3:
                this.card.target_x = (Settings.WIDTH * 0.5f) - ((PADDING + AbstractCard.IMG_WIDTH) * 2.0f);
                return;
            case 4:
                this.card.target_x = (Settings.WIDTH * 0.5f) + ((PADDING + AbstractCard.IMG_WIDTH) * 2.0f);
                return;
            default:
                this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1f, Settings.WIDTH * 0.9f);
                this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2f, Settings.HEIGHT * 0.8f);
                return;
        }
    }

    @Override // com.megacrit.cardcrawl.vfx.AbstractGameEffect
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.5f) {
            if (!this.card.fadingOut) {
                this.card.fadingOut = true;
                if (!Settings.DISABLE_EFFECTS) {
                    for (int i = 0; i < 16; i++) {
                        AbstractDungeon.topLevelEffectsQueue.add(new DamageImpactCurvyEffect(this.card.current_x, this.card.current_y, this.color, false));
                    }
                    for (int i2 = 0; i2 < 8; i2++) {
                        AbstractDungeon.effectsQueue.add(new DamageImpactCurvyEffect(this.card.current_x, this.card.current_y, this.rarityColor, false));
                    }
                }
            }
            updateVfx();
        }
        this.card.update();
        if (this.duration < 0.0f) {
            this.isDone = true;
        }
    }

    private void updateVfx() {
        this.color.a = MathHelper.fadeLerpSnap(this.color.a, 0.5f);
        this.rarityColor.a = this.color.a;
        this.scale = Interpolation.swingOut.apply(1.6f, 1.0f, this.duration * 2.0f) * Settings.scale;
        this.scaleY = Interpolation.fade.apply(0.005f, 1.0f, this.duration * 2.0f) * Settings.scale;
    }

    @Override // com.megacrit.cardcrawl.vfx.AbstractGameEffect
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        this.card.render(sb);
        renderVfx(sb);
    }

    private void renderVfx(SpriteBatch sb) {
        sb.setColor(this.color);
        TextureAtlas.AtlasRegion img = ImageMaster.CARD_POWER_BG_SILHOUETTE;
        sb.draw(img, (this.card.current_x + img.offsetX) - (img.originalWidth / 2.0f), (this.card.current_y + img.offsetY) - (img.originalHeight / 2.0f), (img.originalWidth / 2.0f) - img.offsetX, (img.originalHeight / 2.0f) - img.offsetY, img.packedWidth, img.packedHeight, this.scale * MathUtils.random(0.95f, 1.05f), this.scaleY * MathUtils.random(0.95f, 1.05f), this.rotation);
        sb.setBlendFunction(770, 1);
        sb.setColor(this.rarityColor);
        TextureAtlas.AtlasRegion img2 = ImageMaster.CARD_SUPER_SHADOW;
        sb.draw(img2, (this.card.current_x + img2.offsetX) - (img2.originalWidth / 2.0f), (this.card.current_y + img2.offsetY) - (img2.originalHeight / 2.0f), (img2.originalWidth / 2.0f) - img2.offsetX, (img2.originalHeight / 2.0f) - img2.offsetY, img2.packedWidth, img2.packedHeight, this.scale * 0.75f * MathUtils.random(0.95f, 1.05f), this.scaleY * 0.75f * MathUtils.random(0.95f, 1.05f), this.rotation);
        sb.draw(img2, (this.card.current_x + img2.offsetX) - (img2.originalWidth / 2.0f), (this.card.current_y + img2.offsetY) - (img2.originalHeight / 2.0f), (img2.originalWidth / 2.0f) - img2.offsetX, (img2.originalHeight / 2.0f) - img2.offsetY, img2.packedWidth, img2.packedHeight, this.scale * 0.5f * MathUtils.random(0.95f, 1.05f), this.scaleY * 0.5f * MathUtils.random(0.95f, 1.05f), this.rotation);
        sb.setBlendFunction(770, 771);
    }

    @Override // com.megacrit.cardcrawl.vfx.AbstractGameEffect, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}