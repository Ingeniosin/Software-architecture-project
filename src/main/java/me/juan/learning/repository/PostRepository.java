package me.juan.learning.repository;

import me.juan.learning.api.database.CustomRepository;
import me.juan.learning.api.rest.Request;
import me.juan.learning.api.rest.Utils;
import me.juan.learning.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


interface PostCustomRepository extends CustomRepository<Post> { }

class PostCustomRepositoryImpl implements PostCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> findByRequest(Request request) {
        Query nativeQuery = entityManager.createNativeQuery(Utils.toQuery("post", request), Post.class);
        return nativeQuery.getResultList();
    }
}

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, PostCustomRepository { }