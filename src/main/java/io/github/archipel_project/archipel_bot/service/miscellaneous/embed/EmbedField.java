package io.github.archipel_project.archipel_bot.service.miscellaneous.embed;

public class EmbedField {
    final String primary;
    final String secondary;
    final boolean align;

    public EmbedField(final String primary, final String secondary, final boolean align) {
        this.primary = primary;
        this.secondary = secondary;
        this.align = align;
    }

    public EmbedField(final String primary, final String secondary) {
        this.primary = primary;
        this.secondary = secondary;
        this.align = false;
    }

    public EmbedField(final String primary, final boolean sameLine) {
        this.primary = primary;
        this.secondary = "";
        this.align = sameLine;
    }

    public String getPrimary() {
        return this.primary;
    }

    public String getSecondary() {
        return this.secondary;
    }

    public boolean isAlign() {
        return this.align;
    }
}
