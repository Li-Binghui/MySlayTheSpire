/*    */ package com.megacrit.cardcrawl.relics;
/*    */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
/*    */ 
/*    */ public class Damaru extends AbstractRelic {
/*    */   public Damaru() {
/* 15 */     super("Damaru", "damaru.png", RelicTier.COMMON, LandingSound.SOLID);
/*    */   }
/*    */   public static final String ID = "Damaru";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 25 */     flash();
/* 26 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 27 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, null, (AbstractPower)new MantraPower((AbstractCreature)AbstractDungeon.player, 1), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 32 */     super.update();
/*    */     
/* 34 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 35 */       CardCrawlGame.sound.playA("DAMARU", MathUtils.random(-0.2F, 0.1F));
/* 36 */       flash();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 42 */     return new Damaru();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Damaru.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */