/*    */ package com.megacrit.cardcrawl.actions;
/*    */ import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public abstract class AbstractGameAction {
/*    */   protected static final float DEFAULT_DURATION = 0.5F;
/*    */   protected float duration;
/*    */   protected float startDuration;
/*    */   public ActionType actionType;
/*    */   public DamageInfo.DamageType damageType;
/*    */   public boolean isDone = false;
/*    */   public int amount;
/* 14 */   public AttackEffect attackEffect = AttackEffect.NONE;
/*    */   
/*    */   public AbstractCreature target;
/*    */   public AbstractCreature source;
/*    */   
/*    */   public enum AttackEffect
/*    */   {
/* 21 */     BLUNT_LIGHT, BLUNT_HEAVY, SLASH_DIAGONAL, SMASH, SLASH_HEAVY, SLASH_HORIZONTAL, SLASH_VERTICAL, NONE, FIRE, POISON, SHIELD, LIGHTNING;
/*    */   }
/*    */   
/*    */   public enum ActionType {
/* 25 */     BLOCK, POWER, CARD_MANIPULATION, DAMAGE, DEBUFF, DISCARD, DRAW, EXHAUST, HEAL, ENERGY, TEXT, USE, CLEAR_CARD_QUEUE, DIALOG, SPECIAL, WAIT, SHUFFLE, REDUCE_POWER;
/*    */   }
/*    */   
/*    */   protected void setValues(AbstractCreature target, DamageInfo info) {
/* 29 */     this.target = target;
/* 30 */     this.source = info.owner;
/* 31 */     this.amount = info.output;
/* 32 */     this.duration = 0.5F;
/*    */   }
/*    */   
/*    */   protected void setValues(AbstractCreature target, AbstractCreature source, int amount) {
/* 36 */     this.target = target;
/* 37 */     this.source = source;
/* 38 */     this.amount = amount;
/* 39 */     this.duration = 0.5F;
/*    */   }
/*    */   
/*    */   protected void setValues(AbstractCreature target, AbstractCreature source) {
/* 43 */     this.target = target;
/* 44 */     this.source = source;
/* 45 */     this.amount = 0;
/* 46 */     this.duration = 0.5F;
/*    */   }
/*    */   
/*    */   protected boolean isDeadOrEscaped(AbstractCreature target) {
/* 50 */     if (target.isDying || target.halfDead) {
/* 51 */       return true;
/*    */     }
/* 53 */     if (!target.isPlayer) {
/* 54 */       AbstractMonster m = (AbstractMonster)target;
/* 55 */       if (m.isEscaping) {
/* 56 */         return true;
/*    */       }
/*    */     } 
/* 59 */     return false;
/*    */   }
/*    */   
/*    */   protected void addToBot(AbstractGameAction action) {
/* 63 */     AbstractDungeon.actionManager.addToBottom(action);
/*    */   }
/*    */   
/*    */   protected void addToTop(AbstractGameAction action) {
/* 67 */     AbstractDungeon.actionManager.addToTop(action);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void update();
/*    */ 
/*    */ 
/*    */   
/*    */   protected void tickDuration() {
/* 78 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 79 */     if (this.duration < 0.0F) {
/* 80 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean shouldCancelAction() {
/* 88 */     return (this.target == null || (this.source != null && this.source.isDying) || this.target.isDeadOrEscaped());
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\AbstractGameAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */