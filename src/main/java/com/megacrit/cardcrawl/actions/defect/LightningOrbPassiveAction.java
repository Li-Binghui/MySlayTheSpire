/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
/*    */ 
/*    */ public class LightningOrbPassiveAction
/*    */   extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private AbstractOrb orb;
/*    */   private boolean hitAll;
/*    */   
/*    */   public LightningOrbPassiveAction(DamageInfo info, AbstractOrb orb, boolean hitAll) {
/* 25 */     this.info = info;
/* 26 */     this.orb = orb;
/* 27 */     this.actionType = ActionType.DAMAGE;
/* 28 */     this.attackEffect = AttackEffect.NONE;
/* 29 */     this.hitAll = hitAll;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 34 */     if (!this.hitAll) {
/* 35 */       AbstractMonster abstractMonster = AbstractDungeon.getRandomMonster();
/*    */       
/* 37 */       if (abstractMonster != null) {
/* 38 */         float speedTime = 0.2F / AbstractDungeon.player.orbs.size();
/* 39 */         if (Settings.FAST_MODE) {
/* 40 */           speedTime = 0.0F;
/*    */         }
/*    */         
/* 43 */         this.info.output = AbstractOrb.applyLockOn((AbstractCreature)abstractMonster, this.info.base);
/* 44 */         addToTop((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, this.info, AttackEffect.NONE, true));
/* 45 */         addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(((AbstractCreature)abstractMonster).drawX, ((AbstractCreature)abstractMonster).drawY), speedTime));
/* 46 */         addToTop((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE"));
/* 47 */         if (this.orb != null) {
/* 48 */           addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this.orb, OrbFlareEffect.OrbFlareColor.LIGHTNING), speedTime));
/*    */         }
/*    */       } 
/*    */     } else {
/* 52 */       float speedTime = 0.2F / AbstractDungeon.player.orbs.size();
/* 53 */       if (Settings.FAST_MODE) {
/* 54 */         speedTime = 0.0F;
/*    */       }
/* 56 */       addToTop((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)AbstractDungeon.player, 
/*    */ 
/*    */             
/* 59 */             DamageInfo.createDamageMatrix(this.info.base, true, true), DamageInfo.DamageType.THORNS, AttackEffect.NONE));
/*    */ 
/*    */       
/* 62 */       for (AbstractMonster m3 : (AbstractDungeon.getMonsters()).monsters) {
/* 63 */         if (!m3.isDeadOrEscaped() && !m3.halfDead) {
/* 64 */           addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(m3.drawX, m3.drawY), speedTime));
/*    */         }
/*    */       } 
/* 67 */       addToTop((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE"));
/* 68 */       if (this.orb != null) {
/* 69 */         addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this.orb, OrbFlareEffect.OrbFlareColor.LIGHTNING), speedTime));
/*    */       }
/*    */     } 
/*    */     
/* 73 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\LightningOrbPassiveAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */