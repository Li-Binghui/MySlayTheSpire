/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*    */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ 
/*    */ public class DarkstonePeriapt
/*    */   extends AbstractRelic
/*    */ {
/*    */   public static final String ID = "Darkstone Periapt";
/*    */   private static final int HP_AMT = 6;
/*    */   
/*    */   public DarkstonePeriapt() {
/* 16 */     super("Darkstone Periapt", "darkstone.png", RelicTier.UNCOMMON, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onObtainCard(AbstractCard card) {
/* 21 */     if (card.color == AbstractCard.CardColor.CURSE) {
/* 22 */       if (ModHelper.isModEnabled("Hoarder")) {
/* 23 */         AbstractDungeon.player.increaseMaxHp(6, true);
/* 24 */         AbstractDungeon.player.increaseMaxHp(6, true);
/*    */       } 
/* 26 */       AbstractDungeon.player.increaseMaxHp(6, true);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 32 */     return this.DESCRIPTIONS[0] + '\006' + LocalizedStrings.PERIOD;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 37 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 48);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 42 */     return new DarkstonePeriapt();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\DarkstonePeriapt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */