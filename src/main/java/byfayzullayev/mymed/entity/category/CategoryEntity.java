package byfayzullayev.mymed.entity.category;

import byfayzullayev.mymed.entity.base.BaseEntity;
import byfayzullayev.mymed.entity.news.InNewsEntity;
import byfayzullayev.mymed.entity.reviews.ReviewsEntity;
import byfayzullayev.mymed.entity.sale.InSaleEntity;
import byfayzullayev.mymed.entity.services.CategoryServicesEntity;
import byfayzullayev.mymed.entity.specialist.InSpecialistEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CategoryEntity extends BaseEntity {

    private String name;
    private String imageUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<InNewsEntity> inNewsEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<ReviewsEntity> reviewsEntityList;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<InSaleEntity> inSaleEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<CategoryServicesEntity> categoryServicesEntityList;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<InSpecialistEntity> inSpecialistEntityList;


}
