package com.megacrit.cardcrawl.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonMeshRenderer;
import com.megacrit.cardcrawl.blights.Muzzle;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.daily.mods.Colossus;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.PreservedInsect;
import com.megacrit.cardcrawl.screens.stats.AchievementGrid;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import com.megacrit.cardcrawl.vfx.TintEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockImpactLineEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedNumberEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedWordEffect;
import com.megacrit.cardcrawl.vfx.combat.HbBlockBrokenEffect;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/core/AbstractCreature.class */
public abstract class AbstractCreature {
    public String name;
    public String id;
    public boolean isPlayer;
    public boolean isBloodied;
    public float drawX;
    public float drawY;
    public float dialogX;
    public float dialogY;
    public Hitbox hb;
    public int gold;
    public int displayGold;
    public Hitbox healthHb;
    public float hb_x;
    public float hb_y;
    public float hb_w;
    public float hb_h;
    public int currentHealth;
    public int maxHealth;
    public int currentBlock;
    private float healthBarWidth;
    private float targetHealthBarWidth;
    private static final float BLOCK_ANIM_TIME = 0.7f;
    private static final float SHOW_HB_TIME = 0.7f;
    private static final int BLOCK_W = 64;
    private static final float HEALTH_BAR_PAUSE_DURATION = 1.2f;
    private static final float POWER_ICON_PADDING_X;
    private static final float HEALTH_BG_OFFSET_X;
    public static SkeletonMeshRenderer sr;
    private static final float SHAKE_THRESHOLD;
    private static final float SHAKE_SPEED;
    public float animX;
    public float animY;
    protected float vX;
    protected float vY;
    protected CreatureAnimation animation;
    protected static final float SLOW_ATTACK_ANIM_DUR = 1.0f;
    protected static final float STAGGER_ANIM_DUR = 0.3f;
    protected static final float FAST_ATTACK_ANIM_DUR = 0.4f;
    protected static final float HOP_ANIM_DURATION = 0.7f;
    private static final float STAGGER_MOVE_SPEED;
    protected Skeleton skeleton;
    public AnimationState state;
    protected AnimationStateData stateData;
    private static final int RETICLE_W = 36;
    private static final float RETICLE_OFFSET_DIST;
    private static final Logger logger = LogManager.getLogger(AbstractCreature.class.getName());
    protected static final float TIP_X_THRESHOLD = 1544.0f * Settings.scale;
    protected static final float MULTI_TIP_Y_OFFSET = 80.0f * Settings.scale;
    protected static final float TIP_OFFSET_R_X = 20.0f * Settings.scale;
    protected static final float TIP_OFFSET_L_X = (-380.0f) * Settings.scale;
    protected static final float TIP_OFFSET_Y = 80.0f * Settings.scale;
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbstractCreature");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final float BLOCK_OFFSET_DIST = 12.0f * Settings.scale;
    private static final float HB_Y_OFFSET_DIST = 12.0f * Settings.scale;
    protected static final float BLOCK_ICON_X = (-14.0f) * Settings.scale;
    protected static final float BLOCK_ICON_Y = (-14.0f) * Settings.scale;
    private static final float HEALTH_BAR_HEIGHT = 20.0f * Settings.scale;
    private static final float HEALTH_BAR_OFFSET_Y = (-28.0f) * Settings.scale;
    private static final float HEALTH_TEXT_OFFSET_Y = 6.0f * Settings.scale;
    public ArrayList<AbstractPower> powers = new ArrayList<>();
    public boolean isDying = false;
    public boolean isDead = false;
    public boolean halfDead = false;
    public boolean flipHorizontal = false;
    public boolean flipVertical = false;
    public float escapeTimer = 0.0f;
    public boolean isEscaping = false;
    protected ArrayList<PowerTip> tips = new ArrayList<>();
    private float healthHideTimer = 0.0f;
    public int lastDamageTaken = 0;
    private float hbShowTimer = 0.0f;
    private float healthBarAnimTimer = 0.0f;
    private float blockAnimTimer = 0.0f;
    private float blockOffset = 0.0f;
    private float blockScale = 1.0f;
    public float hbAlpha = 0.0f;
    private float hbYOffset = HB_Y_OFFSET_DIST * 5.0f;
    private Color hbBgColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    private Color hbShadowColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    private Color blockColor = new Color(0.6f, 0.93f, 0.98f, 0.0f);
    private Color blockOutlineColor = new Color(0.6f, 0.93f, 0.98f, 0.0f);
    private Color blockTextColor = new Color(0.9f, 0.9f, 0.9f, 0.0f);
    private Color redHbBarColor = new Color(0.8f, 0.05f, 0.05f, 0.0f);
    private Color greenHbBarColor = Color.valueOf("78c13c00");
    private Color blueHbBarColor = Color.valueOf("31568c00");
    private Color orangeHbBarColor = new Color(1.0f, 0.5f, 0.0f, 0.0f);
    private Color hbTextColor = new Color(1.0f, 1.0f, 1.0f, 0.0f);
    public TintEffect tint = new TintEffect();
    private boolean shakeToggle = true;
    protected float animationTimer = 0.0f;
    protected TextureAtlas atlas = null;
    public float reticleAlpha = 0.0f;
    private Color reticleColor = new Color(1.0f, 1.0f, 1.0f, 0.0f);
    private Color reticleShadowColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    public boolean reticleRendered = false;
    private float reticleOffset = 0.0f;
    private float reticleAnimTimer = 0.0f;

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/core/AbstractCreature$CreatureAnimation.class */
    public enum CreatureAnimation {
        FAST_SHAKE,
        SHAKE,
        ATTACK_FAST,
        ATTACK_SLOW,
        STAGGER,
        HOP,
        JUMP
    }

    public abstract void damage(DamageInfo damageInfo);

    public abstract void render(SpriteBatch spriteBatch);

    static {
        POWER_ICON_PADDING_X = Settings.isMobile ? 55.0f * Settings.scale : 48.0f * Settings.scale;
        HEALTH_BG_OFFSET_X = 31.0f * Settings.scale;
        SHAKE_THRESHOLD = Settings.scale * 8.0f;
        SHAKE_SPEED = 150.0f * Settings.scale;
        STAGGER_MOVE_SPEED = 20.0f * Settings.scale;
        RETICLE_OFFSET_DIST = 15.0f * Settings.scale;
    }

    private void brokeBlock() {
        if (this instanceof AbstractMonster) {
            Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                r.onBlockBroken(this);
            }
        }
        AbstractDungeon.effectList.add(new HbBlockBrokenEffect((this.hb.cX - (this.hb.width / 2.0f)) + BLOCK_ICON_X, (this.hb.cY - (this.hb.height / 2.0f)) + BLOCK_ICON_Y));
        CardCrawlGame.sound.play("BLOCK_BREAK");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int decrementBlock(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.HP_LOSS && this.currentBlock > 0) {
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
            if (damageAmount > this.currentBlock) {
                damageAmount -= this.currentBlock;
                if (Settings.SHOW_DMG_BLOCK) {
                    AbstractDungeon.effectList.add(new BlockedNumberEffect(this.hb.cX, this.hb.cY + (this.hb.height / 2.0f), Integer.toString(this.currentBlock)));
                }
                loseBlock();
                brokeBlock();
            } else if (damageAmount == this.currentBlock) {
                damageAmount = 0;
                loseBlock();
                brokeBlock();
                AbstractDungeon.effectList.add(new BlockedWordEffect(this, this.hb.cX, this.hb.cY, TEXT[1]));
            } else {
                CardCrawlGame.sound.play("BLOCK_ATTACK");
                loseBlock(damageAmount);
                for (int i = 0; i < 18; i++) {
                    AbstractDungeon.effectList.add(new BlockImpactLineEffect(this.hb.cX, this.hb.cY));
                }
                if (Settings.SHOW_DMG_BLOCK) {
                    AbstractDungeon.effectList.add(new BlockedNumberEffect(this.hb.cX, this.hb.cY + (this.hb.height / 2.0f), Integer.toString(damageAmount)));
                }
                damageAmount = 0;
            }
        }
        return damageAmount;
    }

    public void increaseMaxHp(int amount, boolean showEffect) {
        if (!Settings.isEndless || !AbstractDungeon.player.hasBlight(Muzzle.ID)) {
            if (amount < 0) {
                logger.info("Why are we decreasing health with increaseMaxHealth()?");
            }
            this.maxHealth += amount;
            AbstractDungeon.effectsQueue.add(new TextAboveCreatureEffect(this.hb.cX - this.animX, this.hb.cY, TEXT[2] + Integer.toString(amount), Settings.GREEN_TEXT_COLOR));
            heal(amount, true);
            healthBarUpdatedEvent();
        }
    }

    public void decreaseMaxHealth(int amount) {
        if (amount < 0) {
            logger.info("Why are we increasing health with decreaseMaxHealth()?");
        }
        this.maxHealth -= amount;
        if (this.maxHealth <= 1) {
            this.maxHealth = 1;
        }
        if (this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }
        healthBarUpdatedEvent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void refreshHitboxLocation() {
        this.hb.move(this.drawX + this.hb_x + this.animX, this.drawY + this.hb_y + (this.hb_h / 2.0f));
        this.healthHb.move(this.hb.cX, (this.hb.cY - (this.hb_h / 2.0f)) - (this.healthHb.height / 2.0f));
    }

    public void updateAnimations() {
        if (this.animationTimer != 0.0f) {
            switch (this.animation) {
                case ATTACK_FAST:
                    updateFastAttackAnimation();
                    break;
                case ATTACK_SLOW:
                    updateSlowAttackAnimation();
                    break;
                case FAST_SHAKE:
                    updateFastShakeAnimation();
                    break;
                case HOP:
                    updateHopAnimation();
                    break;
                case JUMP:
                    updateJumpAnimation();
                    break;
                case SHAKE:
                    updateShakeAnimation();
                    break;
                case STAGGER:
                    updateStaggerAnimation();
                    break;
            }
        }
        refreshHitboxLocation();
        if (!this.isPlayer) {
            ((AbstractMonster) this).refreshIntentHbLocation();
        }
    }

    protected void updateFastAttackAnimation() {
        this.animationTimer -= Gdx.graphics.getDeltaTime();
        float targetPos = 90.0f * Settings.scale;
        if (!this.isPlayer) {
            targetPos = -targetPos;
        }
        if (this.animationTimer > 0.5f) {
            this.animX = Interpolation.exp5In.apply(0.0f, targetPos, (1.0f - (this.animationTimer / 1.0f)) * 2.0f);
        } else if (this.animationTimer < 0.0f) {
            this.animationTimer = 0.0f;
            this.animX = 0.0f;
        } else {
            this.animX = Interpolation.fade.apply(0.0f, targetPos, (this.animationTimer / 1.0f) * 2.0f);
        }
    }

    protected void updateSlowAttackAnimation() {
        this.animationTimer -= Gdx.graphics.getDeltaTime();
        float targetPos = 90.0f * Settings.scale;
        if (!this.isPlayer) {
            targetPos = -targetPos;
        }
        if (this.animationTimer > 0.5f) {
            this.animX = Interpolation.exp10In.apply(0.0f, targetPos, (1.0f - (this.animationTimer / 1.0f)) * 2.0f);
        } else if (this.animationTimer < 0.0f) {
            this.animationTimer = 0.0f;
            this.animX = 0.0f;
        } else {
            this.animX = Interpolation.fade.apply(0.0f, targetPos, (this.animationTimer / 1.0f) * 2.0f);
        }
    }

    protected void updateFastShakeAnimation() {
        this.animationTimer -= Gdx.graphics.getDeltaTime();
        if (this.animationTimer < 0.0f) {
            this.animationTimer = 0.0f;
            this.animX = 0.0f;
        } else if (this.shakeToggle) {
            this.animX += SHAKE_SPEED * Gdx.graphics.getDeltaTime();
            if (this.animX > SHAKE_THRESHOLD / 2.0f) {
                this.shakeToggle = !this.shakeToggle;
            }
        } else {
            this.animX -= SHAKE_SPEED * Gdx.graphics.getDeltaTime();
            if (this.animX < (-SHAKE_THRESHOLD) / 2.0f) {
                this.shakeToggle = !this.shakeToggle;
            }
        }
    }

    protected void updateHopAnimation() {
        this.vY -= 17.0f * Settings.scale;
        this.animY += this.vY * Gdx.graphics.getDeltaTime();
        if (this.animY < 0.0f) {
            this.animationTimer = 0.0f;
            this.animY = 0.0f;
        }
    }

    protected void updateJumpAnimation() {
        this.vY -= 17.0f * Settings.scale;
        this.animY += this.vY * Gdx.graphics.getDeltaTime();
        if (this.animY < 0.0f) {
            this.animationTimer = 0.0f;
            this.animY = 0.0f;
        }
    }

    protected void updateStaggerAnimation() {
        if (this.animationTimer != 0.0f) {
            this.animationTimer -= Gdx.graphics.getDeltaTime();
            if (!this.isPlayer) {
                this.animX = Interpolation.pow2.apply(STAGGER_MOVE_SPEED, 0.0f, 1.0f - (this.animationTimer / 0.3f));
            } else {
                this.animX = Interpolation.pow2.apply(-STAGGER_MOVE_SPEED, 0.0f, 1.0f - (this.animationTimer / 0.3f));
            }
            if (this.animationTimer < 0.0f) {
                this.animationTimer = 0.0f;
                this.animX = 0.0f;
                this.vX = STAGGER_MOVE_SPEED;
            }
        }
    }

    protected void updateShakeAnimation() {
        this.animationTimer -= Gdx.graphics.getDeltaTime();
        if (this.animationTimer < 0.0f) {
            this.animationTimer = 0.0f;
            this.animX = 0.0f;
        } else if (this.shakeToggle) {
            this.animX += SHAKE_SPEED * Gdx.graphics.getDeltaTime();
            if (this.animX > SHAKE_THRESHOLD) {
                this.shakeToggle = !this.shakeToggle;
            }
        } else {
            this.animX -= SHAKE_SPEED * Gdx.graphics.getDeltaTime();
            if (this.animX < (-SHAKE_THRESHOLD)) {
                this.shakeToggle = !this.shakeToggle;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void loadAnimation(String atlasUrl, String skeletonUrl, float scale) {
        this.atlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
        SkeletonJson json = new SkeletonJson(this.atlas);
        if (!(CardCrawlGame.dungeon == null || AbstractDungeon.player == null)) {
            if (AbstractDungeon.player.hasRelic(PreservedInsect.ID) && !this.isPlayer && AbstractDungeon.getCurrRoom().eliteTrigger) {
                scale += 0.3f;
            }
            if (ModHelper.isModEnabled(Colossus.ID) && !this.isPlayer) {
                scale -= 0.3f;
            }
        }
        json.setScale(Settings.renderScale / scale);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
        this.skeleton = new Skeleton(skeletonData);
        this.skeleton.setColor(Color.WHITE);
        this.stateData = new AnimationStateData(skeletonData);
        this.state = new AnimationState(this.stateData);
    }

    public void heal(int healAmount, boolean showEffect) {
        if (Settings.isEndless && this.isPlayer && AbstractDungeon.player.hasBlight(Muzzle.ID)) {
            healAmount /= 2;
            if (healAmount < 1) {
                healAmount = 1;
            }
        }
        if (!this.isDying) {
            Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                if (this.isPlayer) {
                    healAmount = r.onPlayerHeal(healAmount);
                }
            }
            Iterator<AbstractPower> it2 = this.powers.iterator();
            while (it2.hasNext()) {
                AbstractPower p = it2.next();
                healAmount = p.onHeal(healAmount);
            }
            this.currentHealth += healAmount;
            if (this.currentHealth > this.maxHealth) {
                this.currentHealth = this.maxHealth;
            }
            if (this.currentHealth > this.maxHealth / 2.0f && this.isBloodied) {
                this.isBloodied = false;
                Iterator<AbstractRelic> it3 = AbstractDungeon.player.relics.iterator();
                while (it3.hasNext()) {
                    AbstractRelic r2 = it3.next();
                    r2.onNotBloodied();
                }
            }
            if (healAmount > 0) {
                if (showEffect && this.isPlayer) {
                    AbstractDungeon.topPanel.panelHealEffect();
                    AbstractDungeon.effectsQueue.add(new HealEffect(this.hb.cX - this.animX, this.hb.cY, healAmount));
                }
                healthBarUpdatedEvent();
            }
        }
    }

    public void heal(int amount) {
        heal(amount, true);
    }

    public void addBlock(int blockAmount) {
        float tmp = blockAmount;
        if (this.isPlayer) {
            Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                tmp = r.onPlayerGainedBlock(tmp);
            }
            if (tmp > 0.0f) {
                Iterator<AbstractPower> it2 = this.powers.iterator();
                while (it2.hasNext()) {
                    AbstractPower p = it2.next();
                    p.onGainedBlock(tmp);
                }
            }
        }
        boolean effect = false;
        if (this.currentBlock == 0) {
            effect = true;
        }
        Iterator<AbstractMonster> it3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (it3.hasNext()) {
            AbstractMonster m = it3.next();
            Iterator<AbstractPower> it4 = m.powers.iterator();
            while (it4.hasNext()) {
                AbstractPower p2 = it4.next();
                tmp = p2.onPlayerGainedBlock(tmp);
            }
        }
        this.currentBlock += MathUtils.floor(tmp);
        if (this.currentBlock >= 99 && this.isPlayer) {
            UnlockTracker.unlockAchievement(AchievementGrid.IMPERVIOUS_KEY);
        }
        if (this.currentBlock > 999) {
            this.currentBlock = 999;
        }
        if (this.currentBlock == 999) {
            UnlockTracker.unlockAchievement(AchievementGrid.BARRICADED_KEY);
        }
        if (effect && this.currentBlock > 0) {
            gainBlockAnimation();
        } else if (blockAmount > 0 && blockAmount > 0) {
            Color tmpCol = Settings.GOLD_COLOR.cpy();
            tmpCol.a = this.blockTextColor.a;
            this.blockTextColor = tmpCol;
            this.blockScale = 5.0f;
        }
    }

    public void loseBlock(int amount, boolean noAnimation) {
        boolean effect = false;
        if (this.currentBlock != 0) {
            effect = true;
        }
        this.currentBlock -= amount;
        if (this.currentBlock < 0) {
            this.currentBlock = 0;
        }
        if (this.currentBlock != 0 || !effect) {
            if (this.currentBlock > 0 && amount > 0) {
                Color tmp = Color.SCARLET.cpy();
                tmp.a = this.blockTextColor.a;
                this.blockTextColor = tmp;
                this.blockScale = 5.0f;
            }
        } else if (!noAnimation) {
            AbstractDungeon.effectList.add(new HbBlockBrokenEffect((this.hb.cX - (this.hb.width / 2.0f)) + BLOCK_ICON_X, (this.hb.cY - (this.hb.height / 2.0f)) + BLOCK_ICON_Y));
        }
    }

    public void loseBlock() {
        loseBlock(this.currentBlock);
    }

    public void loseBlock(boolean noAnimation) {
        loseBlock(this.currentBlock, noAnimation);
    }

    public void loseBlock(int amount) {
        loseBlock(amount, false);
    }

    public void showHealthBar() {
        this.hbShowTimer = 0.7f;
        this.hbAlpha = 0.0f;
    }

    public void hideHealthBar() {
        this.hbAlpha = 0.0f;
    }

    public void addPower(AbstractPower powerToApply) {
        boolean hasBuffAlready = false;
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            if (p.ID.equals(powerToApply.ID)) {
                p.stackPower(powerToApply.amount);
                p.updateDescription();
                hasBuffAlready = true;
            }
        }
        if (!hasBuffAlready) {
            this.powers.add(powerToApply);
            if (this.isPlayer) {
                int buffCount = 0;
                Iterator<AbstractPower> it2 = this.powers.iterator();
                while (it2.hasNext()) {
                    if (it2.next().type == AbstractPower.PowerType.BUFF) {
                        buffCount++;
                    }
                }
                if (buffCount >= 10) {
                    UnlockTracker.unlockAchievement(AchievementGrid.POWERFUL_KEY);
                }
            }
        }
    }

    public void applyStartOfTurnPowers() {
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            p.atStartOfTurn();
        }
    }

    public void applyTurnPowers() {
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            p.duringTurn();
        }
    }

    public void applyStartOfTurnPostDrawPowers() {
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            p.atStartOfTurnPostDraw();
        }
    }

    public void applyEndOfTurnTriggers() {
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            if (!this.isPlayer) {
                p.atEndOfTurnPreEndTurnCards(false);
            }
            p.atEndOfTurn(this.isPlayer);
        }
    }

    public void healthBarUpdatedEvent() {
        this.healthBarAnimTimer = HEALTH_BAR_PAUSE_DURATION;
        this.targetHealthBarWidth = (this.hb.width * this.currentHealth) / this.maxHealth;
        if (this.maxHealth == this.currentHealth) {
            this.healthBarWidth = this.targetHealthBarWidth;
        } else if (this.currentHealth == 0) {
            this.healthBarWidth = 0.0f;
            this.targetHealthBarWidth = 0.0f;
        }
        if (this.targetHealthBarWidth > this.healthBarWidth) {
            this.healthBarWidth = this.targetHealthBarWidth;
        }
    }

    public void healthBarRevivedEvent() {
        this.healthBarAnimTimer = HEALTH_BAR_PAUSE_DURATION;
        this.targetHealthBarWidth = (this.hb.width * this.currentHealth) / this.maxHealth;
        this.healthBarWidth = this.targetHealthBarWidth;
        this.hbBgColor.a = 0.75f;
        this.hbShadowColor.a = 0.5f;
        this.hbTextColor.a = 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateHealthBar() {
        updateHbHoverFade();
        updateBlockAnimations();
        updateHbPopInAnimation();
        updateHbDamageAnimation();
        updateHbAlpha();
    }

    private void updateHbHoverFade() {
        if (this.healthHb.hovered) {
            this.healthHideTimer -= Gdx.graphics.getDeltaTime() * 4.0f;
            if (this.healthHideTimer < 0.2f) {
                this.healthHideTimer = 0.2f;
                return;
            }
            return;
        }
        this.healthHideTimer += Gdx.graphics.getDeltaTime() * 4.0f;
        if (this.healthHideTimer > 1.0f) {
            this.healthHideTimer = 1.0f;
        }
    }

    private void updateHbAlpha() {
        if ((this instanceof AbstractMonster) && ((AbstractMonster) this).isEscaping) {
            this.hbAlpha = MathHelper.fadeLerpSnap(this.hbAlpha, 0.0f);
            this.targetHealthBarWidth = 0.0f;
            this.hbBgColor.a = this.hbAlpha * 0.75f;
            this.hbShadowColor.a = this.hbAlpha * 0.5f;
            this.hbTextColor.a = this.hbAlpha;
            this.orangeHbBarColor.a = this.hbAlpha;
            this.redHbBarColor.a = this.hbAlpha;
            this.greenHbBarColor.a = this.hbAlpha;
            this.blueHbBarColor.a = this.hbAlpha;
            this.blockOutlineColor.a = this.hbAlpha;
        } else if (this.targetHealthBarWidth != 0.0f || this.healthBarAnimTimer > 0.0f) {
            this.hbBgColor.a = this.hbAlpha * 0.5f;
            this.hbShadowColor.a = this.hbAlpha * 0.2f;
            this.hbTextColor.a = this.hbAlpha;
            this.orangeHbBarColor.a = this.hbAlpha;
            this.redHbBarColor.a = this.hbAlpha;
            this.greenHbBarColor.a = this.hbAlpha;
            this.blueHbBarColor.a = this.hbAlpha;
            this.blockOutlineColor.a = this.hbAlpha;
        } else {
            this.hbShadowColor.a = MathHelper.fadeLerpSnap(this.hbShadowColor.a, 0.0f);
            this.hbBgColor.a = MathHelper.fadeLerpSnap(this.hbBgColor.a, 0.0f);
            this.hbTextColor.a = MathHelper.fadeLerpSnap(this.hbTextColor.a, 0.0f);
            this.blockOutlineColor.a = MathHelper.fadeLerpSnap(this.blockOutlineColor.a, 0.0f);
        }
    }

    protected void gainBlockAnimation() {
        this.blockAnimTimer = 0.7f;
        this.blockTextColor.a = 0.0f;
        this.blockColor.a = 0.0f;
    }

    private void updateBlockAnimations() {
        if (this.currentBlock > 0) {
            if (this.blockAnimTimer > 0.0f) {
                this.blockAnimTimer -= Gdx.graphics.getDeltaTime();
                if (this.blockAnimTimer < 0.0f) {
                    this.blockAnimTimer = 0.0f;
                }
                this.blockOffset = Interpolation.swingOut.apply(BLOCK_OFFSET_DIST * 3.0f, 0.0f, 1.0f - (this.blockAnimTimer / 0.7f));
                this.blockScale = Interpolation.pow3In.apply(3.0f, 1.0f, 1.0f - (this.blockAnimTimer / 0.7f));
                this.blockColor.a = Interpolation.pow2Out.apply(0.0f, 1.0f, 1.0f - (this.blockAnimTimer / 0.7f));
                this.blockTextColor.a = Interpolation.pow5In.apply(0.0f, 1.0f, 1.0f - (this.blockAnimTimer / 0.7f));
            } else if (this.blockScale != 1.0f) {
                this.blockScale = MathHelper.scaleLerpSnap(this.blockScale, 1.0f);
            }
            if (this.blockTextColor.r != 1.0f) {
                this.blockTextColor.r = MathHelper.slowColorLerpSnap(this.blockTextColor.r, 1.0f);
            }
            if (this.blockTextColor.g != 1.0f) {
                this.blockTextColor.g = MathHelper.slowColorLerpSnap(this.blockTextColor.g, 1.0f);
            }
            if (this.blockTextColor.b != 1.0f) {
                this.blockTextColor.b = MathHelper.slowColorLerpSnap(this.blockTextColor.b, 1.0f);
            }
        }
    }

    private void updateHbPopInAnimation() {
        if (this.hbShowTimer > 0.0f) {
            this.hbShowTimer -= Gdx.graphics.getDeltaTime();
            if (this.hbShowTimer < 0.0f) {
                this.hbShowTimer = 0.0f;
            }
            this.hbAlpha = Interpolation.fade.apply(0.0f, 1.0f, 1.0f - (this.hbShowTimer / 0.7f));
            this.hbYOffset = Interpolation.exp10Out.apply(HB_Y_OFFSET_DIST * 5.0f, 0.0f, 1.0f - (this.hbShowTimer / 0.7f));
        }
    }

    private void updateHbDamageAnimation() {
        if (this.healthBarAnimTimer > 0.0f) {
            this.healthBarAnimTimer -= Gdx.graphics.getDeltaTime();
        }
        if (this.healthBarWidth != this.targetHealthBarWidth && this.healthBarAnimTimer <= 0.0f && this.targetHealthBarWidth < this.healthBarWidth) {
            this.healthBarWidth = MathHelper.uiLerpSnap(this.healthBarWidth, this.targetHealthBarWidth);
        }
    }

    public void updatePowers() {
        for (int i = 0; i < this.powers.size(); i++) {
            this.powers.get(i).update(i);
        }
    }

    public static void initialize() {
        sr = new SkeletonMeshRenderer();
        sr.setPremultipliedAlpha(true);
    }

    public void renderPowerTips(SpriteBatch sb) {
        this.tips.clear();
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            if (p.region48 != null) {
                this.tips.add(new PowerTip(p.name, p.description, p.region48));
            } else {
                this.tips.add(new PowerTip(p.name, p.description, p.img));
            }
        }
        if (this.tips.isEmpty()) {
            return;
        }
        if (this.hb.cX + (this.hb.width / 2.0f) < TIP_X_THRESHOLD) {
            TipHelper.queuePowerTips(this.hb.cX + (this.hb.width / 2.0f) + TIP_OFFSET_R_X, this.hb.cY + TipHelper.calculateAdditionalOffset(this.tips, this.hb.cY), this.tips);
        } else {
            TipHelper.queuePowerTips((this.hb.cX - (this.hb.width / 2.0f)) + TIP_OFFSET_L_X, this.hb.cY + TipHelper.calculateAdditionalOffset(this.tips, this.hb.cY), this.tips);
        }
    }

    public void useFastAttackAnimation() {
        this.animX = 0.0f;
        this.animY = 0.0f;
        this.animationTimer = 0.4f;
        this.animation = CreatureAnimation.ATTACK_FAST;
    }

    public void useSlowAttackAnimation() {
        this.animX = 0.0f;
        this.animY = 0.0f;
        this.animationTimer = 1.0f;
        this.animation = CreatureAnimation.ATTACK_SLOW;
    }

    public void useHopAnimation() {
        this.animX = 0.0f;
        this.animY = 0.0f;
        this.vY = 300.0f * Settings.scale;
        this.animationTimer = 0.7f;
        this.animation = CreatureAnimation.HOP;
    }

    public void useJumpAnimation() {
        this.animX = 0.0f;
        this.animY = 0.0f;
        this.vY = 500.0f * Settings.scale;
        this.animationTimer = 0.7f;
        this.animation = CreatureAnimation.JUMP;
    }

    public void useStaggerAnimation() {
        if (this.animY == 0.0f) {
            this.animX = 0.0f;
            this.animationTimer = 0.3f;
            this.animation = CreatureAnimation.STAGGER;
        }
    }

    public void useFastShakeAnimation(float duration) {
        if (this.animY == 0.0f) {
            this.animX = 0.0f;
            this.animationTimer = duration;
            this.animation = CreatureAnimation.FAST_SHAKE;
        }
    }

    public void useShakeAnimation(float duration) {
        if (this.animY == 0.0f) {
            this.animX = 0.0f;
            this.animationTimer = duration;
            this.animation = CreatureAnimation.SHAKE;
        }
    }

    public AbstractPower getPower(String targetID) {
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            if (p.ID.equals(targetID)) {
                return p;
            }
        }
        return null;
    }

    public boolean hasPower(String targetID) {
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            if (p.ID.equals(targetID)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDeadOrEscaped() {
        if (this.isDying || this.halfDead) {
            return true;
        }
        if (this.isPlayer) {
            return false;
        }
        AbstractMonster m = (AbstractMonster) this;
        if (m.isEscaping) {
            return true;
        }
        return false;
    }

    public void loseGold(int goldAmount) {
        if (goldAmount > 0) {
            this.gold -= goldAmount;
            if (this.gold < 0) {
                this.gold = 0;
                return;
            }
            return;
        }
        logger.info("NEGATIVE MONEY???");
    }

    public void gainGold(int amount) {
        if (amount < 0) {
            logger.info("NEGATIVE MONEY???");
        } else {
            this.gold += amount;
        }
    }

    public void renderReticle(SpriteBatch sb) {
        this.reticleRendered = true;
        renderReticleCorner(sb, ((-this.hb.width) / 2.0f) + this.reticleOffset, (this.hb.height / 2.0f) - this.reticleOffset, false, false);
        renderReticleCorner(sb, (this.hb.width / 2.0f) - this.reticleOffset, (this.hb.height / 2.0f) - this.reticleOffset, true, false);
        renderReticleCorner(sb, ((-this.hb.width) / 2.0f) + this.reticleOffset, ((-this.hb.height) / 2.0f) + this.reticleOffset, false, true);
        renderReticleCorner(sb, (this.hb.width / 2.0f) - this.reticleOffset, ((-this.hb.height) / 2.0f) + this.reticleOffset, true, true);
    }

    public void renderReticle(SpriteBatch sb, Hitbox hb) {
        this.reticleRendered = true;
        renderReticleCorner(sb, ((-hb.width) / 2.0f) + this.reticleOffset, (hb.height / 2.0f) - this.reticleOffset, hb, false, false);
        renderReticleCorner(sb, (hb.width / 2.0f) - this.reticleOffset, (hb.height / 2.0f) - this.reticleOffset, hb, true, false);
        renderReticleCorner(sb, ((-hb.width) / 2.0f) + this.reticleOffset, ((-hb.height) / 2.0f) + this.reticleOffset, hb, false, true);
        renderReticleCorner(sb, (hb.width / 2.0f) - this.reticleOffset, ((-hb.height) / 2.0f) + this.reticleOffset, hb, true, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateReticle() {
        if (this.reticleRendered) {
            this.reticleRendered = false;
            this.reticleAlpha += Gdx.graphics.getDeltaTime() * 3.0f;
            if (this.reticleAlpha > 1.0f) {
                this.reticleAlpha = 1.0f;
            }
            this.reticleAnimTimer += Gdx.graphics.getDeltaTime();
            if (this.reticleAnimTimer > 1.0f) {
                this.reticleAnimTimer = 1.0f;
            }
            this.reticleOffset = Interpolation.elasticOut.apply(RETICLE_OFFSET_DIST, 0.0f, this.reticleAnimTimer);
            return;
        }
        this.reticleAlpha = 0.0f;
        this.reticleAnimTimer = 0.0f;
        this.reticleOffset = RETICLE_OFFSET_DIST;
    }

    public void renderHealth(SpriteBatch sb) {
        if (!Settings.hideCombatElements) {
            float x = this.hb.cX - (this.hb.width / 2.0f);
            float y = (this.hb.cY - (this.hb.height / 2.0f)) + this.hbYOffset;
            renderHealthBg(sb, x, y);
            if (this.targetHealthBarWidth != 0.0f) {
                renderOrangeHealthBar(sb, x, y);
                if (hasPower(PoisonPower.POWER_ID)) {
                    renderGreenHealthBar(sb, x, y);
                }
                renderRedHealthBar(sb, x, y);
            }
            if (!(this.currentBlock == 0 || this.hbAlpha == 0.0f)) {
                renderBlockOutline(sb, x, y);
            }
            renderHealthText(sb, y);
            if (!(this.currentBlock == 0 || this.hbAlpha == 0.0f)) {
                renderBlockIconAndValue(sb, x, y);
            }
            renderPowerIcons(sb, x, y);
        }
    }

    private void renderBlockOutline(SpriteBatch sb, float x, float y) {
        sb.setColor(this.blockOutlineColor);
        sb.setBlendFunction(770, 1);
        sb.draw(ImageMaster.BLOCK_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        sb.draw(ImageMaster.BLOCK_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.hb.width, HEALTH_BAR_HEIGHT);
        sb.draw(ImageMaster.BLOCK_BAR_R, x + this.hb.width, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        sb.setBlendFunction(770, 771);
    }

    private void renderBlockIconAndValue(SpriteBatch sb, float x, float y) {
        sb.setColor(this.blockColor);
        sb.draw(ImageMaster.BLOCK_ICON, (x + BLOCK_ICON_X) - 32.0f, ((y + BLOCK_ICON_Y) - 32.0f) + this.blockOffset, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
        FontHelper.renderFontCentered(sb, FontHelper.blockInfoFont, Integer.toString(this.currentBlock), x + BLOCK_ICON_X, y - (16.0f * Settings.scale), this.blockTextColor, this.blockScale);
    }

    private void renderHealthBg(SpriteBatch sb, float x, float y) {
        sb.setColor(this.hbShadowColor);
        sb.draw(ImageMaster.HB_SHADOW_L, x - HEALTH_BAR_HEIGHT, (y - HEALTH_BG_OFFSET_X) + (3.0f * Settings.scale), HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        sb.draw(ImageMaster.HB_SHADOW_B, x, (y - HEALTH_BG_OFFSET_X) + (3.0f * Settings.scale), this.hb.width, HEALTH_BAR_HEIGHT);
        sb.draw(ImageMaster.HB_SHADOW_R, x + this.hb.width, (y - HEALTH_BG_OFFSET_X) + (3.0f * Settings.scale), HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        sb.setColor(this.hbBgColor);
        if (this.currentHealth != this.maxHealth) {
            sb.draw(ImageMaster.HEALTH_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
            sb.draw(ImageMaster.HEALTH_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.hb.width, HEALTH_BAR_HEIGHT);
            sb.draw(ImageMaster.HEALTH_BAR_R, x + this.hb.width, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        }
    }

    private void renderOrangeHealthBar(SpriteBatch sb, float x, float y) {
        sb.setColor(this.orangeHbBarColor);
        sb.draw(ImageMaster.HEALTH_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.healthBarWidth, HEALTH_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_R, x + this.healthBarWidth, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
    }

    private void renderGreenHealthBar(SpriteBatch sb, float x, float y) {
        sb.setColor(this.greenHbBarColor);
        if (this.currentHealth > 0) {
            sb.draw(ImageMaster.HEALTH_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        }
        sb.draw(ImageMaster.HEALTH_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.targetHealthBarWidth, HEALTH_BAR_HEIGHT);
        sb.draw(ImageMaster.HEALTH_BAR_R, x + this.targetHealthBarWidth, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
    }

    private void renderRedHealthBar(SpriteBatch sb, float x, float y) {
        if (this.currentBlock > 0) {
            sb.setColor(this.blueHbBarColor);
        } else {
            sb.setColor(this.redHbBarColor);
        }
        if (!hasPower(PoisonPower.POWER_ID)) {
            if (this.currentHealth > 0) {
                sb.draw(ImageMaster.HEALTH_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
            }
            sb.draw(ImageMaster.HEALTH_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.targetHealthBarWidth, HEALTH_BAR_HEIGHT);
            sb.draw(ImageMaster.HEALTH_BAR_R, x + this.targetHealthBarWidth, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
            return;
        }
        int poisonAmt = getPower(PoisonPower.POWER_ID).amount;
        if (poisonAmt > 0 && hasPower(IntangiblePower.POWER_ID)) {
            poisonAmt = 1;
        }
        if (this.currentHealth > poisonAmt) {
            float w = (1.0f - ((this.currentHealth - poisonAmt) / this.currentHealth)) * this.targetHealthBarWidth;
            if (this.currentHealth > 0) {
                sb.draw(ImageMaster.HEALTH_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
            }
            sb.draw(ImageMaster.HEALTH_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.targetHealthBarWidth - w, HEALTH_BAR_HEIGHT);
            sb.draw(ImageMaster.HEALTH_BAR_R, (x + this.targetHealthBarWidth) - w, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
        }
    }

    private void renderHealthText(SpriteBatch sb, float y) {
        if (this.targetHealthBarWidth != 0.0f) {
            float tmp = this.hbTextColor.a;
            this.hbTextColor.a *= this.healthHideTimer;
            FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, this.currentHealth + "/" + this.maxHealth, this.hb.cX, y + HEALTH_BAR_OFFSET_Y + HEALTH_TEXT_OFFSET_Y + (5.0f * Settings.scale), this.hbTextColor);
            this.hbTextColor.a = tmp;
            return;
        }
        FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, TEXT[0], this.hb.cX, ((y + HEALTH_BAR_OFFSET_Y) + HEALTH_TEXT_OFFSET_Y) - (1.0f * Settings.scale), this.hbTextColor);
    }

    private void renderPowerIcons(SpriteBatch sb, float x, float y) {
        float offset = 10.0f * Settings.scale;
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            if (Settings.isMobile) {
                p.renderIcons(sb, x + offset, y - (53.0f * Settings.scale), this.hbTextColor);
            } else {
                p.renderIcons(sb, x + offset, y - (48.0f * Settings.scale), this.hbTextColor);
            }
            offset += POWER_ICON_PADDING_X;
        }
        float offset2 = 0.0f * Settings.scale;
        Iterator<AbstractPower> it2 = this.powers.iterator();
        while (it2.hasNext()) {
            AbstractPower p2 = it2.next();
            if (Settings.isMobile) {
                p2.renderAmount(sb, x + offset2 + (32.0f * Settings.scale), y - (75.0f * Settings.scale), this.hbTextColor);
            } else {
                p2.renderAmount(sb, x + offset2 + (32.0f * Settings.scale), y - (66.0f * Settings.scale), this.hbTextColor);
            }
            offset2 += POWER_ICON_PADDING_X;
        }
    }

    private void renderReticleCorner(SpriteBatch sb, float x, float y, Hitbox hb, boolean flipX, boolean flipY) {
        this.reticleShadowColor.a = this.reticleAlpha / 4.0f;
        sb.setColor(this.reticleShadowColor);
        sb.draw(ImageMaster.RETICLE_CORNER, ((hb.cX + x) - 18.0f) + (4.0f * Settings.scale), ((hb.cY + y) - 18.0f) - (4.0f * Settings.scale), 18.0f, 18.0f, 36.0f, 36.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 36, 36, flipX, flipY);
        this.reticleColor.a = this.reticleAlpha;
        sb.setColor(this.reticleColor);
        sb.draw(ImageMaster.RETICLE_CORNER, (hb.cX + x) - 18.0f, (hb.cY + y) - 18.0f, 18.0f, 18.0f, 36.0f, 36.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 36, 36, flipX, flipY);
    }

    private void renderReticleCorner(SpriteBatch sb, float x, float y, boolean flipX, boolean flipY) {
        this.reticleShadowColor.a = this.reticleAlpha / 4.0f;
        sb.setColor(this.reticleShadowColor);
        sb.draw(ImageMaster.RETICLE_CORNER, ((this.hb.cX + x) - 18.0f) + (4.0f * Settings.scale), ((this.hb.cY + y) - 18.0f) - (4.0f * Settings.scale), 18.0f, 18.0f, 36.0f, 36.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 36, 36, flipX, flipY);
        this.reticleColor.a = this.reticleAlpha;
        sb.setColor(this.reticleColor);
        sb.draw(ImageMaster.RETICLE_CORNER, (this.hb.cX + x) - 18.0f, (this.hb.cY + y) - 18.0f, 18.0f, 18.0f, 36.0f, 36.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 36, 36, flipX, flipY);
    }
}