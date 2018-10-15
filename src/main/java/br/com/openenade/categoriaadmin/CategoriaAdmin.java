package br.com.openenade.categoriaadmin;


import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoriaAdmin {
	
	PUBLICO("Publico"),PRIVADO("Privado");
    
    private String value;
    
    private CategoriaAdmin() {
        
    }
    
    private CategoriaAdmin(String value) {
        this.value = value;
    }
 
    @JsonValue
    public String getValue() {
        return this.value;
    }
}
