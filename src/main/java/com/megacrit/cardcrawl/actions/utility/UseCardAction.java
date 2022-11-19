/*     */ package com.megacrit.cardcrawl.actions.utility;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ 
/*     */ public class UseCardAction
/*     */   extends AbstractGameAction
/*     */ {
/*     */   private AbstractCard targetCard;
/*  17 */   public AbstractCreature target = null;
/*     */   
/*     */   public boolean exhaustCard;
/*     */   public boolean returnToHand;
/*     */   
/*     */   public UseCardAction(AbstractCard card, AbstractCreature target) {
/*  23 */     this.targetCard = card;
/*  24 */     this.target = target;
/*     */     
/*  26 */     if (card.exhaustOnUseOnce || card.exhaust) {
/*  27 */       this.exhaustCard = true;
/*     */     }
/*     */     
/*  30 */     setValues((AbstractCreature)AbstractDungeon.player, null, 1);
/*  31 */     this.duration = 0.15F;
/*     */     
/*  33 */     for (AbstractPower p : AbstractDungeon.player.powers) {
/*  34 */       if (!card.dontTriggerOnUseCard) {
/*  35 */         p.onUseCard(card, this);
/*     */       }
/*     */     } 
/*     */     
/*  39 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  40 */       if (!card.dontTriggerOnUseCard) {
/*  41 */         r.onUseCard(card, this);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  46 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/*  47 */       if (!card.dontTriggerOnUseCard) {
/*  48 */         c.triggerOnCardPlayed(card);
/*     */       }
/*     */     } 
/*  51 */     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/*  52 */       if (!card.dontTriggerOnUseCard) {
/*  53 */         c.triggerOnCardPlayed(card);
/*     */       }
/*     */     } 
/*  56 */     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/*  57 */       if (!card.dontTriggerOnUseCard) {
/*  58 */         c.triggerOnCardPlayed(card);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  63 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/*  64 */       for (AbstractPower p : m.powers) {
/*  65 */         if (!card.dontTriggerOnUseCard) {
/*  66 */           p.onUseCard(card, this);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  71 */     if (this.exhaustCard) {
/*  72 */       this.actionType = ActionType.EXHAUST;
/*     */     } else {
/*  74 */       this.actionType = ActionType.USE;
/*     */     } 
/*     */   }
/*     */   public boolean reboundCard = false; private static final float DUR = 0.15F;
/*     */   public UseCardAction(AbstractCard targetCard) {
/*  79 */     this(targetCard, (AbstractCreature)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  84 */     if (this.duration == 0.15F) {
/*  85 */       for (AbstractPower p : AbstractDungeon.player.powers) {
/*  86 */         if (!this.targetCard.dontTriggerOnUseCard) {
/*  87 */           p.onAfterUseCard(this.targetCard, this);
/*     */         }
/*     */       } 
/*     */       
/*  91 */       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/*  92 */         for (AbstractPower p : m.powers) {
/*  93 */           if (!this.targetCard.dontTriggerOnUseCard) {
/*  94 */             p.onAfterUseCard(this.targetCard, this);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  99 */       this.targetCard.freeToPlayOnce = false;
/* 100 */       this.targetCard.isInAutoplay = false;
/* 101 */       if (this.targetCard.purgeOnUse) {
/* 102 */         addToTop(new ShowCardAndPoofAction(this.targetCard));
/* 103 */         this.isDone = true;
/* 104 */         AbstractDungeon.player.cardInUse = null;
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 109 */       if (this.targetCard.type == AbstractCard.CardType.POWER) {
/* 110 */         addToTop(new ShowCardAction(this.targetCard));
/* 111 */         if (Settings.FAST_MODE) {
/* 112 */           addToTop(new WaitAction(0.1F));
/*     */         } else {
/* 114 */           addToTop(new WaitAction(0.7F));
/*     */         } 
/* 116 */         AbstractDungeon.player.hand.empower(this.targetCard);
/* 117 */         this.isDone = true;
/* 118 */         AbstractDungeon.player.hand.applyPowers();
/* 119 */         AbstractDungeon.player.hand.glowCheck();
/* 120 */         AbstractDungeon.player.cardInUse = null;
/*     */         
/*     */         return;
/*     */       } 
/* 124 */       AbstractDungeon.player.cardInUse = null;
/*     */ 
/*     */       
/* 127 */       boolean spoonProc = false;
/* 128 */       if (this.exhaustCard && AbstractDungeon.player.hasRelic("Strange Spoon") && this.targetCard.type != AbstractCard.CardType.POWER) {
/* 129 */         spoonProc = AbstractDungeon.cardRandomRng.randomBoolean();
/*     */       }
/*     */       
/* 132 */       if (!this.exhaustCard || spoonProc) {
/* 133 */         if (spoonProc) {
/* 134 */           AbstractDungeon.player.getRelic("Strange Spoon").flash();
/*     */         }
/* 136 */         if (this.reboundCard) {
/* 137 */           AbstractDungeon.player.hand.moveToDeck(this.targetCard, false);
/* 138 */         } else if (this.targetCard.shuffleBackIntoDrawPile) {
/* 139 */           AbstractDungeon.player.hand.moveToDeck(this.targetCard, true);
/* 140 */         } else if (this.targetCard.returnToHand) {
/* 141 */           AbstractDungeon.player.hand.moveToHand(this.targetCard);
/* 142 */           AbstractDungeon.player.onCardDrawOrDiscard();
/*     */         } else {
/* 144 */           AbstractDungeon.player.hand.moveToDiscardPile(this.targetCard);
/*     */         } 
/*     */       } else {
/* 147 */         AbstractDungeon.player.hand.moveToExhaustPile(this.targetCard);
/* 148 */         CardCrawlGame.dungeon.checkForPactAchievement();
/*     */       } 
/*     */       
/* 151 */       this.targetCard.exhaustOnUseOnce = false;
/* 152 */       this.targetCard.dontTriggerOnUseCard = false;
/*     */ 
/*     */       
/* 155 */       addToBot(new HandCheckAction());
/*     */     } 
/*     */     
/* 158 */     tickDuration();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\UseCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */