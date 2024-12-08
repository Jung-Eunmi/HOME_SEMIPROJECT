package com.ohgiraffers.semiproject.animals.model.service;

import com.ohgiraffers.semiproject.animals.model.dao.AnimalsMapper;
import com.ohgiraffers.semiproject.animals.model.dto.TypeAndBreedAndEmpAndAnimalDTO;
import com.ohgiraffers.semiproject.animals.model.dto.BreedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnimalsService {

    public final AnimalsMapper animalsMapper;

    @Autowired
    public AnimalsService(AnimalsMapper animalsMapper){
        this.animalsMapper = animalsMapper;
    }

//    public List<TypeAndBreedAndEmpAndAnimalDTO> AllList() {
//        return animalsMapper.AllList();
//    }

    @Transactional
    public void newAnimal(TypeAndBreedAndEmpAndAnimalDTO animalsDTO) {
        animalsMapper.newAnimal(animalsDTO);
    }

    public List<BreedDTO> findBreed() {
        return animalsMapper.findBreed();
    }

    public List<TypeAndBreedAndEmpAndAnimalDTO> getPagedAnimalList(int page, int limit) {
        int offset = (page - 1) * limit; // 페이지 시작점 계산
        Map<String, Integer> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);
        return animalsMapper.AllList(params);
    }

    public int getTotalAnimalCount() {
        return animalsMapper.getTotalAnimalCount();
    }

    public void deleteByCode(String code) {
        animalsMapper.deleteByCode(code);
    }
}
