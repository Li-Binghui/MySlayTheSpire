/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.SmithOption;
/*    */ 
/*    */ public class FusionHammer extends AbstractRelic {
/*    */   public static final String ID = "Fusion Hammer";
/*    */   
/*    */   public FusionHammer() {
/* 13 */     super("Fusion Hammer", "burnHammer.png", RelicTier.BOSS, LandingSound.HEAVY);
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
/* 50 */     if (option instanceof SmithOption && option.getClass().getName().equals(SmithOption.class.getName())) {
/* 51 */       ((SmithOption)option).updateUsability(false);
/* 52 */       return false;
/*    */     } 
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 59 */     return new FusionHammer();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\FusionHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */