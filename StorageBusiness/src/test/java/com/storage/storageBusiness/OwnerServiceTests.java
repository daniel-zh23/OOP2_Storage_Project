package com.storage.storageBusiness;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OwnerServiceTests extends BaseTests{

    @Test
    public void fetchNotificationsByUserId_should_return_only_for_user(){
        var result = _ownerService.getOwners();

        Assertions.assertEquals(2, result.size());
    }
}
