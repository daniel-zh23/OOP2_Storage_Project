package com.storage.storageBusiness;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.OwnerViewModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class OwnerServiceTests extends BaseTests{

    @Test
    public void getOwners_should_return_correct_amount(){
        var result = _ownerService.getOwners();

        Assertions.assertEquals(4, result.size());
    }

    @Test
    public void getOwners_should_map_data_correctly(){
        var result = _ownerService.getOwners();

        Assertions.assertEquals(4, result.get(0).getId());
        Assertions.assertEquals(5, result.get(1).getId());
    }

    @Test
    public void createOwner_should_map_and_save_in_db(){
        _ownerService.createOwner("test", "TEST", "owner4", "+1234", "owner4@abv.bg");
        _userDao.openSession();

        Assertions.assertEquals(4, _ownerService.getOwners().size());
    }

    @Test
    public void createOwner_should_hash_password(){
        var expectedPass = "c2ebb28e362632376e57b2986cdf095bb18372171d0faafd4749a81989c60e95";

        _ownerService.createOwner("test", "TEST", "owner5", "+1234", "owner4@abv.bg");
        _userDao.openSession();

        Assertions.assertEquals(expectedPass, _userDao.getByUsername("owner5").getPassword());
    }

    @Test
    public void updateOwners_should_update_entities(){
        var list = new ArrayList<OwnerViewModel>(){
            {
                add(new OwnerViewModel(4, "changed", "TEST", "+1234", "owner1@abv.bg"));
                add(new OwnerViewModel(5, "test", "TEST", "+1235", "owner2@abv.bg"));
            }
        };

        _ownerService.updateOwners(list);
        _userDao.openSession();
        Assertions.assertEquals("changed", _ownerService.getOwners().get(0).getFirstName());
        Assertions.assertEquals("+1235", _ownerService.getOwners().get(1).getPhone());
    }

    @AfterEach
    public void tear(){
        try{
            _userDao.close();
        } catch (Exception e){
            System.out.println("Already closed.");
        }
    }
}
