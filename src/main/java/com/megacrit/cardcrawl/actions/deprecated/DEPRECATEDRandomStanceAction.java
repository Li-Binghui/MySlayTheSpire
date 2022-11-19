/*    */ package com.megacrit.cardcrawl.actions.deprecated;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.CalmStance;
import com.megacrit.cardcrawl.stances.WrathStance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DEPRECATEDRandomStanceAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 25 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*    */       
/* 27 */       ArrayList<AbstractStance> stances = new ArrayList<>();
/* 28 */       AbstractStance oldStance = AbstractDungeon.player.stance;
/*    */       
/* 30 */       if (!oldStance.ID.equals("Wrath")) {
/* 31 */         stances.add(new WrathStance());
/*    */       }
/* 33 */       if (!oldStance.ID.equals("Calm")) {
/* 34 */         stances.add(new CalmStance());
/*    */       }
/*    */       
/* 37 */       Collections.shuffle(stances, new Random(AbstractDungeon.cardRandomRng.randomLong()));
/*    */       
/* 39 */       addToBot((AbstractGameAction)new ChangeStanceAction(((AbstractStance)stances.get(0)).ID));
/*    */       
/* 41 */       if (Settings.FAST_MODE) {
/* 42 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/* 47 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\deprecated\DEPRECATEDRandomStanceAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */