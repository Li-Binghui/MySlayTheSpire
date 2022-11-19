/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class VelvetChoker
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Velvet Choker";
/*    */   private static final int PLAY_LIMIT = 6;
/*    */   
/*    */   public VelvetChoker() {
/* 15 */     super("Velvet Choker", "redChoker.png", RelicTier.BOSS, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     if (AbstractDungeon.player != null) {
/* 21 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 23 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 28 */     return this.DESCRIPTIONS[2] + this.DESCRIPTIONS[0] + '\006' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 33 */     this.description = setDescription(c);
/* 34 */     this.tips.clear();
/* 35 */     this.tips.add(new PowerTip(this.name, this.description));
/* 36 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 41 */     AbstractDungeon.player.energy.energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 46 */     AbstractDungeon.player.energy.energyMaster--;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 51 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 56 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayCard(AbstractCard card, AbstractMonster m) {
/* 61 */     if (this.counter < 6) {
/* 62 */       this.counter++;
/* 63 */       if (this.counter >= 6) {
/* 64 */         flash();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlay(AbstractCard card) {
/* 71 */     if (this.counter >= 6) {
/* 72 */       card.cantUseMessage = this.DESCRIPTIONS[3] + '\006' + this.DESCRIPTIONS[1];
/* 73 */       return false;
/*    */     } 
/* 75 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 80 */     this.counter = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 85 */     return new VelvetChoker();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\VelvetChoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */