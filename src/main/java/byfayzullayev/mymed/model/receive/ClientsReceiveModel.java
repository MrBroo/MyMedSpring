package byfayzullayev.mymed.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClientsReceiveModel {

    @NotEmpty(message = "name bo`sh bo`lishi kerakemas")
    private String name;

    @JsonProperty("phone_number")
    @NotNull(message = "telefon raqam bo`sh bo`lishi kerakemas")
    private String phoneNumber;

    @NotEmpty(message = "booking bo`sh bo`lishi kerakemas")
    private String booking;

}
