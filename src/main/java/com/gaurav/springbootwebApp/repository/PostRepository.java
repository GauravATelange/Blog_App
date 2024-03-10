package com.gaurav.springbootwebApp.repository;

import com.gaurav.springbootwebApp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findByUrl(String url);

    @Query("select p from Post p where " +
            "p.title like concat('%', :query, '%') or " +
            "p.shortDescription like concat('%', :query, '%')")
    List<Post> searchPosts( String query);

    @Query(value = "select * from posts p where p.created_by =:userId",nativeQuery = true)
    List<Post> findPostByUser(Long userId);

}
