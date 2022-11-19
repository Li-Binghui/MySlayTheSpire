/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
/*    */ 
/*    */ public class LightningOrbEvokeAction extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private boolean hitAll;
/*    */   
/*    */   public LightningOrbEvokeAction(DamageInfo info, boolean hitAll) {
/* 22 */     this.info = info;
/* 23 */     this.actionType = ActionType.DAMAGE;
/* 24 */     this.attackEffect = AttackEffect.NONE;
/* 25 */     this.hitAll = hitAll;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 30 */     if (!this.hitAll) {
/* 31 */       AbstractMonster abstractMonster = AbstractDungeon.getRandomMonster();
/*    */       
/* 33 */       if (abstractMonster != null) {
/* 34 */         float speedTime = 0.1F;
/* 35 */         if (!AbstractDungeon.player.orbs.isEmpty()) {
/* 36 */           speedTime = 0.2F / AbstractDungeon.player.orbs.size();
/*    */         }
/* 38 */         if (Settings.FAST_MODE) {
/* 39 */           speedTime = 0.0F;
/*    */         }
/*    */         
/* 42 */         this.info.output = AbstractOrb.applyLockOn((AbstractCreature)abstractMonster, this.info.base);
/* 43 */         addToTop((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, this.info, AttackEffect.NONE, true));
/* 44 */         addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(((AbstractCreature)abstractMonster).drawX, ((AbstractCreature)abstractMonster).drawY), speedTime));
/* 45 */         addToTop((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE"));
/*    */       } 
/*    */     } else {
/* 48 */       float speedTime = 0.2F / AbstractDungeon.player.orbs.size();
/* 49 */       if (Settings.FAST_MODE) {
/* 50 */         speedTime = 0.0F;
/*    */       }
/* 52 */       addToTop((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)AbstractDungeon.player, 
/*    */ 
/*    */             
/* 55 */             DamageInfo.createDamageMatrix(this.info.base, true, true), DamageInfo.DamageType.THORNS, AttackEffect.NONE));
/*    */ 
/*    */ 
/*    */       
/* 59 */       for (AbstractMonster m3 : (AbstractDungeon.getMonsters()).monsters) {
/* 60 */         if (!m3.isDeadOrEscaped() && !m3.halfDead) {
/* 61 */           addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(m3.drawX, m3.drawY), speedTime));
/*    */         }
/*    */       } 
/* 64 */       addToTop((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE"));
/*    */     } 
/*    */     
/* 67 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\LightningOrbEvokeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */