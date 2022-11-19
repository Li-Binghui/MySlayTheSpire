/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;
/*    */ 
/*    */ public class ChangeStanceAction
/*    */   extends AbstractGameAction {
/*    */   private String id;
/* 14 */   private AbstractStance newStance = null;
/*    */   
/*    */   public ChangeStanceAction(String stanceId) {
/* 17 */     this.duration = Settings.ACTION_DUR_FAST;
/* 18 */     this.id = stanceId;
/*    */   }
/*    */   
/*    */   public ChangeStanceAction(AbstractStance newStance) {
/* 22 */     this(newStance.ID);
/* 23 */     this.newStance = newStance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 28 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 29 */       if (AbstractDungeon.player.hasPower("CannotChangeStancePower")) {
/* 30 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 34 */       AbstractStance oldStance = AbstractDungeon.player.stance;
/*    */       
/* 36 */       if (!oldStance.ID.equals(this.id)) {
/* 37 */         if (this.newStance == null) {
/* 38 */           this.newStance = AbstractStance.getStanceFromName(this.id);
/*    */         }
/*    */         
/* 41 */         for (AbstractPower p : AbstractDungeon.player.powers) {
/* 42 */           p.onChangeStance(oldStance, this.newStance);
/*    */         }
/*    */         
/* 45 */         for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 46 */           r.onChangeStance(oldStance, this.newStance);
/*    */         }
/*    */         
/* 49 */         oldStance.onExitStance();
/* 50 */         AbstractDungeon.player.stance = this.newStance;
/* 51 */         this.newStance.onEnterStance();
/*    */         
/* 53 */         if (AbstractDungeon.actionManager.uniqueStancesThisCombat.containsKey(this.newStance.ID)) {
/* 54 */           int currentCount = ((Integer)AbstractDungeon.actionManager.uniqueStancesThisCombat.get(this.newStance.ID)).intValue();
/* 55 */           AbstractDungeon.actionManager.uniqueStancesThisCombat.put(this.newStance.ID, Integer.valueOf(currentCount + 1));
/*    */         } else {
/* 57 */           AbstractDungeon.actionManager.uniqueStancesThisCombat.put(this.newStance.ID, Integer.valueOf(1));
/*    */         } 
/*    */         
/* 60 */         AbstractDungeon.player.switchedStance();
/*    */         
/* 62 */         for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/* 63 */           c.triggerExhaustedCardsOnStanceChange(this.newStance);
/*    */         }
/*    */         
/* 66 */         AbstractDungeon.player.onStanceChange(this.id);
/*    */       } 
/*    */       
/* 69 */       AbstractDungeon.onModifyPower();
/*    */       
/* 71 */       if (Settings.FAST_MODE) {
/* 72 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/* 77 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\ChangeStanceAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */