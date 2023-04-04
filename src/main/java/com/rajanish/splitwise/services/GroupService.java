package com.rajanish.splitwise.services;

import com.rajanish.splitwise.dtos.GetUserGroupsResponseDto;
import com.rajanish.splitwise.enums.ResponseStatus;
import com.rajanish.splitwise.models.Expenses;
import com.rajanish.splitwise.models.Groups;
import com.rajanish.splitwise.models.Users;
import com.rajanish.splitwise.repository.GroupRepository;
import com.rajanish.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    @Autowired
    public GroupService(UserRepository userRepository,GroupRepository groupRepository){
        this.groupRepository=groupRepository;
        this.userRepository=userRepository;
    }
    public List<GetUserGroupsResponseDto> getUserGroups(String userName){
        Users user=userRepository.findUsersByUserName(userName);
        //System.out.println(user.toString());
        List<Groups> groups=groupRepository.findByParticipantsEquals(user);
        List<GetUserGroupsResponseDto> response= new ArrayList<>();
        for(Groups group:groups){
            GetUserGroupsResponseDto getUserGroupsResponseDto=new GetUserGroupsResponseDto();
            getUserGroupsResponseDto.setGroupName(group.getName());
            getUserGroupsResponseDto.setCreatedBy(group.getCreatedBy().getUserName());
            getUserGroupsResponseDto.setCreatedDate(group.getCreatedDate());
            getUserGroupsResponseDto.setGroupId(group.getId());
            response.add(getUserGroupsResponseDto);
        }
        return response;
    }
    public String createGroup(String groupName,String CreatedBy, List<String> users){
        Groups group=new Groups();
        group.setName(groupName);
        group.setCreatedBy(userRepository.findUsersByUserName(CreatedBy));
        List<Users> participants=new ArrayList<>();
        group.setParticipants(participants);
        for(String userName:users){
            group.getParticipants().add(userRepository.findUsersByUserName(userName));
        }
        List<Users> admin=new ArrayList<>();
        admin.add(userRepository.findUsersByUserName(CreatedBy));
        group.setAdmins(admin);
        groupRepository.save(group);
        return ResponseStatus.SUCCESS;
    }
    public List<Expenses> getExpensesForGroup(Long groupId){
        Groups group=groupRepository.findByIdEquals(groupId);
        return group.getExpenses();
    }
    public Groups save(Groups groups){
        return groupRepository.save(groups);
    }
    public Groups findGroup(Long groupId){
        return groupRepository.findByIdEquals(groupId);
    }
}
