/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
/*    */ 
/*    */ public class FollowUpAction
/*    */   extends AbstractGameAction {
/*    */   public void update() {
/* 16 */     if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat
/* 17 */       .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
/* 18 */         .size() - 2)).type == AbstractCard.CardType.ATTACK) {
/* 19 */       addToTop((AbstractGameAction)new GainEnergyAction(1));
/*    */       
/* 21 */       if (Settings.FAST_MODE) {
/* 22 */         addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new MiracleEffect(Color.CYAN, Color.PURPLE, "ATTACK_MAGIC_SLOW_1"), 0.0F));
/*    */       } else {
/* 24 */         addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new MiracleEffect(Color.CYAN, Color.PURPLE, "ATTACK_MAGIC_SLOW_1"), 0.3F));
/*    */       } 
/*    */     } 
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\FollowUpAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */