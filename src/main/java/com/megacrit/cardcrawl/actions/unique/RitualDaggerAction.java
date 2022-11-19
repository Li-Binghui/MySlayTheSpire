/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class RitualDaggerAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private int increaseAmount;
/*    */   private DamageInfo info;
/*    */   private UUID uuid;
/*    */   
/*    */   public RitualDaggerAction(AbstractCreature target, DamageInfo info, int incAmount, UUID targetUUID) {
/* 20 */     this.info = info;
/* 21 */     setValues(target, info);
/* 22 */     this.increaseAmount = incAmount;
/* 23 */     this.actionType = ActionType.DAMAGE;
/* 24 */     this.duration = 0.1F;
/* 25 */     this.uuid = targetUUID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 30 */     if (this.duration == 0.1F && this.target != null) {
/* 31 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HORIZONTAL));
/*    */       
/* 33 */       this.target.damage(this.info);
/* 34 */       if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
/*    */         
/* 36 */         for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/* 37 */           if (!c.uuid.equals(this.uuid))
/*    */             continue; 
/* 39 */           c.misc += this.increaseAmount;
/* 40 */           c.applyPowers();
/* 41 */           c.baseDamage = c.misc;
/* 42 */           c.isDamageModified = false;
/*    */         } 
/* 44 */         for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
/* 45 */           c.misc += this.increaseAmount;
/* 46 */           c.applyPowers();
/* 47 */           c.baseDamage = c.misc;
/*    */         } 
/*    */       } 
/* 50 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 51 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */     } 
/* 54 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\RitualDaggerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */