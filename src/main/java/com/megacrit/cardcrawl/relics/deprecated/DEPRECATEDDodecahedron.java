/*    */ package com.megacrit.cardcrawl.relics.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ public class DEPRECATEDDodecahedron extends AbstractRelic {
/*    */   public static final String ID = "Dodecahedron";
/*    */   private static final int ENERGY_AMT = 1;
/*    */   
/*    */   public DEPRECATEDDodecahedron() {
/* 18 */     super("Dodecahedron", "dodecahedron.png", RelicTier.UNCOMMON, LandingSound.HEAVY);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     if (AbstractDungeon.player != null) {
/* 24 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 26 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 31 */     return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 36 */     this.description = setDescription(c);
/* 37 */     this.tips.clear();
/* 38 */     this.tips.add(new PowerTip(this.name, this.description));
/* 39 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 44 */     controlPulse();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 49 */     stopPulse();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 58 */     addToBot(new AbstractGameAction()
/*    */         {
/*    */           public void update() {
/* 61 */             if (DEPRECATEDDodecahedron.this.isActive()) {
/* 62 */               DEPRECATEDDodecahedron.this.flash();
/* 63 */               addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, DEPRECATEDDodecahedron.this));
/* 64 */               addToBot((AbstractGameAction)new GainEnergyAction(1));
/*    */             } 
/* 66 */             this.isDone = true;
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public int onPlayerHeal(int healAmount) {
/* 73 */     controlPulse();
/* 74 */     return super.onPlayerHeal(healAmount);
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 79 */     if (damageAmount > 0) {
/* 80 */       stopPulse();
/*    */     }
/* 82 */     return super.onAttacked(info, damageAmount);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 87 */     return new DEPRECATEDDodecahedron();
/*    */   }
/*    */   
/*    */   private boolean isActive() {
/* 91 */     return (AbstractDungeon.player.currentHealth >= AbstractDungeon.player.maxHealth);
/*    */   }
/*    */   
/*    */   private void controlPulse() {
/* 95 */     if (isActive()) {
/* 96 */       beginLongPulse();
/*    */     } else {
/* 98 */       stopPulse();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\deprecated\DEPRECATEDDodecahedron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */