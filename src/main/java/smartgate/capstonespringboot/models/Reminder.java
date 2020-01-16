package smartgate.capstonespringboot.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "reminder")
public class Reminder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String title;
	private String description;
	
	
	private Long meeting_id;

	Reminder() {
	}

	public Reminder(String title, String description, long meeting_id) {
		this.title = title;
		this.description = description;
		this.meeting_id = meeting_id;
	}
}