/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class BlackBlood extends AbstractRelic {
/*    */   public BlackBlood() {
/* 11 */     super("Black Blood", "blackBlood.png", RelicTier.BOSS, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0] + '\f' + this.DESCRIPTIONS[1];
/*    */   }
/*    */   public static final String ID = "Black Blood";
/*    */   
/*    */   public void onVictory() {
/* 21 */     flash();
/* 22 */     AbstractPlayer p = AbstractDungeon.player;
/* 23 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)p, this));
/* 24 */     if (p.currentHealth > 0) {
/* 25 */       p.heal(12);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 31 */     return AbstractDungeon.player.hasRelic("Burning Blood");
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 36 */     return new BlackBlood();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\BlackBlood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */