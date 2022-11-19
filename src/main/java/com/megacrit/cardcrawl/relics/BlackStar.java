/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class BlackStar
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Black Star";
/*    */   
/*    */   public BlackStar() {
/* 11 */     super("Black Star", "blackstar.png", RelicTier.BOSS, LandingSound.HEAVY);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom(AbstractRoom room) {
/* 21 */     if (room instanceof com.megacrit.cardcrawl.rooms.MonsterRoomElite) {
/* 22 */       this.pulse = true;
/* 23 */       beginPulse();
/*    */     } else {
/* 25 */       this.pulse = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 31 */     if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomElite) {
/* 32 */       flash();
/* 33 */       this.pulse = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 39 */     return new BlackStar();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\BlackStar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */