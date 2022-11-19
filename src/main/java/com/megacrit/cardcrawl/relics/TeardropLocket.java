/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class TeardropLocket
/*    */   extends AbstractRelic {
/*    */   public TeardropLocket() {
/* 12 */     super("TeardropLocket", "tear_drop_locket.png", RelicTier.UNCOMMON, LandingSound.CLINK);
/*    */   }
/*    */   public static final String ID = "TeardropLocket";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 22 */     flash();
/* 23 */     addToTop((AbstractGameAction)new ChangeStanceAction("Calm"));
/* 24 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new TeardropLocket();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\TeardropLocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */