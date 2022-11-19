/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class IncreaseMaxHpAction extends AbstractGameAction {
/*    */   private boolean showEffect;
/*    */   
/*    */   public IncreaseMaxHpAction(AbstractMonster m, float increasePercent, boolean showEffect) {
/* 13 */     if (Settings.FAST_MODE) {
/* 14 */       this.startDuration = Settings.ACTION_DUR_XFAST;
/*    */     } else {
/* 16 */       this.startDuration = Settings.ACTION_DUR_FAST;
/*    */     } 
/* 18 */     this.duration = this.startDuration;
/* 19 */     this.showEffect = showEffect;
/* 20 */     this.increasePercent = increasePercent;
/* 21 */     this.target = (AbstractCreature)m;
/*    */   }
/*    */   private float increasePercent;
/*    */   
/*    */   public void update() {
/* 26 */     if (this.duration == this.startDuration) {
/* 27 */       this.target.increaseMaxHp(MathUtils.round(this.target.maxHealth * this.increasePercent), this.showEffect);
/*    */     }
/* 29 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\IncreaseMaxHpAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */