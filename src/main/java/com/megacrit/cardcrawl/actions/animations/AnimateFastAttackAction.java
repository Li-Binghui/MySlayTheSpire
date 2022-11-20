package com.megacrit.cardcrawl.actions.animations;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class AnimateFastAttackAction extends AbstractGameAction {
    private boolean called = false;

    public AnimateFastAttackAction(AbstractCreature owner) {
        setValues(null, owner, 0);
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
    }


    public void update() {
        if (!this.called) {
            this.source.useFastAttackAnimation();
            this.called = true;
        }
        tickDuration();
    }
}
