/*    */ package com.megacrit.cardcrawl.potions;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class AncientPotion extends AbstractPotion {
/* 16 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Ancient Potion");
/*    */   
/*    */   public static final String POTION_ID = "Ancient Potion";
/*    */ 
/*    */   
/*    */   public AncientPotion() {
/* 22 */     super(potionStrings.NAME, "Ancient Potion", PotionRarity.UNCOMMON, PotionSize.FAIRY, PotionColor.ANCIENT);
/* 23 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 28 */     this.potency = getPotency();
/* 29 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 30 */     this.tips.clear();
/* 31 */     this.tips.add(new PowerTip(this.name, this.description));
/* 32 */     this.tips.add(new PowerTip(
/*    */           
/* 34 */           TipHelper.capitalize(GameDictionary.ARTIFACT.NAMES[0]), (String)GameDictionary.keywords
/* 35 */           .get(GameDictionary.ARTIFACT.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 40 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 41 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 42 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new ArtifactPower((AbstractCreature)abstractPlayer, this.potency), this.potency));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 48 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 53 */     return new AncientPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\AncientPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */