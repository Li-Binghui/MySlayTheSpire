/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class EternalFeather
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Eternal Feather";
/*    */   
/*    */   public EternalFeather() {
/* 11 */     super("Eternal Feather", "eternal_feather.png", RelicTier.UNCOMMON, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0] + '\005' + this.DESCRIPTIONS[1] + '\003' + this.DESCRIPTIONS[2];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom(AbstractRoom room) {
/* 21 */     if (room instanceof com.megacrit.cardcrawl.rooms.RestRoom) {
/* 22 */       flash();
/* 23 */       int amountToGain = AbstractDungeon.player.masterDeck.size() / 5 * 3;
/* 24 */       AbstractDungeon.player.heal(amountToGain);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 30 */     return new EternalFeather();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\EternalFeather.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */