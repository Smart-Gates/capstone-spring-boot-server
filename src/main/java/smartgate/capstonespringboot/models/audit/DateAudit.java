package smartgate.capstonespringboot.models.audit;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"created_at", "updated_at"},
        allowGetters = true
)
public abstract class DateAudit implements Serializable {

    /** Class add createdAt and updatedAt fields to tables
	 * 
	 */
	private static final long serialVersionUID = 2627705868328078194L;

	@CreatedDate
    @Column(updatable = false)
    private Instant created_at;

    @LastModifiedDate
    @Column()
    private Instant updated_at;
}