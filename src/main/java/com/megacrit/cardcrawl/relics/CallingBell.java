/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ 
/*    */ public class CallingBell
/*    */   extends AbstractRelic
/*    */ {
/*    */   public static final String ID = "Calling Bell";
/*    */   private boolean cardsReceived = true;
/*    */   
/*    */   public CallingBell() {
/* 20 */     super("Calling Bell", "bell.png", RelicTier.BOSS, LandingSound.SOLID);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 25 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 30 */     this.cardsReceived = false;
/* 31 */     CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 32 */     CurseOfTheBell curseOfTheBell = new CurseOfTheBell();
/* 33 */     UnlockTracker.markCardAsSeen(((AbstractCard)curseOfTheBell).cardID);
/* 34 */     group.addToBottom(curseOfTheBell.makeCopy());
/*    */     
/* 36 */     AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, this.DESCRIPTIONS[1]);
/* 37 */     CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 42 */     super.update();
/* 43 */     if (!this.cardsReceived && !AbstractDungeon.isScreenUp) {
/* 44 */       AbstractDungeon.combatRewardScreen.open();
/* 45 */       AbstractDungeon.combatRewardScreen.rewards.clear();
/*    */       
/* 47 */       AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(
/* 48 */             AbstractDungeon.returnRandomScreenlessRelic(RelicTier.COMMON)));
/* 49 */       AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(
/* 50 */             AbstractDungeon.returnRandomScreenlessRelic(RelicTier.UNCOMMON)));
/* 51 */       AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(
/* 52 */             AbstractDungeon.returnRandomScreenlessRelic(RelicTier.RARE)));
/*    */       
/* 54 */       AbstractDungeon.combatRewardScreen.positionRewards();
/* 55 */       AbstractDungeon.overlayMenu.proceedButton.setLabel(this.DESCRIPTIONS[2]);
/*    */       
/* 57 */       this.cardsReceived = true;
/* 58 */       (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;
/*    */     } 
/*    */     
/* 61 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 62 */       CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
/* 63 */       flash();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 69 */     return new CallingBell();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\CallingBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */