package br.com.openenade.CategoriaAdmin;


import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoriaAdmin {
	
	PUBLICO("Publico"),PRIVADO("Privado");
    
    private String value;
    
    CategoriaAdmin(String value) {
        this.value = value;
    }
 
    @JsonValue
    public String getCategoriaAdm() {
        return value;
    }
}
