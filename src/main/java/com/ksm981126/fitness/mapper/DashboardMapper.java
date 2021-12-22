package com.ksm981126.fitness.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashboardMapper {
    public Integer getTotalMemberCnt();
    public Integer getPToptionMemberCnt();
    public Integer getLockerMemberCnt();

    public Integer getTotalTrainerCnt();
    public Integer getWorkTrainerCnt();
    public Integer getDayoffTrainerCnt();
    public Integer getBreakTrainerCnt();

    public Integer getTotalPTCnt();
    public Integer getActivePTCnt();
    public Integer getDeactivePTCnt();
    public Integer getFinishPTCnt();

    public Integer getTotalPTmemberCnt();
    // public Integer getActivePTmemberCnt();
    // public Integer getDeactivePTmemberCnt();
    // public Integer getFinishPTmemberCnt();

    public Date getMemberUpdateDate();
    public Date getTrainerUpdateDate();
    public Date getPTListUpdateDate();
}
