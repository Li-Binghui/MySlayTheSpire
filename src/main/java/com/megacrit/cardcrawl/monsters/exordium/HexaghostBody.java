/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BobEffect;
/*     */ 
/*     */ public class HexaghostBody implements Disposable {
/*     */   public static final String ID = "HexaghostBody";
/*  17 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("HexaghostBody");
/*  18 */   public static final String NAME = monsterStrings.NAME;
/*  19 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  20 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*  22 */   private float rotationSpeed = 1.0F;
/*  23 */   public float targetRotationSpeed = 30.0F; private AbstractMonster m; private static final String IMG_DIR = "images/monsters/theBottom/boss/ghost/"; private static final int W = 512; private Texture plasma1;
/*  24 */   private BobEffect effect = new BobEffect(0.75F);
/*     */   
/*     */   private Texture plasma2;
/*     */   
/*     */   private Texture plasma3;
/*     */   
/*     */   private Texture shadow;
/*  31 */   private float plasma1Angle = 0.0F; private float plasma2Angle = 0.0F; private float plasma3Angle = 0.0F;
/*  32 */   private static final float BODY_OFFSET_Y = 256.0F * Settings.scale;
/*     */   
/*     */   public HexaghostBody(AbstractMonster m) {
/*  35 */     this.m = m;
/*  36 */     this.plasma1 = ImageMaster.loadImage("images/monsters/theBottom/boss/ghost/plasma1.png");
/*  37 */     this.plasma2 = ImageMaster.loadImage("images/monsters/theBottom/boss/ghost/plasma2.png");
/*  38 */     this.plasma3 = ImageMaster.loadImage("images/monsters/theBottom/boss/ghost/plasma3.png");
/*  39 */     this.shadow = ImageMaster.loadImage("images/monsters/theBottom/boss/ghost/shadow.png");
/*     */   }
/*     */   
/*     */   public void update() {
/*  43 */     this.effect.update();
/*  44 */     this.plasma1Angle += this.rotationSpeed * Gdx.graphics.getDeltaTime();
/*  45 */     this.plasma2Angle += this.rotationSpeed / 2.0F * Gdx.graphics.getDeltaTime();
/*  46 */     this.plasma3Angle += this.rotationSpeed / 3.0F * Gdx.graphics.getDeltaTime();
/*     */     
/*  48 */     this.rotationSpeed = MathHelper.fadeLerpSnap(this.rotationSpeed, this.targetRotationSpeed);
/*  49 */     this.effect.speed = this.rotationSpeed * Gdx.graphics.getDeltaTime();
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  53 */     sb.setColor(this.m.tint.color);
/*  54 */     sb.draw(this.plasma3, this.m.drawX - 256.0F + this.m.animX + 12.0F * Settings.scale, this.m.drawY + this.m.animY + this.effect.y * 2.0F - 256.0F + BODY_OFFSET_Y, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * 0.95F, Settings.scale * 0.95F, this.plasma3Angle, 0, 0, 512, 512, false, false);
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
/*  71 */     sb.draw(this.plasma2, this.m.drawX - 256.0F + this.m.animX + 6.0F * Settings.scale, this.m.drawY + this.m.animY + this.effect.y - 256.0F + BODY_OFFSET_Y, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, this.plasma2Angle, 0, 0, 512, 512, false, false);
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
/*  88 */     sb.draw(this.plasma1, this.m.drawX - 256.0F + this.m.animX, this.m.drawY + this.m.animY + this.effect.y * 0.5F - 256.0F + BODY_OFFSET_Y, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, this.plasma1Angle, 0, 0, 512, 512, false, false);
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
/* 106 */     sb.draw(this.shadow, this.m.drawX - 256.0F + this.m.animX + 12.0F * Settings.scale, this.m.drawY + this.m.animY + this.effect.y / 4.0F - 15.0F * Settings.scale - 256.0F + BODY_OFFSET_Y, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 512, false, false);
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
/*     */   public void dispose() {
/* 127 */     this.plasma1.dispose();
/* 128 */     this.plasma2.dispose();
/* 129 */     this.plasma3.dispose();
/* 130 */     this.shadow.dispose();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\HexaghostBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */