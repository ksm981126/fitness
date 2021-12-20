package com.ksm981126.fitness.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ksm981126.fitness.data.PTListVo;
import com.ksm981126.fitness.mapper.PTListMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PTListService {
    @Autowired PTListMapper mapper;

    public Map<String,Object> getPTList(Integer offset, String keyword){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        if(offset == null) {
            offset =0;
            resultMap.put("offset", offset);
        }
        if(keyword == null){
            keyword ="%%";
            resultMap.put("keyword", "");
        }
        else {
            resultMap.put("keyword", keyword);
            keyword = "%"+keyword+"%";
        }

        List<PTListVo> list= mapper.getPTListInfo(offset,keyword);

        Integer cnt = mapper.getPTListCount(keyword);
        Integer page_cnt = cnt/10;
        if(cnt % 10>0) page_cnt++;

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list",list);
        return resultMap;
    }
    public Map<String,Object> addPTList(PTListVo data){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
    if(data.getPi_name() == null || data.getPi_name().equals("")){
        resultMap.put("status", false);
        resultMap.put("message", "종목을 입력하세요");
        return resultMap;
    }
    if(data.getPi_ti_name() == null || data.getPi_ti_name().equals("")){
        resultMap.put("status", false);
        resultMap.put("message", "강사이름을 입력하세요");
        return resultMap;
    }
    if(data.getPi_time() == null || data.getPi_time().equals("")){
        resultMap.put("status", false);
        resultMap.put("message", "강의시간을 입력하세요");
        return resultMap;
    }
    if(data.getPi_start_dt() == null || data.getPi_start_dt().equals("")){
        resultMap.put("status", false);
        resultMap.put("message", "시작일을 입력하세요");
        return resultMap;
    }
    if(data.getPi_end_dt() == null || data.getPi_end_dt().equals("")){
        resultMap.put("status", false);
        resultMap.put("message", "종료일을 입력하세요");
        return resultMap;
    }
        mapper.addPTList(data);
        resultMap.put("status", true);
        resultMap.put("message", "회원 정보가 추가되었습니다");

        return resultMap;
    }

    public Map<String,Object> deletePTList(Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        mapper.deletePTList(seq);
        resultMap.put("status", true);
        resultMap.put("message", "회원 정보가 삭제되었습니다.");
        return resultMap;
    }
    public Map<String,Object> getPTListInfoBySeq(Integer seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("data", mapper.getPTListInfoBySeq(seq));
        return resultMap;
    }   
    public Map<String,Object> updatePTList(PTListVo data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();      
        mapper.updatePTList(data);     
        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다." );
        return resultMap;
    }
}

