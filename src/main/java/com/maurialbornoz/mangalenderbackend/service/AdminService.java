package com.maurialbornoz.mangalenderbackend.service;

import com.maurialbornoz.mangalenderbackend.dao.CheckoutRepository;
import com.maurialbornoz.mangalenderbackend.dao.MangaRepository;
import com.maurialbornoz.mangalenderbackend.dao.ReviewRepository;
import com.maurialbornoz.mangalenderbackend.entity.Manga;
import com.maurialbornoz.mangalenderbackend.requestmodels.AddMangaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional

public class AdminService {
    private MangaRepository mangaRepository;
    private ReviewRepository reviewRepository;
    private CheckoutRepository checkoutRepository;

    @Autowired
    public AdminService(MangaRepository mangaRepository, CheckoutRepository checkoutRepository, ReviewRepository reviewRepository){
        this.mangaRepository = mangaRepository;
        this.reviewRepository = reviewRepository;
        this.checkoutRepository = checkoutRepository;
    }

    public void postManga(AddMangaRequest addMangaRequest){
        Manga manga = new Manga();
        manga.setTitle(addMangaRequest.getTitle());
        manga.setAuthor(addMangaRequest.getAuthor());
        manga.setDescription(addMangaRequest.getDescription());
        manga.setCopiesAvailable(addMangaRequest.getCopies());
        manga.setCopies(addMangaRequest.getCopies());
        manga.setCategory(addMangaRequest.getCategory());
        manga.setImg(addMangaRequest.getImg());
        mangaRepository.save(manga);
    }

    public void increaseMangaQuantity(Long mangaId) throws  Exception{
        Optional<Manga> manga = mangaRepository.findById(mangaId);
        if(!manga.isPresent()){
            throw new Exception("Manga not found");
        }
        manga.get().setCopiesAvailable(manga.get().getCopiesAvailable() + 1);
        manga.get().setCopies(manga.get().getCopies() + 1);

        mangaRepository.save(manga.get());
    }

    public void decreaseMangaQuantity(Long mangaId) throws Exception{
        Optional<Manga> manga = mangaRepository.findById(mangaId);
        if(!manga.isPresent() || manga.get().getCopiesAvailable() <= 0 || manga.get().getCopies() <= 0){
            throw new Exception("Manga not found or quantity locked");
        }
        manga.get().setCopiesAvailable(manga.get().getCopiesAvailable() - 1);
        manga.get().setCopies(manga.get().getCopies() - 1);

        mangaRepository.save(manga.get());
    }

    public void deleteManga(Long mangaId) throws  Exception{
        Optional<Manga> manga = mangaRepository.findById(mangaId);
        if(!manga.isPresent()){
            throw new Exception("Manga not found");
        }

        mangaRepository.delete(manga.get());
        checkoutRepository.deleteAllByMangaId(mangaId);
        reviewRepository.deleteAllByMangaId(mangaId);
    }
}
