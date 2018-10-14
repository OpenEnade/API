package br.com.openenade.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AnoUtils {


    public static ResponseEntity<String> getResponseEntity(HttpStatus status, String message) {

        return ResponseEntity.status(status).body("{\"Response\": \"" + message + "\"}");

    }

}
