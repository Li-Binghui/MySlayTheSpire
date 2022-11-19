/*    */ package com.megacrit.cardcrawl.ui.panels;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.options.ConfirmPopup;
import com.megacrit.cardcrawl.vfx.WarningSignEffect;

import java.util.ArrayList;
import java.util.Iterator;
/*    */ 
/*    */ public class DeleteSaveConfirmPopup
/*    */   extends ConfirmPopup {
/* 13 */   protected static final String[] D_TEXT = (CardCrawlGame.languagePack.getUIString("DeletePopup")).TEXT;
/*    */   
/* 15 */   private ArrayList<WarningSignEffect> effects = new ArrayList<>();
/*    */   
/*    */   public DeleteSaveConfirmPopup() {
/* 18 */     super(D_TEXT[0], D_TEXT[3], ConfirmType.DELETE_SAVE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     super.update();
/*    */     
/* 25 */     if (this.shown && this.effects.isEmpty()) {
/* 26 */       this.effects.add(new WarningSignEffect(Settings.WIDTH / 2.0F, Settings.OPTION_Y + 275.0F * Settings.scale));
/*    */     }
/*    */ 
/*    */     
/* 30 */     for (Iterator<WarningSignEffect> i = this.effects.iterator(); i.hasNext(); ) {
/* 31 */       WarningSignEffect e = i.next();
/* 32 */       e.update();
/* 33 */       if (e.isDone) {
/* 34 */         i.remove();
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public void open(int slot) {
/* 40 */     this.slot = slot;
/* 41 */     this.shown = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 46 */     super.render(sb);
/* 47 */     renderWarning(sb);
/*    */   }
/*    */   
/*    */   private void renderWarning(SpriteBatch sb) {
/* 51 */     for (WarningSignEffect e : this.effects)
/* 52 */       e.render(sb); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\DeleteSaveConfirmPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */