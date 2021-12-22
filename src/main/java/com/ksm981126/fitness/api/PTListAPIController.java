package com.ksm981126.fitness.api;

import java.util.Map;

import com.ksm981126.fitness.data.PTListVo;
import com.ksm981126.fitness.service.PTListService;

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
public class PTListAPIController {
    @Autowired PTListService service;
    @PostMapping("/ptlist/add")
    public Map<String,Object> postPTListadd(@RequestBody PTListVo data){
        return service.addPTList(data);
    }
    @DeleteMapping("/ptlist/delete")
    public Map<String,Object> deleteMember(@RequestParam Integer seq){
    return service.deletePTList(seq);
    }
    @GetMapping("/ptlist/get")
    public Map<String,Object> getMemberInfoBySeq(@RequestParam Integer seq){
        return service.getPTListInfoBySeq(seq);
    }
    @PatchMapping("/ptlist/update")
    public Map<String,Object> patchMemberInfo(@RequestBody PTListVo data){
        return service.updatePTList(data);
    }
    @GetMapping("/ptlist/keyword")
    public Map<String,Object> getPTListByKeyword(@RequestParam @Nullable String keyword){
        return service.getPTListByKeyword(keyword);
    }
}
