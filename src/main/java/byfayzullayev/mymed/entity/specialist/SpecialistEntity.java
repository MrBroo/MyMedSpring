package byfayzullayev.mymed.entity.specialist;

import byfayzullayev.mymed.entity.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SpecialistEntity extends BaseEntity {

    private String name;
    private String category;
    private String seniority;
    private String education;
    @Column(columnDefinition="text")
    private String employmentHistory;
    private String imageUrl;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private InSpecialistEntity inSpecialistEntity;
}
