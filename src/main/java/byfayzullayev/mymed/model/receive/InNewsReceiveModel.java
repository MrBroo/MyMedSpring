package byfayzullayev.mymed.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class InNewsReceiveModel {
    private String name;
    @JsonProperty("content_type")
    private String contentType;
    private String base64;
    private String remainedDate;

    @JsonProperty("category_id")
    private long categoryId;
}
