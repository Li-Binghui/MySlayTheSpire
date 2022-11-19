/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;
/*     */ 
/*     */ public class BattleStartEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*  25 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("BattleStartEffect");
/*  26 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static final float EFFECT_DUR = 4.0F;
/*  29 */   private static final float HEIGHT_DIV_2 = Settings.HEIGHT / 2.0F;
/*  30 */   private static final float WIDTH_DIV_2 = Settings.WIDTH / 2.0F;
/*     */   
/*     */   private boolean surpriseAttack;
/*     */   private boolean soundPlayed = false;
/*     */   private boolean bossFight = false;
/*     */   private Color bgColor;
/*  36 */   private static final float TARGET_HEIGHT = 150.0F * Settings.scale;
/*     */   private static final float BG_RECT_EXPAND_SPEED = 3.0F;
/*  38 */   private float currentHeight = 0.0F;
/*     */   
/*     */   private String battleStartMessage;
/*     */   
/*  42 */   private static final String BATTLE_START_MSG = TEXT[0];
/*  43 */   public static final String PLAYER_TURN_MSG = TEXT[1];
/*  44 */   public static final String ENEMY_TURN_MSG = TEXT[2];
/*  45 */   public static final String TURN_TXT = TEXT[3];
/*     */   private String turnMsg;
/*     */   private static final float TEXT_FADE_SPEED = 5.0F;
/*  48 */   private static final float MAIN_MSG_OFFSET_Y = 20.0F * Settings.scale;
/*  49 */   private static final float TURN_MSG_OFFSET_Y = -30.0F * Settings.scale;
/*  50 */   private Color turnMessageColor = new Color(0.7F, 0.7F, 0.7F, 0.0F);
/*  51 */   private float timer1 = 1.0F; private float timer2 = 1.0F;
/*  52 */   private static final float MSG_VANISH_X = -Settings.WIDTH * 0.25F;
/*  53 */   private float firstMessageX = Settings.WIDTH / 2.0F;
/*  54 */   private float secondMessageX = Settings.WIDTH * 1.5F;
/*     */   
/*     */   private boolean showHb = false;
/*     */   
/*  58 */   private static TextureAtlas.AtlasRegion img = null;
/*     */   private static final float SWORD_ANIM_TIME = 0.5F;
/*  60 */   private float swordTimer = 0.5F;
/*  61 */   private static final float SWORD_START_X = -50.0F * Settings.scale;
/*  62 */   private static final float SWORD_DEST_X = Settings.WIDTH / 2.0F + 0.0F * Settings.scale;
/*     */   private float swordX;
/*     */   private float swordY;
/*  65 */   private Color swordColor = new Color(0.9F, 0.9F, 0.85F, 0.0F); private float swordAngle; private boolean swordSound1 = false;
/*     */   
/*     */   public BattleStartEffect(boolean surpriseAttack) {
/*  68 */     this.duration = 4.0F;
/*  69 */     this.startingDuration = 4.0F;
/*  70 */     this.surpriseAttack = surpriseAttack;
/*     */     
/*  72 */     this.bgColor = new Color(AbstractDungeon.fadeColor.r / 2.0F, AbstractDungeon.fadeColor.g / 2.0F, AbstractDungeon.fadeColor.b / 2.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     if (img == null) {
/*  79 */       img = ImageMaster.vfxAtlas.findRegion("combat/battleStartSword");
/*     */     }
/*     */     
/*  82 */     this.scale = Settings.scale;
/*  83 */     this.swordY = Settings.HEIGHT / 2.0F - img.packedHeight / 2.0F + 20.0F * Settings.scale;
/*     */     
/*  85 */     if (surpriseAttack) {
/*  86 */       this.turnMsg = ENEMY_TURN_MSG;
/*     */     } else {
/*  88 */       this.turnMsg = PLAYER_TURN_MSG;
/*     */     } 
/*     */     
/*  91 */     this.color = Settings.GOLD_COLOR.cpy();
/*  92 */     this.color.a = 0.0F;
/*  93 */     if (Settings.usesOrdinal) {
/*  94 */       this.battleStartMessage = Integer.toString(GameActionManager.turn) + getOrdinalNaming(GameActionManager.turn) + TURN_TXT;
/*     */     }
/*  96 */     else if (Settings.language == Settings.GameLanguage.VIE) {
/*  97 */       this.battleStartMessage = TURN_TXT + " " + Integer.toString(GameActionManager.turn);
/*     */     } else {
/*  99 */       this.battleStartMessage = Integer.toString(GameActionManager.turn) + TURN_TXT;
/*     */     } 
/*     */     
/* 102 */     if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 103 */       this.bossFight = true;
/* 104 */       CardCrawlGame.sound.play("BATTLE_START_BOSS");
/*     */     }
/* 106 */     else if (MathUtils.randomBoolean()) {
/* 107 */       CardCrawlGame.sound.play("BATTLE_START_1");
/*     */     } else {
/* 109 */       CardCrawlGame.sound.play("BATTLE_START_2");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getOrdinalNaming(int i) {
/* 115 */     (new String[10])[0] = "th"; (new String[10])[1] = "st"; (new String[10])[2] = "nd"; (new String[10])[3] = "rd"; (new String[10])[4] = "th"; (new String[10])[5] = "th"; (new String[10])[6] = "th"; (new String[10])[7] = "th"; (new String[10])[8] = "th"; (new String[10])[9] = "th"; return (i % 100 == 11 || i % 100 == 12 || i % 100 == 13) ? "th" : (new String[10])[i % 10];
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 120 */     if (!this.showHb) {
/* 121 */       AbstractDungeon.player.showHealthBar();
/* 122 */       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 123 */         m.showHealthBar();
/*     */       }
/* 125 */       this.showHb = true;
/*     */     } 
/*     */ 
/*     */     
/* 129 */     if (this.duration > 3.0F) {
/* 130 */       this.currentHeight = MathUtils.lerp(this.currentHeight, TARGET_HEIGHT, Gdx.graphics
/*     */ 
/*     */           
/* 133 */           .getDeltaTime() * 3.0F);
/* 134 */     } else if (this.duration < 0.5F) {
/* 135 */       this.currentHeight = MathUtils.lerp(this.currentHeight, 0.0F, Gdx.graphics.getDeltaTime() * 3.0F);
/*     */     } 
/*     */ 
/*     */     
/* 139 */     if (this.duration < 3.0F && this.timer1 != 0.0F) {
/* 140 */       this.timer1 -= Gdx.graphics.getDeltaTime();
/* 141 */       if (this.timer1 < 0.0F) {
/* 142 */         this.timer1 = 0.0F;
/*     */       }
/* 144 */       this.firstMessageX = Interpolation.pow2In.apply(this.firstMessageX, MSG_VANISH_X, 1.0F - this.timer1);
/* 145 */     } else if (this.duration < 3.0F && this.timer2 != 0.0F) {
/* 146 */       if (!this.soundPlayed) {
/* 147 */         CardCrawlGame.sound.play("TURN_EFFECT");
/* 148 */         AbstractDungeon.getMonsters().showIntent();
/* 149 */         this.soundPlayed = true;
/*     */       } 
/* 151 */       this.timer2 -= Gdx.graphics.getDeltaTime();
/* 152 */       if (this.timer2 < 0.0F) {
/* 153 */         this.timer2 = 0.0F;
/*     */       }
/* 155 */       this.secondMessageX = Interpolation.pow2In.apply(this.secondMessageX, WIDTH_DIV_2, 1.0F - this.timer2);
/*     */     } 
/*     */ 
/*     */     
/* 159 */     if (this.duration > 1.0F) {
/* 160 */       this.color.a = MathUtils.lerp(this.color.a, 1.0F, Gdx.graphics.getDeltaTime() * 5.0F);
/*     */     } else {
/* 162 */       this.color.a = MathUtils.lerp(this.color.a, 0.0F, Gdx.graphics.getDeltaTime() * 5.0F);
/*     */     } 
/* 164 */     this.color.a *= 0.75F;
/* 165 */     this.turnMessageColor.a = this.color.a;
/*     */ 
/*     */     
/* 168 */     if (Settings.FAST_MODE) {
/* 169 */       this.duration -= Gdx.graphics.getDeltaTime();
/*     */     }
/* 171 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 172 */     if (this.duration < 0.0F) {
/* 173 */       this.isDone = true;
/*     */     }
/*     */     
/* 176 */     updateSwords();
/*     */   }
/*     */   
/*     */   private void updateSwords() {
/* 180 */     this.swordTimer -= Gdx.graphics.getDeltaTime();
/* 181 */     if (this.swordTimer < 0.0F) {
/* 182 */       this.swordTimer = 0.0F;
/*     */     }
/*     */     
/* 185 */     this.swordColor.a = Interpolation.fade.apply(1.0F, 0.01F, this.swordTimer / 0.5F);
/*     */     
/* 187 */     if (this.bossFight) {
/* 188 */       if (this.swordTimer < 0.1F && !this.swordSound1) {
/* 189 */         this.swordSound1 = true;
/*     */         
/* 191 */         CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
/* 192 */         for (int i = 0; i < 30; i++) {
/* 193 */           if (MathUtils.randomBoolean()) {
/* 194 */             AbstractDungeon.effectsQueue.add(new UpgradeShineParticleEffect(Settings.WIDTH / 2.0F + 
/*     */                   
/* 196 */                   MathUtils.random(-150.0F, 150.0F) * Settings.scale, Settings.HEIGHT / 2.0F + 
/* 197 */                   MathUtils.random(-10.0F, 50.0F) * Settings.scale));
/*     */           } else {
/* 199 */             AbstractDungeon.topLevelEffectsQueue.add(new UpgradeShineParticleEffect(Settings.WIDTH / 2.0F + 
/*     */                   
/* 201 */                   MathUtils.random(-150.0F, 150.0F) * Settings.scale, Settings.HEIGHT / 2.0F + 
/* 202 */                   MathUtils.random(-10.0F, 50.0F) * Settings.scale));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 207 */       this.swordX = Interpolation.pow3Out.apply(SWORD_DEST_X, SWORD_START_X, this.swordTimer / 0.5F);
/* 208 */       this.swordAngle = Interpolation.pow3Out.apply(-50.0F, 500.0F, this.swordTimer / 0.5F);
/*     */     } else {
/* 210 */       this.swordX = SWORD_DEST_X;
/* 211 */       this.swordAngle = -50.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 217 */     sb.setColor(this.bgColor);
/* 218 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, HEIGHT_DIV_2 - this.currentHeight / 2.0F, Settings.WIDTH, this.currentHeight);
/*     */     
/* 220 */     renderSwords(sb);
/*     */ 
/*     */     
/* 223 */     FontHelper.renderFontCentered(sb, FontHelper.bannerNameFont, BATTLE_START_MSG, this.firstMessageX, HEIGHT_DIV_2 + MAIN_MSG_OFFSET_Y, this.color, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     FontHelper.renderFontCentered(sb, FontHelper.bannerNameFont, this.turnMsg, this.secondMessageX, HEIGHT_DIV_2 + MAIN_MSG_OFFSET_Y, this.color, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 242 */     if (!this.surpriseAttack) {
/* 243 */       FontHelper.renderFontCentered(sb, FontHelper.turnNumFont, this.battleStartMessage, this.secondMessageX, HEIGHT_DIV_2 + TURN_MSG_OFFSET_Y, this.turnMessageColor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderSwords(SpriteBatch sb) {
/* 259 */     sb.setColor(this.swordColor);
/* 260 */     sb.draw((TextureRegion)img, Settings.WIDTH - this.swordX - img.packedWidth / 2.0F + this.firstMessageX - Settings.WIDTH / 2.0F, this.swordY, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, -this.scale, -this.scale, -this.swordAngle + 180.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 271 */     sb.draw((TextureRegion)img, this.swordX - img.packedWidth / 2.0F + this.firstMessageX - Settings.WIDTH / 2.0F, this.swordY, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale, this.scale, this.swordAngle);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\BattleStartEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */