package br.com.openenade.api.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GeneralUtils {


    public static ResponseEntity<String> getResponseEntity(HttpStatus status, String message) {

        return ResponseEntity.status(status).body("{\"Response\": \"" + message + "\"}");

    }

}
