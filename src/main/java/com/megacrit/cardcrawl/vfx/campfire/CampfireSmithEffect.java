/*     */ package com.megacrit.cardcrawl.vfx.campfire;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CampfireSmithEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*  27 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CampfireSmithEffect");
/*  28 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static final float DUR = 1.5F;
/*     */   private boolean openedScreen = false;
/*  32 */   private Color screenColor = AbstractDungeon.fadeColor.cpy();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CampfireSmithEffect() {
/*  38 */     this.duration = 1.5F;
/*  39 */     this.screenColor.a = 0.0F;
/*  40 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  45 */     if (!AbstractDungeon.isScreenUp) {
/*  46 */       this.duration -= Gdx.graphics.getDeltaTime();
/*  47 */       updateBlackScreenColor();
/*     */     } 
/*     */ 
/*     */     
/*  51 */     if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && AbstractDungeon.gridSelectScreen.forUpgrade) {
/*     */       
/*  53 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/*  54 */         AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*  55 */         CardCrawlGame.metricData.campfire_upgraded++;
/*  56 */         CardCrawlGame.metricData.addCampfireChoiceData("SMITH", c.getMetricID());
/*  57 */         c.upgrade();
/*  58 */         AbstractDungeon.player.bottledCardUpgradeCheck(c);
/*  59 */         AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy()));
/*     */       } 
/*  61 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*  62 */       ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
/*     */     } 
/*     */ 
/*     */     
/*  66 */     if (this.duration < 1.0F && !this.openedScreen) {
/*  67 */       this.openedScreen = true;
/*     */       
/*  69 */       AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck
/*  70 */           .getUpgradableCards(), 1, TEXT[0], true, false, true, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  78 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  79 */         r.onSmith();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  84 */     if (this.duration < 0.0F) {
/*  85 */       this.isDone = true;
/*  86 */       if (CampfireUI.hidden) {
/*  87 */         AbstractRoom.waitTimer = 0.0F;
/*  88 */         (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*  89 */         ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateBlackScreenColor() {
/*  98 */     if (this.duration > 1.0F) {
/*  99 */       this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
/*     */     } else {
/* 101 */       this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 1.5F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 107 */     sb.setColor(this.screenColor);
/* 108 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     
/* 110 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID)
/* 111 */       AbstractDungeon.gridSelectScreen.render(sb); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\campfire\CampfireSmithEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */