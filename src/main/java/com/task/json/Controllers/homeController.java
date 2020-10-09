package com.task.json.Controllers;

import com.task.json.Model.Data;
import com.task.json.Ropositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("data")
public class homeController {
    @Autowired
    DataRepository dataRepository;


    @GetMapping
    @ResponseBody
    public List<Map<String,String>> home () {
        List <Data> DataList=dataRepository.findAll();
        List<Map<String,String>> jsonDataList = new ArrayList<Map<String,String>>();
        for (Data data : DataList){
            jsonDataList.add(data.ToMap());
        }
        return jsonDataList;
    }

    @GetMapping("{id}")
    public Map<String,String> getOne (@PathVariable int id) {
        Data data=dataRepository.findById(id);
        return data.ToMap();
    }

    @PostMapping
    public Map<String,String> create (@RequestBody Map<String,String> newData) {
        Data data = new Data();
        data.setAddress(newData.get("address"));
        data.setName(newData.get("name"));
        data.setLocalDateTimeOfCreation(LocalDateTime.now());
        data.setLocalDateTimeOfUpdate(LocalDateTime.now());
        dataRepository.save(data);
        return data.ToMap();
    }

    @PutMapping("{id}")
    public Map<String,String> edit (@PathVariable int id,@RequestBody Map<String,String> newData) {
        Data data = dataRepository.findById(id);
        data.setName(newData.get("name"));
        data.setAddress(newData.get("address"));
        data.setLocalDateTimeOfUpdate(LocalDateTime.now());
        dataRepository.save(data);
        return data.ToMap();
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable int id) {
        dataRepository.delete(dataRepository.getOne(id));
    }
}
