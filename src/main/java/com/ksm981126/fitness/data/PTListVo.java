package com.ksm981126.fitness.data;

import lombok.Data;

@Data
public class PTListVo {
    private Integer pi_seq;
    private String pi_name;
    private Integer pi_ti_seq;
    private String pi_ti_name;
    private Integer pi_status;
    private String pi_time;
    private String pi_start_dt;
    private String pi_end_dt;
}
