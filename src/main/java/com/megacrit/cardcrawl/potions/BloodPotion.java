/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.HealAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class BloodPotion
/*    */   extends AbstractPotion {
/*    */   public static final String POTION_ID = "BloodPotion";
/* 16 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("BloodPotion");
/*    */   
/*    */   public BloodPotion() {
/* 19 */     super(potionStrings.NAME, "BloodPotion", PotionRarity.COMMON, PotionSize.H, PotionColor.WHITE);
/* 20 */     this.labOutlineColor = Settings.RED_RELIC_COLOR;
/* 21 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 26 */     this.potency = getPotency();
/* 27 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 28 */     this.tips.clear();
/* 29 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 34 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 35 */       addToBot((AbstractGameAction)new HealAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (int)(AbstractDungeon.player.maxHealth * this.potency / 100.0F)));
/*    */     
/*    */     }
/*    */     else {
/*    */ 
/*    */       
/* 41 */       AbstractDungeon.player.heal((int)(AbstractDungeon.player.maxHealth * this.potency / 100.0F));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse() {
/* 47 */     if (AbstractDungeon.actionManager.turnHasEnded && 
/* 48 */       (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 49 */       return false;
/*    */     }
/* 51 */     if ((AbstractDungeon.getCurrRoom()).event != null && 
/* 52 */       (AbstractDungeon.getCurrRoom()).event instanceof com.megacrit.cardcrawl.events.shrines.WeMeetAgain) {
/* 53 */       return false;
/*    */     }
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 61 */     return 20;
/*    */   }
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 65 */     return new BloodPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\BloodPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */