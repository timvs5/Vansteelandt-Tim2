package be.ucll.vansteelandttim.domain.model;

public class Game {

    private int ID;
    private String name;
    private String genre;
    private int score;

    public Game(int ID, String name, String genre, int score) {
        this.ID = ID;
        this.name = name;
        this.genre = genre;
        this.score = score;
    }

    public Game() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {this.genre = genre;}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {this.score = score;}

    //tweede versie is voor JSP omdat eerste hiermee problemen gaf.
    public int getID() {
        return this.ID;
    }
    public int getId() {
        return this.ID;
    }
}
