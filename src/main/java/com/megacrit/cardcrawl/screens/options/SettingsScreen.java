/*    */ package com.megacrit.cardcrawl.screens.options;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SettingsScreen
/*    */ {
/* 14 */   private static final Logger logger = LogManager.getLogger(SettingsScreen.class.getName());
/* 15 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SettingsScreen");
/* 16 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/* 18 */   public OptionsPanel panel = new OptionsPanel();
/* 19 */   public ConfirmPopup exitPopup = new ConfirmPopup(TEXT[0], TEXT[1], ConfirmPopup.ConfirmType.EXIT);
/* 20 */   public ConfirmPopup abandonPopup = new ConfirmPopup(TEXT[0], TEXT[2], ConfirmPopup.ConfirmType.ABANDON_MID_RUN);
/*    */   
/* 22 */   private static final String NOT_SAVED_MSG = TEXT[3];
/*    */   
/*    */   public void update() {
/* 25 */     if (!this.exitPopup.shown && !this.abandonPopup.shown) {
/* 26 */       this.panel.update();
/*    */     }
/* 28 */     this.exitPopup.update();
/* 29 */     this.abandonPopup.update();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void open() {
/* 36 */     open(true);
/*    */   }
/*    */   
/*    */   public void open(boolean animated) {
/* 40 */     AbstractDungeon.player.releaseCard();
/* 41 */     this.panel.refresh();
/* 42 */     if (animated) {
/* 43 */       AbstractDungeon.overlayMenu.cancelButton.show(TEXT[4]);
/*    */     } else {
/* 45 */       AbstractDungeon.overlayMenu.cancelButton.showInstantly(TEXT[4]);
/*    */     } 
/* 47 */     CardCrawlGame.sound.play("UI_CLICK_1");
/* 48 */     AbstractDungeon.isScreenUp = true;
/* 49 */     AbstractDungeon.overlayMenu.showBlackScreen();
/* 50 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 51 */     AbstractDungeon.overlayMenu.hideCombatPanels();
/* 52 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.SETTINGS;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 61 */     this.panel.render(sb);
/* 62 */     this.exitPopup.render(sb);
/* 63 */     this.abandonPopup.render(sb);
/*    */   }
/*    */   
/*    */   public void popup(ConfirmPopup.ConfirmType type) {
/* 67 */     if (AbstractDungeon.overlayMenu != null) {
/* 68 */       AbstractDungeon.overlayMenu.cancelButton.hide();
/*    */     }
/*    */     
/* 71 */     switch (type) {
/*    */       case ABANDON_MID_RUN:
/* 73 */         this.abandonPopup.show();
/*    */         return;
/*    */       case EXIT:
/* 76 */         this.exitPopup.desc = TEXT[1];
/* 77 */         if (AbstractDungeon.player != null && !AbstractDungeon.player.saveFileExists()) {
/* 78 */           this.exitPopup.desc = NOT_SAVED_MSG;
/*    */         }
/* 80 */         this.exitPopup.show();
/*    */         return;
/*    */     } 
/* 83 */     logger.info("Unspecified case: " + type.name());
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\SettingsScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */