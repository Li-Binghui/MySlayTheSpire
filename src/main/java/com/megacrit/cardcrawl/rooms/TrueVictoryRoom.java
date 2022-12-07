package com.megacrit.cardcrawl.rooms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.cutscenes.Cutscene;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/TrueVictoryRoom.class */
public class TrueVictoryRoom extends AbstractRoom {
    public Cutscene cutscene = new Cutscene(AbstractDungeon.player.chosenClass);

    public TrueVictoryRoom() {
        this.phase = AbstractRoom.RoomPhase.INCOMPLETE;
        AbstractDungeon.overlayMenu.proceedButton.hideInstantly();
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void onPlayerEntry() {
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        GameCursor.hidden = true;
        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.NO_INTERACT;
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void update() {
        super.update();
        this.cutscene.update();
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void render(SpriteBatch sb) {
        super.render(sb);
        this.cutscene.render(sb);
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void renderAboveTopPanel(SpriteBatch sb) {
        super.renderAboveTopPanel(sb);
        this.cutscene.renderAbove(sb);
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        super.dispose();
        this.cutscene.dispose();
    }
}