package com.ksm981126.fitness.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ksm981126.fitness.data.TrainerHistoryVo;
import com.ksm981126.fitness.data.TrainerVo;
import com.ksm981126.fitness.mapper.TrainerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    @Autowired TrainerMapper mapper;

    public Map<String,Object> getTrainerList(String type, String keyword,Integer offset){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        if(keyword == null){
            resultMap.put("keyword", keyword);
            keyword ="%%";
        }
        else {
            resultMap.put("keyword", keyword);
            keyword = "%"+keyword+"%";
        }

        resultMap.put("type", type);

        if(offset ==null) offset=0;

        List<TrainerVo> list = mapper.getTrainerInfo(type, keyword,offset);

        Integer cnt =mapper.getTrainerCount(keyword,type);
        Integer page_cnt =cnt/10;
        if(cnt % 10>0) page_cnt++;

        
        resultMap.put("status", true);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> addTrainerInfo(TrainerVo data) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();

        if(data.getTi_name().equals("") || data.getTi_name()== null){
            resultMap.put("status", false);
            resultMap.put("reason", "name");
            resultMap.put("message", "이름을 입력해주세요");
            return resultMap;
        }
        if(data.getTi_address().equals("") || data.getTi_address()== null){
            resultMap.put("status", false);
            resultMap.put("reason", "address");
            resultMap.put("message", "주소를 입력해주세요");
            return resultMap;
        }
        if(data.getTi_birth().equals("") || data.getTi_birth()== null){
            resultMap.put("status", false);
            resultMap.put("reason", "birth");
            resultMap.put("message", "생년월일을 입력해주세요");
            return resultMap;
        }
        if(data.getTi_phone_num().equals("") || data.getTi_phone_num()== null){
            resultMap.put("status", false);
            resultMap.put("reason", "phone_num");
            resultMap.put("message", "전화번호를 입력해주세요");
            return resultMap;
        }
        if(data.getTi_start_dt().equals("") || data.getTi_start_dt()== null){
            resultMap.put("status", false);
            resultMap.put("reason", "start_dt");
            resultMap.put("message", "입사일을 입력해주세요");
            return resultMap;
        }

        mapper.addTrainerInfo(data);

        TrainerHistoryVo history = new TrainerHistoryVo();
        history.setTih_type("new");
        history.setTih_content(data.makeHistoryStr());
        Integer recent_seq =mapper.getRecentAddedTrainerSeq();
        history.setTih_ti_seq(recent_seq);

        mapper.insertTrainerHistory(history);

        resultMap.put("status", true);
        resultMap.put("message", "강사 정보가 추가되었습니다.");;
        return resultMap;
    }

        public Map<String,Object> deleteTrainerInfo(Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();

        Integer cnt = mapper.isExistTrainer(seq);
        if(cnt ==0){
            resultMap.put("status", false);
            resultMap.put("message","삭제에 실패했습니다.(존재하지 않는 교직원 정보)");
        }
        else{
            mapper.deleteTrainerInfo(seq);
            resultMap.put("status", true);
            resultMap.put("message","삭제했습니다.");

            TrainerHistoryVo history = new TrainerHistoryVo();
            history.setTih_type("delete");
            history.setTih_ti_seq(seq);
            mapper.insertTrainerHistory(history);
        }
        return resultMap;
    }
    public TrainerVo getTrainerInfoBySeq(Integer seq){
        return mapper.getTrainerInfoBySeq(seq);
    }
    public Map<String,Object> updateTrainerInfo(TrainerVo data){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();

        mapper.updateTrainerInfo(data);

        resultMap.put("status", true);
        resultMap.put("message","수정되었습니다.");

        TrainerHistoryVo history = new TrainerHistoryVo();
        history.setTih_type("modify");
        history.setTih_content(data.makeHistoryStr());
        history.setTih_seq(data.getTi_seq());
        
        mapper.insertTrainerHistory(history);

        return resultMap;
    }
}

