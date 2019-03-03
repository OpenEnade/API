package br.com.openenade.api.modalidade;

import br.com.openenade.api.exceptions.ResourceNotFound;

public class ModalidadeService {

    public static Modalidade getModalidadeById(Integer id) {
        Modalidade[] modalidades = Modalidade.values();
        if (id < 0 || id > modalidades.length) {
            throw new ResourceNotFound(
                    "Cannot find Modalidade with Id [" + Integer.toString(id) + "]");
        } else {
            return modalidades[id];
        }
    }

}
