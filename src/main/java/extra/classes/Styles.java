package extra.classes;

public class Styles {
    public static String getStyle(int quesNumb) {
        switch(quesNumb) {
            case 0: return "/css/firstQuestion.css";
            case 1: return "/css/secondQuestion.css";
            case 2: return "/css/thirdQuestion.css";
            case 3: return "/css/fourthQuestion.css";
            case 4: return "/css/fifthQuestion.css";
        }
        return null;
    }
}
