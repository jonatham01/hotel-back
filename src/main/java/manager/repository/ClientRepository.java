package manager.repository;

import manager.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
