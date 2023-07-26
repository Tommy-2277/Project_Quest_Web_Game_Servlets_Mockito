package com.questwebgame;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GameServlet", value = "/ProjectQuestWebGame/game")
public class GameServlet extends HttpServlet {
    private static int questionNumber;
    private final static List<String> questions;
    private final static List<List<String>> answers;
    private static int playedGames;
    private static boolean win;


    static {
        questionNumber = 0;
        questions = fillListWithQuestions();
        answers = fillListWithAnswers();
        playedGames = 0;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (questionNumber < questions.size()) {
            String question = getQuestion(questionNumber);
            List<String> answers = getAnswers(questionNumber);
            req.setAttribute("question", question);
            req.setAttribute("answers", answers);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/game.jsp");
            dispatcher.forward(req, resp);
        } else if (questionNumber == questions.size()) {
            winOrLose(req, resp, true);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String selectedAnswer = req.getParameter("answer");

        List<String> currentAnswers = getAnswers(questionNumber);
        for (String s : currentAnswers) {
            if (selectedAnswer.equals(s)) {
                if (checkAnswer(questionNumber, currentAnswers.indexOf(s))) {
                    increaseQuestionNumber();
                    doGet(req, resp);
                } else {
                    winOrLose(req, resp, false);
                }
                break;
            }
        }
    }

    public static List<String> fillListWithQuestions() {
        return List.of("<h1><br><br>Твой персонаж потерял работу, квартиру, семью, и сейчас нужно вернуться на былое положение.<br></h1>\n\n<h1><br><br><br><br><br>Вопрос №1:</h1>\n<h1>У тебя вырвали из рук кошелёк с последними деньгами. Что будешь делать?</h1>",
                "<h1><br><br>Деньги спасены. Но теперь тебе нужно поесть. Ты очень голодный, промок и замерз на улице.</h1><h1>Еда сейчас будет как нельзя кстати.<br><br></h1>\n\n<h1>Вопрос №2:</h1>\n<h1>Как решишь проблему голода?</h1>",
                "<h1><br><br>После драки у кассы за последний батон с коллегой по положению, ты понял, что пора найти работу.<br><br></h1>\n\n<h1>Вопрос №3:</h1>\n<h1>Какое предложение ты примешь?</h1>",
                "<h1><br><br>Наконец-то есть работа, крыша над головой и возможность подумать о будущем.<br><br></h1>\n\n<h1>Вопрос №4:</h1>\n<h1>Какое направление для развития выберешь?</h1>",
                "<h1><br><br>Отлично.</h1><h1> И вот сама судьба улыбнулась тебе, предоставив кучу возможностей.<br><br></h1>\n\n<h1>Вопрос №5:</h1>\n<h1>Что ты выберешь?</h1>");
    }

    public static List<List<String>> fillListWithAnswers() {
        return List.of(List.of("Догнать вора и вернуть кошелек", "Пусть оставит себе, ему нужнее"),
                List.of("Купить хлеб", "Украсть в магазине мясо", "Порыться в помойке"),
                List.of("Охранник - 200$, проживание", "Мойка авто - 300$", "Фонтан - 0-500$, дружный коллектив"),
                List.of("IT", "Частное Охранное Предприятие", "Боец ММА"),
                List.of("Ставка на спортивное событие", "Вложиться в криптовалюту в мемкоин", "Купить акции на фондовом рынке, ничего в них не понимая", "Открыть шаурмечную", "Создать бренд одежды", "Заняться перепродажей щенков", "Согласиться на одну \"темку\" друга", "Дать в долг под проценты без расписки", "Ничего"));
    }

    public static String getQuestion(int index) {
        return questions.get(index);
    }

    public static int getQuestionNumber() {
        return questionNumber;
    }

    public static List<String> getAnswers(int index) {
        return answers.get(index);
    }

    public static int getPlayedGames() {
        return playedGames;
    }

    public static boolean getWinValue() {
        return win;
    }

    public static void winOrLose(HttpServletRequest req, HttpServletResponse resp, boolean isWin) throws ServletException, IOException {
        win = isWin;
        req.getSession().invalidate();
        questionNumber = 0;
        playedGames++;
        req.getRequestDispatcher("/winLose.jsp").forward(req, resp);
    }

    public static void increaseQuestionNumber() {
        questionNumber++;
    }

    public static void setQuestionNumber(int value) {
        questionNumber = value;
    }

    public static boolean checkAnswer(int questionNumber, int answerNumber) {
        switch (questionNumber) {
            case 0, 3 -> {
                return answerNumber == 0;
            }
            case 1 -> {
                if (answerNumber == 0) {
                    return true;
                } else if (answerNumber == 1) {
                    return false;
                } else {
                    return false;
                }
            }
            case 2 -> {
                if (answerNumber == 0) {
                    return true;
                } else if (answerNumber == 2) {
                    return false;
                } else {
                    return false;
                }
            }
            case 4 -> {
                return answerNumber == 8;
            }
        }
        return true;
    }
}


