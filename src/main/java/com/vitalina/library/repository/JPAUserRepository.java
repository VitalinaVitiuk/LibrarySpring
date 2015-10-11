package com.vitalina.library.repository;

import com.vitalina.library.domain.Issuance;
import com.vitalina.library.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository("userRepository")
public class JPAUserRepository implements UserRepository {

    @PersistenceContext(name = "persistenceUnit")
    private EntityManager em;

    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByUserName(String username) {
        try {
            User user =em.createNamedQuery(User.FIND_USER_BY_USERNAME, User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return user;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Issuance> showAllIssuances(User user) {
        return user.getIssuances();
    }

    @Override
    public Long save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user.getId();
    }

}