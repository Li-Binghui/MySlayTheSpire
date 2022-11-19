/*     */ package com.megacrit.cardcrawl.scenes;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.vfx.scene.*;

import java.util.ArrayList;
import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TheBottomScene
/*     */   extends AbstractScene
/*     */ {
/*     */   private boolean renderLeftWall;
/*     */   private boolean renderSolidMid;
/*     */   private boolean renderHollowMid;
/*     */   private TextureAtlas.AtlasRegion fg;
/*     */   private TextureAtlas.AtlasRegion mg;
/*     */   private TextureAtlas.AtlasRegion leftWall;
/*     */   private TextureAtlas.AtlasRegion hollowWall;
/*     */   private TextureAtlas.AtlasRegion solidWall;
/*     */   private boolean renderCeilingMod1;
/*     */   private boolean renderCeilingMod2;
/*  36 */   private Color overlayColor = Color.WHITE.cpy(); private boolean renderCeilingMod3; private boolean renderCeilingMod4; private boolean renderCeilingMod5; private boolean renderCeilingMod6; private TextureAtlas.AtlasRegion ceiling; private TextureAtlas.AtlasRegion ceilingMod1; private TextureAtlas.AtlasRegion ceilingMod2; private TextureAtlas.AtlasRegion ceilingMod3; private TextureAtlas.AtlasRegion ceilingMod4; private TextureAtlas.AtlasRegion ceilingMod5; private TextureAtlas.AtlasRegion ceilingMod6;
/*  37 */   private Color whiteColor = Color.WHITE.cpy();
/*     */ 
/*     */   
/*  40 */   private ArrayList<DustEffect> dust = new ArrayList<>();
/*  41 */   private ArrayList<BottomFogEffect> fog = new ArrayList<>();
/*  42 */   private ArrayList<InteractableTorchEffect> torches = new ArrayList<>();
/*     */   private static final int DUST_AMT = 24;
/*     */   
/*     */   public TheBottomScene() {
/*  46 */     super("bottomScene/scene.atlas");
/*  47 */     this.fg = this.atlas.findRegion("mod/fg");
/*  48 */     this.mg = this.atlas.findRegion("mod/mg");
/*  49 */     this.leftWall = this.atlas.findRegion("mod/mod1");
/*  50 */     this.hollowWall = this.atlas.findRegion("mod/mod2");
/*  51 */     this.solidWall = this.atlas.findRegion("mod/midWall");
/*     */ 
/*     */     
/*  54 */     this.ceiling = this.atlas.findRegion("mod/ceiling");
/*  55 */     this.ceilingMod1 = this.atlas.findRegion("mod/ceilingMod1");
/*  56 */     this.ceilingMod2 = this.atlas.findRegion("mod/ceilingMod2");
/*  57 */     this.ceilingMod3 = this.atlas.findRegion("mod/ceilingMod3");
/*  58 */     this.ceilingMod4 = this.atlas.findRegion("mod/ceilingMod4");
/*  59 */     this.ceilingMod5 = this.atlas.findRegion("mod/ceilingMod5");
/*  60 */     this.ceilingMod6 = this.atlas.findRegion("mod/ceilingMod6");
/*     */     
/*  62 */     this.ambianceName = "AMBIANCE_BOTTOM";
/*  63 */     fadeInAmbiance();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  68 */     super.update();
/*  69 */     updateDust();
/*  70 */     updateFog();
/*  71 */     updateTorches();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateDust() {
/*  78 */     for (Iterator<DustEffect> e = this.dust.iterator(); e.hasNext(); ) {
/*  79 */       DustEffect effect = e.next();
/*  80 */       effect.update();
/*  81 */       if (effect.isDone) {
/*  82 */         e.remove();
/*     */       }
/*     */     } 
/*     */     
/*  86 */     if (this.dust.size() < 96 && !Settings.DISABLE_EFFECTS) {
/*  87 */       this.dust.add(new DustEffect());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateFog() {
/*  95 */     if (this.fog.size() < 50 && !Settings.DISABLE_EFFECTS) {
/*  96 */       this.fog.add(new BottomFogEffect(true));
/*     */     }
/*     */     
/*  99 */     for (Iterator<BottomFogEffect> e = this.fog.iterator(); e.hasNext(); ) {
/* 100 */       BottomFogEffect effect = e.next();
/* 101 */       effect.update();
/* 102 */       if (effect.isDone) {
/* 103 */         e.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateTorches() {
/* 109 */     for (Iterator<InteractableTorchEffect> e = this.torches.iterator(); e.hasNext(); ) {
/* 110 */       InteractableTorchEffect effect = e.next();
/* 111 */       effect.update();
/* 112 */       if (effect.isDone) {
/* 113 */         e.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextRoom(AbstractRoom room) {
/* 124 */     super.nextRoom(room);
/* 125 */     randomizeScene();
/*     */     
/* 127 */     if (room instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 128 */       CardCrawlGame.music.silenceBGM();
/*     */     }
/*     */     
/* 131 */     if (room instanceof com.megacrit.cardcrawl.rooms.EventRoom || room instanceof com.megacrit.cardcrawl.rooms.RestRoom) {
/* 132 */       this.torches.clear();
/*     */     }
/*     */     
/* 135 */     fadeInAmbiance();
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomizeScene() {
/* 140 */     if (MathUtils.randomBoolean()) {
/* 141 */       this.renderSolidMid = false;
/* 142 */       this.renderLeftWall = false;
/* 143 */       this.renderHollowMid = true;
/* 144 */       if (MathUtils.randomBoolean()) {
/* 145 */         this.renderSolidMid = true;
/* 146 */         if (MathUtils.randomBoolean()) {
/* 147 */           this.renderLeftWall = true;
/*     */         }
/*     */       } 
/*     */     } else {
/* 151 */       this.renderLeftWall = false;
/* 152 */       this.renderHollowMid = false;
/* 153 */       this.renderSolidMid = true;
/* 154 */       if (MathUtils.randomBoolean()) {
/* 155 */         this.renderLeftWall = true;
/*     */       }
/*     */     } 
/*     */     
/* 159 */     this.renderCeilingMod1 = MathUtils.randomBoolean();
/* 160 */     this.renderCeilingMod2 = MathUtils.randomBoolean();
/* 161 */     this.renderCeilingMod3 = MathUtils.randomBoolean();
/* 162 */     this.renderCeilingMod4 = MathUtils.randomBoolean();
/* 163 */     this.renderCeilingMod5 = MathUtils.randomBoolean();
/* 164 */     this.renderCeilingMod6 = MathUtils.randomBoolean();
/*     */     
/* 166 */     randomizeTorch();
/* 167 */     this.overlayColor.r = MathUtils.random(0.0F, 0.05F);
/* 168 */     this.overlayColor.g = MathUtils.random(0.0F, 0.2F);
/* 169 */     this.overlayColor.b = MathUtils.random(0.0F, 0.2F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderCombatRoomBg(SpriteBatch sb) {
/* 175 */     sb.setColor(Color.WHITE);
/* 176 */     renderAtlasRegionIf(sb, this.bg, true);
/*     */     
/* 178 */     if (!this.isCamp) {
/* 179 */       for (BottomFogEffect e : this.fog) {
/* 180 */         e.render(sb);
/*     */       }
/*     */     }
/*     */     
/* 184 */     sb.setColor(Color.WHITE);
/* 185 */     renderAtlasRegionIf(sb, this.mg, true);
/* 186 */     if (this.renderHollowMid && (this.renderSolidMid || this.renderLeftWall)) {
/* 187 */       sb.setColor(Color.GRAY);
/*     */     }
/* 189 */     renderAtlasRegionIf(sb, this.solidWall, this.renderSolidMid);
/* 190 */     sb.setColor(Color.WHITE);
/* 191 */     renderAtlasRegionIf(sb, this.hollowWall, this.renderHollowMid);
/* 192 */     renderAtlasRegionIf(sb, this.leftWall, this.renderLeftWall);
/*     */ 
/*     */     
/* 195 */     renderAtlasRegionIf(sb, this.ceiling, true);
/* 196 */     renderAtlasRegionIf(sb, this.ceilingMod1, this.renderCeilingMod1);
/* 197 */     renderAtlasRegionIf(sb, this.ceilingMod2, this.renderCeilingMod2);
/* 198 */     renderAtlasRegionIf(sb, this.ceilingMod3, this.renderCeilingMod3);
/* 199 */     renderAtlasRegionIf(sb, this.ceilingMod4, this.renderCeilingMod4);
/* 200 */     renderAtlasRegionIf(sb, this.ceilingMod5, this.renderCeilingMod5);
/* 201 */     renderAtlasRegionIf(sb, this.ceilingMod6, this.renderCeilingMod6);
/*     */     
/* 203 */     for (InteractableTorchEffect e : this.torches) {
/* 204 */       e.render(sb);
/*     */     }
/*     */     
/* 207 */     sb.setBlendFunction(768, 1);
/* 208 */     sb.setColor(this.overlayColor);
/* 209 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/* 210 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */ 
/*     */   
/*     */   private void randomizeTorch() {
/* 215 */     this.torches.clear();
/*     */     
/* 217 */     if (MathUtils.randomBoolean(0.1F)) {
/* 218 */       this.torches.add(new InteractableTorchEffect(1790.0F * Settings.xScale, 850.0F * Settings.yScale, InteractableTorchEffect.TorchSize.S));
/*     */     }
/*     */     
/* 221 */     if (this.renderHollowMid && !this.renderSolidMid) {
/* 222 */       int roll = MathUtils.random(2);
/* 223 */       if (roll == 0) {
/* 224 */         this.torches.add(new InteractableTorchEffect(800.0F * Settings.xScale, 768.0F * Settings.yScale));
/* 225 */         this.torches.add(new InteractableTorchEffect(1206.0F * Settings.xScale, 768.0F * Settings.yScale));
/* 226 */       } else if (roll == 1) {
/* 227 */         this.torches.add(new InteractableTorchEffect(328.0F * Settings.xScale, 865.0F * Settings.yScale, InteractableTorchEffect.TorchSize.S));
/*     */       } 
/* 229 */     } else if (!this.renderLeftWall && !this.renderHollowMid) {
/* 230 */       if (MathUtils.randomBoolean(0.75F)) {
/* 231 */         this.torches.add(new InteractableTorchEffect(613.0F * Settings.xScale, 860.0F * Settings.yScale));
/* 232 */         this.torches.add(new InteractableTorchEffect(613.0F * Settings.xScale, 672.0F * Settings.yScale));
/* 233 */         if (MathUtils.randomBoolean(0.3F)) {
/* 234 */           this.torches.add(new InteractableTorchEffect(1482.0F * Settings.xScale, 860.0F * Settings.yScale));
/* 235 */           this.torches.add(new InteractableTorchEffect(1482.0F * Settings.xScale, 672.0F * Settings.yScale));
/*     */         } 
/*     */       } 
/* 238 */     } else if (this.renderSolidMid && this.renderHollowMid) {
/* 239 */       if (!this.renderLeftWall) {
/* 240 */         int roll = MathUtils.random(3);
/* 241 */         if (roll == 0) {
/* 242 */           this.torches.add(new InteractableTorchEffect(912.0F * Settings.xScale, 790.0F * Settings.yScale));
/* 243 */           this.torches.add(new InteractableTorchEffect(912.0F * Settings.xScale, 526.0F * Settings.yScale));
/* 244 */           this.torches.add(new InteractableTorchEffect(844.0F * Settings.xScale, 658.0F * Settings.yScale, InteractableTorchEffect.TorchSize.S));
/* 245 */           this.torches.add(new InteractableTorchEffect(980.0F * Settings.xScale, 658.0F * Settings.yScale, InteractableTorchEffect.TorchSize.S));
/* 246 */         } else if (roll == 1 || roll == 2) {
/* 247 */           this.torches.add(new InteractableTorchEffect(1828.0F * Settings.xScale, 720.0F * Settings.yScale));
/*     */         }
/*     */       
/* 250 */       } else if (MathUtils.randomBoolean(0.75F)) {
/* 251 */         this.torches.add(new InteractableTorchEffect(970.0F * Settings.xScale, 874.0F * Settings.yScale, InteractableTorchEffect.TorchSize.L));
/*     */       }
/*     */     
/* 254 */     } else if (this.renderLeftWall && !this.renderHollowMid && 
/* 255 */       MathUtils.randomBoolean(0.75F)) {
/* 256 */       this.torches.add(new InteractableTorchEffect(970.0F * Settings.xScale, 873.0F * Settings.renderScale, InteractableTorchEffect.TorchSize.L));
/* 257 */       this.torches.add(new InteractableTorchEffect(616.0F * Settings.xScale, 813.0F * Settings.renderScale));
/* 258 */       this.torches.add(new InteractableTorchEffect(1266.0F * Settings.xScale, 708.0F * Settings.renderScale));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 263 */     LightFlareMEffect.renderGreen = MathUtils.randomBoolean();
/* 264 */     TorchParticleMEffect.renderGreen = LightFlareMEffect.renderGreen;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderCombatRoomFg(SpriteBatch sb) {
/* 269 */     if (!this.isCamp) {
/* 270 */       for (DustEffect e : this.dust) {
/* 271 */         e.render(sb);
/*     */       }
/*     */     }
/* 274 */     sb.setColor(Color.WHITE);
/* 275 */     renderAtlasRegionIf(sb, this.fg, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderCampfireRoom(SpriteBatch sb) {
/* 280 */     sb.setColor(Color.WHITE);
/* 281 */     renderAtlasRegionIf(sb, this.campfireBg, true);
/* 282 */     sb.setBlendFunction(770, 1);
/* 283 */     this.whiteColor.a = MathUtils.cosDeg((float)(System.currentTimeMillis() / 3L % 360L)) / 10.0F + 0.8F;
/* 284 */     sb.setColor(this.whiteColor);
/* 285 */     renderQuadrupleSize(sb, this.campfireGlow, !CampfireUI.hidden);
/* 286 */     sb.setBlendFunction(770, 771);
/* 287 */     sb.setColor(Color.WHITE);
/* 288 */     renderAtlasRegionIf(sb, this.campfireKindling, true);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\scenes\TheBottomScene.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */