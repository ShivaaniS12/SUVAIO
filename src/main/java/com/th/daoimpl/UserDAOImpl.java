package com.th.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.th.Utility.DBConnection;
import com.th.dao.UserDAO;
import com.th.model.User;

public class UserDAOImpl implements UserDAO{
	private static final Connection con = DBConnection.getConnection();
	private static final String INSERT_QUERY ="INSERT INTO user(Name,Email,Mobile,Password,Address,Role)"
			+ "VALUES(?,?,?,?,?,?)";
	private static final String GET_USER = "SELECT * FROM user WHERE User_Id = ?";
	private static final String GET_ALL_USER = "SELECT * FROM user";
	private static final String DELETE_USER = "DELETE FROM user WHERE User_Id  = ? ";
	private static final String UPDATE_USER="UPDATE user\r\n"
			+ "SET\r\n"
			+ "name=?,\r\n"
			+ "email=?,\r\n"
			+ "mobile=?,\r\n"
			+ "password=?,\r\n"
			+ "address=?,\r\n"
			+ "role=?\r\n"
			+ "WHERE user_id=?";
	private static final String LOGIN_QUERY = "SELECT * FROM user WHERE email=? AND password=? AND status='ACTIVE'";
	private static final String DEACTIVATE_QUERY =
	        "UPDATE user SET status='INACTIVE' WHERE user_id=?";
	private static final String CHECK_EMAIL =
	        "SELECT COUNT(*) FROM user WHERE Email=?";
	private static final String UPDATE_PROFILE = "UPDATE user SET Name=?,Mobile=?,Address=? WHERE user_id=?";
	
	
	
	PreparedStatement pstmt = null;	

	@Override
	public boolean addUser(User u) {

	    try {

	        

	        PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY);

	        pstmt.setString(1, u.getUsername());
	        pstmt.setString(2, u.getEmail());
	        pstmt.setString(3, "");
	        pstmt.setString(4, u.getPassword());
	        pstmt.setString(5, u.getAddress());
	        pstmt.setString(6, u.getRole());

	        return pstmt.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	@Override
	public User getUser(int User_Id) {
		
		Connection connection = DBConnection.getConnection();
		User u = null;
		
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(GET_USER);
			pstmt.setInt(1, User_Id);
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				 u = getUserByResulySet(res);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return u;
	}

	@Override
	public void updateUser(User u) {
		
		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_USER);
			
			pstmt.setString(1,u.getUsername());
			pstmt.setString(2,u.getEmail());
			pstmt.setString(3,u.getMobile());
			pstmt.setString(4,u.getPassword());
			pstmt.setString(5,u.getAddress());
			pstmt.setString(6,u.getRole());
			pstmt.setInt(7,u.getUserID());
			
			int i=pstmt.executeUpdate();
			System.out.println(i);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(int userID) {
		// TODO Auto-generated method stub
		 try (PreparedStatement pstmt =
		            con.prepareStatement(
		                    DELETE_USER)) {

		        pstmt.setInt(1, userID);

		        pstmt.executeUpdate();

		    }
		    catch (SQLException e) {

		        e.printStackTrace();
		    }
	}

	@Override
	public List<User> getAllUsers() {

	    List<User> users = new ArrayList<>();

	    String query = "SELECT * FROM user";

	    try {

	        Connection con =
	                DBConnection.getConnection();

	        PreparedStatement ps =
	                con.prepareStatement(query);

	        ResultSet rs =
	                ps.executeQuery();

	        while(rs.next()) {

	            User user = new User();

	            user.setUserID(
	                    rs.getInt("User_id"));

	            user.setUsername(
	                    rs.getString("Name"));
	            
	            user.setMobile(
	                    rs.getString("mobile"));

	            user.setEmail(
	                    rs.getString("Email"));

	            user.setAddress(
	                    rs.getString("Address"));

	            user.setRole(
	                    rs.getString("Role"));

	            user.setStatus(
	                    rs.getString("status"));
	            

	            users.add(user);
	        }

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return users;
	}

	
	private static User getUserByResulySet(ResultSet res)
	        throws SQLException {

	    User u = new User();

	    u.setUserID(
	            res.getInt("User_Id"));

	    u.setUsername(
	            res.getString("Name"));
	    
	    u.setMobile(
	            res.getString("mobile"));

	    u.setEmail(
	            res.getString("Email"));

	    u.setPassword(
	            res.getString("Password"));

	    u.setAddress(
	            res.getString("Address"));

	    u.setRole(
	            res.getString("Role"));

	    u.setCreatedDate(
	            res.getTimestamp("createdDate"));

	    u.setLastLoginDate(
	            res.getTimestamp("lastLoginDate"));

	    u.setStatus(
	            res.getString("status"));

	    return u;
	}

@Override
public User validateUser(String email, String password) {
	// TODO Auto-generated method stub
	
	try (PreparedStatement pstmt = con.prepareStatement(
	                 LOGIN_QUERY)) {

	    pstmt.setString(1, email);

	    pstmt.setString(2, password);

	    ResultSet rs =
	            pstmt.executeQuery();

	    if(rs.next()) {

	        return extractUser(rs);
	    }

	}
	catch(Exception e) {

	    e.printStackTrace();
	}


	return null;
}

private User extractUser(ResultSet rs)
        throws SQLException {

    User user = new User();

    user.setUserID(
            rs.getInt("user_id"));

    user.setUsername(
            rs.getString("name"));

    user.setEmail(
            rs.getString("email"));

    user.setPassword(
            rs.getString("password"));

    user.setAddress(
            rs.getString("address"));

    user.setRole(
            rs.getString("role"));

    user.setCreatedDate(
            rs.getTimestamp("createdDate"));

    user.setLastLoginDate(
            rs.getTimestamp("lastLoginDate"));
    
    user.setStatus(
            rs.getString("status"));

    return user;
}

@Override
public void deactivateUser(int userId) {

    try (PreparedStatement pstmt =
            con.prepareStatement(
                    DEACTIVATE_QUERY)) {

        pstmt.setInt(1, userId);

        pstmt.executeUpdate();

    }
    catch (SQLException e) {

        e.printStackTrace();
    }
}

@Override
public boolean updateUserStatus(
        int userId,
        String status) {

    String query =
            "UPDATE user SET status=? WHERE User_id=?";

    try {

        Connection con =
                DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setString(1, status);
        ps.setInt(2, userId);

        int rows =
                ps.executeUpdate();

        return rows > 0;

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
}

@Override
public int getTotalUsers() {

    String query =
            "SELECT COUNT(*) FROM user";

    try {

        Connection con =
                DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(query);

        ResultSet rs =
                ps.executeQuery();
        

        if(rs.next()) {

            return rs.getInt(1);
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    return 0;
}

@Override
public List<User> getAllDeliveryAgents() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean isEmailExists(String email) {

    try {

        PreparedStatement ps =
                con.prepareStatement(CHECK_EMAIL);

        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){

            return rs.getInt(1) > 0;

        }

    }
    catch(Exception e){

        e.printStackTrace();

    }

    return false;

}

@Override
public User getUserById(int userId) {

    User user = null;

    try {

        PreparedStatement ps =
                con.prepareStatement(GET_USER);

        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {

            user = extractUser(rs);

        }

    }
    catch(Exception e) {

        e.printStackTrace();

    }

    return user;
}
@Override
public boolean updateProfile(User user) {

    try {

        PreparedStatement ps =
                con.prepareStatement(UPDATE_PROFILE);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getMobile());
        ps.setString(3, user.getAddress());
        ps.setInt(4, user.getUserID());

        return ps.executeUpdate() > 0;

    }
    catch(Exception e){

        e.printStackTrace();

    }

    return false;

}

}