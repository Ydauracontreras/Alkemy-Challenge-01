package ar.alkemy.com.postcrud.services;

import java.util.List;

import ar.alkemy.com.postcrud.entities.Post;
import ar.alkemy.com.postcrud.services.commons.IService;

public interface PostService extends IService<Post> {

    public List<Post> listPost();

    public Post newPost(Post post);

    public Post create(String titulo, String categoria, String contenido, String imagen);

    public boolean updatePost(Integer post, Post p);

}
