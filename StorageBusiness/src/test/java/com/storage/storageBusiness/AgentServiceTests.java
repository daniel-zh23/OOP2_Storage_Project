package com.storage.storageBusiness;

import com.storage.storageBusiness.Models.AgentViewModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
public class AgentServiceTests extends BaseTests {

    @Test
    public void getAgents_should_filter_agents_only(){
        var result = _agentService.getAgents();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void getAgents_should_filter_active_agents_only(){
        var result = _agentService.getAgents();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void getAgents_should_map_data_correctly(){
        var result = _agentService.getAgents();

        Assertions.assertEquals(1, result.get(0).getId());
        Assertions.assertEquals(2, result.get(1).getId());
    }

    @Test
    public void createAgent_should_add_agent_to_db(){
        _agentService.createAgent("test", "TEST", "test10", "test@abv.bg", "+1234","Google", 123.5);

        _userDao.openSession();
        Assertions.assertEquals(5, _userDao.getAll().count());
    }

    @Test
    public void updateAgents_should_update_collection(){
        var list = new ArrayList<AgentViewModel>(){
            {
                add(new AgentViewModel(1, "changed", "TEST", "+1234", "agent1@abv.bg", 123.5,"Google", 123.5));
                add(new AgentViewModel(2, "test", "TEST", "+1235", "agent2@abv.bg", 123.5,"Google", 123.5));
            }
        };

        _agentService.updateAgents(list);

        _userDao.openSession();
        Assertions.assertEquals("changed", _userDao.getAll().toList().get(0).getFirstName());
        Assertions.assertEquals("+1235", _userDao.getAll().toList().get(1).getPhone());
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
