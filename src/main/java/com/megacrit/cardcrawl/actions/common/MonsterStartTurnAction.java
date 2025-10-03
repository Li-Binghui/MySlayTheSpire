package com.megacrit.cardcrawl.actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/actions/common/MonsterStartTurnAction.class */
public class MonsterStartTurnAction extends AbstractGameAction {
    private static final float DURATION = Settings.ACTION_DUR_FAST;

    public MonsterStartTurnAction() {
        this.duration = DURATION;
    }

    @Override // com.megacrit.cardcrawl.actions.AbstractGameAction
    public void update() {
        if (this.duration == DURATION) {
            this.isDone = true;
            AbstractDungeon.getCurrRoom().monsters.applyPreTurnLogic();
        }
        tickDuration();
    }
}
