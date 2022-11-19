/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class Elixir extends AbstractPotion {
/* 16 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("ElixirPotion"); public static final String POTION_ID = "ElixirPotion";
/*    */   
/*    */   public Elixir() {
/* 19 */     super(potionStrings.NAME, "ElixirPotion", PotionRarity.UNCOMMON, PotionSize.T, PotionColor.WHITE);
/* 20 */     this.labOutlineColor = Settings.RED_RELIC_COLOR;
/* 21 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 26 */     this.potency = getPotency();
/* 27 */     this.description = potionStrings.DESCRIPTIONS[0];
/* 28 */     this.tips.clear();
/* 29 */     this.tips.add(new PowerTip(this.name, this.description));
/* 30 */     this.tips.add(new PowerTip(
/*    */           
/* 32 */           TipHelper.capitalize(GameDictionary.EXHAUST.NAMES[0]), (String)GameDictionary.keywords
/* 33 */           .get(GameDictionary.EXHAUST.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 38 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 39 */       addToBot((AbstractGameAction)new ExhaustAction(false, true, true));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 45 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 50 */     return new Elixir();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\Elixir.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */