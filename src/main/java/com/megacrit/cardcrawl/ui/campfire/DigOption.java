package com.megacrit.cardcrawl.ui.campfire;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.campfire.CampfireDigEffect;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/ui/campfire/DigOption.class */
public class DigOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Dig Option");
    public static final String[] TEXT = uiStrings.TEXT;

    public DigOption() {
        this.label = TEXT[0];
        this.description = TEXT[1];
        this.img = ImageMaster.CAMPFIRE_DIG_BUTTON;
    }

    @Override // com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption
    public void useOption() {
        AbstractDungeon.effectList.add(new CampfireDigEffect());
    }
}
