package app;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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

        System.out.println("\n===============Insert new seller==================");
        Seller newSeller = new Seller(null, "João Godoi", "joao@email.com", new Date(),
                5000.00, new Department(2, "Sports"));
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New seller: " + sellerDao.findById(newSeller.getId()));
        System.out.println("===============Insert new seller==================");

        System.out.println("\n================Update a seller===================");
        sellerById = sellerDao.findById(1);
        System.out.println("Seller before update: " + sellerById);
        sellerById.setName("João Godoi Update");
        sellerById.setEmail("joaogodoi@email.com");
        sellerDao.update(sellerById);
        sellerById = sellerDao.findById(1);
        System.out.println("Seller after update: " + sellerById);
        System.out.println("================Update a seller===================");

        System.out.println("\n================Delete a seller===================");
        System.out.print("Enter id for delete: ");
        int sellerId = scanner.nextInt();
        sellerDao.deleteById(sellerId);
        System.out.println("Deleted!");
        scanner.close();
        AllSellers = sellerDao.findAll();
        for (Seller seller : AllSellers) {
            System.out.println(seller);
        }
        System.out.println("================Delete a seller===================");

    }
}
