package com.ksm981126.fitness.api;

import java.util.Map;

import com.ksm981126.fitness.data.TrainerVo;
import com.ksm981126.fitness.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainerAPIController {
    @Autowired TrainerService service;
    @PostMapping("/trainer/add")
    public Map<String, Object> postTeacherAdd(@RequestBody TrainerVo data) throws Exception{
        return service.addTrainerInfo(data);
    }
    @DeleteMapping("trainer/delete")
    public Map<String,Object> deleteTrainerInfo(@RequestParam Integer seq){
        return service.deleteTrainerInfo(seq);
    }
    @GetMapping("/trainer/get")
    public TrainerVo getTrainerInfoBySeq(@RequestParam Integer seq){
        return service.getTrainerInfoBySeq(seq);
    }
    @PatchMapping("/trainer/modify")
    public Map<String, Object> patchTeacherInfo(@RequestBody TrainerVo data){
        return service.updateTrainerInfo(data);
    }
}
