/*     */ package com.megacrit.cardcrawl.ui.panels;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.ExhaustPileParticle;
/*     */ 
/*     */ public class ExhaustPanel
/*     */   extends AbstractPanel {
/*  22 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Exhaust Tip");
/*  23 */   public static final String[] MSG = tutorialStrings.TEXT;
/*  24 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*  25 */   public static float fontScale = 1.0F;
/*     */   public static final float FONT_POP_SCALE = 2.0F;
/*  27 */   private static final float COUNT_CIRCLE_W = 128.0F * Settings.scale;
/*  28 */   public static int totalCount = 0;
/*  29 */   private Hitbox hb = new Hitbox(0.0F, 0.0F, 100.0F * Settings.scale, 100.0F * Settings.scale);
/*  30 */   public static float energyVfxTimer = 0.0F;
/*     */   public static final float ENERGY_VFX_TIME = 2.0F;
/*     */   
/*     */   public ExhaustPanel() {
/*  34 */     super(Settings.WIDTH - 70.0F * Settings.scale, 184.0F * Settings.scale, Settings.WIDTH + 100.0F * Settings.scale, 184.0F * Settings.scale, 0.0F, 0.0F, null, false);
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
/*     */   
/*     */   public void updatePositions() {
/*  47 */     super.updatePositions();
/*     */     
/*  49 */     if (!this.isHidden && 
/*  50 */       AbstractDungeon.player.exhaustPile.size() > 0) {
/*  51 */       this.hb.update();
/*  52 */       updateVfx();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  57 */     if (this.hb.hovered && (!AbstractDungeon.isScreenUp || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.EXHAUST_VIEW || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT || (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD && AbstractDungeon.overlayMenu.combatPanelsShown))) {
/*     */ 
/*     */ 
/*     */       
/*  61 */       AbstractDungeon.overlayMenu.hoveredTip = true;
/*  62 */       if (InputHelper.justClickedLeft) {
/*  63 */         this.hb.clickStarted = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  68 */     if ((this.hb.clicked || InputActionSet.exhaustPile.isJustPressed() || CInputActionSet.pageRightViewExhaust
/*  69 */       .isJustPressed()) && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.EXHAUST_VIEW) {
/*  70 */       this.hb.clicked = false;
/*  71 */       this.hb.hovered = false;
/*  72 */       CardCrawlGame.sound.play("DECK_CLOSE");
/*  73 */       AbstractDungeon.closeCurrentScreen();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  78 */     if ((this.hb.clicked || InputActionSet.exhaustPile.isJustPressed() || CInputActionSet.pageRightViewExhaust
/*  79 */       .isJustPressed()) && AbstractDungeon.overlayMenu.combatPanelsShown && AbstractDungeon.getMonsters() != null && 
/*  80 */       !AbstractDungeon.getMonsters().areMonstersDead() && !AbstractDungeon.player.isDead && 
/*  81 */       !AbstractDungeon.player.exhaustPile.isEmpty()) {
/*     */       
/*  83 */       this.hb.clicked = false;
/*  84 */       this.hb.hovered = false;
/*     */       
/*  86 */       if (AbstractDungeon.isScreenUp) {
/*  87 */         if (AbstractDungeon.previousScreen == null) {
/*  88 */           AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*     */         }
/*     */       } else {
/*  91 */         AbstractDungeon.previousScreen = null;
/*     */       } 
/*     */       
/*  94 */       openExhaustPile();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void openExhaustPile() {
/*  99 */     if (AbstractDungeon.player.hoveredCard != null) {
/* 100 */       AbstractDungeon.player.releaseCard();
/*     */     }
/*     */     
/* 103 */     AbstractDungeon.dynamicBanner.hide();
/* 104 */     AbstractDungeon.exhaustPileViewScreen.open();
/* 105 */     this.hb.hovered = false;
/* 106 */     InputHelper.justClickedLeft = false;
/*     */   }
/*     */   
/*     */   private void updateVfx() {
/* 110 */     energyVfxTimer -= Gdx.graphics.getDeltaTime();
/* 111 */     if (energyVfxTimer < 0.0F && !Settings.hideLowerElements) {
/* 112 */       AbstractDungeon.effectList.add(new ExhaustPileParticle(this.current_x, this.current_y));
/* 113 */       energyVfxTimer = 0.05F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 119 */     if (!AbstractDungeon.player.exhaustPile.isEmpty()) {
/* 120 */       this.hb.move(this.current_x, this.current_y);
/*     */ 
/*     */       
/* 123 */       String msg = Integer.toString(AbstractDungeon.player.exhaustPile.size());
/* 124 */       sb.setColor(Settings.TWO_THIRDS_TRANSPARENT_BLACK_COLOR);
/* 125 */       sb.draw(ImageMaster.DECK_COUNT_CIRCLE, this.current_x - COUNT_CIRCLE_W / 2.0F, this.current_y - COUNT_CIRCLE_W / 2.0F, COUNT_CIRCLE_W, COUNT_CIRCLE_W);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 132 */       FontHelper.renderFontCentered(sb, FontHelper.turnNumFont, msg, this.current_x, this.current_y + 2.0F * Settings.scale, Settings.PURPLE_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 140 */       if (Settings.isControllerMode) {
/* 141 */         sb.setColor(Color.WHITE);
/* 142 */         sb.draw(CInputActionSet.pageRightViewExhaust
/* 143 */             .getKeyImg(), this.current_x - 32.0F + 30.0F * Settings.scale, this.current_y - 32.0F - 30.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 64, 64, false, false);
/*     */       } 
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
/* 161 */       this.hb.render(sb);
/* 162 */       if (this.hb.hovered && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.isScreenUp)
/* 163 */         if (Settings.isConsoleBuild) {
/* 164 */           TipHelper.renderGenericTip(1550.0F * Settings.scale, 450.0F * Settings.scale, LABEL[0] + " (" + InputActionSet.exhaustPile
/*     */ 
/*     */               
/* 167 */               .getKeyString() + ")", MSG[1]);
/*     */         } else {
/*     */           
/* 170 */           TipHelper.renderGenericTip(1550.0F * Settings.scale, 450.0F * Settings.scale, LABEL[0] + " (" + InputActionSet.exhaustPile
/*     */ 
/*     */               
/* 173 */               .getKeyString() + ")", MSG[0]);
/*     */         }  
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\ExhaustPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */