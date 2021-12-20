package com.ksm981126.fitness.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ksm981126.fitness.data.MemberHistoryVo;
import com.ksm981126.fitness.data.MemberVo;
import com.ksm981126.fitness.mapper.MemberMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired MemberMapper mapper;

    public Map<String,Object> getMemberList(Integer offset, String keyword){
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

        List<MemberVo> list= mapper.getMemberInfo(offset,keyword);

        Integer cnt = mapper.getMemberCount(keyword);
        Integer page_cnt = cnt/10;
        if(cnt % 10>0) page_cnt++;

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list",list);
        return resultMap;
    }
    public Map<String,Object> addMember(MemberVo data){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
    if(data.getMi_name() == null || data.getMi_name().equals("")){
        resultMap.put("status", false);
        resultMap.put("message", "이름을 입력하세요");
        return resultMap;
    }
    if(data.getMi_birth() == null || data.getMi_birth().equals("")){
        resultMap.put("status", false);
        resultMap.put("message", "생년월일을 입력하세요");
        return resultMap;
    }
    if(data.getMi_phone_num() == null || data.getMi_phone_num().equals("")){
        resultMap.put("status", false);
        resultMap.put("message", "전화번호를 입력하세요");
        return resultMap;
    }
    if(data.getMi_address() == null || data.getMi_address().equals("")){
        resultMap.put("status", false);
        resultMap.put("message", "주소를 입력하세요");
        return resultMap;
    }
    if(data.getMi_start_dt() == null || data.getMi_start_dt().equals("")){
        resultMap.put("status", false);
        resultMap.put("message", "시작일을 입력하세요");
        return resultMap;
    }
    if(data.getMi_end_dt() == null || data.getMi_end_dt().equals("")){
        resultMap.put("status", false);
        resultMap.put("message", "종료일을 입력하세요");
        return resultMap;
    }
        mapper.addMember(data);
        resultMap.put("status", true);
        resultMap.put("message", "회원 정보가 추가되었습니다");
        Integer seq = mapper.selectLatesDataSeq();

        MemberHistoryVo history =new MemberHistoryVo();
        history.setMh_mi_seq(seq);
        history.setMh_type("new");
        String content = data.getMi_name()+"|"+data.getMi_birth()+"|"+data.getMi_gen()+"|"+data.getMi_phone_num()+"|"+data.getMi_address()+"|"+data.getMi_pt_option()+"|"+data.getMi_locker_option()+"|"+data.getMi_start_dt()+"|"+data.getMi_end_dt();
        history.setMh_content(content);
        mapper.insertMemberHistory(history);

        return resultMap;
    }
    public Map<String,Object> deleteMember(Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        mapper.deleteMember(seq);
        resultMap.put("status", true);
        resultMap.put("message", "회원 정보가 삭제되었습니다.");

        MemberHistoryVo history =new MemberHistoryVo();
        history.setMh_mi_seq(seq);
        history.setMh_type("delete");
        // String content = data.getMi_name()+"|"+data.getMi_birth()+"|"+data.getMi_gen()+"|"+data.getMi_phone_num()+"|"+data.getMi_address()+"|"+data.getMi_pt_option()+"|"+data.getMi_locker_option()+"|"+data.getMi_start_dt()+"|"+data.getMi_end_dt();
        // history.setMh_content(content);
        mapper.insertMemberHistory(history);

        return resultMap;
        }

    public Map<String,Object> getMemberInfoBySeq(Integer seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("data", mapper.getMemberInfoBySeq(seq));
        return resultMap;
    }   
    public Map<String,Object> updateMember(MemberVo data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();      
        mapper.updateMember(data);        
        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다." );

        MemberHistoryVo history =new MemberHistoryVo();
        history.setMh_mi_seq(data.getMi_seq());
        history.setMh_type("update");
        String content = data.getMi_name()+"|"+data.getMi_birth()+"|"+data.getMi_gen()+"|"+data.getMi_phone_num()+"|"+data.getMi_address()+"|"+data.getMi_pt_option()+"|"+data.getMi_locker_option()+"|"+data.getMi_start_dt()+"|"+data.getMi_end_dt();
        history.setMh_content(content);
        mapper.insertMemberHistory(history);

        return resultMap;
    }

    public Map<String,Object> getMemberByKeyword(String keyword){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(keyword == null)keyword ="%%";
        keyword="%"+keyword+"%";

        List<MemberVo> list=mapper.getMemberByKeyword(keyword);
        resultMap.put("status", true);
        resultMap.put("list", list);
        return resultMap;
        }
}

