package com.megacrit.cardcrawl.events.shrines;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.*;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/events/shrines/Designer.class */
public class Designer extends AbstractImageEvent {
    public static final String ID = "Designer";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESC = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private OptionChosen option;
    public static final int GOLD_REQ = 75;
    public static final int UPG_AMT = 2;
    public static final int REMOVE_AMT = 2;
    private int adjustCost;
    private int cleanUpCost;
    private int fullServiceCost;
    private int hpLoss;
    private CurrentScreen curScreen = CurrentScreen.INTRO;
    private boolean adjustmentUpgradesOne = AbstractDungeon.miscRng.randomBoolean();
    private boolean cleanUpRemovesCards = AbstractDungeon.miscRng.randomBoolean();

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/events/shrines/Designer$CurrentScreen.class */
    private enum CurrentScreen {
        INTRO,
        MAIN,
        DONE
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/events/shrines/Designer$OptionChosen.class */
    private enum OptionChosen {
        UPGRADE,
        REMOVE,
        REMOVE_AND_UPGRADE,
        TRANSFORM,
        NONE
    }

    public Designer() {
        super(NAME, DESC[0], "images/events/designer2.jpg");
        this.option = null;
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.option = OptionChosen.NONE;
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.adjustCost = 50;
            this.cleanUpCost = 75;
            this.fullServiceCost = 110;
            this.hpLoss = 5;
            return;
        }
        this.adjustCost = 40;
        this.cleanUpCost = 60;
        this.fullServiceCost = 90;
        this.hpLoss = 3;
    }

    @Override // com.megacrit.cardcrawl.events.AbstractImageEvent, com.megacrit.cardcrawl.events.AbstractEvent
    public void update() {
        super.update();
        if (this.option != OptionChosen.NONE) {
            switch (this.option) {
                case REMOVE:
                    if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        CardCrawlGame.sound.play("CARD_EXHAUST");
                        AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                        logMetricCardRemovalAtCost(ID, "Single Remove", c, this.cleanUpCost);
                        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                        AbstractDungeon.player.masterDeck.removeCard(c);
                        AbstractDungeon.gridSelectScreen.selectedCards.clear();
                        this.option = OptionChosen.NONE;
                        return;
                    }
                    return;
                case REMOVE_AND_UPGRADE:
                    if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        AbstractCard removeCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                        CardCrawlGame.sound.play("CARD_EXHAUST");
                        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(removeCard, ((Settings.WIDTH / 2.0f) - AbstractCard.IMG_WIDTH) - (20.0f * Settings.scale), Settings.HEIGHT / 2.0f));
                        AbstractDungeon.player.masterDeck.removeCard(removeCard);
                        AbstractDungeon.gridSelectScreen.selectedCards.clear();
                        ArrayList<AbstractCard> upgradableCards = new ArrayList<>();
                        Iterator<AbstractCard> it = AbstractDungeon.player.masterDeck.group.iterator();
                        while (it.hasNext()) {
                            AbstractCard c2 = it.next();
                            if (c2.canUpgrade()) {
                                upgradableCards.add(c2);
                            }
                        }
                        Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
                        if (!upgradableCards.isEmpty()) {
                            AbstractCard upgradeCard = upgradableCards.get(0);
                            upgradeCard.upgrade();
                            AbstractDungeon.player.bottledCardUpgradeCheck(upgradeCard);
                            AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(upgradeCard.makeStatEquivalentCopy()));
                            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                            logMetricCardUpgradeAndRemovalAtCost(ID, "Upgrade and Remove", upgradeCard, removeCard, this.fullServiceCost);
                        } else {
                            logMetricCardRemovalAtCost(ID, "Removal", removeCard, this.fullServiceCost);
                        }
                        this.option = OptionChosen.NONE;
                        return;
                    }
                    return;
                case TRANSFORM:
                    if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        List<String> transCards = new ArrayList<>();
                        List<String> obtainedCards = new ArrayList<>();
                        if (AbstractDungeon.gridSelectScreen.selectedCards.size() == 2) {
                            AbstractCard c3 = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                            AbstractDungeon.player.masterDeck.removeCard(c3);
                            transCards.add(c3.cardID);
                            AbstractDungeon.transformCard(c3, false, AbstractDungeon.miscRng);
                            AbstractCard newCard1 = AbstractDungeon.getTransformedCard();
                            obtainedCards.add(newCard1.cardID);
                            AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(newCard1, ((Settings.WIDTH / 2.0f) - (AbstractCard.IMG_WIDTH / 2.0f)) - (20.0f * Settings.scale), Settings.HEIGHT / 2.0f));
                            AbstractCard c4 = AbstractDungeon.gridSelectScreen.selectedCards.get(1);
                            AbstractDungeon.player.masterDeck.removeCard(c4);
                            transCards.add(c4.cardID);
                            AbstractDungeon.transformCard(c4, false, AbstractDungeon.miscRng);
                            AbstractCard newCard2 = AbstractDungeon.getTransformedCard();
                            obtainedCards.add(newCard2.cardID);
                            AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(newCard2, (Settings.WIDTH / 2.0f) + (AbstractCard.IMG_WIDTH / 2.0f) + (20.0f * Settings.scale), Settings.HEIGHT / 2.0f));
                            AbstractDungeon.gridSelectScreen.selectedCards.clear();
                            logMetricTransformCardsAtCost(ID, "Transformed Cards", transCards, obtainedCards, this.cleanUpCost);
                        } else {
                            AbstractCard c5 = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                            AbstractDungeon.player.masterDeck.removeCard(c5);
                            AbstractDungeon.transformCard(c5, false, AbstractDungeon.miscRng);
                            AbstractCard transCard = AbstractDungeon.getTransformedCard();
                            AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(transCard, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                            AbstractDungeon.gridSelectScreen.selectedCards.clear();
                            logMetricTransformCardAtCost(ID, "Transform", transCard, c5, this.cleanUpCost);
                        }
                        this.option = OptionChosen.NONE;
                        return;
                    }
                    return;
                case UPGRADE:
                    if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        logMetricCardUpgradeAtCost(ID, "Upgrade", AbstractDungeon.gridSelectScreen.selectedCards.get(0), this.adjustCost);
                        AbstractDungeon.gridSelectScreen.selectedCards.get(0).upgrade();
                        AbstractDungeon.player.bottledCardUpgradeCheck(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
                        AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeStatEquivalentCopy()));
                        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                        AbstractDungeon.gridSelectScreen.selectedCards.clear();
                        this.option = OptionChosen.NONE;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.megacrit.cardcrawl.events.AbstractEvent
    public void buttonEffect(int buttonPressed) {
        switch (this.curScreen) {
            case INTRO:
                this.imageEventText.updateBodyText(DESC[1]);
                this.imageEventText.removeDialogOption(0);
                if (this.adjustmentUpgradesOne) {
                    this.imageEventText.updateDialogOption(0, OPTIONS[1] + this.adjustCost + OPTIONS[6] + OPTIONS[9], AbstractDungeon.player.gold < this.adjustCost || !AbstractDungeon.player.masterDeck.hasUpgradableCards().booleanValue());
                } else {
                    this.imageEventText.updateDialogOption(0, OPTIONS[1] + this.adjustCost + OPTIONS[6] + OPTIONS[7] + 2 + OPTIONS[8], AbstractDungeon.player.gold < this.adjustCost || !AbstractDungeon.player.masterDeck.hasUpgradableCards().booleanValue());
                }
                if (this.cleanUpRemovesCards) {
                    this.imageEventText.setDialogOption(OPTIONS[2] + this.cleanUpCost + OPTIONS[6] + OPTIONS[10], AbstractDungeon.player.gold < this.cleanUpCost || CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck).size() == 0);
                } else {
                    this.imageEventText.setDialogOption(OPTIONS[2] + this.cleanUpCost + OPTIONS[6] + OPTIONS[11] + 2 + OPTIONS[12], AbstractDungeon.player.gold < this.cleanUpCost || CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck).size() < 2);
                }
                this.imageEventText.setDialogOption(OPTIONS[3] + this.fullServiceCost + OPTIONS[6] + OPTIONS[13], AbstractDungeon.player.gold < this.fullServiceCost || CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck).size() == 0);
                this.imageEventText.setDialogOption(OPTIONS[4] + this.hpLoss + OPTIONS[5]);
                this.curScreen = CurrentScreen.MAIN;
                return;
            case MAIN:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(DESC[2]);
                        AbstractDungeon.player.loseGold(this.adjustCost);
                        if (!this.adjustmentUpgradesOne) {
                            upgradeTwoRandomCards();
                            break;
                        } else {
                            this.option = OptionChosen.UPGRADE;
                            AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getUpgradableCards(), 1, OPTIONS[15], true, false, false, false);
                            break;
                        }
                    case 1:
                        this.imageEventText.updateBodyText(DESC[2]);
                        AbstractDungeon.player.loseGold(this.cleanUpCost);
                        if (!this.cleanUpRemovesCards) {
                            this.option = OptionChosen.TRANSFORM;
                            AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 2, OPTIONS[16], false, false, false, false);
                            break;
                        } else {
                            this.option = OptionChosen.REMOVE;
                            AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[17], false, false, false, true);
                            break;
                        }
                    case 2:
                        this.imageEventText.updateBodyText(DESC[2]);
                        AbstractDungeon.player.loseGold(this.fullServiceCost);
                        this.option = OptionChosen.REMOVE_AND_UPGRADE;
                        AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[17], false, false, false, true);
                        break;
                    case 3:
                        this.imageEventText.loadImage("images/events/designerPunched2.jpg");
                        this.imageEventText.updateBodyText(DESC[3]);
                        logMetricTakeDamage(ID, "Punched", this.hpLoss);
                        CardCrawlGame.sound.play("BLUNT_FAST");
                        AbstractDungeon.player.damage(new DamageInfo(null, this.hpLoss, DamageInfo.DamageType.HP_LOSS));
                        break;
                }
                this.imageEventText.updateDialogOption(0, OPTIONS[14]);
                this.imageEventText.clearRemainingOptions();
                this.curScreen = CurrentScreen.DONE;
                return;
            case DONE:
            default:
                openMap();
                return;
        }
    }

    private void upgradeTwoRandomCards() {
        ArrayList<AbstractCard> upgradableCards = new ArrayList<>();
        Iterator<AbstractCard> it = AbstractDungeon.player.masterDeck.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (c.canUpgrade()) {
                upgradableCards.add(c);
            }
        }
        Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
        if (upgradableCards.isEmpty()) {
            logMetricLoseGold(ID, "Tried to Upgrade", this.adjustCost);
        } else if (upgradableCards.size() == 1) {
            upgradableCards.get(0).upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
            AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(upgradableCards.get(0).makeStatEquivalentCopy()));
            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
            logMetricCardUpgradeAtCost(ID, "Tried to Upgrade", upgradableCards.get(0), this.adjustCost);
        } else {
            List<String> cards = new ArrayList<>();
            cards.add(upgradableCards.get(0).cardID);
            cards.add(upgradableCards.get(1).cardID);
            logMetricUpgradeCardsAtCost(ID, "Upgraded Two", cards, this.adjustCost);
            upgradableCards.get(0).upgrade();
            upgradableCards.get(1).upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
            AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(1));
            AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(upgradableCards.get(0).makeStatEquivalentCopy(), ((Settings.WIDTH / 2.0f) - (AbstractCard.IMG_WIDTH / 2.0f)) - (20.0f * Settings.scale), Settings.HEIGHT / 2.0f));
            AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(upgradableCards.get(1).makeStatEquivalentCopy(), (Settings.WIDTH / 2.0f) + (AbstractCard.IMG_WIDTH / 2.0f) + (20.0f * Settings.scale), Settings.HEIGHT / 2.0f));
            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
        }
    }
}