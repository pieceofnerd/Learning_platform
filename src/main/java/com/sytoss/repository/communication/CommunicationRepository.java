package com.sytoss.repository.communication;

import com.sytoss.model.communication.Communication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunicationRepository extends JpaRepository<Communication,Long> {
}
