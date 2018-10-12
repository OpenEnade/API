package br.com.openenade.CategoriaAdmin;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = CategoriaAdminController.ENDPOINT)
public class CategoriaAdminController {
    
    public static final String ENDPOINT = "/CategoriaAdmin";
    
    public CategoriaAdmin[] getCategoriaAdmin() {
        
        return CategoriaAdmin.values();
    }
    
    
}
