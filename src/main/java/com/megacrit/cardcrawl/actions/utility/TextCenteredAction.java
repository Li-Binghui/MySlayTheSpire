/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.TextCenteredEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextCenteredAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private boolean used = false;
/*    */   private String msg;
/*    */   private static final float DURATION = 2.0F;
/*    */   
/*    */   public TextCenteredAction(AbstractCreature source, String text) {
/* 18 */     setValues(source, source);
/* 19 */     this.msg = text;
/* 20 */     this.duration = 2.0F;
/* 21 */     this.actionType = ActionType.TEXT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     if (!this.used) {
/* 27 */       AbstractDungeon.effectList.add(new TextCenteredEffect(this.msg));
/* 28 */       this.used = true;
/*    */     } 
/* 30 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\TextCenteredAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */