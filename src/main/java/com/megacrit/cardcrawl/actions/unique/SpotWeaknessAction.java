/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.vfx.ThoughtBubble;
/*    */ 
/*    */ public class SpotWeaknessAction extends AbstractGameAction {
/* 13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("OpeningAction");
/* 14 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   private int damageIncrease;
/*    */   private AbstractMonster targetMonster;
/*    */   
/*    */   public SpotWeaknessAction(int damageIncrease, AbstractMonster m) {
/* 20 */     this.duration = 0.0F;
/* 21 */     this.actionType = ActionType.WAIT;
/* 22 */     this.damageIncrease = damageIncrease;
/* 23 */     this.targetMonster = m;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 28 */     if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
/* 29 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, this.damageIncrease), this.damageIncrease));
/*    */ 
/*    */     
/*    */     }
/*    */     else {
/*    */ 
/*    */       
/* 36 */       AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[0], true));
/*    */     } 
/*    */ 
/*    */     
/* 40 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\SpotWeaknessAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */