/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class Ambrosia extends AbstractPotion {
/*    */   public static final String POTION_ID = "Ambrosia";
/* 17 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Ambrosia");
/*    */   
/*    */   public Ambrosia() {
/* 20 */     super(potionStrings.NAME, "Ambrosia", PotionRarity.RARE, PotionSize.EYE, PotionColor.WEAK);
/* 21 */     this.labOutlineColor = Settings.PURPLE_RELIC_COLOR;
/* 22 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 27 */     this.potency = getPotency();
/* 28 */     this.description = potionStrings.DESCRIPTIONS[0];
/* 29 */     this.tips.clear();
/* 30 */     this.tips.add(new PowerTip(this.name, this.description));
/* 31 */     this.tips.add(new PowerTip(
/*    */           
/* 33 */           TipHelper.capitalize(GameDictionary.STANCE.NAMES[0]), (String)GameDictionary.keywords
/* 34 */           .get(GameDictionary.STANCE.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 39 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 40 */       addToBot((AbstractGameAction)new ChangeStanceAction("Divinity"));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 46 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 51 */     return new Ambrosia();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\Ambrosia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */