package com.megacrit.cardcrawl.events.exordium;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/events/exordium/GoldenWing.class */
public class GoldenWing extends AbstractImageEvent {
    private int damage;
    private boolean canAttack;
    private boolean purgeResult;
    private static final int MIN_GOLD = 50;
    private static final int MAX_GOLD = 80;
    private static final int REQUIRED_DAMAGE = 10;
    private int goldAmount;
    private CUR_SCREEN screen;
    public static final String ID = "Golden Wing";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO = DESCRIPTIONS[0];
    private static final String AGREE_DIALOG = DESCRIPTIONS[1];
    private static final String SPECIAL_OPTION = DESCRIPTIONS[2];
    private static final String DISAGREE_DIALOG = DESCRIPTIONS[3];

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/events/exordium/GoldenWing$CUR_SCREEN.class */
    private enum CUR_SCREEN {
        INTRO,
        PURGE,
        MAP
    }

    public GoldenWing() {
        super(NAME, INTRO, "images/events/goldenWing.jpg");
        this.damage = 7;
        this.purgeResult = false;
        this.screen = CUR_SCREEN.INTRO;
        this.canAttack = CardHelper.hasCardWithXDamage(10);
        this.imageEventText.setDialogOption(OPTIONS[0] + this.damage + OPTIONS[1]);
        if (this.canAttack) {
            this.imageEventText.setDialogOption(OPTIONS[2] + 50 + OPTIONS[3] + 80 + OPTIONS[4]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[5] + 10 + OPTIONS[6], !this.canAttack);
        }
        this.imageEventText.setDialogOption(OPTIONS[7]);
    }

    @Override // com.megacrit.cardcrawl.events.AbstractImageEvent, com.megacrit.cardcrawl.events.AbstractEvent
    public void update() {
        super.update();
        purgeLogic();
        if (this.waitForInput) {
            buttonEffect(GenericEventDialog.getSelectedOption());
        }
    }

    @Override // com.megacrit.cardcrawl.events.AbstractEvent
    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(AGREE_DIALOG);
                        AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, this.damage));
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractGameAction.AttackEffect.FIRE));
                        this.screen = CUR_SCREEN.PURGE;
                        this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                        this.imageEventText.removeDialogOption(1);
                        this.imageEventText.removeDialogOption(1);
                        break;
                    case 1:
                        if (this.canAttack) {
                            this.goldAmount = AbstractDungeon.miscRng.random(50, 80);
                            AbstractDungeon.effectList.add(new RainingGoldEffect(this.goldAmount));
                            AbstractDungeon.player.gainGold(this.goldAmount);
                            AbstractEvent.logMetricGainGold(ID, "Gained Gold", this.goldAmount);
                            this.imageEventText.updateBodyText(SPECIAL_OPTION);
                            this.screen = CUR_SCREEN.MAP;
                            this.imageEventText.updateDialogOption(0, OPTIONS[7]);
                            this.imageEventText.removeDialogOption(1);
                            this.imageEventText.removeDialogOption(1);
                            break;
                        }
                        break;
                    default:
                        this.imageEventText.updateBodyText(DISAGREE_DIALOG);
                        AbstractEvent.logMetricIgnored(ID);
                        this.screen = CUR_SCREEN.MAP;
                        this.imageEventText.updateDialogOption(0, OPTIONS[7]);
                        this.imageEventText.removeDialogOption(1);
                        this.imageEventText.removeDialogOption(1);
                        break;
                }
            case PURGE:
                AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[9], false, false, false, true);
                this.imageEventText.updateDialogOption(0, OPTIONS[7]);
                this.purgeResult = true;
                this.screen = CUR_SCREEN.MAP;
                break;
            case MAP:
                openMap();
                break;
            default:
                openMap();
                break;
        }
    }

    private void purgeLogic() {
        if (this.purgeResult && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c, Settings.WIDTH / 2, Settings.HEIGHT / 2));
            AbstractEvent.logMetricCardRemovalAndDamage(ID, "Card Removal", c, this.damage);
            AbstractDungeon.player.masterDeck.removeCard(c);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.purgeResult = false;
        }
    }
}
