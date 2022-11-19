/*     */ package com.megacrit.cardcrawl.ui.panels;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.GlyphLayout;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.TutorialStrings;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.BobEffect;
/*     */ import com.megacrit.cardcrawl.vfx.DiscardGlowEffect;
/*     */ import com.megacrit.cardcrawl.vfx.ThoughtBubble;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ public class DiscardPilePanel
/*     */   extends AbstractPanel
/*     */ {
/*  33 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Discard Tip");
/*  34 */   public static final String[] MSG = tutorialStrings.TEXT;
/*  35 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*  36 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardPilePanel");
/*  37 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static final int RAW_W = 128;
/*  40 */   private float scale = 1.0F;
/*  41 */   private static final float COUNT_CIRCLE_W = 128.0F * Settings.scale;
/*  42 */   private static final float DECK_X = 180.0F * Settings.scale - 64.0F;
/*  43 */   private static final float DECK_Y = 70.0F * Settings.scale - 64.0F;
/*  44 */   private static final float COUNT_X = 134.0F * Settings.scale;
/*  45 */   private static final float COUNT_Y = 48.0F * Settings.scale;
/*  46 */   private static final float COUNT_OFFSET_X = 70.0F * Settings.scale;
/*  47 */   private static final float COUNT_OFFSET_Y = -18.0F * Settings.scale;
/*     */   
/*  49 */   private Color glowColor = Color.WHITE.cpy();
/*  50 */   private float glowAlpha = 0.0F;
/*  51 */   private GlyphLayout gl = new GlyphLayout();
/*  52 */   private BobEffect bob = new BobEffect(1.0F);
/*     */   
/*  54 */   private ArrayList<DiscardGlowEffect> vfxAbove = new ArrayList<>();
/*  55 */   private ArrayList<DiscardGlowEffect> vfxBelow = new ArrayList<>();
/*     */   
/*  57 */   private static final float DECK_TIP_X = 1550.0F * Settings.xScale;
/*  58 */   private static final float DECK_TIP_Y = 470.0F * Settings.scale;
/*     */ 
/*     */   
/*  61 */   private static final float HITBOX_W = 120.0F * Settings.scale;
/*  62 */   private static final float HITBOX_W2 = 450.0F * Settings.xScale;
/*     */   
/*  64 */   private Hitbox hb = new Hitbox(Settings.WIDTH - HITBOX_W, 0.0F, HITBOX_W, HITBOX_W);
/*  65 */   private Hitbox bannerHb = new Hitbox(Settings.WIDTH - HITBOX_W2, 0.0F, HITBOX_W2, HITBOX_W);
/*     */   
/*     */   public DiscardPilePanel() {
/*  68 */     super(Settings.WIDTH - 256.0F * Settings.scale, 0.0F, Settings.WIDTH, -300.0F * Settings.scale, 8.0F * Settings.xScale, 0.0F, null, true);
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
/*     */   public void updatePositions() {
/*  81 */     super.updatePositions();
/*  82 */     this.bob.update();
/*  83 */     updateVfx();
/*     */     
/*  85 */     if (!this.isHidden) {
/*  86 */       this.hb.update();
/*  87 */       this.bannerHb.update();
/*  88 */       updatePop();
/*     */     } 
/*     */ 
/*     */     
/*  92 */     if (this.hb.hovered && (!AbstractDungeon.isScreenUp || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DISCARD_VIEW || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT || (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD && AbstractDungeon.overlayMenu.combatPanelsShown))) {
/*     */ 
/*     */ 
/*     */       
/*  96 */       AbstractDungeon.overlayMenu.hoveredTip = true;
/*  97 */       if (InputHelper.justClickedLeft) {
/*  98 */         this.hb.clickStarted = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 103 */     if ((this.hb.clicked || InputActionSet.discardPile.isJustPressed() || CInputActionSet.discardPile.isJustPressed()) && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DISCARD_VIEW) {
/*     */ 
/*     */       
/* 106 */       this.hb.clicked = false;
/* 107 */       this.hb.hovered = false;
/* 108 */       this.bannerHb.hovered = false;
/*     */       
/* 110 */       CardCrawlGame.sound.play("DECK_CLOSE");
/* 111 */       if (AbstractDungeon.previousScreen == AbstractDungeon.CurrentScreen.DISCARD_VIEW) {
/* 112 */         AbstractDungeon.previousScreen = null;
/*     */       }
/* 114 */       AbstractDungeon.closeCurrentScreen();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 119 */     this.glowAlpha += Gdx.graphics.getDeltaTime() * 3.0F;
/* 120 */     if (this.glowAlpha < 0.0F) {
/* 121 */       this.glowAlpha *= -1.0F;
/*     */     }
/* 123 */     float tmp = MathUtils.cos(this.glowAlpha);
/* 124 */     if (tmp < 0.0F) {
/* 125 */       this.glowColor.a = -tmp / 2.0F;
/*     */     } else {
/* 127 */       this.glowColor.a = tmp / 2.0F;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 132 */     if ((this.hb.clicked || InputActionSet.discardPile.isJustPressed() || CInputActionSet.discardPile.isJustPressed()) && AbstractDungeon.overlayMenu.combatPanelsShown && 
/* 133 */       AbstractDungeon.getMonsters() != null && 
/* 134 */       !AbstractDungeon.getMonsters().areMonstersDead() && !AbstractDungeon.player.isDead) {
/*     */       
/* 136 */       this.hb.clicked = false;
/* 137 */       this.hb.hovered = false;
/* 138 */       this.bannerHb.hovered = false;
/*     */       
/* 140 */       AbstractDungeon.dynamicBanner.hide();
/*     */       
/* 142 */       if (AbstractDungeon.player.hoveredCard != null) {
/* 143 */         AbstractDungeon.player.releaseCard();
/*     */       }
/*     */       
/* 146 */       if (AbstractDungeon.isScreenUp) {
/* 147 */         if (AbstractDungeon.previousScreen == null) {
/* 148 */           AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*     */         }
/*     */       } else {
/* 151 */         AbstractDungeon.previousScreen = null;
/*     */       } 
/*     */       
/* 154 */       openDiscardPile();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void openDiscardPile() {
/* 159 */     AbstractPlayer p = AbstractDungeon.player;
/*     */     
/* 161 */     if (p.discardPile.size() != 0) {
/* 162 */       AbstractDungeon.discardPileViewScreen.open();
/*     */     } else {
/* 164 */       AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, TEXT[0], true));
/*     */     } 
/*     */     
/* 167 */     this.hb.hovered = false;
/* 168 */     InputHelper.justClickedLeft = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateVfx() {
/*     */     Iterator<DiscardGlowEffect> i;
/* 175 */     for (i = this.vfxAbove.iterator(); i.hasNext(); ) {
/* 176 */       AbstractGameEffect e = (AbstractGameEffect)i.next();
/* 177 */       e.update();
/* 178 */       if (e.isDone) {
/* 179 */         i.remove();
/*     */       }
/*     */     } 
/* 182 */     for (i = this.vfxBelow.iterator(); i.hasNext(); ) {
/* 183 */       AbstractGameEffect e = (AbstractGameEffect)i.next();
/* 184 */       e.update();
/* 185 */       if (e.isDone) {
/* 186 */         i.remove();
/*     */       }
/*     */     } 
/*     */     
/* 190 */     if (this.vfxAbove.size() < 9 && !Settings.DISABLE_EFFECTS) {
/* 191 */       this.vfxAbove.add(new DiscardGlowEffect(true));
/*     */     }
/* 193 */     if (this.vfxBelow.size() < 9 && !Settings.DISABLE_EFFECTS) {
/* 194 */       this.vfxBelow.add(new DiscardGlowEffect(false));
/*     */     }
/*     */   }
/*     */   
/*     */   private void updatePop() {
/* 199 */     this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale);
/*     */   }
/*     */   
/*     */   public void pop() {
/* 203 */     this.scale = 1.75F * Settings.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 208 */     renderButton(sb);
/*     */ 
/*     */     
/* 211 */     String msg = Integer.toString(AbstractDungeon.player.discardPile.size());
/* 212 */     this.gl.setText(FontHelper.turnNumFont, msg);
/* 213 */     sb.setColor(Color.WHITE);
/* 214 */     sb.draw(ImageMaster.DECK_COUNT_CIRCLE, this.current_x + COUNT_OFFSET_X, this.current_y + COUNT_OFFSET_Y, COUNT_CIRCLE_W, COUNT_CIRCLE_W);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     if (Settings.isControllerMode) {
/* 222 */       sb.draw(CInputActionSet.discardPile
/* 223 */           .getKeyImg(), this.current_x - 32.0F + 220.0F * Settings.scale, this.current_y - 32.0F + 40.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 64, 64, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 241 */     FontHelper.renderFontCentered(sb, FontHelper.turnNumFont, msg, this.current_x + COUNT_X, this.current_y + COUNT_Y);
/*     */     
/* 243 */     if (!this.isHidden) {
/* 244 */       this.hb.render(sb);
/* 245 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DISCARD_VIEW) {
/* 246 */         this.bannerHb.render(sb);
/*     */       }
/*     */     } 
/*     */     
/* 250 */     if (!this.isHidden && this.hb != null && this.hb.hovered && !AbstractDungeon.isScreenUp && 
/* 251 */       AbstractDungeon.getMonsters() != null && !AbstractDungeon.getMonsters().areMonstersDead()) {
/* 252 */       if (Settings.isConsoleBuild) {
/* 253 */         TipHelper.renderGenericTip(DECK_TIP_X, DECK_TIP_Y, LABEL[0] + " (" + InputActionSet.discardPile
/*     */ 
/*     */             
/* 256 */             .getKeyString() + ")", MSG[1]);
/*     */       } else {
/*     */         
/* 259 */         TipHelper.renderGenericTip(DECK_TIP_X, DECK_TIP_Y, LABEL[0] + " (" + InputActionSet.discardPile
/*     */ 
/*     */             
/* 262 */             .getKeyString() + ")", MSG[0]);
/*     */       } 
/*     */     } else {
/*     */       
/* 266 */       this.hb.hovered = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderButton(SpriteBatch sb) {
/* 271 */     if (this.hb.hovered || (this.bannerHb.hovered && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DISCARD_VIEW)) {
/* 272 */       this.scale = 1.2F * Settings.scale;
/*     */     }
/*     */     
/* 275 */     for (DiscardGlowEffect e : this.vfxBelow) {
/* 276 */       e.render(sb, this.current_x - 1664.0F * Settings.scale, this.current_y + this.bob.y * 0.5F);
/*     */     }
/*     */     
/* 279 */     sb.setColor(Color.WHITE);
/* 280 */     sb.draw(ImageMaster.DISCARD_BTN_BASE, this.current_x + DECK_X, this.current_y + DECK_Y + this.bob.y / 2.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, 0.0F, 0, 0, 128, 128, false, false);
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
/* 298 */     for (DiscardGlowEffect e : this.vfxAbove)
/* 299 */       e.render(sb, this.current_x - 1664.0F * Settings.scale, this.current_y + this.bob.y * 0.5F); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\DiscardPilePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */