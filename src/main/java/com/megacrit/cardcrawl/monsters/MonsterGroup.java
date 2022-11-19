/*     */ package com.megacrit.cardcrawl.monsters;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.ui.buttons.PeekButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MonsterGroup
/*     */ {
/*  25 */   private static final Logger logger = LogManager.getLogger(MonsterGroup.class.getName());
/*  26 */   public ArrayList<AbstractMonster> monsters = new ArrayList<>();
/*  27 */   public AbstractMonster hoveredMonster = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterGroup(AbstractMonster[] input) {
/*  35 */     Collections.addAll(this.monsters, input);
/*     */   }
/*     */   
/*     */   public void addMonster(int newIndex, AbstractMonster m) {
/*  39 */     if (newIndex < 0) {
/*  40 */       newIndex = 0;
/*     */     }
/*  42 */     this.monsters.add(newIndex, m);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void addMonster(AbstractMonster m) {
/*  47 */     this.monsters.add(m);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void addSpawnedMonster(AbstractMonster m) {
/*  52 */     this.monsters.add(0, m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterGroup(AbstractMonster m) {
/*  61 */     this(new AbstractMonster[] { m });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showIntent() {
/*  68 */     for (AbstractMonster m : this.monsters) {
/*  69 */       m.createIntent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  77 */     for (AbstractMonster m : this.monsters) {
/*  78 */       m.init();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(AbstractMonster m) {
/*  86 */     this.monsters.add(m);
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  91 */     if (AbstractDungeon.loading_post_combat) {
/*     */       return;
/*     */     }
/*     */     
/*  95 */     for (AbstractMonster m : this.monsters) {
/*  96 */       m.usePreBattleAction();
/*  97 */       m.useUniversalPreBattleAction();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean areMonstersDead() {
/* 102 */     for (AbstractMonster m : this.monsters) {
/* 103 */       if (!m.isDead && !m.escaped) {
/* 104 */         return false;
/*     */       }
/*     */     } 
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean areMonstersBasicallyDead() {
/* 116 */     for (AbstractMonster m : this.monsters) {
/* 117 */       if (!m.isDying && !m.isEscaping) {
/* 118 */         return false;
/*     */       }
/*     */     } 
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyPreTurnLogic() {
/* 128 */     for (AbstractMonster m : this.monsters) {
/* 129 */       if (!m.isDying && !m.isEscaping) {
/* 130 */         if (!m.hasPower("Barricade")) {
/* 131 */           m.loseBlock();
/*     */         }
/* 133 */         m.applyStartOfTurnPowers();
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
/*     */ 
/*     */   
/*     */   public AbstractMonster getMonster(String id) {
/* 147 */     for (AbstractMonster m : this.monsters) {
/* 148 */       if (m.id.equals(id)) {
/* 149 */         return m;
/*     */       }
/*     */     } 
/*     */     
/* 153 */     logger.info("MONSTER GROUP getMonster(): Could not find monster: " + id);
/* 154 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void queueMonsters() {
/* 161 */     for (AbstractMonster m : this.monsters) {
/* 162 */       if (!m.isDeadOrEscaped() || m.halfDead) {
/* 163 */         AbstractDungeon.actionManager.monsterQueue.add(new MonsterQueueItem(m));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean haveMonstersEscaped() {
/* 174 */     for (AbstractMonster m : this.monsters) {
/* 175 */       if (!m.escaped) {
/* 176 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 180 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isMonsterEscaping() {
/* 184 */     for (AbstractMonster m : this.monsters) {
/* 185 */       if (m.nextMove == 99) {
/* 186 */         return true;
/*     */       }
/*     */     } 
/* 189 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasMonsterEscaped() {
/* 196 */     for (AbstractMonster m : this.monsters) {
/* 197 */       if (m.isEscaping) {
/* 198 */         return true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 203 */     if (CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheCity) {
/* 204 */       return true;
/*     */     }
/* 206 */     return false;
/*     */   }
/*     */   
/*     */   public AbstractMonster getRandomMonster() {
/* 210 */     return getRandomMonster(null, false);
/*     */   }
/*     */   
/*     */   public AbstractMonster getRandomMonster(boolean aliveOnly) {
/* 214 */     return getRandomMonster(null, aliveOnly);
/*     */   }
/*     */   
/*     */   public AbstractMonster getRandomMonster(AbstractMonster exception, boolean aliveOnly, Random rng) {
/* 218 */     if (areMonstersBasicallyDead()) {
/* 219 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 223 */     if (exception == null) {
/* 224 */       if (aliveOnly) {
/* 225 */         ArrayList<AbstractMonster> arrayList = new ArrayList<>();
/* 226 */         for (AbstractMonster m : this.monsters) {
/* 227 */           if (!m.halfDead && !m.isDying && !m.isEscaping) {
/* 228 */             arrayList.add(m);
/*     */           }
/*     */         } 
/*     */         
/* 232 */         if (arrayList.size() <= 0) {
/* 233 */           return null;
/*     */         }
/* 235 */         return arrayList.get(rng.random(0, arrayList.size() - 1));
/*     */       } 
/*     */       
/* 238 */       return this.monsters.get(rng.random(0, this.monsters.size() - 1));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     if (this.monsters.size() == 1) {
/* 246 */       return this.monsters.get(0);
/*     */     }
/*     */     
/* 249 */     if (aliveOnly) {
/* 250 */       ArrayList<AbstractMonster> arrayList = new ArrayList<>();
/* 251 */       for (AbstractMonster m : this.monsters) {
/* 252 */         if (!m.halfDead && !m.isDying && !m.isEscaping && !exception.equals(m)) {
/* 253 */           arrayList.add(m);
/*     */         }
/*     */       } 
/* 256 */       if (arrayList.size() == 0) {
/* 257 */         return null;
/*     */       }
/*     */       
/* 260 */       return arrayList.get(rng.random(0, arrayList.size() - 1));
/*     */     } 
/*     */     
/* 263 */     ArrayList<AbstractMonster> tmp = new ArrayList<>();
/* 264 */     for (AbstractMonster m : this.monsters) {
/* 265 */       if (!exception.equals(m)) {
/* 266 */         tmp.add(m);
/*     */       }
/*     */     } 
/* 269 */     return tmp.get(rng.random(0, tmp.size() - 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractMonster getRandomMonster(AbstractMonster exception, boolean aliveOnly) {
/* 276 */     if (areMonstersBasicallyDead()) {
/* 277 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 281 */     if (exception == null) {
/* 282 */       if (aliveOnly) {
/* 283 */         ArrayList<AbstractMonster> arrayList = new ArrayList<>();
/* 284 */         for (AbstractMonster m : this.monsters) {
/* 285 */           if (!m.halfDead && !m.isDying && !m.isEscaping) {
/* 286 */             arrayList.add(m);
/*     */           }
/*     */         } 
/*     */         
/* 290 */         if (arrayList.size() <= 0) {
/* 291 */           return null;
/*     */         }
/* 293 */         return arrayList.get(MathUtils.random(0, arrayList.size() - 1));
/*     */       } 
/*     */       
/* 296 */       return this.monsters.get(MathUtils.random(0, this.monsters.size() - 1));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 303 */     if (this.monsters.size() == 1) {
/* 304 */       return this.monsters.get(0);
/*     */     }
/*     */     
/* 307 */     if (aliveOnly) {
/* 308 */       ArrayList<AbstractMonster> arrayList = new ArrayList<>();
/* 309 */       for (AbstractMonster m : this.monsters) {
/* 310 */         if (!m.halfDead && !m.isDying && !m.isEscaping && !exception.equals(m)) {
/* 311 */           arrayList.add(m);
/*     */         }
/*     */       } 
/* 314 */       if (arrayList.size() == 0) {
/* 315 */         return null;
/*     */       }
/*     */       
/* 318 */       return arrayList.get(MathUtils.random(0, arrayList.size() - 1));
/*     */     } 
/*     */     
/* 321 */     ArrayList<AbstractMonster> tmp = new ArrayList<>();
/* 322 */     for (AbstractMonster m : this.monsters) {
/* 323 */       if (!exception.equals(m)) {
/* 324 */         tmp.add(m);
/*     */       }
/*     */     } 
/* 327 */     return tmp.get(MathUtils.random(0, tmp.size() - 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 336 */     for (AbstractMonster m : this.monsters) {
/* 337 */       m.update();
/*     */     }
/*     */     
/* 340 */     if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.DEATH) {
/*     */       
/* 342 */       this.hoveredMonster = null;
/* 343 */       for (AbstractMonster m : this.monsters) {
/* 344 */         if (!m.isDying && !m.isEscaping) {
/* 345 */           m.hb.update();
/* 346 */           m.intentHb.update();
/* 347 */           m.healthHb.update();
/* 348 */           if ((m.hb.hovered || m.intentHb.hovered || m.healthHb.hovered) && !AbstractDungeon.player.isDraggingCard) {
/*     */             
/* 350 */             this.hoveredMonster = m;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 356 */       if (this.hoveredMonster == null) {
/* 357 */         AbstractDungeon.player.hoverEnemyWaitTimer = -1.0F;
/*     */       }
/*     */     } else {
/* 360 */       this.hoveredMonster = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateAnimations() {
/* 365 */     for (AbstractMonster m : this.monsters) {
/* 366 */       m.updatePowers();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldFlipVfx() {
/* 372 */     if (AbstractDungeon.lastCombatMetricKey.equals("Shield and Spear") && ((AbstractMonster)this.monsters.get(1)).isDying) {
/* 373 */       return true;
/*     */     }
/* 375 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void escape() {
/* 382 */     for (AbstractMonster m : this.monsters) {
/* 383 */       m.escape();
/*     */     }
/*     */   }
/*     */   
/*     */   public void unhover() {
/* 388 */     for (AbstractMonster m : this.monsters) {
/* 389 */       m.unhover();
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 394 */     if (this.hoveredMonster != null && !this.hoveredMonster.isDead && !this.hoveredMonster.escaped && AbstractDungeon.player.hoverEnemyWaitTimer < 0.0F)
/*     */     {
/* 396 */       if (!AbstractDungeon.isScreenUp || PeekButton.isPeeking) {
/* 397 */         this.hoveredMonster.renderTip(sb);
/*     */       }
/*     */     }
/*     */     
/* 401 */     for (AbstractMonster m : this.monsters) {
/* 402 */       m.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   public void applyEndOfTurnPowers() {
/* 407 */     for (AbstractMonster m : this.monsters) {
/* 408 */       if (!m.isDying && !m.isEscaping) {
/* 409 */         m.applyEndOfTurnTriggers();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 414 */     for (AbstractPower p : AbstractDungeon.player.powers) {
/* 415 */       p.atEndOfRound();
/*     */     }
/*     */     
/* 418 */     for (AbstractMonster m : this.monsters) {
/* 419 */       if (!m.isDying && !m.isEscaping) {
/* 420 */         for (AbstractPower p : m.powers) {
/* 421 */           p.atEndOfRound();
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderReticle(SpriteBatch sb) {
/* 428 */     for (AbstractMonster m : this.monsters) {
/* 429 */       if (!m.isDying && !m.isEscaping) {
/* 430 */         m.renderReticle(sb);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayList<String> getMonsterNames() {
/* 436 */     ArrayList<String> arr = new ArrayList<>();
/* 437 */     for (AbstractMonster m : this.monsters) {
/* 438 */       arr.add(m.id);
/*     */     }
/* 440 */     return arr;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\MonsterGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */