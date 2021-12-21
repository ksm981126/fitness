package com.ksm981126.fitness.mapper;

import java.util.List;

import com.ksm981126.fitness.data.PTListHistoryVo;
import com.ksm981126.fitness.data.PTListVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PTListMapper {
    public List<PTListVo> getPTListInfo(Integer offset,String keyword);
    public Integer getPTListCount(String keyword);
    public void addPTList(PTListVo data);
    public void deletePTList(Integer seq);

    public PTListVo getPTListInfoBySeq(Integer seq);

    public void updatePTList(PTListVo data);

    public Integer selectLatesDataSeq();

    public void insertPTHistory(PTListHistoryVo data);

}
