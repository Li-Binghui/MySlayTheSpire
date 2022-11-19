/*     */ package com.megacrit.cardcrawl.shop;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.potions.AbstractPotion;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StorePotion
/*     */ {
/*  21 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("StorePotion");
/*  22 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public AbstractPotion potion;
/*     */   private ShopScreen shopScreen;
/*     */   public int price;
/*     */   private int slot;
/*     */   public boolean isPurchased = false;
/*  29 */   private static final float RELIC_GOLD_OFFSET_X = -56.0F * Settings.scale;
/*  30 */   private static final float RELIC_GOLD_OFFSET_Y = -100.0F * Settings.scale;
/*  31 */   private static final float RELIC_PRICE_OFFSET_X = 14.0F * Settings.scale;
/*  32 */   private static final float RELIC_PRICE_OFFSET_Y = -62.0F * Settings.scale;
/*  33 */   private static final float GOLD_IMG_WIDTH = ImageMaster.UI_GOLD.getWidth() * Settings.scale;
/*     */   
/*     */   public StorePotion(AbstractPotion potion, int slot, ShopScreen screenRef) {
/*  36 */     this.potion = potion;
/*  37 */     this.price = potion.getPrice();
/*  38 */     this.slot = slot;
/*  39 */     this.shopScreen = screenRef;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(float rugY) {
/*  48 */     if (this.potion != null) {
/*     */       
/*  50 */       if (!this.isPurchased) {
/*  51 */         this.potion.posX = 1000.0F * Settings.xScale + 150.0F * this.slot * Settings.xScale;
/*  52 */         this.potion.posY = rugY + 200.0F * Settings.yScale;
/*  53 */         this.potion.hb.move(this.potion.posX, this.potion.posY);
/*     */ 
/*     */         
/*  56 */         this.potion.hb.update();
/*  57 */         if (this.potion.hb.hovered) {
/*  58 */           this.shopScreen.moveHand(this.potion.posX - 190.0F * Settings.scale, this.potion.posY - 70.0F * Settings.scale);
/*  59 */           if (InputHelper.justClickedLeft) {
/*  60 */             this.potion.hb.clickStarted = true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  66 */       if (this.potion.hb.clicked || (this.potion.hb.hovered && CInputActionSet.select.isJustPressed())) {
/*  67 */         this.potion.hb.clicked = false;
/*  68 */         if (!Settings.isTouchScreen) {
/*  69 */           purchasePotion();
/*  70 */         } else if (AbstractDungeon.shopScreen.touchPotion == null) {
/*  71 */           if (AbstractDungeon.player.gold < this.price) {
/*  72 */             this.shopScreen.playCantBuySfx();
/*  73 */             this.shopScreen.createSpeech(ShopScreen.getCantBuyMsg());
/*     */           } else {
/*  75 */             AbstractDungeon.shopScreen.confirmButton.hideInstantly();
/*  76 */             AbstractDungeon.shopScreen.confirmButton.show();
/*  77 */             AbstractDungeon.shopScreen.confirmButton.isDisabled = false;
/*  78 */             AbstractDungeon.shopScreen.confirmButton.hb.clickStarted = false;
/*  79 */             AbstractDungeon.shopScreen.touchPotion = this;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void purchasePotion() {
/*  87 */     if (AbstractDungeon.player.hasRelic("Sozu")) {
/*  88 */       AbstractDungeon.player.getRelic("Sozu").flash();
/*     */       
/*     */       return;
/*     */     } 
/*  92 */     if (AbstractDungeon.player.gold >= this.price) {
/*  93 */       if (AbstractDungeon.player.obtainPotion(this.potion)) {
/*  94 */         AbstractDungeon.player.loseGold(this.price);
/*  95 */         CardCrawlGame.sound.play("SHOP_PURCHASE", 0.1F);
/*  96 */         CardCrawlGame.metricData.addShopPurchaseData(this.potion.ID);
/*  97 */         this.shopScreen.playBuySfx();
/*  98 */         this.shopScreen.createSpeech(ShopScreen.getBuyMsg());
/*     */ 
/*     */         
/* 101 */         if (AbstractDungeon.player.hasRelic("The Courier")) {
/* 102 */           this.potion = AbstractDungeon.returnRandomPotion();
/* 103 */           this.price = this.potion.getPrice();
/* 104 */           this.shopScreen.getNewPrice(this);
/*     */         } else {
/* 106 */           this.isPurchased = true;
/*     */         } 
/*     */         return;
/*     */       } 
/* 110 */       this.shopScreen.createSpeech(TEXT[0]);
/* 111 */       AbstractDungeon.topPanel.flashRed();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 116 */       this.shopScreen.playCantBuySfx();
/* 117 */       this.shopScreen.createSpeech(ShopScreen.getCantBuyMsg());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void hide() {
/* 122 */     if (this.potion != null) {
/* 123 */       this.potion.posY = Settings.HEIGHT + 200.0F * Settings.scale;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 133 */     if (this.potion != null) {
/* 134 */       this.potion.shopRender(sb);
/*     */ 
/*     */       
/* 137 */       sb.setColor(Color.WHITE);
/* 138 */       sb.draw(ImageMaster.UI_GOLD, this.potion.posX + RELIC_GOLD_OFFSET_X, this.potion.posY + RELIC_GOLD_OFFSET_Y, GOLD_IMG_WIDTH, GOLD_IMG_WIDTH);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       Color color = Color.WHITE;
/* 146 */       if (this.price > AbstractDungeon.player.gold) {
/* 147 */         color = Color.SALMON;
/*     */       }
/* 149 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont, 
/*     */ 
/*     */           
/* 152 */           Integer.toString(this.price), this.potion.posX + RELIC_PRICE_OFFSET_X, this.potion.posY + RELIC_PRICE_OFFSET_Y, color);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\shop\StorePotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */