package ar.alkemy.com.postcrud.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import ar.alkemy.com.postcrud.entities.Post;
import ar.alkemy.com.postcrud.repositories.PostRepository;
import ar.alkemy.com.postcrud.services.commons.GenericService;

@Service
public class PostServiceImp extends GenericService<Post> implements PostService {

    public PostRepository repo() {
        return (PostRepository) repository;
    }

    @Override
    public List<Post> listPost() {
        return repo().findByOrderByFechaDeCreacionDesc().stream().map(p -> {
            Post post = new Post();
            post.setId(p.getId());
            post.setTitulo(p.getTitulo());
            post.setCategoria(p.getCategoria());
            post.setImagen(p.getImagen());
            post.setFechaDeCreacion(p.getFechaDeCreacion());
            return post;
        }).collect(Collectors.toList());
    }

    @Override
    public Post newPost(Post post) {
        post.setFechaDeCreacion(new Date());
        create(post);
        return post;
    }

    @Override
    public Post create(String titulo, String categoria, String contenido, String imagen) {
        Post p = new Post();
        p.setTitulo(titulo);
        p.setImagen(imagen);
        p.setCategoria(categoria);
        p.setContenido(contenido);
        p.setFechaDeCreacion(new Date());
        create(p);
        return p;
    }

    @Override
    public boolean updatePost(Integer id, Post postN) {
        Post postO = findById(id);
        postO.setCategoria(postN.getCategoria());
        postO.setContenido(postN.getContenido());
        postO.setImagen(postN.getImagen());
        postO.setTitulo(postN.getTitulo());
        update(postO);
        return true;
    }

}