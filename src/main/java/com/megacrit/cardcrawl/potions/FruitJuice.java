/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class FruitJuice
/*    */   extends AbstractPotion {
/*    */   public static final String POTION_ID = "Fruit Juice";
/* 13 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Fruit Juice");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FruitJuice() {
/* 19 */     super(potionStrings.NAME, "Fruit Juice", PotionRarity.RARE, PotionSize.HEART, PotionColor.FRUIT);
/* 20 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 25 */     this.potency = getPotency();
/* 26 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 27 */     this.tips.clear();
/* 28 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 33 */     AbstractDungeon.player.increaseMaxHp(this.potency, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse() {
/* 38 */     if (AbstractDungeon.actionManager.turnHasEnded && 
/* 39 */       (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 40 */       return false;
/*    */     }
/* 42 */     if ((AbstractDungeon.getCurrRoom()).event != null && 
/* 43 */       (AbstractDungeon.getCurrRoom()).event instanceof com.megacrit.cardcrawl.events.shrines.WeMeetAgain) {
/* 44 */       return false;
/*    */     }
/*    */     
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 52 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 57 */     return new FruitJuice();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\FruitJuice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */