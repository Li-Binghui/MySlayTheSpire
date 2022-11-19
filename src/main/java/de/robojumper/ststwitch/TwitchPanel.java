/*     */ package de.robojumper.ststwitch;
/*     */ 

/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Optional;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TwitchPanel
/*     */ {
/*  20 */   private static final Logger logger = LogManager.getLogger(TwitchPanel.class.getName());
/*     */   
/*     */   private static Texture TWITCH_RED;
/*     */   
/*     */   private static Texture TWITCH_YELLOW;
/*     */   private static Texture TWITCH_GREEN;
/*  26 */   private float iconScale = 1.0F; private static final float SIZE = 64.0F; public TwitchConnection connection; private TwitchVoter voter;
/*     */   private float x;
/*     */   private float y;
/*     */   private Hitbox hb;
/*     */   
/*     */   public TwitchPanel(TwitchConnection twitchConnection) {
/*  32 */     this.connection = twitchConnection;
/*  33 */     logger.info("Instantiated twitch panel");
/*  34 */     if (TWITCH_RED == null) {
/*  35 */       TWITCH_RED = getTexture("images" + File.separator + "twitch_assets" + File.separator + "twitch_red.png");
/*  36 */       TWITCH_YELLOW = getTexture("images" + File.separator + "twitch_assets" + File.separator + "twitch_orange.png");
/*     */       
/*  38 */       TWITCH_GREEN = getTexture("images" + File.separator + "twitch_assets" + File.separator + "twitch_green.png");
/*     */     } 
/*     */     
/*  41 */     logger.info("Starting Twitch connection");
/*  42 */     this.connection.setStatus(settingsGetTwitchEnabled());
/*  43 */     this.connection.start();
/*  44 */     this.hb = new Hitbox(0.0F, 0.0F, 64.0F * Settings.scale, 64.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   static TwitchConnection getTwitch() {
/*  48 */     return AbstractDungeon.topPanel.twitch.map(twitchPanel -> twitchPanel.connection).orElse(null);
/*     */   }
/*     */   
/*     */   public static Optional<TwitchVoter> getDefaultVoter() {
/*  52 */     if (AbstractDungeon.topPanel.twitch.isPresent()) {
/*  53 */       TwitchPanel twitchPanel = AbstractDungeon.topPanel.twitch.get();
/*  54 */       if (twitchPanel.voter == null) {
/*  55 */         twitchPanel.voter = new TwitchVoter(twitchPanel.connection.getTwitchConfig());
/*     */       }
/*  57 */       return Optional.of(twitchPanel.voter);
/*     */     } 
/*  59 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPosition(float x, float y) {
/*  64 */     this.x = x;
/*  65 */     this.y = y;
/*     */   }
/*     */   
/*     */   private static Texture getTexture(String path) {
/*  69 */     logger.info("Loading: " + path);
/*  70 */     Texture tex = new Texture(path);
/*  71 */     tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
/*  72 */     return tex;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean settingsGetTwitchEnabled() {
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unhover() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  87 */     if (settingsGetTwitchEnabled()) {
/*  88 */       updateInstructions();
/*  89 */       updateInput();
/*  90 */       updateTwitch();
/*     */     } else {
/*     */       
/*  93 */       this.connection.setStatus(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateTwitch() {
/*  98 */     this.connection.update();
/*  99 */     if (this.voter != null) {
/* 100 */       this.voter.update();
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateInstructions() {
/* 105 */     if (this.connection.popPulse()) {
/* 106 */       this.iconScale = 1.5F;
/*     */     }
/* 108 */     this.iconScale = MathHelper.popLerpSnap(this.iconScale, 1.0F);
/*     */   }
/*     */   
/*     */   private void updateInput() {
/* 112 */     this.hb.update(this.x, this.y - 64.0F * Settings.scale * this.iconScale);
/* 113 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 114 */       InputHelper.justClickedLeft = false;
/*     */       
/* 116 */       this.connection.toggleStatus();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 122 */     if (!settingsGetTwitchEnabled())
/*     */       return; 
/* 124 */     Texture useTex = null;
/* 125 */     switch (this.connection.getConnectionStatus()) {
/*     */       case CONNECTED:
/* 127 */         useTex = TWITCH_GREEN;
/*     */         break;
/*     */       case CONNECTING:
/*     */       case DISCONNECTING:
/* 131 */         useTex = TWITCH_YELLOW;
/*     */         break;
/*     */       case DISCONNECTED:
/* 134 */         useTex = TWITCH_RED;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     sb.setColor(Color.WHITE);
/* 142 */     sb.draw(useTex, this.x, this.y - 64.0F * Settings.scale, 64.0F * Settings.scale * 0.5F, 64.0F * Settings.scale * 0.5F, 64.0F * Settings.scale, 64.0F * Settings.scale, this.iconScale, this.iconScale, 0.0F, 0, 0, 128, 128, false, false);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\de\robojumper\ststwitch\TwitchPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */