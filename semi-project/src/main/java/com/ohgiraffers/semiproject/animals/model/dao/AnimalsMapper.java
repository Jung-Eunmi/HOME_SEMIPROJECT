package com.ohgiraffers.semiproject.animals.model.dao;

import com.ohgiraffers.semiproject.animals.model.dto.TypeAndBreedAndEmpAndAnimalDTO;
import com.ohgiraffers.semiproject.animals.model.dto.BreedDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnimalsMapper {
//    List<TypeAndBreedAndEmpAndAnimalDTO> AllList();

    List<BreedDTO> findBreed();

    void newAnimal(TypeAndBreedAndEmpAndAnimalDTO animalsDTO);

    List<TypeAndBreedAndEmpAndAnimalDTO> AllList(Map<String, Integer> params);

    int getTotalAnimalCount();

    void deleteByCode(String code);
}
