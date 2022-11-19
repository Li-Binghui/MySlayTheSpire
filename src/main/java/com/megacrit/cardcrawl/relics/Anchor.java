/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class Anchor extends AbstractRelic {
/*    */   public static final String ID = "Anchor";
/*    */   
/*    */   public Anchor() {
/* 13 */     super("Anchor", "anchor.png", RelicTier.COMMON, LandingSound.HEAVY);
/*    */   }
/*    */   private static final int BLOCK_AMT = 10;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\n' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 23 */     flash();
/* 24 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 25 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 10));
/* 26 */     this.grayscale = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void justEnteredRoom(AbstractRoom room) {
/* 31 */     this.grayscale = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 36 */     return new Anchor();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Anchor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */