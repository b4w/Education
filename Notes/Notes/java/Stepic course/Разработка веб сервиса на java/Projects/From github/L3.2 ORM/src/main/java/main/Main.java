package main;

import dbService.DBService;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception {

        // создаем подключение к БД
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        // создаем контекст
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        // добавляем в контекст сервлеты по обработке запросов
        context.addServlet(new ServletHolder(new SignUpServlet(dbService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(dbService)), "/signin");

        // создаем обработчика ресурсов
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("html");

        // регистрируем обрабочики
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        // запускаем сервер
        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        Logger.getGlobal().log(Level.INFO, "Server started");
        server.join();
    }
}