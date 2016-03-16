package servlets;

import accountServer.AccountServerI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPageServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(AdminPageServlet.class.getName());
    public static final String PAGE_URL = "/admin";
    private final AccountServerI accountServer;

    public AdminPageServlet(AccountServerI accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String remove = req.getParameter("remove");

        if (remove != null) {
            accountServer.removeUser();
            resp.getWriter().println("Hasta la vista");
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        int limit = accountServer.getUsersLimit();
        int count = accountServer.getUsersCount();

        LOGGER.info("Users limit: {}, Users count: {}", limit, count);

        if (limit > count) {
            LOGGER.info("User pass");
            accountServer.addNewUser();
            resp.getWriter().println(accountServer.getUsersLimit());
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            LOGGER.info("User were rejected");
            resp.getWriter().println("Server is close");
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
