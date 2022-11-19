/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class GremlinHorn extends AbstractRelic {
/*    */   public GremlinHorn() {
/* 15 */     super("Gremlin Horn", "gremlin_horn.png", RelicTier.UNCOMMON, LandingSound.HEAVY);
/* 16 */     this.energyBased = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     if (AbstractDungeon.player != null) {
/* 22 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 24 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */   public static final String ID = "Gremlin Horn";
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 29 */     return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 34 */     this.description = setDescription(c);
/* 35 */     this.tips.clear();
/* 36 */     this.tips.add(new PowerTip(this.name, this.description));
/* 37 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onMonsterDeath(AbstractMonster m) {
/* 42 */     if (m.currentHealth == 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 43 */       flash();
/* 44 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)m, this));
/* 45 */       addToBot((AbstractGameAction)new GainEnergyAction(1));
/* 46 */       addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 52 */     return new GremlinHorn();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\GremlinHorn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */