package vn.edu.fpt.jpos.repositories;

import java.util.List;
import vn.edu.fpt.jpos.repositories.entities.product.ProductDAO;
import vn.edu.fpt.jpos.repositories.entities.product.ProductDTO;
import vn.edu.fpt.jpos.repositories.entities.product.ProductERROR;

public class ProductRepository {

    private static ProductRepository instance;
    private final ProductDAO productDao = ProductDAO.getInstance();

    private ProductRepository() {
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public List<ProductDTO> getAllProducts() throws ProductERROR {
        return productDao.gets();
    }

    public ProductDTO getProduct(String id) throws ProductERROR {
        return productDao.get(id);
    }

    public ProductDTO addProduct(ProductDTO p) throws ProductERROR {
        return productDao.post(p);
    }
}
