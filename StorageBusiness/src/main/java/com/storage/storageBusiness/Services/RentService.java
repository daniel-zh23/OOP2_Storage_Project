package com.storage.storageBusiness.Services;

import com.storage.storageBusiness.Models.RenterViewModel;
import com.storage.storageBusiness.Models.SaleViewModel;
import com.storage.storagedb.DAO.RenterDAO;
import com.storage.storagedb.DAO.SaleDAO;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Owner;
import com.storage.storagedb.Entity.Renter;
import com.storage.storagedb.Entity.Sales;
import com.storage.storagedb.Entity.Agent;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RentService {
    private final RenterDAO _renterDao;
    private final SaleDAO _saleDao;
    private final UserDAO _userDao;
    public RentService()
    {
        _renterDao= new RenterDAO();
        _saleDao=new SaleDAO();
        _userDao = new UserDAO();
    }
    public List<RenterViewModel> getAllRenters()
    {
        _renterDao.openSession();
        List<RenterViewModel> renters = _renterDao.getAll().map(r->new RenterViewModel(r.getId(),r.getFirstName(),r.getLastName(), r.getPhone())).toList();
        _renterDao.close();
        return renters;
    }
    public void createRenter(String fName, String lName, String phone)
    {
        _renterDao.openSession();
        _renterDao.save(new Renter(fName, lName, phone));
        _renterDao.close();
    }
    public void createSale(Double price, Integer duration, Integer storageId, Integer agentId, Integer renterId)
    {
        try {
            _saleDao.openSession();
            _saleDao.save(new Sales(price, duration, LocalDate.now(), storageId, agentId, renterId));
            _saleDao.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public List<SaleViewModel> fetchByAgentId(int id)
    {
        _saleDao.openSession();
        List<SaleViewModel> sales=_saleDao.getAll()
                .filter(s->s.getAgentId()==id)
                .map(s->new SaleViewModel(s.getId(),s.getPrice(),s.getDuration(),s.getDateOfSale(),
                        s.getStorageId(),s.getAgentId(),s.getRenterId(),
                        String.format("Address: %s",s.getStorage().getAddress()),
                        String.format("Name: %s %s",s.getAgent().getFirstName(),s.getAgent().getLastName()),
                        String.format("Name: %s %s",s.getRenter().getFirstName(),s.getRenter().getLastName()))
                )
                .toList();
        _saleDao.close();
        return sales;
    }
    public List<SaleViewModel> fetchByStorageId(int id)
    {
        _saleDao.openSession();
        List<SaleViewModel> sales=_saleDao.getAll()
                .filter(s->s.getStorageId()==id)
                .map(s->new SaleViewModel(s.getId(),s.getPrice(),s.getDuration(),s.getDateOfSale(),
                        s.getStorageId(),s.getAgentId(),s.getRenterId(),
                        String.format("Address: %s",s.getStorage().getAddress()),
                        String.format("Name: %s %s",s.getAgent().getFirstName(),s.getAgent().getLastName()),
                        String.format("Name: %s %s",s.getRenter().getFirstName(),s.getRenter().getLastName()))
                )
                .toList();
        _saleDao.close();
        return sales;
    }


    public boolean checkPhone(String phone) {
        _renterDao.openSession();
        var result = _renterDao.getAll().map(r -> r.getPhone()).anyMatch(u -> u.equals(phone));
        _renterDao.close();
        return result;
    }
}
