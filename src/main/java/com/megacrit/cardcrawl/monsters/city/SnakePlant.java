/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.FrailPower;
/*     */ import com.megacrit.cardcrawl.powers.MalleablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
/*     */ 
/*     */ public class SnakePlant extends AbstractMonster {
/*  27 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("SnakePlant"); public static final String ID = "SnakePlant";
/*  28 */   public static final String NAME = monsterStrings.NAME;
/*  29 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  30 */   public static final String[] DIALOG = monsterStrings.DIALOG; private static final int HP_MIN = 75;
/*     */   private static final int HP_MAX = 79;
/*     */   private static final int A_2_HP_MIN = 78;
/*     */   private static final int A_2_HP_MAX = 82;
/*     */   private static final byte CHOMPY_CHOMPS = 1;
/*     */   private static final byte SPORES = 2;
/*     */   private static final int CHOMPY_AMT = 3;
/*     */   private static final int CHOMPY_DMG = 7;
/*     */   private static final int A_2_CHOMPY_DMG = 8;
/*     */   private int rainBlowsDmg;
/*     */   
/*     */   public SnakePlant(float x, float y) {
/*  42 */     super(NAME, "SnakePlant", 79, 0.0F, -44.0F, 350.0F, 360.0F, null, x, y + 50.0F);
/*  43 */     loadAnimation("images/monsters/theCity/snakePlant/skeleton.atlas", "images/monsters/theCity/snakePlant/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  47 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  48 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  49 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  50 */     e.setTimeScale(0.8F);
/*     */     
/*  52 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  53 */       setHp(78, 82);
/*     */     } else {
/*  55 */       setHp(75, 79);
/*     */     } 
/*     */     
/*  58 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  59 */       this.rainBlowsDmg = 8;
/*     */     } else {
/*  61 */       this.rainBlowsDmg = 7;
/*     */     } 
/*     */     
/*  64 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.rainBlowsDmg));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  69 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new MalleablePower((AbstractCreature)this)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/*  74 */     switch (stateName) {
/*     */       case "ATTACK":
/*  76 */         this.state.setAnimation(0, "Attack", false);
/*  77 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/*  84 */     super.damage(info);
/*  85 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/*  86 */       this.state.setAnimation(0, "Hit", false);
/*  87 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int numBlows, i;
/*  93 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/*  94 */     switch (this.nextMove) {
/*     */       case 1:
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  97 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/*  98 */         numBlows = 3;
/*     */         
/* 100 */         for (i = 0; i < numBlows; i++) {
/* 101 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(AbstractDungeon.player.hb.cX + 
/*     */ 
/*     */                   
/* 104 */                   MathUtils.random(-50.0F, 50.0F) * Settings.scale, AbstractDungeon.player.hb.cY + 
/* 105 */                   MathUtils.random(-50.0F, 50.0F) * Settings.scale, Color.CHARTREUSE
/* 106 */                   .cpy()), 0.2F));
/*     */           
/* 108 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)abstractPlayer, this.damage
/* 109 */                 .get(0), AbstractGameAction.AttackEffect.NONE, true));
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 113 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 119 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 134 */     if (AbstractDungeon.ascensionLevel >= 17) {
/* 135 */       if (num < 65) {
/* 136 */         if (lastTwoMoves((byte)1)) {
/* 137 */           setMove(MOVES[0], (byte)2, Intent.STRONG_DEBUFF);
/*     */         } else {
/* 139 */           setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
/*     */         }
/*     */       
/* 142 */       } else if (lastMove((byte)2) || lastMoveBefore((byte)2)) {
/* 143 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
/*     */       } else {
/* 145 */         setMove(MOVES[0], (byte)2, Intent.STRONG_DEBUFF);
/*     */       }
/*     */     
/*     */     }
/* 149 */     else if (num < 65) {
/* 150 */       if (lastTwoMoves((byte)1)) {
/* 151 */         setMove(MOVES[0], (byte)2, Intent.STRONG_DEBUFF);
/*     */       } else {
/* 153 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
/*     */       }
/*     */     
/* 156 */     } else if (lastMove((byte)2)) {
/* 157 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
/*     */     } else {
/* 159 */       setMove(MOVES[0], (byte)2, Intent.STRONG_DEBUFF);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\SnakePlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */