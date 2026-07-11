package com.th.test;

import java.util.List;

import com.th.daoimpl.DeliveryAgentDAOImpl;
import com.th.model.DeliveryAgent;

public class DeliveryAgentTest {

    public static void main(String[] args) {

        DeliveryAgentDAOImpl dao = new DeliveryAgentDAOImpl();

        System.out.println("========== INSERT TEST ==========");

        DeliveryAgent agent = new DeliveryAgent(
                0,
                "Test Agent",
                "testagent@gmail.com",
                "9999999999",
                "test123",
                "Bike",
                "KA01ZZ9999",
                "Available",
                4.5
        );

        dao.addDeliveryAgent(agent);
        System.out.println("Agent Added Successfully");



        System.out.println("\n========== GET BY ID TEST ==========");

        DeliveryAgent fetchedAgent = dao.getDeliveryAgent(1);

        if (fetchedAgent != null) {
            System.out.println(fetchedAgent);
        } else {
            System.out.println("Agent Not Found");
        }



        System.out.println("\n========== GET ALL TEST ==========");

        List<DeliveryAgent> agents = dao.getAllDeliveryAgents();

        for (DeliveryAgent a : agents) {
            System.out.println(a);
        }



        System.out.println("\n========== UPDATE TEST ==========");

        DeliveryAgent updateAgent = dao.getDeliveryAgent(1);

        if (updateAgent != null) {

            updateAgent.setStatus("Busy");
            updateAgent.setRating(4.9);

            dao.updateDeliveryAgent(updateAgent);

            System.out.println("Agent Updated Successfully");

            System.out.println(dao.getDeliveryAgent(1));
        }



        System.out.println("\n========== DELETE TEST ==========");

        dao.deleteDeliveryAgent(21);

        System.out.println("Agent Deleted Successfully");



        System.out.println("\n========== FINAL DATA ==========");

        List<DeliveryAgent> finalList = dao.getAllDeliveryAgents();

        for (DeliveryAgent a : finalList) {
            System.out.println(a);
        }
    }
}