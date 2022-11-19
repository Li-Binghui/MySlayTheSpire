/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class BaneAction extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.01F;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.1F;
/*    */   private AbstractMonster m;
/*    */   
/*    */   public BaneAction(AbstractMonster target, DamageInfo info) {
/* 18 */     this.info = info;
/* 19 */     setValues((AbstractCreature)target, info);
/* 20 */     this.m = target;
/* 21 */     this.actionType = ActionType.DAMAGE;
/* 22 */     this.attackEffect = AttackEffect.SLASH_VERTICAL;
/* 23 */     this.duration = 0.01F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 28 */     if (this.target == null) {
/* 29 */       this.isDone = true;
/*    */       return;
/*    */     } 
/* 32 */     if (this.m.hasPower("Poison")) {
/* 33 */       if (this.duration == 0.01F && this.target != null && this.target.currentHealth > 0) {
/*    */         
/* 35 */         if (this.info.type != DamageInfo.DamageType.THORNS && 
/* 36 */           this.info.owner.isDying) {
/* 37 */           this.isDone = true;
/*    */           
/*    */           return;
/*    */         } 
/* 41 */         AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/*    */       } 
/*    */       
/* 44 */       tickDuration();
/*    */       
/* 46 */       if (this.isDone && this.target != null && this.target.currentHealth > 0) {
/* 47 */         this.target.damage(this.info);
/*    */         
/* 49 */         if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 50 */           AbstractDungeon.actionManager.clearPostCombatActions();
/*    */         }
/* 52 */         addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */       } 
/*    */     } else {
/*    */       
/* 56 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\BaneAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */