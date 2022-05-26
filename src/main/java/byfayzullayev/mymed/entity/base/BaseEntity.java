package byfayzullayev.mymed.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    @JsonIgnore
    @CreatedBy
    private String username;

    @JsonIgnore
    @LastModifiedBy
    private String changerUser;

    @JsonProperty("created_date")
    public String createdDate() {
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        if (this.createdDate != null)
            return simpleDateFormat.format(this.createdDate);

        return null;

    }
}
