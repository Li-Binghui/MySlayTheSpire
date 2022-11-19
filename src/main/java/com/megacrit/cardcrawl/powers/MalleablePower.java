/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MalleablePower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Malleable";
/*    */   
/*    */   public MalleablePower(AbstractCreature owner) {
/* 18 */     this(owner, 3);
/*    */   }
/*    */   
/*    */   public MalleablePower(AbstractCreature owner, int amt) {
/* 22 */     this.name = NAME;
/* 23 */     this.ID = "Malleable";
/* 24 */     this.owner = owner;
/* 25 */     this.amount = amt;
/* 26 */     this.basePower = amt;
/* 27 */     updateDescription();
/* 28 */     loadRegion("malleable");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 33 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + NAME + DESCRIPTIONS[2] + this.basePower + DESCRIPTIONS[3];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 40 */     if (this.owner.isPlayer)
/*    */       return; 
/* 42 */     this.amount = this.basePower;
/* 43 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 48 */     if (!this.owner.isPlayer)
/*    */       return; 
/* 50 */     this.amount = this.basePower;
/* 51 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 56 */     if (damageAmount < this.owner.currentHealth && damageAmount > 0 && info.owner != null && info.type == DamageInfo.DamageType.NORMAL && info.type != DamageInfo.DamageType.HP_LOSS) {
/*    */       
/* 58 */       flash();
/* 59 */       if (this.owner.isPlayer) {
/* 60 */         addToTop((AbstractGameAction)new GainBlockAction(this.owner, this.owner, this.amount));
/*    */       } else {
/* 62 */         addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.owner, this.amount));
/*    */       } 
/* 64 */       this.amount++;
/* 65 */       updateDescription();
/*    */     } 
/* 67 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 72 */     this.amount += stackAmount;
/* 73 */     this.basePower += stackAmount;
/*    */   }
/*    */ 
/*    */   
/* 77 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Malleable");
/* 78 */   public static final String NAME = powerStrings.NAME;
/* 79 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private static final int STARTING_BLOCK = 3;
/*    */   private int basePower;
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\MalleablePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */