/*    */ package com.megacrit.cardcrawl.blights;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.BlightStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

/*    */
/*    */ public class Hauntings extends AbstractBlight {
/* 13 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("GraspOfShadows"); public static final String ID = "GraspOfShadows";
/* 14 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/*    */   
/*    */   public Hauntings() {
/* 17 */     super("GraspOfShadows", NAME, DESC[0] + '\001' + DESC[1], "hauntings.png", false);
/* 18 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stack() {
/* 23 */     this.counter++;
/* 24 */     updateDescription();
/* 25 */     flash();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 30 */     this.description = DESC[0] + this.counter + DESC[1];
/* 31 */     this.tips.clear();
/* 32 */     this.tips.add(new PowerTip(this.name, this.description));
/* 33 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCreateEnemy(AbstractMonster m) {
/* 38 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new IntangiblePlayerPower((AbstractCreature)m, this.counter), this.counter));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\Hauntings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */