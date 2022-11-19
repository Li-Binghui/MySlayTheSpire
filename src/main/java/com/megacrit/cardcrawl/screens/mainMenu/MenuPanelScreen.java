/*     */ package com.megacrit.cardcrawl.screens.mainMenu;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.integrations.DistributorFactory;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
/*     */ import com.megacrit.cardcrawl.screens.stats.StatsScreen;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MenuPanelScreen
/*     */ {
/*  19 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("MenuPanels");
/*  20 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  22 */   public ArrayList<MainMenuPanelButton> panels = new ArrayList<>();
/*     */   private PanelScreen screen;

    private static final float PANEL_Y = Settings.HEIGHT / 2.0F;
/*  25 */   public MenuCancelButton button = new MenuCancelButton();
/*     */   
/*     */   public enum PanelScreen {
/*  28 */     PLAY, COMPENDIUM, STATS, SETTINGS;
/*     */   }
/*     */   
/*     */   public void open(PanelScreen screenType) {
/*  32 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.PANEL_MENU;
/*  33 */     this.screen = screenType;
/*  34 */     initializePanels();
/*  35 */     this.button.show(CharacterSelectScreen.TEXT[5]);
/*  36 */     CardCrawlGame.mainMenuScreen.darken();
/*     */   }
/*     */ 
/*     */   
/*     */   public void hide() {}
/*     */   
/*     */   private void initializePanels() {
/*     */     float offset;
/*  44 */     this.panels.clear();
/*  45 */     switch (this.screen) {
/*     */       case PLAY:
/*  47 */         this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.PLAY_NORMAL, MainMenuPanelButton.PanelColor.BLUE, Settings.WIDTH / 2.0F - 450.0F * Settings.scale, PANEL_Y));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  54 */         if (CardCrawlGame.mainMenuScreen.statsScreen.statScreenUnlocked()) {
/*  55 */           this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.PLAY_DAILY, MainMenuPanelButton.PanelColor.BEIGE, Settings.WIDTH / 2.0F, PANEL_Y));
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/*  62 */           this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.PLAY_DAILY, MainMenuPanelButton.PanelColor.GRAY, Settings.WIDTH / 2.0F, PANEL_Y));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  70 */         if (StatsScreen.all.highestDaily > 0) {
/*  71 */           this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.PLAY_CUSTOM, MainMenuPanelButton.PanelColor.RED, Settings.WIDTH / 2.0F + 450.0F * Settings.scale, PANEL_Y));
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */ 
/*     */         
/*  78 */         this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.PLAY_CUSTOM, MainMenuPanelButton.PanelColor.GRAY, Settings.WIDTH / 2.0F + 450.0F * Settings.scale, PANEL_Y));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case COMPENDIUM:
/*  87 */         this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.INFO_CARD, MainMenuPanelButton.PanelColor.BLUE, Settings.WIDTH / 2.0F - 450.0F * Settings.scale, PANEL_Y));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  93 */         this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.INFO_RELIC, MainMenuPanelButton.PanelColor.BEIGE, Settings.WIDTH / 2.0F, PANEL_Y));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  99 */         this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.INFO_POTION, MainMenuPanelButton.PanelColor.RED, Settings.WIDTH / 2.0F + 450.0F * Settings.scale, PANEL_Y));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case STATS:
/* 107 */         offset = 225.0F;
/* 108 */         if (DistributorFactory.isLeaderboardEnabled()) {
/* 109 */           offset = 450.0F;
/*     */         }
/* 111 */         this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.STAT_CHAR, MainMenuPanelButton.PanelColor.BLUE, Settings.WIDTH / 2.0F - offset * Settings.scale, PANEL_Y));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 117 */         if (DistributorFactory.isLeaderboardEnabled()) {
/* 118 */           this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.STAT_LEADERBOARDS, MainMenuPanelButton.PanelColor.BEIGE, Settings.WIDTH / 2.0F, PANEL_Y));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 125 */         this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.STAT_HISTORY, MainMenuPanelButton.PanelColor.RED, Settings.WIDTH / 2.0F + offset * Settings.scale, PANEL_Y));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case SETTINGS:
/* 133 */         this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.SETTINGS_GAME, MainMenuPanelButton.PanelColor.BLUE, Settings.WIDTH / 2.0F - 450.0F * Settings.scale, PANEL_Y));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 139 */         this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.SETTINGS_INPUT, MainMenuPanelButton.PanelColor.BLUE, Settings.WIDTH / 2.0F, PANEL_Y));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 145 */         this.panels.add(new MainMenuPanelButton(MainMenuPanelButton.PanelClickResult.SETTINGS_CREDITS, MainMenuPanelButton.PanelColor.BLUE, Settings.WIDTH / 2.0F + 450.0F * Settings.scale, PANEL_Y));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 158 */     this.button.update();
/*     */     
/* 160 */     if (this.button.hb.clicked || InputHelper.pressedEscape) {
/* 161 */       InputHelper.pressedEscape = false;
/* 162 */       CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
/* 163 */       this.button.hide();
/* 164 */       CardCrawlGame.mainMenuScreen.lighten();
/*     */     } 
/*     */     
/* 167 */     for (MainMenuPanelButton panel : this.panels) {
/* 168 */       panel.update();
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 173 */     for (MainMenuPanelButton panel : this.panels) {
/* 174 */       panel.render(sb);
/*     */     }
/*     */     
/* 177 */     this.button.render(sb);
/*     */   }
/*     */   
/*     */   public void refresh() {
/* 181 */     this.button.hideInstantly();
/* 182 */     this.button.show(CharacterSelectScreen.TEXT[5]);
/* 183 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.PANEL_MENU;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\MenuPanelScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */