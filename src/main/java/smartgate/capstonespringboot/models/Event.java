package smartgate.capstonespringboot.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import smartgate.capstonespringboot.models.audit.DateAudit;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// @Entity maps to table names meetings
// @Data removes getter and setter boilerplate

@Entity
@Data
@Table(name = "event")
@EqualsAndHashCode(callSuper = false)
public class Event extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
// set the SQL values for each of the 
	private String title;
	private String description;
	private String location;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp start_time;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp end_time;

	// this field should never be edited, as it should show up in audits
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "created_by", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User creator;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "event")
	private Reminder reminder;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "event_attendee", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonManagedReference

	public List<User> attendees = new ArrayList<User>();
	public Event() {
	}

	public Event(String title, String description, String location, Timestamp start_time, Timestamp end_time,
			User user) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.start_time = start_time;
		this.end_time = end_time;
		this.creator = user;
	}

}