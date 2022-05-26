package byfayzullayev.mymed.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryServicesReceiveModel {

    private String name;
    @JsonProperty("content_type")
    private String contentType;
    private String base64;

    @JsonProperty("category_id")
    private long categoryId;
}
