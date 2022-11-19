/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.DialogWord;
/*    */ 
/*    */ public class GameSavedEffect extends AbstractGameEffect {
/* 12 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("GameSavedEffect");
/* 13 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     if (ModHelper.enabledMods.size() > 0) {
/* 18 */       if (ModHelper.enabledMods.size() > 3) {
/* 19 */         AbstractDungeon.topLevelEffects.add(new SpeechTextEffect(1600.0F * Settings.scale, Settings.HEIGHT - 74.0F * Settings.scale, 2.0F, TEXT[0], DialogWord.AppearEffect.FADE_IN));
/*    */ 
/*    */       
/*    */       }
/*    */       else {
/*    */ 
/*    */ 
/*    */         
/* 27 */         AbstractDungeon.topLevelEffects.add(new SpeechTextEffect(1600.0F * Settings.scale, Settings.HEIGHT - 26.0F * Settings.scale, 2.0F, TEXT[0], DialogWord.AppearEffect.FADE_IN));
/*    */       
/*    */       }
/*    */ 
/*    */     
/*    */     }
/*    */     else {
/*    */ 
/*    */       
/* 36 */       AbstractDungeon.topLevelEffects.add(new SpeechTextEffect(1450.0F * Settings.scale, Settings.HEIGHT - 26.0F * Settings.scale, 2.0F, TEXT[0], DialogWord.AppearEffect.FADE_IN));
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 45 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\GameSavedEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */