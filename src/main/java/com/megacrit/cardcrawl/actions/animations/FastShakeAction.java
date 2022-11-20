package com.megacrit.cardcrawl.actions.animations;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class FastShakeAction extends AbstractGameAction {
    private boolean called = false;
    private float shakeDur;

    public FastShakeAction(AbstractCreature owner, float shakeDur, float actionDur) {
        setValues(null, owner, 0);
        this.duration = actionDur;
        this.actionType = ActionType.WAIT;
        this.shakeDur = shakeDur;
    }


    public void update() {
        if (!this.called) {
            this.source.useShakeAnimation(this.shakeDur);
            this.called = true;
        }
        tickDuration();
    }
}

