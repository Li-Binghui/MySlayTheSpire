/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class SlaversCollar extends AbstractRelic {
/*    */   public static final String ID = "SlaversCollar";
/*    */   
/*    */   public SlaversCollar() {
/* 12 */     super("SlaversCollar", "collar.png", RelicTier.BOSS, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     if (AbstractDungeon.player != null) {
/* 18 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 20 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 25 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 30 */     this.description = setDescription(c);
/* 31 */     this.tips.clear();
/* 32 */     this.tips.add(new PowerTip(this.name, this.description));
/* 33 */     initializeTips();
/*    */   }
/*    */   
/*    */   public void beforeEnergyPrep() {
/* 37 */     boolean isEliteOrBoss = (AbstractDungeon.getCurrRoom()).eliteTrigger;
/* 38 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 39 */       if (m.type == AbstractMonster.EnemyType.BOSS) {
/* 40 */         isEliteOrBoss = true;
/*    */       }
/*    */     } 
/*    */     
/* 44 */     if (isEliteOrBoss) {
/* 45 */       beginLongPulse();
/* 46 */       flash();
/* 47 */       AbstractDungeon.player.energy.energyMaster++;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 53 */     if (this.pulse) {
/* 54 */       AbstractDungeon.player.energy.energyMaster--;
/* 55 */       stopPulse();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 61 */     return new SlaversCollar();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\SlaversCollar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */