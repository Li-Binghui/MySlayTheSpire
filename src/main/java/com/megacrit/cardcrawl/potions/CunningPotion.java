/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class CunningPotion extends AbstractPotion {
/*    */   public static final String POTION_ID = "CunningPotion";
/* 17 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("CunningPotion");
/*    */   
/*    */   public CunningPotion() {
/* 20 */     super(potionStrings.NAME, "CunningPotion", PotionRarity.UNCOMMON, PotionSize.SPIKY, PotionEffect.NONE, Color.GRAY, Color.DARK_GRAY, null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     this.labOutlineColor = Settings.GREEN_RELIC_COLOR;
/* 30 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 35 */     this.potency = getPotency();
/* 36 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 37 */     this.tips.clear();
/* 38 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 43 */     Shiv shiv = new Shiv();
/* 44 */     shiv.upgrade();
/* 45 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 46 */       addToBot((AbstractGameAction)new MakeTempCardInHandAction(shiv.makeStatEquivalentCopy(), this.potency));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 52 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 57 */     return new CunningPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\CunningPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */