package smartgate.capstonespringboot.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import smartgate.capstonespringboot.models.audit.DateAudit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// @Entity maps to table names meetings
// @Data removes getter and setter boilerplate

@Entity
@Data
@Table(name = "fcm_token")
@EqualsAndHashCode(callSuper = false)
public class FCMToken extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "fcm_token")
	private User user;

	private String fcm_token;

	public FCMToken() {
	}

	public FCMToken(String fcm_token) {
		this.fcm_token = fcm_token;
	}

}