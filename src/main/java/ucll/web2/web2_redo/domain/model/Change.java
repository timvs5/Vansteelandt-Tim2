package ucll.web2.web2_redo.domain.model;

public class Change {

    private final Game oldGame;
    private final Game newGame;

    public Change(Game oldGame, Game newGame) {
        this.oldGame = oldGame;
        this.newGame = newGame;
    }

    public Game getOldGame() {
        return this.oldGame;
    }

    public Game getNewGame() {
        return this.newGame;
    }
}
