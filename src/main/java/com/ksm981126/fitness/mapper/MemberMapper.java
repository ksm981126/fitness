package com.ksm981126.fitness.mapper;

import java.util.List;

import com.ksm981126.fitness.data.MemberHistoryVo;
import com.ksm981126.fitness.data.MemberVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public List<MemberVo> getMemberInfo(Integer offset,String keyword);
    public Integer getMemberCount(String keyword);
    public void addMember(MemberVo data);
    public void deleteMember(Integer seq);

    public MemberVo getMemberInfoBySeq(Integer seq);

    public void updateMember(MemberVo data);

    public Integer selectLatesDataSeq();

    public void insertMemberHistory(MemberHistoryVo data);

    public List<MemberVo> getMemberByKeyword(String keyword);
}
