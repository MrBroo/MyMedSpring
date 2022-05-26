package byfayzullayev.mymed.model.receive.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SignInReceiveModel {

    @NotBlank(message = "username bo`sh bo`lmasligi kerak")
    private String username;

    @NotBlank(message = "password bo`sh bo`lmasligi kerak")
    private String password;
}
