/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
/*    */ 
/*    */ public class EmptyBody extends AbstractCard {
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("EmptyBody"); public static final String ID = "EmptyBody";
/*    */   
/*    */   public EmptyBody() {
/* 20 */     super("EmptyBody", cardStrings.NAME, "purple/skill/empty_body", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.COMMON, CardTarget.SELF);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 31 */     this.baseBlock = 7;
/* 32 */     this.tags.add(CardTags.EMPTY);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));
/* 38 */     addToBot((AbstractGameAction)new NotStanceCheckAction("Neutral", (AbstractGameAction)new VFXAction((AbstractGameEffect)new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
/*    */ 
/*    */ 
/*    */     
/* 42 */     addToBot((AbstractGameAction)new ChangeStanceAction("Neutral"));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 47 */     return new EmptyBody();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 52 */     if (!this.upgraded) {
/* 53 */       upgradeName();
/* 54 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\EmptyBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */