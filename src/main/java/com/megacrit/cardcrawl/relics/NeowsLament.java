/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class NeowsLament extends AbstractRelic {
/*    */   public NeowsLament() {
/* 12 */     super("NeowsBlessing", "lament.png", RelicTier.SPECIAL, LandingSound.FLAT);
/* 13 */     this.counter = 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   public static final String ID = "NeowsBlessing";
/*    */   
/*    */   public void atBattleStart() {
/* 23 */     if (this.counter > 0) {
/* 24 */       this.counter--;
/* 25 */       if (this.counter == 0) {
/* 26 */         setCounter(-2);
/* 27 */         this.description = this.DESCRIPTIONS[1];
/* 28 */         this.tips.clear();
/* 29 */         this.tips.add(new PowerTip(this.name, this.description));
/* 30 */         initializeTips();
/*    */       } 
/* 32 */       flash();
/* 33 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 34 */         m.currentHealth = 1;
/* 35 */         m.healthBarUpdatedEvent();
/*    */       } 
/* 37 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCounter(int setCounter) {
/* 44 */     this.counter = setCounter;
/* 45 */     if (setCounter <= 0) {
/* 46 */       usedUp();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 52 */     return new NeowsLament();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\NeowsLament.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */