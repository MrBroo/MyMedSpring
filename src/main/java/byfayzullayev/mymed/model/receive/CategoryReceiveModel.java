package byfayzullayev.mymed.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryReceiveModel {
    private String name;
    @JsonProperty("content_type")
    private String contentType;
    private String base64;

}
