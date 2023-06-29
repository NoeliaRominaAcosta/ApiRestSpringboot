package com.itrsa.monolith.dto;

import lombok.Data;

@Data
public class ErrorHTTPDTO {

    private String tipo;

    private int codigo;

    private String estado;

    private String detalle;
}
