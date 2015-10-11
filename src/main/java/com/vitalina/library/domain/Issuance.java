package com.vitalina.library.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "issuance")
@NamedQueries({
        @NamedQuery(name = Issuance.FIND_All, query = "SELECT issuance FROM Issuance issuance"),
        @NamedQuery(name = Issuance.FIND_BETWEEN_DATES, query = "select issuance from Issuance issuance WHERE issuance.startDate BETWEEN :fromDate AND :toDate"),
        @NamedQuery(name = Issuance.GET_ISSUANCES_BY_USER, query = "SELECT issuance FROM Issuance issuance WHERE issuance.user =:user"),
})

public class Issuance {
    public static final String FIND_All = "FIND_ALL";
    public static final String FIND_BETWEEN_DATES = "FIND_BETWEEN_DATES";
    public static final String GET_ISSUANCES_BY_USER = "GET_ISSUANCES_BY_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @OneToOne
    private Book book;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private IssuanceStatus status;
    @Enumerated(EnumType.STRING)
    private IssuanceType issuanceType;
    private LocalDate startDate;
    private LocalDate endDate;

    public Issuance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IssuanceStatus getStatus() {
        return status;
    }

    public void setStatus(IssuanceStatus status) {
        this.status = status;
    }

    public IssuanceType getIssuanceType() {
        return issuanceType;
    }

    public void setIssuanceType(IssuanceType issuanceType) {
        this.issuanceType = issuanceType;
    }


    public void defineDates() {
        LocalDate startDate = LocalDate.now();
        this.setStartDate(startDate);
        LocalDate endDate;
        if (this.getIssuanceType() == IssuanceType.TWO_WEEKS) {
            Period period = Period.of(0, 0, 14);
            endDate = startDate.plus(period);
            this.setEndDate(endDate);
        } else if (this.getIssuanceType() == IssuanceType.THREE_MONTHS) {
            Period period = Period.of(0, 3, 0);
            endDate = startDate.plus(period);
            this.setEndDate(endDate);
        } else {
            endDate = startDate;
            this.setEndDate(endDate);
        }
    }

}