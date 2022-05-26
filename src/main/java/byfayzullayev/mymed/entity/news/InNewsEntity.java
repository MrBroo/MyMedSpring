package byfayzullayev.mymed.entity.news;

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
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InNewsEntity extends BaseEntity {

    private String name;
    private String imageUrl;
    private String remainedDate;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "inNewsEntity")
    private NewsEntity newsEntity;


    @JsonIgnore
    @ManyToOne
    private CategoryEntity categoryEntity;


}

