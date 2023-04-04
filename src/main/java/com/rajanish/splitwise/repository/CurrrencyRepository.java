package com.rajanish.splitwise.repository;

import com.rajanish.splitwise.models.Currrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrrencyRepository extends JpaRepository<Currrency,Long> {
    Currrency save(Currrency currrency);
    Currrency findByBaseCurrencyEquals(String baseCurrency);
}
