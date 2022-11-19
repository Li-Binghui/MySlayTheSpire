/*    */ package com.megacrit.cardcrawl.rooms;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.GameCursor;
/*    */ import com.megacrit.cardcrawl.cutscenes.Cutscene;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class TrueVictoryRoom
/*    */   extends AbstractRoom {
/*    */   public Cutscene cutscene;
/*    */   
/*    */   public TrueVictoryRoom() {
/* 13 */     this.phase = RoomPhase.INCOMPLETE;
/* 14 */     this.cutscene = new Cutscene(AbstractDungeon.player.chosenClass);
/* 15 */     AbstractDungeon.overlayMenu.proceedButton.hideInstantly();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayerEntry() {
/* 20 */     AbstractDungeon.isScreenUp = true;
/* 21 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 22 */     GameCursor.hidden = true;
/* 23 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.NO_INTERACT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 28 */     super.update();
/* 29 */     this.cutscene.update();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 35 */     super.render(sb);
/* 36 */     this.cutscene.render(sb);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderAboveTopPanel(SpriteBatch sb) {
/* 41 */     super.renderAboveTopPanel(sb);
/* 42 */     this.cutscene.renderAbove(sb);
/*    */   }
/*    */ 
/*    */   
/*    */   public void dispose() {
/* 47 */     super.dispose();
/* 48 */     this.cutscene.dispose();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\TrueVictoryRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */