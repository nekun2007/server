import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tmp on 14.04.2016.
 */
public class TestServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> data = Collections.emptyMap();
        TemplateUtil.render("page1.vsl", data, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("username", name);
        TemplateUtil.render("page2.vsl", data, resp.getWriter());

    }
}

