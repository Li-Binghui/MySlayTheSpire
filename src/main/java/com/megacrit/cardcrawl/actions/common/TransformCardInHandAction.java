/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class TransformCardInHandAction extends AbstractGameAction {
/*    */   private AbstractCard replacement;
/*    */   private int handIndex;
/*    */   
/*    */   public TransformCardInHandAction(int index, AbstractCard replacement) {
/* 14 */     this.handIndex = index;
/* 15 */     this.replacement = replacement;
/*    */     
/* 17 */     if (Settings.FAST_MODE) {
/* 18 */       this.startDuration = 0.05F;
/*    */     } else {
/* 20 */       this.startDuration = 0.15F;
/*    */     } 
/* 22 */     this.duration = this.startDuration;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 27 */     if (this.duration == this.startDuration) {
/* 28 */       AbstractCard target = AbstractDungeon.player.hand.group.get(this.handIndex);
/* 29 */       this.replacement.current_x = target.current_x;
/* 30 */       this.replacement.current_y = target.current_y;
/* 31 */       this.replacement.target_x = target.target_x;
/* 32 */       this.replacement.target_y = target.target_y;
/* 33 */       this.replacement.drawScale = 1.0F;
/* 34 */       this.replacement.targetDrawScale = target.targetDrawScale;
/* 35 */       this.replacement.angle = target.angle;
/* 36 */       this.replacement.targetAngle = target.targetAngle;
/* 37 */       this.replacement.superFlash(Color.WHITE.cpy());
/* 38 */       AbstractDungeon.player.hand.group.set(this.handIndex, this.replacement);
/* 39 */       AbstractDungeon.player.hand.glowCheck();
/*    */     } 
/* 41 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\TransformCardInHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */