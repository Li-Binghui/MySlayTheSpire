/*    */ package com.megacrit.cardcrawl.orbs;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
/*    */ 
/*    */ public class EmptyOrbSlot extends AbstractOrb {
/*    */   public static final String ORB_ID = "Empty";
/* 13 */   private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("Empty");
/* 14 */   public static final String[] DESC = orbString.DESCRIPTION;
/*    */   
/*    */   public EmptyOrbSlot(float x, float y) {
/* 17 */     this.angle = MathUtils.random(360.0F);
/* 18 */     this.ID = "Empty";
/* 19 */     this.name = orbString.NAME;
/* 20 */     this.evokeAmount = 0;
/* 21 */     this.cX = x;
/* 22 */     this.cY = y;
/* 23 */     updateDescription();
/* 24 */     this.channelAnimTimer = 0.5F;
/*    */   }
/*    */   
/*    */   public EmptyOrbSlot() {
/* 28 */     this.angle = MathUtils.random(360.0F);
/* 29 */     this.name = orbString.NAME;
/* 30 */     this.evokeAmount = 0;
/* 31 */     this.cX = AbstractDungeon.player.drawX + AbstractDungeon.player.hb_x;
/* 32 */     this.cY = AbstractDungeon.player.drawY + AbstractDungeon.player.hb_y + AbstractDungeon.player.hb_h / 2.0F;
/* 33 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 38 */     this.description = DESC[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEvoke() {}
/*    */ 
/*    */   
/*    */   public void updateAnimation() {
/* 47 */     super.updateAnimation();
/* 48 */     this.angle += Gdx.graphics.getDeltaTime() * 10.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 53 */     sb.setColor(this.c);
/*    */     
/* 55 */     sb.draw(ImageMaster.ORB_SLOT_2, this.cX - 48.0F - this.bobEffect.y / 8.0F, this.cY - 48.0F + this.bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 72 */     sb.draw(ImageMaster.ORB_SLOT_1, this.cX - 48.0F + this.bobEffect.y / 8.0F, this.cY - 48.0F - this.bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 89 */     renderText(sb);
/* 90 */     this.hb.render(sb);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractOrb makeCopy() {
/* 95 */     return new EmptyOrbSlot();
/*    */   }
/*    */   
/*    */   public void playChannelSFX() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\orbs\EmptyOrbSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */