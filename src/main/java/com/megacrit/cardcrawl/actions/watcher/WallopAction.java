/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class WallopAction
/*    */   extends AbstractGameAction {
/*    */   public WallopAction(AbstractCreature target, DamageInfo info) {
/* 18 */     this.info = info;
/* 19 */     setValues(target, info);
/* 20 */     this.actionType = ActionType.DAMAGE;
/* 21 */     this.startDuration = Settings.ACTION_DUR_FAST;
/* 22 */     this.duration = this.startDuration;
/*    */   }
/*    */   private DamageInfo info;
/*    */   
/*    */   public void update() {
/* 27 */     if (shouldCancelAction()) {
/* 28 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 32 */     tickDuration();
/*    */     
/* 34 */     if (this.isDone) {
/* 35 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY, false));
/*    */       
/* 37 */       this.target.damage(this.info);
/* 38 */       if (this.target.lastDamageTaken > 0) {
/* 39 */         addToTop((AbstractGameAction)new GainBlockAction(this.source, this.target.lastDamageTaken));
/* 40 */         if (this.target.hb != null) {
/* 41 */           addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new WallopEffect(this.target.lastDamageTaken, this.target.hb.cX, this.target.hb.cY)));
/*    */         }
/*    */       } 
/*    */       
/* 45 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 46 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       } else {
/* 48 */         addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\WallopAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */