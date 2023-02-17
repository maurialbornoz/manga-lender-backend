package com.maurialbornoz.mangalenderbackend.dao;

import com.maurialbornoz.mangalenderbackend.entity.Manga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MangaRepository extends JpaRepository<Manga, Long> {
    Page<Manga> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);
    Page<Manga> findByCategory(@RequestParam("category") String category, Pageable pageable);

    Page<Manga> findByOrderByTitleAsc( Pageable pageable);
    Page<Manga> findByOrderByTitleDesc( Pageable pageable);

    Page<Manga> findByCategoryOrderByTitleAsc(@RequestParam("category") String category, Pageable pageable);
    Page<Manga> findByCategoryOrderByTitleDesc(@RequestParam("category") String category, Pageable pageable);
    @Query("select o from Manga o where id in :manga_ids")
    List<Manga> findMangasByMangaIds(@Param("manga_ids") List<Long> mangaId);
}
