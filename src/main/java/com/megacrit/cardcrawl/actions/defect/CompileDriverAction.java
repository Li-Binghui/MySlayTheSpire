/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class CompileDriverAction
/*    */   extends AbstractGameAction {
/*    */   public CompileDriverAction(AbstractPlayer source, int amount) {
/* 14 */     setValues(this.target, (AbstractCreature)source, amount);
/* 15 */     this.actionType = ActionType.WAIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     ArrayList<String> orbList = new ArrayList<>();
/* 21 */     for (AbstractOrb o : AbstractDungeon.player.orbs) {
/* 22 */       if (o.ID != null && !o.ID.equals("Empty") && !orbList.contains(o.ID)) {
/* 23 */         orbList.add(o.ID);
/*    */       }
/*    */     } 
/* 26 */     int toDraw = orbList.size() * this.amount;
/* 27 */     if (toDraw > 0) {
/* 28 */       addToTop((AbstractGameAction)new DrawCardAction(this.source, toDraw));
/*    */     }
/* 30 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\CompileDriverAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */