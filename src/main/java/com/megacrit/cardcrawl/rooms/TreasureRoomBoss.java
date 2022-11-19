/*    */ package com.megacrit.cardcrawl.rooms;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
import com.megacrit.cardcrawl.rewards.chests.BossChest;
import com.megacrit.cardcrawl.vfx.scene.SpookierChestEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TreasureRoomBoss
/*    */   extends AbstractRoom
/*    */ {
/* 20 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TreasureRoomBoss");
/* 21 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   public AbstractChest chest;
/*    */   
/* 25 */   private float shinyTimer = 0.0F;
/*    */   
/*    */   private static final float SHINY_INTERVAL = 0.02F;
/*    */   public boolean choseRelic = false;
/*    */   
/*    */   public TreasureRoomBoss() {
/* 31 */     CardCrawlGame.nextDungeon = getNextDungeonName();
/* 32 */     if (AbstractDungeon.actNum < 4 || !AbstractPlayer.customMods.contains("Blight Chests")) {
/* 33 */       this.phase = RoomPhase.COMPLETE;
/*    */     } else {
/* 35 */       this.phase = RoomPhase.INCOMPLETE;
/*    */     } 
/* 37 */     this.mapImg = ImageMaster.MAP_NODE_TREASURE;
/* 38 */     this.mapImgOutline = ImageMaster.MAP_NODE_TREASURE_OUTLINE;
/*    */   }
/*    */   
/*    */   private String getNextDungeonName() {
/* 42 */     switch (AbstractDungeon.id) {
/*    */       case "Exordium":
/* 44 */         return "TheCity";
/*    */       case "TheCity":
/* 46 */         return "TheBeyond";
/*    */       case "TheBeyond":
/* 48 */         if (Settings.isEndless) {
/* 49 */           return "Exordium";
/*    */         }
/* 51 */         return null;
/*    */     } 
/* 53 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onPlayerEntry() {
/* 59 */     CardCrawlGame.music.silenceBGM();
/* 60 */     if (AbstractDungeon.actNum < 4 || !AbstractPlayer.customMods.contains("Blight Chests")) {
/* 61 */       AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[0]);
/*    */     }
/* 63 */     playBGM("SHRINE");
/* 64 */     this.chest = (AbstractChest)new BossChest();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 70 */     super.update();
/* 71 */     this.chest.update();
/* 72 */     updateShiny();
/*    */   }
/*    */   
/*    */   private void updateShiny() {
/* 76 */     if (!this.chest.isOpen) {
/* 77 */       this.shinyTimer -= Gdx.graphics.getDeltaTime();
/* 78 */       if (this.shinyTimer < 0.0F && !Settings.DISABLE_EFFECTS) {
/* 79 */         this.shinyTimer = 0.02F;
/* 80 */         AbstractDungeon.effectList.add(new SpookierChestEffect());
/* 81 */         AbstractDungeon.effectList.add(new SpookierChestEffect());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderAboveTopPanel(SpriteBatch sb) {
/* 88 */     super.renderAboveTopPanel(sb);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 94 */     this.chest.render(sb);
/* 95 */     super.render(sb);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\TreasureRoomBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */