/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.PoisonPower;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class TheSpecimen extends AbstractRelic {
/* 14 */   private static final Logger logger = LogManager.getLogger(TheSpecimen.class.getName());
/*    */   public static final String ID = "The Specimen";
/*    */   
/*    */   public TheSpecimen() {
/* 18 */     super("The Specimen", "the_specimen.png", RelicTier.RARE, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onMonsterDeath(AbstractMonster m) {
/* 28 */     if (m.hasPower("Poison")) {
/* 29 */       int amount = (m.getPower("Poison")).amount;
/*    */       
/* 31 */       if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 32 */         flash();
/*    */         
/* 34 */         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)m, this));
/* 35 */         addToBot((AbstractGameAction)new ApplyPowerToRandomEnemyAction((AbstractCreature)AbstractDungeon.player, (AbstractPower)new PoisonPower(null, (AbstractCreature)AbstractDungeon.player, amount), amount, false, AbstractGameAction.AttackEffect.POISON));
/*    */ 
/*    */       
/*    */       }
/*    */       else {
/*    */ 
/*    */ 
/*    */         
/* 43 */         logger.info("no target for the specimen");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 50 */     return new TheSpecimen();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\TheSpecimen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */