package byfayzullayev.mymed.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InSaleReceiveModel {
    private String name;
    private String base64;
    private String remainedDate;
    @JsonProperty("content_type")
    private String contentType;

    @JsonProperty("category_id")
    private long category_Id;
}
