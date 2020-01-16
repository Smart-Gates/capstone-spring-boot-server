package smartgate.capstonespringboot.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
// @Entity maps to table names meetings
// @Data removes getter and setter boilerplate
@Entity
@Data
@Table(name = "meetings")
public class Meeting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
// set the SQL values for each of the 
	private String title;
	private String description;
	private Status status;
	private Timestamp start_time;
	private Timestamp end_time;

	Meeting() {
	}

	public Meeting(String title, String description, Timestamp start_time, Timestamp end_time, Status status) {
		this.title = title;
		this.description = description;
		this.start_time = start_time;
		this.end_time = end_time;
		this.status = status;
	}

}