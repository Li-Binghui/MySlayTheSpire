/*     */ package com.megacrit.cardcrawl.cards;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SoulGroup
/*     */ {
/*  18 */   private static final Logger logger = LogManager.getLogger(SoulGroup.class.getName());
/*  19 */   private ArrayList<Soul> souls = new ArrayList<>();
/*     */   private static final int DEFAULT_SOUL_CACHE = 20;
/*     */   
/*     */   public SoulGroup() {
/*  23 */     for (int i = 0; i < 20; i++) {
/*  24 */       this.souls.add(new Soul());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discard(AbstractCard card, boolean visualOnly) {
/*  35 */     boolean needMoreSouls = true;
/*  36 */     for (Soul s : this.souls) {
/*  37 */       if (s.isReadyForReuse) {
/*  38 */         card.untip();
/*  39 */         card.unhover();
/*  40 */         s.discard(card, visualOnly);
/*  41 */         needMoreSouls = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  46 */     if (needMoreSouls) {
/*  47 */       logger.info("Not enough souls, creating...");
/*  48 */       Soul s = new Soul();
/*  49 */       s.discard(card, visualOnly);
/*  50 */       this.souls.add(s);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discard(AbstractCard card) {
/*  61 */     discard(card, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void empower(AbstractCard card) {
/*  70 */     boolean needMoreSouls = true;
/*  71 */     for (Soul s : this.souls) {
/*  72 */       if (s.isReadyForReuse) {
/*  73 */         card.untip();
/*  74 */         card.unhover();
/*  75 */         s.empower(card);
/*  76 */         needMoreSouls = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  81 */     if (needMoreSouls) {
/*  82 */       logger.info("Not enough souls, creating...");
/*  83 */       Soul s = new Soul();
/*  84 */       s.empower(card);
/*  85 */       this.souls.add(s);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void obtain(AbstractCard card, boolean obtainCard) {
/*  95 */     CardCrawlGame.sound.play("CARD_OBTAIN");
/*  96 */     boolean needMoreSouls = true;
/*  97 */     for (Soul s : this.souls) {
/*  98 */       if (s.isReadyForReuse) {
/*  99 */         if (obtainCard) {
/* 100 */           s.obtain(card);
/*     */         }
/* 102 */         needMoreSouls = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 107 */     if (needMoreSouls) {
/* 108 */       logger.info("Not enough souls, creating...");
/* 109 */       Soul s = new Soul();
/* 110 */       if (obtainCard) {
/* 111 */         s.obtain(card);
/*     */       }
/* 113 */       this.souls.add(s);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void shuffle(AbstractCard card, boolean isInvisible) {
/* 123 */     card.untip();
/* 124 */     card.unhover();
/* 125 */     card.darken(true);
/* 126 */     card.shrink(true);
/*     */     
/* 128 */     boolean needMoreSouls = true;
/* 129 */     for (Soul s : this.souls) {
/* 130 */       if (s.isReadyForReuse) {
/* 131 */         s.shuffle(card, isInvisible);
/* 132 */         needMoreSouls = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 137 */     if (needMoreSouls) {
/* 138 */       logger.info("Not enough souls, creating...");
/* 139 */       Soul s = new Soul();
/* 140 */       s.shuffle(card, isInvisible);
/* 141 */       this.souls.add(s);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onToBottomOfDeck(AbstractCard card) {
/* 146 */     boolean needMoreSouls = true;
/* 147 */     for (Soul s : this.souls) {
/* 148 */       if (s.isReadyForReuse) {
/* 149 */         card.untip();
/* 150 */         card.unhover();
/* 151 */         s.onToBottomOfDeck(card);
/* 152 */         needMoreSouls = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 157 */     if (needMoreSouls) {
/* 158 */       logger.info("Not enough souls, creating...");
/* 159 */       Soul s = new Soul();
/* 160 */       s.onToBottomOfDeck(card);
/* 161 */       this.souls.add(s);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onToDeck(AbstractCard card, boolean randomSpot, boolean visualOnly) {
/* 166 */     boolean needMoreSouls = true;
/* 167 */     for (Soul s : this.souls) {
/* 168 */       if (s.isReadyForReuse) {
/* 169 */         card.untip();
/* 170 */         card.unhover();
/* 171 */         s.onToDeck(card, randomSpot, visualOnly);
/* 172 */         needMoreSouls = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 177 */     if (needMoreSouls) {
/* 178 */       logger.info("Not enough souls, creating...");
/* 179 */       Soul s = new Soul();
/* 180 */       s.onToDeck(card, randomSpot, visualOnly);
/* 181 */       this.souls.add(s);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onToDeck(AbstractCard card, boolean randomSpot) {
/* 186 */     onToDeck(card, randomSpot, false);
/*     */   }
/*     */   
/*     */   public void update() {
/* 190 */     for (Soul s : this.souls) {
/* 191 */       if (!s.isReadyForReuse) {
/* 192 */         s.update();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 198 */     for (Soul s : this.souls) {
/* 199 */       if (!s.isReadyForReuse) {
/* 200 */         s.render(sb);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove(AbstractCard card) {
/* 206 */     for (Iterator<Soul> s = this.souls.iterator(); s.hasNext(); ) {
/* 207 */       Soul derp = s.next();
/* 208 */       if (derp.card == card) {
/* 209 */         s.remove();
/* 210 */         logger.info(derp + " removed.");
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isActive() {
/* 217 */     for (Soul s : (AbstractDungeon.getCurrRoom()).souls.souls) {
/* 218 */       if (!s.isReadyForReuse) {
/* 219 */         return true;
/*     */       }
/*     */     } 
/* 222 */     return false;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\SoulGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */