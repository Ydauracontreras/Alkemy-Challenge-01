package ar.alkemy.com.postcrud.models.requests;

import javax.validation.constraints.NotBlank;

public class PostRequest {
    @NotBlank(message = "El titulo no puede estar vacio")
    public String titulo;
    @NotBlank(message = "El contenido no puede estar vacio")
    public String contenido;
    public String categoria;
    public String imagen;
}
