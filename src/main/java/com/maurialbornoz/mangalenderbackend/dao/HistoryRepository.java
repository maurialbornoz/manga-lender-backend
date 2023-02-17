package com.maurialbornoz.mangalenderbackend.dao;

import com.maurialbornoz.mangalenderbackend.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;


public interface HistoryRepository extends JpaRepository<History, Long> {
    Page<History> findHistoriesByUserEmail(@RequestParam("userEmail") String userEmail, Pageable pageable);
}
