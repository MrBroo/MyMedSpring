package byfayzullayev.mymed.model.receive;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SaleReceiveModel {

    private String name;
    private String remainedDate;
    private String about;
    private String base64;
    @JsonProperty("content_type")
    private String contentType;

    @JsonProperty("inSale_id")
    private long inSaleId;
}
