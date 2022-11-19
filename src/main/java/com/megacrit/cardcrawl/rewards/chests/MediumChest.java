/*    */ package com.megacrit.cardcrawl.rewards.chests;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class MediumChest
/*    */   extends AbstractChest {
/*    */   public MediumChest() {
/* 10 */     this.img = ImageMaster.M_CHEST;
/* 11 */     this.openedImg = ImageMaster.M_CHEST_OPEN;
/*    */     
/* 13 */     this.hb = new Hitbox(256.0F * Settings.scale, 270.0F * Settings.scale);
/* 14 */     this.hb.move(CHEST_LOC_X, CHEST_LOC_Y - 90.0F * Settings.scale);
/*    */     
/* 16 */     this.COMMON_CHANCE = 35;
/* 17 */     this.UNCOMMON_CHANCE = 50;
/* 18 */     this.RARE_CHANCE = 15;
/* 19 */     this.GOLD_CHANCE = 35;
/* 20 */     this.GOLD_AMT = 50;
/*    */     
/* 22 */     randomizeReward();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rewards\chests\MediumChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */