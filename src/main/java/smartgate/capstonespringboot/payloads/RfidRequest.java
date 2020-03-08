package smartgate.capstonespringboot.payloads;


import lombok.Data;

@Data
public class RfidRequest {
    
    private String tag;
    
    private Long userId;
}
