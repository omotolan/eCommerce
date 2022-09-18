package africa.semicolon.ecommerce.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean isSuccessful;

    private Object data;

}
