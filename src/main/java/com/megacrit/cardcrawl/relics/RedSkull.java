/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class RedSkull
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Red Skull";
/*    */   
/*    */   public RedSkull() {
/* 18 */     super("Red Skull", "red_skull.png", RelicTier.COMMON, LandingSound.FLAT);
/*    */   }
/*    */   private static final int STR_AMT = 3; private boolean isActive = false;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 28 */     this.isActive = false;
/* 29 */     addToBot(new AbstractGameAction()
/*    */         {
/*    */           public void update() {
/* 32 */             if (!RedSkull.this.isActive && AbstractDungeon.player.isBloodied) {
/* 33 */               RedSkull.this.flash();
/* 34 */               RedSkull.this.pulse = true;
/* 35 */               AbstractDungeon.player.addPower((AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, 3));
/* 36 */               addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, RedSkull.this));
/* 37 */               RedSkull.this.isActive = true;
/* 38 */               AbstractDungeon.onModifyPower();
/*    */             } 
/* 40 */             this.isDone = true;
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onBloodied() {
/* 48 */     flash();
/* 49 */     this.pulse = true;
/* 50 */     if (!this.isActive && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 51 */       AbstractPlayer p = AbstractDungeon.player;
/* 52 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, 3), 3));
/* 53 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 54 */       this.isActive = true;
/* 55 */       AbstractDungeon.player.hand.applyPowers();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onNotBloodied() {
/* 61 */     if (this.isActive && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 62 */       AbstractPlayer p = AbstractDungeon.player;
/* 63 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, -3), -3));
/*    */     } 
/* 65 */     stopPulse();
/* 66 */     this.isActive = false;
/* 67 */     AbstractDungeon.player.hand.applyPowers();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 72 */     this.pulse = false;
/* 73 */     this.isActive = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 78 */     return new RedSkull();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\RedSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */