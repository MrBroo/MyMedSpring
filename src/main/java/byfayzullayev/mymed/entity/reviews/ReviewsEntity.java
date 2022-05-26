package byfayzullayev.mymed.entity.reviews;

import byfayzullayev.mymed.entity.base.BaseEntity;
import byfayzullayev.mymed.entity.category.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReviewsEntity extends BaseEntity {

    private String name;
    private String remainedDate;
    @Column(columnDefinition="text")
    private String reviews;

    @JsonIgnore
    @ManyToOne
    private CategoryEntity categoryEntity;

}
