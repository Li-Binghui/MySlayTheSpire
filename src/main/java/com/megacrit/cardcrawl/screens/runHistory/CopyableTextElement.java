/*    */ package com.megacrit.cardcrawl.screens.runHistory;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Clipboard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ 
/*    */ public class CopyableTextElement
/*    */ {
/* 16 */   private static final float HITBOX_PADDING = 4.0F * Settings.scale;
/*    */   
/* 18 */   private String text = "";
/* 19 */   private String copyText = "";
/*    */   
/*    */   private Hitbox hb;
/*    */   private BitmapFont font;
/*    */   
/*    */   public CopyableTextElement(BitmapFont font) {
/* 25 */     this.font = font;
/*    */ 
/*    */     
/* 28 */     float height = font.getLineHeight() * Settings.scale;
/* 29 */     this.hb = new Hitbox(0.0F, height + 2.0F * HITBOX_PADDING);
/*    */   }
/*    */   
/*    */   public void update() {
/* 33 */     this.hb.update();
/* 34 */     if (this.hb.justHovered) {
/* 35 */       CardCrawlGame.sound.play("UI_HOVER");
/* 36 */     } else if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 37 */       this.hb.clickStarted = true;
/* 38 */       CardCrawlGame.sound.play("UI_CLICK_1");
/* 39 */     } else if (this.hb.clicked) {
/* 40 */       this.hb.clicked = false;
/* 41 */       copySeedToSystem();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void copySeedToSystem() {
/* 46 */     Clipboard clipBoard = Gdx.app.getClipboard();
/* 47 */     clipBoard.setContents(this.copyText);
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb, float x, float y) {
/* 51 */     Color textColor = this.hb.hovered ? Settings.GREEN_TEXT_COLOR : Settings.GOLD_COLOR;
/* 52 */     renderSmallText(sb, getText(), x, y, textColor);
/* 53 */     this.hb.translate(x - HITBOX_PADDING, y - this.hb.height + HITBOX_PADDING);
/* 54 */     this.hb.render(sb);
/*    */   }
/*    */   
/*    */   public float approximateWidth() {
/* 58 */     return FontHelper.getSmartWidth(this.font, getText(), Float.MAX_VALUE, 36.0F * Settings.scale);
/*    */   }
/*    */   
/*    */   private void renderSmallText(SpriteBatch sb, String text, float x, float y, Color color) {
/* 62 */     FontHelper.renderSmartText(sb, this.font, text, x, y, Float.MAX_VALUE, 36.0F * Settings.scale, color);
/*    */   }
/*    */   
/*    */   public String getText() {
/* 66 */     return this.text;
/*    */   }
/*    */   
/*    */   public void setText(String text) {
/* 70 */     this.text = text;
/* 71 */     this.hb.width = approximateWidth() + 2.0F * HITBOX_PADDING;
/*    */   }
/*    */   
/*    */   public void setText(String text, String copyText) {
/* 75 */     setText(text);
/* 76 */     this.copyText = copyText;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\runHistory\CopyableTextElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */