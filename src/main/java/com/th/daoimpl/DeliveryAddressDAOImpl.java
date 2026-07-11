package com.th.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.th.Utility.DBConnection;
import com.th.dao.DeliveryAddressDAO;
import com.th.model.DeliveryAddress;

public class DeliveryAddressDAOImpl implements DeliveryAddressDAO {

    private static final Connection connection =
            DBConnection.getConnection();

    private static final String INSERT =
    		"INSERT INTO delivery_address\r\n"
    		+ "(user_id,\r\n"
    		+ "name,\r\n"
    		+ "mobile,\r\n"
    		+ "house_no,\r\n"
    		+ "landmark,\r\n"
    		+ "city,\r\n"
    		+ "state,\r\n"
    		+ "pincode,\r\n"
    		+ "latitude,\r\n"
    		+ "longitude,\r\n"
    		+ "is_default,\r\n"
    		+ "address_type)\r\n"
    		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE =
    		"UPDATE delivery_address SET " +
    		"name=?," +
    		"mobile=?," +
    		"house_no=?," +
    		"landmark=?," +
    		"city=?," +
    		"state=?," +
    		"pincode=?," +
    		"latitude=?," +
    		"longitude=?," +
    		"is_default=?," +
    		"address_type=? " +
    		"WHERE address_id=?";

    private static final String DELETE =
            "DELETE FROM delivery_address WHERE address_id=?";

    private static final String GET =
            "SELECT * FROM delivery_address WHERE address_id=?";

    private static final String GET_DEFAULT =
            "SELECT * FROM delivery_address WHERE user_id=? AND is_default=TRUE LIMIT 1";

    private static final String GET_ALL =
            "SELECT * FROM delivery_address WHERE user_id=? ORDER BY is_default DESC,address_id DESC";

    @Override
    public int addAddress(
            DeliveryAddress address){

        try {

        	PreparedStatement ps =
        			connection.prepareStatement(
        			INSERT,
        			Statement.RETURN_GENERATED_KEYS);

        	ps.setInt(1, address.getUserId());

        	ps.setString(2, address.getName());

        	ps.setString(3, address.getMobile());

        	ps.setString(4, address.getHouseNo());

        	ps.setString(5, address.getLandmark());

        	ps.setString(6, address.getCity());

        	ps.setString(7, address.getState());

        	ps.setString(8, address.getPincode());

        	ps.setDouble(9, address.getLatitude());

        	ps.setDouble(10, address.getLongitude());

        	ps.setBoolean(11, address.isDefault());

        	ps.setString(12, address.getAddressType());
            
            

            ps.executeUpdate();
            ResultSet rs =
            		ps.getGeneratedKeys();

            		if(rs.next()){

            		    return rs.getInt(1);

            		}

        }catch(Exception e){

            e.printStackTrace();

        }

        return 0;
    }

    @Override
    public void updateAddress(DeliveryAddress address) {

        try {

            PreparedStatement ps =
                    connection.prepareStatement(UPDATE);

            ps.setString(1, address.getName());
            ps.setString(2, address.getMobile());
            ps.setString(3, address.getHouseNo());
            ps.setString(4, address.getLandmark());
            ps.setString(5, address.getCity());
            ps.setString(6, address.getState());
            ps.setString(7, address.getPincode());
            ps.setDouble(8, address.getLatitude());
            ps.setDouble(9, address.getLongitude());
            ps.setBoolean(10, address.isDefault());
            ps.setInt(11, address.getAddressId());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public void deleteAddress(int addressId) {

        try {

            PreparedStatement ps =
                    connection.prepareStatement(DELETE);

            ps.setInt(1, addressId);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public DeliveryAddress getAddress(int addressId) {

        try {

            PreparedStatement ps =
                    connection.prepareStatement(GET);

            ps.setInt(1, addressId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return extractAddress(rs);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public DeliveryAddress getDefaultAddress(int userId) {

        try {

            PreparedStatement ps =
                    connection.prepareStatement(GET_DEFAULT);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return extractAddress(rs);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<DeliveryAddress> getAddressesByUser(int userId) {

        List<DeliveryAddress> list =
                new ArrayList<>();

        try {

            PreparedStatement ps =
                    connection.prepareStatement(GET_ALL);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(extractAddress(rs));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;
    }

    private DeliveryAddress extractAddress(ResultSet rs)
            throws SQLException {

        DeliveryAddress address =
                new DeliveryAddress();

        address.setAddressId(
                rs.getInt("address_id"));

        address.setUserId(
                rs.getInt("user_id"));

        address.setName(
                rs.getString("name"));

        address.setMobile(
                rs.getString("mobile"));

        address.setHouseNo(
                rs.getString("house_no"));

        address.setLandmark(
                rs.getString("landmark"));

        address.setCity(
                rs.getString("city"));

        address.setState(
                rs.getString("state"));

        address.setPincode(
                rs.getString("pincode"));

        address.setLatitude(
                rs.getDouble("latitude"));

        address.setLongitude(
                rs.getDouble("longitude"));

        address.setDefault(
                rs.getBoolean("is_default"));

        address.setCreatedDate(
                rs.getTimestamp("created_date"));
        
        address.setAddressType(
                rs.getString("address_type"));

        return address;

    }

}