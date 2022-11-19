/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.DigOption;

import java.util.ArrayList;
/*    */ 
/*    */ public class Shovel
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Shovel";
/*    */   
/*    */   public Shovel() {
/* 14 */     super("Shovel", "shovel.png", RelicTier.RARE, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 24 */     if (AbstractDungeon.floorNum >= 48 && !Settings.isEndless) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     int campfireRelicCount = 0;
/*    */     
/* 30 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 31 */       if (r instanceof PeacePipe || r instanceof Shovel || r instanceof Girya) {
/* 32 */         campfireRelicCount++;
/*    */       }
/*    */     } 
/*    */     
/* 36 */     return (campfireRelicCount < 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
/* 41 */     options.add(new DigOption());
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 46 */     return new Shovel();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Shovel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */