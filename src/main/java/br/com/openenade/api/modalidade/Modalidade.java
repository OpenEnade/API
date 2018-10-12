package br.com.openenade.api.modalidade;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Modalidade {
    EDUCACAO_PRESENCIAL("Educação Presencial"), EDUCACAO_A_DISTANCIA("Educação à Distância");

    private String value;

    private Modalidade() {
        
    }

    private Modalidade(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

}
