package vn.edu.tdtu.lab4.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
public class ResponseDTO {
    private Integer errorCode;
    private String message;
    private Object data;

    public ResponseDTO(Integer errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }
}
