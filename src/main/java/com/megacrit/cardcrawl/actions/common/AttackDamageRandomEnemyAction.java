/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
/*    */ 
/*    */ public class AttackDamageRandomEnemyAction extends AbstractGameAction {
/*    */   private AbstractCard card;
/*    */   
/*    */   public AttackDamageRandomEnemyAction(AbstractCard card, AttackEffect effect) {
/* 18 */     this.card = card;
/* 19 */     this.effect = effect;
/*    */   }
/*    */   private AttackEffect effect;
/*    */   public AttackDamageRandomEnemyAction(AbstractCard card) {
/* 23 */     this(card, AttackEffect.NONE);
/*    */   }
/*    */   
/*    */   public void update() {
/* 27 */     this.target = (AbstractCreature)AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
/* 28 */     if (this.target != null) {
/* 29 */       this.card.calculateCardDamage((AbstractMonster)this.target);
/* 30 */       if (AttackEffect.LIGHTNING == this.effect) {
/* 31 */         addToTop(new DamageAction(this.target, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), AttackEffect.NONE));
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 36 */         addToTop((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
/* 37 */         addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(this.target.hb.cX, this.target.hb.cY)));
/*    */       } else {
/* 39 */         addToTop(new DamageAction(this.target, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), this.effect));
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\AttackDamageRandomEnemyAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */