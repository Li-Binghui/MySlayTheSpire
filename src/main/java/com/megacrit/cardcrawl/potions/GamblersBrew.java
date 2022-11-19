/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ 
/*    */ public class GamblersBrew extends AbstractPotion {
/* 12 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("GamblersBrew");
/*    */   
/*    */   public static final String POTION_ID = "GamblersBrew";
/*    */ 
/*    */   
/*    */   public GamblersBrew() {
/* 18 */     super(potionStrings.NAME, "GamblersBrew", PotionRarity.UNCOMMON, PotionSize.S, PotionColor.SMOKE);
/* 19 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 24 */     this.description = potionStrings.DESCRIPTIONS[0];
/* 25 */     this.tips.clear();
/* 26 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 31 */     if (!AbstractDungeon.player.hand.isEmpty()) {
/* 32 */       addToBot((AbstractGameAction)new GamblingChipAction((AbstractCreature)AbstractDungeon.player, true));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 38 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 43 */     return new GamblersBrew();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\GamblersBrew.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */