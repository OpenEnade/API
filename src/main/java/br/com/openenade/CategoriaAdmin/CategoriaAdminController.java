package br.com.openenade.CategoriaAdmin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = CategoriaAdminController.ENDPOINT)
public class CategoriaAdminController {
    
    public static final String ENDPOINT = "/CategoriaAdmin";
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<CategoriaAdmin[]> getCategoriaAdmin() {
        
        return new ResponseEntity<>(CategoriaAdmin.values(), HttpStatus.OK);
    }
   
}
