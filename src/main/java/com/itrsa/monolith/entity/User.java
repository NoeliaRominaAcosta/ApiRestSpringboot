package com.itrsa.monolith.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String login;
    private long id;
    private String url;
    private String company;
    private String blog;
    private String email;
    private String bio;
}
