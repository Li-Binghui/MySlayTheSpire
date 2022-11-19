/*     */ package com.megacrit.cardcrawl.cards;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.CatmullRomSpline;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.badlogic.gdx.math.Vector;
/*     */ import com.badlogic.gdx.math.Vector2;
/*     */ import com.badlogic.gdx.utils.Pool;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.vfx.CardTrailEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.EmpowerEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class Soul
/*     */ {
/*     */   public AbstractCard card;
/*     */   public CardGroup group;
/*  23 */   private CatmullRomSpline<Vector2> crs = new CatmullRomSpline();
/*  24 */   private ArrayList<Vector2> controlPoints = new ArrayList<>();
/*     */   private static final int NUM_POINTS = 20;
/*  26 */   private Vector2[] points = new Vector2[20];
/*     */   private Vector2 pos;
/*     */   private Vector2 target;
/*  29 */   private float vfxTimer = 0.015F; private static final float VFX_INTERVAL = 0.015F; private float backUpTimer;
/*     */   private static final float BACK_UP_TIME = 1.5F;
/*  31 */   private float spawnStutterTimer = 0.0F;
/*     */   
/*     */   private static final float STUTTER_TIME_MAX = 0.12F;
/*     */   private boolean isInvisible = false;
/*     */   
/*  36 */   public static final Pool<CardTrailEffect> trailEffectPool = new Pool<CardTrailEffect>()
/*     */     {
/*     */       protected CardTrailEffect newObject() {
/*  39 */         return new CardTrailEffect();
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*  44 */   private static final float DISCARD_X = Settings.WIDTH * 0.96F;
/*  45 */   private static final float DISCARD_Y = Settings.HEIGHT * 0.06F;
/*  46 */   private static final float DRAW_PILE_X = Settings.WIDTH * 0.04F;
/*  47 */   private static final float DRAW_PILE_Y = Settings.HEIGHT * 0.06F;
/*  48 */   private static final float MASTER_DECK_X = Settings.WIDTH - 96.0F * Settings.scale;
/*  49 */   private static final float MASTER_DECK_Y = Settings.HEIGHT - 32.0F * Settings.scale;
/*     */ 
/*     */   
/*  52 */   private float currentSpeed = 0.0F;
/*  53 */   private static final float START_VELOCITY = 200.0F * Settings.scale;
/*  54 */   private static final float MAX_VELOCITY = 6000.0F * Settings.scale;
/*  55 */   private static final float VELOCITY_RAMP_RATE = 3000.0F * Settings.scale; public boolean isReadyForReuse;
/*     */   public boolean isDone;
/*  57 */   private static final float DST_THRESHOLD = 36.0F * Settings.scale;
/*  58 */   private static final float HOME_IN_THRESHOLD = 72.0F * Settings.scale;
/*     */   
/*     */   private float rotation;
/*     */   
/*     */   private boolean rotateClockwise = true;
/*     */   private boolean stopRotating = false;
/*     */   private float rotationRate;
/*  65 */   private static final float ROTATION_RATE = 150.0F * Settings.scale;
/*     */   
/*     */   private static final float ROTATION_RAMP_RATE = 800.0F;
/*     */   
/*  69 */   private Vector2 tmp = new Vector2();
/*     */   
/*     */   public Soul() {
/*  72 */     this.crs.controlPoints = (Vector2[]) new Vector2[1];
/*  73 */     this.isReadyForReuse = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discard(AbstractCard card, boolean visualOnly) {
/*  82 */     this.card = card;
/*  83 */     this.group = AbstractDungeon.player.discardPile;
/*  84 */     if (!visualOnly) {
/*  85 */       this.group.addToTop(card);
/*     */     }
/*  87 */     this.pos = new Vector2(card.current_x, card.current_y);
/*  88 */     this.target = new Vector2(DISCARD_X, DISCARD_Y);
/*  89 */     setSharedVariables();
/*  90 */     this.rotation = card.angle + 270.0F;
/*  91 */     this.rotateClockwise = false;
/*  92 */     if (Settings.FAST_MODE) {
/*  93 */       this.currentSpeed = START_VELOCITY * MathUtils.random(4.0F, 6.0F);
/*     */     } else {
/*  95 */       this.currentSpeed = START_VELOCITY * MathUtils.random(1.0F, 4.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discard(AbstractCard card) {
/* 105 */     discard(card, false);
/*     */   }
/*     */   
/*     */   public void shuffle(AbstractCard card, boolean isInvisible) {
/* 109 */     this.isInvisible = isInvisible;
/* 110 */     this.card = card;
/* 111 */     this.group = AbstractDungeon.player.drawPile;
/* 112 */     this.group.addToTop(card);
/* 113 */     this.pos = new Vector2(DISCARD_X, DISCARD_Y);
/* 114 */     this.target = new Vector2(DRAW_PILE_X, DRAW_PILE_Y);
/* 115 */     setSharedVariables();
/* 116 */     this.rotation = MathUtils.random(260.0F, 310.0F);
/* 117 */     if (Settings.FAST_MODE) {
/* 118 */       this.currentSpeed = START_VELOCITY * MathUtils.random(8.0F, 12.0F);
/*     */     } else {
/* 120 */       this.currentSpeed = START_VELOCITY * MathUtils.random(2.0F, 5.0F);
/*     */     } 
/* 122 */     this.rotateClockwise = true;
/* 123 */     this.spawnStutterTimer = MathUtils.random(0.0F, 0.12F);
/*     */   }
/*     */   
/*     */   public void onToDeck(AbstractCard card, boolean randomSpot, boolean visualOnly) {
/* 127 */     this.card = card;
/* 128 */     this.group = AbstractDungeon.player.drawPile;
/* 129 */     if (!visualOnly) {
/* 130 */       if (randomSpot) {
/* 131 */         this.group.addToRandomSpot(card);
/*     */       } else {
/* 133 */         this.group.addToTop(card);
/*     */       } 
/*     */     }
/* 136 */     this.pos = new Vector2(card.current_x, card.current_y);
/* 137 */     this.target = new Vector2(DRAW_PILE_X, DRAW_PILE_Y);
/* 138 */     setSharedVariables();
/* 139 */     this.rotation = card.angle + 270.0F;
/* 140 */     this.rotateClockwise = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onToDeck(AbstractCard card, boolean randomSpot) {
/* 150 */     onToDeck(card, randomSpot, false);
/*     */   }
/*     */   
/*     */   public void onToBottomOfDeck(AbstractCard card) {
/* 154 */     this.card = card;
/* 155 */     this.group = AbstractDungeon.player.drawPile;
/* 156 */     this.group.addToBottom(card);
/* 157 */     this.pos = new Vector2(card.current_x, card.current_y);
/* 158 */     this.target = new Vector2(DRAW_PILE_X, DRAW_PILE_Y);
/* 159 */     setSharedVariables();
/* 160 */     this.rotation = card.angle + 270.0F;
/* 161 */     this.rotateClockwise = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void empower(AbstractCard card) {
/* 168 */     CardCrawlGame.sound.play("CARD_POWER_WOOSH", 0.1F);
/* 169 */     this.card = card;
/* 170 */     this.group = null;
/* 171 */     this.pos = new Vector2(card.current_x, card.current_y);
/* 172 */     this.target = new Vector2(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY);
/* 173 */     setSharedVariables();
/*     */   }
/*     */   
/*     */   public void obtain(AbstractCard card) {
/* 177 */     this.card = card;
/* 178 */     this.group = AbstractDungeon.player.masterDeck;
/* 179 */     this.group.addToTop(card);
/* 180 */     if (ModHelper.isModEnabled("Hoarder")) {
/* 181 */       this.group.addToTop(card.makeStatEquivalentCopy());
/* 182 */       this.group.addToTop(card.makeStatEquivalentCopy());
/*     */     } 
/* 184 */     this.pos = new Vector2(card.current_x, card.current_y);
/* 185 */     this.target = new Vector2(MASTER_DECK_X, MASTER_DECK_Y);
/* 186 */     setSharedVariables();
/*     */   }
/*     */   
/*     */   private void setSharedVariables() {
/* 190 */     this.controlPoints.clear();
/* 191 */     if (Settings.FAST_MODE) {
/* 192 */       this.rotationRate = ROTATION_RATE * MathUtils.random(4.0F, 6.0F);
/* 193 */       this.currentSpeed = START_VELOCITY * MathUtils.random(1.0F, 1.5F);
/* 194 */       this.backUpTimer = 0.5F;
/*     */     } else {
/* 196 */       this.rotationRate = ROTATION_RATE * MathUtils.random(1.0F, 2.0F);
/* 197 */       this.currentSpeed = START_VELOCITY * MathUtils.random(0.2F, 1.0F);
/* 198 */       this.backUpTimer = 1.5F;
/*     */     } 
/*     */     
/* 201 */     this.stopRotating = false;
/* 202 */     this.rotateClockwise = MathUtils.randomBoolean();
/* 203 */     this.rotation = MathUtils.random(0, 359);
/* 204 */     this.isReadyForReuse = false;
/* 205 */     this.isDone = false;
/*     */   }
/*     */   
/*     */   public void update() {
/* 209 */     if (isCarryingCard()) {
/* 210 */       this.card.update();
/* 211 */       this.card.targetAngle = this.rotation + 90.0F;
/* 212 */       this.card.current_x = this.pos.x;
/* 213 */       this.card.current_y = this.pos.y;
/* 214 */       this.card.target_x = this.card.current_x;
/* 215 */       this.card.target_y = this.card.current_y;
/*     */       
/* 217 */       if (this.spawnStutterTimer > 0.0F) {
/* 218 */         this.spawnStutterTimer -= Gdx.graphics.getDeltaTime();
/*     */         
/*     */         return;
/*     */       } 
/* 222 */       updateMovement();
/* 223 */       updateBackUpTimer();
/*     */     } else {
/* 225 */       this.isDone = true;
/*     */     } 
/*     */     
/* 228 */     if (this.isDone) {
/*     */       
/* 230 */       if (this.group == null) {
/* 231 */         AbstractDungeon.effectList.add(new EmpowerEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY));
/*     */         
/* 233 */         this.isReadyForReuse = true;
/*     */         
/*     */         return;
/*     */       } 
/* 237 */       switch (this.group.type) {
/*     */         case MASTER_DECK:
/* 239 */           this.card.setAngle(0.0F);
/* 240 */           this.card.targetDrawScale = 0.75F;
/*     */           break;
/*     */         case DRAW_PILE:
/* 243 */           this.card.targetDrawScale = 0.75F;
/* 244 */           this.card.setAngle(0.0F);
/* 245 */           this.card.lighten(false);
/* 246 */           this.card.clearPowers();
/* 247 */           AbstractDungeon.overlayMenu.combatDeckPanel.pop();
/*     */           break;
/*     */         case DISCARD_PILE:
/* 250 */           this.card.targetDrawScale = 0.75F;
/* 251 */           this.card.setAngle(0.0F);
/* 252 */           this.card.lighten(false);
/* 253 */           this.card.clearPowers();
/* 254 */           this.card.teleportToDiscardPile();
/* 255 */           AbstractDungeon.overlayMenu.discardPilePanel.pop();
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 262 */       if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 263 */         AbstractDungeon.player.hand.applyPowers();
/*     */       }
/* 265 */       this.isReadyForReuse = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isCarryingCard() {
/* 270 */     if (this.group == null) {
/* 271 */       return true;
/*     */     }
/*     */     
/* 274 */     switch (this.group.type) {
/*     */       case DRAW_PILE:
/* 276 */         if (AbstractDungeon.player.drawPile.contains(this.card)) {
/* 277 */           return true;
/*     */         }
/* 279 */         return false;
/*     */ 
/*     */       
/*     */       case DISCARD_PILE:
/* 283 */         if (AbstractDungeon.player.discardPile.contains(this.card)) {
/* 284 */           return true;
/*     */         }
/* 286 */         return false;
/*     */     } 
/*     */ 
/*     */     
/* 290 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateMovement() {
/* 297 */     this.pos.x -= this.target.x;
/* 298 */     this.pos.y -= this.target.y;
/* 299 */     this.tmp.nor();
/* 300 */     float targetAngle = this.tmp.angle();
/* 301 */     this.rotationRate += Gdx.graphics.getDeltaTime() * 800.0F;
/*     */ 
/*     */     
/* 304 */     if (!this.stopRotating) {
/* 305 */       if (this.rotateClockwise) {
/* 306 */         this.rotation += Gdx.graphics.getDeltaTime() * this.rotationRate;
/*     */       } else {
/* 308 */         this.rotation -= Gdx.graphics.getDeltaTime() * this.rotationRate;
/* 309 */         if (this.rotation < 0.0F) {
/* 310 */           this.rotation += 360.0F;
/*     */         }
/*     */       } 
/*     */       
/* 314 */       this.rotation %= 360.0F;
/*     */       
/* 316 */       if (!this.stopRotating) {
/* 317 */         if (this.target.dst(this.pos) < HOME_IN_THRESHOLD) {
/* 318 */           this.rotation = targetAngle;
/* 319 */           this.stopRotating = true;
/* 320 */         } else if (Math.abs(this.rotation - targetAngle) < Gdx.graphics.getDeltaTime() * this.rotationRate) {
/* 321 */           this.rotation = targetAngle;
/* 322 */           this.stopRotating = true;
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 328 */     this.tmp.setAngle(this.rotation);
/*     */ 
/*     */     
/* 331 */     this.tmp.x *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
/* 332 */     this.tmp.y *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
/* 333 */     this.pos.sub(this.tmp);
/*     */     
/* 335 */     if (this.stopRotating && this.backUpTimer < 1.3499999F) {
/* 336 */       this.currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 3.0F;
/*     */     } else {
/* 338 */       this.currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 1.5F;
/*     */     } 
/* 340 */     if (this.currentSpeed > MAX_VELOCITY) {
/* 341 */       this.currentSpeed = MAX_VELOCITY;
/*     */     }
/*     */ 
/*     */     
/* 345 */     if (this.target.x < Settings.WIDTH / 2.0F && this.pos.x < 0.0F) {
/* 346 */       this.isDone = true;
/* 347 */     } else if (this.target.x > Settings.WIDTH / 2.0F && this.pos.x > Settings.WIDTH) {
/* 348 */       this.isDone = true;
/*     */     } 
/*     */     
/* 351 */     if (this.target.dst(this.pos) < DST_THRESHOLD) {
/* 352 */       this.isDone = true;
/*     */     }
/*     */     
/* 355 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/* 356 */     if (!this.isDone && this.vfxTimer < 0.0F) {
/* 357 */       this.vfxTimer = 0.015F;
/* 358 */       if (!this.controlPoints.isEmpty()) {
/* 359 */         if (!((Vector2)this.controlPoints.get(0)).equals(this.pos)) {
/* 360 */           this.controlPoints.add(this.pos.cpy());
/*     */         }
/*     */       } else {
/* 363 */         this.controlPoints.add(this.pos.cpy());
/*     */       } 
/*     */       
/* 366 */       if (this.controlPoints.size() > 10) {
/* 367 */         this.controlPoints.remove(0);
/*     */       }
/*     */       
/* 370 */       if (this.controlPoints.size() > 3) {
/* 371 */         Vector2[] vec2Array = new Vector2[0];
/* 372 */         this.crs.set((Vector2[]) this.controlPoints.toArray((Object[])vec2Array), false);
/*     */         
/* 374 */         for (int i = 0; i < 20; i++) {
/* 375 */           if (this.points[i] == null) {
/* 376 */             this.points[i] = new Vector2();
/*     */           }
/* 378 */           Vector2 derp = (Vector2)this.crs.valueAt((Vector2) this.points[i], i / 19.0F);
/* 379 */           CardTrailEffect effect = (CardTrailEffect)trailEffectPool.obtain();
/* 380 */           effect.init(derp.x, derp.y);
/* 381 */           AbstractDungeon.topLevelEffects.add(effect);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateBackUpTimer() {
/* 388 */     this.backUpTimer -= Gdx.graphics.getDeltaTime();
/* 389 */     if (this.backUpTimer < 0.0F) {
/* 390 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 395 */     if (!this.isInvisible) {
/* 396 */       this.card.renderOuterGlow(sb);
/* 397 */       this.card.render(sb);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\Soul.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */