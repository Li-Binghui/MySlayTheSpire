/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class BlessingOfTheForge extends AbstractPotion {
/* 15 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("BlessingOfTheForge"); public static final String POTION_ID = "BlessingOfTheForge";
/*    */   
/*    */   public BlessingOfTheForge() {
/* 18 */     super(potionStrings.NAME, "BlessingOfTheForge", PotionRarity.COMMON, PotionSize.ANVIL, PotionColor.FIRE);
/* 19 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 24 */     this.potency = getPotency();
/* 25 */     this.description = potionStrings.DESCRIPTIONS[0];
/* 26 */     this.tips.clear();
/* 27 */     this.tips.add(new PowerTip(this.name, this.description));
/* 28 */     this.tips.add(new PowerTip(
/*    */           
/* 30 */           TipHelper.capitalize(GameDictionary.UPGRADE.NAMES[0]), (String)GameDictionary.keywords
/* 31 */           .get(GameDictionary.UPGRADE.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 36 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 37 */       addToBot((AbstractGameAction)new ArmamentsAction(true));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 43 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 48 */     return new BlessingOfTheForge();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\BlessingOfTheForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */