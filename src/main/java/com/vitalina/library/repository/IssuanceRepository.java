package com.vitalina.library.repository;

import com.vitalina.library.domain.Book;
import com.vitalina.library.domain.Issuance;
import com.vitalina.library.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface IssuanceRepository {

    List<Issuance> getAllIssuances ();
    List<Issuance> findIsuancesBetweenDates(LocalDate fromDate, LocalDate toDate);
    Issuance save(Issuance issuance);
    Issuance getIssuanceById(Long id);
    List<Issuance> getIssuancesByUser(User user);
    List<Issuance> getIssuanceByBook(Book book);
}
