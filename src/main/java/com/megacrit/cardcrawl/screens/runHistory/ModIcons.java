/*    */ package com.megacrit.cardcrawl.screens.runHistory;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.mods.AbstractDailyMod;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.screens.stats.RunData;

import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class ModIcons
/*    */ {
/* 17 */   private static final float DAILY_MOD_ICON_SIZE = 52.0F * Settings.scale;
/* 18 */   private static final float DAILY_MOD_VISIBLE_SIZE = DAILY_MOD_ICON_SIZE * 0.75F;
/*    */   
/*    */   private static final float HOVER_SCALE = 1.5F;
/* 21 */   private ArrayList<AbstractDailyMod> dailyModList = new ArrayList<>();
/* 22 */   private ArrayList<Hitbox> hitboxes = new ArrayList<>();
/*    */   
/*    */   public void setRunData(RunData runData) {
/* 25 */     this.dailyModList.clear();
/* 26 */     this.hitboxes.clear();
/* 27 */     if (runData.daily_mods != null) {
/* 28 */       for (String modId : runData.daily_mods) {
/* 29 */         this.dailyModList.add(ModHelper.getMod(modId));
/* 30 */         this.hitboxes.add(new Hitbox(DAILY_MOD_VISIBLE_SIZE, DAILY_MOD_VISIBLE_SIZE));
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean hasMods() {
/* 36 */     return (this.dailyModList.size() > 0);
/*    */   }
/*    */   
/*    */   public void update() {
/* 40 */     boolean isHovered = false;
/* 41 */     for (int i = 0; i < this.hitboxes.size(); i++) {
/* 42 */       AbstractDailyMod mod = this.dailyModList.get(i);
/* 43 */       Hitbox hbox = this.hitboxes.get(i);
/* 44 */       hbox.update();
/* 45 */       if (hbox.hovered) {
/* 46 */         isHovered = true;
/* 47 */         TipHelper.renderGenericTip(hbox.x + 64.0F * Settings.scale, hbox.y + DAILY_MOD_VISIBLE_SIZE / 2.0F, mod.name, mod.description);
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     if (isHovered) {
/* 55 */       CardCrawlGame.cursor.changeType(GameCursor.CursorType.INSPECT);
/*    */     }
/*    */   }
/*    */   
/*    */   public void renderDailyMods(SpriteBatch sb, float x, float y) {
/* 60 */     float drawX = x;
/* 61 */     float drawY = y - DAILY_MOD_VISIBLE_SIZE;
/*    */     
/* 63 */     for (int i = 0; i < this.dailyModList.size(); i++) {
/* 64 */       AbstractDailyMod mod = this.dailyModList.get(i);
/* 65 */       Hitbox hbox = this.hitboxes.get(i);
/*    */       
/* 67 */       float halfSize = DAILY_MOD_ICON_SIZE / 2.0F;
/* 68 */       float cx = drawX + halfSize;
/* 69 */       float cy = drawY + halfSize;
/* 70 */       hbox.move(cx, cy);
/* 71 */       hbox.render(sb);
/*    */       
/* 73 */       if (mod != null && mod.img != null) {
/* 74 */         float drawSize = DAILY_MOD_ICON_SIZE;
/* 75 */         float offset = 0.0F;
/*    */         
/* 77 */         if (hbox.hovered) {
/* 78 */           offset = drawSize * 0.25F;
/* 79 */           drawSize *= 1.5F;
/*    */         } 
/* 81 */         sb.draw(mod.img, hbox.x - offset, hbox.y - offset, drawSize, drawSize);
/*    */       } 
/* 83 */       drawX += DAILY_MOD_VISIBLE_SIZE;
/*    */     } 
/*    */   }
/*    */   
/*    */   public float approximateWidth() {
/* 88 */     return this.dailyModList.size() * DAILY_MOD_VISIBLE_SIZE;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\runHistory\ModIcons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */