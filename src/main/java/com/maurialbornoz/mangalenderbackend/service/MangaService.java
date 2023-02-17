package com.maurialbornoz.mangalenderbackend.service;

import com.maurialbornoz.mangalenderbackend.dao.CheckoutRepository;
import com.maurialbornoz.mangalenderbackend.dao.HistoryRepository;
import com.maurialbornoz.mangalenderbackend.dao.MangaRepository;
import com.maurialbornoz.mangalenderbackend.entity.Checkout;
import com.maurialbornoz.mangalenderbackend.entity.History;
import com.maurialbornoz.mangalenderbackend.entity.Manga;
import com.maurialbornoz.mangalenderbackend.responsemodels.ShelfCurrentLoansResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class MangaService {

    private MangaRepository mangaRepository;

    private CheckoutRepository checkoutRepository;

    private HistoryRepository historyRepository;

    public MangaService(MangaRepository mangaRepository, CheckoutRepository checkoutRepository, HistoryRepository historyRepository) {
        this.mangaRepository = mangaRepository;
        this.checkoutRepository = checkoutRepository;
        this.historyRepository = historyRepository;
    }

    public Manga checkoutManga(String userEmail, Long mangaId) throws Exception {
        Optional<Manga> manga = mangaRepository.findById(mangaId);
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndMangaId(userEmail, mangaId);
        if(!manga.isPresent() || validateCheckout != null || manga.get().getCopiesAvailable() <= 0){
            throw new Exception("Manga does not exist or already checkout by the user");

        }
        manga.get().setCopiesAvailable(manga.get().getCopiesAvailable() - 1);
        mangaRepository.save(manga.get());

        Checkout checkout = new Checkout(userEmail, LocalDate.now().toString(), LocalDate.now().plusDays(7).toString(), manga.get().getId());
        checkoutRepository.save(checkout);
        return manga.get();
    }

    public boolean checkoutMangaByUser(String userEmail, Long mangaId){
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndMangaId(userEmail, mangaId);
        if(validateCheckout != null){
            return true;
        } else {
            return false;
        }
    }

    public int currentLoansCount(String userEmail){
        return checkoutRepository.findMangasByUserEmail(userEmail).size();
    }

    public List<ShelfCurrentLoansResponse> currentLoans(String userEmail) throws Exception {
        List<ShelfCurrentLoansResponse> shelfCurrentLoansResponses = new ArrayList();
        List<Checkout> checkoutList = checkoutRepository.findMangasByUserEmail(userEmail);
        List<Long> mangaIdList = new ArrayList();

        for (Checkout checkout : checkoutList){
            mangaIdList.add(checkout.getMangaId());
        }

        List<Manga> mangas = mangaRepository.findMangasByMangaIds(mangaIdList);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Manga manga : mangas){
            Optional<Checkout> checkout = checkoutList.stream().filter(x -> x.getMangaId() == manga.getId()).findFirst();
            if(checkout.isPresent()){
                Date d1 = sdf.parse(checkout.get().getReturnDate());
                Date d2 = sdf.parse(LocalDate.now().toString());

                TimeUnit time = TimeUnit.DAYS;
                long difference_In_Time = time.convert(d1.getTime() - d2.getTime(), TimeUnit.MILLISECONDS);

                shelfCurrentLoansResponses.add(new ShelfCurrentLoansResponse(manga, (int)difference_In_Time));
            }
        }

        return shelfCurrentLoansResponses;
    }


    public void returnManga(String userEmail, Long mangaId) throws  Exception{
        Optional<Manga> manga = mangaRepository.findById(mangaId);
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndMangaId(userEmail, mangaId);

        if(!manga.isPresent() || validateCheckout == null){
            throw new Exception("Manga does not exist or not checked out by user");

        }

        manga.get().setCopiesAvailable(manga.get().getCopiesAvailable() + 1);

        mangaRepository.save(manga.get());
        checkoutRepository.deleteById(validateCheckout.getId());

        History history = new History(
                userEmail,
                validateCheckout.getCheckoutDate(),
                LocalDate.now().toString(),
                manga.get().getTitle(),
                manga.get().getAuthor(),
                manga.get().getDescription(),
                manga.get().getImg()
        );
        historyRepository.save(history);
    }

    public void renewLoan(String userEmail, Long mangaId) throws  Exception{
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndMangaId(userEmail, mangaId);
        if(validateCheckout == null){
            throw new Exception("Manga does not exist or not checked out by user");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse(validateCheckout.getReturnDate());
        Date d2 = sdf.parse(LocalDate.now().toString());

        if(d1.compareTo(d2) > 0 || d1.compareTo(d2) == 0){
            validateCheckout.setReturnDate(LocalDate.now().plusDays(7).toString());
            checkoutRepository.save(validateCheckout);
        }
    }




}
