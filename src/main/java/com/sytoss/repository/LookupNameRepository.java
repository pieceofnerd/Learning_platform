package com.sytoss.repository;

import com.sytoss.model.Lookup;
import com.sytoss.model.LookupName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LookupNameRepository extends JpaRepository<LookupName, Long> {

    @Query("select l From LookupName l where l.name=?1")
    LookupName findByName(String name);

}
