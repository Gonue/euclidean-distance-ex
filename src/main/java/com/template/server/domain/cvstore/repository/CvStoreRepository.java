package com.template.server.domain.cvstore.repository;

import com.template.server.domain.cvstore.entity.CvStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvStoreRepository extends JpaRepository<CvStore, Long> {
}
