/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class PanachePower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Panache";
/* 16 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Panache");
/* 17 */   public static final String NAME = powerStrings.NAME;
/* 18 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   public static final int CARD_AMT = 5;
/*    */   private int damage;
/*    */   
/*    */   public PanachePower(AbstractCreature owner, int damage) {
/* 23 */     this.name = NAME;
/* 24 */     this.ID = "Panache";
/* 25 */     this.owner = owner;
/* 26 */     this.amount = 5;
/* 27 */     this.damage = damage;
/* 28 */     updateDescription();
/* 29 */     loadRegion("panache");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 34 */     if (this.amount == 1) {
/* 35 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.damage + DESCRIPTIONS[2];
/*    */     } else {
/* 37 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3] + this.damage + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 43 */     this.fontScale = 8.0F;
/* 44 */     this.damage += stackAmount;
/* 45 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 50 */     this.amount--;
/* 51 */     if (this.amount == 0) {
/* 52 */       flash();
/* 53 */       this.amount = 5;
/* 54 */       addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)AbstractDungeon.player, 
/*    */ 
/*    */             
/* 57 */             DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*    */     } 
/*    */ 
/*    */     
/* 61 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 66 */     this.amount = 5;
/* 67 */     updateDescription();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\PanachePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */