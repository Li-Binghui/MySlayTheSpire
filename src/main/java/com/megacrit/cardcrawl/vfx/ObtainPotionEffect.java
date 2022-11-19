/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.potions.AbstractPotion;
/*    */ 
/*    */ public class ObtainPotionEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private AbstractPotion potion;
/*    */   
/*    */   public ObtainPotionEffect(AbstractPotion potion) {
/* 13 */     this.potion = potion;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 18 */     if (!AbstractDungeon.player.hasRelic("Sozu")) {
/* 19 */       AbstractDungeon.player.obtainPotion(this.potion);
/*    */     }
/* 21 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch spriteBatch) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\ObtainPotionEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */