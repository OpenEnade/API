package br.com.openenade.api.modalidade;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Modalidade {
    
    @Enumerated(EnumType.ORDINAL)
    EDUCACAO_PRESENCIAL("Educação Presencial", 0), EDUCACAO_A_DISTANCIA("Educação à Distância", 1);

    private String value;
    private Integer id;

    private Modalidade() {
        
    }

    private Modalidade(String value, Integer id) {
        this.value = value;
        this.id = id;
    }

    @JsonValue
    public String getValue() {
        return this.value + " - " + this.id;
    }
    
}
