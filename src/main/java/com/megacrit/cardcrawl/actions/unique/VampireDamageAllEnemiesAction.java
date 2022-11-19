/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;
/*    */ 
/*    */ public class VampireDamageAllEnemiesAction extends AbstractGameAction {
/*    */   public int[] damage;
/*    */   
/*    */   public VampireDamageAllEnemiesAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type, AttackEffect effect) {
/* 21 */     setValues(null, source, amount[0]);
/* 22 */     this.damage = amount;
/* 23 */     this.actionType = ActionType.DAMAGE;
/* 24 */     this.damageType = type;
/* 25 */     this.attackEffect = effect;
/* 26 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 31 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 32 */       boolean playedMusic = false;
/* 33 */       int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
/* 34 */       for (int i = 0; i < temp; i++) {
/* 35 */         if (!((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isDying && 
/* 36 */           ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).currentHealth > 0 && 
/* 37 */           !((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isEscaping) {
/* 38 */           if (playedMusic) {
/* 39 */             AbstractDungeon.effectList.add(new FlashAtkImgEffect(
/*    */                   
/* 41 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cX, 
/* 42 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cY, this.attackEffect, true));
/*    */           }
/*    */           else {
/*    */             
/* 46 */             playedMusic = true;
/* 47 */             AbstractDungeon.effectList.add(new FlashAtkImgEffect(
/*    */                   
/* 49 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cX, 
/* 50 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cY, this.attackEffect));
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 57 */     tickDuration();
/*    */     
/* 59 */     if (this.isDone) {
/* 60 */       for (AbstractPower p : AbstractDungeon.player.powers) {
/* 61 */         p.onDamageAllEnemies(this.damage);
/*    */       }
/*    */       
/* 64 */       int healAmount = 0;
/* 65 */       for (int i = 0; i < (AbstractDungeon.getCurrRoom()).monsters.monsters.size(); i++) {
/* 66 */         AbstractMonster target = (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i);
/* 67 */         if (!target.isDying && target.currentHealth > 0 && !target.isEscaping) {
/* 68 */           target.damage(new DamageInfo(this.source, this.damage[i], this.damageType));
/* 69 */           if (target.lastDamageTaken > 0) {
/* 70 */             healAmount += target.lastDamageTaken;
/* 71 */             for (int j = 0; j < target.lastDamageTaken / 2 && j < 10; j++) {
/* 72 */               addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new FlyingOrbEffect(target.hb.cX, target.hb.cY)));
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */       
/* 78 */       if (healAmount > 0) {
/* 79 */         if (!Settings.FAST_MODE) {
/* 80 */           addToBot((AbstractGameAction)new WaitAction(0.3F));
/*    */         }
/* 82 */         addToBot((AbstractGameAction)new HealAction(this.source, this.source, healAmount));
/*    */       } 
/*    */       
/* 85 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 86 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/* 88 */       addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\VampireDamageAllEnemiesAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */