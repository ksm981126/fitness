package com.ksm981126.fitness.data;

import java.util.Date;

import lombok.Data;

@Data
public class TrainerHistoryVo {
    private Integer tih_seq;
    private Integer tih_ti_seq;
    private String tih_type;
    private String tih_content;
    private Date tih_reg_dt;
}
