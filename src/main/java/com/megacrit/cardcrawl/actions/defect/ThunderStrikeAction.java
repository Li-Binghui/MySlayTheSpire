/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
/*    */ 
/*    */ public class ThunderStrikeAction extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.01F;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.2F;
/*    */   private int numTimes;
/*    */   
/*    */   public ThunderStrikeAction(AbstractCreature target, DamageInfo info, int numTimes) {
/* 19 */     this.info = info;
/* 20 */     this.target = target;
/* 21 */     this.actionType = ActionType.DAMAGE;
/* 22 */     this.attackEffect = AttackEffect.NONE;
/* 23 */     this.duration = 0.01F;
/* 24 */     this.numTimes = numTimes;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     if (this.target == null) {
/* 30 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 34 */     if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 35 */       AbstractDungeon.actionManager.clearPostCombatActions();
/* 36 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     if (this.target.currentHealth > 0) {
/* 41 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/* 42 */       AbstractDungeon.effectList.add(new LightningEffect(this.target.drawX, this.target.drawY));
/* 43 */       CardCrawlGame.sound.play("ORB_LIGHTNING_EVOKE", 0.1F);
/*    */       
/* 45 */       this.info.applyPowers(this.info.owner, this.target);
/* 46 */       this.target.damage(this.info);
/*    */ 
/*    */       
/* 49 */       if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 50 */         this.numTimes--;
/* 51 */         addToTop(new ThunderStrikeAction(
/*    */               
/* 53 */               (AbstractCreature)AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng), this.info, this.numTimes));
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 58 */       addToTop((AbstractGameAction)new WaitAction(0.2F));
/*    */     } 
/*    */     
/* 61 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\ThunderStrikeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */