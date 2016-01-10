package servlets;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by KonstantinSysoev on 25.12.15.
 */
public class SignInServlet extends HttpServlet {

    private DBService dbService;

    public SignInServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        resp.setContentType("text/html;charset=utf-8");

        if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
            try {
                UsersDataSet usersDataSet = dbService.getUserByLoginAndPassword(login, password);

                if (usersDataSet != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().println("Authorized");
                } else {
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    resp.getWriter().println("Unauthorized");
                }

            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("Unauthorized");
        }
        return;
    }
}
