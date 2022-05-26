package byfayzullayev.mymed.entity.services;


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
public class CategoryServicesEntity extends BaseEntity {

    private String name;
    private String imageUrl;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "categoryServicesEntity")
    private ServicesEntity servicesEntity;

    @JsonIgnore
    @ManyToOne
    private CategoryEntity categoryEntity;

}
