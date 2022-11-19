/*     */ package com.megacrit.cardcrawl.map;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.screens.DungeonMapScreen;
/*     */ import com.megacrit.cardcrawl.vfx.FadeWipeParticle;
/*     */ import com.megacrit.cardcrawl.vfx.FlameAnimationEffect;
/*     */ import com.megacrit.cardcrawl.vfx.MapCircleEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
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
/*     */ public class MapRoomNode
/*     */ {
/*  34 */   private static final int IMG_WIDTH = (int)(Settings.xScale * 64.0F);
/*  35 */   public static final float OFFSET_X = Settings.isMobile ? (496.0F * Settings.xScale) : (560.0F * Settings.xScale);
/*  36 */   private static final float OFFSET_Y = 180.0F * Settings.scale;
/*  37 */   private static final float SPACING_X = Settings.isMobile ? (IMG_WIDTH * 2.2F) : (IMG_WIDTH * 2.0F);
/*  38 */   private static final float JITTER_X = Settings.isMobile ? (13.0F * Settings.xScale) : (27.0F * Settings.xScale);
/*  39 */   private static final float JITTER_Y = Settings.isMobile ? (18.0F * Settings.xScale) : (37.0F * Settings.xScale);
/*  40 */   public float offsetX = (int)MathUtils.random(-JITTER_X, JITTER_X);
/*  41 */   public float offsetY = (int)MathUtils.random(-JITTER_Y, JITTER_Y);
/*  42 */   public static final Color AVAILABLE_COLOR = new Color(0.09F, 0.13F, 0.17F, 1.0F);
/*  43 */   private static final Color NOT_TAKEN_COLOR = new Color(0.34F, 0.34F, 0.34F, 1.0F);
/*  44 */   private static final Color OUTLINE_COLOR = Color.valueOf("8c8c80ff");
/*  45 */   public Color color = NOT_TAKEN_COLOR.cpy();
/*  46 */   private float oscillateTimer = MathUtils.random(0.0F, 6.28F);
/*  47 */   public Hitbox hb = null;
/*     */   private static final int W = 128;
/*  49 */   private float scale = 0.5F; private static final int O_W = 192;
/*  50 */   private float angle = MathUtils.random(360.0F);
/*  51 */   private ArrayList<MapRoomNode> parents = new ArrayList<>();
/*     */ 
/*     */   
/*  54 */   private ArrayList<FlameAnimationEffect> fEffects = new ArrayList<>();
/*  55 */   private float flameVfxTimer = 0.0F;
/*     */   
/*     */   public int x;
/*     */   public int y;
/*  59 */   public AbstractRoom room = null;
/*  60 */   private ArrayList<MapEdge> edges = new ArrayList<>(); public boolean taken = false;
/*     */   public boolean highlighted = false;
/*  62 */   private float animWaitTimer = 0.0F;
/*     */   public boolean hasEmeraldKey = false;
/*     */   private static final float ANIM_WAIT_TIME = 0.25F;
/*     */   
/*     */   public MapRoomNode(int x, int y) {
/*  67 */     this.x = x;
/*  68 */     this.y = y;
/*     */     
/*  70 */     float hitbox_w = Settings.isMobile ? (114.0F * Settings.scale) : (64.0F * Settings.scale);
/*  71 */     this.hb = new Hitbox(hitbox_w, hitbox_w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasEdges() {
/*  78 */     return !this.edges.isEmpty();
/*     */   }
/*     */   
/*     */   public void addEdge(MapEdge e) {
/*  82 */     Boolean unique = Boolean.valueOf(true);
/*  83 */     for (MapEdge otherEdge : this.edges) {
/*  84 */       if (e.compareTo(otherEdge) == 0) {
/*  85 */         unique = Boolean.valueOf(false);
/*     */       }
/*     */     } 
/*     */     
/*  89 */     if (unique.booleanValue()) {
/*  90 */       this.edges.add(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void delEdge(MapEdge e) {
/*  95 */     this.edges.remove(e);
/*     */   }
/*     */   
/*     */   public ArrayList<MapEdge> getEdges() {
/*  99 */     return this.edges;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConnectedTo(MapRoomNode node) {
/* 109 */     for (MapEdge edge : this.edges) {
/* 110 */       if (node.x == edge.dstX && node.y == edge.dstY) {
/* 111 */         return true;
/*     */       }
/*     */     } 
/* 114 */     return false;
/*     */   }
/*     */   
/*     */   public boolean wingedIsConnectedTo(MapRoomNode node) {
/* 118 */     for (MapEdge edge : this.edges) {
/* 119 */       if (ModHelper.isModEnabled("Flight") && node.y == edge.dstY) {
/* 120 */         return true;
/*     */       }
/* 122 */       if (node.y == edge.dstY && AbstractDungeon.player.hasRelic("WingedGreaves") && 
/* 123 */         (AbstractDungeon.player.getRelic("WingedGreaves")).counter > 0) {
/* 124 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapEdge getEdgeConnectedTo(MapRoomNode node) {
/* 138 */     for (MapEdge edge : this.edges) {
/* 139 */       if (node.x == edge.dstX && node.y == edge.dstY) {
/* 140 */         return edge;
/*     */       }
/*     */     } 
/* 143 */     return null;
/*     */   }
/*     */   
/*     */   public void setRoom(AbstractRoom room) {
/* 147 */     this.room = room;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean leftNodeAvailable() {
/* 156 */     for (MapEdge edge : this.edges) {
/* 157 */       if (edge.dstX < this.x) {
/* 158 */         return true;
/*     */       }
/*     */     } 
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean centerNodeAvailable() {
/* 170 */     for (MapEdge edge : this.edges) {
/* 171 */       if (edge.dstX == this.x) {
/* 172 */         return true;
/*     */       }
/*     */     } 
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean rightNodeAvailable() {
/* 184 */     for (MapEdge edge : this.edges) {
/* 185 */       if (edge.dstX > this.x) {
/* 186 */         return true;
/*     */       }
/*     */     } 
/* 189 */     return false;
/*     */   }
/*     */   
/*     */   public void addParent(MapRoomNode parent) {
/* 193 */     this.parents.add(parent);
/*     */   }
/*     */   
/*     */   public ArrayList<MapRoomNode> getParents() {
/* 197 */     return this.parents;
/*     */   }
/*     */   
/*     */   public String getRoomSymbol(Boolean showSpecificRoomSymbol) {
/* 201 */     if (this.room == null || !showSpecificRoomSymbol.booleanValue()) {
/* 202 */       return "*";
/*     */     }
/* 204 */     return this.room.getMapSymbol();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 211 */     return "(" + this.x + "," + this.y + "):" + this.edges.toString();
/*     */   }
/*     */   
/*     */   public AbstractRoom getRoom() {
/* 215 */     return this.room;
/*     */   }
/*     */   
/*     */   public void update() {
/* 219 */     if (this.animWaitTimer != 0.0F) {
/* 220 */       this.animWaitTimer -= Gdx.graphics.getDeltaTime();
/* 221 */       if (this.animWaitTimer < 0.0F) {
/* 222 */         if (!AbstractDungeon.firstRoomChosen) {
/* 223 */           AbstractDungeon.setCurrMapNode(this);
/*     */         } else {
/* 225 */           (AbstractDungeon.getCurrMapNode()).taken = true;
/*     */         } 
/* 227 */         MapEdge connectedEdge = AbstractDungeon.getCurrMapNode().getEdgeConnectedTo(this);
/* 228 */         if (connectedEdge != null) {
/* 229 */           connectedEdge.markAsTaken();
/*     */         }
/*     */         
/* 232 */         this.animWaitTimer = 0.0F;
/*     */         
/* 234 */         AbstractDungeon.nextRoom = this;
/* 235 */         AbstractDungeon.pathX.add(Integer.valueOf(this.x));
/* 236 */         AbstractDungeon.pathY.add(Integer.valueOf(this.y));
/* 237 */         CardCrawlGame.metricData.path_taken.add(AbstractDungeon.nextRoom.getRoom().getMapSymbol());
/*     */         
/* 239 */         if (!AbstractDungeon.isDungeonBeaten) {
/* 240 */           AbstractDungeon.nextRoomTransitionStart();
/* 241 */           CardCrawlGame.music.fadeOutTempBGM();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 246 */     updateEmerald();
/*     */     
/* 248 */     this.highlighted = false;
/* 249 */     this.scale = MathHelper.scaleLerpSnap(this.scale, 0.5F);
/*     */ 
/*     */     
/* 252 */     this.hb.move(this.x * SPACING_X + OFFSET_X + this.offsetX, this.y * Settings.MAP_DST_Y + OFFSET_Y + DungeonMapScreen.offsetY + this.offsetY);
/*     */ 
/*     */     
/* 255 */     this.hb.update();
/*     */ 
/*     */     
/* 258 */     for (MapEdge edge : this.edges) {
/* 259 */       if (!edge.taken) {
/* 260 */         edge.color = NOT_TAKEN_COLOR;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 265 */     if ((AbstractDungeon.getCurrRoom()).phase.equals(AbstractRoom.RoomPhase.COMPLETE)) {
/*     */       
/* 267 */       if (equals(AbstractDungeon.getCurrMapNode()))
/*     */       {
/* 269 */         for (MapEdge edge : this.edges) {
/* 270 */           edge.color = AVAILABLE_COLOR;
/*     */         }
/*     */       }
/*     */       
/* 274 */       boolean normalConnection = AbstractDungeon.getCurrMapNode().isConnectedTo(this);
/* 275 */       boolean wingedConnection = AbstractDungeon.getCurrMapNode().wingedIsConnectedTo(this);
/*     */       
/* 277 */       if (normalConnection || Settings.isDebug || wingedConnection) {
/* 278 */         if (this.hb.hovered) {
/* 279 */           if (this.hb.justHovered) {
/* 280 */             playNodeHoveredSound();
/*     */           }
/*     */ 
/*     */           
/* 284 */           if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && AbstractDungeon.dungeonMapScreen.clicked && this.animWaitTimer <= 0.0F) {
/*     */ 
/*     */             
/* 287 */             playNodeSelectedSound();
/* 288 */             AbstractDungeon.dungeonMapScreen.clicked = false;
/* 289 */             AbstractDungeon.dungeonMapScreen.clickTimer = 0.0F;
/*     */             
/* 291 */             if (!normalConnection && wingedConnection && 
/* 292 */               AbstractDungeon.player.hasRelic("WingedGreaves")) {
/* 293 */               (AbstractDungeon.player.getRelic("WingedGreaves")).counter--;
/* 294 */               if ((AbstractDungeon.player.getRelic("WingedGreaves")).counter <= 0) {
/* 295 */                 AbstractDungeon.player.getRelic("WingedGreaves").setCounter(-2);
/*     */               }
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 301 */             AbstractDungeon.topLevelEffects.add(new MapCircleEffect(this.x * SPACING_X + OFFSET_X + this.offsetX, this.y * Settings.MAP_DST_Y + OFFSET_Y + DungeonMapScreen.offsetY + this.offsetY, this.angle));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 307 */             if (!Settings.FAST_MODE) {
/* 308 */               AbstractDungeon.topLevelEffects.add(new FadeWipeParticle());
/*     */             }
/* 310 */             this.animWaitTimer = 0.25F;
/*     */             
/* 312 */             if (this.room instanceof com.megacrit.cardcrawl.rooms.EventRoom) {
/* 313 */               CardCrawlGame.mysteryMachine++;
/*     */             }
/*     */           } 
/* 316 */           this.highlighted = true;
/*     */         } else {
/* 318 */           this.color = AVAILABLE_COLOR.cpy();
/*     */         } 
/* 320 */         oscillateColor();
/*     */       }
/* 322 */       else if (this.hb.hovered && !this.taken) {
/* 323 */         this.scale = 1.0F;
/* 324 */         this.color = AVAILABLE_COLOR.cpy();
/*     */       } else {
/* 326 */         this.color = NOT_TAKEN_COLOR.cpy();
/*     */       }
/*     */     
/* 329 */     } else if (this.hb.hovered) {
/* 330 */       this.scale = 1.0F;
/* 331 */       this.color = AVAILABLE_COLOR.cpy();
/*     */     } else {
/* 333 */       this.color = NOT_TAKEN_COLOR.cpy();
/*     */     } 
/*     */     
/* 336 */     if (!AbstractDungeon.firstRoomChosen && this.y == 0 && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMPLETE) {
/* 337 */       if (this.hb.hovered) {
/* 338 */         if (this.hb.justHovered) {
/* 339 */           playNodeHoveredSound();
/*     */         }
/*     */ 
/*     */         
/* 343 */         if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && (CInputActionSet.select.isJustPressed() || AbstractDungeon.dungeonMapScreen.clicked)) {
/*     */           
/* 345 */           playNodeSelectedSound();
/* 346 */           AbstractDungeon.dungeonMapScreen.clicked = false;
/* 347 */           AbstractDungeon.dungeonMapScreen.clickTimer = 0.0F;
/* 348 */           AbstractDungeon.dungeonMapScreen.dismissable = true;
/* 349 */           if (!AbstractDungeon.firstRoomChosen) {
/* 350 */             AbstractDungeon.firstRoomChosen = true;
/*     */           }
/* 352 */           AbstractDungeon.topLevelEffects.add(new MapCircleEffect(this.x * SPACING_X + OFFSET_X + this.offsetX, this.y * Settings.MAP_DST_Y + OFFSET_Y + DungeonMapScreen.offsetY + this.offsetY, this.angle));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 357 */           AbstractDungeon.topLevelEffects.add(new FadeWipeParticle());
/* 358 */           this.animWaitTimer = 0.25F;
/*     */         } 
/* 360 */         this.highlighted = true;
/* 361 */       } else if (this.y != 0) {
/* 362 */         this.highlighted = true;
/* 363 */         this.scale = 1.0F;
/*     */       } else {
/* 365 */         this.color = AVAILABLE_COLOR.cpy();
/*     */       } 
/* 367 */       oscillateColor();
/*     */     } 
/*     */     
/* 370 */     if (equals(AbstractDungeon.getCurrMapNode())) {
/* 371 */       this.color = AVAILABLE_COLOR.cpy();
/* 372 */       this.scale = MathHelper.scaleLerpSnap(this.scale, 0.5F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateEmerald() {
/* 377 */     if (Settings.isFinalActAvailable && this.hasEmeraldKey) {
/* 378 */       this.flameVfxTimer -= Gdx.graphics.getDeltaTime();
/* 379 */       if (this.flameVfxTimer < 0.0F) {
/* 380 */         this.flameVfxTimer = MathUtils.random(0.2F, 0.4F);
/* 381 */         this.fEffects.add(new FlameAnimationEffect(this.hb));
/*     */       } 
/*     */       Iterator<FlameAnimationEffect> i;
/* 384 */       for (i = this.fEffects.iterator(); i.hasNext(); ) {
/* 385 */         FlameAnimationEffect e = i.next();
/* 386 */         if (e.isDone) {
/* 387 */           e.dispose();
/* 388 */           i.remove();
/*     */         } 
/*     */       } 
/*     */       
/* 392 */       for (i = this.fEffects.iterator(); i.hasNext(); ) { FlameAnimationEffect e = i.next();
/* 393 */         e.update(); }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playNodeHoveredSound() {
/* 399 */     int roll = MathUtils.random(3);
/* 400 */     switch (roll) {
/*     */       case 0:
/* 402 */         CardCrawlGame.sound.play("MAP_HOVER_1");
/*     */         return;
/*     */       case 1:
/* 405 */         CardCrawlGame.sound.play("MAP_HOVER_2");
/*     */         return;
/*     */       case 2:
/* 408 */         CardCrawlGame.sound.play("MAP_HOVER_3");
/*     */         return;
/*     */     } 
/* 411 */     CardCrawlGame.sound.play("MAP_HOVER_4");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void playNodeSelectedSound() {
/* 417 */     int roll = MathUtils.random(3);
/* 418 */     switch (roll) {
/*     */       case 0:
/* 420 */         CardCrawlGame.sound.play("MAP_SELECT_1");
/*     */         return;
/*     */       case 1:
/* 423 */         CardCrawlGame.sound.play("MAP_SELECT_2");
/*     */         return;
/*     */       case 2:
/* 426 */         CardCrawlGame.sound.play("MAP_SELECT_3");
/*     */         return;
/*     */     } 
/* 429 */     CardCrawlGame.sound.play("MAP_SELECT_4");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void oscillateColor() {
/* 435 */     if (!this.taken) {
/* 436 */       this.oscillateTimer += Gdx.graphics.getDeltaTime() * 5.0F;
/* 437 */       this.color.a = 0.66F + (MathUtils.cos(this.oscillateTimer) + 1.0F) / 6.0F;
/* 438 */       this.scale = 0.25F + this.color.a;
/*     */     } else {
/* 440 */       this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 446 */     for (MapEdge edge : this.edges) {
/* 447 */       edge.render(sb);
/*     */     }
/*     */     
/* 450 */     renderEmeraldVfx(sb);
/*     */     
/* 452 */     if (this.highlighted) {
/* 453 */       sb.setColor(new Color(0.9F, 0.9F, 0.9F, 1.0F));
/*     */     } else {
/* 455 */       sb.setColor(OUTLINE_COLOR);
/*     */     } 
/*     */     
/* 458 */     boolean legendHovered = AbstractDungeon.dungeonMapScreen.map.legend.isIconHovered(getRoomSymbol(Boolean.valueOf(true)));
/* 459 */     if (legendHovered) {
/* 460 */       this.scale = 0.68F;
/* 461 */       sb.setColor(Color.LIGHT_GRAY);
/*     */     } 
/*     */     
/* 464 */     if (!Settings.isMobile) {
/* 465 */       sb.draw(this.room
/* 466 */           .getMapImgOutline(), this.x * SPACING_X + OFFSET_X - 64.0F + this.offsetX, this.y * Settings.MAP_DST_Y + OFFSET_Y + DungeonMapScreen.offsetY - 64.0F + this.offsetY, 64.0F, 64.0F, 128.0F, 128.0F, this.scale * Settings.scale, this.scale * Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 483 */       sb.draw(this.room
/* 484 */           .getMapImgOutline(), this.x * SPACING_X + OFFSET_X - 64.0F + this.offsetX, this.y * Settings.MAP_DST_Y + OFFSET_Y + DungeonMapScreen.offsetY - 64.0F + this.offsetY, 64.0F, 64.0F, 128.0F, 128.0F, this.scale * Settings.scale * 2.0F, this.scale * Settings.scale * 2.0F, 0.0F, 0, 0, 128, 128, false, false);
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
/* 502 */     if (this.taken) {
/* 503 */       sb.setColor(AVAILABLE_COLOR);
/*     */     } else {
/* 505 */       sb.setColor(this.color);
/*     */     } 
/*     */     
/* 508 */     if (legendHovered) {
/* 509 */       sb.setColor(AVAILABLE_COLOR);
/*     */     }
/*     */     
/* 512 */     if (!Settings.isMobile) {
/* 513 */       sb.draw(this.room
/* 514 */           .getMapImg(), this.x * SPACING_X + OFFSET_X - 64.0F + this.offsetX, this.y * Settings.MAP_DST_Y + OFFSET_Y + DungeonMapScreen.offsetY - 64.0F + this.offsetY, 64.0F, 64.0F, 128.0F, 128.0F, this.scale * Settings.scale, this.scale * Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 531 */       sb.draw(this.room
/* 532 */           .getMapImg(), this.x * SPACING_X + OFFSET_X - 64.0F + this.offsetX, this.y * Settings.MAP_DST_Y + OFFSET_Y + DungeonMapScreen.offsetY - 64.0F + this.offsetY, 64.0F, 64.0F, 128.0F, 128.0F, this.scale * Settings.scale * 2.0F, this.scale * Settings.scale * 2.0F, 0.0F, 0, 0, 128, 128, false, false);
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
/*     */     
/* 551 */     if (this.taken || (AbstractDungeon.firstRoomChosen && equals(AbstractDungeon.getCurrMapNode()))) {
/* 552 */       sb.setColor(AVAILABLE_COLOR);
/*     */       
/* 554 */       if (!Settings.isMobile) {
/* 555 */         sb.draw(ImageMaster.MAP_CIRCLE_5, this.x * SPACING_X + OFFSET_X - 96.0F + this.offsetX, this.y * Settings.MAP_DST_Y + OFFSET_Y + DungeonMapScreen.offsetY - 96.0F + this.offsetY, 96.0F, 96.0F, 192.0F, 192.0F, (this.scale * 0.95F + 0.2F) * Settings.scale, (this.scale * 0.95F + 0.2F) * Settings.scale, this.angle, 0, 0, 192, 192, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 573 */         sb.draw(ImageMaster.MAP_CIRCLE_5, this.x * SPACING_X + OFFSET_X - 96.0F + this.offsetX, this.y * Settings.MAP_DST_Y + OFFSET_Y + DungeonMapScreen.offsetY - 96.0F + this.offsetY, 96.0F, 96.0F, 192.0F, 192.0F, (this.scale * 0.95F + 0.2F) * Settings.scale * 2.0F, (this.scale * 0.95F + 0.2F) * Settings.scale * 2.0F, this.angle, 0, 0, 192, 192, false, false);
/*     */       } 
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
/*     */     
/* 593 */     if (this.hb != null) {
/* 594 */       this.hb.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderEmeraldVfx(SpriteBatch sb) {
/* 599 */     if (Settings.isFinalActAvailable && this.hasEmeraldKey)
/* 600 */       for (FlameAnimationEffect e : this.fEffects)
/* 601 */         e.render(sb, this.scale);  
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\map\MapRoomNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */