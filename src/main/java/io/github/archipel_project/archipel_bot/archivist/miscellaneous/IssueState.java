package io.github.archipel_project.archipel_bot.archivist.miscellaneous;

public enum IssueState {

    OPEN("Open", Emoji.GREEN_CIRCLE),
    CLOSED_COMPLETED("Closed - Completed", Emoji.PURPLE_CIRCLE),
    CLOSED_NOT_PLANNED("Closed - Not planned", Emoji.BROWN_CIRLCE);

    private final String state;
    private final Emoji emoji;

    IssueState(final String state, final Emoji emoji) {
        this.state = state;
        this.emoji = emoji;
    }

    public static IssueState fromInputs(final String state, final String reason) {
        if (state.equals("open")) {
            return IssueState.OPEN;
        } else {
            if(reason != null && reason.equals("not_planned"))
                return IssueState.CLOSED_NOT_PLANNED;

            return IssueState.CLOSED_COMPLETED;
        }
    }

    public String getState() {
        return this.state;
    }

    public Emoji getEmoji() {
        return this.emoji;
    }

    public boolean isClosed() {
        return this.state.toLowerCase().contains("closed");
    }
}
