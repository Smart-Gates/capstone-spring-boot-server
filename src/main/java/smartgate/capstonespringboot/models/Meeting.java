package smartgate.capstonespringboot.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "USER_MEETING")
public class Meeting {

  private @Id @GeneratedValue Long id;

  private String title;
  private String description;
  private Status status;
  private long timeStartUnix; 
  private long timeEndUnix;
  Meeting() {}

  public Meeting(String title, String description, Status status, long timeStartUnix, long timeEndUnix) {
	this.title = title;
    this.description = description;
    this.status = status;
    this.timeStartUnix = timeStartUnix;
    this.timeEndUnix = timeEndUnix;

  }
}