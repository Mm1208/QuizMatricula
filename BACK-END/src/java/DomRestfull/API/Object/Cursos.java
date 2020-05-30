package DomRestfull.API.Object;

import static Logic.Utils.verifyString;
import Model.Model_Curso;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@WebServlet(name = "curso", urlPatterns = {"/curso"})
public class Cursos extends HttpServlet {

    Model_Curso model = Model_Curso.getModel();

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            model.insert(request.getParameter("json"));
            Synchronizer.getSynchronizer().setChange(true);
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(400);
            response.setContentType("text/plain");
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            model.update(request.getParameter("json"));
            Synchronizer.getSynchronizer().setChange(true);
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(400);
            response.setContentType("text/plain");
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            model.delete(request.getParameter("json"));
            Synchronizer.getSynchronizer().setChange(true);
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(400);
            response.setContentType("text/plain");
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            opciones(request, response);
        } catch (Exception ex) {
            response.setStatus(400);
            response.setContentType("text/plain");
            response.getWriter().write(ex.getMessage());
        }
    }

    public void opciones(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String opcion = verifyString(request.getParameter("opcion"), "una opción");
        String result = null;
        switch (opcion) {
            case "insert":
                model.insert(request.getParameter("json"));
                Synchronizer.getSynchronizer().setChange(true);
                break;
            case "update":
                model.update(request.getParameter("json"));
                Synchronizer.getSynchronizer().setChange(true);
                break;
            case "delete":
                model.delete(request.getParameter("json"));
                Synchronizer.getSynchronizer().setChange(true);
                break;
            case "query":
                result = model.query(request.getParameter("json")).getJSON().toString();
                break;
            case "list":
                result = model.list().stream()
                        .map((x) -> {
                            try {
                                return x.getJSON().toString();
                            } catch (Exception ex) {
                                throw new RuntimeException(ex.getMessage());
                            }
                        })
                        .collect(Collectors.toList()).toString();
                break;
            default:
                throw new Exception("¡Opción desconocida!");
        }
        if (result == null) {
            response.setStatus(200);
        } else {
            response.setContentType("text/plain");
            response.getWriter().write(result);
        }
    }

}
