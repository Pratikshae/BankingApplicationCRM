package in.natalia.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.natalia.main.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
