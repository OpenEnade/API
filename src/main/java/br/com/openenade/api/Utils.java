package br.com.openenade.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {


    public static ResponseEntity<String> getResponseEntity(HttpStatus status, String message) {

        return ResponseEntity.status(status).body("{\"Response\": \"" + message + "\"}");

    }

}
