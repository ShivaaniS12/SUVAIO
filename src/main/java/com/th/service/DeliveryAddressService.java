package com.th.service;

import java.util.List;

import com.th.model.DeliveryAddress;

public interface DeliveryAddressService {

	int addAddress(DeliveryAddress address);

    void updateAddress(DeliveryAddress address);

    void deleteAddress(int addressId);

    DeliveryAddress getAddress(int addressId);

    DeliveryAddress getDefaultAddress(int userId);

    List<DeliveryAddress> getAddressesByUser(int userId);

}