package br.com.openenade.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public ResourceNotFound(String msg) {
        super(msg);
    }
}
