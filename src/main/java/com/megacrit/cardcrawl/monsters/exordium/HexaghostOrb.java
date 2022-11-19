/*    */ package com.megacrit.cardcrawl.monsters.exordium;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.GhostlyFireEffect;
import com.megacrit.cardcrawl.vfx.GhostlyWeakFireEffect;
import com.megacrit.cardcrawl.vfx.combat.GhostIgniteEffect;
/*    */ 
/*    */ public class HexaghostOrb {
/*    */   public static final String ID = "HexaghostOrb";
/* 18 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("HexaghostOrb");
/* 19 */   public static final String NAME = monsterStrings.NAME;
/* 20 */   public static final String[] MOVES = monsterStrings.MOVES;
/* 21 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*    */   
/* 23 */   private BobEffect effect = new BobEffect(2.0F);
/*    */   
/*    */   private float activateTimer;
/*    */   public boolean activated = false;
/*    */   public boolean hidden = false;
/* 28 */   private float particleTimer = 0.0F; public boolean playedSfx = false; private Color color; private float x; private float y;
/*    */   private static final float PARTICLE_INTERVAL = 0.06F;
/*    */   
/*    */   public HexaghostOrb(float x, float y, int index) {
/* 32 */     this.x = x * Settings.scale + MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 33 */     this.y = y * Settings.scale + MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 34 */     this.activateTimer = index * 0.3F;
/* 35 */     this.color = Color.CHARTREUSE.cpy();
/* 36 */     this.color.a = 0.0F;
/* 37 */     this.hidden = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void activate(float oX, float oY) {
/* 44 */     this.playedSfx = false;
/* 45 */     this.activated = true;
/* 46 */     this.hidden = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void deactivate() {
/* 53 */     this.activated = false;
/*    */   }
/*    */   
/*    */   public void hide() {
/* 57 */     this.hidden = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update(float oX, float oY) {
/* 64 */     if (!this.hidden) {
/* 65 */       if (this.activated) {
/* 66 */         this.activateTimer -= Gdx.graphics.getDeltaTime();
/* 67 */         if (this.activateTimer < 0.0F) {
/* 68 */           if (!this.playedSfx) {
/* 69 */             this.playedSfx = true;
/* 70 */             AbstractDungeon.effectsQueue.add(new GhostIgniteEffect(this.x + oX, this.y + oY));
/* 71 */             if (MathUtils.randomBoolean()) {
/* 72 */               CardCrawlGame.sound.play("GHOST_ORB_IGNITE_1", 0.3F);
/*    */             } else {
/* 74 */               CardCrawlGame.sound.play("GHOST_ORB_IGNITE_2", 0.3F);
/*    */             } 
/*    */           } 
/* 77 */           this.color.a = MathHelper.fadeLerpSnap(this.color.a, 1.0F);
/* 78 */           this.effect.update();
/* 79 */           this.effect.update();
/* 80 */           this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 81 */           if (this.particleTimer < 0.0F) {
/* 82 */             AbstractDungeon.effectList.add(new GhostlyFireEffect(this.x + oX + this.effect.y * 2.0F, this.y + oY + this.effect.y * 2.0F));
/*    */             
/* 84 */             this.particleTimer = 0.06F;
/*    */           } 
/*    */         } 
/*    */       } else {
/* 88 */         this.effect.update();
/* 89 */         this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 90 */         if (this.particleTimer < 0.0F) {
/* 91 */           AbstractDungeon.effectList.add(new GhostlyWeakFireEffect(this.x + oX + this.effect.y * 2.0F, this.y + oY + this.effect.y * 2.0F));
/*    */           
/* 93 */           this.particleTimer = 0.06F;
/*    */         } 
/*    */       } 
/*    */     } else {
/* 97 */       this.color.a = MathHelper.fadeLerpSnap(this.color.a, 0.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\HexaghostOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */