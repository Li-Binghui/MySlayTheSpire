/*    */ package com.megacrit.cardcrawl.screens.mainMenu;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*    */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ import com.megacrit.cardcrawl.screens.compendium.CardLibSortHeader;
/*    */ 
/*    */ public class SortHeaderButton
/*    */ {
/*    */   public Hitbox hb;
/*    */   private boolean isAscending = false;
/*    */   private boolean isActive = false;
/*    */   private String text;
/*    */   public SortHeaderButtonListener delegate;
/* 21 */   private final float ARROW_SIZE = 32.0F;
/*    */   public float textWidth;
/*    */   
/*    */   public SortHeaderButton(String text, float cx, float cy) {
/* 25 */     this.hb = new Hitbox(210.0F * Settings.xScale, 48.0F * Settings.scale);
/* 26 */     this.hb.move(cx, cy);
/* 27 */     this.text = text;
/* 28 */     this.textWidth = FontHelper.getSmartWidth(FontHelper.topPanelInfoFont, text, Float.MAX_VALUE, 0.0F);
/*    */   }
/*    */   
/*    */   public SortHeaderButton(String text, float cx, float cy, SortHeaderButtonListener delegate) {
/* 32 */     this(text, cx, cy);
/* 33 */     this.delegate = delegate;
/*    */   }
/*    */   
/*    */   public void update() {
/* 37 */     this.hb.update();
/* 38 */     if (this.hb.justHovered) {
/* 39 */       CardCrawlGame.sound.playA("UI_HOVER", -0.3F);
/*    */     }
/*    */     
/* 42 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 43 */       this.hb.clickStarted = true;
/*    */     }
/*    */     
/* 46 */     if (this.hb.clicked || (this.hb.hovered && CInputActionSet.select.isJustPressed())) {
/* 47 */       this.hb.clicked = false;
/* 48 */       this.isAscending = !this.isAscending;
/* 49 */       CardCrawlGame.sound.playA("UI_CLICK_1", -0.2F);
/* 50 */       if (this.delegate instanceof CardLibSortHeader) {
/* 51 */         ((CardLibSortHeader)this.delegate).clearActiveButtons();
/*    */       }
/* 53 */       this.delegate.didChangeOrder(this, this.isAscending);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void updateScrollPosition(float newY) {
/* 58 */     this.hb.move(this.hb.cX, newY);
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 62 */     Color color = (this.hb.hovered || this.isActive) ? Settings.GOLD_COLOR : Settings.CREAM_COLOR;
/* 63 */     FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, this.text, this.hb.cX, this.hb.cY, color);
/* 64 */     sb.setColor(color);
/*    */     
/* 66 */     float arrowCenterOffset = 16.0F;
/*    */     
/* 68 */     sb.draw(ImageMaster.FILTER_ARROW, this.hb.cX - 16.0F + this.textWidth / 2.0F + 16.0F * Settings.xScale, this.hb.cY - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 32, 32, false, !this.isAscending);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 86 */     this.hb.render(sb);
/*    */   }
/*    */   
/*    */   public void reset() {
/* 90 */     this.isAscending = false;
/*    */   }
/*    */   
/*    */   public void setActive(boolean isActive) {
/* 94 */     this.isActive = isActive;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\SortHeaderButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */