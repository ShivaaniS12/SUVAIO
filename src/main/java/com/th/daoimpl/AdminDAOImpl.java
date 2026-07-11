package com.th.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.th.Utility.DBConnection;
import com.th.dao.AdminDAO;
import com.th.model.Admin;

public class AdminDAOImpl implements AdminDAO {

    private Connection connection = DBConnection.getConnection();

    private static final String INSERT_QUERY =
            "INSERT INTO admin(admin_name,email,password,role) VALUES(?,?,?,?)";

    private static final String GET_ADMIN_QUERY =
            "SELECT * FROM admin WHERE admin_id=?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM admin";

    private static final String UPDATE_QUERY =
            "UPDATE admin SET admin_name=?,email=?,password=?,role=? WHERE admin_id=?";

    private static final String DELETE_QUERY =
            "DELETE FROM admin WHERE admin_id=?";

    

    @Override
    public void addAdmin(Admin admin) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(INSERT_QUERY)) {

            pstmt.setString(1, admin.getAdminName());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getPassword());
            pstmt.setString(4, admin.getRole());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin getAdmin(int adminId) {

        Admin admin = null;

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_ADMIN_QUERY)) {

            pstmt.setInt(1, adminId);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {

                admin = new Admin(
                        res.getInt("admin_id"),
                        res.getString("admin_name"),
                        res.getString("email"),
                        res.getString("password"),
                        res.getString("role")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    @Override
    public List<Admin> getAllAdmins() {

        List<Admin> admins = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_ALL_QUERY)) {

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {

                admins.add(
                        new Admin(
                                res.getInt("admin_id"),
                                res.getString("admin_name"),
                                res.getString("email"),
                                res.getString("password"),
                                res.getString("role")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    @Override
    public void updateAdmin(Admin admin) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(UPDATE_QUERY)) {

            pstmt.setString(1, admin.getAdminName());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getPassword());
            pstmt.setString(4, admin.getRole());
            pstmt.setInt(5, admin.getAdminId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAdmin(int adminId) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(DELETE_QUERY)) {

            pstmt.setInt(1, adminId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}