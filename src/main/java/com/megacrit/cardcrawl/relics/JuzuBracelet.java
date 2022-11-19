/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class JuzuBracelet extends AbstractRelic {
/*    */   public static final String ID = "Juzu Bracelet";
/*    */   
/*    */   public JuzuBracelet() {
/* 10 */     super("Juzu Bracelet", "juzuBracelet.png", RelicTier.COMMON, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 20 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 48);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 25 */     return new JuzuBracelet();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\JuzuBracelet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */