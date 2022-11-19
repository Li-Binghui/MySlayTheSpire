/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
/*    */ 
/*    */ 
/*    */ public class TextAboveCreatureAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private boolean used = false;
/*    */   private String msg;
/*    */   
/*    */   public enum TextType
/*    */   {
/* 19 */     STUNNED, INTERRUPTED;
/*    */   }
/*    */   
/*    */   public TextAboveCreatureAction(AbstractCreature source, TextType type) {
/* 23 */     if (type == TextType.STUNNED) {
/* 24 */       setValues(source, source);
/* 25 */       this.msg = AbstractCreature.TEXT[3];
/* 26 */       this.actionType = ActionType.TEXT;
/* 27 */       this.duration = Settings.ACTION_DUR_FASTER;
/* 28 */     } else if (type == TextType.INTERRUPTED) {
/* 29 */       setValues(source, source);
/* 30 */       this.msg = AbstractCreature.TEXT[4];
/* 31 */       this.actionType = ActionType.TEXT;
/* 32 */       this.duration = Settings.ACTION_DUR_FASTER;
/*    */     } else {
/* 34 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   public TextAboveCreatureAction(AbstractCreature source, String text) {
/* 39 */     setValues(source, source);
/* 40 */     this.msg = text;
/* 41 */     this.actionType = ActionType.TEXT;
/* 42 */     this.duration = Settings.ACTION_DUR_FASTER;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 47 */     if (!this.used) {
/* 48 */       AbstractDungeon.effectList.add(new TextAboveCreatureEffect(this.source.hb.cX - this.source.animX, this.source.hb.cY + this.target.hb.height / 2.0F, this.msg, Color.WHITE
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 53 */             .cpy()));
/* 54 */       this.used = true;
/*    */     } 
/* 56 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\TextAboveCreatureAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */