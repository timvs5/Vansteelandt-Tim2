package be.ucll.vansteelandttim.ui.controller;

import be.ucll.vansteelandttim.Test;
import be.ucll.vansteelandttim.domain.db.Database;
import be.ucll.vansteelandttim.domain.model.Game;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.lang.*;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    private String message;
    Database DB;
    private Test test;

    public void init() {
        DB = new Database();
        message = "Hello World!";
        test = new Test();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            String action = request.getParameter("action");
            String destination = "none";
            switch (action) {
                case "getGameScore":
                    String param = request.getParameter("getGameScore");
                    int resultScore = DB.getGameScoreByName(param);
                    request.setAttribute("result", resultScore);
                    destination = "/result.jsp";
                    break;

                case  "getOverview":
                    ArrayList<Game> gamesList = DB.getGamesList();
                    request.setAttribute("games", gamesList);
                    destination = "/overview.jsp";
                    break;

                case "getIndex":
                    String result = DB.getGameHighestScore().getName();
                    request.setAttribute("highestScoreGame", result);
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
                    if (cookieFound == -1) {
                        response.addCookie(new Cookie("formulierTries", "0"));
                    } else {
                        response.addCookie(cookies[cookieFound]);
                    }
                    destination = "/formulier.jsp";
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
        int tries = 0;

        switch (action) {
            case "addGame":
                Cookie[] cookies = request.getCookies();
                int cookPos = -1;
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("formulierTries")) {
                        cookPos = i;
                     }
                }
                try {
                    String name = request.getParameter("gameName");
                    String genre = request.getParameter("gameGenre");
                    int score = Integer.parseInt(request.getParameter("gameScore"));

                    if (name.equals("") || genre.equals("")) {
                        throw new Exception();
                    }

                    DB.add(name, genre, score);
                    gamesList = DB.getGamesList();
                    request.setAttribute("games", gamesList);
                    destination = "/overview.jsp";

                } catch (Exception E) {
                    if (cookPos != -1) {
                        response.addCookie(new Cookie("formulierTries",Integer.toString(Integer.parseInt(cookies[cookPos].getValue()) + 1)));
                    } else {response.addCookie(new Cookie("formulierTries","1"));}
                    destination = "/formulier.jsp";
                }
                break;

            case "removeGame":
                request.setAttribute("Game", DB.getGameByID(Integer.parseInt(request.getParameter("gameID"))));
                destination = "/confirmDelete.jsp";

                break;

            case "confirmRemoveGame":
                gameID = Integer.parseInt(request.getParameter("gameID"));
                DB.removeGameByID(gameID);
                gamesList = DB.getGamesList();
                request.setAttribute("games", gamesList);
                destination = "/overview.jsp";
                break;

            case "cancelRemoveGame":
                destination = "/formulier.jsp";
                break;

            case "changeGame":
                request.setAttribute("gameID", Integer.parseInt(request.getParameter("gameID")));
                Game gam = DB.getGameByID(Integer.parseInt(request.getParameter("gameID")));
                request.setAttribute("Game", gam);
                destination = "/confirmChange.jsp";

                break;

            case "confirmChangeGame":
                gameID = Integer.parseInt(request.getParameter("gameID"));
                DB.changeGameByID(gameID, request.getParameter("gameName"), request.getParameter("gameGenre"), Integer.parseInt(request.getParameter("gameScore")));
                gamesList = DB.getGamesList();
                request.setAttribute("games", gamesList);
                destination = "/overview.jsp";

                break;

            case "resetCookies":
                response.addCookie(new Cookie("formulierTries", "0"));
                destination = "/formulier.jsp";
                break;

            default:
                destination = "/index.jsp";
                break;
        }

        request.getRequestDispatcher(destination).forward(request, response);


    }


    public void destroy() {
    }
}