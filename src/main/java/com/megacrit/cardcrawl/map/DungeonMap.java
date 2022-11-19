/*     */ package com.megacrit.cardcrawl.map;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
/*     */ import com.megacrit.cardcrawl.screens.DungeonMapScreen;
/*     */ 
/*     */ public class DungeonMap
/*     */ {
/*     */   private static Texture top;
/*     */   private static Texture mid;
/*     */   private static Texture bot;
/*  25 */   public float targetAlpha = 0.0F; private static Texture blend; public static Texture boss; public static Texture bossOutline;
/*  26 */   private static final Color NOT_TAKEN_COLOR = new Color(0.34F, 0.34F, 0.34F, 1.0F);
/*  27 */   private Color bossNodeColor = NOT_TAKEN_COLOR.cpy();
/*  28 */   private Color baseMapColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   private float mapMidDist;
/*     */   private static float mapOffsetY;
/*  31 */   private static final float BOSS_W = Settings.isMobile ? (560.0F * Settings.scale) : (512.0F * Settings.scale);
/*  32 */   private static final float BOSS_OFFSET_Y = 1416.0F * Settings.scale;
/*  33 */   private static final float H = 1020.0F * Settings.scale;
/*  34 */   private static final float BLEND_H = 512.0F * Settings.scale;
/*     */   
/*     */   public Hitbox bossHb;
/*     */   
/*     */   public boolean atBoss = false;
/*  39 */   private Color reticleColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */ 
/*     */   
/*  42 */   public Legend legend = new Legend();
/*     */   
/*     */   public DungeonMap() {
/*  45 */     if (top == null) {
/*  46 */       top = ImageMaster.loadImage("images/ui/map/mapTop.png");
/*  47 */       mid = ImageMaster.loadImage("images/ui/map/mapMid.png");
/*  48 */       bot = ImageMaster.loadImage("images/ui/map/mapBot.png");
/*  49 */       blend = ImageMaster.loadImage("images/ui/map/mapBlend.png");
/*     */     } 
/*  51 */     this.bossHb = new Hitbox(400.0F * Settings.scale, 360.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update() {
/*  55 */     this.legend.update(this.baseMapColor.a, (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP));
/*  56 */     this.baseMapColor.a = MathHelper.fadeLerpSnap(this.baseMapColor.a, this.targetAlpha);
/*     */     
/*  58 */     this.bossHb.move(Settings.WIDTH / 2.0F, DungeonMapScreen.offsetY + mapOffsetY + BOSS_OFFSET_Y + BOSS_W / 2.0F);
/*  59 */     this.bossHb.update();
/*  60 */     updateReticle();
/*     */     
/*  62 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMPLETE && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && (Settings.isDebug || 
/*  63 */       (AbstractDungeon.getCurrMapNode()).y == 14 || (AbstractDungeon.id
/*  64 */       .equals("TheEnding") && (AbstractDungeon.getCurrMapNode()).y == 2)))
/*     */     {
/*  66 */       if (this.bossHb.hovered && (InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed())) {
/*     */         
/*  68 */         (AbstractDungeon.getCurrMapNode()).taken = true;
/*  69 */         MapRoomNode node2 = AbstractDungeon.getCurrMapNode();
/*  70 */         for (MapEdge e : node2.getEdges()) {
/*  71 */           if (e != null) {
/*  72 */             e.markAsTaken();
/*     */           }
/*     */         } 
/*     */         
/*  76 */         InputHelper.justClickedLeft = false;
/*  77 */         CardCrawlGame.music.fadeOutTempBGM();
/*  78 */         MapRoomNode node = new MapRoomNode(-1, 15);
/*  79 */         node.room = (AbstractRoom)new MonsterRoomBoss();
/*  80 */         AbstractDungeon.nextRoom = node;
/*     */         
/*  82 */         if (AbstractDungeon.pathY.size() > 1) {
/*  83 */           AbstractDungeon.pathX.add(AbstractDungeon.pathX.get(AbstractDungeon.pathX.size() - 1));
/*  84 */           AbstractDungeon.pathY.add(Integer.valueOf(((Integer)AbstractDungeon.pathY.get(AbstractDungeon.pathY.size() - 1)).intValue() + 1));
/*     */         } else {
/*  86 */           AbstractDungeon.pathX.add(Integer.valueOf(1));
/*  87 */           AbstractDungeon.pathY.add(Integer.valueOf(15));
/*     */         } 
/*     */         
/*  90 */         AbstractDungeon.nextRoomTransitionStart();
/*  91 */         this.bossHb.hovered = false;
/*     */       } 
/*     */     }
/*     */     
/*  95 */     if (this.bossHb.hovered || this.atBoss) {
/*  96 */       this.bossNodeColor = MapRoomNode.AVAILABLE_COLOR.cpy();
/*     */     } else {
/*  98 */       this.bossNodeColor.lerp(NOT_TAKEN_COLOR, Gdx.graphics.getDeltaTime() * 8.0F);
/*     */     } 
/*     */     
/* 101 */     this.bossNodeColor.a = this.baseMapColor.a;
/*     */   }
/*     */   
/*     */   private void updateReticle() {
/* 105 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 109 */     if (this.bossHb.hovered) {
/* 110 */       this.reticleColor.a += Gdx.graphics.getDeltaTime() * 3.0F;
/*     */       
/* 112 */       if (this.reticleColor.a > 1.0F) {
/* 113 */         this.reticleColor.a = 1.0F;
/*     */       }
/*     */     } else {
/* 116 */       this.reticleColor.a = 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   private float calculateMapSize() {
/* 121 */     if (AbstractDungeon.id.equals("TheEnding")) {
/* 122 */       return Settings.MAP_DST_Y * 4.0F - 1380.0F * Settings.scale;
/*     */     }
/* 124 */     return Settings.MAP_DST_Y * 16.0F - 1380.0F * Settings.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void show() {
/* 129 */     this.targetAlpha = 1.0F;
/* 130 */     this.mapMidDist = calculateMapSize();
/* 131 */     mapOffsetY = this.mapMidDist - 120.0F * Settings.scale;
/*     */   }
/*     */   
/*     */   public void hide() {
/* 135 */     this.targetAlpha = 0.0F;
/*     */   }
/*     */   
/*     */   public void hideInstantly() {
/* 139 */     this.targetAlpha = 0.0F;
/* 140 */     this.baseMapColor.a = 0.0F;
/* 141 */     this.legend.c.a = 0.0F;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 145 */     if (!AbstractDungeon.id.equals("TheEnding")) {
/* 146 */       renderNormalMap(sb);
/*     */     } else {
/* 148 */       renderFinalActMap(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderNormalMap(SpriteBatch sb) {
/* 153 */     sb.setColor(this.baseMapColor);
/*     */ 
/*     */     
/* 156 */     if (!Settings.isMobile) {
/* 157 */       sb.draw(top, 0.0F, H + DungeonMapScreen.offsetY + mapOffsetY, Settings.WIDTH, 1080.0F * Settings.scale);
/*     */     } else {
/* 159 */       sb.draw(top, -Settings.WIDTH * 0.05F, H + DungeonMapScreen.offsetY + mapOffsetY, Settings.WIDTH * 1.1F, 1080.0F * Settings.scale);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     renderMapCenters(sb);
/*     */     
/* 169 */     if (!Settings.isMobile) {
/* 170 */       sb.draw(bot, 0.0F, -this.mapMidDist + DungeonMapScreen.offsetY + mapOffsetY + 1.0F, Settings.WIDTH, 1080.0F * Settings.scale);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 177 */       sb.draw(bot, -Settings.WIDTH * 0.05F, -this.mapMidDist + DungeonMapScreen.offsetY + mapOffsetY + 1.0F, Settings.WIDTH * 1.1F, 1080.0F * Settings.scale);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     renderMapBlender(sb);
/* 186 */     this.legend.render(sb);
/*     */   }
/*     */   
/*     */   private void renderFinalActMap(SpriteBatch sb) {
/* 190 */     sb.setColor(this.baseMapColor);
/*     */ 
/*     */     
/* 193 */     if (!Settings.isMobile) {
/* 194 */       sb.draw(top, 0.0F, H + DungeonMapScreen.offsetY + mapOffsetY, Settings.WIDTH, 1080.0F * Settings.scale);
/* 195 */       sb.draw(bot, 0.0F, -this.mapMidDist + DungeonMapScreen.offsetY + mapOffsetY + 1.0F, Settings.WIDTH, 1080.0F * Settings.scale);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 202 */       sb.draw(top, -Settings.WIDTH * 0.05F, H + DungeonMapScreen.offsetY + mapOffsetY, Settings.WIDTH * 1.1F, 1080.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 208 */       sb.draw(bot, -Settings.WIDTH * 0.05F, -this.mapMidDist + DungeonMapScreen.offsetY + mapOffsetY + 1.0F, Settings.WIDTH * 1.1F, 1080.0F * Settings.scale);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     renderMapBlender(sb);
/* 217 */     this.legend.render(sb);
/*     */   }
/*     */   
/*     */   public void renderBossIcon(SpriteBatch sb) {
/* 221 */     if (boss != null) {
/* 222 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.bossNodeColor.a));
/* 223 */       if (!Settings.isMobile) {
/* 224 */         sb.draw(bossOutline, Settings.WIDTH / 2.0F - BOSS_W / 2.0F, DungeonMapScreen.offsetY + mapOffsetY + BOSS_OFFSET_Y, BOSS_W, BOSS_W);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 230 */         sb.setColor(this.bossNodeColor);
/* 231 */         sb.draw(boss, Settings.WIDTH / 2.0F - BOSS_W / 2.0F, DungeonMapScreen.offsetY + mapOffsetY + BOSS_OFFSET_Y, BOSS_W, BOSS_W);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 238 */         sb.draw(bossOutline, Settings.WIDTH / 2.0F - BOSS_W / 2.0F, DungeonMapScreen.offsetY + mapOffsetY + BOSS_OFFSET_Y, BOSS_W, BOSS_W);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 244 */         sb.setColor(this.bossNodeColor);
/* 245 */         sb.draw(boss, Settings.WIDTH / 2.0F - BOSS_W / 2.0F, DungeonMapScreen.offsetY + mapOffsetY + BOSS_OFFSET_Y, BOSS_W, BOSS_W);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 254 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
/* 255 */       this.bossHb.render(sb);
/* 256 */       if (Settings.isControllerMode && AbstractDungeon.dungeonMapScreen.map.bossHb.hovered) {
/* 257 */         renderReticle(sb, AbstractDungeon.dungeonMapScreen.map.bossHb);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderMapCenters(SpriteBatch sb) {
/* 263 */     if (!Settings.isMobile) {
/* 264 */       sb.draw(mid, 0.0F, DungeonMapScreen.offsetY + mapOffsetY, Settings.WIDTH, 1080.0F * Settings.scale);
/*     */     } else {
/* 266 */       sb.draw(mid, -Settings.WIDTH * 0.05F, DungeonMapScreen.offsetY + mapOffsetY, Settings.WIDTH * 1.1F, 1080.0F * Settings.scale);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderReticle(SpriteBatch sb, Hitbox hb) {
/* 276 */     float offset = Interpolation.fade.apply(24.0F * Settings.scale, 12.0F * Settings.scale, this.reticleColor.a);
/* 277 */     sb.setColor(this.reticleColor);
/* 278 */     renderReticleCorner(sb, -hb.width / 2.0F + offset, hb.height / 2.0F - offset, hb, false, false);
/* 279 */     renderReticleCorner(sb, hb.width / 2.0F - offset, hb.height / 2.0F - offset, hb, true, false);
/* 280 */     renderReticleCorner(sb, -hb.width / 2.0F + offset, -hb.height / 2.0F + offset, hb, false, true);
/* 281 */     renderReticleCorner(sb, hb.width / 2.0F - offset, -hb.height / 2.0F + offset, hb, true, true);
/*     */   }
/*     */   
/*     */   private void renderReticleCorner(SpriteBatch sb, float x, float y, Hitbox hb, boolean flipX, boolean flipY) {
/* 285 */     sb.draw(ImageMaster.RETICLE_CORNER, hb.cX + x - 18.0F, hb.cY + y - 18.0F, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);
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
/*     */   private void renderMapBlender(SpriteBatch sb) {
/* 310 */     if (!AbstractDungeon.id.equals("TheEnding"))
/* 311 */       if (!Settings.isMobile) {
/* 312 */         sb.draw(blend, 0.0F, DungeonMapScreen.offsetY + mapOffsetY + 800.0F * Settings.scale, Settings.WIDTH, BLEND_H);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 318 */         sb.draw(blend, 0.0F, DungeonMapScreen.offsetY + mapOffsetY - 220.0F * Settings.scale, Settings.WIDTH, BLEND_H);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 325 */         sb.draw(blend, -Settings.WIDTH * 0.05F, DungeonMapScreen.offsetY + mapOffsetY + 800.0F * Settings.scale, Settings.WIDTH * 1.1F, BLEND_H);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 331 */         sb.draw(blend, -Settings.WIDTH * 0.05F, DungeonMapScreen.offsetY + mapOffsetY - 220.0F * Settings.scale, Settings.WIDTH * 1.1F, BLEND_H);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\map\DungeonMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */