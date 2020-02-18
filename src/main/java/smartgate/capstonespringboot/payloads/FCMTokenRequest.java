package smartgate.capstonespringboot.payloads;

import lombok.Data;

@Data
public class FCMTokenRequest {


    private String token;

    public FCMTokenRequest() {
    }

    public FCMTokenRequest(String token) {
        this.token = token;
    }
}