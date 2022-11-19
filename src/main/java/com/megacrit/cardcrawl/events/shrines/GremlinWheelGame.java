/*     */ package com.megacrit.cardcrawl.events.shrines;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.cards.curses.Decay;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.events.GenericEventDialog;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import java.util.Objects;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GremlinWheelGame
/*     */   extends AbstractImageEvent
/*     */ {
/*  41 */   private static final Logger logger = LogManager.getLogger(GremlinWheelGame.class.getName());
/*     */   public static final String ID = "Wheel of Change";
/*  43 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Wheel of Change");
/*  44 */   public static final String NAME = eventStrings.NAME;
/*  45 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  46 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  48 */   public static final String INTRO_DIALOG = DESCRIPTIONS[0];
/*     */ 
/*     */   
/*  51 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*     */   private int result;
/*     */   private float resultAngle;
/*     */   private float tmpAngle;
/*  55 */   private float bounceTimer = 1.0F, animTimer = 3.0F; private boolean startSpin = false; private boolean finishSpin = false; private boolean doneSpinning = false; private boolean bounceIn = true;
/*  56 */   private float spinVelocity = 200.0F; private int goldAmount;
/*     */   private boolean purgeResult = false;
/*     */   private boolean buttonPressed = false;
/*  59 */   private Hitbox buttonHb = new Hitbox(450.0F * Settings.scale, 300.0F * Settings.scale);
/*     */   private Texture wheelImg;
/*     */   private Texture arrowImg;
/*     */   private Texture buttonImg;
/*  63 */   private static final float START_Y = Settings.OPTION_Y + 1000.0F * Settings.scale; private static final float TARGET_Y = Settings.OPTION_Y;
/*  64 */   private float imgX = Settings.WIDTH / 2.0F; private float imgY = START_Y; private float wheelAngle = 0.0F;
/*     */   private static final int WHEEL_W = 1024;
/*     */   private static final int ARROW_W = 512;
/*  67 */   private static final float ARROW_OFFSET_X = 300.0F * Settings.scale;
/*  68 */   private Color color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   
/*  70 */   private float hpLossPercent = 0.1F;
/*     */ 
/*     */   
/*     */   private static final float A_2_HP_LOSS = 0.15F;
/*     */ 
/*     */   
/*     */   public GremlinWheelGame() {
/*  77 */     super(NAME, INTRO_DIALOG, "images/events/spinTheWheel.jpg");
/*  78 */     this.wheelImg = ImageMaster.loadImage("images/events/wheel.png");
/*  79 */     this.arrowImg = ImageMaster.loadImage("images/events/wheelArrow.png");
/*  80 */     this.buttonImg = ImageMaster.loadImage("images/events/spinButton.png");
/*  81 */     this.noCardsInRewards = true;
/*     */     
/*  83 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  84 */       this.hpLossPercent = 0.15F;
/*     */     }
/*     */     
/*  87 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*  88 */     setGold();
/*     */     
/*  90 */     this.hasDialog = true;
/*  91 */     this.hasFocus = true;
/*     */     
/*  93 */     this.buttonHb.move(500.0F * Settings.scale, -500.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   private void setGold() {
/*  97 */     if (Objects.equals(AbstractDungeon.id, "Exordium")) {
/*  98 */       this.goldAmount = 100;
/*  99 */     } else if (Objects.equals(AbstractDungeon.id, "TheCity")) {
/* 100 */       this.goldAmount = 200;
/* 101 */     } else if (Objects.equals(AbstractDungeon.id, "TheBeyond")) {
/* 102 */       this.goldAmount = 300;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private enum CUR_SCREEN
/*     */   {
/* 110 */     INTRO, LEAVE, SPIN, COMPLETE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 115 */     super.update();
/* 116 */     updatePosition();
/* 117 */     purgeLogic();
/* 118 */     if (this.bounceTimer == 0.0F && this.startSpin) {
/* 119 */       if (!this.buttonPressed) {
/* 120 */         this.buttonHb.cY = MathHelper.cardLerpSnap(this.buttonHb.cY, Settings.OPTION_Y - 330.0F * Settings.scale);
/* 121 */         this.buttonHb.move(this.buttonHb.cX, this.buttonHb.cY);
/* 122 */         this.buttonHb.update();
/* 123 */         if ((this.buttonHb.hovered && InputHelper.justClickedLeft) || CInputActionSet.proceed.isJustPressed()) {
/* 124 */           this.buttonPressed = true;
/* 125 */           this.buttonHb.hovered = false;
/* 126 */           CardCrawlGame.sound.play("WHEEL");
/*     */         } 
/*     */       } else {
/* 129 */         this.buttonHb.cY = MathHelper.cardLerpSnap(this.buttonHb.cY, -500.0F * Settings.scale);
/*     */       } 
/*     */     }
/*     */     
/* 133 */     if (this.startSpin && this.bounceTimer == 0.0F && this.buttonPressed) {
/* 134 */       this.imgY = TARGET_Y;
/* 135 */       if (this.animTimer > 0.0F) {
/* 136 */         this.animTimer -= Gdx.graphics.getDeltaTime();
/* 137 */         this.wheelAngle += this.spinVelocity * Gdx.graphics.getDeltaTime();
/*     */       } else {
/* 139 */         this.finishSpin = true;
/* 140 */         this.animTimer = 3.0F;
/* 141 */         this.startSpin = false;
/* 142 */         this.tmpAngle = this.resultAngle;
/*     */       } 
/* 144 */     } else if (this.finishSpin) {
/* 145 */       if (this.animTimer > 0.0F) {
/* 146 */         this.animTimer -= Gdx.graphics.getDeltaTime();
/* 147 */         if (this.animTimer < 0.0F) {
/* 148 */           this.animTimer = 1.0F;
/* 149 */           this.finishSpin = false;
/* 150 */           this.doneSpinning = true;
/*     */         } 
/* 152 */         this.wheelAngle = Interpolation.elasticIn.apply(this.tmpAngle, -180.0F, this.animTimer / 3.0F);
/*     */       } 
/* 154 */     } else if (this.doneSpinning) {
/* 155 */       if (this.animTimer > 0.0F) {
/* 156 */         this.animTimer -= Gdx.graphics.getDeltaTime();
/* 157 */         if (this.animTimer <= 0.0F) {
/* 158 */           this.bounceTimer = 1.0F;
/* 159 */           this.bounceIn = false;
/*     */         } 
/* 161 */       } else if (this.bounceTimer == 0.0F) {
/* 162 */         this.doneSpinning = false;
/* 163 */         this.imageEventText.clearAllDialogs();
/* 164 */         preApplyResult();
/* 165 */         GenericEventDialog.show();
/* 166 */         this.screen = CUR_SCREEN.COMPLETE;
/*     */       } 
/*     */     } 
/*     */     
/* 170 */     if (!GenericEventDialog.waitForInput) {
/* 171 */       buttonEffect(GenericEventDialog.getSelectedOption());
/*     */     }
/*     */   }
/*     */   
/*     */   private void updatePosition() {
/* 176 */     if (this.bounceTimer != 0.0F) {
/* 177 */       this.bounceTimer -= Gdx.graphics.getDeltaTime();
/* 178 */       if (this.bounceTimer < 0.0F) {
/* 179 */         this.bounceTimer = 0.0F;
/*     */       }
/* 181 */       if (this.bounceIn && this.startSpin) {
/* 182 */         this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.bounceTimer);
/* 183 */         this.imgY = Interpolation.bounceIn.apply(TARGET_Y, START_Y, this.bounceTimer);
/* 184 */       } else if (this.doneSpinning) {
/* 185 */         this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.bounceTimer);
/* 186 */         this.imgY = Interpolation.swingOut.apply(START_Y, TARGET_Y, this.bounceTimer);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void preApplyResult() {
/* 197 */     switch (this.result) {
/*     */       case 0:
/* 199 */         this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/* 200 */         this.imageEventText.setDialogOption(OPTIONS[1]);
/* 201 */         AbstractDungeon.effectList.add(new RainingGoldEffect(this.goldAmount));
/* 202 */         AbstractDungeon.player.gainGold(this.goldAmount);
/*     */         return;
/*     */       case 1:
/* 205 */         this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
/* 206 */         this.imageEventText.setDialogOption(OPTIONS[2]);
/*     */         return;
/*     */       case 2:
/* 209 */         this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
/* 210 */         this.imageEventText.setDialogOption(OPTIONS[3]);
/*     */         return;
/*     */       case 3:
/* 213 */         this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
/* 214 */         this.imageEventText.setDialogOption(OPTIONS[4]);
/*     */         return;
/*     */       case 4:
/* 217 */         this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
/* 218 */         this.imageEventText.setDialogOption(OPTIONS[5]);
/*     */         return;
/*     */     } 
/* 221 */     this.imageEventText.updateBodyText(DESCRIPTIONS[6]);
/* 222 */     this.imageEventText.setDialogOption(OPTIONS[6] + (int)(AbstractDungeon.player.maxHealth * this.hpLossPercent) + OPTIONS[7]);
/*     */     
/* 224 */     this.color = new Color(0.5F, 0.5F, 0.5F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/* 236 */     switch (this.screen) {
/*     */       case INTRO:
/* 238 */         if (buttonPressed == 0) {
/* 239 */           GenericEventDialog.hide();
/* 240 */           this.result = AbstractDungeon.miscRng.random(0, 5);
/* 241 */           this.resultAngle = this.result * 60.0F + MathUtils.random(-10.0F, 10.0F);
/* 242 */           this.wheelAngle = 0.0F;
/* 243 */           this.startSpin = true;
/* 244 */           this.bounceTimer = 2.0F;
/* 245 */           this.animTimer = 2.0F;
/* 246 */           this.spinVelocity = 1500.0F;
/*     */         } 
/*     */         return;
/*     */       case COMPLETE:
/* 250 */         applyResult();
/* 251 */         this.imageEventText.clearAllDialogs();
/* 252 */         this.imageEventText.setDialogOption(OPTIONS[8]);
/* 253 */         this.screen = CUR_SCREEN.LEAVE;
/*     */         return;
/*     */       case LEAVE:
/* 256 */         openMap();
/*     */         return;
/*     */     } 
/* 259 */     logger.info("UNHANDLED CASE");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void applyResult() {
/*     */     AbstractRelic r;
/*     */     Decay decay;
/* 268 */     switch (this.result) {
/*     */       
/*     */       case 0:
/* 271 */         this.hasFocus = false;
/* 272 */         logMetricGainGold("Wheel of Change", "Gold", this.goldAmount);
/*     */         return;
/*     */       case 1:
/* 275 */         (AbstractDungeon.getCurrRoom()).rewards.clear();
/* 276 */         r = AbstractDungeon.returnRandomScreenlessRelic(AbstractDungeon.returnRandomRelicTier());
/* 277 */         AbstractDungeon.getCurrRoom().addRelicToRewards(r);
/* 278 */         (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 279 */         AbstractDungeon.combatRewardScreen.open();
/* 280 */         logMetric("Wheel of Change", "Relic");
/* 281 */         this.hasFocus = false;
/*     */         return;
/*     */       case 2:
/* 284 */         logMetricHeal("Wheel of Change", "Full Heal", AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
/* 285 */         AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
/* 286 */         this.hasFocus = false;
/*     */         return;
/*     */       case 3:
/* 289 */         decay = new Decay();
/* 290 */         logMetricObtainCard("Wheel of Change", "Cursed", (AbstractCard)decay);
/* 291 */         AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)decay, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */         
/* 293 */         this.hasFocus = false;
/*     */         return;
/*     */       case 4:
/* 296 */         if (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards())
/* 297 */           .size() > 0) {
/* 298 */           AbstractDungeon.gridSelectScreen.open(
/* 299 */               CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[9], false, false, false, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 306 */           this.roomEventText.hide();
/* 307 */           this.purgeResult = true;
/*     */         } 
/*     */         return;
/*     */     } 
/* 311 */     this.imageEventText.updateBodyText(DESCRIPTIONS[7]);
/* 312 */     CardCrawlGame.sound.play("ATTACK_DAGGER_6");
/* 313 */     CardCrawlGame.sound.play("BLOOD_SPLAT");
/* 314 */     int damageAmount = (int)(AbstractDungeon.player.maxHealth * this.hpLossPercent);
/* 315 */     AbstractDungeon.player.damage(new DamageInfo(null, damageAmount, DamageInfo.DamageType.HP_LOSS));
/* 316 */     logMetricTakeDamage("Wheel of Change", "Damaged", damageAmount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void purgeLogic() {
/* 322 */     if (this.purgeResult && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 323 */       AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
/* 324 */       logMetricCardRemoval("Wheel of Change", "Card Removal", c);
/* 325 */       AbstractDungeon.player.masterDeck.removeCard(c);
/* 326 */       AbstractDungeon.effectList.add(new PurgeCardEffect(c));
/* 327 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 328 */       this.hasFocus = false;
/* 329 */       this.purgeResult = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 335 */     sb.setColor(this.color);
/* 336 */     sb.draw(this.wheelImg, this.imgX - 512.0F, this.imgY - 512.0F, 512.0F, 512.0F, 1024.0F, 1024.0F, Settings.scale, Settings.scale, this.wheelAngle, 0, 0, 1024, 1024, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     sb.draw(this.arrowImg, this.imgX - 256.0F + ARROW_OFFSET_X + 180.0F * Settings.scale, this.imgY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 512, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     if (this.buttonHb.hovered) {
/* 373 */       sb.draw(this.buttonImg, this.buttonHb.cX - 256.0F, this.buttonHb.cY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * 1.05F, Settings.scale * 1.05F, 0.0F, 0, 0, 512, 512, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 391 */       sb.draw(this.buttonImg, this.buttonHb.cX - 256.0F, this.buttonHb.cY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 512, false, false);
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 410 */     sb.setBlendFunction(770, 1);
/* 411 */     if (this.buttonHb.hovered) {
/* 412 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.25F));
/*     */     } else {
/* 414 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, (MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) + 1.25F) / 3.5F));
/*     */     } 
/* 416 */     if (this.buttonHb.hovered) {
/* 417 */       sb.draw(this.buttonImg, this.buttonHb.cX - 256.0F, this.buttonHb.cY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * 1.05F, Settings.scale * 1.05F, 0.0F, 0, 0, 512, 512, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 435 */       sb.draw(this.buttonImg, this.buttonHb.cX - 256.0F, this.buttonHb.cY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 512, false, false);
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 454 */     if (Settings.isControllerMode) {
/* 455 */       sb.draw(CInputActionSet.proceed
/* 456 */           .getKeyImg(), this.buttonHb.cX - 32.0F - 160.0F * Settings.scale, this.buttonHb.cY - 32.0F - 70.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 474 */     sb.setBlendFunction(770, 771);
/* 475 */     this.buttonHb.render(sb);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderAboveTopPanel(SpriteBatch sb) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 485 */     super.dispose();
/* 486 */     if (this.wheelImg != null) {
/* 487 */       this.wheelImg.dispose();
/*     */     }
/* 489 */     if (this.arrowImg != null) {
/* 490 */       this.arrowImg.dispose();
/*     */     }
/* 492 */     if (this.buttonImg != null)
/* 493 */       this.buttonImg.dispose(); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\GremlinWheelGame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */