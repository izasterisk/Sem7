package vn.edu.fpt.jpos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import vn.edu.fpt.jpos.repositories.CategoryRepository;
import vn.edu.fpt.jpos.repositories.ProductRepository;
import vn.edu.fpt.jpos.repositories.entities.category.CategoryDTO;
import vn.edu.fpt.jpos.repositories.entities.category.CategoryERROR;
import vn.edu.fpt.jpos.repositories.entities.product.ProductDTO;
import vn.edu.fpt.jpos.repositories.entities.product.ProductERROR;

public class ProductService {

    private static ProductService instance;

    private ProductService() {
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public List<ProductDTO> getAllProducts() throws ProductERROR {
        ProductRepository repo = ProductRepository.getInstance();
        return repo.getAllProducts();
    }

    public ProductDTO getProduct(String id) throws ProductERROR {
        ProductRepository repo = ProductRepository.getInstance();
        return repo.getProduct(id);
    }

    public List<ProductDTO> getProductsByCategoryId(String cateId)
            throws CategoryERROR, ProductERROR {
        CategoryRepository cateRepo = CategoryRepository.getInstance();
        ProductRepository productRepo = ProductRepository.getInstance();
        CategoryDTO cate = cateRepo.getCategoryById(cateId);
        List<ProductDTO> products = productRepo.getAllProducts();
        return products.stream()
                .filter(p -> p.getCate_id().equals(cate.getId()))
                .collect(Collectors.toList());
    }

    public ProductDTO addNewProduct(ProductDTO product)
            throws ProductERROR {
        ProductRepository productRepo = ProductRepository.getInstance();
        return productRepo.addProduct(product);
    }
}
