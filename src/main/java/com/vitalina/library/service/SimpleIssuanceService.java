package com.vitalina.library.service;

import com.vitalina.library.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vitalina.library.repository.IssuanceRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class SimpleIssuanceService implements IssuanceService {

    @Autowired
    IssuanceRepository issuanceRepository;

    @Override
    @Transactional
    public List<Issuance> getAllIssuances() {
        return issuanceRepository.getAllIssuances();
    }

    @Override
    @Transactional
    public Issuance save(Issuance issuance) {
        return issuanceRepository.save(issuance);
    }

    @Override
    @Transactional
    public List<Issuance> getIssuancesByUser(User user) {
        return issuanceRepository.getIssuancesByUser(user);
    }

    @Override
    @Transactional
    public List<Issuance> getIssuanceByBook(Book book) {
        return issuanceRepository.getIssuanceByBook(book);
    }

    @Override
    @Transactional
    public List<Issuance> findIsuancesBetweenDates(LocalDate fromDate, LocalDate toDate) {
        return issuanceRepository.findIsuancesBetweenDates(fromDate, toDate);
    }

    @Override
    @Transactional
    public Issuance getIssuanceById(Long id) {
        return issuanceRepository.getIssuanceById(id);
    }

    @Override
    @Transactional
    public Issuance fulfilAndReturn(Long issuanceId) {
        Issuance issuance = getIssuanceById(issuanceId);
        issuance.setStatus(IssuanceStatus.READY);
        return issuance;
    }

    @Override
    @Transactional
    public Issuance issueAndReturn(Long issuanceId) {
        Issuance issuance = getIssuanceById(issuanceId);
        issuance.setStatus(IssuanceStatus.ACTIVE);
        issuance.defineDates();
        return issuance;
    }

    @Override
    @Transactional
    public Issuance closeAndReturn(Long issuanceId) {
        Issuance issuance = getIssuanceById(issuanceId);
        issuance.setStatus(IssuanceStatus.CLOSED);

        LocalDate endDate = LocalDate.now();
        issuance.setEndDate(endDate);

        issuance.getBook().setStatus(BookStatus.AVAILABLE);

        return issuance;
    }

}
