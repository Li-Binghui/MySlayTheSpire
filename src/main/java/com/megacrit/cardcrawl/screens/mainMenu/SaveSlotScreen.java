/*     */ package com.megacrit.cardcrawl.screens.mainMenu;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.SaveHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputActionSet;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
/*     */ import com.megacrit.cardcrawl.ui.panels.DeleteSaveConfirmPopup;
/*     */ import com.megacrit.cardcrawl.ui.panels.RenamePopup;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SaveSlotScreen
/*     */ {
/*  26 */   private static final Logger logger = LogManager.getLogger(SaveSlotScreen.class.getName());
/*  27 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SaveSlotScreen");
/*  28 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public static boolean slotDeleted = false;
/*  31 */   private Color screenColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  32 */   public Color uiColor = new Color(1.0F, 0.965F, 0.886F, 0.0F);
/*     */   public boolean shown = false;
/*  34 */   public ArrayList<SaveSlot> slots = new ArrayList<>();
/*  35 */   public MenuCancelButton cancelButton = new MenuCancelButton();
/*     */ 
/*     */   
/*  38 */   private RenamePopup renamePopup = new RenamePopup();
/*  39 */   private DeleteSaveConfirmPopup deletePopup = new DeleteSaveConfirmPopup();
/*  40 */   public CurrentPopup curPop = CurrentPopup.NONE;
/*     */   
/*     */   public enum CurrentPopup {
/*  43 */     DELETE, RENAME, NONE;
/*     */   }
/*     */   
/*     */   public void update() {
/*  47 */     this.deletePopup.update();
/*  48 */     this.renamePopup.update();
/*     */     
/*  50 */     updateColors();
/*     */     
/*  52 */     switch (this.curPop) {
/*     */ 
/*     */       
/*     */       case NONE:
/*  56 */         if (this.shown) {
/*  57 */           updateSaveSlots();
/*  58 */           updateControllerInput();
/*     */         } 
/*     */         
/*  61 */         updateCancelButton();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateCancelButton() {
/*  71 */     this.cancelButton.update();
/*  72 */     if (this.cancelButton.hb.clicked || (!this.cancelButton.isHidden && InputActionSet.cancel.isJustPressed())) {
/*  73 */       this.cancelButton.hb.clicked = false;
/*  74 */       if (!((SaveSlot)this.slots.get(CardCrawlGame.saveSlot)).emptySlot) {
/*  75 */         confirm(CardCrawlGame.saveSlot);
/*     */       } else {
/*  77 */         for (int i = 0; i < 3; i++) {
/*  78 */           if (!((SaveSlot)this.slots.get(i)).emptySlot) {
/*  79 */             confirm(i);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/*  87 */     if (!Settings.isControllerMode || this.slots.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/*  91 */     boolean anyHovered = false;
/*     */     
/*  93 */     int index = 0;
/*  94 */     for (SaveSlot slot : this.slots) {
/*  95 */       if (slot.slotHb.hovered) {
/*  96 */         anyHovered = true;
/*     */         break;
/*     */       } 
/*  99 */       index++;
/*     */     } 
/*     */     
/* 102 */     if (!anyHovered) {
/* 103 */       CInputHelper.setCursor(((SaveSlot)this.slots.get(0)).slotHb);
/*     */     } else {
/* 105 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 106 */         index--;
/* 107 */         if (index < 0) {
/* 108 */           index = 2;
/*     */         }
/*     */       } else {
/* 111 */         index++;
/* 112 */         if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && index > 2) {
/* 113 */           index = 0;
/*     */         }
/*     */       } 
/*     */       
/* 117 */       CInputHelper.setCursor(((SaveSlot)this.slots.get(index)).slotHb);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateColors() {
/* 122 */     if (this.shown) {
/* 123 */       this.screenColor.a = MathHelper.fadeLerpSnap(this.screenColor.a, 0.75F);
/* 124 */       this.uiColor.a = MathHelper.fadeLerpSnap(this.uiColor.a, 1.0F);
/*     */     } else {
/* 126 */       this.screenColor.a = MathHelper.fadeLerpSnap(this.screenColor.a, 0.0F);
/* 127 */       this.uiColor.a = MathHelper.fadeLerpSnap(this.uiColor.a, 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateSaveSlots() {
/* 132 */     for (SaveSlot slot : this.slots) {
/* 133 */       slot.update();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void open(String curName) {
/* 140 */     if (this.slots.isEmpty()) {
/* 141 */       this.slots.add(new SaveSlot(0));
/* 142 */       this.slots.add(new SaveSlot(1));
/* 143 */       this.slots.add(new SaveSlot(2));
/* 144 */       SaveSlot.uiColor = this.uiColor;
/*     */     } 
/*     */     
/* 147 */     this.shown = true;
/* 148 */     for (SaveSlot s : this.slots) {
/* 149 */       if (!s.emptySlot) {
/* 150 */         this.cancelButton.show(CharacterSelectScreen.TEXT[5]);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rename(int slot, String name) {
/* 157 */     ((SaveSlot)this.slots.get(slot)).setName(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirm(int slot) {
/* 162 */     this.shown = false;
/* 163 */     CardCrawlGame.saveSlot = slot;
/* 164 */     CardCrawlGame.playerName = ((SaveSlot)this.slots.get(slot)).getName();
/* 165 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
/* 166 */     this.cancelButton.hide();
/*     */ 
/*     */     
/* 169 */     if (CardCrawlGame.saveSlotPref.getInteger("DEFAULT_SLOT", -1) != slot || slotDeleted) {
/* 170 */       logger.info("Default slot updated: " + slot);
/* 171 */       CardCrawlGame.saveSlotPref.putInteger("DEFAULT_SLOT", slot);
/* 172 */       CardCrawlGame.reloadPrefs();
/* 173 */       CardCrawlGame.saveSlotPref.flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 178 */     sb.setColor(this.screenColor);
/* 179 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/* 180 */     if (this.shown) {
/* 181 */       renderSaveSlots(sb);
/* 182 */       this.deletePopup.render(sb);
/* 183 */       this.renamePopup.render(sb);
/* 184 */       if (this.curPop == CurrentPopup.NONE) {
/* 185 */         this.cancelButton.render(sb);
/* 186 */         renderSelectSlotMessage(sb);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderSelectSlotMessage(SpriteBatch sb) {
/* 193 */     boolean showingTip = false;
/* 194 */     for (SaveSlot s : this.slots) {
/* 195 */       if (s.renameHb.hovered) {
/* 196 */         FontHelper.renderFontCentered(sb, FontHelper.topPanelAmountFont, TEXT[1], Settings.WIDTH / 2.0F, 80.0F * Settings.scale, Settings.BLUE_TEXT_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 203 */         showingTip = true;
/*     */         
/*     */         break;
/*     */       } 
/* 207 */       if (s.deleteHb.hovered) {
/* 208 */         FontHelper.renderFontCentered(sb, FontHelper.topPanelAmountFont, TEXT[2], Settings.WIDTH / 2.0F, 80.0F * Settings.scale, Settings.RED_TEXT_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 215 */         showingTip = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 220 */     if (!showingTip) {
/* 221 */       FontHelper.renderFontCentered(sb, FontHelper.topPanelAmountFont, TEXT[0], Settings.WIDTH / 2.0F, 80.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 230 */     if (Settings.isControllerMode) {
/* 231 */       sb.setColor(Color.WHITE);
/* 232 */       sb.draw(CInputActionSet.select
/* 233 */           .getKeyImg(), Settings.WIDTH / 2.0F - 
/* 234 */           FontHelper.getSmartWidth(FontHelper.topPanelAmountFont, TEXT[0], 99999.0F, 0.0F) / 2.0F - 32.0F - 48.0F * Settings.scale, 80.0F * Settings.scale - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderSaveSlots(SpriteBatch sb) {
/* 254 */     sb.setColor(this.uiColor);
/* 255 */     for (SaveSlot slot : this.slots) {
/* 256 */       slot.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   public void openDeletePopup(int index) {
/* 261 */     this.deletePopup.open(index);
/* 262 */     this.curPop = CurrentPopup.DELETE;
/*     */   }
/*     */   
/*     */   public void deleteSlot(int index) {
/* 266 */     CardCrawlGame.saveSlotPref.putString(SaveHelper.slotName("PROFILE_NAME", index), "");
/* 267 */     ((SaveSlot)this.slots.get(index)).clear();
/*     */   }
/*     */   
/*     */   public void openRenamePopup(int index, boolean newSave) {
/* 271 */     this.renamePopup.open(index, newSave);
/* 272 */     this.curPop = CurrentPopup.RENAME;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\SaveSlotScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */