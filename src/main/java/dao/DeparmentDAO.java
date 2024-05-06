package dao;

import models.Deparment;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeparmentDAO {
    private static final String DELETE_DEPARMENT = "DELETE FROM department WHERE id = ?";

    private static final String INSERT_DEPARTMENT_SQL = "INSERT INTO department (name, description) VALUES (?,?)";
    public void insertDeparment(Deparment deparment) throws SQLException{
    Connection conection = ConnectionDB.conn();
    PreparedStatement preparedStatement = conection.prepareStatement(INSERT_DEPARTMENT_SQL);
    preparedStatement.setString(1, deparment.getName());
    preparedStatement.setString(2, deparment.getDescription());
    preparedStatement.executeUpdate();
    conection.close();
    }
    private static final String SELECT_DEPARTMENT_BY_ID = "SELECT id, name, description FROM department WHERE id=?";
    public Deparment getDeparmentById(int id) throws SQLException{
        Deparment deparment = null;
        Connection connection = ConnectionDB.conn();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet =preparedStatement.executeQuery();

        while (resultSet.next()){
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            deparment = new Deparment(id, name, description);
        }
        connection.close();
        resultSet.close();
        return deparment;
    }
    private static final String SELECT_ALL_DEPARMENT = "SELECT * FROM department";
    public List<Deparment> getAllDeparment() throws SQLException{
        List<Deparment> deparments = new ArrayList<>();
        Connection connection = ConnectionDB.conn();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEPARMENT);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            deparments.add(new Deparment(id, name, description));
        }
        connection.close();
        resultSet.close();
        return deparments;
    }
    private static final String UPDATE_DEPARMENT = "UPDATE department SET name = ?, description = ? WHERE id = ?";
    public boolean updateDepartment(Deparment deparment) throws SQLException{
        boolean rowUpdated;
        Connection connection = ConnectionDB.conn();
        PreparedStatement statement = connection.prepareStatement(UPDATE_DEPARMENT);
         statement.setString(1, deparment.getName());
         statement.setString(2, deparment.getDescription());
         statement.setInt(3, deparment.getId());
         rowUpdated = statement.executeUpdate() > 0;
         return rowUpdated;
    }
    public boolean deleteDeparment(int id) throws SQLException{
        boolean rowDeleted;
        Connection connection = ConnectionDB.conn();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPARMENT);
        preparedStatement.setInt(1, id);
        rowDeleted = preparedStatement.executeUpdate() > 0;
        return  rowDeleted;
    }
}
