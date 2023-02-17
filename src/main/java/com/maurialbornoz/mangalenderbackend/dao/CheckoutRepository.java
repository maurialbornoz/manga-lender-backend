package com.maurialbornoz.mangalenderbackend.dao;

import com.maurialbornoz.mangalenderbackend.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    Checkout findByUserEmailAndMangaId(String userEmail, Long mangaId);

    List<Checkout> findMangasByUserEmail(String userEmail);

    @Modifying
    @Query("delete from Checkout where mangaId in :manga_id")
    void deleteAllByMangaId(@Param("manga_id")Long mangaId);
}
