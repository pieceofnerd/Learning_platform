package com.sytoss.repository;

import com.sytoss.model.communication.Communication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunicationRepository extends JpaRepository<Communication,Long> {
}
