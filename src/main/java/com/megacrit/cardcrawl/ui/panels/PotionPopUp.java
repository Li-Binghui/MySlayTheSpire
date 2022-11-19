/*     */ package com.megacrit.cardcrawl.ui.panels;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.SmokeBomb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ public class PotionPopUp
/*     */ {
/*  37 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Potion Panel Tip");
/*     */   
/*  39 */   public static final String[] MSG = tutorialStrings.TEXT;
/*  40 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*  41 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("PotionPopUp");
/*  42 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private int slot;
/*     */   
/*     */   private AbstractPotion potion;
/*     */   public boolean isHidden = true;
/*     */   public boolean targetMode = false;
/*     */   private Hitbox hbTop;
/*     */   private Hitbox hbBot;
/*  51 */   private Color topHoverColor = new Color(0.5F, 0.9F, 1.0F, 0.0F); private Color botHoverColor = new Color(1.0F, 0.4F, 0.3F, 0.0F);
/*     */   
/*     */   private float x;
/*     */   private float y;
/*     */   private static final int SEGMENTS = 20;
/*  56 */   private Vector2[] points = new Vector2[20]; private Vector2 controlPoint;
/*     */   private float arrowScale;
/*  58 */   private float arrowScaleTimer = 0.0F;
/*     */   private static final float ARROW_TARGET_SCALE = 1.2F;
/*     */   private static final int TARGET_ARROW_W = 256;
/*  61 */   private AbstractMonster hoveredMonster = null;
/*     */   private boolean autoTargetFirst = false;
/*     */   
/*     */   public PotionPopUp() {
/*  65 */     this.hbTop = new Hitbox(286.0F * Settings.scale, 120.0F * Settings.scale);
/*  66 */     this.hbBot = new Hitbox(286.0F * Settings.scale, 90.0F * Settings.scale);
/*     */     
/*  68 */     for (int i = 0; i < this.points.length; i++) {
/*  69 */       this.points[i] = new Vector2();
/*     */     }
/*     */   }
/*     */   
/*     */   public void open(int slot, AbstractPotion potion) {
/*  74 */     this.topHoverColor.a = 0.0F;
/*  75 */     this.botHoverColor.a = 0.0F;
/*  76 */     AbstractDungeon.topPanel.selectPotionMode = false;
/*  77 */     this.slot = slot;
/*  78 */     this.potion = potion;
/*  79 */     this.x = potion.posX;
/*  80 */     this.y = Settings.HEIGHT - 230.0F * Settings.scale;
/*  81 */     this.isHidden = false;
/*     */     
/*  83 */     this.hbTop.move(this.x, this.y + 44.0F * Settings.scale);
/*  84 */     this.hbBot.move(this.x, this.y - 76.0F * Settings.scale);
/*     */     
/*  86 */     this.hbTop.clickStarted = false;
/*  87 */     this.hbBot.clickStarted = false;
/*  88 */     this.hbTop.clicked = false;
/*  89 */     this.hbBot.clicked = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/*  94 */     this.isHidden = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  99 */     if (!this.isHidden) {
/* 100 */       updateControllerInput();
/* 101 */       this.hbTop.update();
/* 102 */       this.hbBot.update();
/* 103 */       updateInput();
/* 104 */       if (this.potion != null) {
/* 105 */         TipHelper.queuePowerTips(this.x + 180.0F * Settings.scale, this.y + 70.0F * Settings.scale, this.potion.tips);
/*     */       }
/*     */     }
/* 108 */     else if (this.targetMode) {
/* 109 */       updateControllerTargetInput();
/* 110 */       updateTargetMode();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateControllerTargetInput() {
/* 115 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 119 */     int offsetEnemyIndex = 0;
/*     */     
/* 121 */     if (this.autoTargetFirst) {
/* 122 */       this.autoTargetFirst = false;
/* 123 */       offsetEnemyIndex++;
/*     */     } 
/*     */     
/* 126 */     if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 127 */       offsetEnemyIndex--;
/*     */     }
/* 129 */     if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 130 */       offsetEnemyIndex++;
/*     */     }
/*     */     
/* 133 */     if (offsetEnemyIndex != 0) {
/*     */       AbstractMonster newTarget;
/*     */ 
/*     */       
/* 137 */       ArrayList<AbstractMonster> prefiltered = (AbstractDungeon.getCurrRoom()).monsters.monsters;
/*     */       
/* 139 */       ArrayList<AbstractMonster> sortedMonsters = new ArrayList<>((AbstractDungeon.getCurrRoom()).monsters.monsters);
/*     */       
/* 141 */       for (AbstractMonster mons : prefiltered) {
/* 142 */         if (mons.isDying) {
/* 143 */           sortedMonsters.remove(mons);
/*     */         }
/*     */       } 
/*     */       
/* 147 */       sortedMonsters.sort(AbstractMonster.sortByHitbox);
/*     */       
/* 149 */       if (sortedMonsters.isEmpty()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 154 */       for (AbstractMonster m : sortedMonsters) {
/* 155 */         if (m.hb.hovered) {
/* 156 */           this.hoveredMonster = m;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 162 */       if (this.hoveredMonster == null) {
/*     */         
/* 164 */         if (offsetEnemyIndex == 1) {
/*     */           
/* 166 */           newTarget = sortedMonsters.get(0);
/*     */         } else {
/*     */           
/* 169 */           newTarget = sortedMonsters.get(sortedMonsters.size() - 1);
/*     */         } 
/*     */       } else {
/*     */         
/* 173 */         int currentTargetIndex = sortedMonsters.indexOf(this.hoveredMonster);
/* 174 */         int newTargetIndex = currentTargetIndex + offsetEnemyIndex;
/* 175 */         newTargetIndex = (newTargetIndex + sortedMonsters.size()) % sortedMonsters.size();
/* 176 */         newTarget = sortedMonsters.get(newTargetIndex);
/*     */       } 
/*     */       
/* 179 */       if (newTarget != null) {
/* 180 */         Hitbox target = newTarget.hb;
/* 181 */         Gdx.input.setCursorPosition((int)target.cX, Settings.HEIGHT - (int)target.cY);
/* 182 */         this.hoveredMonster = newTarget;
/*     */       } 
/*     */       
/* 185 */       if (this.hoveredMonster.halfDead) {
/* 186 */         this.hoveredMonster = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 192 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 196 */     if (CInputActionSet.cancel.isJustPressed()) {
/* 197 */       CInputActionSet.cancel.unpress();
/* 198 */       close();
/*     */       
/*     */       return;
/*     */     } 
/* 202 */     if (!this.hbTop.hovered && !this.hbBot.hovered) {
/* 203 */       if (this.potion.canUse()) {
/* 204 */         Gdx.input.setCursorPosition((int)this.hbTop.cX, Settings.HEIGHT - (int)this.hbTop.cY);
/*     */       } else {
/* 206 */         Gdx.input.setCursorPosition((int)this.hbBot.cX, Settings.HEIGHT - (int)this.hbBot.cY);
/*     */       } 
/* 208 */     } else if (this.hbTop.hovered) {
/* 209 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.down.isJustPressed() || CInputActionSet.altUp
/* 210 */         .isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 211 */         Gdx.input.setCursorPosition((int)this.hbBot.cX, Settings.HEIGHT - (int)this.hbBot.cY);
/*     */       }
/* 213 */     } else if (this.hbBot.hovered && 
/* 214 */       this.potion.canUse() && (CInputActionSet.up.isJustPressed() || CInputActionSet.down.isJustPressed() || CInputActionSet.altUp
/* 215 */       .isJustPressed() || CInputActionSet.altDown.isJustPressed())) {
/* 216 */       Gdx.input.setCursorPosition((int)this.hbTop.cX, Settings.HEIGHT - (int)this.hbTop.cY);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateTargetMode() {
/* 222 */     if (InputHelper.justClickedRight || AbstractDungeon.isScreenUp || InputHelper.mY > Settings.HEIGHT - 80.0F * Settings.scale || AbstractDungeon.player.hoveredCard != null || InputHelper.mY < 140.0F * Settings.scale || CInputActionSet.cancel
/*     */       
/* 224 */       .isJustPressed()) {
/* 225 */       CInputActionSet.cancel.unpress();
/* 226 */       this.targetMode = false;
/* 227 */       GameCursor.hidden = false;
/*     */     } 
/*     */     
/* 230 */     this.hoveredMonster = null;
/* 231 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 232 */       if (m.hb.hovered && !m.isDying) {
/* 233 */         this.hoveredMonster = m;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 238 */     if (InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed()) {
/* 239 */       InputHelper.justClickedLeft = false;
/* 240 */       CInputActionSet.select.unpress();
/*     */       
/* 242 */       if (this.hoveredMonster != null) {
/* 243 */         if (AbstractDungeon.player.hasPower("Surrounded")) {
/* 244 */           AbstractDungeon.player.flipHorizontal = (this.hoveredMonster.drawX < AbstractDungeon.player.drawX);
/*     */         }
/* 246 */         CardCrawlGame.metricData.potions_floor_usage.add(Integer.valueOf(AbstractDungeon.floorNum));
/* 247 */         this.potion.use((AbstractCreature)this.hoveredMonster);
/* 248 */         if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
/*     */         {
/*     */           
/* 251 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HandCheckAction());
/*     */         }
/* 253 */         for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 254 */           r.onUsePotion();
/*     */         }
/* 256 */         AbstractDungeon.topPanel.destroyPotion(this.slot);
/* 257 */         this.targetMode = false;
/* 258 */         GameCursor.hidden = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateInput() {
/* 264 */     if (InputHelper.justClickedLeft) {
/* 265 */       InputHelper.justClickedLeft = false;
/* 266 */       if (this.hbTop.hovered) {
/* 267 */         this.hbTop.clickStarted = true;
/* 268 */       } else if (this.hbBot.hovered) {
/* 269 */         this.hbBot.clickStarted = true;
/*     */       } else {
/* 271 */         close();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 276 */     if ((this.hbTop.clicked || (this.hbTop.hovered && CInputActionSet.select.isJustPressed())) && (!AbstractDungeon.isScreenUp || this.potion
/* 277 */       .canUse() == true)) {
/* 278 */       CInputActionSet.select.unpress();
/* 279 */       this.hbTop.clicked = false;
/* 280 */       if (this.potion.canUse()) {
/* 281 */         if (!this.potion.targetRequired) {
/* 282 */           CardCrawlGame.metricData.potions_floor_usage.add(Integer.valueOf(AbstractDungeon.floorNum));
/* 283 */           this.potion.use(null);
/* 284 */           for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 285 */             r.onUsePotion();
/*     */           }
/* 287 */           CardCrawlGame.sound.play("POTION_1");
/* 288 */           AbstractDungeon.topPanel.destroyPotion(this.slot);
/*     */         } else {
/* 290 */           this.targetMode = true;
/* 291 */           GameCursor.hidden = true;
/* 292 */           this.autoTargetFirst = true;
/*     */         } 
/* 294 */         close();
/*     */       }
/* 296 */       else if (this.potion.ID == "SmokeBomb" && 
/* 297 */         (AbstractDungeon.getCurrRoom()).monsters != null) {
/* 298 */         for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 299 */           if (m.hasPower("BackAttack")) {
/* 300 */             AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, SmokeBomb.potionStrings.DESCRIPTIONS[1], true));
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 313 */     else if ((this.hbBot.clicked || (this.hbBot.hovered && CInputActionSet.select.isJustPressed())) && 
/* 314 */       this.potion.canDiscard()) {
/* 315 */       CInputActionSet.select.unpress();
/* 316 */       this.hbBot.clicked = false;
/* 317 */       CardCrawlGame.sound.play("POTION_DROP_2");
/* 318 */       AbstractDungeon.topPanel.destroyPotion(this.slot);
/* 319 */       this.slot = -1;
/* 320 */       this.potion = null;
/* 321 */       close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 327 */     if (!this.isHidden) {
/* 328 */       sb.setColor(Color.WHITE);
/* 329 */       sb.draw(ImageMaster.POTION_UI_BG, this.x - 200.0F, this.y - 169.0F, 200.0F, 169.0F, 400.0F, 338.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 400, 338, false, false);
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
/* 347 */       if (this.hbTop.hovered) {
/* 348 */         this.topHoverColor.a = 0.5F;
/*     */       } else {
/* 350 */         this.topHoverColor.a = MathHelper.fadeLerpSnap(this.topHoverColor.a, 0.0F);
/*     */       } 
/*     */       
/* 353 */       if (this.hbBot.hovered) {
/* 354 */         this.botHoverColor.a = 0.5F;
/*     */       } else {
/* 356 */         this.botHoverColor.a = MathHelper.fadeLerpSnap(this.botHoverColor.a, 0.0F);
/*     */       } 
/*     */       
/* 359 */       sb.setBlendFunction(770, 1);
/* 360 */       sb.setColor(this.topHoverColor);
/* 361 */       sb.draw(ImageMaster.POTION_UI_TOP, this.x - 200.0F, this.y - 169.0F, 200.0F, 169.0F, 400.0F, 338.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 400, 338, false, false);
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
/* 379 */       sb.setColor(this.botHoverColor);
/* 380 */       sb.draw(ImageMaster.POTION_UI_BOT, this.x - 200.0F, this.y - 169.0F, 200.0F, 169.0F, 400.0F, 338.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 400, 338, false, false);
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
/* 397 */       sb.setBlendFunction(770, 771);
/*     */       
/* 399 */       Color c = Settings.CREAM_COLOR;
/* 400 */       if (!this.potion.canUse() || AbstractDungeon.isScreenUp) {
/* 401 */         c = Color.GRAY;
/*     */       }
/*     */       
/* 404 */       if (this.potion.canUse()) {
/* 405 */         if ((AbstractDungeon.getCurrRoom()).event != null) {
/* 406 */           if (!((AbstractDungeon.getCurrRoom()).event instanceof com.megacrit.cardcrawl.events.shrines.WeMeetAgain)) {
/* 407 */             c = Settings.CREAM_COLOR;
/*     */           }
/*     */         } else {
/* 410 */           c = Settings.CREAM_COLOR;
/*     */         } 
/*     */       }
/*     */       
/* 414 */       String label = TEXT[1];
/* 415 */       if (this.potion.isThrown) {
/* 416 */         label = TEXT[0];
/*     */       }
/*     */       
/* 419 */       FontHelper.renderFontCenteredWidth(sb, FontHelper.buttonLabelFont, label, this.x, this.hbTop.cY + 4.0F * Settings.scale, c);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 426 */       FontHelper.renderFontCenteredWidth(sb, FontHelper.buttonLabelFont, TEXT[2], this.x, this.hbBot.cY + 12.0F * Settings.scale, Settings.RED_TEXT_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 434 */       this.hbTop.render(sb);
/* 435 */       this.hbBot.render(sb);
/*     */       
/* 437 */       if (this.hbTop.hovered) {
/* 438 */         if (this.potion.isThrown) {
/* 439 */           TipHelper.renderGenericTip(this.x + 174.0F * Settings.scale, this.y + 20.0F * Settings.scale, LABEL[0], MSG[0]);
/*     */         } else {
/* 441 */           TipHelper.renderGenericTip(this.x + 174.0F * Settings.scale, this.y + 20.0F * Settings.scale, LABEL[1], MSG[1]);
/*     */         } 
/* 443 */       } else if (this.hbBot.hovered) {
/* 444 */         TipHelper.renderGenericTip(this.x + 174.0F * Settings.scale, this.y + 20.0F * Settings.scale, LABEL[2], MSG[2]);
/*     */       } 
/*     */     } 
/*     */     
/* 448 */     if (this.targetMode) {
/* 449 */       if (this.hoveredMonster != null) {
/* 450 */         this.hoveredMonster.renderReticle(sb);
/*     */       }
/* 452 */       renderTargetingUi(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderTargetingUi(SpriteBatch sb) {
/* 457 */     float x = InputHelper.mX, y = InputHelper.mY;
/* 458 */     this.controlPoint = new Vector2(this.potion.posX - (x - this.potion.posX) / 4.0F, y + (y - this.potion.posY - 40.0F * Settings.scale) / 2.0F);
/*     */ 
/*     */ 
/*     */     
/* 462 */     if (this.hoveredMonster == null) {
/* 463 */       this.arrowScale = Settings.scale;
/* 464 */       this.arrowScaleTimer = 0.0F;
/* 465 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
/*     */     } else {
/* 467 */       this.arrowScaleTimer += Gdx.graphics.getDeltaTime();
/* 468 */       if (this.arrowScaleTimer > 1.0F) {
/* 469 */         this.arrowScaleTimer = 1.0F;
/*     */       }
/*     */       
/* 472 */       this.arrowScale = Interpolation.elasticOut.apply(Settings.scale, Settings.scale * 1.2F, this.arrowScaleTimer);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 477 */       sb.setColor(new Color(1.0F, 0.2F, 0.3F, 1.0F));
/*     */     } 
/*     */ 
/*     */     
/* 481 */     Vector2 tmp = new Vector2(this.controlPoint.x - x, this.controlPoint.y - y);
/* 482 */     tmp.nor();
/*     */     
/* 484 */     drawCurvedLine(sb, new Vector2(this.potion.posX, this.potion.posY - 40.0F * Settings.scale), new Vector2(x, y), this.controlPoint);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 489 */     sb.draw(ImageMaster.TARGET_UI_ARROW, x - 128.0F, y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.arrowScale, this.arrowScale, tmp
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 499 */         .angle() + 90.0F, 0, 0, 256, 256, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCurvedLine(SpriteBatch sb, Vector2 start, Vector2 end, Vector2 control) {
/* 509 */     float radius = 7.0F * Settings.scale;
/*     */     
/* 511 */     for (int i = 0; i < this.points.length - 1; i++) {
/* 512 */       float angle; this.points[i] = (Vector2)Bezier.quadratic((Vector)this.points[i], i / 20.0F, (Vector)start, (Vector)control, (Vector)end, (Vector)new Vector2());
/* 513 */       radius += 0.4F * Settings.scale;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 518 */       if (i != 0) {
/* 519 */         Vector2 tmp = new Vector2((this.points[i - 1]).x - (this.points[i]).x, (this.points[i - 1]).y - (this.points[i]).y);
/* 520 */         angle = tmp.nor().angle() + 90.0F;
/*     */       } else {
/* 522 */         Vector2 tmp = new Vector2(this.controlPoint.x - (this.points[i]).x, this.controlPoint.y - (this.points[i]).y);
/* 523 */         angle = tmp.nor().angle() + 270.0F;
/*     */       } 
/*     */       
/* 526 */       sb.draw(ImageMaster.TARGET_UI_CIRCLE, (this.points[i]).x - 64.0F, (this.points[i]).y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, radius / 18.0F, radius / 18.0F, angle, 0, 0, 128, 128, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\PotionPopUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */