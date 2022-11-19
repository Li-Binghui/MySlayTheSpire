/*    */ package com.megacrit.cardcrawl.actions.animations;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.MegaSpeechBubble;
/*    */ 
/*    */ public class ShoutAction extends AbstractGameAction {
/*    */   private String msg;
/*    */   private boolean used = false;
/*    */   private float bubbleDuration;
/*    */   private static final float DEFAULT_BUBBLE_DUR = 3.0F;
/*    */   
/*    */   public ShoutAction(AbstractCreature source, String text, float duration, float bubbleDuration) {
/* 16 */     setValues(source, source);
/* 17 */     if (Settings.FAST_MODE) {
/* 18 */       this.duration = Settings.ACTION_DUR_MED;
/*    */     } else {
/* 20 */       this.duration = duration;
/*    */     } 
/*    */     
/* 23 */     this.msg = text;
/* 24 */     this.actionType = ActionType.TEXT;
/* 25 */     this.bubbleDuration = bubbleDuration;
/*    */   }
/*    */   
/*    */   public ShoutAction(AbstractCreature source, String text) {
/* 29 */     this(source, text, 0.5F, 3.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 34 */     if (!this.used) {
/* 35 */       AbstractDungeon.effectList.add(new MegaSpeechBubble(this.source.hb.cX + this.source.dialogX, this.source.hb.cY + this.source.dialogY, this.bubbleDuration, this.msg, this.source.isPlayer));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 42 */       this.used = true;
/*    */     } 
/*    */     
/* 45 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\animations\ShoutAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */