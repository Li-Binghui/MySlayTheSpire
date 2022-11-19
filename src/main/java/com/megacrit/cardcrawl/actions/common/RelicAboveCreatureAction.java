/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.vfx.RelicAboveCreatureEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RelicAboveCreatureAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private boolean used = false;
/*    */   private AbstractRelic relic;
/*    */   
/*    */   public RelicAboveCreatureAction(AbstractCreature source, AbstractRelic relic) {
/* 19 */     setValues(source, source);
/* 20 */     this.relic = relic;
/* 21 */     this.actionType = ActionType.TEXT;
/* 22 */     this.duration = Settings.ACTION_DUR_XFAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 27 */     if (!this.used) {
/* 28 */       AbstractDungeon.effectList.add(new RelicAboveCreatureEffect(this.source.hb.cX - this.source.animX, this.source.hb.cY + this.source.hb.height / 2.0F - this.source.animY, this.relic));
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 33 */       this.used = true;
/*    */     } 
/* 35 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\RelicAboveCreatureAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */