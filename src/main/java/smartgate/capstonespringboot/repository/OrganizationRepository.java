package smartgate.capstonespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import smartgate.capstonespringboot.models.Meeting;


public interface OrganizationRepository extends JpaRepository<Meeting, Long> {
}
