package ar.alkemy.com.postcrud.constrollers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ar.alkemy.com.postcrud.entities.Post;
import ar.alkemy.com.postcrud.models.errors.ErrorResponseItem;
import ar.alkemy.com.postcrud.models.requests.*;
import ar.alkemy.com.postcrud.models.response.*;
import ar.alkemy.com.postcrud.services.PostServiceImp;

@RestController
public class PostRestController {
    @Autowired
    PostServiceImp postServiceImp;

    @PostMapping("/posts")
    public ResponseEntity<?> crearPost(@Valid @RequestBody PostRequest postR, BindingResult results) {
        newPostResponse r = new newPostResponse();
        if (results.hasErrors()) {
            r.isOk = false;
            r.message = "Error al registar el Post";
            results.getFieldErrors().stream().forEach(e -> {
                r.errors.add(new ErrorResponseItem(e.getField(), e.getDefaultMessage()));
            });

            return ResponseEntity.badRequest().body(r);
        }

        Post post = postServiceImp.create(postR.titulo, postR.categoria, postR.contenido, postR.imagen);
        r.isOk = true;
        r.message = "Post creado con exito";
        r.postId = post.getId();
        return ResponseEntity.ok(r);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> listAllPost() {
        GenericResponse r = new GenericResponse();
        List<PostResponse> posts = postServiceImp.findAll().stream().map(p -> {
            PostResponse post = new PostResponse();
            post.id = p.getId();
            post.titulo = p.getTitulo();
            post.categoria = p.getCategoria();
            post.imagen = p.getImagen();
            post.fechaDeCreacion = p.getFechaDeCreacion();
            return post;
        }).collect(Collectors.toList());
        if (posts.size() == 0) {
            r.isOk = false;
            r.message = " No existen post Registrados";
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        GenericResponse r = new GenericResponse();
        Post post = postServiceImp.findById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        }
        r.isOk = false;
        r.message = "El post indicado no existe";
        return ResponseEntity.badRequest().body(r);

    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<?> deletedPost(@PathVariable Integer id) {
        GenericResponse r = new GenericResponse();
        Post post = postServiceImp.findById(id);
        if (post != null) {
            postServiceImp.delete(id);
            r.isOk = true;
            r.id = post.getId();
            r.message = "Eliminaste con existo el Post";
            return ResponseEntity.ok(r);
        }
        r.isOk = false;
        r.message = "El post indicado no existe";
        return ResponseEntity.badRequest().body(r);
    }

    @PatchMapping("posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id, @RequestBody Post pN) {
        GenericResponse r = new GenericResponse();
        Post postO = postServiceImp.findById(id);
        if (postO == null) {
            r.isOk = false;
            r.message = "El post indicado no existe";
            return ResponseEntity.badRequest().body(r);
        }
        boolean resultado = postServiceImp.updatePost(id, pN);
        if (resultado) {
            r.isOk = true;
            r.id = postO.getId();
            r.message = "Editaste con existo el Post";
            return ResponseEntity.ok(r);
        }

        r.isOk = false;
        r.message = "No se puedo actualizar el post indicado";
        return ResponseEntity.badRequest().body(r);

    }

}