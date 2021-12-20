package com.ksm981126.fitness.data;

import lombok.Data;

@Data
public class MemberVo {
    private Integer mi_seq;
    private String mi_name;
    private String mi_birth;
    private Integer mi_gen;
    private String mi_phone_num;
    private String mi_address;
    private String mi_start_dt;
    private String mi_end_dt;
    private Integer mi_pt_option;
    private Integer mi_locker_option;
}
