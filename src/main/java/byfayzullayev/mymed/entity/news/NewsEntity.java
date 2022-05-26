package byfayzullayev.mymed.entity.news;

import byfayzullayev.mymed.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NewsEntity extends BaseEntity {

    private String name;
    private String remainedDate;
    @Column(columnDefinition="text")
    private String about;
    private String imageUrl;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private InNewsEntity inNewsEntity;

}
