package com.sytoss.repository;

import com.sytoss.model.Lookup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LookupRepository extends JpaRepository<Lookup, Long> {
}
