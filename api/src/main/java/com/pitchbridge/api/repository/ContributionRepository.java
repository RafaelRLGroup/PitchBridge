package com.pitchbridge.api.repository;

import com.pitchbridge.api.dto.UserRankingDTO;
import com.pitchbridge.api.model.Contribution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {

    // Mural paginado: busca as doações de um sonho específico
    Page<Contribution> findByDreamId(Long dreamId, Pageable pageable);

    @Query("SELECT new com.pitchbridge.api.dto.UserRankingDTO(c.donor.name, SUM(c.amount)) " +
           "FROM Contribution c " +
           "GROUP BY c.donor.id, c.donor.name " +
           "ORDER BY SUM(c.amount) DESC")
    List<UserRankingDTO> getTopDonors();
}
