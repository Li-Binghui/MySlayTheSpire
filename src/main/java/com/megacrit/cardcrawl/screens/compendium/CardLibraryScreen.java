package com.megacrit.cardcrawl.screens.compendium;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.screens.mainMenu.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/screens/compendium/CardLibraryScreen.class */
public class CardLibraryScreen implements TabBarListener, ScrollBarListener {
    private static float drawStartX;
    private static float padX;
    private static float padY;
    private CardGroup visibleCards;
    private static final Logger logger = LogManager.getLogger(CardLibraryScreen.class.getName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CardLibraryScreen");
    public static final String[] TEXT = uiStrings.TEXT;
    private static float drawStartY = Settings.HEIGHT * 0.66f;
    private static final int CARDS_PER_LINE = (int) (Settings.WIDTH / ((AbstractCard.IMG_WIDTH * 0.75f) + (Settings.CARD_VIEW_PAD_X * 3.0f)));
    private boolean grabbedScreen = false;
    private float grabStartY = 0.0f;
    private float currentDiffY = 0.0f;
    private float scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT;
    private float scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
    private AbstractCard hoveredCard = null;
    private AbstractCard clickStartedCard = null;
    public MenuCancelButton button = new MenuCancelButton();
    private CardGroup redCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private CardGroup greenCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private CardGroup blueCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private CardGroup purpleCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private CardGroup colorlessCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private CardGroup curseCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private CardLibSelectionType type = CardLibSelectionType.NONE;
    private Texture filterSelectionImg = null;
    private int selectionIndex = 0;
    private AbstractCard controllerCard = null;
    private Color highlightBoxColor = new Color(1.0f, 0.95f, 0.5f, 0.0f);
    private ColorTabBar colorBar = new ColorTabBar(this);
    private CardLibSortHeader sortHeader = new CardLibSortHeader(null);
    private ScrollBar scrollBar = new ScrollBar(this);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/screens/compendium/CardLibraryScreen$CardLibSelectionType.class */
    public enum CardLibSelectionType {
        NONE,
        FILTERS,
        CARDS
    }

    public CardLibraryScreen() {
        drawStartX = Settings.WIDTH;
        drawStartX -= (CARDS_PER_LINE * AbstractCard.IMG_WIDTH) * 0.75f;
        drawStartX -= (CARDS_PER_LINE - 1) * Settings.CARD_VIEW_PAD_X;
        drawStartX /= 2.0f;
        drawStartX += (AbstractCard.IMG_WIDTH * 0.75f) / 2.0f;
        padX = (AbstractCard.IMG_WIDTH * 0.75f) + Settings.CARD_VIEW_PAD_X;
        padY = (AbstractCard.IMG_HEIGHT * 0.75f) + Settings.CARD_VIEW_PAD_Y;
    }

    public void initialize() {
        logger.info("Initializing card library screen.");
        this.redCards.group = CardLibrary.getCardList(CardLibrary.LibraryType.RED);
        this.greenCards.group = CardLibrary.getCardList(CardLibrary.LibraryType.GREEN);
        this.blueCards.group = CardLibrary.getCardList(CardLibrary.LibraryType.BLUE);
        this.purpleCards.group = CardLibrary.getCardList(CardLibrary.LibraryType.PURPLE);
        this.colorlessCards.group = CardLibrary.getCardList(CardLibrary.LibraryType.COLORLESS);
        this.curseCards.group = CardLibrary.getCardList(CardLibrary.LibraryType.CURSE);
        this.visibleCards = this.redCards;
        this.sortHeader.setGroup(this.visibleCards);
        calculateScrollBounds();
    }

    private void setLockStatus() {
        lockStatusHelper(this.redCards);
        lockStatusHelper(this.greenCards);
        lockStatusHelper(this.blueCards);
        lockStatusHelper(this.purpleCards);
        lockStatusHelper(this.colorlessCards);
        lockStatusHelper(this.curseCards);
    }

    private void lockStatusHelper(CardGroup group) {
        ArrayList<AbstractCard> toAdd = new ArrayList<>();
        Iterator<AbstractCard> i = group.group.iterator();
        while (i.hasNext()) {
            AbstractCard c = i.next();
            if (UnlockTracker.isCardLocked(c.cardID)) {
                AbstractCard tmp = CardLibrary.getCopy(c.cardID);
                tmp.setLocked();
                toAdd.add(tmp);
                i.remove();
            }
        }
        group.group.addAll(toAdd);
    }

    public void open() {
        this.controllerCard = null;
        if (Settings.isInfo) {
            CardLibrary.unlockAndSeeAllCards();
        }
        if (this.filterSelectionImg == null) {
            this.filterSelectionImg = ImageMaster.loadImage("images/ui/cardlibrary/selectBox.png");
        }
        setLockStatus();
        sortOnOpen();
        this.button.show(TEXT[0]);
        this.currentDiffY = this.scrollLowerBound;
        SingleCardViewPopup.isViewingUpgrade = false;
        CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.CARD_LIBRARY;
    }

    private void sortOnOpen() {
        this.sortHeader.justSorted = true;
        this.visibleCards.sortAlphabetically(true);
        this.visibleCards.sortByRarity(true);
        this.visibleCards.sortByStatus(true);
        Iterator<AbstractCard> it = this.visibleCards.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            c.drawScale = MathUtils.random(0.6f, 0.65f);
            c.targetDrawScale = 0.75f;
        }
    }

    public void update() {
        updateControllerInput();
        if (Settings.isControllerMode && this.controllerCard != null && !CardCrawlGame.isPopupOpen) {
            if (Gdx.input.getY() > Settings.HEIGHT * 0.75f) {
                this.currentDiffY += Settings.SCROLL_SPEED;
            } else if (Gdx.input.getY() < Settings.HEIGHT * 0.25f) {
                this.currentDiffY -= Settings.SCROLL_SPEED;
            }
        }
        this.colorBar.update(this.visibleCards.getBottomCard().current_y + (230.0f * Settings.yScale));
        this.sortHeader.update();
        if (this.hoveredCard != null) {
            CardCrawlGame.cursor.changeType(GameCursor.CursorType.INSPECT);
            if (InputHelper.justClickedLeft) {
                this.clickStartedCard = this.hoveredCard;
            }
            if (!(!InputHelper.justReleasedClickLeft || this.clickStartedCard == null || this.hoveredCard == null) || (this.hoveredCard != null && CInputActionSet.select.isJustPressed())) {
                if (Settings.isControllerMode) {
                    this.clickStartedCard = this.hoveredCard;
                }
                InputHelper.justReleasedClickLeft = false;
                CardCrawlGame.cardPopup.open(this.clickStartedCard, this.visibleCards);
                this.clickStartedCard = null;
            }
        } else {
            this.clickStartedCard = null;
        }
        boolean isScrollBarScrolling = this.scrollBar.update();
        if (!CardCrawlGame.cardPopup.isOpen && !isScrollBarScrolling) {
            updateScrolling();
        }
        updateCards();
        this.button.update();
        if (this.button.hb.clicked || InputHelper.pressedEscape) {
            InputHelper.pressedEscape = false;
            this.button.hb.clicked = false;
            this.button.hide();
            CardCrawlGame.mainMenuScreen.panelScreen.refresh();
        }
        if (Settings.isControllerMode && this.controllerCard != null) {
            CInputHelper.setCursor(this.controllerCard.hb);
        }
    }

    private void updateControllerInput() {
        if (Settings.isControllerMode) {
            this.selectionIndex = 0;
            boolean anyHovered = false;
            this.type = CardLibSelectionType.NONE;
            if (!this.colorBar.viewUpgradeHb.hovered) {
                if (this.sortHeader.updateControllerInput() == null) {
                    Iterator<AbstractCard> it = this.visibleCards.group.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        AbstractCard c = it.next();
                        if (c.hb.hovered) {
                            anyHovered = true;
                            this.type = CardLibSelectionType.CARDS;
                            break;
                        }
                        this.selectionIndex++;
                    }
                } else {
                    anyHovered = true;
                    this.controllerCard = null;
                    this.type = CardLibSelectionType.FILTERS;
                    this.selectionIndex = this.sortHeader.getHoveredIndex();
                }
            } else {
                anyHovered = true;
                this.type = CardLibSelectionType.FILTERS;
                this.selectionIndex = 4;
                this.controllerCard = null;
            }
            if (!anyHovered) {
                CInputHelper.setCursor(this.visibleCards.group.get(0).hb);
                return;
            }
            switch (this.type) {
                case CARDS:
                    if ((!CInputActionSet.up.isJustPressed() && !CInputActionSet.altUp.isJustPressed()) || this.visibleCards.size() <= CARDS_PER_LINE) {
                        if ((!CInputActionSet.down.isJustPressed() && !CInputActionSet.altDown.isJustPressed()) || this.visibleCards.size() <= CARDS_PER_LINE) {
                            if (!CInputActionSet.left.isJustPressed() && !CInputActionSet.altLeft.isJustPressed()) {
                                if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
                                    if (this.selectionIndex % CARDS_PER_LINE < CARDS_PER_LINE - 1) {
                                        this.selectionIndex++;
                                        if (this.selectionIndex > this.visibleCards.size() - 1) {
                                            this.selectionIndex -= this.visibleCards.size() % CARDS_PER_LINE;
                                        }
                                    } else {
                                        this.selectionIndex -= CARDS_PER_LINE - 1;
                                        if (this.selectionIndex < 0) {
                                            this.selectionIndex = 0;
                                        }
                                    }
                                    CInputHelper.setCursor(this.visibleCards.group.get(this.selectionIndex).hb);
                                    this.controllerCard = this.visibleCards.group.get(this.selectionIndex);
                                    break;
                                }
                            } else {
                                if (this.selectionIndex % CARDS_PER_LINE > 0) {
                                    this.selectionIndex--;
                                } else {
                                    this.selectionIndex += CARDS_PER_LINE - 1;
                                    if (this.selectionIndex > this.visibleCards.size() - 1) {
                                        this.selectionIndex = this.visibleCards.size() - 1;
                                    }
                                }
                                CInputHelper.setCursor(this.visibleCards.group.get(this.selectionIndex).hb);
                                this.controllerCard = this.visibleCards.group.get(this.selectionIndex);
                                break;
                            }
                        } else {
                            if (this.selectionIndex < this.visibleCards.size() - CARDS_PER_LINE) {
                                this.selectionIndex += CARDS_PER_LINE;
                            } else {
                                this.selectionIndex %= CARDS_PER_LINE;
                            }
                            CInputHelper.setCursor(this.visibleCards.group.get(this.selectionIndex).hb);
                            this.controllerCard = this.visibleCards.group.get(this.selectionIndex);
                            break;
                        }
                    } else if (this.selectionIndex >= CARDS_PER_LINE) {
                        this.selectionIndex -= 5;
                        CInputHelper.setCursor(this.visibleCards.group.get(this.selectionIndex).hb);
                        this.controllerCard = this.visibleCards.group.get(this.selectionIndex);
                        break;
                    } else {
                        CInputHelper.setCursor(this.sortHeader.buttons[0].hb);
                        this.controllerCard = null;
                        return;
                    }
                    break;
                case FILTERS:
                    if (!CInputActionSet.down.isJustPressed() && !CInputActionSet.altDown.isJustPressed()) {
                        if (!CInputActionSet.right.isJustPressed() && !CInputActionSet.altRight.isJustPressed()) {
                            if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
                                this.selectionIndex--;
                                if (this.selectionIndex != -1) {
                                    if (this.selectionIndex > this.sortHeader.buttons.length - 1) {
                                        this.selectionIndex = this.sortHeader.buttons.length - 1;
                                    }
                                    CInputHelper.setCursor(this.sortHeader.buttons[this.selectionIndex].hb);
                                    break;
                                } else {
                                    CInputHelper.setCursor(this.colorBar.viewUpgradeHb);
                                    break;
                                }
                            }
                        } else {
                            this.selectionIndex++;
                            if (this.selectionIndex != this.sortHeader.buttons.length) {
                                if (this.selectionIndex > this.sortHeader.buttons.length) {
                                    this.selectionIndex = 0;
                                }
                                CInputHelper.setCursor(this.sortHeader.buttons[this.selectionIndex].hb);
                                break;
                            } else {
                                CInputHelper.setCursor(this.colorBar.viewUpgradeHb);
                                break;
                            }
                        }
                    } else {
                        CInputHelper.setCursor(this.visibleCards.group.get(0).hb);
                        break;
                    }
                    break;
            }
            if (this.type == CardLibSelectionType.FILTERS) {
                this.sortHeader.selectionIndex = this.selectionIndex;
            } else {
                this.sortHeader.selectionIndex = -1;
            }
        }
    }

    private void updateCards() {
        this.hoveredCard = null;
        int lineNum = 0;
        ArrayList<AbstractCard> cards = this.visibleCards.group;
        for (int i = 0; i < cards.size(); i++) {
            int mod = i % CARDS_PER_LINE;
            if (mod == 0 && i != 0) {
                lineNum++;
            }
            cards.get(i).target_x = drawStartX + (mod * padX);
            cards.get(i).target_y = (drawStartY + this.currentDiffY) - (lineNum * padY);
            cards.get(i).update();
            cards.get(i).updateHoverLogic();
            if (cards.get(i).hb.hovered) {
                this.hoveredCard = cards.get(i);
            }
        }
        if (this.sortHeader.justSorted) {
            Iterator<AbstractCard> it = cards.iterator();
            while (it.hasNext()) {
                AbstractCard c = it.next();
                c.current_x = c.target_x;
                c.current_y = c.target_y;
            }
            this.sortHeader.justSorted = false;
        }
    }

    private void updateScrolling() {
        int y = InputHelper.mY;
        if (!this.grabbedScreen) {
            if (InputHelper.scrolledDown) {
                this.currentDiffY += Settings.SCROLL_SPEED;
            } else if (InputHelper.scrolledUp) {
                this.currentDiffY -= Settings.SCROLL_SPEED;
            }
            if (InputHelper.justClickedLeft) {
                this.grabbedScreen = true;
                this.grabStartY = y - this.currentDiffY;
            }
        } else if (InputHelper.isMouseDown) {
            this.currentDiffY = y - this.grabStartY;
        } else {
            this.grabbedScreen = false;
        }
        resetScrolling();
        updateBarPosition();
    }

    private void calculateScrollBounds() {
        int size = this.visibleCards.size();
        if (size > CARDS_PER_LINE * 2) {
            int scrollTmp = (size / CARDS_PER_LINE) - 2;
            if (size % CARDS_PER_LINE != 0) {
                scrollTmp++;
            }
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + (scrollTmp * padY);
            return;
        }
        this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
    }

    private void resetScrolling() {
        if (this.currentDiffY < this.scrollLowerBound) {
            this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollLowerBound);
        } else if (this.currentDiffY > this.scrollUpperBound) {
            this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollUpperBound);
        }
    }

    public void render(SpriteBatch sb) {
        this.scrollBar.render(sb);
        this.colorBar.render(sb, this.visibleCards.getBottomCard().current_y + (230.0f * Settings.yScale));
        this.sortHeader.render(sb);
        renderGroup(sb, this.visibleCards);
        if (this.hoveredCard != null) {
            this.hoveredCard.renderHoverShadow(sb);
            this.hoveredCard.renderInLibrary(sb);
        }
        this.button.render(sb);
        if (Settings.isControllerMode) {
            renderControllerUi(sb);
        }
    }

    private void renderControllerUi(SpriteBatch sb) {
        sb.draw(CInputActionSet.pageLeftViewDeck.getKeyImg(), (280.0f * Settings.xScale) - 32.0f, (this.sortHeader.group.getBottomCard().current_y + (280.0f * Settings.yScale)) - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
        sb.draw(CInputActionSet.pageRightViewExhaust.getKeyImg(), (1640.0f * Settings.xScale) - 32.0f, (this.sortHeader.group.getBottomCard().current_y + (280.0f * Settings.yScale)) - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
        if (this.type != CardLibSelectionType.FILTERS) {
            return;
        }
        if (this.selectionIndex == 4 || (this.selectionIndex == 3 && Settings.removeAtoZSort)) {
            this.highlightBoxColor.a = 0.7f + (MathUtils.cosDeg((float) ((System.currentTimeMillis() / 2) % 360)) / 5.0f);
            sb.setColor(this.highlightBoxColor);
            float doop = 1.0f + ((1.0f + MathUtils.cosDeg((float) ((System.currentTimeMillis() / 2) % 360))) / 50.0f);
            sb.draw(this.filterSelectionImg, this.colorBar.viewUpgradeHb.cX - 100.0f, this.colorBar.viewUpgradeHb.cY - 43.0f, 100.0f, 43.0f, 200.0f, 86.0f, Settings.scale * doop * ((this.colorBar.viewUpgradeHb.width / 150.0f) / Settings.scale), Settings.scale * doop, 0.0f, 0, 0, 200, 86, false, false);
        }
    }

    private void renderGroup(SpriteBatch sb, CardGroup group) {
        group.renderInLibrary(sb);
        group.renderTip(sb);
    }

    @Override // com.megacrit.cardcrawl.screens.mainMenu.TabBarListener
    public void didChangeTab(ColorTabBar tabBar, ColorTabBar.CurrentTab newSelection) {
        CardGroup oldSelection = this.visibleCards;
        switch (newSelection) {
            case RED:
                this.visibleCards = this.redCards;
                break;
            case GREEN:
                this.visibleCards = this.greenCards;
                break;
            case BLUE:
                this.visibleCards = this.blueCards;
                break;
            case PURPLE:
                this.visibleCards = this.purpleCards;
                break;
            case COLORLESS:
                this.visibleCards = this.colorlessCards;
                break;
            case CURSE:
                this.visibleCards = this.curseCards;
                break;
        }
        if (oldSelection != this.visibleCards) {
            this.sortHeader.setGroup(this.visibleCards);
            calculateScrollBounds();
        }
        this.sortHeader.justSorted = true;
        Iterator<AbstractCard> it = this.visibleCards.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            c.drawScale = MathUtils.random(0.6f, 0.65f);
            c.targetDrawScale = 0.75f;
        }
    }

    @Override // com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener
    public void scrolledUsingBar(float newPercent) {
        this.currentDiffY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
        updateBarPosition();
    }

    private void updateBarPosition() {
        float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.currentDiffY);
        this.scrollBar.parentScrolledToPercent(percent);
    }
}