package vn.edu.fpt.jpos.repositories;

import java.util.List;
import vn.edu.fpt.jpos.repositories.entities.category.CategoryDAO;
import vn.edu.fpt.jpos.repositories.entities.category.CategoryDTO;
import vn.edu.fpt.jpos.repositories.entities.category.CategoryERROR;

public class CategoryRepository {

    private static CategoryRepository instance;
    private CategoryDAO dao = CategoryDAO.getInstance();

    private CategoryRepository() {
    }

    public static CategoryRepository getInstance() {
        if (instance == null) {
            instance = new CategoryRepository();
        }
        return instance;
    }

    public List<CategoryDTO> getAllCategories() throws CategoryERROR {
        return dao.gets();
    }

    public CategoryDTO getCategoryById(String id) throws CategoryERROR {
        return dao.get(id);
    }
}
