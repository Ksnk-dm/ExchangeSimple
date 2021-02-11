import com.google.gson.Gson;
import java.io.*;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "indexServlet", value = "/indexServlet")
public class IndexServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        for (int i = 1; i <= 10; i++) { //понимаю что не совсем корректно, та и нагрузка большая на бд
            URL url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date=202101" + i + "&json");
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()))) {
                Gson gson = new Gson();
                Exchange[] curs = gson.fromJson(in, Exchange[].class);
                for (Exchange user : curs) {
                    int r030 = user.getR030();
                    String txt = user.getTxt();
                    double rate = user.getRate();
                    String cc = user.getCc();
                    String exchangedate = user.getExchangedate();
                    Exchange newCurs = new Exchange(r030, txt, rate, cc, exchangedate);
                    DataBase.Insert(newCurs);
                }

            }
        }
        request.setAttribute("result", "done");
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, IllegalArgumentException, SecurityException {
        DataBase.delDB();
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }
}




