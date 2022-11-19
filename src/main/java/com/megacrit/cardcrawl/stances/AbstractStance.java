/*     */ package com.megacrit.cardcrawl.stances;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractStance
/*     */ {
/*  19 */   private static final Logger logger = LogManager.getLogger(AbstractStance.class.getName());
/*     */   public String name;
/*     */   public String description;
/*     */   public String ID;
/*  23 */   protected ArrayList<PowerTip> tips = new ArrayList<>();
/*     */ 
/*     */   
/*  26 */   protected Color c = Color.WHITE.cpy();
/*     */   protected static final int W = 512;
/*  28 */   protected Texture img = null;
/*  29 */   protected float particleTimer = 0.0F, particleTimer2 = 0.0F;
/*     */   
/*     */   protected float angle;
/*     */ 
/*     */   
/*     */   public abstract void updateDescription();
/*     */ 
/*     */   
/*     */   public void atStartOfTurn() {}
/*     */   
/*     */   public void onEndOfTurn() {}
/*     */   
/*     */   public void onEnterStance() {}
/*     */   
/*     */   public void onExitStance() {}
/*     */   
/*     */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/*  46 */     return damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
/*  51 */     return atDamageGive(damage, type);
/*     */   }
/*     */   
/*     */   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
/*  55 */     return damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlayCard(AbstractCard card) {}
/*     */   
/*     */   public void update() {
/*  62 */     updateAnimation();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnimation() {}
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  69 */     if (this.img == null) {
/*     */       return;
/*     */     }
/*  72 */     sb.setColor(this.c);
/*  73 */     sb.setBlendFunction(770, 1);
/*  74 */     sb.draw(this.img, AbstractDungeon.player.drawX - 256.0F + AbstractDungeon.player.animX, AbstractDungeon.player.drawY - 256.0F + AbstractDungeon.player.animY + AbstractDungeon.player.hb_h / 2.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, -this.angle, 0, 0, 512, 512, false, false);
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
/*  91 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopIdleSfx() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AbstractStance getStanceFromName(String name) {
/* 105 */     if (name.equals("Calm"))
/* 106 */       return new CalmStance(); 
/* 107 */     if (name.equals("Wrath"))
/* 108 */       return new WrathStance(); 
/* 109 */     if (name.equals("Divinity"))
/* 110 */       return new DivinityStance(); 
/* 111 */     if (name.equals("Neutral")) {
/* 112 */       return new NeutralStance();
/*     */     }
/* 114 */     logger.info("[ERROR] Unknown stance: " + name + " called for in getStanceFromName()");
/* 115 */     return null;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\stances\AbstractStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */