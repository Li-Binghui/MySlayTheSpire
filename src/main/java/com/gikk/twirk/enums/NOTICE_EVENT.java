package com.gikk.twirk.enums;

import java.util.HashMap;
import java.util.Map;

/* loaded from: desktop-1.0.jar:com/gikk/twirk/enums/NOTICE_EVENT.class */
public enum NOTICE_EVENT {
    ALREADY_BANNED("already_banned"),
    ALREADY_EMOTES_ONLY_OFF("already_emote_only_off"),
    ALREADY_EMOTES_ONLY_ON("already_emote_only_on"),
    ALREADY_R9K_OFF("already_r9k_off"),
    ALREADY_R9K_ON("already_r9k_on"),
    ALREADY_SUBS_OFF("already_subs_off"),
    ALREADY_SUBS_ON("already_subs_on"),
    BAD_HOST_HOSTING("bad_host_hosting"),
    BAD_UNBAN_NO_BAN("bad_unban_no_ban"),
    BAN_SUCCESS("ban_success"),
    EMOTE_ONLY_OFF("emote_only_off"),
    EMOTE_ONLY_ON("emote_only_on"),
    HOST_OFF("host_off"),
    HOST_ON("host_on"),
    HOST_REMAINING("hosts_remaining"),
    MESSAGE_CHANNEL_SUSPENDED("msg_channel_suspended"),
    R9K_OFF("r9k_off"),
    R9K_ON("r9k_on"),
    SLOW_OFF("slow_off"),
    SLOW_ON("slow_on"),
    SUBSCRIBER_MODE_OFF("subs_off"),
    SUBSCRIBER_MODE_ON("subs_on"),
    TIMEOUT_SUCCESS("timeout_success"),
    UNBAN_SUCCESS("unban_success"),
    UNRECOGNIZED_COMMAND("unrecognized_cmd"),
    OTHER("");

    private final String msgID;
    private static final Map<String, NOTICE_EVENT> mapping = new HashMap();

    static {
        NOTICE_EVENT[] values;
        for (NOTICE_EVENT n : values()) {
            mapping.put(n.msgID, n);
        }
    }

    NOTICE_EVENT(String msgID) {
        this.msgID = msgID;
    }

    public static NOTICE_EVENT of(String msgID) {
        return mapping.getOrDefault(msgID, OTHER);
    }
}