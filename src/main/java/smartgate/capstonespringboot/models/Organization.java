package smartgate.capstonespringboot.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "organizations")
public class Organization {

  private @Id @GeneratedValue Long id;

  private String title;
  private String description;
  private Timestamp reminder_time; 
  
  Organization() {}

  public Organization(String title, String description, Timestamp reminder_time) {
	this.title = title;
    this.description = description;
    this.reminder_time = reminder_time;
  }
}