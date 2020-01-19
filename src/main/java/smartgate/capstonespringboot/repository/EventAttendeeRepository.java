package smartgate.capstonespringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import smartgate.capstonespringboot.models.Event;
import smartgate.capstonespringboot.models.EventAttendee;


public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Long> {

	List<Event> findAllByUserId(Long id);
}
