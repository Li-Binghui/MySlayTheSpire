/*     */ package com.megacrit.cardcrawl.cutscenes;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.utils.Disposable;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.GameCursor;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.screens.VictoryScreen;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class Cutscene
/*     */   implements Disposable {
/*  20 */   private int currentScene = 0;
/*  21 */   private float darkenTimer = 1.0F; private float fadeTimer = 1.0F; private float switchTimer = 1.0F; private Color screenColor;
/*     */   private Color bgColor;
/*  23 */   private ArrayList<CutscenePanel> panels = new ArrayList<>();
/*     */   private Texture bgImg;
/*     */   private boolean isDone = false;
/*     */   
/*     */   public Cutscene(AbstractPlayer.PlayerClass chosenClass) {
/*  28 */     switch (chosenClass) {
/*     */       case IRONCLAD:
/*  30 */         this.bgImg = ImageMaster.loadImage("images/scenes/redBg.jpg");
/*  31 */         this.panels.add(new CutscenePanel("images/scenes/ironclad1.png", "ATTACK_HEAVY"));
/*  32 */         this.panels.add(new CutscenePanel("images/scenes/ironclad2.png"));
/*  33 */         this.panels.add(new CutscenePanel("images/scenes/ironclad3.png"));
/*     */         break;
/*     */       case THE_SILENT:
/*  36 */         this.bgImg = ImageMaster.loadImage("images/scenes/greenBg.jpg");
/*  37 */         this.panels.add(new CutscenePanel("images/scenes/silent1.png", "ATTACK_POISON2"));
/*  38 */         this.panels.add(new CutscenePanel("images/scenes/silent2.png"));
/*  39 */         this.panels.add(new CutscenePanel("images/scenes/silent3.png"));
/*     */         break;
/*     */       case DEFECT:
/*  42 */         this.bgImg = ImageMaster.loadImage("images/scenes/blueBg.jpg");
/*  43 */         this.panels.add(new CutscenePanel("images/scenes/defect1.png", "ATTACK_MAGIC_BEAM_SHORT"));
/*  44 */         this.panels.add(new CutscenePanel("images/scenes/defect2.png"));
/*  45 */         this.panels.add(new CutscenePanel("images/scenes/defect3.png"));
/*     */         break;
/*     */       case WATCHER:
/*  48 */         this.bgImg = ImageMaster.loadImage("images/scenes/purpleBg.jpg");
/*  49 */         this.panels.add(new CutscenePanel("images/scenes/watcher1.png", "WATCHER_HEART_PUNCH"));
/*  50 */         this.panels.add(new CutscenePanel("images/scenes/watcher2.png"));
/*  51 */         this.panels.add(new CutscenePanel("images/scenes/watcher3.png"));
/*     */         break;
/*     */       default:
/*  54 */         this.bgImg = ImageMaster.loadImage("images/scenes/redBg.jpg");
/*  55 */         this.panels.add(new CutscenePanel("images/scenes/ironclad1.png", "ATTACK_HEAVY"));
/*  56 */         this.panels.add(new CutscenePanel("images/scenes/ironclad2.png"));
/*  57 */         this.panels.add(new CutscenePanel("images/scenes/ironclad3.png"));
/*     */         break;
/*     */     } 
/*     */     
/*  61 */     this.bgColor = Color.WHITE.cpy();
/*  62 */     this.screenColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void update() {
/*  66 */     updateFadeOut();
/*  67 */     updateFadeIn();
/*  68 */     for (CutscenePanel p : this.panels) {
/*  69 */       p.update();
/*     */     }
/*  71 */     updateIfDone();
/*  72 */     updateSceneChange();
/*     */   }
/*     */   
/*     */   private void updateIfDone() {
/*  76 */     if (this.isDone) {
/*  77 */       this.bgColor.a -= Gdx.graphics.getDeltaTime();
/*     */       
/*  79 */       for (CutscenePanel p : this.panels) {
/*  80 */         if (!p.finished) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/*  85 */       dispose();
/*  86 */       this.bgColor.a = 0.0F;
/*  87 */       openVictoryScreen();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateSceneChange() {
/*  92 */     this.switchTimer -= Gdx.graphics.getDeltaTime();
/*     */     
/*  94 */     if ((InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed()) && this.switchTimer > 1.0F) {
/*  95 */       this.switchTimer = 1.0F;
/*     */     }
/*     */     
/*  98 */     if (this.switchTimer < 0.0F) {
/*  99 */       for (CutscenePanel p : this.panels) {
/* 100 */         if (!p.activated) {
/* 101 */           p.activate();
/* 102 */           this.switchTimer = 5.0F;
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 107 */       for (CutscenePanel p : this.panels) {
/* 108 */         p.fadeOut();
/*     */       }
/* 110 */       this.isDone = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void openVictoryScreen() {
/* 115 */     GameCursor.hidden = false;
/* 116 */     AbstractDungeon.victoryScreen = new VictoryScreen(null);
/*     */   }
/*     */   
/*     */   private void updateFadeIn() {
/* 120 */     if (this.darkenTimer == 0.0F) {
/* 121 */       this.fadeTimer -= Gdx.graphics.getDeltaTime();
/* 122 */       if (this.fadeTimer < 0.0F) {
/* 123 */         this.fadeTimer = 0.0F;
/*     */       }
/* 125 */       this.screenColor.a = this.fadeTimer;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateFadeOut() {
/* 130 */     if (this.darkenTimer != 0.0F) {
/* 131 */       this.darkenTimer -= Gdx.graphics.getDeltaTime();
/* 132 */       if (this.darkenTimer < 0.0F) {
/* 133 */         this.darkenTimer = 0.0F;
/* 134 */         this.fadeTimer = 1.0F;
/* 135 */         this.switchTimer = 1.0F;
/*     */       } 
/* 137 */       this.screenColor.a = 1.0F - this.darkenTimer;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 142 */     if (this.currentScene <= 1) {
/* 143 */       sb.setColor(Color.BLACK);
/* 144 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderAbove(SpriteBatch sb) {
/* 149 */     if (this.bgImg != null) {
/* 150 */       sb.setColor(this.bgColor);
/* 151 */       renderImg(sb, this.bgImg);
/*     */     } 
/* 153 */     renderPanels(sb);
/* 154 */     sb.setColor(this.screenColor);
/* 155 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */   }
/*     */   
/*     */   private void renderPanels(SpriteBatch sb) {
/* 159 */     for (CutscenePanel p : this.panels) {
/* 160 */       p.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderImg(SpriteBatch sb, Texture img) {
/* 165 */     if (Settings.isSixteenByTen) {
/* 166 */       sb.draw(img, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     } else {
/* 168 */       sb.draw(img, 0.0F, -50.0F * Settings.scale, Settings.WIDTH, Settings.HEIGHT + 110.0F * Settings.scale);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 174 */     if (this.bgImg != null) {
/* 175 */       this.bgImg.dispose();
/* 176 */       this.bgImg = null;
/*     */     } 
/* 178 */     for (CutscenePanel p : this.panels)
/* 179 */       p.dispose(); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cutscenes\Cutscene.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */