/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MillAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class Careless extends AbstractDailyMod {
/* 10 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Careless"); public static final String ID = "Careless";
/* 11 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public Careless() {
/* 14 */     super("Careless", NAME, DESC, "slow_start.png", false);
/*    */   }
/*    */   
/*    */   public static void modAction() {
/* 18 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MillAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 1));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\Careless.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */