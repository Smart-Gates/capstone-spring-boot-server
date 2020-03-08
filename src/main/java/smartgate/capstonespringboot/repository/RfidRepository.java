package smartgate.capstonespringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smartgate.capstonespringboot.models.Rfid;
import smartgate.capstonespringboot.models.User;

@Repository
public interface RfidRepository extends JpaRepository<Rfid, Long> {
	
	Optional<Rfid> findByIdAndUser(Long id, User user);
	Optional<Rfid> findById(Long id);
	Optional<Rfid> findByUser(User user);
	Optional<Rfid> findByTag(String tag);
	Optional<Rfid> deleteByUser(User user);
	Optional<Rfid> deleteByIdAndUser(Long id, User user);
}
