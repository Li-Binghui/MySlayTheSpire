/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
/*    */ 
/*    */ public class WhirlwindAction
/*    */   extends AbstractGameAction {
/*    */   public int[] multiDamage;
/*    */   private boolean freeToPlayOnce = false;
/* 20 */   private int energyOnUse = -1;
/*    */   
/*    */   private DamageInfo.DamageType damageType;
/*    */   
/*    */   private AbstractPlayer p;
/*    */ 
/*    */   
/*    */   public WhirlwindAction(AbstractPlayer p, int[] multiDamage, DamageInfo.DamageType damageType, boolean freeToPlayOnce, int energyOnUse) {
/* 28 */     this.multiDamage = multiDamage;
/* 29 */     this.damageType = damageType;
/* 30 */     this.p = p;
/* 31 */     this.freeToPlayOnce = freeToPlayOnce;
/* 32 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 33 */     this.actionType = ActionType.SPECIAL;
/* 34 */     this.energyOnUse = energyOnUse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 39 */     int effect = EnergyPanel.totalCount;
/* 40 */     if (this.energyOnUse != -1) {
/* 41 */       effect = this.energyOnUse;
/*    */     }
/*    */     
/* 44 */     if (this.p.hasRelic("Chemical X")) {
/* 45 */       effect += 2;
/* 46 */       this.p.getRelic("Chemical X").flash();
/*    */     } 
/*    */     
/* 49 */     if (effect > 0) {
/* 50 */       for (int i = 0; i < effect; i++) {
/* 51 */         if (i == 0) {
/* 52 */           addToBot((AbstractGameAction)new SFXAction("ATTACK_WHIRLWIND"));
/* 53 */           addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WhirlwindEffect(), 0.0F));
/*    */         } 
/*    */         
/* 56 */         addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
/* 57 */         addToBot((AbstractGameAction)new VFXAction((AbstractCreature)this.p, (AbstractGameEffect)new CleaveEffect(), 0.0F));
/* 58 */         addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)this.p, this.multiDamage, this.damageType, AttackEffect.NONE, true));
/*    */       } 
/*    */       
/* 61 */       if (!this.freeToPlayOnce) {
/* 62 */         this.p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     } 
/* 65 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\WhirlwindAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */