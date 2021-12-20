package com.ksm981126.fitness.api;

import java.util.Map;

import com.ksm981126.fitness.data.MemberVo;
import com.ksm981126.fitness.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAPIController {
    @Autowired MemberService service;
    @PostMapping("/member/add")
    public Map<String,Object> postMemberAdd(@RequestBody MemberVo data){
        return service.addMember(data);
    }
    @DeleteMapping("/member/delete")
    public Map<String,Object> deleteMember(@RequestParam Integer seq){
    return service.deleteMember(seq);
    }
    @GetMapping("/member/get")
    public Map<String,Object> getMemberInfoBySeq(@RequestParam Integer seq){
        return service.getMemberInfoBySeq(seq);
    }
    @PatchMapping("/member/update")
    public Map<String,Object> patchMemberInfo(@RequestBody MemberVo data){
        return service.updateMember(data);
    }
    @GetMapping("/member/keyword")
    public Map<String,Object> getMemberByKeyword(@RequestParam @Nullable String keyword){
        return service.getMemberByKeyword(keyword);
    }
}
