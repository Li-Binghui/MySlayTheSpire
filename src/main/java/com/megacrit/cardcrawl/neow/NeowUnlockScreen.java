/*     */ package com.megacrit.cardcrawl.neow;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.buttons.UnlockConfirmButton;
import com.megacrit.cardcrawl.unlock.AbstractUnlock;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.ConeEffect;
import com.megacrit.cardcrawl.vfx.RoomShineEffect;
import com.megacrit.cardcrawl.vfx.RoomShineEffect2;

import java.util.ArrayList;
import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NeowUnlockScreen
/*     */ {
/*     */   public ArrayList<AbstractUnlock> unlockBundle;
/*  30 */   private ArrayList<ConeEffect> cones = new ArrayList<>();
/*     */   
/*     */   private static final int CONE_AMT = 30;
/*  33 */   private float shinyTimer = 0.0F;
/*     */   private static final float SHINY_INTERVAL = 0.2F;
/*  35 */   public UnlockConfirmButton button = new UnlockConfirmButton();
/*     */   
/*     */   public long id;
/*     */   
/*  39 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("UnlockScreen");
/*  40 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public void open(ArrayList<AbstractUnlock> unlock, boolean isVictory) {
/*  43 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.NEOW_UNLOCK;
/*  44 */     this.unlockBundle = unlock;
/*  45 */     this.button.show();
/*  46 */     this.id = CardCrawlGame.sound.play("UNLOCK_SCREEN");
/*     */     
/*  48 */     this.cones.clear(); int i;
/*  49 */     for (i = 0; i < 30; i++) {
/*  50 */       this.cones.add(new ConeEffect());
/*     */     }
/*     */     
/*  53 */     switch (((AbstractUnlock)this.unlockBundle.get(0)).type) {
/*     */       case CARD:
/*  55 */         for (i = 0; i < this.unlockBundle.size(); i++) {
/*  56 */           UnlockTracker.unlockCard(((AbstractUnlock)this.unlockBundle.get(i)).card.cardID);
/*  57 */           AbstractDungeon.dynamicBanner.appearInstantly(TEXT[0]);
/*  58 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.targetDrawScale = 1.0F;
/*  59 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.drawScale = 0.01F;
/*  60 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.current_x = Settings.WIDTH * 0.25F * (i + 1);
/*  61 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.current_y = Settings.HEIGHT / 2.0F;
/*  62 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.target_x = Settings.WIDTH * 0.25F * (i + 1);
/*  63 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.target_y = Settings.HEIGHT / 2.0F - 30.0F * Settings.scale;
/*     */         } 
/*     */         break;
/*     */       case RELIC:
/*  67 */         for (i = 0; i < this.unlockBundle.size(); i++) {
/*  68 */           UnlockTracker.hardUnlockOverride(((AbstractUnlock)this.unlockBundle.get(i)).relic.relicId);
/*  69 */           UnlockTracker.markRelicAsSeen(((AbstractUnlock)this.unlockBundle.get(i)).relic.relicId);
/*  70 */           ((AbstractUnlock)this.unlockBundle.get(i)).relic.loadLargeImg();
/*  71 */           AbstractDungeon.dynamicBanner.appearInstantly(TEXT[1]);
/*  72 */           ((AbstractUnlock)this.unlockBundle.get(i)).relic.currentX = Settings.WIDTH * 0.25F * (i + 1);
/*  73 */           ((AbstractUnlock)this.unlockBundle.get(i)).relic.currentY = Settings.HEIGHT / 2.0F;
/*  74 */           ((AbstractUnlock)this.unlockBundle.get(i)).relic.hb.move(
/*  75 */               ((AbstractUnlock)this.unlockBundle.get(i)).relic.currentX, 
/*  76 */               ((AbstractUnlock)this.unlockBundle.get(i)).relic.currentY);
/*     */         } 
/*     */         break;
/*     */       case CHARACTER:
/*  80 */         ((AbstractUnlock)this.unlockBundle.get(0)).onUnlockScreenOpen();
/*  81 */         AbstractDungeon.dynamicBanner.appearInstantly(TEXT[2]);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reOpen() {
/*  91 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.NEOW_UNLOCK;
/*  92 */     this.button.show();
/*  93 */     this.id = CardCrawlGame.sound.play("UNLOCK_SCREEN");
/*     */     
/*  95 */     this.cones.clear(); int i;
/*  96 */     for (i = 0; i < 30; i++) {
/*  97 */       this.cones.add(new ConeEffect());
/*     */     }
/*     */     
/* 100 */     switch (((AbstractUnlock)this.unlockBundle.get(0)).type) {
/*     */       case CARD:
/* 102 */         for (i = 0; i < this.unlockBundle.size(); i++) {
/* 103 */           UnlockTracker.unlockCard(((AbstractUnlock)this.unlockBundle.get(i)).card.cardID);
/* 104 */           AbstractDungeon.dynamicBanner.appearInstantly(TEXT[0]);
/* 105 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.targetDrawScale = 1.0F;
/* 106 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.drawScale = 0.01F;
/* 107 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.current_x = Settings.WIDTH * 0.25F * (i + 1);
/* 108 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.current_y = Settings.HEIGHT / 2.0F;
/* 109 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.target_x = Settings.WIDTH * 0.25F * (i + 1);
/* 110 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.target_y = Settings.HEIGHT / 2.0F - 30.0F * Settings.scale;
/*     */         } 
/*     */         break;
/*     */       case RELIC:
/* 114 */         for (i = 0; i < this.unlockBundle.size(); i++) {
/* 115 */           AbstractDungeon.dynamicBanner.appearInstantly(TEXT[1]);
/* 116 */           ((AbstractUnlock)this.unlockBundle.get(i)).relic.currentX = Settings.WIDTH * 0.25F * (i + 1);
/* 117 */           ((AbstractUnlock)this.unlockBundle.get(i)).relic.currentY = Settings.HEIGHT / 2.0F;
/* 118 */           ((AbstractUnlock)this.unlockBundle.get(i)).relic.hb.move(
/* 119 */               ((AbstractUnlock)this.unlockBundle.get(i)).relic.currentX, 
/* 120 */               ((AbstractUnlock)this.unlockBundle.get(i)).relic.currentY);
/*     */         } 
/*     */         break;
/*     */       case CHARACTER:
/* 124 */         ((AbstractUnlock)this.unlockBundle.get(0)).onUnlockScreenOpen();
/* 125 */         AbstractDungeon.dynamicBanner.appearInstantly(TEXT[2]);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*     */     int i;
/* 135 */     this.shinyTimer -= Gdx.graphics.getDeltaTime();
/* 136 */     if (this.shinyTimer < 0.0F) {
/* 137 */       this.shinyTimer = 0.2F;
/* 138 */       AbstractDungeon.topLevelEffects.add(new RoomShineEffect());
/* 139 */       AbstractDungeon.topLevelEffects.add(new RoomShineEffect());
/* 140 */       AbstractDungeon.topLevelEffects.add(new RoomShineEffect2());
/*     */     } 
/*     */     
/* 143 */     switch (((AbstractUnlock)this.unlockBundle.get(0)).type) {
/*     */       case CARD:
/* 145 */         updateConeEffect();
/* 146 */         for (i = 0; i < this.unlockBundle.size(); i++) {
/* 147 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.update();
/* 148 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.updateHoverLogic();
/* 149 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.targetDrawScale = 1.0F;
/*     */         } 
/*     */         break;
/*     */       case RELIC:
/* 153 */         updateConeEffect();
/* 154 */         for (i = 0; i < this.unlockBundle.size(); i++) {
/* 155 */           ((AbstractUnlock)this.unlockBundle.get(i)).relic.update();
/*     */         }
/*     */         break;
/*     */       case CHARACTER:
/* 159 */         updateConeEffect();
/* 160 */         ((AbstractUnlock)this.unlockBundle.get(0)).player.update();
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 166 */     this.button.update();
/*     */   }
/*     */   
/*     */   private void updateConeEffect() {
/* 170 */     for (Iterator<ConeEffect> e = this.cones.iterator(); e.hasNext(); ) {
/* 171 */       ConeEffect d = e.next();
/* 172 */       d.update();
/* 173 */       if (d.isDone) {
/* 174 */         e.remove();
/*     */       }
/*     */     } 
/*     */     
/* 178 */     if (this.cones.size() < 30)
/* 179 */       this.cones.add(new ConeEffect()); 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*     */     int i;
/* 184 */     sb.setColor(new Color(0.05F, 0.15F, 0.18F, 1.0F));
/* 185 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     
/* 187 */     sb.setBlendFunction(770, 1);
/* 188 */     for (ConeEffect e : this.cones) {
/* 189 */       e.render(sb);
/*     */     }
/* 191 */     sb.setBlendFunction(770, 771);
/*     */     
/* 193 */     switch (((AbstractUnlock)this.unlockBundle.get(0)).type) {
/*     */       case CARD:
/* 195 */         for (i = 0; i < this.unlockBundle.size(); i++) {
/* 196 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.renderHoverShadow(sb);
/* 197 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.render(sb);
/* 198 */           ((AbstractUnlock)this.unlockBundle.get(i)).card.renderCardTip(sb);
/*     */         } 
/*     */         
/* 201 */         sb.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
/* 202 */         sb.draw(ImageMaster.UNLOCK_TEXT_BG, Settings.WIDTH / 2.0F - 500.0F, Settings.HEIGHT / 2.0F - 330.0F * Settings.scale - 130.0F, 500.0F, 130.0F, 1000.0F, 260.0F, Settings.scale * 1.2F, Settings.scale * 0.8F, 0.0F, 0, 0, 1000, 260, false, false);
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
/* 220 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[3], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 330.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case CHARACTER:
/* 229 */         ((AbstractUnlock)this.unlockBundle.get(0)).render(sb);
/* 230 */         ((AbstractUnlock)this.unlockBundle.get(0)).player.renderPlayerImage(sb);
/*     */         break;
/*     */       case RELIC:
/* 233 */         for (i = 0; i < this.unlockBundle.size(); i++) {
/* 234 */           if (RelicLibrary.redList.contains(((AbstractUnlock)this.unlockBundle.get(i)).relic)) {
/* 235 */             ((AbstractUnlock)this.unlockBundle.get(i)).relic.render(sb, false, Settings.RED_RELIC_COLOR);
/* 236 */           } else if (RelicLibrary.greenList.contains(((AbstractUnlock)this.unlockBundle.get(i)).relic)) {
/* 237 */             ((AbstractUnlock)this.unlockBundle.get(i)).relic.render(sb, false, Settings.GREEN_RELIC_COLOR);
/* 238 */           } else if (RelicLibrary.blueList.contains(((AbstractUnlock)this.unlockBundle.get(i)).relic)) {
/* 239 */             ((AbstractUnlock)this.unlockBundle.get(i)).relic.render(sb, false, Settings.BLUE_RELIC_COLOR);
/* 240 */           } else if (RelicLibrary.whiteList.contains(((AbstractUnlock)this.unlockBundle.get(i)).relic)) {
/* 241 */             ((AbstractUnlock)this.unlockBundle.get(i)).relic.render(sb, false, Settings.PURPLE_RELIC_COLOR);
/*     */           } else {
/* 243 */             ((AbstractUnlock)this.unlockBundle.get(i)).relic.render(sb, false, Settings.TWO_THIRDS_TRANSPARENT_BLACK_COLOR);
/*     */           } 
/*     */           
/* 246 */           sb.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
/* 247 */           sb.draw(ImageMaster.UNLOCK_TEXT_BG, Settings.WIDTH / 2.0F - 500.0F, Settings.HEIGHT / 2.0F - 330.0F * Settings.scale - 130.0F, 500.0F, 130.0F, 1000.0F, 260.0F, Settings.scale * 1.2F, Settings.scale * 0.8F, 0.0F, 0, 0, 1000, 260, false, false);
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
/* 265 */           FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, 
/*     */ 
/*     */               
/* 268 */               ((AbstractUnlock)this.unlockBundle.get(i)).relic.name, Settings.WIDTH * 0.25F * (i + 1), Settings.HEIGHT / 2.0F - 150.0F * Settings.scale, Settings.GOLD_COLOR, 1.2F);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 275 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[3], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 330.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 287 */     this.button.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\neow\NeowUnlockScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */