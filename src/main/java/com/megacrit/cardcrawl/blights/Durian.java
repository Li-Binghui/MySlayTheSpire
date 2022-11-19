/*    */ package com.megacrit.cardcrawl.blights;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.BlightStrings;
/*    */ 
/*    */ public class Durian extends AbstractBlight {
/*    */   public static final String ID = "BlightedDurian";
/*  9 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("BlightedDurian");
/* 10 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/*    */   
/*    */   public Durian() {
/* 13 */     super("BlightedDurian", NAME, DESC[0] + '2' + DESC[1], "durian.png", false);
/* 14 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stack() {
/* 19 */     this.counter++;
/* 20 */     AbstractDungeon.player.decreaseMaxHealth(AbstractDungeon.player.maxHealth / 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 25 */     AbstractDungeon.player.decreaseMaxHealth(AbstractDungeon.player.maxHealth / 2);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\Durian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */