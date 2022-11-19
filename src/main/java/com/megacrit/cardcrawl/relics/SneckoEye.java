/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.ConfusionPower;
/*    */ 
/*    */ public class SneckoEye extends AbstractRelic {
/*    */   public static final String ID = "Snecko Eye";
/*    */   
/*    */   public SneckoEye() {
/* 12 */     super("Snecko Eye", "sneckoEye.png", RelicTier.BOSS, LandingSound.FLAT);
/*    */   }
/*    */   public static final int HAND_MODIFICATION = 2;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 22 */     AbstractDungeon.player.masterHandSize += 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 27 */     AbstractDungeon.player.masterHandSize -= 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atPreBattle() {
/* 32 */     flash();
/* 33 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new ConfusionPower((AbstractCreature)AbstractDungeon.player)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 42 */     return new SneckoEye();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\SneckoEye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */