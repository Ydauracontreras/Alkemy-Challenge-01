package ar.alkemy.com.postcrud.models.response;

import java.util.*;

import ar.alkemy.com.postcrud.models.errors.ErrorResponseItem;

public class newPostResponse {
    public boolean isOk = false;
    public String message = "";
    public Integer postId;

    public List<ErrorResponseItem> errors = new ArrayList<>();
}