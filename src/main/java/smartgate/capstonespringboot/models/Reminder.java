package smartgate.capstonespringboot.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import smartgate.capstonespringboot.models.audit.DateAudit;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@Table(name = "reminder")
@EqualsAndHashCode(callSuper = false)
public class Reminder extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String title;
	private String description;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "event_id", nullable = true)
	private Event event;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp start_time;

	// this field should never be edited, as it should show up in audits
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "created_by", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User creator;

	Reminder() {
	}

	public Reminder(String title, String description, Timestamp start_time, Event event, User user) {
		this.title = title;
		this.description = description;
		this.start_time = start_time;
		this.event = event;
		this.creator = user;
	}
}
