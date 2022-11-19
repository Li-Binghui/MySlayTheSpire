/*    */ package com.megacrit.cardcrawl.potions;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class EssenceOfSteel extends AbstractPotion {
/* 15 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("EssenceOfSteel");
/*    */   
/*    */   public static final String POTION_ID = "EssenceOfSteel";
/*    */ 
/*    */   
/*    */   public EssenceOfSteel() {
/* 21 */     super(potionStrings.NAME, "EssenceOfSteel", PotionRarity.UNCOMMON, PotionSize.ANVIL, PotionEffect.NONE, Color.TEAL, null, null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 35 */     this.potency = getPotency();
/* 36 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 37 */     this.tips.clear();
/* 38 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 43 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 44 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 45 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new PlatedArmorPower((AbstractCreature)abstractPlayer, this.potency), this.potency));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 52 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 57 */     return new EssenceOfSteel();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\EssenceOfSteel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */