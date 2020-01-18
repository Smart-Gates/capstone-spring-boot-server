package smartgate.capstonespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import smartgate.capstonespringboot.models.Organization;


public interface OrganizationRepository extends JpaRepository<Organization, Long> {
	Boolean existsByName(String name);
	Organization findByName(String name);
}
