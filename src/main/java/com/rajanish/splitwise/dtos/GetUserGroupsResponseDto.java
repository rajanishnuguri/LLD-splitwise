package com.rajanish.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
public class GetUserGroupsResponseDto {
    private Long groupId;
    private String groupName;
    private String createdBy;
    private Date createdDate;
}
