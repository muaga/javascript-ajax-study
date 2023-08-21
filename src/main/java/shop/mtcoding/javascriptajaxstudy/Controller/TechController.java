package shop.mtcoding.javascriptajaxstudy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.javascriptajaxstudy.dto.TechResponse;
import shop.mtcoding.javascriptajaxstudy.model.Category;
import shop.mtcoding.javascriptajaxstudy.model.CategoryRepository;
import shop.mtcoding.javascriptajaxstudy.model.Tech;
import shop.mtcoding.javascriptajaxstudy.model.TechRepository;

@Controller
public class TechController {

    @Autowired
    private TechRepository techRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // main
    @GetMapping("/tech")
    public String main() {
        return "main";
    }

    // category JSON 데이터
    @GetMapping("/api/category")
    public @ResponseBody List<Category> categoryApi(){
        return categoryRepository.findAll();
    }

    // tech JSON 데이터
    @GetMapping("/api/tech")
    public @ResponseBody List<Tech> techApi(@RequestParam(defaultValue = "1") Integer categoryId){
        return techRepository.findByCategoryId(categoryId);
    }

    // 이전 JSON 데이터 - 이렇게 보내면 안된다.
    @GetMapping("/test/tech")
    public @ResponseBody List<Tech> tech(){
        List<Tech> techList = techRepository.findAll();
        return techList;
    }

    // 이후 JSON 데이터 - Eager
    // tech에 해당되는 category가 나온다. - 이렇게 보내면 안된다.
    @GetMapping("/test/tech/eager")
    public @ResponseBody List<Tech> techEager(){
        // List<Category> categoryList = categoryRepository.findAll();
        List<Tech> techList = techRepository.findByCategoryId(1);
        return techList;
    }

    // 이후 JSON 데이터 - LAZY
    // LazyLoading으로 연관되어서 나온다.
    @GetMapping("/test/tech/lazy")
    public @ResponseBody List<Tech> techLazy(){
        // List<Category> categoryList = categoryRepository.findAll();
        List<Tech> techList = techRepository.findByCategoryId(1);
        return techList;
    }

    // 이후 JSON 데이터 - LAZY(DTO, stream)
    // 연관관계를 모두 떨어뜨려놓은 뒤, 객체 리스트를 따로 받는다.
    @GetMapping("/test/tech/lazy-dto")
    public @ResponseBody TechResponse.MainDTO techDTO(){
        List<Category> categoryList = categoryRepository.findAll();
        List<Tech> techList = techRepository.findByCategoryId(1);
        System.out.println("====================================");
        TechResponse.MainDTO mainDTO = new TechResponse.MainDTO(categoryList, techList);
        return mainDTO;
    }

    // 이후JSON 데이터 - LAZY(@JsonIgnore)
    @GetMapping("/test/tech/lazy-jsonignore")
    public @ResponseBody List<Tech> techLazyJsonignore(){
        List<Tech> techList = techRepository.findByCategoryId(1);
        return techList;
    }
}
