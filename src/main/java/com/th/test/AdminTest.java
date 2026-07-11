package com.th.test;

import java.util.List;

import com.th.daoimpl.AdminDAOImpl;
import com.th.model.Admin;

public class AdminTest {

    public static void main(String[] args) {

        AdminDAOImpl dao = new AdminDAOImpl();

        // ==========================
        // INSERT TEST
        // ==========================
        System.out.println("===== INSERT TEST =====");

        Admin admin = new Admin(
                0,
                "Test Admin",
                "testadmin@foodapp.com",
                "test123",
                "ADMIN"
        );

        dao.addAdmin(admin);

        System.out.println("Admin Added Successfully");


        // ==========================
        // GET BY ID TEST
        // ==========================
        System.out.println("\n===== GET ADMIN BY ID =====");

        Admin fetchedAdmin = dao.getAdmin(1);

        if (fetchedAdmin != null) {
            System.out.println(fetchedAdmin);
        } else {
            System.out.println("Admin Not Found");
        }


        // ==========================
        // GET ALL TEST
        // ==========================
        System.out.println("\n===== GET ALL ADMINS =====");

        List<Admin> admins = dao.getAllAdmins();

        for (Admin a : admins) {
            System.out.println(a);
        }


        // ==========================
        // UPDATE TEST
        // ==========================
        System.out.println("\n===== UPDATE TEST =====");

        Admin updateAdmin = dao.getAdmin(1);

        if (updateAdmin != null) {

            updateAdmin.setAdminName("Updated Super Admin");
            updateAdmin.setRole("SUPER_ADMIN");

            dao.updateAdmin(updateAdmin);

            System.out.println("Admin Updated Successfully");

            System.out.println(dao.getAdmin(1));
        }


        // ==========================
        // DELETE TEST
        // ==========================
        System.out.println("\n===== DELETE TEST =====");

        dao.deleteAdmin(11);

        System.out.println("Admin Deleted Successfully");


        // ==========================
        // FINAL DATA
        // ==========================
        System.out.println("\n===== FINAL ADMIN LIST =====");

        List<Admin> finalAdmins = dao.getAllAdmins();

        for (Admin a : finalAdmins) {
            System.out.println(a);
        }
    }
}