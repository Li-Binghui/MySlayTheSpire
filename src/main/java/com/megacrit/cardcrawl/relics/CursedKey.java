/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ 
/*    */ public class CursedKey
/*    */   extends AbstractRelic {
/*    */   public CursedKey() {
/* 16 */     super("Cursed Key", "cursedKey.png", RelicTier.BOSS, LandingSound.CLINK);
/*    */   }
/*    */   public static final String ID = "Cursed Key";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     if (AbstractDungeon.player != null) {
/* 22 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 24 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void justEnteredRoom(AbstractRoom room) {
/* 30 */     if (room instanceof com.megacrit.cardcrawl.rooms.TreasureRoom) {
/* 31 */       flash();
/* 32 */       this.pulse = true;
/*    */     } else {
/* 34 */       this.pulse = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 39 */     return this.DESCRIPTIONS[1] + this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onChestOpen(boolean bossChest) {
/* 44 */     if (!bossChest) {
/* 45 */       AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(
/*    */             
/* 47 */             AbstractDungeon.returnRandomCurse(), (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*    */ 
/*    */       
/* 50 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 56 */     this.description = setDescription(c);
/* 57 */     this.tips.clear();
/* 58 */     this.tips.add(new PowerTip(this.name, this.description));
/* 59 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 64 */     AbstractDungeon.player.energy.energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 69 */     AbstractDungeon.player.energy.energyMaster--;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 74 */     return new CursedKey();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\CursedKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */