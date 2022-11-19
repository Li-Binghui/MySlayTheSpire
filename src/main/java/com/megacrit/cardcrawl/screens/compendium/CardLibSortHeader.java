/*     */ package com.megacrit.cardcrawl.screens.compendium;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.SortHeaderButton;
import com.megacrit.cardcrawl.screens.mainMenu.SortHeaderButtonListener;
/*     */ 
/*     */ public class CardLibSortHeader implements SortHeaderButtonListener {
/*  17 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CardLibSortHeader");
/*  18 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public CardGroup group;
/*     */   public boolean justSorted = false;
/*  22 */   public static final float START_X = 430.0F * Settings.xScale;
/*  23 */   public static final float SPACE_X = 226.0F * Settings.xScale;
/*     */   
/*     */   private SortHeaderButton rarityButton;
/*     */   private SortHeaderButton typeButton;
/*     */   private SortHeaderButton costButton;
/*     */   private SortHeaderButton nameButton;
/*     */   public SortHeaderButton[] buttons;
/*  30 */   public int selectionIndex = -1;
/*     */   
/*     */   private static Texture img;
/*  33 */   private Color selectionColor = new Color(1.0F, 0.95F, 0.5F, 0.0F);
/*     */   
/*     */   public CardLibSortHeader(CardGroup group) {
/*  36 */     if (img == null) {
/*  37 */       img = ImageMaster.loadImage("images/ui/cardlibrary/selectBox.png");
/*     */     }
/*     */     
/*  40 */     this.group = group;
/*     */     
/*  42 */     float xPosition = START_X;
/*  43 */     this.rarityButton = new SortHeaderButton(TEXT[0], xPosition, 0.0F, this);
/*  44 */     xPosition += SPACE_X;
/*  45 */     this.typeButton = new SortHeaderButton(TEXT[1], xPosition, 0.0F, this);
/*  46 */     xPosition += SPACE_X;
/*  47 */     this.costButton = new SortHeaderButton(TEXT[3], xPosition, 0.0F, this);
/*     */     
/*  49 */     if (!Settings.removeAtoZSort) {
/*  50 */       xPosition += SPACE_X;
/*  51 */       this.nameButton = new SortHeaderButton(TEXT[2], xPosition, 0.0F, this);
/*  52 */       this.buttons = new SortHeaderButton[] { this.rarityButton, this.typeButton, this.costButton, this.nameButton };
/*     */     } else {
/*  54 */       this.buttons = new SortHeaderButton[] { this.rarityButton, this.typeButton, this.costButton };
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setGroup(CardGroup group) {
/*  59 */     this.group = group;
/*  60 */     group.sortAlphabetically(true);
/*  61 */     group.sortByRarity(true);
/*  62 */     group.sortByStatus(true);
/*     */     
/*  64 */     for (SortHeaderButton button : this.buttons) {
/*  65 */       button.reset();
/*     */     }
/*     */   }
/*     */   
/*     */   public void update() {
/*  70 */     for (SortHeaderButton button : this.buttons) {
/*  71 */       button.update();
/*     */     }
/*     */   }
/*     */   
/*     */   public Hitbox updateControllerInput() {
/*  76 */     for (SortHeaderButton button : this.buttons) {
/*  77 */       if (button.hb.hovered) {
/*  78 */         return button.hb;
/*     */       }
/*     */     } 
/*  81 */     return null;
/*     */   }
/*     */   
/*     */   public int getHoveredIndex() {
/*  85 */     int retVal = 0;
/*  86 */     for (SortHeaderButton button : this.buttons) {
/*  87 */       if (button.hb.hovered) {
/*  88 */         return retVal;
/*     */       }
/*  90 */       retVal++;
/*     */     } 
/*  92 */     return 0;
/*     */   }
/*     */   
/*     */   public void clearActiveButtons() {
/*  96 */     for (SortHeaderButton button : this.buttons) {
/*  97 */       button.setActive(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void didChangeOrder(SortHeaderButton button, boolean isAscending) {
/* 103 */     if (button == this.rarityButton) {
/* 104 */       this.group.sortByRarity(isAscending);
/* 105 */     } else if (button == this.typeButton) {
/* 106 */       this.group.sortByType(isAscending);
/* 107 */     } else if (button == this.nameButton) {
/* 108 */       this.group.sortAlphabetically(isAscending);
/* 109 */     } else if (button == this.costButton) {
/* 110 */       this.group.sortByCost(isAscending);
/*     */     } else {
/*     */       return;
/*     */     } 
/* 114 */     this.group.sortByStatus(false);
/* 115 */     this.justSorted = true;
/* 116 */     button.setActive(true);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 120 */     updateScrollPositions();
/* 121 */     renderButtons(sb);
/* 122 */     renderSelection(sb);
/*     */   }
/*     */   
/*     */   protected void updateScrollPositions() {
/* 126 */     float scrolledY = (this.group.getBottomCard()).current_y + 230.0F * Settings.yScale;
/* 127 */     for (SortHeaderButton button : this.buttons) {
/* 128 */       button.updateScrollPosition(scrolledY);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void renderButtons(SpriteBatch sb) {
/* 133 */     for (SortHeaderButton b : this.buttons) {
/* 134 */       b.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void renderSelection(SpriteBatch sb) {
/* 139 */     for (int i = 0; i < this.buttons.length; i++) {
/* 140 */       if (i == this.selectionIndex) {
/* 141 */         this.selectionColor.a = 0.7F + MathUtils.cosDeg((float)(System.currentTimeMillis() / 2L % 360L)) / 5.0F;
/* 142 */         sb.setColor(this.selectionColor);
/* 143 */         float doop = 1.0F + (1.0F + MathUtils.cosDeg((float)(System.currentTimeMillis() / 2L % 360L))) / 50.0F;
/*     */         
/* 145 */         sb.draw(img, (this.buttons[this.selectionIndex]).hb.cX - 80.0F - (this.buttons[this.selectionIndex]).textWidth / 2.0F * Settings.scale, (this.buttons[this.selectionIndex]).hb.cY - 43.0F, 100.0F, 43.0F, 160.0F + (this.buttons[this.selectionIndex]).textWidth, 86.0F, Settings.scale * doop, Settings.scale * doop, 0.0F, 0, 0, 200, 86, false, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\compendium\CardLibSortHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */