package app;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("====================Find by id====================");
        Seller sellerById = sellerDao.findById(3);
        System.out.println(sellerById);
        System.out.println("====================Find by id====================");

        System.out.println("\n================Find by department================");
        List<Seller> sellersByDepartment = sellerDao.findByDepartment(2);
        for (Seller sellerByDepartment : sellersByDepartment) {
            System.out.println(sellerByDepartment);
        }
        System.out.println("================Find by department================");

        System.out.println("\n=================Get all sellers==================");
        List<Seller> AllSellers = sellerDao.findAll();
        for (Seller seller : AllSellers) {
            System.out.println(seller);
        }
        System.out.println("=================Get all sellers==================");
    }
}
