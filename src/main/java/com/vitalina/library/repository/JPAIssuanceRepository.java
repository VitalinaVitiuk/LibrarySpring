package com.vitalina.library.repository;

import com.vitalina.library.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository("issuanceRepository")
public class JPAIssuanceRepository implements IssuanceRepository {

    @PersistenceContext(name = "persistenceUnit")
    private EntityManager em;

    @Override
    public List<Issuance> getAllIssuances() {
        return em.createNamedQuery(Issuance.FIND_All, Issuance.class)
                .getResultList();
    }

    @Override
    public List<Issuance> findIsuancesBetweenDates(LocalDate fromDate, LocalDate toDate) {
        return em.createNamedQuery(Issuance.FIND_BETWEEN_DATES, Issuance.class)
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .getResultList();
    }

    @Override
    public List<Issuance> getIssuancesByUser(User user) {
        return em.createNamedQuery(Issuance.GET_ISSUANCES_BY_USER, Issuance.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Transactional
    @Override
    public Issuance save(Issuance issuance) {
        em.persist(issuance);
        return issuance;
    }


    @Override
    public Issuance getIssuanceById(Long id) {
        Issuance issuance = em.find(Issuance.class, id);
        return issuance;
    }

    @Override
    public List<Issuance> getIssuanceByBook(Book book) {
        return null;
    }
}
