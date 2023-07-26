import com.questwebgame.GameServlet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.mockito.Mockito.*;

public class GameServletTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        //создал объект сервлета, заглушки, путь к jsp, вопрос+ответ
        GameServlet gameServlet = new GameServlet();
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        PrintWriter printWriter = mock(PrintWriter.class);
        String pathWinLose = "/winLose.jsp";
        String path = "/game.jsp";
        String questionTest = "<h1><br><br>Твой персонаж потерял работу, квартиру, семью, и сейчас нужно вернуться на былое положение.<br></h1>\n\n<h1><br><br><br>Вопрос №1:</h1>\n<h1>У тебя вырвали из рук кошелёк с последними деньгами. Что будешь делать?</h1>";
        List<String> answerTest = List.of("Догнать вора и вернуть кошелек", "Пусть оставит себе, ему нужнее");

        //когда будет вызван request.getRequestDispatcher(path), тогда вернется dispatcher. иначе будет NPE у dispatcher
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getRequestDispatcher(pathWinLose)).thenReturn(dispatcher);
        //возврат заглушек
        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getAttribute("question")).thenReturn(1);
        when(request.getAttribute("answers")).thenReturn(2);

        //установил переменной 0, что меньше листа с вопросами, вызвал doGet и проверяю, установились ли атрибуты
        GameServlet.setQuestionNumber(0);
        gameServlet.doGet(request, response);
        verify(request, times(1)).setAttribute("question", questionTest);
        verify(request, times(1)).setAttribute("answers", answerTest);

        //сверяю возвращаемые значения с ожидаемыми
        Assertions.assertEquals(1, request.getAttribute("question"));
        Assertions.assertEquals(2, request.getAttribute("answers"));
        //убеждаюсь, что запрос вызывал 1 раз метод getRequestDispatcher с верным параметром
        verify(request, times(1)).getRequestDispatcher(path);
        //убеждаюсь, что диспетчер 1 раз перенаправляет запрос и ответ на другой сервлет/jsp
        verify(dispatcher, times(1)).forward(request, response);

        //установил переменной 5, что равно листу с вопросами, вызвал doGet, убедился, что все методы были вызваны по одному разу
        GameServlet.setQuestionNumber(5);
        gameServlet.doGet(request, response);
        verify(session, times(1)).invalidate();
    }

    @Test
    void doPostTest() throws IOException, ServletException {
        //объявил нужные заглушки
        GameServlet gameServlet = mock(GameServlet.class);
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);


        //настроил работу внутри метода
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        when(request.getParameter("answer")).thenReturn("1");
        when(request.getSession()).thenReturn(session);


        //убеждаюсь, что все работает
        gameServlet.doPost(request, response);
        verify(response, times(1)).setCharacterEncoding("UTF-8");
        verify(response, times(1)).setContentType("text/html;charset=UTF-8");
        verify(request, times(1)).setCharacterEncoding("UTF-8");
        gameServlet.doGet(request, response);
        verify(gameServlet, times(1)).doGet(request, response);
        session.invalidate();
        verify(session, times(1)).invalidate();
    }
}
