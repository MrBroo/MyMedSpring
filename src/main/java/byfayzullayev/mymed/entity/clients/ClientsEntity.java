package byfayzullayev.mymed.entity.clients;

import byfayzullayev.mymed.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ClientsEntity extends BaseEntity {
    private String name;
    private String phoneNumber;
    @Column(columnDefinition="text")
    private String booking;

}
