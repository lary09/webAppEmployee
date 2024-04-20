package service;

import dao.DeparmentDAO;
import models.Deparment;

import java.sql.SQLException;
import java.util.List;

public class DepartmentService {
    DeparmentDAO deparmentDAO = new DeparmentDAO();

    public void inertDepartment(Deparment deparment) throws SQLException{
        deparmentDAO.insertDeparment(deparment);
    }
    public Deparment getDepartmentById (int id) throws SQLException{
        return deparmentDAO.getDeparmentById(id);
    }
    public List<Deparment> getAllDepartment()  throws SQLException {
        return deparmentDAO.getAllDeparment();
    }
    public boolean updateDepartment(Deparment deparment) throws SQLException{
        return deparmentDAO.updateDepartment(deparment);
    }
    public boolean deleteDepartment(int id) throws SQLException{
        return deparmentDAO.deleteDeparment(id);
    }
}
