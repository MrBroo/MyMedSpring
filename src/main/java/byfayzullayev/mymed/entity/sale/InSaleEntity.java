package byfayzullayev.mymed.entity.sale;

import byfayzullayev.mymed.entity.base.BaseEntity;
import byfayzullayev.mymed.entity.category.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InSaleEntity extends BaseEntity {
    private String name;
    private String remainedDate;
    private String imageUrl;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "inSaleEntity")
    private SaleEntity saleEntity;

    @JsonIgnore
    @ManyToOne
    private CategoryEntity categoryEntity;
}
