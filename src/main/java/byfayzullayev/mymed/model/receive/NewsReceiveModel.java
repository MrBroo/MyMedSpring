package byfayzullayev.mymed.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewsReceiveModel {

    private String name;
    private String remainedDate;
    private String about;
    private String base64;
    @JsonProperty("content_type")
    private String contentType;

    @JsonProperty("inNews_id")
    private long InNewsId;
}
