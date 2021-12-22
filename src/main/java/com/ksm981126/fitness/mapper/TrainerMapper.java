package com.ksm981126.fitness.mapper;

import java.util.List;

import com.ksm981126.fitness.data.TrainerHistoryVo;
import com.ksm981126.fitness.data.TrainerVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrainerMapper {
    public List<TrainerVo> getTrainerInfo(String type,String keyword,Integer offset);
    public Integer getTrainerCount(String type,String keyword);

    public void addTrainerInfo(TrainerVo data);

    public void deleteTrainerInfo(Integer seq);
    public Integer isExistTrainer(Integer seq);

    public void updateTrainerInfo(TrainerVo data);
    public TrainerVo getTrainerInfoBySeq(Integer seq);

    public void insertTrainerHistory(TrainerHistoryVo data);
    public Integer getRecentAddedTrainerSeq();
}
