/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReviveMonsterAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.SnakeDagger;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class SpawnDaggerAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public static final float pos0X = 210.0F;
/*    */   public static final float pos0Y = 50.0F;
/*    */   public static final float pos1X = -220.0F;
/*    */   public static final float pos1Y = 90.0F;
/*    */   
/*    */   public SpawnDaggerAction(AbstractMonster monster) {
/* 29 */     this.source = (AbstractCreature)monster;
/* 30 */     this.duration = Settings.ACTION_DUR_XFAST;
/*    */   }
/*    */   private static final float pos2X = 180.0F; private static final float pos2Y = 320.0F; private static final float pos3X = -250.0F; private static final float pos3Y = 310.0F;
/*    */   
/*    */   public void update() {
/* 35 */     if (this.duration == Settings.ACTION_DUR_XFAST) {
/* 36 */       int count = 0;
/* 37 */       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 38 */         if (m != this.source) {
/* 39 */           if (m.isDying) {
/* 40 */             addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new MinionPower(this.source)));
/* 41 */             addToTop((AbstractGameAction)new ReviveMonsterAction(m, this.source, false));
/*    */             
/* 43 */             if (AbstractDungeon.player.hasRelic("Philosopher's Stone")) {
/* 44 */               m.addPower((AbstractPower)new StrengthPower((AbstractCreature)m, 1));
/* 45 */               AbstractDungeon.onModifyPower();
/*    */             } 
/* 47 */             if (ModHelper.isModEnabled("Lethality")) {
/* 48 */               addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new StrengthPower((AbstractCreature)m, 3), 3));
/*    */             }
/*    */             
/* 51 */             if (ModHelper.isModEnabled("Time Dilation")) {
/* 52 */               addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new SlowPower((AbstractCreature)m, 0)));
/*    */             }
/* 54 */             tickDuration();
/*    */             return;
/*    */           } 
/* 57 */           count++;
/*    */         } 
/*    */       } 
/*    */       
/* 61 */       if (count == 1) {
/* 62 */         addToTop((AbstractGameAction)new SpawnMonsterAction((AbstractMonster)new SnakeDagger(-220.0F, 90.0F), true));
/* 63 */       } else if (count == 2) {
/* 64 */         addToTop((AbstractGameAction)new SpawnMonsterAction((AbstractMonster)new SnakeDagger(180.0F, 320.0F), true));
/* 65 */       } else if (count == 3) {
/* 66 */         addToTop((AbstractGameAction)new SpawnMonsterAction((AbstractMonster)new SnakeDagger(-250.0F, 310.0F), true));
/*    */       } 
/*    */     } 
/* 69 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\SpawnDaggerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */