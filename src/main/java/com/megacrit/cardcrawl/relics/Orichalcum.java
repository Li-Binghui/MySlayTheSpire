/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class Orichalcum extends AbstractRelic {
/*    */   public static final String ID = "Orichalcum";
/*    */   
/*    */   public Orichalcum() {
/* 14 */     super("Orichalcum", "orichalcum.png", RelicTier.COMMON, LandingSound.HEAVY);
/*    */   }
/*    */   private static final int BLOCK_AMT = 6; public boolean trigger = false;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + '\006' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayerEndTurn() {
/* 24 */     if (AbstractDungeon.player.currentBlock == 0 || this.trigger) {
/* 25 */       this.trigger = false;
/* 26 */       flash();
/* 27 */       stopPulse();
/* 28 */       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 6));
/* 29 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 35 */     this.trigger = false;
/* 36 */     if (AbstractDungeon.player.currentBlock == 0) {
/* 37 */       beginLongPulse();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int onPlayerGainedBlock(float blockAmount) {
/* 43 */     if (blockAmount > 0.0F) {
/* 44 */       stopPulse();
/*    */     }
/*    */     
/* 47 */     return MathUtils.floor(blockAmount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 52 */     stopPulse();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 57 */     return new Orichalcum();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Orichalcum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */