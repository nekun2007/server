/**
 * Created by tmp on 14.04.2016.
 */
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
public class JettyTest {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8088); //создаем объект сервер
        ServletContextHandler handler = new ServletContextHandler(); //обработчик запросов
        server.setHandler(handler);
        handler.addServlet(TodoServlet.class, "/"); // слеш означает что обращение идет к корню сайта и перенаправляет на тудуСервлет
        server.start();
    }
}
// создание бд джетитест едит конфигуратионз плюсик апликатиеон org.h2.tools.Console name любое ПА