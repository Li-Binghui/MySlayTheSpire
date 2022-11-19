/*     */ package com.megacrit.cardcrawl.monsters.ending;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.ArtifactPower;
/*     */ import com.megacrit.cardcrawl.powers.FocusPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.powers.SurroundedPower;
/*     */ 
/*     */ public class SpireShield
/*     */   extends AbstractMonster {
/*     */   public static final String ID = "SpireShield";
/*  28 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("SpireShield");
/*  29 */   public static final String NAME = monsterStrings.NAME;
/*  30 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  31 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */ 
/*     */   
/*  34 */   private int moveCount = 0;
/*     */   
/*     */   private static final byte BASH = 1;
/*     */   private static final byte FORTIFY = 2;
/*     */   
/*     */   public SpireShield() {
/*  40 */     super(NAME, "SpireShield", 110, 0.0F, -20.0F, 380.0F, 290.0F, null, -1000.0F, 15.0F);
/*  41 */     this.type = EnemyType.ELITE;
/*     */     
/*  43 */     loadAnimation("images/monsters/theEnding/shield/skeleton.atlas", "images/monsters/theEnding/shield/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  47 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  48 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  49 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*     */     
/*  51 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  52 */       setHp(125);
/*     */     } else {
/*  54 */       setHp(110);
/*     */     } 
/*     */     
/*  57 */     if (AbstractDungeon.ascensionLevel >= 3) {
/*  58 */       this.damage.add(new DamageInfo((AbstractCreature)this, 14));
/*  59 */       this.damage.add(new DamageInfo((AbstractCreature)this, 38));
/*     */     } else {
/*  61 */       this.damage.add(new DamageInfo((AbstractCreature)this, 12));
/*  62 */       this.damage.add(new DamageInfo((AbstractCreature)this, 34));
/*     */     } 
/*     */   }
/*     */   private static final byte SMASH = 3; private static final int BASH_DEBUFF = -1; private static final int FORTIFY_BLOCK = 30;
/*     */   
/*     */   public void usePreBattleAction() {
/*  68 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new SurroundedPower((AbstractCreature)AbstractDungeon.player)));
/*     */ 
/*     */     
/*  71 */     if (AbstractDungeon.ascensionLevel >= 18) {
/*  72 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 2)));
/*     */     } else {
/*  74 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 1)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  80 */     switch (this.nextMove) {
/*     */       case 1:
/*  82 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  83 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.35F));
/*  84 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  85 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*     */ 
/*     */         
/*  88 */         if (!AbstractDungeon.player.orbs.isEmpty() && AbstractDungeon.aiRng.randomBoolean()) {
/*  89 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FocusPower((AbstractCreature)AbstractDungeon.player, -1), -1));
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */ 
/*     */         
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, -1), -1));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 105 */         for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 106 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)this, 30));
/*     */         }
/*     */         break;
/*     */       case 3:
/* 110 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "OLD_ATTACK"));
/* 111 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/* 112 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 113 */               .get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*     */         
/* 115 */         if (AbstractDungeon.ascensionLevel >= 18) {
/* 116 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 99)); break;
/*     */         } 
/* 118 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, ((DamageInfo)this.damage.get(1)).output));
/*     */         break;
/*     */     } 
/*     */     
/* 122 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 127 */     switch (this.moveCount % 3) {
/*     */       case 0:
/* 129 */         if (AbstractDungeon.aiRng.randomBoolean()) {
/* 130 */           setMove((byte)2, Intent.DEFEND); break;
/*     */         } 
/* 132 */         setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */         break;
/*     */       
/*     */       case 1:
/* 136 */         if (!lastMove((byte)1)) {
/* 137 */           setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base); break;
/*     */         } 
/* 139 */         setMove((byte)2, Intent.DEFEND);
/*     */         break;
/*     */       
/*     */       default:
/* 143 */         setMove((byte)3, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
/*     */         break;
/*     */     } 
/* 146 */     this.moveCount++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 151 */     switch (key) {
/*     */       case "OLD_ATTACK":
/* 153 */         this.state.setAnimation(0, "old_attack", false);
/* 154 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case "ATTACK":
/* 157 */         this.state.setAnimation(0, "Attack", false);
/* 158 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 167 */     super.damage(info);
/* 168 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 169 */       this.state.setAnimation(0, "Hit", false);
/* 170 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 176 */     super.die();
/* 177 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 178 */       if (!m.isDead && !m.isDying) {
/* 179 */         if (AbstractDungeon.player.hasPower("Surrounded")) {
/* 180 */           AbstractDungeon.player.flipHorizontal = (m.drawX < AbstractDungeon.player.drawX);
/* 181 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, "Surrounded"));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 187 */         if (m.hasPower("BackAttack"))
/* 188 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)m, (AbstractCreature)m, "BackAttack")); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\ending\SpireShield.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */