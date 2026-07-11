package com.th.serviceimpl;

import java.util.List;

import com.th.dao.DeliveryAddressDAO;
import com.th.daoimpl.DeliveryAddressDAOImpl;
import com.th.model.DeliveryAddress;
import com.th.service.DeliveryAddressService;

public class DeliveryAddressServiceImpl
implements DeliveryAddressService{

    private DeliveryAddressDAO dao =
            new DeliveryAddressDAOImpl();

    @Override
    public int addAddress(
            DeliveryAddress address){

        return dao.addAddress(address);

    }

    @Override
    public void updateAddress(
            DeliveryAddress address){

        dao.updateAddress(address);

    }

    @Override
    public void deleteAddress(
            int addressId){

        dao.deleteAddress(addressId);

    }

    @Override
    public DeliveryAddress getAddress(
            int addressId){

        return dao.getAddress(addressId);

    }

    @Override
    public DeliveryAddress getDefaultAddress(
            int userId){

        return dao.getDefaultAddress(userId);

    }

    @Override
    public List<DeliveryAddress> getAddressesByUser(
            int userId){

        return dao.getAddressesByUser(userId);

    }

}