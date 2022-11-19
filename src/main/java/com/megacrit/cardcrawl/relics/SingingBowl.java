/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ 
/*    */ public class SingingBowl extends AbstractRelic {
/*    */   public static final String ID = "Singing Bowl";
/*    */   public static final int HP_AMT = 2;
/*    */   
/*    */   public SingingBowl() {
/* 14 */     super("Singing Bowl", "singingBowl.png", RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     super.update();
/*    */     
/* 26 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 27 */       CardCrawlGame.sound.playA("SINGING_BOWL", MathUtils.random(-0.2F, 0.1F));
/* 28 */       flash();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 34 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 48);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 39 */     return new SingingBowl();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\SingingBowl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */