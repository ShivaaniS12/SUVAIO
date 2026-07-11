package com.th.dao;

import java.util.List;
import com.th.model.Admin;

public interface AdminDAO {

    void addAdmin(Admin admin);

    Admin getAdmin(int adminId);

    List<Admin> getAllAdmins();

    void updateAdmin(Admin admin);

    void deleteAdmin(int adminId);
}