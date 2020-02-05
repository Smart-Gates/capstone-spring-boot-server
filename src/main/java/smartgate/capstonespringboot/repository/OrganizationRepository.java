package smartgate.capstonespringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import smartgate.capstonespringboot.models.Organization;


public interface OrganizationRepository extends JpaRepository<Organization, Long> {
	Boolean existsByName(String name);
	Optional<Organization> findByName(String name);
	
	Optional<Organization> findById(Long id);
}
