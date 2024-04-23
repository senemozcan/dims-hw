package com.example.dimshw.DTOs;
import com.example.dimshw.Model.Employee;
import com.example.dimshw.Model.Task;
import lombok.*;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Taskerview {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Long taskId;
    private Long employeeId;
    private String taskName;
    private String employeeName;
}
