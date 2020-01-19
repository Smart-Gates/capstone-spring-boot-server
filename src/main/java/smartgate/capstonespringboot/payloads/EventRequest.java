package smartgate.capstonespringboot.payloads;

import javax.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class EventRequest {

	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private String location;
	@NotBlank
	private Timestamp start_time;
	@NotBlank
	private Timestamp end_time;

	private List<Long> attendee_id;
}
