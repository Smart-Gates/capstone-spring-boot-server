package smartgate.capstonespringboot.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import smartgate.capstonespringboot.models.audit.DateAudit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

// @Entity maps to table names meetings
// @Data removes getter and setter boilerplate

@Entity
@Data
@Table(name = "rfid")
@EqualsAndHashCode(callSuper = false)
public class Rfid extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	@JsonIgnore
	@OneToOne
    @MapsId
    private User user;

	private String tag;

	public Rfid() {
	}

	public Rfid(User user, String tag) {
		this.tag = tag;
		this.user = user;
	}
}
