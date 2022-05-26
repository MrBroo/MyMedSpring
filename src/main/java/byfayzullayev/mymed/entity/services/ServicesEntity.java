package byfayzullayev.mymed.entity.services;

import byfayzullayev.mymed.entity.base.BaseEntity;
import byfayzullayev.mymed.entity.specialist.InSpecialistEntity;
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
public class ServicesEntity extends BaseEntity {
    private String name;
    @Column(columnDefinition="text")
    private String about;
    private String imageUrl;
    private String allDefinition;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private CategoryServicesEntity categoryServicesEntity;
}
