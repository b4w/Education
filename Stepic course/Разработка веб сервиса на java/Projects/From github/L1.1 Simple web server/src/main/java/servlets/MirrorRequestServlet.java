package servlets;

import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MirrorRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = generatePageVariables(req);
//        resp.getWriter().println(PageGenerator.instance().getPage("mirror.html", pageVariables));
        resp.getWriter().println(pageVariables.get("value"));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = generatePageVariables(req);

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(PageGenerator.instance().getPage("mirror.html", pageVariables));
    }

    private static Map<String, Object> generatePageVariables(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("value", request.getParameterMap().get("key")[0]);
        return result;
    }
}
