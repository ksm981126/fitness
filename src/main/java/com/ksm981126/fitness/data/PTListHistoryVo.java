package com.ksm981126.fitness.data;

import java.util.Date;

import lombok.Data;

@Data
public class PTListHistoryVo {
    private Integer pih_seq;
    private Integer pih_pi_seq;
    private String pih_type;
    private String pih_content;
    private Date pih_reg_dt;
}
