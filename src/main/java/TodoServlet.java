import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tmp on 14.04.2016.
 */
public class TodoServlet extends HttpServlet {

    protected void outputList(HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<TodoItem> tasks = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", ""))  //по указанному адресу обращается к БД
        {
            try (PreparedStatement st = conn.prepareStatement("SELECT ID, TEXT FROM TODO ORDER BY ID DESC")) //запрос селект считывает даные из БД
            {
                try (ResultSet rs = st.executeQuery())// выполнение запроса и возвращает таблицу
                {
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String text = rs.getString(2);
//                        System.out.println(id + " " + text);
                        tasks.add(new TodoItem(id, text));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> data = new HashMap<>();
        data.put("allTasks", tasks);
        TemplateUtil.render("todo.vsl", data, resp.getWriter());
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        outputList(resp);
    }//отображение по шаблону бд


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newTask = req.getParameter("newTask");
        String id = req.getParameter("id");
//        tasks.add(newTask);
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
            if (id !=null) { //удаление записи если айди не пустое(айди идет снизу вверх)
                try (PreparedStatement st = conn.prepareStatement("DELETE FROM TODO WHERE ID = ?")) {
                    st.setInt(1, Integer.parseInt(id));
                    st.execute();
                }

            } else {
                try (PreparedStatement st = conn.prepareStatement("INSERT INTO TODO (TEXT) VALUES (?)")) {
                    st.setString(1, newTask);
                    st.execute();
                }

            }
            //outputList(resp);
            resp.sendRedirect("/");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }// нажатие на кнопку
}
