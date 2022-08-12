package ucll.web2.web2_redo.domain.model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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

    public Game(int ID) {
        this.ID = ID;
        this.name = "Undefined";
        this.genre = "Undefined";
        this.score = 0;
    }


    public void controlAndSetAll(String name, String genre, String score, ArrayList<String> errors) {
        String resetName = getName();
        String resetGenre = getGenre();
        String resetScore = Integer.toString(getScore());

        try {
            setName(name);
        } catch (DomainException I) {
            errors.add(I.getMessage());
        }

        try {
            setGenre(genre);
        } catch (DomainException I) {
            errors.add(I.getMessage());
        }

        try {
            setScore(score);
        } catch (DomainException I) {
            errors.add(I.getMessage());
        }

        if (errors.size() != 0) {
            setName(resetName);
            setGenre(resetGenre);
            setScore(resetScore);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new DomainException("Geen naam ingevuld");
        }
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            throw new DomainException("Geen genre ingevuld");
        }
        this.genre = genre;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setScore(String score) {
        if (score == null || score.isEmpty()) {
            throw new DomainException("Geen score ingevuld");
        }
        try {
            setScore(Integer.parseInt(score));
        } catch (Exception E) {
            throw new DomainException("Score moet geheel getal zijn.");
        }
    }

    //tweede versie is voor JSP omdat eerste hiermee problemen gaf.
    public int getID() {
        return this.ID;
    }
    public int getId() {
        return this.ID;
    }
}
