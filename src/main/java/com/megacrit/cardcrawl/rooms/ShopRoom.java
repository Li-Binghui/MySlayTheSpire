/*    */ package com.megacrit.cardcrawl.rooms;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.shop.Merchant;
/*    */ import com.megacrit.cardcrawl.shop.ShopScreen;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
/*    */ 
/*    */ public class ShopRoom
/*    */   extends AbstractRoom {
/* 16 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ShopRoom");
/* 17 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/* 19 */   public int shopRarityBonus = 6;
/*    */   public Merchant merchant;
/*    */   
/*    */   public ShopRoom() {
/* 23 */     this.phase = RoomPhase.COMPLETE;
/* 24 */     this.merchant = null;
/* 25 */     this.mapSymbol = "$";
/* 26 */     this.mapImg = ImageMaster.MAP_NODE_MERCHANT;
/* 27 */     this.mapImgOutline = ImageMaster.MAP_NODE_MERCHANT_OUTLINE;
/* 28 */     this.baseRareCardChance = 9;
/* 29 */     this.baseUncommonCardChance = 37;
/*    */   }
/*    */   
/*    */   public void setMerchant(Merchant merc) {
/* 33 */     this.merchant = merc;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayerEntry() {
/* 38 */     if (!AbstractDungeon.id.equals("TheEnding")) {
/* 39 */       playBGM("SHOP");
/*    */     }
/* 41 */     AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[0]);
/* 42 */     setMerchant(new Merchant());
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard.CardRarity getCardRarity(int roll) {
/* 47 */     return getCardRarity(roll, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 55 */     super.update();
/* 56 */     if (this.merchant != null) {
/* 57 */       this.merchant.update();
/*    */     }
/* 59 */     updatePurge();
/*    */   }
/*    */   
/*    */   private void updatePurge() {
/* 63 */     if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 64 */       ShopScreen.purgeCard();
/* 65 */       for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 66 */         CardCrawlGame.metricData.addPurgedItem(card.getMetricID());
/* 67 */         AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */         
/* 69 */         AbstractDungeon.player.masterDeck.removeCard(card);
/*    */       } 
/* 71 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 72 */       AbstractDungeon.shopScreen.purgeAvailable = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 81 */     if (this.merchant != null) {
/* 82 */       this.merchant.render(sb);
/*    */     }
/*    */     
/* 85 */     super.render(sb);
/* 86 */     renderTips(sb);
/*    */   }
/*    */ 
/*    */   
/*    */   public void dispose() {
/* 91 */     super.dispose();
/* 92 */     if (this.merchant != null) {
/* 93 */       this.merchant.dispose();
/* 94 */       this.merchant = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\ShopRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */