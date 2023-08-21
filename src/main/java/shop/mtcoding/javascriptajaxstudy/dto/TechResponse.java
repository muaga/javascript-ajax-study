package shop.mtcoding.javascriptajaxstudy.dto;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.javascriptajaxstudy.model.Category;
import shop.mtcoding.javascriptajaxstudy.model.Tech;

// DTO로 만들어서, 연관관계를 아예 끊어버린다.

public class TechResponse {
    // 1. 기본 화면 DTO
    // 굳이 List를 DTO로 만드는 이유는 연관관계를 끊어버리려고 만든다.
    // List를 model로 받으면, model에서 또 연관관계에서
    // LazyLoading이 발생한다.
    @Setter
    @Getter
    public static class MainDTO {
        private List<CategoryDTO> categoryList;
        private List<TechDTO> techList;

        public MainDTO(List<Category> categoryList, List<Tech> techList) {
            this.categoryList = categoryList.stream().map(CategoryDTO::new).collect(Collectors.toList());
            this.techList = techList.stream().map(TechDTO::new).collect(Collectors.toList());
        }

        // 2. category 위주 DTO
        @Setter
        @Getter
        public static class CategoryDTO {
            private Integer id;
            private String name;

            // MainDTO에서 데이터를 받을 생성자를 설정한다.
            public CategoryDTO(Category category) {
                this.id = category.getId();
                this.name = category.getName();
            }
        }

        // 3. tech 위주 DTO
        @Setter
        @Getter
        public static class TechDTO {
            private Integer id;
            private String name;

            public TechDTO(Tech tech) {
                this.id = tech.getId();
                this.name = tech.getName();
            }
        }
    }

}
