/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class PotionOfCapacity extends AbstractPotion {
/* 14 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("PotionOfCapacity"); public static final String POTION_ID = "PotionOfCapacity";
/*    */   
/*    */   public PotionOfCapacity() {
/* 17 */     super(potionStrings.NAME, "PotionOfCapacity", PotionRarity.UNCOMMON, PotionSize.SPHERE, PotionColor.BLUE);
/* 18 */     this.labOutlineColor = Settings.BLUE_RELIC_COLOR;
/* 19 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 24 */     this.potency = getPotency();
/* 25 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 26 */     this.tips.clear();
/* 27 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 32 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 33 */       addToBot((AbstractGameAction)new IncreaseMaxOrbAction(this.potency));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 39 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 44 */     return new PotionOfCapacity();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\PotionOfCapacity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */