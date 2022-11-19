/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
/*    */ 
/*    */ public class OfferingEffect extends AbstractGameEffect {
/* 13 */   private int count = 0;
/* 14 */   private float timer = 0.0F;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     this.timer -= Gdx.graphics.getDeltaTime();
/* 21 */     if (this.timer < 0.0F) {
/* 22 */       this.timer += 0.3F;
/*    */       
/* 24 */       switch (this.count) {
/*    */         case 0:
/* 26 */           CardCrawlGame.sound.playA("ATTACK_FIRE", -0.5F);
/* 27 */           CardCrawlGame.sound.playA("BLOOD_SPLAT", -0.75F);
/* 28 */           AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(new Color(1.0F, 0.1F, 0.1F, 1.0F)));
/* 29 */           AbstractDungeon.effectsQueue.add(new WaterDropEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY + 250.0F * Settings.scale));
/*    */           break;
/*    */ 
/*    */ 
/*    */         
/*    */         case 1:
/* 35 */           AbstractDungeon.effectsQueue.add(new WaterDropEffect(AbstractDungeon.player.hb.cX + 150.0F * Settings.scale, AbstractDungeon.player.hb.cY - 80.0F * Settings.scale));
/*    */           break;
/*    */ 
/*    */ 
/*    */         
/*    */         case 2:
/* 41 */           AbstractDungeon.effectsQueue.add(new WaterDropEffect(AbstractDungeon.player.hb.cX - 200.0F * Settings.scale, AbstractDungeon.player.hb.cY + 50.0F * Settings.scale));
/*    */           break;
/*    */ 
/*    */ 
/*    */         
/*    */         case 3:
/* 47 */           AbstractDungeon.effectsQueue.add(new WaterDropEffect(AbstractDungeon.player.hb.cX + 200.0F * Settings.scale, AbstractDungeon.player.hb.cY + 50.0F * Settings.scale));
/*    */           break;
/*    */ 
/*    */ 
/*    */         
/*    */         case 4:
/* 53 */           AbstractDungeon.effectsQueue.add(new WaterDropEffect(AbstractDungeon.player.hb.cX - 150.0F * Settings.scale, AbstractDungeon.player.hb.cY - 80.0F * Settings.scale));
/*    */           break;
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 62 */       this.count++;
/*    */       
/* 64 */       if (this.count == 6)
/* 65 */         this.isDone = true; 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\OfferingEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */