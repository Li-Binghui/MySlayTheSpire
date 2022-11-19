/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ImpulseAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class EmotionChip
/*    */   extends AbstractRelic {
/*    */   public EmotionChip() {
/* 11 */     super("Emotion Chip", "emotionChip.png", RelicTier.RARE, LandingSound.CLINK);
/* 12 */     this.pulse = false;
/*    */   }
/*    */   public static final String ID = "Emotion Chip";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 22 */     if (this.pulse) {
/* 23 */       this.pulse = false;
/* 24 */       flash();
/* 25 */       addToBot((AbstractGameAction)new ImpulseAction());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void wasHPLost(int damageAmount) {
/* 31 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && 
/* 32 */       damageAmount > 0) {
/* 33 */       flash();
/* 34 */       if (!this.pulse) {
/* 35 */         beginPulse();
/* 36 */         this.pulse = true;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 44 */     this.pulse = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 49 */     return new EmotionChip();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\EmotionChip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */