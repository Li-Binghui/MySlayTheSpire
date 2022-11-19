/*    */ package com.megacrit.cardcrawl.rooms;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
import com.megacrit.cardcrawl.vfx.ChestShineEffect;
import com.megacrit.cardcrawl.vfx.scene.SpookyChestEffect;
/*    */ 
/*    */ public class TreasureRoom extends AbstractRoom {
/* 15 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TreasureRoom");
/* 16 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   public AbstractChest chest;
/*    */   
/* 20 */   private float shinyTimer = 0.0F;
/*    */   
/*    */   private static final float SHINY_INTERVAL = 0.2F;
/*    */   
/*    */   public TreasureRoom() {
/* 25 */     this.phase = RoomPhase.COMPLETE;
/* 26 */     this.mapSymbol = "T";
/* 27 */     this.mapImg = ImageMaster.MAP_NODE_TREASURE;
/* 28 */     this.mapImgOutline = ImageMaster.MAP_NODE_TREASURE_OUTLINE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayerEntry() {
/* 33 */     playBGM(null);
/* 34 */     this.chest = AbstractDungeon.getRandomChest();
/* 35 */     AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 41 */     super.update();
/* 42 */     if (this.chest != null) {
/* 43 */       this.chest.update();
/*    */     }
/* 45 */     updateShiny();
/*    */   }
/*    */   
/*    */   private void updateShiny() {
/* 49 */     if (!this.chest.isOpen) {
/* 50 */       this.shinyTimer -= Gdx.graphics.getDeltaTime();
/* 51 */       if (this.shinyTimer < 0.0F && !Settings.DISABLE_EFFECTS) {
/* 52 */         this.shinyTimer = 0.2F;
/* 53 */         AbstractDungeon.topLevelEffects.add(new ChestShineEffect());
/* 54 */         AbstractDungeon.effectList.add(new SpookyChestEffect());
/* 55 */         AbstractDungeon.effectList.add(new SpookyChestEffect());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderAboveTopPanel(SpriteBatch sb) {
/* 62 */     super.renderAboveTopPanel(sb);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 68 */     if (this.chest != null) {
/* 69 */       this.chest.render(sb);
/*    */     }
/* 71 */     super.render(sb);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\TreasureRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */