/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardAtBottomOfDeckAction;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class ControlledChaos extends AbstractDailyMod {
/* 10 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("ControlledChaos"); public static final String ID = "ControlledChaos";
/* 11 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public ControlledChaos() {
/* 14 */     super("ControlledChaos", NAME, DESC, "controlled_chaos.png", true);
/*    */   }
/*    */   
/*    */   public static void modAction() {
/* 18 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardAtBottomOfDeckAction(10));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\ControlledChaos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */