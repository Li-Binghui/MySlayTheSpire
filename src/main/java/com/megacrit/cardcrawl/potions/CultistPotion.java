/*    */ package com.megacrit.cardcrawl.potions;
/*    */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.city.Byrd;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RitualPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class CultistPotion extends AbstractPotion {
/* 21 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("CultistPotion"); public static final String POTION_ID = "CultistPotion";
/*    */   
/*    */   public CultistPotion() {
/* 24 */     super(potionStrings.NAME, "CultistPotion", PotionRarity.RARE, PotionSize.MOON, PotionEffect.NONE, new Color(676576511), new Color(472670463), null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 33 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 38 */     this.potency = getPotency();
/* 39 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 40 */     this.tips.clear();
/* 41 */     this.tips.add(new PowerTip(this.name, this.description));
/* 42 */     this.tips.add(new PowerTip(
/*    */           
/* 44 */           TipHelper.capitalize(GameDictionary.RITUAL.NAMES[0]), (String)GameDictionary.keywords
/* 45 */           .get(GameDictionary.RITUAL.NAMES[0])));
/* 46 */     this.tips.add(new PowerTip(
/*    */           
/* 48 */           TipHelper.capitalize(GameDictionary.STRENGTH.NAMES[0]), (String)GameDictionary.keywords
/* 49 */           .get(GameDictionary.STRENGTH.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 54 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 55 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 56 */       playSfx();
/* 57 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction(true, Byrd.DIALOG[0], 1.2F, 1.2F));
/* 58 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new RitualPower((AbstractCreature)abstractPlayer, this.potency, true), this.potency));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void playSfx() {
/* 64 */     int roll = MathUtils.random(2);
/* 65 */     if (roll == 0) {
/* 66 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_CULTIST_1A"));
/* 67 */     } else if (roll == 1) {
/* 68 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_CULTIST_1B"));
/*    */     } else {
/* 70 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_CULTIST_1C"));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 76 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 81 */     return new CultistPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\CultistPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */