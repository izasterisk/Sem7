package vn.edu.fpt.jpos.services;

import java.util.List;
import vn.edu.fpt.jpos.repositories.CategoryRepository;
import vn.edu.fpt.jpos.repositories.entities.category.CategoryDTO;
import vn.edu.fpt.jpos.repositories.entities.category.CategoryERROR;

public class CategoryService {

    private static CategoryService instance;

    private CategoryService() {
    }

    public static CategoryService getInstance() {
        if (instance == null) {
            instance = new CategoryService();
        }
        return instance;
    }

    public List<CategoryDTO> getAllCategories() throws CategoryERROR {
        CategoryRepository repo = CategoryRepository.getInstance();
        return repo.getAllCategories();
    }
}
