package com.sytoss.repository;

import com.sytoss.model.Lookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LookupRepository extends JpaRepository<Lookup, Long> {

    @Query("select l from Lookup l where l.lookupName.id = 8 and upper(l.value) like upper(concat(?1, '%'))")
    List<Lookup> findLookupsByValueStartingWithIgnoreCase(String value);

}
