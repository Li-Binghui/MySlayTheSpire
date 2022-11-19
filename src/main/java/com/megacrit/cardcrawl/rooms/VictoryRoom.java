/*    */ package com.megacrit.cardcrawl.rooms;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*    */ import com.megacrit.cardcrawl.events.beyond.SpireHeart;
/*    */ 
/*    */ public class VictoryRoom extends AbstractRoom {
/*    */   public EventType eType;
/*    */   
/*    */   public enum EventType {
/* 11 */     HEART, NONE;
/*    */   }
/*    */   
/*    */   public VictoryRoom(EventType type) {
/* 15 */     this.phase = RoomPhase.EVENT;
/* 16 */     this.eType = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayerEntry() {
/* 21 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/*    */     
/* 23 */     switch (this.eType) {
/*    */       case HEART:
/* 25 */         this.event = (AbstractEvent)new SpireHeart();
/* 26 */         this.event.onEnterRoom();
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 38 */     super.update();
/* 39 */     if (!AbstractDungeon.isScreenUp) {
/* 40 */       this.event.update();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 47 */     if (this.event != null) {
/* 48 */       this.event.renderRoomEventPanel(sb);
/* 49 */       this.event.render(sb);
/*    */     } 
/* 51 */     super.render(sb);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderAboveTopPanel(SpriteBatch sb) {
/* 56 */     super.renderAboveTopPanel(sb);
/* 57 */     if (this.event != null)
/* 58 */       this.event.renderAboveTopPanel(sb); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\VictoryRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */