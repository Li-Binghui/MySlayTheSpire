/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ 
/*    */ public class Tingsha extends AbstractRelic {
/*    */   public static final String ID = "Tingsha";
/*    */   private static final int DMG_AMT = 3;
/*    */   
/*    */   public Tingsha() {
/* 18 */     super("Tingsha", "tingsha.png", RelicTier.RARE, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onManualDiscard() {
/* 28 */     flash();
/* 29 */     CardCrawlGame.sound.play("TINGSHA");
/* 30 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 31 */     addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo((AbstractCreature)AbstractDungeon.player, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 39 */     super.update();
/*    */     
/* 41 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 42 */       CardCrawlGame.sound.playA("TINGSHA", MathUtils.random(-0.2F, 0.1F));
/* 43 */       flash();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 49 */     return new Tingsha();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Tingsha.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */