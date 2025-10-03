package com.megacrit.cardcrawl.actions.common;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/actions/common/MakeTempCardInHandAction.class */
public class MakeTempCardInHandAction extends AbstractGameAction {
    private AbstractCard c;
    private static final float PADDING = 25.0f * Settings.scale;
    private boolean isOtherCardInCenter;
    private boolean sameUUID;

    public MakeTempCardInHandAction(AbstractCard card, boolean isOtherCardInCenter) {
        this.isOtherCardInCenter = true;
        this.sameUUID = false;
        UnlockTracker.markCardAsSeen(card.cardID);
        this.amount = 1;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.c = card;
        if (this.c.type != AbstractCard.CardType.CURSE && this.c.type != AbstractCard.CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower")) {
            this.c.upgrade();
        }
        this.isOtherCardInCenter = isOtherCardInCenter;
    }

    public MakeTempCardInHandAction(AbstractCard card) {
        this(card, 1);
    }

    public MakeTempCardInHandAction(AbstractCard card, int amount) {
        this.isOtherCardInCenter = true;
        this.sameUUID = false;
        UnlockTracker.markCardAsSeen(card.cardID);
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.c = card;
        if (this.c.type != AbstractCard.CardType.CURSE && this.c.type != AbstractCard.CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower")) {
            this.c.upgrade();
        }
    }

    public MakeTempCardInHandAction(AbstractCard card, int amount, boolean isOtherCardInCenter) {
        this(card, amount);
        this.isOtherCardInCenter = isOtherCardInCenter;
    }

    public MakeTempCardInHandAction(AbstractCard card, boolean isOtherCardInCenter, boolean sameUUID) {
        this(card, 1);
        this.isOtherCardInCenter = isOtherCardInCenter;
        this.sameUUID = sameUUID;
    }

    @Override // com.megacrit.cardcrawl.actions.AbstractGameAction
    public void update() {
        if (this.amount == 0) {
            this.isDone = true;
            return;
        }
        int discardAmount = 0;
        int handAmount = this.amount;
        if (this.amount + AbstractDungeon.player.hand.size() > 10) {
            AbstractDungeon.player.createHandIsFullDialog();
            discardAmount = (this.amount + AbstractDungeon.player.hand.size()) - 10;
            handAmount -= discardAmount;
        }
        addToHand(handAmount);
        addToDiscard(discardAmount);
        if (this.amount > 0) {
            addToTop(new WaitAction(0.8f));
        }
        this.isDone = true;
    }

    private void addToHand(int handAmt) {
        switch (this.amount) {
            case 0:
                break;
            case 1:
                if (handAmt == 1) {
                    if (this.isOtherCardInCenter) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard(), (Settings.WIDTH / 2.0f) - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0f));
                        break;
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard()));
                        break;
                    }
                }
                break;
            case 2:
                if (handAmt == 1) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard(), (Settings.WIDTH / 2.0f) - (PADDING + (AbstractCard.IMG_WIDTH * 0.5f)), Settings.HEIGHT / 2.0f));
                    break;
                } else if (handAmt == 2) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard(), (Settings.WIDTH / 2.0f) + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0f));
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard(), (Settings.WIDTH / 2.0f) - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0f));
                    break;
                }
                break;
            case 3:
                if (handAmt == 1) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard(), (Settings.WIDTH / 2.0f) - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0f));
                    break;
                } else if (handAmt == 2) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard(), (Settings.WIDTH / 2.0f) + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0f));
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard(), (Settings.WIDTH / 2.0f) - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0f));
                    break;
                } else if (handAmt == 3) {
                    for (int i = 0; i < this.amount; i++) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard()));
                    }
                    break;
                }
                break;
            default:
                for (int i2 = 0; i2 < handAmt; i2++) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard(), MathUtils.random(Settings.WIDTH * 0.2f, Settings.WIDTH * 0.8f), MathUtils.random(Settings.HEIGHT * 0.3f, Settings.HEIGHT * 0.7f)));
                }
                break;
        }
    }

    private void addToDiscard(int discardAmt) {
        switch (this.amount) {
            case 0:
                break;
            case 1:
                if (discardAmt == 1) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(), (Settings.WIDTH / 2.0f) + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0f));
                    break;
                }
                break;
            case 2:
                if (discardAmt == 1) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(), (Settings.WIDTH * 0.5f) - (PADDING + (AbstractCard.IMG_WIDTH * 0.5f)), Settings.HEIGHT * 0.5f));
                    break;
                } else if (discardAmt == 2) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(), (Settings.WIDTH * 0.5f) - (PADDING + (AbstractCard.IMG_WIDTH * 0.5f)), Settings.HEIGHT * 0.5f));
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(), (Settings.WIDTH * 0.5f) + PADDING + (AbstractCard.IMG_WIDTH * 0.5f), Settings.HEIGHT * 0.5f));
                    break;
                }
                break;
            case 3:
                if (discardAmt == 1) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(), (Settings.WIDTH * 0.5f) + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT * 0.5f));
                    break;
                } else if (discardAmt == 2) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH * 0.5f, Settings.HEIGHT * 0.5f));
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(), (Settings.WIDTH * 0.5f) + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT * 0.5f));
                    break;
                } else if (discardAmt == 3) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH * 0.5f, Settings.HEIGHT * 0.5f));
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(), (Settings.WIDTH * 0.5f) - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT * 0.5f));
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(), (Settings.WIDTH * 0.5f) + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT * 0.5f));
                    break;
                }
                break;
            default:
                for (int i = 0; i < discardAmt; i++) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(), MathUtils.random(Settings.WIDTH * 0.2f, Settings.WIDTH * 0.8f), MathUtils.random(Settings.HEIGHT * 0.3f, Settings.HEIGHT * 0.7f)));
                }
                break;
        }
    }

    private AbstractCard makeNewCard() {
        if (this.sameUUID) {
            return this.c.makeSameInstanceOf();
        }
        return this.c.makeStatEquivalentCopy();
    }
}
