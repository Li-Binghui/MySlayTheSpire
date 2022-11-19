/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.RipAndTearEffect;
/*    */ 
/*    */ public class RipAndTearAction extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private int numTimes;
/*    */   
/*    */   public RipAndTearAction(AbstractCreature target, DamageInfo info, int numTimes) {
/* 18 */     this.info = info;
/* 19 */     this.target = target;
/* 20 */     this.actionType = ActionType.DAMAGE;
/* 21 */     if (Settings.FAST_MODE) {
/* 22 */       this.startDuration = 0.05F;
/*    */     } else {
/* 24 */       this.startDuration = 0.2F;
/*    */     } 
/* 26 */     this.duration = this.startDuration;
/* 27 */     this.numTimes = numTimes;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 32 */     if (this.target == null) {
/* 33 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 37 */     if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 38 */       AbstractDungeon.actionManager.clearPostCombatActions();
/* 39 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 43 */     if (this.duration == this.startDuration) {
/* 44 */       AbstractDungeon.effectsQueue.add(new RipAndTearEffect(this.target.hb.cX, this.target.hb.cY, Color.RED, Color.GOLD));
/*    */     }
/*    */     
/* 47 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 49 */     if (this.duration < 0.0F) {
/* 50 */       if (this.target.currentHealth > 0) {
/* 51 */         this.info.applyPowers(this.info.owner, this.target);
/* 52 */         this.target.damage(this.info);
/*    */ 
/*    */         
/* 55 */         if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 56 */           this.numTimes--;
/* 57 */           addToTop(new RipAndTearAction(
/*    */                 
/* 59 */                 (AbstractCreature)AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng), this.info, this.numTimes));
/*    */         } 
/*    */ 
/*    */ 
/*    */         
/* 64 */         if (Settings.FAST_MODE) {
/* 65 */           addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */         } else {
/* 67 */           addToTop((AbstractGameAction)new WaitAction(0.2F));
/*    */         } 
/*    */       } 
/* 70 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\RipAndTearAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */