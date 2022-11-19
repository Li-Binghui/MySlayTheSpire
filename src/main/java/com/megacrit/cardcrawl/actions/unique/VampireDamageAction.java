/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.HealAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class VampireDamageAction
/*    */   extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   
/*    */   public VampireDamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect) {
/* 16 */     this.info = info;
/* 17 */     setValues(target, info);
/* 18 */     this.actionType = ActionType.DAMAGE;
/* 19 */     this.attackEffect = effect;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == 0.5F) {
/* 25 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/*    */     }
/*    */     
/* 28 */     tickDuration();
/*    */     
/* 30 */     if (this.isDone) {
/* 31 */       this.target.damage(this.info);
/* 32 */       if (this.target.lastDamageTaken > 0) {
/* 33 */         addToTop((AbstractGameAction)new HealAction(this.source, this.source, this.target.lastDamageTaken));
/* 34 */         addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */       } 
/*    */       
/* 37 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
/* 38 */         AbstractDungeon.actionManager.clearPostCombatActions(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\VampireDamageAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */