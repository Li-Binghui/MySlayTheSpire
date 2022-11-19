/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.DuplicationPower;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class DuplicationPotion extends AbstractPotion {
/* 16 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("DuplicationPotion"); public static final String POTION_ID = "DuplicationPotion";
/*    */   
/*    */   public DuplicationPotion() {
/* 19 */     super(potionStrings.NAME, "DuplicationPotion", PotionRarity.UNCOMMON, PotionSize.CARD, PotionEffect.RAINBOW, Color.WHITE, null, null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 33 */     this.potency = getPotency();
/* 34 */     if (AbstractDungeon.player == null || !AbstractDungeon.player.hasRelic("SacredBark")) {
/* 35 */       this.description = potionStrings.DESCRIPTIONS[0];
/*    */     } else {
/* 37 */       this.description = potionStrings.DESCRIPTIONS[1];
/*    */     } 
/* 39 */     this.tips.clear();
/* 40 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 46 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 47 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DuplicationPower((AbstractCreature)AbstractDungeon.player, this.potency), this.potency));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 58 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 63 */     return new DuplicationPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\DuplicationPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */