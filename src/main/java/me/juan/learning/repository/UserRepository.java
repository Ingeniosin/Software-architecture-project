package me.juan.learning.repository;

import me.juan.learning.api.database.CustomRepository;
import me.juan.learning.api.rest.Request;
import me.juan.learning.api.rest.Utils;
import me.juan.learning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


interface UserCustomRepository extends CustomRepository<User> { }

class UserCustomRepositoryImpl implements UserCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findByRequest(Request request) {
        Query nativeQuery = entityManager.createNativeQuery(Utils.toQuery("user", request), User.class);
        return nativeQuery.getResultList();
    }
}

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserCustomRepository { }
