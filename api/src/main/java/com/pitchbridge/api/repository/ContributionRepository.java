package com.pitchbridge.api.repository;

import com.pitchbridge.api.model.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {

    // O Spring traduz esse nome de método para SQL sozinho!
    List<Contribution> findByDreamId(Long dreamId);
}
