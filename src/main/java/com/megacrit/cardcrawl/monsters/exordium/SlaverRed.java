/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.EntangleEffect;
/*     */ 
/*     */ public class SlaverRed extends AbstractMonster {
/*  25 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("SlaverRed"); public static final String ID = "SlaverRed";
/*  26 */   public static final String NAME = monsterStrings.NAME;
/*  27 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  28 */   public static final String[] DIALOG = monsterStrings.DIALOG; private static final int HP_MIN = 46;
/*     */   private static final int HP_MAX = 50;
/*     */   private static final int A_2_HP_MIN = 48;
/*     */   private static final int A_2_HP_MAX = 52;
/*     */   private static final int STAB_DMG = 13;
/*     */   private static final int A_2_STAB_DMG = 14;
/*     */   private static final int SCRAPE_DMG = 8;
/*     */   private static final int A_2_SCRAPE_DMG = 9;
/*     */   private int stabDmg;
/*     */   private int scrapeDmg;
/*  38 */   private int VULN_AMT = 1; private static final byte STAB = 1; private static final byte ENTANGLE = 2;
/*     */   private static final byte SCRAPE = 3;
/*  40 */   private static final String SCRAPE_NAME = MOVES[0]; private static final String ENTANGLE_NAME = MOVES[1];
/*     */   private boolean usedEntangle = false, firstTurn = true;
/*     */   
/*     */   public SlaverRed(float x, float y) {
/*  44 */     super(NAME, "SlaverRed", 50, 0.0F, 0.0F, 170.0F, 230.0F, null, x, y);
/*     */     
/*  46 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  47 */       setHp(48, 52);
/*     */     } else {
/*  49 */       setHp(46, 50);
/*     */     } 
/*     */     
/*  52 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  53 */       this.stabDmg = 14;
/*  54 */       this.scrapeDmg = 9;
/*     */     } else {
/*  56 */       this.stabDmg = 13;
/*  57 */       this.scrapeDmg = 8;
/*     */     } 
/*     */     
/*  60 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.stabDmg));
/*  61 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.scrapeDmg));
/*     */     
/*  63 */     loadAnimation("images/monsters/theBottom/redSlaver/skeleton.atlas", "images/monsters/theBottom/redSlaver/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  67 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  68 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  69 */     this.firstTurn = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  74 */     switch (this.nextMove) {
/*     */       case 2:
/*  76 */         playSfx();
/*  77 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "Use Net"));
/*  78 */         if (this.hb != null && AbstractDungeon.player.hb != null && 
/*  79 */           !Settings.FAST_MODE) {
/*  80 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new EntangleEffect(this.hb.cX - 70.0F * Settings.scale, this.hb.cY + 10.0F * Settings.scale, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.5F));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  90 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new EntanglePower((AbstractCreature)AbstractDungeon.player)));
/*     */         
/*  92 */         this.usedEntangle = true;
/*     */         break;
/*     */       case 1:
/*  95 */         playSfx();
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  97 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  98 */               .get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*     */         break;
/*     */       case 3:
/* 101 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 102 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 103 */               .get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/* 104 */         if (AbstractDungeon.ascensionLevel >= 17) {
/* 105 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, this.VULN_AMT + 1, true), this.VULN_AMT + 1));
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */ 
/*     */         
/* 112 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, this.VULN_AMT, true), this.VULN_AMT));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void playSfx() {
/* 127 */     int roll = MathUtils.random(1);
/* 128 */     if (roll == 0) {
/* 129 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_SLAVERRED_1A"));
/*     */     } else {
/* 131 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_SLAVERRED_1B"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 136 */     int roll = MathUtils.random(1);
/* 137 */     if (roll == 0) {
/* 138 */       CardCrawlGame.sound.play("VO_SLAVERRED_2A");
/*     */     } else {
/* 140 */       CardCrawlGame.sound.play("VO_SLAVERRED_2B");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/* 146 */     float tmp = this.state.getCurrent(0).getTime();
/* 147 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idleNoNet", true);
/* 148 */     e.setTime(tmp);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 153 */     if (this.firstTurn) {
/* 154 */       this.firstTurn = false;
/* 155 */       setMove((byte)1, Intent.ATTACK, this.stabDmg);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 160 */     if (num >= 75 && !this.usedEntangle) {
/* 161 */       setMove(ENTANGLE_NAME, (byte)2, Intent.STRONG_DEBUFF);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 166 */     if (num >= 55 && this.usedEntangle && !lastTwoMoves((byte)1)) {
/* 167 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       return;
/*     */     } 
/* 171 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*     */       
/* 173 */       if (!lastMove((byte)3)) {
/* 174 */         setMove(SCRAPE_NAME, (byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */         return;
/*     */       } 
/* 177 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 182 */     if (!lastTwoMoves((byte)3)) {
/* 183 */       setMove(SCRAPE_NAME, (byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */       return;
/*     */     } 
/* 186 */     setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 194 */     super.die();
/* 195 */     playDeathSfx();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\SlaverRed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */