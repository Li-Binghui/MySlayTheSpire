/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReaperEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public void update() {
/* 16 */     CardCrawlGame.sound.playA("ORB_LIGHTNING_EVOKE", 0.9F);
/* 17 */     CardCrawlGame.sound.playA("ORB_LIGHTNING_PASSIVE", -0.3F);
/* 18 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 19 */       if (!m.isDeadOrEscaped()) {
/* 20 */         AbstractDungeon.effectsQueue.add(new LightningEffect(m.hb.cX, m.hb.cY));
/*    */       }
/*    */     } 
/*    */     
/* 24 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\ReaperEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */