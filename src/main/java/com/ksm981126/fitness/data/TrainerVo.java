package com.ksm981126.fitness.data;

import java.util.Date;

import lombok.Data;

@Data
public class TrainerVo {
    private Integer ti_seq;
    private Integer ti_pi_seq;
    private String ti_name;
    private Integer ti_gen;
    private String ti_birth;
    private String ti_phone_num;
    private String ti_address;
    private String ti_start_dt;
    private Integer ti_status;
    private Date ti_reg_dt;
    private Date ti_mod_dt;

    private String pt_name;

    public String makeHistoryStr(){
        return ti_pi_seq+"|"+ti_name+"|"+ti_birth+"|"+ti_gen+"|"+ti_phone_num+"|"+ti_address+"|"+ti_start_dt+"|"+ti_status;
    }
}
