package ucll.web2.web2_redo.domain.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Changelog {

    ArrayList<Change> changes;

    public Changelog() {
        this.changes = new ArrayList<>();
    }

    public void AddChange(Game oldGame, Game newGame) {
        Change change = new Change(oldGame, newGame);
        this.changes.add(change);
    }

    public ArrayList<Change> getChanges() {
        return this.changes;
    }

}


