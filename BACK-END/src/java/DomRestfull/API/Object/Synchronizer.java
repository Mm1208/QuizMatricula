package DomRestfull.API.Object;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "synchronizer", urlPatterns = {"/synchronizer"})
public class Synchronizer extends HttpServlet {

    private static final Synchronizer synchronizer = new Synchronizer();
    private boolean change;

    public static Synchronizer getSynchronizer() {
        return synchronizer;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/plain");
            response.getWriter().write(String.valueOf(getSynchronizer().isChange()));
            getSynchronizer().setChange(false);
    }

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }
}
