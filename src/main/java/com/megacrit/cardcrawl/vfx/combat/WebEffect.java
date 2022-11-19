/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class WebEffect extends AbstractGameEffect {
/* 11 */   private float timer = 0.0F;
/* 12 */   private int count = 0;
/*    */   
/*    */   private AbstractCreature target;
/*    */   
/*    */   public WebEffect(AbstractCreature target, float srcX, float srcY) {
/* 17 */     this.target = target;
/* 18 */     this.srcX = srcX;
/* 19 */     this.srcY = srcY;
/* 20 */     this.duration = 1.0F;
/*    */   }
/*    */   private float srcX; private float srcY;
/*    */   public void update() {
/* 24 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 25 */     this.timer -= Gdx.graphics.getDeltaTime();
/*    */     
/* 27 */     if (this.timer < 0.0F) {
/* 28 */       this.timer += 0.1F;
/*    */       
/* 30 */       switch (this.count) {
/*    */         case 0:
/* 32 */           AbstractDungeon.effectsQueue.add(new WebLineEffect(this.srcX, this.srcY, true));
/* 33 */           AbstractDungeon.effectsQueue.add(new WebLineEffect(this.srcX, this.srcY, true));
/* 34 */           AbstractDungeon.effectsQueue.add(new WebParticleEffect(this.target.hb.cX - 90.0F * Settings.scale, this.target.hb.cY - 10.0F * Settings.scale));
/*    */           break;
/*    */ 
/*    */ 
/*    */         
/*    */         case 1:
/* 40 */           AbstractDungeon.effectsQueue.add(new WebLineEffect(this.srcX, this.srcY, true));
/* 41 */           AbstractDungeon.effectsQueue.add(new WebLineEffect(this.srcX, this.srcY, true));
/*    */           break;
/*    */         case 2:
/* 44 */           AbstractDungeon.effectsQueue.add(new WebLineEffect(this.srcX, this.srcY, true));
/* 45 */           AbstractDungeon.effectsQueue.add(new WebLineEffect(this.srcX, this.srcY, true));
/* 46 */           AbstractDungeon.effectsQueue.add(new WebParticleEffect(this.target.hb.cX + 70.0F * Settings.scale, this.target.hb.cY + 80.0F * Settings.scale));
/*    */           break;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/*    */         case 4:
/* 54 */           AbstractDungeon.effectsQueue.add(new WebParticleEffect(this.target.hb.cX + 30.0F * Settings.scale, this.target.hb.cY - 100.0F * Settings.scale));
/*    */           break;
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 63 */       this.count++;
/*    */     } 
/*    */     
/* 66 */     if (this.duration < 0.0F)
/* 67 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\WebEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */