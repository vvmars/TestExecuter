package com;

import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @ResponseBody
    @RequestMapping("")
    public Map<String, Object> getAllUsers(){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Get All Tickets Implementation");
        return map;
    }
    @ResponseBody
    @RequestMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable("id") Integer id){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Get Ticket Implementation");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, Object> createUser(){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Create Ticket Implementation");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Map<String, Object> updateUser(){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Update Ticket Implementation");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Map<String, Object> deleteUser(){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Delete Ticket Implementation");
        return map;
    }
}
