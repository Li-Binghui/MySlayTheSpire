/*    */ package com.megacrit.cardcrawl.blights;
/*    */ 
/*    */

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.BlightStrings;
/*    */ 
/*    */ public class VoidEssence extends AbstractBlight {
/*    */   public static final String ID = "VoidEssence";
/* 11 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("VoidEssence");
/* 12 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/*    */   
/*    */   public VoidEssence() {
/* 15 */     super("VoidEssence", NAME, DESC[0] + "1" + DESC[1], "void.png", false);
/* 16 */     this.counter = 1;
/* 17 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stack() {
/* 22 */     this.counter++;
/* 23 */     updateDescription();
/* 24 */     if (AbstractDungeon.player.energy.energyMaster > 0) {
/* 25 */       AbstractDungeon.player.energy.energyMaster--;
/*    */     }
/* 27 */     flash();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 32 */     this.description = DESC[0] + this.counter + DESC[1];
/*    */     
/* 34 */     this.tips.clear();
/* 35 */     this.tips.add(new PowerTip(this.name, this.description));
/* 36 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 41 */     if (AbstractDungeon.player != null) {
/* 42 */       this.description = DESC[0] + this.counter + DESC[1];
/*    */     }
/*    */     
/* 45 */     this.tips.clear();
/* 46 */     this.tips.add(new PowerTip(this.name, this.description));
/* 47 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 52 */     if (AbstractDungeon.player.energy.energyMaster > 0)
/* 53 */       AbstractDungeon.player.energy.energyMaster--; 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\VoidEssence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */