package com.ksm981126.fitness.data;

import java.util.Date;

import lombok.Data;

@Data
public class PTListVo {
    private Integer pi_seq;
    private String pi_name;
    private Integer pi_status;
    private String pi_time;
    private String pi_start_dt;
    private String pi_end_dt;
    private Date pi_reg_dt;
    private Date pi_mod_dt;
}
