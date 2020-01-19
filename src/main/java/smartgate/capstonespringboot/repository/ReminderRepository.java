package smartgate.capstonespringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import smartgate.capstonespringboot.models.Reminder;
import smartgate.capstonespringboot.models.User;


public interface ReminderRepository extends JpaRepository<Reminder, Long> {
	List<Reminder> findAllByCreator(User user);

	List<Reminder>  findAllById(Long id);
}
