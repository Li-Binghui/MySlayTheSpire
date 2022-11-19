/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class MeatOnTheBone extends AbstractRelic {
/*    */   public static final String ID = "Meat on the Bone";
/*    */   
/*    */   public MeatOnTheBone() {
/* 14 */     super("Meat on the Bone", "meat.png", RelicTier.UNCOMMON, LandingSound.HEAVY);
/*    */   }
/*    */   private static final int HEAL_AMT = 12;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + '\f' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTrigger() {
/* 24 */     AbstractPlayer p = AbstractDungeon.player;
/* 25 */     if (p.currentHealth <= p.maxHealth / 2.0F && p.currentHealth > 0) {
/* 26 */       flash();
/* 27 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 28 */       p.heal(12);
/* 29 */       stopPulse();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBloodied() {
/* 35 */     flash();
/* 36 */     this.pulse = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onNotBloodied() {
/* 41 */     stopPulse();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 46 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 48);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 51 */     return new MeatOnTheBone();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\MeatOnTheBone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */