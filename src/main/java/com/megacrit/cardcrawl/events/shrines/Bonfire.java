package com.megacrit.cardcrawl.events.shrines;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.relics.SpiritPoop;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/events/shrines/Bonfire.class */
public class Bonfire extends AbstractImageEvent {
    public static final String ID = "Bonfire Elementals";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String DIALOG_1 = DESCRIPTIONS[0];
    private static final String DIALOG_2 = DESCRIPTIONS[1];
    private static final String DIALOG_3 = DESCRIPTIONS[2];
    private CUR_SCREEN screen = CUR_SCREEN.INTRO;
    private AbstractCard offeredCard = null;
    private boolean cardSelect = false;

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/events/shrines/Bonfire$CUR_SCREEN.class */
    private enum CUR_SCREEN {
        INTRO,
        CHOOSE,
        COMPLETE
    }

    public Bonfire() {
        super(NAME, DIALOG_1, "images/events/bonfire.jpg");
        this.imageEventText.setDialogOption(OPTIONS[0]);
    }

    @Override // com.megacrit.cardcrawl.events.AbstractEvent
    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_GOOP");
        }
    }

    @Override // com.megacrit.cardcrawl.events.AbstractImageEvent, com.megacrit.cardcrawl.events.AbstractEvent
    public void update() {
        super.update();
        if (this.cardSelect && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            this.offeredCard = AbstractDungeon.gridSelectScreen.selectedCards.remove(0);
            switch (this.offeredCard.rarity) {
                case CURSE:
                    logMetricRemoveCardAndObtainRelic(ID, "Offered Curse", this.offeredCard, new SpiritPoop());
                    break;
                case BASIC:
                    logMetricCardRemoval(ID, "Offered Basic", this.offeredCard);
                    break;
                case COMMON:
                    logMetricCardRemovalAndHeal(ID, "Offered Common", this.offeredCard, 5);
                case SPECIAL:
                    logMetricCardRemovalAndHeal(ID, "Offered Special", this.offeredCard, 5);
                    break;
                case UNCOMMON:
                    int heal = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
                    logMetricCardRemovalAndHeal(ID, "Offered Uncommon", this.offeredCard, heal);
                    break;
                case RARE:
                    int heal2 = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
                    logMetricCardRemovalHealMaxHPUp(ID, "Offered Rare", this.offeredCard, heal2, 10);
                    break;
            }
            setReward(this.offeredCard.rarity);
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(this.offeredCard, Settings.WIDTH / 2, Settings.HEIGHT / 2));
            AbstractDungeon.player.masterDeck.removeCard(this.offeredCard);
            this.imageEventText.updateDialogOption(0, OPTIONS[1]);
            this.screen = CUR_SCREEN.COMPLETE;
            this.cardSelect = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.megacrit.cardcrawl.events.AbstractEvent
    public void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                this.imageEventText.updateBodyText(DIALOG_2);
                this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                this.screen = CUR_SCREEN.CHOOSE;
                return;
            case CHOOSE:
                if (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).size() > 0) {
                    AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[3], false, false, false, true);
                    this.cardSelect = true;
                    return;
                }
                this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                this.screen = CUR_SCREEN.COMPLETE;
                return;
            case COMPLETE:
                openMap();
                return;
            default:
                return;
        }
    }

    private void setReward(AbstractCard.CardRarity rarity) {
        String dialog = DIALOG_3;
        switch (rarity) {
            case CURSE:
                dialog = dialog + DESCRIPTIONS[3];
                if (AbstractDungeon.player.hasRelic(SpiritPoop.ID)) {
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, new Circlet());
                    break;
                } else {
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, RelicLibrary.getRelic(SpiritPoop.ID).makeCopy());
                    break;
                }
            case BASIC:
                dialog = dialog + DESCRIPTIONS[4];
                break;
            case COMMON:
            case SPECIAL:
                dialog = dialog + DESCRIPTIONS[5];
                AbstractDungeon.player.heal(5);
                break;
            case UNCOMMON:
                dialog = dialog + DESCRIPTIONS[6];
                AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
                break;
            case RARE:
                dialog = dialog + DESCRIPTIONS[7];
                AbstractDungeon.player.increaseMaxHp(10, false);
                AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
                break;
        }
        this.imageEventText.updateBodyText(dialog);
    }
}