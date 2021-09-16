package com.sytoss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountBaseRepository<T> extends JpaRepository<T,Long> {

}
