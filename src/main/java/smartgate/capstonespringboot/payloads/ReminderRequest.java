package smartgate.capstonespringboot.payloads;

import javax.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReminderRequest {

	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private Timestamp start_time;
	
	private Long event_id;
}
