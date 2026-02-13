package matteobenetazzo.testweektwobu2.repositories;

import matteobenetazzo.testweektwobu2.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ViaggiRepository extends JpaRepository<Viaggio, UUID> {
}

