package com.megacrit.cardcrawl.actions.unique;

import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.mods.Lethality;
import com.megacrit.cardcrawl.daily.mods.TimeDilation;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.GremlinLeader;
import com.megacrit.cardcrawl.monsters.exordium.GremlinFat;
import com.megacrit.cardcrawl.monsters.exordium.GremlinThief;
import com.megacrit.cardcrawl.monsters.exordium.GremlinTsundere;
import com.megacrit.cardcrawl.monsters.exordium.GremlinWarrior;
import com.megacrit.cardcrawl.monsters.exordium.GremlinWizard;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/actions/unique/SummonGremlinAction.class */
public class SummonGremlinAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(SummonGremlinAction.class.getName());
    private AbstractMonster m;

    public SummonGremlinAction(AbstractMonster[] gremlins) {
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        if (Settings.FAST_MODE) {
            this.startDuration = Settings.ACTION_DUR_FAST;
        } else {
            this.startDuration = Settings.ACTION_DUR_LONG;
        }
        this.duration = this.startDuration;
        int slot = identifySlot(gremlins);
        if (slot == -1) {
            logger.info("INCORRECTLY ATTEMPTED TO CHANNEL GREMLIN.");
            return;
        }
        this.m = getRandomGremlin(slot);
        gremlins[slot] = this.m;
        Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            r.onSpawnMonster(this.m);
        }
    }

    private int identifySlot(AbstractMonster[] gremlins) {
        for (int i = 0; i < gremlins.length; i++) {
            if (gremlins[i] == null || gremlins[i].isDying) {
                return i;
            }
        }
        return -1;
    }

    private AbstractMonster getRandomGremlin(int slot) {
        float y;
        float x;
        ArrayList<String> pool = new ArrayList<>();
        pool.add(GremlinWarrior.ID);
        pool.add(GremlinWarrior.ID);
        pool.add(GremlinThief.ID);
        pool.add(GremlinThief.ID);
        pool.add(GremlinFat.ID);
        pool.add(GremlinFat.ID);
        pool.add(GremlinTsundere.ID);
        pool.add(GremlinWizard.ID);
        switch (slot) {
            case 0:
                x = GremlinLeader.POSX[0];
                y = GremlinLeader.POSY[0];
                break;
            case 1:
                x = GremlinLeader.POSX[1];
                y = GremlinLeader.POSY[1];
                break;
            case 2:
                x = GremlinLeader.POSX[2];
                y = GremlinLeader.POSY[2];
                break;
            default:
                x = GremlinLeader.POSX[0];
                y = GremlinLeader.POSY[0];
                break;
        }
        return MonsterHelper.getGremlin(pool.get(AbstractDungeon.aiRng.random(0, pool.size() - 1)), x, y);
    }

    private int getSmartPosition() {
        int position = 0;
        Iterator<AbstractMonster> it = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (it.hasNext()) {
            AbstractMonster mo = it.next();
            if (this.m.drawX <= mo.drawX) {
                break;
            }
            position++;
        }
        return position;
    }

    @Override // com.megacrit.cardcrawl.actions.AbstractGameAction
    public void update() {
        if (this.duration == this.startDuration) {
            this.m.animX = 1200.0f * Settings.xScale;
            this.m.init();
            this.m.applyPowers();
            AbstractDungeon.getCurrRoom().monsters.addMonster(getSmartPosition(), this.m);
            if (ModHelper.isModEnabled(Lethality.ID)) {
                addToBot(new ApplyPowerAction(this.m, this.m, new StrengthPower(this.m, 3), 3));
            }
            if (ModHelper.isModEnabled(TimeDilation.ID)) {
                addToBot(new ApplyPowerAction(this.m, this.m, new SlowPower(this.m, 0)));
            }
            addToBot(new ApplyPowerAction(this.m, this.m, new MinionPower(this.m)));
        }
        tickDuration();
        if (this.isDone) {
            this.m.animX = 0.0f;
            this.m.showHealthBar();
            this.m.usePreBattleAction();
            return;
        }
        this.m.animX = Interpolation.fade.apply(0.0f, 1200.0f * Settings.xScale, this.duration);
    }
}