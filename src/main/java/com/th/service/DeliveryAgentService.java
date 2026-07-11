package com.th.service;

import java.util.List;

import com.th.model.DeliveryAgent;

public interface DeliveryAgentService {

    void addDeliveryAgent(DeliveryAgent agent);

    DeliveryAgent getDeliveryAgent(int agentId);

    List<DeliveryAgent> getAllDeliveryAgents();

    void updateDeliveryAgent(DeliveryAgent agent);

    void deleteDeliveryAgent(int agentId);
    
    void activateAgent(int agentId);

    void deactivateAgent(int agentId);
}