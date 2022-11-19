/*    */ package com.megacrit.cardcrawl.vfx.campfire;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CampfireTokeEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 25 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CampfireTokeEffect");
/* 26 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   private static final float DUR = 1.5F;
/*    */   private boolean openedScreen = false;
/* 30 */   private Color screenColor = AbstractDungeon.fadeColor.cpy();
/*    */   
/*    */   public CampfireTokeEffect() {
/* 33 */     this.duration = 1.5F;
/* 34 */     this.screenColor.a = 0.0F;
/* 35 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 40 */     if (!AbstractDungeon.isScreenUp) {
/* 41 */       this.duration -= Gdx.graphics.getDeltaTime();
/* 42 */       updateBlackScreenColor();
/*    */     } 
/*    */ 
/*    */     
/* 46 */     if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && AbstractDungeon.gridSelectScreen.forPurge) {
/*    */       
/* 48 */       AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
/* 49 */       CardCrawlGame.metricData.addCampfireChoiceData("PURGE", card.getMetricID());
/* 50 */       CardCrawlGame.sound.play("CARD_EXHAUST");
/* 51 */       AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/* 52 */       AbstractDungeon.player.masterDeck.removeCard(card);
/* 53 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*    */     } 
/*    */ 
/*    */     
/* 57 */     if (this.duration < 1.0F && !this.openedScreen) {
/* 58 */       this.openedScreen = true;
/* 59 */       AbstractDungeon.gridSelectScreen.open(
/* 60 */           CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, TEXT[0], false, false, true, true);
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 70 */     if (this.duration < 0.0F) {
/* 71 */       this.isDone = true;
/* 72 */       if (CampfireUI.hidden) {
/* 73 */         AbstractRoom.waitTimer = 0.0F;
/* 74 */         (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 75 */         ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void updateBlackScreenColor() {
/* 84 */     if (this.duration > 1.0F) {
/* 85 */       this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
/*    */     } else {
/* 87 */       this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 1.5F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 93 */     sb.setColor(this.screenColor);
/* 94 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */     
/* 96 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID)
/* 97 */       AbstractDungeon.gridSelectScreen.render(sb); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\campfire\CampfireTokeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */