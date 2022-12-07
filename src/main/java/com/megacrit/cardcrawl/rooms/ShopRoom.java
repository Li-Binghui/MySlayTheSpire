package com.megacrit.cardcrawl.rooms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.shop.Merchant;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/ShopRoom.class */
public class ShopRoom extends AbstractRoom {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ShopRoom");
    public static final String[] TEXT = uiStrings.TEXT;
    public int shopRarityBonus = 6;
    public Merchant merchant = null;

    public ShopRoom() {
        this.phase = AbstractRoom.RoomPhase.COMPLETE;
        this.mapSymbol = "$";
        this.mapImg = ImageMaster.MAP_NODE_MERCHANT;
        this.mapImgOutline = ImageMaster.MAP_NODE_MERCHANT_OUTLINE;
        this.baseRareCardChance = 9;
        this.baseUncommonCardChance = 37;
    }

    public void setMerchant(Merchant merc) {
        this.merchant = merc;
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void onPlayerEntry() {
        if (!AbstractDungeon.id.equals(TheEnding.ID)) {
            playBGM("SHOP");
        }
        AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[0]);
        setMerchant(new Merchant());
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public AbstractCard.CardRarity getCardRarity(int roll) {
        return getCardRarity(roll, false);
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void update() {
        super.update();
        if (this.merchant != null) {
            this.merchant.update();
        }
        updatePurge();
    }

    private void updatePurge() {
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            ShopScreen.purgeCard();
            Iterator<AbstractCard> it = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
            while (it.hasNext()) {
                AbstractCard card = it.next();
                CardCrawlGame.metricData.addPurgedItem(card.getMetricID());
                AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                AbstractDungeon.player.masterDeck.removeCard(card);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.shopScreen.purgeAvailable = false;
        }
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void render(SpriteBatch sb) {
        if (this.merchant != null) {
            this.merchant.render(sb);
        }
        super.render(sb);
        renderTips(sb);
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        super.dispose();
        if (this.merchant != null) {
            this.merchant.dispose();
            this.merchant = null;
        }
    }
}