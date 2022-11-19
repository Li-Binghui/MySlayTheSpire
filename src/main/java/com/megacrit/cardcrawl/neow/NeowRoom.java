/*    */ package com.megacrit.cardcrawl.neow;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class NeowRoom
/*    */   extends AbstractRoom {
/*    */   public NeowRoom(boolean isDone) {
/* 10 */     this.phase = RoomPhase.EVENT;
/* 11 */     this.event = new NeowEvent(isDone);
/* 12 */     this.event.onEnterRoom();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayerEntry() {
/* 17 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     super.update();
/* 23 */     if (!AbstractDungeon.isScreenUp) {
/* 24 */       this.event.update();
/*    */     }
/*    */ 
/*    */     
/* 28 */     if (this.event.waitTimer == 0.0F && !this.event.hasFocus && 
/* 29 */       this.phase != RoomPhase.COMBAT) {
/* 30 */       this.phase = RoomPhase.COMPLETE;
/* 31 */       this.event.reopen();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 38 */     super.render(sb);
/* 39 */     this.event.render(sb);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderAboveTopPanel(SpriteBatch sb) {
/* 44 */     super.renderAboveTopPanel(sb);
/* 45 */     if (this.event != null)
/* 46 */       this.event.renderAboveTopPanel(sb); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\neow\NeowRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */