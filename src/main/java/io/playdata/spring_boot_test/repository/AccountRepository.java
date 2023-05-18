package io.playdata.spring_boot_test.repository;

import io.playdata.spring_boot_test.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);

    boolean existsByUsername(String username);
}



