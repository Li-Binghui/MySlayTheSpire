package com.megacrit.cardcrawl.blights;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.BlightStrings;

public class MimicInfestation extends AbstractBlight {
    public static final String ID = "MimicInfestation";
    private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("MimicInfestation");
    public static final String NAME = blightStrings.NAME;
    public static final String[] DESC = blightStrings.DESCRIPTION;

    public MimicInfestation() {
        super("MimicInfestation", NAME, DESC[0], "mimic.png", true);
    }
}
