/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class SwordBoomerangAction extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.01F;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.2F;
/*    */   private int numTimes;
/*    */   
/*    */   public SwordBoomerangAction(AbstractCreature target, DamageInfo info, int numTimes) {
/* 17 */     this.info = info;
/* 18 */     this.target = target;
/* 19 */     this.actionType = ActionType.DAMAGE;
/* 20 */     this.attackEffect = AttackEffect.SLASH_HORIZONTAL;
/* 21 */     this.duration = 0.01F;
/* 22 */     this.numTimes = numTimes;
/*    */   }
/*    */   
/*    */   public SwordBoomerangAction(DamageInfo info, int numTimes) {
/* 26 */     this.info = info;
/* 27 */     this.actionType = ActionType.DAMAGE;
/* 28 */     this.attackEffect = AttackEffect.SLASH_HORIZONTAL;
/* 29 */     this.duration = 0.01F;
/*    */     
/* 31 */     this.numTimes = numTimes;
/* 32 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 33 */       addToTop(new SwordBoomerangAction(
/*    */             
/* 35 */             (AbstractCreature)AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng), info, numTimes));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 43 */     if (this.target == null) {
/* 44 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 48 */     if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 49 */       AbstractDungeon.actionManager.clearPostCombatActions();
/* 50 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 54 */     if (this.target.currentHealth > 0) {
/* 55 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/* 56 */       this.info.applyPowers(this.info.owner, this.target);
/* 57 */       this.target.damage(this.info);
/*    */ 
/*    */       
/* 60 */       if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 61 */         this.numTimes--;
/* 62 */         addToTop(new SwordBoomerangAction(
/*    */               
/* 64 */               (AbstractCreature)AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng), this.info, this.numTimes));
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 69 */       addToTop((AbstractGameAction)new WaitAction(0.2F));
/*    */     } else {
/* 71 */       addToTop(new SwordBoomerangAction(
/*    */             
/* 73 */             (AbstractCreature)AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng), this.info, this.numTimes));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 78 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\SwordBoomerangAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */