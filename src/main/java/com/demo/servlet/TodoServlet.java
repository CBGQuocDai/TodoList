package com.demo.servlet;

import com.demo.DAO.OwnDAO;
import com.demo.DAO.TaskDAO;
import com.demo.model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import org.json.JSONObject;

@WebServlet(urlPatterns = {"/t"})
public class TodoServlet extends HttpServlet {
    private static final TaskDAO taskDAO = new TaskDAO();
    private static final OwnDAO ownDAO = new OwnDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        System.out.println(email);
        List<Task> tasks= taskDAO.getListTask(email);
        int length = tasks.size();
        System.out.println("len= "+length);
        for(Task task:tasks){
            System.out.println(task.getName());
        }
        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("/todo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameTask = req.getParameter("task");
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        Task task = new Task(nameTask);
        taskDAO.add(task);
        ownDAO.add(email, task.getId());
        resp.sendRedirect("/TodoList_war_exploded/t");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task task= jsonToTask(req);
        taskDAO.delete(task);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task task = jsonToTask(req);
        taskDAO.update(task);
    }
    private Task jsonToTask(HttpServletRequest req) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String data = sb.toString();
        JSONObject json = new JSONObject(data);
        Task task =new Task();
        task.setName(json.getString("name"));
        task.setId(json.getInt("id"));
        task.setStatus(json.getString("status"));
        return task;
    }
}
