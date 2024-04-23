package com.example.dimshw.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskerCreateDTO {
    Integer days;
    String date;
    Long taskId;
    Long employeeId;
}
