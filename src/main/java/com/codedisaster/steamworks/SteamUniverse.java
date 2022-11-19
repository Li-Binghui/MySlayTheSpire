package com.codedisaster.steamworks;

/* loaded from: desktop-1.0.jar:com/codedisaster/steamworks/SteamUniverse.class */
public enum SteamUniverse {
    Invalid(0),
    Public(1),
    Beta(2),
    Internal(3),
    Dev(4);

    private final int value;
    private static final SteamUniverse[] values = values();

    SteamUniverse(int value) {
        this.value = value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SteamUniverse byValue(int value) {
        SteamUniverse[] steamUniverseArr;
        for (SteamUniverse type : values) {
            if (type.value == value) {
                return type;
            }
        }
        return Invalid;
    }
}