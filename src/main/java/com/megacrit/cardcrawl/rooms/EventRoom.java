/*    */ package com.megacrit.cardcrawl.rooms;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.random.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EventRoom
/*    */   extends AbstractRoom
/*    */ {
/*    */   public void onPlayerEntry() {
/* 20 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 21 */     Random eventRngDuplicate = new Random(Settings.seed, AbstractDungeon.eventRng.counter);
/* 22 */     this.event = AbstractDungeon.generateEvent(eventRngDuplicate);
/* 23 */     this.event.onEnterRoom();
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 28 */     super.update();
/* 29 */     if (!AbstractDungeon.isScreenUp) {
/* 30 */       this.event.update();
/*    */     }
/*    */ 
/*    */     
/* 34 */     if (this.event.waitTimer == 0.0F && !this.event.hasFocus && 
/* 35 */       this.phase != RoomPhase.COMBAT) {
/* 36 */       this.phase = RoomPhase.COMPLETE;
/* 37 */       this.event.reopen();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 45 */     if (this.event != null) {
/* 46 */       this.event.render(sb);
/*    */     }
/* 48 */     super.render(sb);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderAboveTopPanel(SpriteBatch sb) {
/* 53 */     super.renderAboveTopPanel(sb);
/* 54 */     if (this.event != null)
/* 55 */       this.event.renderAboveTopPanel(sb); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\EventRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */