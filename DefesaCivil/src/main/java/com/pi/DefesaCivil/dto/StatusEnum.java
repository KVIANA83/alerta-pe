package com.pi.DefesaCivil.dto;

import com.pi.DefesaCivil.exceptions.ValidacaoException;

public enum StatusEnum {

    CRIADO,
    EM_ANDAMENTO,
    CRIAR_PROCESSO,
    RESOLVIDO,
    CANCELADO;


    public static StatusEnum pegarEnumPeloStatus(String status) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (status.equals(statusEnum.name())) {
                return statusEnum;
            }
        }
        throw new ValidacaoException("StatusEnum não encontrado!");
    }
}
