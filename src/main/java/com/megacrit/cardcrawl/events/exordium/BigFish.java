package com.megacrit.cardcrawl.events.exordium;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/events/exordium/BigFish.class */
public class BigFish extends AbstractImageEvent {
    public static final String ID = "Big Fish";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String DIALOG_1 = DESCRIPTIONS[0];
    private static final String BANANA_RESULT = DESCRIPTIONS[1];
    private static final String DONUT_RESULT = DESCRIPTIONS[2];
    private static final String BOX_RESULT = DESCRIPTIONS[4];
    private static final String BOX_BAD = DESCRIPTIONS[5];
    private int healAmt;
    private static final int MAX_HP_AMT = 5;
    private CurScreen screen;

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/events/exordium/BigFish$CurScreen.class */
    private enum CurScreen {
        INTRO,
        RESULT
    }

    public BigFish() {
        super(NAME, DIALOG_1, "images/events/fishing.jpg");
        this.healAmt = 0;
        this.screen = CurScreen.INTRO;
        this.healAmt = AbstractDungeon.player.maxHealth / 3;
        this.imageEventText.setDialogOption(OPTIONS[0] + this.healAmt + OPTIONS[1]);
        this.imageEventText.setDialogOption(OPTIONS[2] + 5 + OPTIONS[3]);
        this.imageEventText.setDialogOption(OPTIONS[4], CardLibrary.getCopy(Regret.ID));
    }

    @Override // com.megacrit.cardcrawl.events.AbstractEvent
    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        AbstractDungeon.player.heal(this.healAmt, true);
                        this.imageEventText.updateBodyText(BANANA_RESULT);
                        AbstractEvent.logMetricHeal(ID, "Banana", this.healAmt);
                        break;
                    case 1:
                        AbstractDungeon.player.increaseMaxHp(5, true);
                        this.imageEventText.updateBodyText(DONUT_RESULT);
                        AbstractEvent.logMetricMaxHPGain(ID, "Donut", 5);
                        break;
                    default:
                        this.imageEventText.updateBodyText(BOX_RESULT + BOX_BAD);
                        AbstractCard c = new Regret();
                        AbstractRelic r = AbstractDungeon.returnRandomScreenlessRelic(AbstractDungeon.returnRandomRelicTier());
                        AbstractEvent.logMetricObtainCardAndRelic(ID, "Box", c, r);
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(CardLibrary.getCopy(c.cardID), Settings.WIDTH / 2, Settings.HEIGHT / 2));
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, r);
                        break;
                }
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[5]);
                this.screen = CurScreen.RESULT;
                break;
            default:
                openMap();
                break;
        }
    }

    public void logMetric(String actionTaken) {
        AbstractEvent.logMetric(ID, actionTaken);
    }
}
