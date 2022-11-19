/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ 
/*    */ public class Ectoplasm extends AbstractRelic {
/*    */   public static final String ID = "Ectoplasm";
/*    */   
/*    */   public Ectoplasm() {
/* 11 */     super("Ectoplasm", "ectoplasm.png", RelicTier.BOSS, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     if (AbstractDungeon.player != null) {
/* 17 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 19 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 24 */     return this.DESCRIPTIONS[1] + this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 29 */     this.description = setDescription(c);
/* 30 */     this.tips.clear();
/* 31 */     this.tips.add(new PowerTip(this.name, this.description));
/* 32 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 37 */     AbstractDungeon.player.energy.energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 42 */     AbstractDungeon.player.energy.energyMaster--;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 47 */     if (AbstractDungeon.actNum > 1) {
/* 48 */       return false;
/*    */     }
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 55 */     return new Ectoplasm();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Ectoplasm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */