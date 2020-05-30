package DomRestfull.API.Object;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "logout", urlPatterns = {"/logout"})
public class Logout extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            login(request, response);
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(400);
            response.getWriter().write(ex.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            login(request, response);
            response.setStatus(204);
        } catch (Exception ex) {
            response.setStatus(400);
            response.setContentType("text/plain");
            response.getWriter().write(ex.getMessage());
        }
    }
    
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
    }
}
