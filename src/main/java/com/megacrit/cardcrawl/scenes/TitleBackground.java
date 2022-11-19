/*     */ package com.megacrit.cardcrawl.scenes;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.vfx.scene.LogoFlameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.scene.TitleDustEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class TitleBackground {
/*     */   protected static TextureAtlas atlas;
/*     */   protected final TextureAtlas.AtlasRegion mg3Bot;
/*     */   protected final TextureAtlas.AtlasRegion mg3Top;
/*     */   protected final TextureAtlas.AtlasRegion topGlow;
/*     */   protected final TextureAtlas.AtlasRegion topGlow2;
/*     */   protected final TextureAtlas.AtlasRegion botGlow;
/*     */   protected final TextureAtlas.AtlasRegion sky;
/*  28 */   private static Texture titleLogoImg = null;
/*  29 */   private static int W = 0; private static int H = 0;
/*     */   
/*  31 */   protected ArrayList<TitleCloud> topClouds = new ArrayList<>();
/*  32 */   protected ArrayList<TitleCloud> midClouds = new ArrayList<>();
/*  33 */   public float slider = 1.0F;
/*  34 */   private float timer = 1.0F;
/*     */   
/*     */   public boolean activated = false;
/*     */   
/*  38 */   private ArrayList<TitleDustEffect> dust = new ArrayList<>();
/*  39 */   private ArrayList<TitleDustEffect> dust2 = new ArrayList<>();
/*  40 */   private ArrayList<LogoFlameEffect> flame = new ArrayList<>();
/*  41 */   private float dustTimer = 2.0F; private float flameTimer = 0.2F;
/*     */   private static final float FLAME_INTERVAL = 0.05F;
/*  43 */   private float logoAlpha = 1.0F;
/*     */   
/*  45 */   private Color promptTextColor = Settings.CREAM_COLOR.cpy();
/*     */   
/*     */   public TitleBackground() {
/*  48 */     this.promptTextColor.a = 0.0F;
/*  49 */     if (atlas == null) {
/*  50 */       atlas = new TextureAtlas(Gdx.files.internal("title/title.atlas"));
/*     */     }
/*  52 */     this.sky = atlas.findRegion("jpg/sky");
/*  53 */     this.mg3Bot = atlas.findRegion("mg3Bot");
/*  54 */     this.mg3Top = atlas.findRegion("mg3Top");
/*  55 */     this.topGlow = atlas.findRegion("mg3TopGlow1");
/*  56 */     this.topGlow2 = atlas.findRegion("mg3TopGlow2");
/*  57 */     this.botGlow = atlas.findRegion("mg3BotGlow");
/*     */     int i;
/*  59 */     for (i = 1; i < 7; i++) {
/*  60 */       this.topClouds.add(new TitleCloud(atlas
/*     */             
/*  62 */             .findRegion("topCloud" + Integer.toString(i)), 
/*  63 */             MathUtils.random(10.0F, 50.0F) * Settings.scale, 
/*  64 */             MathUtils.random(-1920.0F, 1920.0F) * Settings.scale));
/*     */     }
/*     */     
/*  67 */     for (i = 1; i < 13; i++) {
/*  68 */       this.midClouds.add(new TitleCloud(atlas
/*     */             
/*  70 */             .findRegion("midCloud" + Integer.toString(i)), 
/*  71 */             MathUtils.random(-50.0F, -10.0F) * Settings.scale, 
/*  72 */             MathUtils.random(-1920.0F, 1920.0F) * Settings.scale));
/*     */     }
/*     */     
/*  75 */     if (titleLogoImg == null) {
/*  76 */       switch (Settings.language) {
/*     */       
/*     */       } 
/*     */ 
/*     */       
/*  81 */       titleLogoImg = ImageMaster.loadImage("images/ui/title_logo/eng.png");
/*     */ 
/*     */       
/*  84 */       W = titleLogoImg.getWidth();
/*  85 */       H = titleLogoImg.getHeight();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void slideDownInstantly() {
/*  90 */     this.activated = true;
/*  91 */     this.timer = 0.0F;
/*  92 */     this.slider = 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  97 */     if (CardCrawlGame.mainMenuScreen.darken) {
/*  98 */       this.logoAlpha = MathHelper.slowColorLerpSnap(this.logoAlpha, 0.25F);
/*     */     } else {
/* 100 */       this.logoAlpha = MathHelper.slowColorLerpSnap(this.logoAlpha, 1.0F);
/*     */     } 
/*     */     
/* 103 */     if (InputHelper.justClickedLeft && 
/* 104 */       !this.activated) {
/* 105 */       this.activated = true;
/* 106 */       this.timer = 1.0F;
/*     */     } 
/*     */ 
/*     */     
/* 110 */     if (this.activated && 
/* 111 */       this.timer != 0.0F) {
/* 112 */       this.timer -= Gdx.graphics.getDeltaTime();
/* 113 */       if (this.timer < 0.0F) {
/* 114 */         this.timer = 0.0F;
/*     */       }
/* 116 */       if (this.timer < 1.0F) {
/* 117 */         this.slider = Interpolation.pow4In.apply(0.0F, 1.0F, this.timer);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 122 */     for (TitleCloud c : this.topClouds) {
/* 123 */       c.update();
/*     */     }
/* 125 */     for (TitleCloud c : this.midClouds) {
/* 126 */       c.update();
/*     */     }
/*     */     
/* 129 */     if (!Settings.DISABLE_EFFECTS) {
/* 130 */       updateDust();
/*     */     }
/*     */     
/* 133 */     if (!CardCrawlGame.mainMenuScreen.isFadingOut) {
/* 134 */       updateFlame();
/*     */     } else {
/* 136 */       this.flame.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateFlame() {
/* 141 */     this.flameTimer -= Gdx.graphics.getDeltaTime();
/* 142 */     if (this.flameTimer < 0.0F) {
/* 143 */       this.flameTimer = 0.05F;
/* 144 */       this.flame.add(new LogoFlameEffect());
/*     */     } 
/*     */     
/* 147 */     for (Iterator<LogoFlameEffect> e = this.flame.iterator(); e.hasNext(); ) {
/* 148 */       LogoFlameEffect effect = e.next();
/* 149 */       effect.update();
/* 150 */       if (effect.isDone) {
/* 151 */         e.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateDust() {
/* 157 */     this.dustTimer -= Gdx.graphics.getDeltaTime();
/* 158 */     if (this.dustTimer < 0.0F) {
/* 159 */       this.dustTimer = 0.05F;
/* 160 */       this.dust.add(new TitleDustEffect());
/*     */     } 
/*     */     
/* 163 */     for (Iterator<TitleDustEffect> e = this.dust.iterator(); e.hasNext(); ) {
/* 164 */       TitleDustEffect effect = e.next();
/* 165 */       effect.update();
/* 166 */       if (effect.isDone) {
/* 167 */         e.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 173 */     renderRegion(sb, this.sky, 0.0F, -100.0F * Settings.scale * this.slider);
/*     */ 
/*     */     
/* 176 */     renderRegion(sb, this.mg3Bot, 0.0F, 
/*     */ 
/*     */ 
/*     */         
/* 180 */         MathUtils.round(-45.0F * Settings.scale * this.slider + Settings.HEIGHT - 2219.0F * Settings.scale));
/*     */ 
/*     */     
/* 183 */     renderRegion(sb, this.mg3Top, 0.0F, 
/*     */ 
/*     */ 
/*     */         
/* 187 */         MathUtils.round(-45.0F * Settings.scale * this.slider + Settings.HEIGHT - 1080.0F * Settings.scale));
/*     */ 
/*     */     
/* 190 */     sb.setBlendFunction(770, 1);
/* 191 */     sb.setColor(new Color(1.0F, 0.2F, 0.1F, 0.1F + (
/* 192 */           MathUtils.cosDeg((float)(System.currentTimeMillis() / 16L % 360L)) + 1.25F) / 5.0F));
/* 193 */     renderRegion(sb, this.botGlow, 0.0F, 
/*     */ 
/*     */ 
/*     */         
/* 197 */         MathUtils.round(-45.0F * Settings.scale * this.slider + Settings.HEIGHT - 2220.0F * Settings.scale));
/* 198 */     renderRegion(sb, this.topGlow, 0.0F, -45.0F * Settings.scale * this.slider + Settings.HEIGHT - 1080.0F * Settings.scale);
/* 199 */     renderRegion(sb, this.topGlow2, 0.0F, -45.0F * Settings.scale * this.slider + Settings.HEIGHT - 1080.0F * Settings.scale);
/* 200 */     sb.setColor(Color.WHITE);
/* 201 */     sb.setBlendFunction(770, 771);
/*     */     
/* 203 */     for (TitleDustEffect e : this.dust2) {
/* 204 */       e.render(sb, 0.0F, -50.0F * Settings.scale * this.slider + Settings.HEIGHT - 1300.0F * Settings.scale);
/*     */     }
/* 206 */     for (TitleDustEffect e : this.dust) {
/* 207 */       e.render(sb, 0.0F, -50.0F * Settings.scale * this.slider + Settings.HEIGHT - 1300.0F * Settings.scale);
/*     */     }
/* 209 */     sb.setColor(Color.WHITE);
/*     */     
/* 211 */     for (TitleCloud c : this.midClouds) {
/* 212 */       c.render(sb, this.slider);
/*     */     }
/* 214 */     for (TitleCloud c : this.topClouds) {
/* 215 */       c.render(sb, this.slider);
/*     */     }
/*     */     
/* 218 */     sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.logoAlpha));
/* 219 */     sb.draw(titleLogoImg, 930.0F * Settings.xScale - W / 2.0F, -70.0F * Settings.scale * this.slider + Settings.HEIGHT / 2.0F - H / 2.0F + 14.0F * Settings.scale, W / 2.0F, H / 2.0F, W, H, Settings.scale, Settings.scale, 0.0F, 0, 0, W, H, false, false);
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
/* 236 */     sb.setBlendFunction(770, 1);
/*     */     
/* 238 */     for (LogoFlameEffect e : this.flame) {
/* 239 */       switch (Settings.language) {
/*     */       
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 247 */       e.render(sb, Settings.WIDTH / 2.0F, -70.0F * Settings.scale * this.slider + Settings.HEIGHT / 2.0F - 260.0F * Settings.scale);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 254 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   private void renderRegion(SpriteBatch sb, TextureAtlas.AtlasRegion region, float x, float y) {
/* 258 */     if (Settings.isLetterbox) {
/* 259 */       sb.draw(region
/* 260 */           .getTexture(), region.offsetX * Settings.scale + x, region.offsetY * Settings.scale + y, 0.0F, 0.0F, region.packedWidth, region.packedHeight, Settings.xScale, Settings.xScale, 0.0F, region
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 270 */           .getRegionX(), region
/* 271 */           .getRegionY(), region
/* 272 */           .getRegionWidth(), region
/* 273 */           .getRegionHeight(), false, false);
/*     */     }
/*     */     else {
/*     */       
/* 277 */       sb.draw(region
/* 278 */           .getTexture(), region.offsetX * Settings.scale + x, region.offsetY * Settings.scale + y, 0.0F, 0.0F, region.packedWidth, region.packedHeight, Settings.scale, Settings.scale, 0.0F, region
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 288 */           .getRegionX(), region
/* 289 */           .getRegionY(), region
/* 290 */           .getRegionWidth(), region
/* 291 */           .getRegionHeight(), false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\scenes\TitleBackground.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */