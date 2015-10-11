package com.vitalina.library.service;

import com.vitalina.library.domain.Book;
import com.vitalina.library.domain.Issuance;
import com.vitalina.library.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface IssuanceService {
    List<Issuance> getAllIssuances ();
    Issuance save(Issuance issuance);
    List<Issuance> getIssuancesByUser(User user);
    List<Issuance> getIssuanceByBook(Book book);
    List<Issuance> findIsuancesBetweenDates(LocalDate fromDate, LocalDate toDate);
    Issuance getIssuanceById(Long id);
    Issuance fulfilAndReturn(Long issuanceId);
    Issuance issueAndReturn(Long issuanceId);
    Issuance closeAndReturn (Long issuanceId);
}
