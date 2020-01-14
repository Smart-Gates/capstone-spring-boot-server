package smartgate.capstonespringboot.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "USER_MEETING")
public class Reminder {

  private @Id @GeneratedValue Long id;

  private String title;
  private String description;
  private long reminderTimes; 
  private long condition; 
  Reminder() {}

  public Reminder(String title, String description, Status status, long timeStartUnix, long timeEndUnix) {
	this.title = title;
    this.description = description;
  

  }
}