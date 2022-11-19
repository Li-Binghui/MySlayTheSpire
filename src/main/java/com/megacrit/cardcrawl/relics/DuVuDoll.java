/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ 
/*    */ public class DuVuDoll extends AbstractRelic {
/*    */   public static final String ID = "Du-Vu Doll";
/*    */   
/*    */   public DuVuDoll() {
/* 17 */     super("Du-Vu Doll", "duvuDoll.png", RelicTier.RARE, LandingSound.MAGICAL);
/*    */   }
/*    */   private static final int AMT = 1;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCounter(int c) {
/* 27 */     this.counter = c;
/* 28 */     if (this.counter == 0) {
/* 29 */       this.description = this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
/*    */     } else {
/* 31 */       this.description = this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[3] + this.counter + this.DESCRIPTIONS[4];
/*    */     } 
/*    */     
/* 34 */     this.tips.clear();
/* 35 */     this.tips.add(new PowerTip(this.name, this.description));
/* 36 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onMasterDeckChange() {
/* 41 */     this.counter = 0;
/* 42 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/* 43 */       if (c.type == AbstractCard.CardType.CURSE) {
/* 44 */         this.counter++;
/*    */       }
/*    */     } 
/*    */     
/* 48 */     if (this.counter == 0) {
/* 49 */       this.description = this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
/*    */     } else {
/* 51 */       this.description = this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[3] + this.counter + this.DESCRIPTIONS[4];
/*    */     } 
/*    */     
/* 54 */     this.tips.clear();
/* 55 */     this.tips.add(new PowerTip(this.name, this.description));
/* 56 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 61 */     this.counter = 0;
/* 62 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/* 63 */       if (c.type == AbstractCard.CardType.CURSE) {
/* 64 */         this.counter++;
/*    */       }
/*    */     } 
/*    */     
/* 68 */     if (this.counter == 0) {
/* 69 */       this.description = this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
/*    */     } else {
/* 71 */       this.description = this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[3] + this.counter + this.DESCRIPTIONS[4];
/*    */     } 
/*    */     
/* 74 */     this.tips.clear();
/* 75 */     this.tips.add(new PowerTip(this.name, this.description));
/* 76 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 81 */     if (this.counter > 0) {
/* 82 */       flash();
/* 83 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, this.counter), this.counter));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 89 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 95 */     return new DuVuDoll();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\DuVuDoll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */