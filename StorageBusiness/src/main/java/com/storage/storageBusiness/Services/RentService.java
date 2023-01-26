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
        List<RenterViewModel> renters = _renterDao.getAll().map(r->new RenterViewModel(r.getId(),r.getFirstName(),r.getLastName())).toList();
        _renterDao.close();
        return renters;
    }
    public void createRenter(RenterViewModel renter)
    {
        _renterDao.openSession();
        _renterDao.save(new Renter(renter.getFirstName(),renter.getLastName()));
        _renterDao.close();
    }
    public void createSale(SaleViewModel sale)
    {
        try {
            _saleDao.openSession();
            _saleDao.save(new Sales(sale.getPrice(), sale.getDuration(), new SimpleDateFormat("dd/MM/yyyy").parse(sale.getDate()),sale.getStorageId(),sale.getAgentId(),sale.getRenterId()));
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


}
