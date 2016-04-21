import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tmp on 14.04.2016.
 */
public class TodoServlet extends HttpServlet {

    private ArrayList<String> tasks = new ArrayList<>();

    protected void outputList (HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> data =new HashMap<>();
        data.put("allTasks", tasks);
        TemplateUtil.render("todo.vsl", data, resp.getWriter());
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        outputList(resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newTask=req.getParameter("newTask");
        tasks.add(newTask);
        outputList(resp);
    }
}

