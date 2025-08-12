package com.aman.security.repo;

import com.aman.security.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> {
    
    Optional<Trader> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    List<Trader> findAllByOrderByIdAsc();
}
