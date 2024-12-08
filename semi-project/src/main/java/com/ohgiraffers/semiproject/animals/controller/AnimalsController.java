package com.ohgiraffers.semiproject.animals.controller;

import com.ohgiraffers.semiproject.animals.model.dto.TypeAndBreedAndEmpAndAnimalDTO;
import com.ohgiraffers.semiproject.animals.model.dto.BreedDTO;
import com.ohgiraffers.semiproject.animals.model.service.AnimalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AnimalsController {

    private final AnimalsService animalsService;

    @Autowired
    public AnimalsController(AnimalsService animalsService){
        this.animalsService = animalsService;
    }

    // 구조 동물 페이지
    @GetMapping("/sidemenu/animals")
    public String animals(@RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "10") int limit,
                          Model model){

        List<TypeAndBreedAndEmpAndAnimalDTO> list = animalsService.getPagedAnimalList(page, limit);
        model.addAttribute("list", list);

        // 페이지 네비게이션 정보 추가
        int totalRecords = animalsService.getTotalAnimalCount(); // 총 레코드 수 조회
        int totalPages = (int) Math.ceil((double) totalRecords / limit);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        return "sidemenu/animals/animals";
    }

    // 구조동물 등록
    @GetMapping("/sidemenu/animals/insert")
    public void addAnimal(){}

    @GetMapping(value = "/sidemenu/animals/breed", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<BreedDTO> findCategoryList(){
        return animalsService.findBreed();
    }
    @PostMapping("/sidemenu/animals/insert")
    public String newAnimal(@ModelAttribute TypeAndBreedAndEmpAndAnimalDTO animalsDTO, RedirectAttributes rttr){

        animalsService.newAnimal(animalsDTO);

        System.out.println("변경 = " + animalsDTO);

        return "redirect:/sidemenu/animals";
    }

    // 구조동물 삭제
    @PostMapping("/sidemenu/animals/delete")
    public String delete(@RequestParam List<String> animalCodes){

        for (String code : animalCodes) {
            // 동물 삭제 로직 (예: 데이터베이스에서 삭제)
            animalsService.deleteByCode(code);
        }

        return "redirect:/sidemenu/animals";
    }


    // 입양완료 페이지로 이동
    @GetMapping("/sidemenu/adoptionComplete")
    public String adoptionComplete(){
        return "sidemenu/animals/adoptionComplete";
    }

    // 재고관리 페이지로 이동
    @GetMapping("/sidemenu/stock")
    public String stock(){
        return "sidemenu/animals/stock";
    }
}
