package com.megacrit.cardcrawl.rooms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/VictoryRoom.class */
public class VictoryRoom extends AbstractRoom {
    public EventType eType;

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/VictoryRoom$EventType.class */
    public enum EventType {
        HEART,
        NONE
    }

    public VictoryRoom(EventType type) {
        this.phase = AbstractRoom.RoomPhase.EVENT;
        this.eType = type;
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void onPlayerEntry() {
        AbstractDungeon.overlayMenu.proceedButton.hide();
        switch (this.eType) {
            case HEART:
                this.event = new SpireHeart();
                this.event.onEnterRoom();
                return;
            case NONE:
            default:
                return;
        }
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void update() {
        super.update();
        if (!AbstractDungeon.isScreenUp) {
            this.event.update();
        }
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void render(SpriteBatch sb) {
        if (this.event != null) {
            this.event.renderRoomEventPanel(sb);
            this.event.render(sb);
        }
        super.render(sb);
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void renderAboveTopPanel(SpriteBatch sb) {
        super.renderAboveTopPanel(sb);
        if (this.event != null) {
            this.event.renderAboveTopPanel(sb);
        }
    }
}