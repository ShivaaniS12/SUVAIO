package com.th.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.th.Utility.DBConnection;
import com.th.dao.DeliveryAgentDAO;
import com.th.model.DeliveryAgent;

public class DeliveryAgentDAOImpl implements DeliveryAgentDAO{
	Connection connection = DBConnection.getConnection();

	private static final String INSERT_QUERY =
			"INSERT INTO delivery_agent(agent_name,email,phone,password,vehicle_type,vehicle_number,status,rating,employment_status) VALUES(?,?,?,?,?,?,?,?,?)";

    private static final String GET_AGENT_QUERY =
            "SELECT * FROM delivery_agent WHERE agent_id=?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM delivery_agent";

    private static final String UPDATE_QUERY =
            "UPDATE delivery_agent SET agent_name=?,email=?,phone=?,password=?,vehicle_type=?,vehicle_number=?,status=?,rating=? WHERE agent_id=?";

    private static final String DELETE_QUERY =
            "DELETE FROM delivery_agent WHERE agent_id=?";


    @Override
    public void addDeliveryAgent(DeliveryAgent agent) {

        try (PreparedStatement pstmt =
                connection.prepareStatement(INSERT_QUERY)) {

            pstmt.setString(1, agent.getAgentName());
            pstmt.setString(2, agent.getEmail());
            pstmt.setString(3, agent.getPhone());
            pstmt.setString(4, agent.getPassword());
            pstmt.setString(5, agent.getVehicleType());
            pstmt.setString(6, agent.getVehicleNumber());
            pstmt.setString(7, agent.getStatus());
            pstmt.setDouble(8,
                    agent.getRating());

            pstmt.setString(9,
                    agent.getEmploymentStatus());

            int rows =
                    pstmt.executeUpdate();

            System.out.println(
                    "Rows Inserted = "
                    + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DeliveryAgent getDeliveryAgent(int agentId) {

        DeliveryAgent agent = null;

        try (PreparedStatement pstmt =
                connection.prepareStatement(GET_AGENT_QUERY)) {

            pstmt.setInt(1, agentId);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {

                agent = new DeliveryAgent(
                        res.getInt("agent_id"),
                        res.getString("agent_name"),
                        res.getString("email"),
                        res.getString("phone"),
                        res.getString("password"),
                        res.getString("vehicle_type"),
                        res.getString("vehicle_number"),
                        res.getString("status"),
                        res.getDouble("rating")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agent;
    }

    @Override
    public List<DeliveryAgent> getAllDeliveryAgents() {

        List<DeliveryAgent> agents = new ArrayList<>();

        try (PreparedStatement pstmt =
                connection.prepareStatement(GET_ALL_QUERY)) {

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {

            	DeliveryAgent agent = new DeliveryAgent(
            	        res.getInt("agent_id"),
            	        res.getString("agent_name"),
            	        res.getString("email"),
            	        res.getString("phone"),
            	        res.getString("password"),
            	        res.getString("vehicle_type"),
            	        res.getString("vehicle_number"),
            	        res.getString("status"),
            	        res.getDouble("rating")
            	);
            	
            	
            	agent.setCreatedAt(
            	        res.getTimestamp("created_at"));

            	agent.setEmploymentStatus(
            	        res.getString("employment_status"));

            	agents.add(agent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agents;
    }

    @Override
    public void updateDeliveryAgent(DeliveryAgent agent) {

        try (PreparedStatement pstmt =
                connection.prepareStatement(UPDATE_QUERY)) {

            pstmt.setString(1, agent.getAgentName());
            pstmt.setString(2, agent.getEmail());
            pstmt.setString(3, agent.getPhone());
            pstmt.setString(4, agent.getPassword());
            pstmt.setString(5, agent.getVehicleType());
            pstmt.setString(6, agent.getVehicleNumber());
            pstmt.setString(7, agent.getStatus());
            pstmt.setDouble(8, agent.getRating());
            pstmt.setInt(9, agent.getAgentId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDeliveryAgent(int agentId) {

        try (PreparedStatement pstmt =
                connection.prepareStatement(DELETE_QUERY)) {

            pstmt.setInt(1, agentId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void activateAgent(int agentId) {

        String query =
            "UPDATE delivery_agent " +
            "SET employment_status='ACTIVE' " +
            "WHERE agent_id=?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, agentId);

            int rows = ps.executeUpdate();

            System.out.println(
                    "Rows Updated = "
                    + rows);

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void deactivateAgent(int agentId) {

        String query =
            "UPDATE delivery_agent " +
            "SET employment_status='INACTIVE' " +
            "WHERE agent_id=?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, agentId);

            int rows = ps.executeUpdate();

            System.out.println(
                    "Rows Updated = "
                    + rows);
        } catch(Exception e) {

            e.printStackTrace();
        }
    }
    
    @Override
    public DeliveryAgent validateAgent(
            String email,
            String password) {
    	System.out.println(connection);
        String query =
            "SELECT * FROM delivery_agent " +
            "WHERE email=? " +
            "AND password=? " +
            "AND employment_status='ACTIVE'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();
            
            System.out.println("Query Executed");

            if(rs.next()) {
            	
            	 System.out.println("Agent Found");

                DeliveryAgent agent =
                        new DeliveryAgent();

                agent.setAgentId(
                        rs.getInt("agent_id"));

                agent.setAgentName(
                        rs.getString("agent_name"));

                agent.setEmail(
                        rs.getString("email"));

                agent.setPhone(
                        rs.getString("phone"));

                agent.setVehicleType(
                        rs.getString("vehicle_type"));

                agent.setVehicleNumber(
                        rs.getString("vehicle_number"));

                agent.setStatus(
                        rs.getString("status"));

                agent.setEmploymentStatus(
                        rs.getString(
                                "employment_status"));

                return agent;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}