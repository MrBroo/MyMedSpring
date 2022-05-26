package byfayzullayev.mymed.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServicesReceiveModel {

    private String name;
    @JsonProperty("content_type")
    private String contentType;
    private String base64;
    private String about;
    private String allDefinition;
    @JsonProperty("category_id")
    private long categoryId;
}
