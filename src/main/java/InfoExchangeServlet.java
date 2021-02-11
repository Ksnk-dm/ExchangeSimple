import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "infoExchangeServlet ", value = "/infoExchangeServlet")
public class InfoExchangeServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        DataBase dataBase = new DataBase();
String a = request.getParameter("list");
        try {
            String text= dataBase.max(a);
            String text1=dataBase.avg(a);
            request.setAttribute("a",a);
            request.setAttribute("max",text);
            request.setAttribute("avg",text1);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (IOException |ServletException| IllegalArgumentException |
                SecurityException e) {
            e.printStackTrace();

        }}
}
