/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
/*    */ 
/*    */ public class FrozenCore
/*    */   extends AbstractRelic {
/*    */   public FrozenCore() {
/* 10 */     super("FrozenCore", "frozenOrb.png", RelicTier.BOSS, LandingSound.CLINK);
/*    */   }
/*    */   public static final String ID = "FrozenCore";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayerEndTurn() {
/* 20 */     if (AbstractDungeon.player.hasEmptyOrb()) {
/* 21 */       flash();
/* 22 */       AbstractDungeon.player.channelOrb((AbstractOrb)new Frost());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 28 */     return AbstractDungeon.player.hasRelic("Cracked Core");
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new FrozenCore();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\FrozenCore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */