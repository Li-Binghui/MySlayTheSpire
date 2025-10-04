package com.megacrit.cardcrawl.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/actions/unique/ApotheosisAction.class */
public class ApotheosisAction extends AbstractGameAction {
    public ApotheosisAction() {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = AbstractGameAction.ActionType.WAIT;
    }

    @Override // com.megacrit.cardcrawl.actions.AbstractGameAction
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractPlayer p = AbstractDungeon.player;
            upgradeAllCardsInGroup(p.hand);
            upgradeAllCardsInGroup(p.drawPile);
            upgradeAllCardsInGroup(p.discardPile);
            upgradeAllCardsInGroup(p.exhaustPile);
            this.isDone = true;
        }
    }

    private void upgradeAllCardsInGroup(CardGroup cardGroup) {
        Iterator<AbstractCard> it = cardGroup.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (c.canUpgrade()) {
                if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                    c.superFlash();
                }
                c.upgrade();
                c.applyPowers();
            }
        }
    }
}
