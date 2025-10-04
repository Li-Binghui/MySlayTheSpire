package com.megacrit.cardcrawl.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.stats.AchievementGrid;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/actions/unique/ExhaustAllNonAttackAction.class */
public class ExhaustAllNonAttackAction extends AbstractGameAction {
    private float startingDuration;

    public ExhaustAllNonAttackAction() {
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    @Override // com.megacrit.cardcrawl.actions.AbstractGameAction
    public void update() {
        if (this.duration == this.startingDuration) {
            Iterator<AbstractCard> it = AbstractDungeon.player.hand.group.iterator();
            while (it.hasNext()) {
                AbstractCard c = it.next();
                if (c.type != AbstractCard.CardType.ATTACK) {
                    addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                }
            }
            this.isDone = true;
            if (AbstractDungeon.player.exhaustPile.size() >= 20) {
                UnlockTracker.unlockAchievement(AchievementGrid.THE_PACT_KEY);
            }
        }
    }
}
