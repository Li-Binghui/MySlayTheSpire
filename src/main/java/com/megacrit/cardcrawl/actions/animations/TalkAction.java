/*    */ package com.megacrit.cardcrawl.actions.animations;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.SpeechBubble;
/*    */ 
/*    */ public class TalkAction extends AbstractGameAction {
/*    */   private String msg;
/*    */   private boolean used = false;
/*    */   private float bubbleDuration;
/*    */   private boolean player = false;
/*    */   
/*    */   public TalkAction(AbstractCreature source, String text, float duration, float bubbleDuration) {
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
/* 26 */     this.player = false;
/*    */   }
/*    */   
/*    */   public TalkAction(AbstractCreature source, String text) {
/* 30 */     this(source, text, 2.0F, 2.0F);
/*    */   }
/*    */   
/*    */   public TalkAction(boolean isPlayer, String text, float duration, float bubbleDuration) {
/* 34 */     this((AbstractCreature)AbstractDungeon.player, text, duration, bubbleDuration);
/* 35 */     this.player = isPlayer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 40 */     if (!this.used) {
/* 41 */       if (this.player) {
/* 42 */         AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, this.bubbleDuration, this.msg, this.source.isPlayer));
/*    */ 
/*    */       
/*    */       }
/*    */       else {
/*    */ 
/*    */ 
/*    */         
/* 50 */         AbstractDungeon.effectList.add(new SpeechBubble(this.source.hb.cX + this.source.dialogX, this.source.hb.cY + this.source.dialogY, this.bubbleDuration, this.msg, this.source.isPlayer));
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 58 */       this.used = true;
/*    */     } 
/*    */     
/* 61 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\animations\TalkAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */