package com.demo.DAO;

import com.demo.model.Task;
import com.demo.util.JdbcConnetion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private static final String ADD_TASK="INSERT INTO task(id,name) VALUES(?,?)";
    private static final String DELETE_TASK="DELETE FROM task WHERE id=?";
    private static final String GET_LIST_TASK="SELECT t.id,t.name,t.status FROM task t JOIN own o ON t.id = o.tid WHERE o.email=?";
    private static final String UPDATE_TASK="UPDATE task SET name=? WHERE id=?";
    public void add(Task task) {
        try{
            Connection connection = new JdbcConnetion().getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD_TASK);
            ps.setInt(1, task.getId());
            ps.setString(2, task.getName());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(Task task) {
        try{
            Connection connection = new JdbcConnetion().getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_TASK);
            ps.setInt(1, task.getId());
            System.out.println(task.getName());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Task> getListTask(String email){
        List<Task> taskList= new ArrayList<>();
        try{
            Connection connection = new JdbcConnetion().getConnection();
            PreparedStatement ps =connection.prepareStatement(GET_LIST_TASK);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Task t= new Task();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setStatus(rs.getString("status"));
                taskList.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }
    public void update(Task task) {
        try{
            Connection connection = new JdbcConnetion().getConnection();
            PreparedStatement ps =connection.prepareStatement(UPDATE_TASK);
            ps.setString(1, task.getName());
            ps.setInt(2, task.getId());
            ps.executeUpdate();
            System.out.println("delete");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
