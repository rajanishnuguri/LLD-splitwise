package com.rajanish.splitwise.controllers;

import com.rajanish.splitwise.dtos.CreateGroupRequestDto;
import com.rajanish.splitwise.dtos.GetUserGroupsResponseDto;
import com.rajanish.splitwise.dtos.UserLoginRequestDto;
import com.rajanish.splitwise.enums.ResponseStatus;
import com.rajanish.splitwise.services.GroupService;
import com.rajanish.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    private GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService)
    {
        this.groupService=groupService;
    }
    @GetMapping("/groups/{userName}")
    public List<GetUserGroupsResponseDto> getUserGroups(@PathVariable("userName") String userName){
       // System.out.println(userName);
        return groupService.getUserGroups(userName);
    }
    @PostMapping("/group/create")
    public String createGroup(@RequestBody CreateGroupRequestDto request){
    if(groupService.createGroup(request.getName(),request.getCreatedBy(),request.getParticipants())==ResponseStatus.SUCCESS)
        return ResponseStatus.SUCCESS;
    return ResponseStatus.FAILURE;
    }
}
