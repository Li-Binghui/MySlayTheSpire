/*     */ package com.megacrit.cardcrawl.scenes;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.vfx.scene.CeilingDustEffect;
import com.megacrit.cardcrawl.vfx.scene.FireFlyEffect;

import java.util.ArrayList;
import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TheCityScene
/*     */   extends AbstractScene
/*     */ {
/*     */   private final TextureAtlas.AtlasRegion bg;
/*     */   private final TextureAtlas.AtlasRegion bgGlow;
/*     */   private final TextureAtlas.AtlasRegion bgGlow2;
/*     */   private final TextureAtlas.AtlasRegion bg2;
/*     */   private final TextureAtlas.AtlasRegion bg2Glow;
/*     */   private final TextureAtlas.AtlasRegion floor;
/*     */   private final TextureAtlas.AtlasRegion ceiling;
/*     */   private final TextureAtlas.AtlasRegion wall;
/*     */   private final TextureAtlas.AtlasRegion chains;
/*     */   private final TextureAtlas.AtlasRegion chainsGlow;
/*     */   private final TextureAtlas.AtlasRegion pillar1;
/*  34 */   private Color overlayColor = Color.WHITE.cpy(); private final TextureAtlas.AtlasRegion pillar2; private final TextureAtlas.AtlasRegion pillar3; private final TextureAtlas.AtlasRegion pillar4; private final TextureAtlas.AtlasRegion pillar5; private final TextureAtlas.AtlasRegion throne; private final TextureAtlas.AtlasRegion throneGlow; private final TextureAtlas.AtlasRegion mg; private final TextureAtlas.AtlasRegion mgGlow; private final TextureAtlas.AtlasRegion mg2; private final TextureAtlas.AtlasRegion fg; private final TextureAtlas.AtlasRegion fgGlow; private final TextureAtlas.AtlasRegion fg2;
/*  35 */   private Color whiteColor = Color.WHITE.cpy();
/*  36 */   private Color yellowTint = new Color(1.0F, 1.0F, 0.9F, 1.0F); private boolean renderAltBg; private boolean renderMg; private boolean renderMgGlow; private boolean renderMgAlt; private boolean renderWall; private boolean renderChains;
/*     */   private boolean renderThrone;
/*     */   private boolean renderFg2;
/*     */   private boolean darkDay;
/*  40 */   private PillarConfig pillarConfig = PillarConfig.OPEN;
/*     */ 
/*     */   
/*  43 */   private float ceilingDustTimer = 1.0F;
/*  44 */   private ArrayList<FireFlyEffect> fireFlies = new ArrayList<>(); private boolean hasFlies;
/*     */   private boolean blueFlies;
/*     */   
/*     */   public TheCityScene() {
/*  48 */     super("cityScene/scene.atlas");
/*  49 */     this.bg = this.atlas.findRegion("mod/bg1");
/*  50 */     this.bgGlow = this.atlas.findRegion("mod/bgGlowv2");
/*  51 */     this.bgGlow2 = this.atlas.findRegion("mod/bgGlowBlur");
/*  52 */     this.bg2 = this.atlas.findRegion("mod/bg2");
/*  53 */     this.bg2Glow = this.atlas.findRegion("mod/bg2Glow");
/*  54 */     this.floor = this.atlas.findRegion("mod/floor");
/*  55 */     this.ceiling = this.atlas.findRegion("mod/ceiling");
/*  56 */     this.wall = this.atlas.findRegion("mod/wall");
/*  57 */     this.chains = this.atlas.findRegion("mod/chains");
/*  58 */     this.chainsGlow = this.atlas.findRegion("mod/chainsGlow");
/*  59 */     this.pillar1 = this.atlas.findRegion("mod/p1");
/*  60 */     this.pillar2 = this.atlas.findRegion("mod/p2");
/*  61 */     this.pillar3 = this.atlas.findRegion("mod/p3");
/*  62 */     this.pillar4 = this.atlas.findRegion("mod/p4");
/*  63 */     this.pillar5 = this.atlas.findRegion("mod/p5");
/*  64 */     this.throne = this.atlas.findRegion("mod/throne");
/*  65 */     this.throneGlow = this.atlas.findRegion("mod/throneGlow");
/*  66 */     this.mg = this.atlas.findRegion("mod/mg1");
/*  67 */     this.mgGlow = this.atlas.findRegion("mod/mg1Glow");
/*  68 */     this.mg2 = this.atlas.findRegion("mod/mg2");
/*  69 */     this.fg = this.atlas.findRegion("mod/fg");
/*  70 */     this.fgGlow = this.atlas.findRegion("mod/fgGlow");
/*  71 */     this.fg2 = this.atlas.findRegion("mod/fgHideWindow");
/*     */     
/*  73 */     this.ambianceName = "AMBIANCE_CITY";
/*  74 */     fadeInAmbiance();
/*     */   }
/*     */   
/*     */   private enum PillarConfig {
/*  78 */     OPEN, SIDES_ONLY, FULL, LEFT_1, LEFT_2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  83 */     super.update();
/*  84 */     updateFireFlies();
/*  85 */     if (!(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.RestRoom) && 
/*  86 */       !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.EventRoom)) {
/*  87 */       updateParticles();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateFireFlies() {
/*  95 */     for (Iterator<FireFlyEffect> e = this.fireFlies.iterator(); e.hasNext(); ) {
/*  96 */       FireFlyEffect effect = e.next();
/*  97 */       effect.update();
/*  98 */       if (effect.isDone) {
/*  99 */         e.remove();
/*     */       }
/*     */     } 
/*     */     
/* 103 */     if (this.fireFlies.size() < 9 && !Settings.DISABLE_EFFECTS && 
/* 104 */       MathUtils.randomBoolean(0.1F)) {
/* 105 */       if (this.blueFlies) {
/* 106 */         this.fireFlies.add(new FireFlyEffect(new Color(
/*     */ 
/*     */                 
/* 109 */                 MathUtils.random(0.1F, 0.2F), 
/* 110 */                 MathUtils.random(0.6F, 0.8F), 
/* 111 */                 MathUtils.random(0.8F, 1.0F), 1.0F)));
/*     */       } else {
/*     */         
/* 114 */         this.fireFlies.add(new FireFlyEffect(new Color(
/*     */ 
/*     */                 
/* 117 */                 MathUtils.random(0.8F, 1.0F), 
/* 118 */                 MathUtils.random(0.5F, 0.8F), 
/* 119 */                 MathUtils.random(0.3F, 0.5F), 1.0F)));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateParticles() {
/* 127 */     if (Settings.DISABLE_EFFECTS) {
/*     */       return;
/*     */     }
/*     */     
/* 131 */     this.ceilingDustTimer -= Gdx.graphics.getDeltaTime();
/* 132 */     if (this.ceilingDustTimer < 0.0F) {
/* 133 */       int roll = MathUtils.random(4);
/* 134 */       if (roll == 0) {
/* 135 */         AbstractDungeon.effectsQueue.add(new CeilingDustEffect());
/* 136 */         playDustSfx(false);
/* 137 */       } else if (roll == 1) {
/* 138 */         AbstractDungeon.effectsQueue.add(new CeilingDustEffect());
/* 139 */         AbstractDungeon.effectsQueue.add(new CeilingDustEffect());
/* 140 */         playDustSfx(false);
/*     */       } else {
/* 142 */         AbstractDungeon.effectsQueue.add(new CeilingDustEffect());
/* 143 */         AbstractDungeon.effectsQueue.add(new CeilingDustEffect());
/* 144 */         AbstractDungeon.effectsQueue.add(new CeilingDustEffect());
/* 145 */         if (!Settings.isBackgrounded) {
/* 146 */           playDustSfx(true);
/*     */         }
/*     */       } 
/* 149 */       this.ceilingDustTimer = MathUtils.random(0.5F, 60.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDustSfx(boolean boom) {
/* 154 */     if (boom) {
/* 155 */       int roll = MathUtils.random(2);
/* 156 */       if (roll == 0) {
/* 157 */         CardCrawlGame.sound.play("CEILING_BOOM_1", 0.2F);
/* 158 */       } else if (roll == 1) {
/* 159 */         CardCrawlGame.sound.play("CEILING_BOOM_2", 0.2F);
/*     */       } else {
/* 161 */         CardCrawlGame.sound.play("CEILING_BOOM_3", 0.2F);
/*     */       } 
/*     */     } else {
/* 164 */       int roll = MathUtils.random(2);
/* 165 */       if (roll == 0) {
/* 166 */         CardCrawlGame.sound.play("CEILING_DUST_1", 0.2F);
/* 167 */       } else if (roll == 1) {
/* 168 */         CardCrawlGame.sound.play("CEILING_DUST_2", 0.2F);
/*     */       } else {
/* 170 */         CardCrawlGame.sound.play("CEILING_DUST_3", 0.2F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomizeScene() {
/* 177 */     this.hasFlies = MathUtils.randomBoolean();
/* 178 */     this.blueFlies = MathUtils.randomBoolean();
/*     */ 
/*     */     
/* 181 */     this.overlayColor.r = MathUtils.random(0.8F, 0.9F);
/* 182 */     this.overlayColor.g = MathUtils.random(0.8F, 0.9F);
/* 183 */     this.overlayColor.b = MathUtils.random(0.95F, 1.0F);
/*     */     
/* 185 */     this.darkDay = MathUtils.randomBoolean(0.33F);
/*     */     
/* 187 */     if (this.darkDay) {
/* 188 */       this.overlayColor.r = 0.6F;
/* 189 */       this.overlayColor.g = MathUtils.random(0.7F, 0.8F);
/* 190 */       this.overlayColor.b = MathUtils.random(0.8F, 0.95F);
/*     */     } 
/*     */     
/* 193 */     this.renderAltBg = MathUtils.randomBoolean();
/*     */     
/* 195 */     this.renderMg = true;
/* 196 */     if (this.renderMg) {
/* 197 */       this.renderMgAlt = MathUtils.randomBoolean();
/* 198 */       if (!this.renderMgAlt) {
/* 199 */         this.renderMgGlow = MathUtils.randomBoolean();
/*     */       }
/*     */     } 
/*     */     
/* 203 */     if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 204 */       this.renderWall = false;
/*     */     } else {
/* 206 */       this.renderWall = (MathUtils.random(4) == 4);
/*     */     } 
/* 208 */     if (this.renderWall) {
/* 209 */       this.renderChains = MathUtils.randomBoolean();
/*     */     } else {
/* 211 */       this.renderChains = false;
/*     */     } 
/*     */     
/* 214 */     this.renderFg2 = MathUtils.randomBoolean();
/*     */     
/* 216 */     if (this.renderWall) {
/* 217 */       int roll = MathUtils.random(2);
/* 218 */       if (roll == 0) {
/* 219 */         this.pillarConfig = PillarConfig.OPEN;
/* 220 */       } else if (roll == 1) {
/* 221 */         this.pillarConfig = PillarConfig.LEFT_1;
/*     */       } else {
/* 223 */         this.pillarConfig = PillarConfig.LEFT_2;
/*     */       } 
/*     */     } else {
/* 226 */       int roll = MathUtils.random(2);
/* 227 */       if (roll == 0) {
/* 228 */         this.pillarConfig = PillarConfig.OPEN;
/* 229 */       } else if (roll == 1) {
/* 230 */         this.pillarConfig = PillarConfig.SIDES_ONLY;
/*     */       } else {
/* 232 */         this.pillarConfig = PillarConfig.FULL;
/*     */       } 
/*     */     } 
/*     */     
/* 236 */     if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss && (AbstractDungeon.getCurrRoom()).monsters
/* 237 */       .getMonster("TheCollector") != null) {
/* 238 */       this.renderThrone = true;
/*     */     } else {
/* 240 */       this.renderThrone = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextRoom(AbstractRoom room) {
/* 250 */     super.nextRoom(room);
/* 251 */     this.fireFlies.clear();
/* 252 */     randomizeScene();
/* 253 */     if (room instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 254 */       CardCrawlGame.music.silenceBGM();
/*     */     }
/* 256 */     fadeInAmbiance();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderCombatRoomBg(SpriteBatch sb) {
/* 261 */     sb.setColor(this.overlayColor);
/*     */ 
/*     */     
/* 264 */     renderAtlasRegionIf(sb, this.bg, true);
/* 265 */     sb.setBlendFunction(770, 1);
/* 266 */     renderAtlasRegionIf(sb, this.bgGlow, true);
/* 267 */     if (this.darkDay) {
/* 268 */       sb.setColor(Color.WHITE);
/* 269 */       renderAtlasRegionIf(sb, this.bgGlow2, true);
/* 270 */       renderAtlasRegionIf(sb, this.bgGlow2, true);
/*     */     } 
/* 272 */     sb.setBlendFunction(770, 771);
/*     */ 
/*     */     
/* 275 */     renderAtlasRegionIf(sb, this.bg2, this.renderAltBg);
/* 276 */     sb.setBlendFunction(770, 1);
/* 277 */     renderAtlasRegionIf(sb, this.bg2Glow, this.renderAltBg);
/* 278 */     sb.setBlendFunction(770, 771);
/*     */ 
/*     */     
/* 281 */     sb.setColor(this.overlayColor);
/* 282 */     renderAtlasRegionIf(sb, this.floor, true);
/* 283 */     renderAtlasRegionIf(sb, this.ceiling, true);
/*     */ 
/*     */     
/* 286 */     renderAtlasRegionIf(sb, this.wall, this.renderWall);
/* 287 */     renderAtlasRegionIf(sb, this.chains, this.renderChains);
/* 288 */     if (this.renderChains) {
/* 289 */       sb.setBlendFunction(770, 1);
/* 290 */       this.whiteColor.a = MathUtils.cosDeg((float)(System.currentTimeMillis() / 1L % 360L)) / 10.0F + 0.9F;
/* 291 */       sb.setColor(this.whiteColor);
/* 292 */       renderAtlasRegionIf(sb, this.chainsGlow, true);
/* 293 */       renderAtlasRegionIf(sb, this.chainsGlow, true);
/* 294 */       sb.setBlendFunction(770, 771);
/* 295 */       sb.setColor(this.overlayColor);
/*     */     } 
/*     */ 
/*     */     
/* 299 */     renderAtlasRegionIf(sb, this.mg, this.renderMg);
/* 300 */     sb.setBlendFunction(770, 1);
/* 301 */     if (this.renderMgGlow) {
/* 302 */       this.whiteColor.a = MathUtils.cosDeg((float)(System.currentTimeMillis() / 10L % 360L)) / 2.0F + 0.5F;
/* 303 */       sb.setColor(this.whiteColor);
/* 304 */       renderAtlasRegionIf(sb, this.mgGlow, this.renderMg);
/* 305 */       renderAtlasRegionIf(sb, this.mgGlow, this.renderMg);
/* 306 */       sb.setColor(this.yellowTint);
/*     */     } else {
/* 308 */       renderAtlasRegionIf(sb, this.mgGlow, this.renderMg);
/*     */     } 
/* 310 */     sb.setBlendFunction(770, 771);
/* 311 */     renderAtlasRegionIf(sb, this.mg2, this.renderMgAlt);
/*     */ 
/*     */     
/* 314 */     switch (this.pillarConfig) {
/*     */ 
/*     */       
/*     */       case SIDES_ONLY:
/* 318 */         renderAtlasRegionIf(sb, this.pillar1, true);
/* 319 */         renderAtlasRegionIf(sb, this.pillar5, true);
/*     */         break;
/*     */       case FULL:
/* 322 */         renderAtlasRegionIf(sb, this.pillar1, true);
/* 323 */         renderAtlasRegionIf(sb, this.pillar2, true);
/* 324 */         renderAtlasRegionIf(sb, this.pillar3, true);
/* 325 */         renderAtlasRegionIf(sb, this.pillar4, true);
/* 326 */         renderAtlasRegionIf(sb, this.pillar5, true);
/*     */         break;
/*     */       case LEFT_1:
/* 329 */         renderAtlasRegionIf(sb, this.pillar1, true);
/*     */         break;
/*     */       case LEFT_2:
/* 332 */         renderAtlasRegionIf(sb, this.pillar1, true);
/* 333 */         renderAtlasRegionIf(sb, this.pillar2, true);
/*     */         break;
/*     */     } 
/*     */     
/* 337 */     renderAtlasRegionIf(sb, this.throne, this.renderThrone);
/* 338 */     sb.setBlendFunction(770, 1);
/* 339 */     renderAtlasRegionIf(sb, this.throneGlow, this.renderThrone);
/* 340 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderCombatRoomFg(SpriteBatch sb) {
/* 345 */     if (!this.isCamp && this.hasFlies) {
/* 346 */       for (FireFlyEffect e : this.fireFlies) {
/* 347 */         e.render(sb);
/*     */       }
/*     */     }
/* 350 */     sb.setColor(Color.WHITE);
/* 351 */     renderAtlasRegionIf(sb, this.fg, true);
/* 352 */     sb.setBlendFunction(770, 1);
/* 353 */     renderAtlasRegionIf(sb, this.fgGlow, true);
/* 354 */     sb.setBlendFunction(770, 771);
/* 355 */     renderAtlasRegionIf(sb, this.fg2, this.renderFg2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderCampfireRoom(SpriteBatch sb) {
/* 360 */     sb.setColor(Color.WHITE);
/* 361 */     renderAtlasRegionIf(sb, this.campfireBg, true);
/* 362 */     sb.setBlendFunction(770, 1);
/* 363 */     this.whiteColor.a = MathUtils.cosDeg((float)(System.currentTimeMillis() / 3L % 360L)) / 10.0F + 0.8F;
/* 364 */     sb.setColor(this.whiteColor);
/* 365 */     renderQuadrupleSize(sb, this.campfireGlow, !CampfireUI.hidden);
/* 366 */     sb.setBlendFunction(770, 771);
/* 367 */     sb.setColor(Color.WHITE);
/* 368 */     renderAtlasRegionIf(sb, this.campfireKindling, true);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\scenes\TheCityScene.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */