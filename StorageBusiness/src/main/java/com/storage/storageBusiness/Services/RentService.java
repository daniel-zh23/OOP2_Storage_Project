package com.storage.storageBusiness.Services;

import com.storage.storagedb.DAO.RenterDAO;
import com.storage.storagedb.DAO.SaleDAO;

public class RentService {
    private final RenterDAO _renterDao;
    private final SaleDAO _saleDao;
    public RentService()
    {
        _renterDao= new RenterDAO();
        _saleDao=new SaleDAO();
    }

}
