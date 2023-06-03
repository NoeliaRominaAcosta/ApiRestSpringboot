package com.itrsa.monolith.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorHTTPDTO {

    private String tipo;
    private int codigo;
    private String estado;
    private String detalle;
}
