package app;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("====================Find by id====================");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println("====================Find by id====================");

        System.out.println("\n================Find by department================");
        List<Seller> sellers = sellerDao.findByDepartment(2);
        for (Seller obj : sellers) {
            System.out.println(obj);
        }
        System.out.println("================Find by department================");
    }
}
