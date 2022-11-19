/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.OmegaFlashEffect;
/*    */ 
/*    */ public class OmegaPower extends AbstractPower {
/* 18 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("OmegaPower"); public static final String POWER_ID = "OmegaPower";
/*    */   
/*    */   public OmegaPower(AbstractCreature owner, int newAmount) {
/* 21 */     this.name = powerStrings.NAME;
/* 22 */     this.ID = "OmegaPower";
/* 23 */     this.owner = owner;
/* 24 */     this.amount = newAmount;
/* 25 */     updateDescription();
/* 26 */     loadRegion("omega");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 31 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 36 */     if (isPlayer) {
/* 37 */       flash();
/*    */       
/* 39 */       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 40 */         if (m != null && !m.isDeadOrEscaped()) {
/* 41 */           if (Settings.FAST_MODE) {
/* 42 */             addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new OmegaFlashEffect(m.hb.cX, m.hb.cY))); continue;
/*    */           } 
/* 44 */           addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new OmegaFlashEffect(m.hb.cX, m.hb.cY), 0.2F));
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 49 */       addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, 
/*    */ 
/*    */             
/* 52 */             DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\OmegaPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */