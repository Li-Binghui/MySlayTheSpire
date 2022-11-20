package com.megacrit.cardcrawl.actions.animations;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class AnimateSlowAttackAction extends AbstractGameAction {
    private boolean called = false;

    public AnimateSlowAttackAction(AbstractCreature owner) {
        setValues(null, owner, 0);
        this.startDuration = 0.5F;
        this.duration = this.startDuration;
        this.actionType = ActionType.WAIT;
    }


    public void update() {
        if (!this.called) {
            if (Settings.FAST_MODE) {
                this.source.useFastAttackAnimation();
                this.duration = Settings.ACTION_DUR_FAST;
            } else {
                this.source.useSlowAttackAnimation();
            }
            this.called = true;
        }
        tickDuration();
    }
}
