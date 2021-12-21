package com.ksm981126.fitness.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ksm981126.fitness.mapper.DashboardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired DashboardMapper mapper;

    public Map<String,Object> getCounts(){
        List<Integer> memberCntList =new ArrayList<Integer>();
        memberCntList.add(mapper.getTotalMemberCnt());
        memberCntList.add(mapper.getPToptionMemberCnt());
        memberCntList.add(mapper.getLockerMemberCnt());
        
        List<Integer> trainerCntList =new ArrayList<Integer>();
        trainerCntList.add(mapper.getTotalTrainerCnt());
        trainerCntList.add(mapper.getWorkTrainerCnt());
        trainerCntList.add(mapper.getDayoffTrainerCnt());

        List<Integer> ptCntList =new ArrayList<Integer>();
        ptCntList.add(mapper.getTotalPTCnt());
        ptCntList.add(mapper.getActivePTCnt());
        ptCntList.add(mapper.getDeactivePTCnt());
        ptCntList.add(mapper.getFinishPTCnt());

        List<Integer> ptmemberCntList =new ArrayList<Integer>();
        ptmemberCntList.add(mapper.getTotalPTmemberCnt());
        // ptmemberCntList.add(mapper.getActivePTmemberCnt());
        // ptmemberCntList.add(mapper.getDeactivePTmemberCnt());
        // ptmemberCntList.add(mapper.getFinishPTmemberCnt());

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("member", memberCntList);
        map.put("trainer", trainerCntList);
        map.put("ptlist", ptCntList);
        map.put("ptmember", ptmemberCntList);
        return map;
    }

    public Map<String,Object> getUpdateDate(){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();

        resultMap.put("member", mapper.getMemberUpdateDate());


        resultMap.put("ptlist", mapper.getPTListUpdateDate());

        
        return resultMap;
    }
}
