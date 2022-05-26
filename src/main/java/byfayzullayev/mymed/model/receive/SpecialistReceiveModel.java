package byfayzullayev.mymed.model.receive;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpecialistReceiveModel {

    private String name;
    private String category;
    private String seniority;
    private String education;
    private String employmentHistory;
    @JsonProperty("content_type")
    private String contentType;
    private String base64;

    @JsonProperty("category_id")
    private long categoryId;

}

