/*    */ package com.megacrit.cardcrawl.rewards.chests;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class LargeChest
/*    */   extends AbstractChest {
/*    */   public LargeChest() {
/* 10 */     this.img = ImageMaster.L_CHEST;
/* 11 */     this.openedImg = ImageMaster.L_CHEST_OPEN;
/*    */     
/* 13 */     this.hb = new Hitbox(340.0F * Settings.scale, 200.0F * Settings.scale);
/* 14 */     this.hb.move(CHEST_LOC_X, CHEST_LOC_Y - 120.0F * Settings.scale);
/*    */     
/* 16 */     this.COMMON_CHANCE = 0;
/* 17 */     this.UNCOMMON_CHANCE = 75;
/* 18 */     this.RARE_CHANCE = 25;
/* 19 */     this.GOLD_CHANCE = 50;
/* 20 */     this.GOLD_AMT = 75;
/*    */     
/* 22 */     randomizeReward();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rewards\chests\LargeChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */