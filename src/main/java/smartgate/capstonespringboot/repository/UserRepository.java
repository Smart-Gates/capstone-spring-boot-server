package smartgate.capstonespringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smartgate.capstonespringboot.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByIdIn(List<Long> userIds);

	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

	Page<User> findByOrganizationId(Long postId, Pageable pageable);

	Optional<User> findByIdAndOrganizationId(Long id, Long postId);
}