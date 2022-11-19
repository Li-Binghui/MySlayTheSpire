/*    */ package com.megacrit.cardcrawl.powers;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.SuicideAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
/*    */ 
/*    */ public class FadingPower extends AbstractPower {
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Fading"); public static final String POWER_ID = "Fading";
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public FadingPower(AbstractCreature owner, int turns) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = "Fading";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = turns;
/* 23 */     updateDescription();
/* 24 */     loadRegion("fading");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 29 */     if (this.amount == 1) {
/* 30 */       this.description = DESCRIPTIONS[2];
/*    */     } else {
/* 32 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void duringTurn() {
/* 38 */     if (this.amount == 1 && !this.owner.isDying) {
/* 39 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ExplosionSmallEffect(this.owner.hb.cX, this.owner.hb.cY), 0.1F));
/* 40 */       addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)this.owner));
/*    */     } else {
/* 42 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Fading", 1));
/* 43 */       updateDescription();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\FadingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */