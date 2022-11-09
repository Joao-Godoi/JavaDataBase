package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller Seller) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Seller Seller) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT seller.*,department.Name as DepartmentName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Department department = instantiateDepartment(resultSet);
                Seller seller = instantiateSeller(resultSet, department);
                return seller;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
        }
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("DepartmentId"));
        department.setName(resultSet.getString("DepartmentName"));
        return department;
    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString("Name"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
        seller.setBirthDate(resultSet.getDate("BirthDate"));
        seller.setDepartment(department);
        return seller;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Seller> sellerList = new ArrayList<Seller>();

        try {
            statement = connection.prepareStatement("SELECT seller.*,department.Name as DepartmentName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Id");
            resultSet = statement.executeQuery();

            Map<Integer, Department> map = new HashMap<Integer, Department>();
            while (resultSet.next()) {
                Department department = map.get(resultSet.getInt("DepartmentId"));
                if (department == null) {
                    department = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), department);
                }
                Seller seller = instantiateSeller(resultSet, department);
                sellerList.add(seller);
            }
            return sellerList;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
        }
    }

    @Override
    public List<Seller> findByDepartment(Integer departmentId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT seller.*,department.Name as DepartmentName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Id");
            statement.setInt(1, departmentId);
            resultSet = statement.executeQuery();

            List<Seller> sellerList = new ArrayList<Seller>();
            Map<Integer, Department> map = new HashMap<Integer, Department>();

            while (resultSet.next()) {
                Department department = map.get(resultSet.getInt("DepartmentId"));
                if (department == null) {
                    department = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), department);
                }
                Seller seller = instantiateSeller(resultSet, department);
                sellerList.add(seller);
            }
            return sellerList;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
        }
    }
}
