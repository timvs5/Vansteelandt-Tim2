package ucll.web2.web2_redo.domain.db;

import ucll.web2.web2_redo.domain.model.Game;

import java.util.ArrayList;

public class Database {

    private ArrayList<Game> gamesList;
    private int gameID;

    public Database() {
        this.gamesList = new ArrayList<Game>();
        this.gameID = 0;
        addGame(new Game(this.gameID++,"Deus ex", "Cyberpunk-rpg-shooter", 2));
        addGame(new Game(this.gameID++,"FTL", "single ship space RTS", 134));
        addGame(new Game(this.gameID++,"TF2", "class-based shooter", 48));
    }

    public void addGame(Game game) {
        this.gamesList.add(game);
    }

    public void add(String name, String genre, int score) {
        this.addGame(new Game(this.gameID++,name, genre, score));
    }

    public ArrayList<Game> getGamesList() {
        return gamesList;
    }

    //finds the game by name in the list and returns null if game is not found
    public Game getGameByName(String name) {
        for (int i = 0; i < this.gamesList.size(); i++) {
            if (this.gamesList.get(i).getName().equals(name)) {
                return this.gamesList.get(i);
            }
        }
        return null;
    }

    public Game getGameByID(int ID) {
        for (int i = 0; i < this.gamesList.size(); i++) {
            if (this.gamesList.get(i).getID() == ID) {
                return this.gamesList.get(i);
            }
        }
        return null;
    }

    public String getGameScoreByName(String name) {
        try {int score = getGameByName(name).getScore();
            return Integer.toString(score);}
        catch(Exception E) {
            return "Game of score niet gevonden";
        }

    }

    public Game getGameHighestScore() {
        int highest = 0;
        int index = 0;
        if (gamesList.size() == 0) {
            return new Game(-1,"There is no game here", "unexisting", 0);
        }
        for (int i = 0; i < gamesList.size(); i++) {
            if (gamesList.get(i).getScore() > highest) {
                highest = gamesList.get(i).getScore();
                index = i;
            }
        }
        return gamesList.get(index);
    }

    //removes (every instance of) game with the given name
    public void removeGameByName(String name) {
        for (int i = 0; i < this.gamesList.size(); i++) {
            if (this.gamesList.get(i).getName().equals(name)) {
                gamesList.remove(i);
            }
        }
    }

    public void removeGameByID(int ID) {
        for (int i = 0; i < this.gamesList.size(); i++) {
            if (this.gamesList.get(i).getID() == ID) {
                gamesList.remove(i);
            }
        }
    }

    //removes game
    public void removeGame(Game game) {
        for (int i = 0; i < this.gamesList.size(); i++) {
            if (this.gamesList.get(i).equals(game)) {
                gamesList.remove(i);
            }
        }
    }

    public void changeGameByID(int ID, String Name, String Genre, int Score) {
        getGameByID(ID).setName(Name);
        getGameByID(ID).setGenre(Genre);
        getGameByID(ID).setScore(Score);
    }

    public int requestID() {
        return this.gameID++;
    }
}
