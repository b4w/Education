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
public class SignUpServlet extends HttpServlet {

    private DBService dbService;

    public SignUpServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
            resp.setContentType("text/html;charset=utf-8");
            try {
                dbService.addUser(login, password);
                UsersDataSet usersDataSet = dbService.getUserByLoginAndPassword(login, password);
                if (usersDataSet != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } catch (DBException e) {
                System.out.println("When adding there was an error. " + e);
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return;
    }
}
