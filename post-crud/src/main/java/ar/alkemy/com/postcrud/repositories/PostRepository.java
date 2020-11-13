package ar.alkemy.com.postcrud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.alkemy.com.postcrud.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByOrderByFechaDeCreacionDesc();

}
