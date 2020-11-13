package ar.alkemy.com.postcrud.constrollers;

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

}