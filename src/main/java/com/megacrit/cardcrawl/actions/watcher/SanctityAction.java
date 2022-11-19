/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.SanctityEffect;
/*    */ 
/*    */ public class SanctityAction
/*    */   extends AbstractGameAction {
/*    */   public SanctityAction(int amount) {
/* 17 */     this.amtToDraw = amount;
/*    */   }
/*    */   private int amtToDraw;
/*    */   
/*    */   public void update() {
/* 22 */     if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat
/* 23 */       .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
/* 24 */         .size() - 2)).type == AbstractCard.CardType.SKILL) {
/* 25 */       addToTop((AbstractGameAction)new DrawCardAction(this.amtToDraw));
/* 26 */       addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new SanctityEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY)));
/* 27 */       addToTop((AbstractGameAction)new SFXAction("HEAL_1"));
/* 28 */       addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.GOLD, true), 0.1F));
/*    */     } 
/* 30 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\SanctityAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */