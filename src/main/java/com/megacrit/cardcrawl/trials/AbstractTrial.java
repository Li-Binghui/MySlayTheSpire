/*    */ package com.megacrit.cardcrawl.trials;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class AbstractTrial
/*    */ {
/*    */   public String name;
/*    */   public AbstractPlayer.PlayerClass c;
/*    */   public int energy;
/*    */   public CardGroup deck;
/* 16 */   public ArrayList<AbstractRelic> relics = new ArrayList<>();
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
/*    */   public AbstractPlayer setupPlayer(AbstractPlayer player) {
/* 30 */     return player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean keepStarterRelic() {
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> extraStartingRelicIDs() {
/* 48 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean keepsStarterCards() {
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> extraStartingCardIDs() {
/* 66 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean useRandomDailyMods() {
/* 75 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<String> dailyModIDs() {
/* 84 */     return new ArrayList<>();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\trials\AbstractTrial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */