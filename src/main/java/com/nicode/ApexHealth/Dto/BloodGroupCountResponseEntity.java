package com.nicode.ApexHealth.Dto;

import com.nicode.ApexHealth.Entity.Type.bloodgroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodGroupCountResponseEntity {

    private bloodgroup bloodgroup;
    private Long count;

}
