/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
/*    */ import com.megacrit.cardcrawl.ui.campfire.RestOption;
/*    */ 
/*    */ public class CoffeeDripper extends AbstractRelic {
/*    */   public static final String ID = "Coffee Dripper";
/*    */   
/*    */   public CoffeeDripper() {
/* 13 */     super("Coffee Dripper", "coffeeDripper.png", RelicTier.BOSS, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     if (AbstractDungeon.player != null) {
/* 19 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 21 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 26 */     return this.DESCRIPTIONS[1] + this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 31 */     this.description = setDescription(c);
/* 32 */     this.tips.clear();
/* 33 */     this.tips.add(new PowerTip(this.name, this.description));
/* 34 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 39 */     AbstractDungeon.player.energy.energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 44 */     AbstractDungeon.player.energy.energyMaster--;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUseCampfireOption(AbstractCampfireOption option) {
/* 50 */     if (option instanceof RestOption && option.getClass().getName().equals(RestOption.class.getName())) {
/* 51 */       ((RestOption)option).updateUsability(false);
/* 52 */       return false;
/*    */     } 
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 59 */     return new CoffeeDripper();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\CoffeeDripper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */