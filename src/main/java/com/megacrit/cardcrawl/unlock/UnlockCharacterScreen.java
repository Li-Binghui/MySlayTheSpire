/*    */ package com.megacrit.cardcrawl.unlock;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.ui.buttons.UnlockConfirmButton;
import com.megacrit.cardcrawl.vfx.ConeEffect;
import com.megacrit.cardcrawl.vfx.RoomShineEffect;
import com.megacrit.cardcrawl.vfx.RoomShineEffect2;

import java.util.ArrayList;
import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnlockCharacterScreen
/*    */ {
/* 25 */   private Color unlockBgColor = new Color(0.1F, 0.2F, 0.25F, 1.0F);
/*    */   
/*    */   public AbstractUnlock unlock;
/* 28 */   private ArrayList<ConeEffect> cones = new ArrayList<>();
/*    */   
/* 30 */   private float shinyTimer = 0.0F;
/* 31 */   public UnlockConfirmButton button = new UnlockConfirmButton();
/*    */   
/*    */   public long id;
/*    */   
/*    */   public void open(AbstractUnlock unlock) {
/* 36 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.UNLOCK;
/* 37 */     this.unlock = unlock;
/* 38 */     this.id = CardCrawlGame.sound.play("UNLOCK_SCREEN");
/*    */     
/* 40 */     this.button.show();
/*    */     
/* 42 */     this.cones.clear();
/* 43 */     for (int i = 0; i < 30; i++) {
/* 44 */       this.cones.add(new ConeEffect());
/*    */     }
/*    */     
/* 47 */     unlock.onUnlockScreenOpen();
/* 48 */     UnlockTracker.hardUnlockOverride(unlock.key);
/* 49 */     UnlockTracker.lockedCharacters.remove(unlock.key);
/* 50 */     AbstractDungeon.dynamicBanner.appearInstantly(
/* 51 */         (CardCrawlGame.languagePack.getUIString("UnlockCharacterScreen")).TEXT[3]);
/*    */   }
/*    */   
/*    */   public void update() {
/* 55 */     if (InputHelper.justClickedRight) {
/* 56 */       this.button.show();
/*    */     }
/* 58 */     this.shinyTimer -= Gdx.graphics.getDeltaTime();
/* 59 */     if (this.shinyTimer < 0.0F) {
/* 60 */       this.shinyTimer = 0.2F;
/* 61 */       AbstractDungeon.topLevelEffects.add(new RoomShineEffect());
/* 62 */       AbstractDungeon.topLevelEffects.add(new RoomShineEffect());
/* 63 */       AbstractDungeon.topLevelEffects.add(new RoomShineEffect2());
/*    */     } 
/*    */     
/* 66 */     updateConeEffect();
/* 67 */     this.unlock.player.update();
/* 68 */     this.button.update();
/*    */   }
/*    */   
/*    */   private void updateConeEffect() {
/* 72 */     for (Iterator<ConeEffect> e = this.cones.iterator(); e.hasNext(); ) {
/* 73 */       ConeEffect d = e.next();
/* 74 */       d.update();
/* 75 */       if (d.isDone) {
/* 76 */         e.remove();
/*    */       }
/*    */     } 
/*    */     
/* 80 */     if (this.cones.size() < 30) {
/* 81 */       this.cones.add(new ConeEffect());
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 86 */     sb.setColor(this.unlockBgColor);
/* 87 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */     
/* 89 */     sb.setBlendFunction(770, 1);
/* 90 */     for (ConeEffect e : this.cones) {
/* 91 */       e.render(sb);
/*    */     }
/*    */     
/* 94 */     sb.setBlendFunction(770, 771);
/* 95 */     this.unlock.render(sb);
/* 96 */     this.unlock.player.renderPlayerImage(sb);
/* 97 */     this.button.render(sb);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\unlock\UnlockCharacterScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */