import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "infoExchangeServlet ", value = "/infoExchangeServlet")
public class infoExchangeServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
String a = request.getParameter("list");
        try {
            String text= DataBase.Max(a);
            String text1=DataBase.Avg(a);
            request.setAttribute("a",a);
            request.setAttribute("max",text);
            request.setAttribute("avg",text1);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (IOException |ServletException| IllegalArgumentException |
                SecurityException e) {
            e.printStackTrace();

        }}
}
