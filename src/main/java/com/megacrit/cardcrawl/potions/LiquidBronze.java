/*    */ package com.megacrit.cardcrawl.potions;
/*    */

import com.badlogic.gdx.graphics.Color;
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
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class LiquidBronze extends AbstractPotion {
/* 17 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("LiquidBronze");
/*    */   
/*    */   public static final String POTION_ID = "LiquidBronze";
/*    */ 
/*    */   
/*    */   public LiquidBronze() {
/* 23 */     super(potionStrings.NAME, "LiquidBronze", PotionRarity.UNCOMMON, PotionSize.SPIKY, PotionEffect.NONE, new Color(-491249153), new Color(415023359), null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 32 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 37 */     this.potency = getPotency();
/* 38 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 39 */     this.tips.clear();
/* 40 */     this.tips.add(new PowerTip(this.name, this.description));
/* 41 */     this.tips.add(new PowerTip(
/*    */           
/* 43 */           TipHelper.capitalize(GameDictionary.THORNS.NAMES[0]), (String)GameDictionary.keywords
/* 44 */           .get(GameDictionary.THORNS.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 49 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 50 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 51 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new ThornsPower((AbstractCreature)abstractPlayer, this.potency), this.potency));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 57 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 62 */     return new LiquidBronze();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\LiquidBronze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */