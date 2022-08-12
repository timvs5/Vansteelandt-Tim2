package ucll.web2.web2_redo.ui.controller;

import ucll.web2.web2_redo.domain.db.Database;
import ucll.web2.web2_redo.domain.model.DomainException;
import ucll.web2.web2_redo.domain.model.Fiets;
import ucll.web2.web2_redo.domain.model.Game;
import ucll.web2.web2_redo.domain.model.Changelog;
import ucll.web2.web2_redo.domain.db.FietsenDB;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.lang.*;
import java.util.List;

@WebServlet("/servlet")
public class Controller extends HttpServlet {
    private String message;
    Database DB;
    FietsenDB fietsendb;

    public void init() {
        DB = new Database();
        fietsendb = new FietsenDB();
        message = "Hello World!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String action = request.getParameter("action");
            String destination = "none";
            ArrayList<Game> gamesList;
            Game g;
            Fiets f;

            switch (action) {
                case "getGameScore":
                    String param = request.getParameter("getGameScore");
                    String resultScore = DB.getGameScoreByName(param);
                    request.setAttribute("result", resultScore);
                    destination = "/result.jsp";
                    break;

                case "getOverview":
                case "cancelChangeGame":
                    gamesList = DB.getGamesList();
                    request.setAttribute("games", gamesList);
                    String result = DB.getGameHighestScore().getName();
                    request.setAttribute("highestScoreGame", result);
                    destination = "/overview.jsp";
                    break;

                case "getIndex":
                    Changelog changelog = (Changelog) request.getSession().getAttribute("changes");
                    if (changelog != null) {
                        request.setAttribute("changes", changelog.getChanges());
                    }
                    destination = "/index.jsp";
                    break;

                case "getFormulier":
                    int cookieFound = -1;
                    Cookie[] cookies = request.getCookies();
                    for (int i = 0; i < cookies.length; i++) {
                        if (cookies[i].getName().equals("formulierTries")) {
                            cookieFound = i;
                        }
                    }
                    if (cookieFound == -1 || cookies.length == 0) {
                        response.addCookie(new Cookie("formulierTries", "0"));
                    } else {
                        response.addCookie(cookies[cookieFound]);
                    }
                    destination = "/formulier.jsp";
                    break;

                case "changeGame":
                    request.setAttribute("gameID", Integer.parseInt(request.getParameter("gameID")));
                    g = DB.getGameByID(Integer.parseInt(request.getParameter("gameID")));
                    request.setAttribute("Game", g);
                    destination = "/confirmChange.jsp";
                    break;

                case "removeGame":
                    request.setAttribute("Game", DB.getGameByID(Integer.parseInt(request.getParameter("gameID"))));
                    gamesList = DB.getGamesList();
                    request.setAttribute("games", gamesList);
                    destination = "/confirmDelete.jsp";
                    break;

                case "cancelRemoveGame":
                    gamesList = DB.getGamesList();
                    request.setAttribute("games", gamesList);
                    destination = "/overview.jsp";
                    break;

                case "getOverzichtFietsen":
                case "cancelRemoveFiets":
                    request.setAttribute("fietsen", fietsendb.getAlleFietsen());
                    request.setAttribute("laatstAangepastBestaat", fietsExists(request.getCookies()));
                    destination = "/overzichtFietsen.jsp";
                    break;

                case "searchFietsen":
                    ArrayList<String> errors = new ArrayList<>();
                    String merk = request.getParameter("fietsMerk");
                    request.setAttribute("fietsen", fietsendb.getFietsenVanMerk(merk , errors));
                    request.setAttribute("errors", errors);
                    if (merk.isEmpty() == true) {
                        merk = "ongeldig";
                    }
                    request.setAttribute("gezochtMerk", merk);
                    request.setAttribute("laatstAangepastBestaat", fietsExists(request.getCookies()));
                    destination = "/overzichtFietsen.jsp";
                    break;

                case "resetCookies":
                    response.addCookie(new Cookie("formulierTries", "0"));
                    request.setAttribute("tries", "0");
                    destination = "/confirmCookieReset.jsp";
                    break;

                case "removeFiets":
                    f = fietsendb.getFietsById(Integer.parseInt(request.getParameter("fietsID")));
                    request.setAttribute("fiets", f);
                    destination = "/verwijderFietsBevestiging.jsp";
                    break;

                case "fietsKilometerUpdater":
                    f = fietsendb.getFietsById(Integer.parseInt(request.getParameter("fietsID")));
                    request.setAttribute("fiets", f);
                    destination = "/updateKilometers.jsp";
                    break;

                default:
                    destination = "/index.jsp";
                    break;
            }

            request.getRequestDispatcher(destination).forward(request, response);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        String destination = "none";
        int gameID = -1;
        ArrayList<Game> gamesList = DB.getGamesList();
        Game g;
        String name;
        String genre;
        String score;
        ArrayList<String> errors;
        Fiets f;
        HttpSession session;

        switch (action) {
            case "addGame":
                name = request.getParameter("gameName");
                genre = request.getParameter("gameGenre");
                score = request.getParameter("gameScore");

                errors = new ArrayList<>();

                g = new Game(DB.requestID());
                g.controlAndSetAll(name, genre, score, errors);
                if (errors.size() == 0) {
                    DB.addGame(g);
                    gamesList = DB.getGamesList();
                    request.setAttribute("games", gamesList);
                    destination = "/overview.jsp";
                } else {
                    Cookie[] cookies = request.getCookies();
                    int tries = 0;
                    for (int i = 0; i < cookies.length; i++) {
                        if (cookies[i].getName().equals("formulierTries")) {
                            tries = Integer.parseInt(cookies[i].getValue()) + 1;
                        }
                    }
                    response.addCookie(new Cookie("formulierTries",Integer.toString(tries)));
                    request.setAttribute("tries", Integer.toString(tries));

                    request.setAttribute("errors", errors);

                    gamesList = DB.getGamesList();
                    request.setAttribute("games", gamesList);
                    destination = "/formulier.jsp";
                }
                break;

            case "confirmRemoveGame":
                gameID = Integer.parseInt(request.getParameter("gameID"));
                DB.removeGameByID(gameID);
                gamesList = DB.getGamesList();
                request.setAttribute("games", gamesList);
                destination = "/overview.jsp";
                break;


            case "confirmChangeGame":
                name = request.getParameter("gameName");
                genre = request.getParameter("gameGenre");
                score = request.getParameter("gameScore");

                errors = new ArrayList<>();

                gameID = Integer.parseInt(request.getParameter("gameID"));
                g = DB.getGameByID(gameID);
                Game oldGame = new Game(DB.requestID(), g.getName(), g.getGenre(), g.getScore());
                g.controlAndSetAll(name, genre, score, errors);

                destination = "/overview.jsp";

                if (errors.size() == 0) {
                    session = request.getSession();
                    Changelog changelog = (Changelog) session.getAttribute("changes");
                    if (changelog == null) {
                        changelog = new Changelog();
                    }
                    Game newGame = new Game(DB.requestID(), g.getName(), g.getGenre(), g.getScore());
                    changelog.AddChange(oldGame, newGame);
                    session.setAttribute("changes", changelog);
                } else {
                    request.setAttribute("errors", errors);
                    request.setAttribute("gameID", gameID);
                    request.setAttribute("Game", g);
                    destination = "/confirmChange.jsp";
                }

                gamesList = DB.getGamesList();
                request.setAttribute("games", gamesList);
                break;


            case "confirmRemoveFiets":
                int fietsID = Integer.parseInt(request.getParameter("fietsID"));
                session = request.getSession();
                session.setAttribute("verwijderdeFiets",  fietsendb.getFietsById(fietsID));
                fietsendb.remove(fietsID);
                request.setAttribute("fietsen", fietsendb.getAlleFietsen());
                request.setAttribute("laatstAangepastBestaat", fietsExists(request.getCookies()));
                destination = "/overzichtFietsen.jsp";
                break;

            case "updateKilometers":
                String km = request.getParameter("km");
                errors = new ArrayList<>();
                f = fietsendb.getFietsById(Integer.parseInt(request.getParameter("fietsID")));
                f.setKilometers(km, errors);
                if (errors.size() == 0) {
                    request.setAttribute("fietsen", fietsendb.getAlleFietsen());
                    response.addCookie(new Cookie("laatstAangepast", Integer.toString(f.getId())));
                    request.setAttribute("lAangepast", Integer.toString(f.getId()));
                    request.setAttribute("laatstAangepastBestaat", true);
                    destination = "/overzichtFietsen.jsp";
                } else {
                    request.setAttribute("errors", errors);
                    request.setAttribute("fiets", f);
                    destination = "/updateKilometers.jsp";
                }
                break;

            case "undeleteFiets":
                session = request.getSession();
                f = (Fiets) session.getAttribute("verwijderdeFiets");
                fietsendb.addWithId(f);
                session.setAttribute("verwijderdeFiets", null);
                request.setAttribute("fietsen", fietsendb.getAlleFietsen());

                destination = "/overzichtFietsen.jsp";
                break;

            default:
                destination = "/index.jsp";
                break;
        }

        request.getRequestDispatcher(destination).forward(request, response);

    }

    private boolean fietsExists(Cookie[] cookies) {
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("laatstAangepast")) {
                try {
                    fietsendb.getFietsById(Integer.parseInt(cookies[i].getValue()));
                } catch (DomainException D) {
                    return false;
                }
            }
        }
        return true;
    }
}