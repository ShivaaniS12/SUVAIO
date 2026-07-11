package com.th.model;

import java.sql.Timestamp;

public class OrderTracking {

    private int trackingId;

    private int orderId;

    private String status;

    private String remarks;

    private String updatedBy;

    private Timestamp updatedTime;

    public OrderTracking() {
    }

    public OrderTracking(int trackingId,
                         int orderId,
                         String status,
                         String remarks,
                         String updatedBy,
                         Timestamp updatedTime) {

        this.trackingId = trackingId;
        this.orderId = orderId;
        this.status = status;
        this.remarks = remarks;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
    }

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "OrderTracking [trackingId=" + trackingId
                + ", orderId=" + orderId
                + ", status=" + status
                + ", remarks=" + remarks
                + ", updatedBy=" + updatedBy
                + ", updatedTime=" + updatedTime
                + "]";
    }
}