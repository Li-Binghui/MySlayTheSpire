/*     */ package com.megacrit.cardcrawl.screens;
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.compendium.CardLibSortHeader;
import com.megacrit.cardcrawl.screens.mainMenu.SortHeaderButton;
import com.megacrit.cardcrawl.screens.mainMenu.SortHeaderButtonListener;

import java.util.Comparator;
/*     */ 
/*     */ public class MasterDeckSortHeader extends CardLibSortHeader {
/*     */   private static final int BAR_W = 1334;
/*     */   private static final int BAR_H = 102;
/*  16 */   private static final Color BAR_COLOR = new Color(0.4F, 0.4F, 0.4F, 1.0F);
/*  17 */   private static final Color IRONCLAD_COLOR = new Color(0.5F, 0.1F, 0.1F, 1.0F);
/*  18 */   private static final Color SILENT_COLOR = new Color(0.25F, 0.55F, 0.0F, 1.0F); private static final Comparator<AbstractCard> BY_TYPE; private static final Comparator<AbstractCard> ALPHA;
/*  19 */   private static final Color DEFECT_COLOR = new Color(0.01F, 0.34F, 0.52F, 1.0F); private static final Comparator<AbstractCard> BY_COST;
/*     */   static {
/*  21 */     BY_TYPE = ((a, b) -> (a.type.name() + a.name).compareTo(b.type.name() + b.name));
/*     */     
/*  23 */     ALPHA = ((a, b) -> a.name.compareTo(b.name));
/*  24 */     BY_COST = ((a, b) -> ("" + a.cost + a.name).compareTo("" + b.cost + b.name));
/*     */     
/*  26 */     PURE_REVERSE = ((a, b) -> a.cardID.equals(b.cardID) ? 0 : -1);
/*     */   }
/*     */   private static final Comparator<AbstractCard> PURE_REVERSE; private MasterDeckViewScreen masterDeckView;
/*     */   private float scrollY;
/*     */   
/*     */   public MasterDeckSortHeader(MasterDeckViewScreen masterDeckView) {
/*  32 */     super(null);
/*  33 */     this.masterDeckView = masterDeckView;
/*  34 */     this.buttons[0] = new SortHeaderButton(TEXT[5], START_X, 0.0F, (SortHeaderButtonListener)this);
/*  35 */     this.buttons[0].setActive(true);
/*     */ 
/*     */     
/*  38 */     float HB_W = (this.buttons[0]).hb.width;
/*     */ 
/*     */     
/*  41 */     float leftSideOffset = Settings.WIDTH / 2.0F - HB_W * this.buttons.length / 2.0F;
/*  42 */     for (int i = 0; i < this.buttons.length; i++) {
/*  43 */       SortHeaderButton button = this.buttons[i];
/*  44 */       button.hb.move(leftSideOffset + HB_W * i + HB_W / 2.0F, button.hb.cY);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void didChangeOrder(SortHeaderButton button, boolean isAscending) {
/*     */     Comparator<AbstractCard> order;
/*  50 */     button.setActive(true);
/*     */     
/*  52 */     if (button == this.buttons[0]) {
/*  53 */       if (isAscending) {
/*  54 */         this.masterDeckView.setSortOrder(null);
/*     */       } else {
/*  56 */         this.masterDeckView.setSortOrder(PURE_REVERSE);
/*     */       }  return;
/*     */     } 
/*  59 */     if (button == this.buttons[1]) {
/*  60 */       order = BY_TYPE;
/*  61 */     } else if (button == this.buttons[2]) {
/*  62 */       order = BY_COST;
/*  63 */     } else if (button == this.buttons[3]) {
/*  64 */       order = ALPHA;
/*     */     } else {
/*     */       return;
/*     */     } 
/*  68 */     if (!isAscending) {
/*  69 */       order = order.reversed();
/*     */     }
/*  71 */     this.masterDeckView.setSortOrder(order);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateScrollPositions() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  81 */     switch (AbstractDungeon.player.chosenClass) {
/*     */       case IRONCLAD:
/*  83 */         sb.setColor(IRONCLAD_COLOR);
/*     */         break;
/*     */       case THE_SILENT:
/*  86 */         sb.setColor(SILENT_COLOR);
/*     */         break;
/*     */       case DEFECT:
/*  89 */         sb.setColor(DEFECT_COLOR);
/*     */         break;
/*     */       default:
/*  92 */         sb.setColor(BAR_COLOR);
/*     */         break;
/*     */     } 
/*  95 */     sb.draw(ImageMaster.COLOR_TAB_BAR, Settings.WIDTH / 2.0F - 667.0F, this.scrollY - 51.0F, 667.0F, 51.0F, 1334.0F, 102.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1334, 102, false, false);
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
/*     */     
/* 113 */     super.render(sb);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScrollPosition(float y) {
/* 123 */     this.scrollY = y + 240.0F * Settings.scale;
/* 124 */     for (SortHeaderButton button : this.buttons)
/* 125 */       button.updateScrollPosition(this.scrollY); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\MasterDeckSortHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */