/*    */ package com.megacrit.cardcrawl.monsters.exordium;
/*    */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.SlimeAnimListener;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class ApologySlime extends AbstractMonster {
/* 20 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Apology Slime"); public static final String ID = "Apology Slime";
/* 21 */   public static final String NAME = monsterStrings.NAME;
/* 22 */   public static final String[] MOVES = monsterStrings.MOVES;
/* 23 */   public static final String[] DIALOG = monsterStrings.DIALOG; public static final int HP_MIN = 8;
/*    */   public static final int HP_MAX = 12;
/*    */   public static final int TACKLE_DAMAGE = 3;
/*    */   public static final int WEAK_TURNS = 1;
/*    */   private static final byte TACKLE = 1;
/*    */   private static final byte DEBUFF = 2;
/*    */   
/*    */   public ApologySlime() {
/* 31 */     super(NAME, "Apology Slime", AbstractDungeon.monsterHpRng.random(8, 12), 0.0F, -4.0F, 130.0F, 100.0F, null);
/*    */     
/* 33 */     this.damage.add(new DamageInfo((AbstractCreature)this, 3));
/*    */     
/* 35 */     loadAnimation("images/monsters/theBottom/slimeS/skeleton.atlas", "images/monsters/theBottom/slimeS/skeleton.json", 1.0F);
/*    */ 
/*    */ 
/*    */     
/* 39 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/* 40 */     e.setTime(e.getEndTime() * MathUtils.random());
/* 41 */     this.state.addListener((AnimationState.AnimationStateListener)new SlimeAnimListener());
/*    */   }
/*    */ 
/*    */   
/*    */   public void usePreBattleAction() {
/* 46 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, "Aw, something went wrong... NL please let the devs know!", 4.0F, 4.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void takeTurn() {
/* 52 */     switch (this.nextMove) {
/*    */       case 1:
/* 54 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 55 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 56 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 57 */         setMove((byte)2, Intent.DEBUFF);
/*    */         break;
/*    */       case 2:
/* 60 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 61 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 67 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void getMove(int num) {
/* 74 */     if (AbstractDungeon.aiRng.randomBoolean()) {
/* 75 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*    */     } else {
/* 77 */       setMove((byte)2, Intent.DEBUFF);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\ApologySlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */