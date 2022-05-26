package byfayzullayev.mymed.entity.specialist;

import byfayzullayev.mymed.entity.base.BaseEntity;
import byfayzullayev.mymed.entity.category.CategoryEntity;
import byfayzullayev.mymed.entity.services.CategoryServicesEntity;
import byfayzullayev.mymed.entity.services.ServicesEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InSpecialistEntity extends BaseEntity {

    private String imageUrl;
    private String name;
    @Column(columnDefinition="text")
    private String direction;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "inSpecialistEntity")
    private SpecialistEntity specialist;

    @JsonIgnore
    @ManyToOne
    private CategoryEntity categoryEntity;

}
