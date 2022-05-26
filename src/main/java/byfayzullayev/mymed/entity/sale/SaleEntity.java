package byfayzullayev.mymed.entity.sale;


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
public class SaleEntity extends BaseEntity {

    private String name;
    private String remainedDate;
    @Column(columnDefinition="text")
    private String about;
    private String imageUrl;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private InSaleEntity inSaleEntity;


}
