package byfayzullayev.mymed.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReviewsReceiveModel {

    private String name;
    private String remainedDate;
    private String reviews;

    @JsonProperty("category_id")
    private long categoryId;
}
