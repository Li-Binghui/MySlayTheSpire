/*    */ package com.megacrit.cardcrawl.rewards.chests;
/*    */ 
/*    */ import com.megacrit.cardcrawl.blights.AbstractBlight;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.BlightHelper;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class BossChest
/*    */   extends AbstractChest
/*    */ {
/* 19 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("BossChest");
/* 20 */   public static final String[] TEXT = uiStrings.TEXT;
/* 21 */   public ArrayList<AbstractRelic> relics = new ArrayList<>();
/* 22 */   public ArrayList<AbstractBlight> blights = new ArrayList<>();
/*    */   
/*    */   public BossChest() {
/* 25 */     this.img = ImageMaster.BOSS_CHEST;
/* 26 */     this.openedImg = ImageMaster.BOSS_CHEST_OPEN;
/*    */     
/* 28 */     this.hb = new Hitbox(256.0F * Settings.scale, 200.0F * Settings.scale);
/* 29 */     this.hb.move(CHEST_LOC_X, CHEST_LOC_Y - 100.0F * Settings.scale);
/*    */     
/* 31 */     if (AbstractDungeon.actNum < 4 || !AbstractPlayer.customMods.contains("Blight Chests")) {
/*    */       
/* 33 */       this.relics.clear();
/* 34 */       for (int i = 0; i < 3; i++) {
/* 35 */         this.relics.add(AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.BOSS));
/*    */       }
/*    */     }
/*    */     else {
/*    */       
/* 40 */       this.blights.clear();
/* 41 */       this.blights.add(BlightHelper.getRandomBlight());
/* 42 */       ArrayList<String> exclusion = new ArrayList<>();
/* 43 */       exclusion.add(((AbstractBlight)this.blights.get(0)).blightID);
/* 44 */       this.blights.add(BlightHelper.getRandomChestBlight(exclusion));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void open(boolean bossChest) {
/* 51 */     if (AbstractDungeon.actNum < 4 || !AbstractPlayer.customMods.contains("Blight Chests")) {
/* 52 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 53 */         if (!(r instanceof com.megacrit.cardcrawl.relics.Matryoshka)) {
/* 54 */           r.onChestOpen(true);
/*    */         }
/*    */       } 
/*    */       
/* 58 */       AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[0]);
/* 59 */       CardCrawlGame.sound.play("CHEST_OPEN");
/* 60 */       AbstractDungeon.bossRelicScreen.open(this.relics);
/*    */     } else {
/* 62 */       CardCrawlGame.sound.play("CHEST_OPEN");
/* 63 */       AbstractDungeon.bossRelicScreen.openBlight(this.blights);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 69 */     CardCrawlGame.sound.play("CHEST_OPEN");
/* 70 */     this.isOpen = false;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rewards\chests\BossChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */