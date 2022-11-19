/*    */ package com.megacrit.cardcrawl.trials;
/*    */ 
/*    */

import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestTrial
/*    */   extends AbstractTrial
/*    */ {
/*    */   public AbstractPlayer setupPlayer(AbstractPlayer player) {
/* 23 */     player.maxHealth = 20;
/* 24 */     player.currentHealth = 10;
/* 25 */     player.gold = 777;
/* 26 */     return player;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean keepStarterRelic() {
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> extraStartingRelicIDs() {
/* 36 */     return Arrays.asList(new String[] { "Derp Rock", "Unceasing Top" });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean keepsStarterCards() {
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> extraStartingCardIDs() {
/* 46 */     return Arrays.asList(new String[] { "Demon Form", "Wraith Form v2", "Echo Form" });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean useRandomDailyMods() {
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<String> dailyModIDs() {
/* 56 */     ArrayList<String> retVal = new ArrayList<>();
/* 57 */     retVal.add("Diverse");
/* 58 */     retVal.add("Lethality");
/* 59 */     retVal.add("Time Dilation");
/* 60 */     retVal.add("Cursed Run");
/* 61 */     retVal.add("Elite Swarm");
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\trials\TestTrial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */