package com.bank.tsehay.wikitsehay.dto.department;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateDepartmentResponse {
    private Long id;
    private String name;
    private String description;
}
