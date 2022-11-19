/*     */ package com.megacrit.cardcrawl.core;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.blights.AbstractBlight;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.ui.buttons.CancelButton;
/*     */ import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;
/*     */ import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
/*     */ import com.megacrit.cardcrawl.ui.panels.BottomBgPanel;
/*     */ import com.megacrit.cardcrawl.ui.panels.DiscardPilePanel;
/*     */ import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
/*     */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*     */ import com.megacrit.cardcrawl.ui.panels.ExhaustPanel;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OverlayMenu
/*     */ {
/*     */   private AbstractPlayer player;
/*  28 */   public static final float HAND_HIDE_Y = 300.0F * Settings.scale;
/*     */   public boolean combatPanelsShown = true;
/*  30 */   public float tipHoverDuration = 0.0F;
/*     */   private static final float HOVER_TIP_TIME = 0.01F;
/*     */   public boolean hoveredTip = false;
/*  33 */   public ArrayList<AbstractRelic> relicQueue = new ArrayList<>();
/*     */ 
/*     */   
/*  36 */   public BottomBgPanel bottomBgPanel = new BottomBgPanel();
/*  37 */   public EnergyPanel energyPanel = new EnergyPanel();
/*  38 */   private Color blackScreenColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  39 */   private float blackScreenTarget = 0.0F;
/*     */ 
/*     */   
/*  42 */   public DrawPilePanel combatDeckPanel = new DrawPilePanel();
/*  43 */   public DiscardPilePanel discardPilePanel = new DiscardPilePanel();
/*  44 */   public ExhaustPanel exhaustPanel = new ExhaustPanel();
/*     */ 
/*     */   
/*  47 */   public EndTurnButton endTurnButton = new EndTurnButton();
/*  48 */   public ProceedButton proceedButton = new ProceedButton();
/*  49 */   public CancelButton cancelButton = new CancelButton();
/*     */   
/*     */   public OverlayMenu(AbstractPlayer player) {
/*  52 */     this.player = player;
/*     */   }
/*     */   
/*     */   public void update() {
/*  56 */     this.hoveredTip = false;
/*  57 */     this.bottomBgPanel.updatePositions();
/*  58 */     this.energyPanel.updatePositions();
/*  59 */     this.energyPanel.update();
/*  60 */     this.player.hand.update();
/*  61 */     this.combatDeckPanel.updatePositions();
/*  62 */     this.discardPilePanel.updatePositions();
/*  63 */     this.exhaustPanel.updatePositions();
/*  64 */     this.endTurnButton.update();
/*  65 */     this.proceedButton.update();
/*  66 */     this.cancelButton.update();
/*  67 */     updateBlackScreen();
/*     */     
/*  69 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  70 */       if (r != null) {
/*  71 */         r.update();
/*     */       }
/*     */     } 
/*     */     
/*  75 */     for (AbstractBlight b : AbstractDungeon.player.blights) {
/*  76 */       if (b != null) {
/*  77 */         b.update();
/*     */       }
/*     */     } 
/*     */     
/*  81 */     if (!this.relicQueue.isEmpty()) {
/*  82 */       for (AbstractRelic r : this.relicQueue) {
/*  83 */         AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, r);
/*     */       }
/*  85 */       this.relicQueue.clear();
/*     */     } 
/*     */     
/*  88 */     if (this.hoveredTip) {
/*  89 */       this.tipHoverDuration += Gdx.graphics.getDeltaTime();
/*  90 */       if (this.tipHoverDuration > 0.01F) {
/*  91 */         this.tipHoverDuration = 0.02F;
/*     */       }
/*     */     } else {
/*  94 */       this.tipHoverDuration -= Gdx.graphics.getDeltaTime();
/*  95 */       if (this.tipHoverDuration < 0.0F) {
/*  96 */         this.tipHoverDuration = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void showCombatPanels() {
/* 102 */     this.combatPanelsShown = true;
/* 103 */     this.bottomBgPanel.changeMode(BottomBgPanel.Mode.NORMAL);
/* 104 */     this.combatDeckPanel.show();
/* 105 */     this.discardPilePanel.show();
/* 106 */     this.exhaustPanel.show();
/* 107 */     this.energyPanel.show();
/* 108 */     this.endTurnButton.show();
/*     */     
/* 110 */     if (AbstractDungeon.ftue == null) {
/* 111 */       this.proceedButton.hide();
/*     */     }
/*     */     
/* 114 */     this.player.hand.refreshHandLayout();
/*     */   }
/*     */   
/*     */   public void hideCombatPanels() {
/* 118 */     this.combatPanelsShown = false;
/* 119 */     this.bottomBgPanel.changeMode(BottomBgPanel.Mode.HIDDEN);
/* 120 */     this.combatDeckPanel.hide();
/* 121 */     this.discardPilePanel.hide();
/* 122 */     this.exhaustPanel.hide();
/* 123 */     this.endTurnButton.hide();
/* 124 */     this.energyPanel.hide();
/*     */     
/* 126 */     for (AbstractCard c : this.player.hand.group) {
/* 127 */       c.target_y = -AbstractCard.IMG_HEIGHT;
/*     */     }
/*     */   }
/*     */   
/*     */   public void showBlackScreen(float target) {
/* 132 */     this.blackScreenTarget = target;
/*     */   }
/*     */   
/*     */   public void showBlackScreen() {
/* 136 */     if (this.blackScreenTarget < 0.85F) {
/* 137 */       this.blackScreenTarget = 0.85F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void hideBlackScreen() {
/* 142 */     this.blackScreenTarget = 0.0F;
/*     */   }
/*     */   
/*     */   private void updateBlackScreen() {
/* 146 */     if (this.blackScreenColor.a != this.blackScreenTarget) {
/* 147 */       if (this.blackScreenTarget > this.blackScreenColor.a) {
/* 148 */         this.blackScreenColor.a += Gdx.graphics.getDeltaTime() * 2.0F;
/* 149 */         if (this.blackScreenColor.a > this.blackScreenTarget) {
/* 150 */           this.blackScreenColor.a = this.blackScreenTarget;
/*     */         }
/*     */       } else {
/* 153 */         this.blackScreenColor.a -= Gdx.graphics.getDeltaTime() * 2.0F;
/* 154 */         if (this.blackScreenColor.a < this.blackScreenTarget) {
/* 155 */           this.blackScreenColor.a = this.blackScreenTarget;
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 163 */     this.endTurnButton.render(sb);
/* 164 */     this.proceedButton.render(sb);
/* 165 */     this.cancelButton.render(sb);
/*     */     
/* 167 */     if (!Settings.hideLowerElements) {
/* 168 */       this.energyPanel.render(sb);
/* 169 */       this.combatDeckPanel.render(sb);
/* 170 */       this.discardPilePanel.render(sb);
/* 171 */       this.exhaustPanel.render(sb);
/*     */     } 
/*     */ 
/*     */     
/* 175 */     this.player.renderHand(sb);
/* 176 */     this.player.hand.renderTip(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderBlackScreen(SpriteBatch sb) {
/* 181 */     if (this.blackScreenColor.a != 0.0F) {
/* 182 */       sb.setColor(this.blackScreenColor);
/* 183 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\core\OverlayMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */