package smartgate.capstonespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import smartgate.capstonespringboot.models.FCMToken;

@Repository
public interface FCMTokenRepository extends JpaRepository<FCMToken, Long> {
	
}
