package smartgate.capstonespringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import smartgate.capstonespringboot.models.Event;
import smartgate.capstonespringboot.models.User;


public interface EventRepository extends JpaRepository<Event, Long> {
	List<Event> findAllByCreator(User user);

	List<Event>  findAllById(Long id);
	
	Optional<Event> findByIdAndCreator(Long id, User user);
	
	Optional<Event> deleteByIdAndCreator(Long id, User user);
}
