package smartgate.capstonespringboot.meetings;

import org.springframework.data.jpa.repository.JpaRepository;


interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
