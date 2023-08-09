package io.github.archipel_project.archipel_bot.miscellaneous;

public enum Emoji {
    BLANK("U+2800"),
    CRY_FACE("U+1F622"),
    CRYSTAL_BALL("U+1F52E"),
    FIRE("U+1F525"),
    RED_CIRCLE("U+1F534"),
    BROWN_CIRLCE("U+1F7E4"),
    GREEN_CIRCLE("U+1F7E2"),
    PURPLE_CIRCLE("U+1F7E3"),
    BLUE_CIRCLE("U+1F535"),
    YELLOW_CIRCLE("U+1F7E1"),
    BOOM("U+1F4A5"),
    STAR_FACE("U+1F929"),
    STAR("U+1F31F"),
    SPARKLES("U+2728"),
    ROCKET("U+1F680"),
    MEMO("U+1F4DD"),
    CROSS("U+274C"),
    RED_SQUARE("U+1F7E5"),
    GREEN_SQUARE("U+1F7E9"),
    TADA("U+1F389"),
    ZZZ("U+1F4A4"),
    WARNING("U+26A0"),
    BELLHOP("U+1F6CE"),
    WRENCH("U+1F527"),
    GEAR("U+2699"),
    CONSTRUCTION("U+1F3D7"),
    NEWSPAPER("U+1F4F0"),
    SMOKING("U+1F6AC"),
    SATELLITE("U+1F6F0"),
    PHONE("U+1F4F1"),
    FLOPPY("U+1F4BE"),
    TOOLS("U+1F6E0"),
    EYES("U+1F440"),
    MAG_RIGHT("U+1F50E"),
    BOOKS("U+1F4DA"),
    ADMISSION_TICKET("U+1F39F"),
    ROUND_PUSHPIN("U+1F4CD"),
    PUSHPIN("U+1F4CC"),
    SHOPPING_CART("U+1F6D2"),
    BOOKMARK_TABS("U+1F4D1"),
    LINK("U+1F517"),
    LABEL("U+1F3F7"),
    FILE_FOLDER("U+1F4C1"),
    PEN("U+1F58B"),
    THOUGHT_BALLOON("U+1F4AD");

    private final net.dv8tion.jda.api.entities.emoji.Emoji emoji;
    private final String unicode;

    Emoji(final String unicode) {
        this.unicode = unicode;
        this.emoji = net.dv8tion.jda.api.entities.emoji.Emoji.fromFormatted(unicode);
    }

    public net.dv8tion.jda.api.entities.emoji.Emoji getEmoji() {
        return this.emoji;
    }

    public String get() {
        return this.emoji.getName();
    }

    public String getDisplay() {
        return this.getDisplay(true);
    }

    public String getDisplay(final boolean withSpace) {
        return this.get() + (withSpace ? Emoji.BLANK.get() : "");
    }

    public String getUnicode() {
        return this.unicode;
    }
}

