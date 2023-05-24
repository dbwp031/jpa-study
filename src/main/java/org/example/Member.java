package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String city;
    private String street;
    private String zipcode;

}
