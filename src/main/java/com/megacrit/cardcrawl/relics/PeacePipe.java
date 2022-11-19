/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.TokeOption;

import java.util.ArrayList;
/*    */ 
/*    */ public class PeacePipe
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Peace Pipe";
/*    */   
/*    */   public PeacePipe() {
/* 15 */     super("Peace Pipe", "peacePipe.png", RelicTier.RARE, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 25 */     if (AbstractDungeon.floorNum >= 48 && !Settings.isEndless) {
/* 26 */       return false;
/*    */     }
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
/* 41 */     options.add(new TokeOption(
/*    */ 
/*    */           
/* 44 */           !CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).isEmpty()));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 49 */     return new PeacePipe();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\PeacePipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */