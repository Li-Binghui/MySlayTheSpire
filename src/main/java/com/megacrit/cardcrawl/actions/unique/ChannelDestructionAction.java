/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class ChannelDestructionAction
/*    */   extends AbstractGameAction {
/*    */   public ChannelDestructionAction(AbstractCreature target) {
/* 13 */     this.target = target;
/* 14 */     this.source = (AbstractCreature)AbstractDungeon.player;
/* 15 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 21 */       DamageInfo info = new DamageInfo(this.source, AbstractDungeon.player.hand.size() * 2);
/* 22 */       info.applyPowers((AbstractCreature)AbstractDungeon.player, this.target);
/* 23 */       addToTop((AbstractGameAction)new DamageAction(this.target, info, AttackEffect.BLUNT_HEAVY));
/*    */     } 
/*    */     
/* 26 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\ChannelDestructionAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */