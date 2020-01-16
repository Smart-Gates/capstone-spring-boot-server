package smartgate.capstonespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import smartgate.capstonespringboot.models.Meeting;


public interface ReminderRepository extends JpaRepository<Meeting, Long> {
}
