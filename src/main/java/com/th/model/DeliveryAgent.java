package com.th.model;

import java.sql.Timestamp;

public class DeliveryAgent {

    private int agentId;
    private String agentName;
    private String email;
    private String phone;
    private String password;
    private String vehicleType;
    private String vehicleNumber;
    private String status;
    private double rating;
    private Timestamp createdAt;
    private String employmentStatus;

    // Default Constructor
    public DeliveryAgent() {
    }

    // Parameterized Constructor
    public DeliveryAgent(int agentId, String agentName, String email,
                         String phone, String password,
                         String vehicleType, String vehicleNumber,
                         String status, double rating) {

        this.agentId = agentId;
        this.agentName = agentName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.status = status;
        this.rating = rating;
    }

    // Getters and Setters

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    @Override
    public String toString() {
        return "DeliveryAgent [agentId=" + agentId
                + ", agentName=" + agentName
                + ", email=" + email
                + ", phone=" + phone
                + ", vehicleType=" + vehicleType
                + ", vehicleNumber=" + vehicleNumber
                + ", status=" + status
                + ", rating=" + rating 
        + ", createdAt=" + createdAt
        + ", employmentStatus=" + employmentStatus+ "]";
    }
}