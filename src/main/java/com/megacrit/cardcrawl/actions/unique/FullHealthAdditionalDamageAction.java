/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class FullHealthAdditionalDamageAction
/*    */   extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private static final int ADDITIONAL_DAMAGE = 6;
/*    */   
/*    */   public FullHealthAdditionalDamageAction(AbstractCreature target, DamageInfo info) {
/* 14 */     this.info = info;
/* 15 */     setValues(target, info);
/* 16 */     this.actionType = ActionType.DAMAGE;
/*    */   }
/*    */ 
/*    */   
/*    */   public FullHealthAdditionalDamageAction(AbstractCreature target, AbstractCreature source, int damage) {
/* 21 */     this(target, source, damage, DamageInfo.DamageType.NORMAL);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FullHealthAdditionalDamageAction(AbstractCreature target, AbstractCreature source, int damage, DamageInfo.DamageType type) {
/* 29 */     this.info = new DamageInfo(source, damage, type);
/* 30 */     setValues(target, this.info);
/* 31 */     this.actionType = ActionType.DAMAGE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 37 */     if (this.duration == 0.5F) {
/* 38 */       if (this.target.currentHealth != this.target.maxHealth) {
/* 39 */         this.target.damage(this.info);
/*    */       } else {
/* 41 */         this.target.damage(new DamageInfo(this.info.owner, this.info.output + 6, this.info.type));
/*    */       } 
/*    */       
/* 44 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 45 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */     } 
/*    */     
/* 49 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\FullHealthAdditionalDamageAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */