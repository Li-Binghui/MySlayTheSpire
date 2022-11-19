/*    */ package com.megacrit.cardcrawl.stances;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
/*    */ 
/*    */ public class NeutralStance extends AbstractStance {
/*    */   public static final String STANCE_ID = "Neutral";
/*  9 */   private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString("Neutral");
/*    */   
/*    */   public NeutralStance() {
/* 12 */     this.ID = "Neutral";
/* 13 */     this.img = null;
/* 14 */     this.name = null;
/* 15 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 20 */     this.description = stanceString.DESCRIPTION[0];
/*    */   }
/*    */   
/*    */   public void onEnterStance() {}
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\stances\NeutralStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */