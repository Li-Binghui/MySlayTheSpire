/*    */ package com.megacrit.cardcrawl.scenes;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.scene.ShinySparkleEffect;
/*    */ import com.megacrit.cardcrawl.vfx.scene.WobblyCircleEffect;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TheEndingScene
/*    */   extends AbstractScene
/*    */ {
/* 21 */   private ArrayList<AbstractGameEffect> circles = new ArrayList<>();
/*    */   
/*    */   public TheEndingScene() {
/* 24 */     super("endingScene/scene.atlas");
/* 25 */     this.ambianceName = "AMBIANCE_BEYOND";
/* 26 */     fadeInAmbiance();
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 31 */     super.update();
/* 32 */     updateParticles();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void randomizeScene() {}
/*    */ 
/*    */   
/*    */   private void updateParticles() {
/* 41 */     for (Iterator<AbstractGameEffect> e = this.circles.iterator(); e.hasNext(); ) {
/* 42 */       AbstractGameEffect effect = e.next();
/* 43 */       effect.update();
/* 44 */       if (effect.isDone) {
/* 45 */         e.remove();
/*    */       }
/*    */     } 
/*    */     
/* 49 */     if (!(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.TrueVictoryRoom) && 
/* 50 */       this.circles.size() < 72 && !Settings.DISABLE_EFFECTS) {
/* 51 */       if (MathUtils.randomBoolean(0.2F)) {
/* 52 */         this.circles.add(new ShinySparkleEffect());
/*    */       } else {
/* 54 */         this.circles.add(new WobblyCircleEffect());
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void nextRoom(AbstractRoom room) {
/* 66 */     super.nextRoom(room);
/* 67 */     randomizeScene();
/* 68 */     if (room instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 69 */       CardCrawlGame.music.silenceBGM();
/*    */     }
/* 71 */     fadeInAmbiance();
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderCombatRoomBg(SpriteBatch sb) {
/* 76 */     sb.setColor(Color.WHITE);
/* 77 */     renderAtlasRegionIf(sb, this.bg, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderCombatRoomFg(SpriteBatch sb) {
/* 82 */     if (!this.isCamp) {
/* 83 */       sb.setBlendFunction(770, 1);
/* 84 */       for (AbstractGameEffect e : this.circles) {
/* 85 */         e.render(sb);
/*    */       }
/* 87 */       sb.setBlendFunction(770, 771);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderCampfireRoom(SpriteBatch sb) {
/* 93 */     sb.setColor(Color.WHITE);
/* 94 */     renderAtlasRegionIf(sb, this.campfireBg, true);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\scenes\TheEndingScene.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */