/*     */ package com.megacrit.cardcrawl.events.shrines;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/*     */ 
/*     */ public class GremlinMatchGame
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "Match and Keep!";
/*  28 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Match and Keep!");
/*  29 */   public static final String NAME = eventStrings.NAME;
/*  30 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  31 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*     */   private AbstractCard chosenCard;
/*     */   private AbstractCard hoveredCard;
/*  35 */   private int attemptCount = 5; private boolean cardFlipped = false; private boolean gameDone = false; private boolean cleanUpCalled = false;
/*  36 */   private CardGroup cards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*  37 */   private float waitTimer = 0.0F;
/*  38 */   private int cardsMatched = 0;
/*     */   
/*  40 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*  41 */   private static final String MSG_2 = DESCRIPTIONS[0];
/*  42 */   private static final String MSG_3 = DESCRIPTIONS[1];
/*     */ 
/*     */   
/*     */   private List<String> matchedCards;
/*     */ 
/*     */ 
/*     */   
/*     */   public GremlinMatchGame() {
/*  50 */     super(NAME, DESCRIPTIONS[2], "images/events/matchAndKeep.jpg");
/*  51 */     this.cards.group = initializeCards();
/*  52 */     Collections.shuffle(this.cards.group, new Random(AbstractDungeon.miscRng.randomLong()));
/*     */     
/*  54 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*  55 */     this.matchedCards = new ArrayList<>();
/*     */   }
/*     */   
/*     */   private ArrayList<AbstractCard> initializeCards() {
/*  59 */     ArrayList<AbstractCard> retVal = new ArrayList<>();
/*  60 */     ArrayList<AbstractCard> retVal2 = new ArrayList<>();
/*     */     
/*  62 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  63 */       retVal.add(AbstractDungeon.getCard(AbstractCard.CardRarity.RARE).makeCopy());
/*  64 */       retVal.add(AbstractDungeon.getCard(AbstractCard.CardRarity.UNCOMMON).makeCopy());
/*  65 */       retVal.add(AbstractDungeon.getCard(AbstractCard.CardRarity.COMMON).makeCopy());
/*  66 */       retVal.add(AbstractDungeon.returnRandomCurse());
/*  67 */       retVal.add(AbstractDungeon.returnRandomCurse());
/*     */     } else {
/*  69 */       retVal.add(AbstractDungeon.getCard(AbstractCard.CardRarity.RARE).makeCopy());
/*  70 */       retVal.add(AbstractDungeon.getCard(AbstractCard.CardRarity.UNCOMMON).makeCopy());
/*  71 */       retVal.add(AbstractDungeon.getCard(AbstractCard.CardRarity.COMMON).makeCopy());
/*  72 */       retVal.add(AbstractDungeon.returnColorlessCard(AbstractCard.CardRarity.UNCOMMON).makeCopy());
/*  73 */       retVal.add(AbstractDungeon.returnRandomCurse());
/*     */     } 
/*     */     
/*  76 */     retVal.add(AbstractDungeon.player.getStartCardForEvent());
/*     */     
/*  78 */     for (AbstractCard c : retVal) {
/*  79 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  80 */         r.onPreviewObtainCard(c);
/*     */       }
/*  82 */       retVal2.add(c.makeStatEquivalentCopy());
/*     */     } 
/*     */     
/*  85 */     retVal.addAll(retVal2);
/*     */     
/*  87 */     for (AbstractCard c : retVal) {
/*  88 */       c.current_x = Settings.WIDTH / 2.0F;
/*  89 */       c.target_x = c.current_x;
/*  90 */       c.current_y = -300.0F * Settings.scale;
/*  91 */       c.target_y = c.current_y;
/*     */     } 
/*     */     
/*  94 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private enum CUR_SCREEN
/*     */   {
/* 101 */     INTRO, RULE_EXPLANATION, PLAY, COMPLETE, CLEAN_UP;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 106 */     super.update();
/* 107 */     this.cards.update();
/*     */     
/* 109 */     if (this.screen == CUR_SCREEN.PLAY) {
/* 110 */       updateControllerInput();
/* 111 */       updateMatchGameLogic();
/* 112 */     } else if (this.screen == CUR_SCREEN.CLEAN_UP) {
/* 113 */       if (!this.cleanUpCalled) {
/* 114 */         this.cleanUpCalled = true;
/* 115 */         cleanUpCards();
/*     */       } 
/* 117 */       if (this.waitTimer > 0.0F) {
/* 118 */         this.waitTimer -= Gdx.graphics.getDeltaTime();
/* 119 */         if (this.waitTimer < 0.0F) {
/* 120 */           this.waitTimer = 0.0F;
/* 121 */           this.screen = CUR_SCREEN.COMPLETE;
/* 122 */           GenericEventDialog.show();
/* 123 */           this.imageEventText.updateBodyText(MSG_3);
/* 124 */           this.imageEventText.clearRemainingOptions();
/* 125 */           this.imageEventText.setDialogOption(OPTIONS[1]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     if (!GenericEventDialog.waitForInput) {
/* 131 */       buttonEffect(GenericEventDialog.getSelectedOption());
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 136 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 140 */     boolean anyHovered = false;
/* 141 */     int index = 0;
/* 142 */     for (AbstractCard c : this.cards.group) {
/* 143 */       if (c.hb.hovered) {
/* 144 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 147 */       index++;
/*     */     } 
/*     */     
/* 150 */     if (!anyHovered) {
/* 151 */       Gdx.input.setCursorPosition(
/* 152 */           (int)((AbstractCard)this.cards.group.get(0)).hb.cX, Settings.HEIGHT - 
/* 153 */           (int)((AbstractCard)this.cards.group.get(0)).hb.cY);
/*     */     } else {
/* 155 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 156 */         float y = ((AbstractCard)this.cards.group.get(index)).hb.cY + 230.0F * Settings.scale;
/* 157 */         if (y > 865.0F * Settings.scale) {
/* 158 */           y = 290.0F * Settings.scale;
/*     */         }
/* 160 */         Gdx.input.setCursorPosition((int)((AbstractCard)this.cards.group.get(index)).hb.cX, (int)(Settings.HEIGHT - y));
/* 161 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 162 */         float y = ((AbstractCard)this.cards.group.get(index)).hb.cY - 230.0F * Settings.scale;
/* 163 */         if (y < 175.0F * Settings.scale) {
/* 164 */           y = 750.0F * Settings.scale;
/*     */         }
/* 166 */         Gdx.input.setCursorPosition((int)((AbstractCard)this.cards.group.get(index)).hb.cX, (int)(Settings.HEIGHT - y));
/*     */       }
/* 168 */       else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 169 */         float x = ((AbstractCard)this.cards.group.get(index)).hb.cX - 210.0F * Settings.scale;
/* 170 */         if (x < 530.0F * Settings.scale) {
/* 171 */           x = 1270.0F * Settings.scale;
/*     */         }
/* 173 */         Gdx.input.setCursorPosition((int)x, Settings.HEIGHT - (int)((AbstractCard)this.cards.group.get(index)).hb.cY);
/* 174 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 175 */         float x = ((AbstractCard)this.cards.group.get(index)).hb.cX + 210.0F * Settings.scale;
/* 176 */         if (x > 1375.0F * Settings.scale) {
/* 177 */           x = 640.0F * Settings.scale;
/*     */         }
/* 179 */         Gdx.input.setCursorPosition((int)x, Settings.HEIGHT - (int)((AbstractCard)this.cards.group.get(index)).hb.cY);
/*     */       } 
/*     */       
/* 182 */       if (CInputActionSet.select.isJustPressed()) {
/* 183 */         CInputActionSet.select.unpress();
/* 184 */         InputHelper.justClickedLeft = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void cleanUpCards() {
/* 190 */     for (AbstractCard c : this.cards.group) {
/* 191 */       c.targetDrawScale = 0.5F;
/* 192 */       c.target_x = Settings.WIDTH / 2.0F;
/* 193 */       c.target_y = -300.0F * Settings.scale;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateMatchGameLogic() {
/* 198 */     if (this.waitTimer == 0.0F) {
/* 199 */       this.hoveredCard = null;
/* 200 */       for (AbstractCard c : this.cards.group) {
/* 201 */         c.hb.update();
/* 202 */         if (this.hoveredCard == null && c.hb.hovered) {
/* 203 */           c.drawScale = 0.7F;
/* 204 */           c.targetDrawScale = 0.7F;
/* 205 */           this.hoveredCard = c;
/* 206 */           if (InputHelper.justClickedLeft && this.hoveredCard.isFlipped) {
/* 207 */             InputHelper.justClickedLeft = false;
/* 208 */             this.hoveredCard.isFlipped = false;
/* 209 */             if (!this.cardFlipped) {
/* 210 */               this.cardFlipped = true;
/* 211 */               this.chosenCard = this.hoveredCard; continue;
/*     */             } 
/* 213 */             this.cardFlipped = false;
/* 214 */             if (this.chosenCard.cardID.equals(this.hoveredCard.cardID)) {
/* 215 */               this.waitTimer = 1.0F;
/* 216 */               this.chosenCard.targetDrawScale = 0.7F;
/* 217 */               this.chosenCard.target_x = Settings.WIDTH / 2.0F;
/* 218 */               this.chosenCard.target_y = Settings.HEIGHT / 2.0F;
/* 219 */               this.hoveredCard.targetDrawScale = 0.7F;
/* 220 */               this.hoveredCard.target_x = Settings.WIDTH / 2.0F;
/* 221 */               this.hoveredCard.target_y = Settings.HEIGHT / 2.0F; continue;
/*     */             } 
/* 223 */             this.waitTimer = 1.25F;
/* 224 */             this.chosenCard.targetDrawScale = 1.0F;
/* 225 */             this.hoveredCard.targetDrawScale = 1.0F;
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/* 230 */         if (c != this.chosenCard) {
/* 231 */           c.targetDrawScale = 0.5F;
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 236 */       this.waitTimer -= Gdx.graphics.getDeltaTime();
/* 237 */       if (this.waitTimer < 0.0F && !this.gameDone) {
/* 238 */         this.waitTimer = 0.0F;
/*     */ 
/*     */         
/* 241 */         if (this.chosenCard.cardID.equals(this.hoveredCard.cardID)) {
/* 242 */           this.cardsMatched++;
/* 243 */           this.cards.group.remove(this.chosenCard);
/* 244 */           this.cards.group.remove(this.hoveredCard);
/* 245 */           this.matchedCards.add(this.chosenCard.cardID);
/* 246 */           AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(this.chosenCard
/* 247 */                 .makeCopy(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/* 248 */           this.chosenCard = null;
/* 249 */           this.hoveredCard = null;
/*     */         } else {
/* 251 */           this.chosenCard.isFlipped = true;
/* 252 */           this.hoveredCard.isFlipped = true;
/* 253 */           this.chosenCard.targetDrawScale = 0.5F;
/* 254 */           this.hoveredCard.targetDrawScale = 0.5F;
/* 255 */           this.chosenCard = null;
/* 256 */           this.hoveredCard = null;
/*     */         } 
/* 258 */         this.attemptCount--;
/* 259 */         if (this.attemptCount == 0) {
/* 260 */           this.gameDone = true;
/* 261 */           this.waitTimer = 1.0F;
/*     */         } 
/* 263 */       } else if (this.gameDone) {
/*     */         
/* 265 */         this.screen = CUR_SCREEN.CLEAN_UP;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/* 277 */     switch (this.screen) {
/*     */       case INTRO:
/* 279 */         switch (buttonPressed) {
/*     */           case 0:
/* 281 */             this.imageEventText.updateBodyText(MSG_2);
/* 282 */             this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/* 283 */             this.screen = CUR_SCREEN.RULE_EXPLANATION;
/*     */             break;
/*     */         } 
/*     */         
/*     */         break;
/*     */       case RULE_EXPLANATION:
/* 289 */         switch (buttonPressed) {
/*     */           case 0:
/* 291 */             this.imageEventText.removeDialogOption(0);
/* 292 */             GenericEventDialog.hide();
/* 293 */             this.screen = CUR_SCREEN.PLAY;
/* 294 */             placeCards();
/*     */             break;
/*     */         } 
/*     */         
/*     */         break;
/*     */       case COMPLETE:
/* 300 */         logMetricObtainCards("Match and Keep!", this.cardsMatched + " cards matched", this.matchedCards);
/* 301 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void placeCards() {
/* 309 */     for (int i = 0; i < this.cards.size(); i++) {
/* 310 */       ((AbstractCard)this.cards.group.get(i)).target_x = (i % 4) * 210.0F * Settings.xScale + 640.0F * Settings.xScale;
/* 311 */       ((AbstractCard)this.cards.group.get(i)).target_y = (i % 3) * -230.0F * Settings.yScale + 750.0F * Settings.yScale;
/* 312 */       ((AbstractCard)this.cards.group.get(i)).targetDrawScale = 0.5F;
/* 313 */       ((AbstractCard)this.cards.group.get(i)).isFlipped = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 319 */     this.cards.render(sb);
/* 320 */     if (this.chosenCard != null) {
/* 321 */       this.chosenCard.render(sb);
/*     */     }
/* 323 */     if (this.hoveredCard != null) {
/* 324 */       this.hoveredCard.render(sb);
/*     */     }
/*     */     
/* 327 */     if (this.screen == CUR_SCREEN.PLAY)
/* 328 */       FontHelper.renderSmartText(sb, FontHelper.panelNameFont, OPTIONS[3] + this.attemptCount, 780.0F * Settings.scale, 80.0F * Settings.scale, 2000.0F * Settings.scale, 0.0F, Color.WHITE); 
/*     */   }
/*     */   
/*     */   public void renderAboveTopPanel(SpriteBatch sb) {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\GremlinMatchGame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */