package smartgate.capstonespringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import smartgate.capstonespringboot.models.Event;
import smartgate.capstonespringboot.models.User;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	List<Event> findAllByCreator(User user);

	List<Event>  findAllById(Long id);
	
	Optional<Event> findByIdAndCreator(Long id, User user);
	
	List<Event> findAllByAttendees(User user);

	Optional<Event> deleteByIdAndCreator(Long id, User user);
}
