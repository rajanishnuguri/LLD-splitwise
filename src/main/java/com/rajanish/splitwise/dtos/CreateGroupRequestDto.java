package com.rajanish.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class CreateGroupRequestDto {
    private String name;
    private String createdBy;
    private List<String> participants;
}
