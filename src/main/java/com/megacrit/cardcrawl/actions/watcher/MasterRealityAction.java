/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.defect.LightningOrbEvokeAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class MasterRealityAction
/*    */   extends AbstractGameAction {
/*    */   public MasterRealityAction(int damageAmount) {
/* 13 */     this.amount = damageAmount;
/* 14 */     this.actionType = ActionType.DAMAGE;
/* 15 */     this.attackEffect = AttackEffect.NONE;
/* 16 */     this.duration = 0.01F;
/*    */   }
/*    */   
/*    */   private static final float DURATION = 0.01F;
/*    */   
/*    */   public void update() {
/* 22 */     if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 23 */       AbstractDungeon.actionManager.clearPostCombatActions();
/* 24 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 28 */     int count = 0;
/*    */     
/* 30 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 31 */       if (c.selfRetain || c.retain) {
/* 32 */         count++;
/*    */       }
/*    */     } 
/*    */     
/* 36 */     for (int i = 0; i < count; i++) {
/* 37 */       addToTop((AbstractGameAction)new LightningOrbEvokeAction(new DamageInfo((AbstractCreature)AbstractDungeon.player, this.amount, DamageInfo.DamageType.THORNS), false));
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\MasterRealityAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */