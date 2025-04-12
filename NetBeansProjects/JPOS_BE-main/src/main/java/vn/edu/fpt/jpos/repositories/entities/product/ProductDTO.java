package vn.edu.fpt.jpos.repositories.entities.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.edu.fpt.jpos.repositories.enums.ProductStatusEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {

    private String id;
    private String cate_id;
    private String name;
    private String description;
    private String image;
    private float price;
    private ProductStatusEnum status;
}
