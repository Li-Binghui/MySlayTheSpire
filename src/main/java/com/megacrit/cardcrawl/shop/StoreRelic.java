/*     */ package com.megacrit.cardcrawl.shop;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StoreRelic
/*     */ {
/*     */   public AbstractRelic relic;
/*     */   private ShopScreen shopScreen;
/*     */   public int price;
/*     */   private int slot;
/*     */   public boolean isPurchased = false;
/*  30 */   private static final float RELIC_GOLD_OFFSET_X = -56.0F * Settings.scale;
/*  31 */   private static final float RELIC_GOLD_OFFSET_Y = -100.0F * Settings.scale;
/*  32 */   private static final float RELIC_PRICE_OFFSET_X = 14.0F * Settings.scale;
/*  33 */   private static final float RELIC_PRICE_OFFSET_Y = -62.0F * Settings.scale;
/*  34 */   private static final float GOLD_IMG_WIDTH = ImageMaster.UI_GOLD.getWidth() * Settings.scale;
/*     */   
/*     */   public StoreRelic(AbstractRelic relic, int slot, ShopScreen screenRef) {
/*  37 */     this.relic = relic;
/*  38 */     this.price = relic.getPrice();
/*  39 */     this.slot = slot;
/*  40 */     this.shopScreen = screenRef;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(float rugY) {
/*  49 */     if (this.relic != null) {
/*  50 */       if (!this.isPurchased) {
/*     */         
/*  52 */         this.relic.currentX = 1000.0F * Settings.xScale + 150.0F * this.slot * Settings.xScale;
/*  53 */         this.relic.currentY = rugY + 400.0F * Settings.yScale;
/*  54 */         this.relic.hb.move(this.relic.currentX, this.relic.currentY);
/*     */ 
/*     */         
/*  57 */         this.relic.hb.update();
/*  58 */         if (this.relic.hb.hovered) {
/*  59 */           this.shopScreen.moveHand(this.relic.currentX - 190.0F * Settings.xScale, this.relic.currentY - 70.0F * Settings.yScale);
/*  60 */           if (InputHelper.justClickedLeft) {
/*  61 */             this.relic.hb.clickStarted = true;
/*     */           }
/*  63 */           this.relic.scale = Settings.scale * 1.25F;
/*     */         } else {
/*  65 */           this.relic.scale = MathHelper.scaleLerpSnap(this.relic.scale, Settings.scale);
/*     */         } 
/*     */         
/*  68 */         if (this.relic.hb.hovered && InputHelper.justClickedRight)
/*     */         {
/*  70 */           CardCrawlGame.relicPopup.open(this.relic);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  75 */       if (this.relic.hb.clicked || (this.relic.hb.hovered && CInputActionSet.select.isJustPressed())) {
/*  76 */         this.relic.hb.clicked = false;
/*  77 */         if (!Settings.isTouchScreen) {
/*  78 */           purchaseRelic();
/*  79 */         } else if (AbstractDungeon.shopScreen.touchRelic == null) {
/*  80 */           if (AbstractDungeon.player.gold < this.price) {
/*  81 */             this.shopScreen.playCantBuySfx();
/*  82 */             this.shopScreen.createSpeech(ShopScreen.getCantBuyMsg());
/*     */           } else {
/*  84 */             AbstractDungeon.shopScreen.confirmButton.hideInstantly();
/*  85 */             AbstractDungeon.shopScreen.confirmButton.show();
/*  86 */             AbstractDungeon.shopScreen.confirmButton.isDisabled = false;
/*  87 */             AbstractDungeon.shopScreen.confirmButton.hb.clickStarted = false;
/*  88 */             AbstractDungeon.shopScreen.touchRelic = this;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void purchaseRelic() {
/*  96 */     if (AbstractDungeon.player.gold >= this.price) {
/*  97 */       AbstractDungeon.player.loseGold(this.price);
/*  98 */       CardCrawlGame.sound.play("SHOP_PURCHASE", 0.1F);
/*  99 */       CardCrawlGame.metricData.addShopPurchaseData(this.relic.relicId);
/* 100 */       (AbstractDungeon.getCurrRoom()).relics.add(this.relic);
/* 101 */       this.relic.instantObtain(AbstractDungeon.player, AbstractDungeon.player.relics.size(), true);
/* 102 */       this.relic.flash();
/*     */       
/* 104 */       if (this.relic.relicId.equals("Membership Card")) {
/* 105 */         this.shopScreen.applyDiscount(0.5F, true);
/*     */       }
/* 107 */       if (this.relic.relicId.equals("Smiling Mask")) {
/* 108 */         ShopScreen.actualPurgeCost = 50;
/*     */       }
/*     */       
/* 111 */       for (AbstractCard c : this.shopScreen.coloredCards) {
/* 112 */         this.relic.onPreviewObtainCard(c);
/*     */       }
/* 114 */       for (AbstractCard c : this.shopScreen.colorlessCards) {
/* 115 */         this.relic.onPreviewObtainCard(c);
/*     */       }
/*     */       
/* 118 */       this.shopScreen.playBuySfx();
/* 119 */       this.shopScreen.createSpeech(ShopScreen.getBuyMsg());
/*     */ 
/*     */       
/* 122 */       if (this.relic.relicId.equals("The Courier") || AbstractDungeon.player.hasRelic("The Courier")) {
/* 123 */         AbstractRelic tempRelic = AbstractDungeon.returnRandomRelicEnd(ShopScreen.rollRelicTier());
/*     */         
/* 125 */         while (tempRelic instanceof com.megacrit.cardcrawl.relics.OldCoin || tempRelic instanceof com.megacrit.cardcrawl.relics.SmilingMask || tempRelic instanceof com.megacrit.cardcrawl.relics.MawBank || tempRelic instanceof com.megacrit.cardcrawl.relics.Courier)
/*     */         {
/* 127 */           tempRelic = AbstractDungeon.returnRandomRelicEnd(ShopScreen.rollRelicTier());
/*     */         }
/* 129 */         this.relic = tempRelic;
/* 130 */         this.price = this.relic.getPrice();
/* 131 */         this.shopScreen.getNewPrice(this);
/*     */       } else {
/* 133 */         this.isPurchased = true;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 138 */       this.shopScreen.playCantBuySfx();
/* 139 */       this.shopScreen.createSpeech(ShopScreen.getCantBuyMsg());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void hide() {
/* 144 */     if (this.relic != null) {
/* 145 */       this.relic.currentY = Settings.HEIGHT + 200.0F * Settings.scale;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 155 */     if (this.relic != null) {
/* 156 */       this.relic.renderWithoutAmount(sb, new Color(0.0F, 0.0F, 0.0F, 0.25F));
/*     */ 
/*     */       
/* 159 */       sb.setColor(Color.WHITE);
/* 160 */       sb.draw(ImageMaster.UI_GOLD, this.relic.currentX + RELIC_GOLD_OFFSET_X, this.relic.currentY + RELIC_GOLD_OFFSET_Y, GOLD_IMG_WIDTH, GOLD_IMG_WIDTH);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       Color color = Color.WHITE;
/* 168 */       if (this.price > AbstractDungeon.player.gold) {
/* 169 */         color = Color.SALMON;
/*     */       }
/* 171 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont, 
/*     */ 
/*     */           
/* 174 */           Integer.toString(this.price), this.relic.currentX + RELIC_PRICE_OFFSET_X, this.relic.currentY + RELIC_PRICE_OFFSET_Y, color);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\shop\StoreRelic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */