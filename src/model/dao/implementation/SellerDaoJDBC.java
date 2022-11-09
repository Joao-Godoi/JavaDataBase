package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
                Department department = new Department(resultSet.getInt("DepartmentId"),
                        resultSet.getString("DepartmentName"));

                Seller seller = new Seller();
                seller.setId(resultSet.getInt("Id"));
                seller.setName(resultSet.getString("Name"));
                seller.setEmail(resultSet.getString("Email"));
                seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
                seller.setBirthDate(resultSet.getDate("BirthDate"));
                seller.setDepartment(department);

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

    @Override
    public List<Seller> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
