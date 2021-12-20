package com.ksm981126.fitness.data;

import java.util.Date;

import lombok.Data;

@Data
public class MemberHistoryVo {
    private Integer mh_seq;
    private String mh_type;
    private String mh_content;
    private Date mh_reg_dt;
    private Integer mh_mi_seq;
}
