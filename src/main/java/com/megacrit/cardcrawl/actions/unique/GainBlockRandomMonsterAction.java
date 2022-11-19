/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class GainBlockRandomMonsterAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public GainBlockRandomMonsterAction(AbstractCreature source, int amount) {
/* 15 */     this.duration = 0.5F;
/* 16 */     this.source = source;
/* 17 */     this.amount = amount;
/* 18 */     this.actionType = ActionType.BLOCK;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     if (this.duration == 0.5F) {
/* 24 */       ArrayList<AbstractMonster> validMonsters = new ArrayList<>();
/* 25 */       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 26 */         if (m != this.source && m.intent != AbstractMonster.Intent.ESCAPE && !m.isDying) {
/* 27 */           validMonsters.add(m);
/*    */         }
/*    */       } 
/*    */       
/* 31 */       if (!validMonsters.isEmpty()) {
/* 32 */         this.target = (AbstractCreature)validMonsters.get(AbstractDungeon.aiRng.random(validMonsters.size() - 1));
/*    */       } else {
/* 34 */         this.target = this.source;
/*    */       } 
/*    */       
/* 37 */       if (this.target != null) {
/* 38 */         AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SHIELD));
/* 39 */         this.target.addBlock(this.amount);
/*    */       } 
/*    */     } 
/*    */     
/* 43 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\GainBlockRandomMonsterAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */