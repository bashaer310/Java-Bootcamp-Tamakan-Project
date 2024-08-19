package com.example.tamakanfp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation {

    @Id
    private Integer id;

    @NotEmpty(message = "Reccomendation must not be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private  String reccomendation;

    @NotEmpty(message = "Reccomendation must not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String company;

    @OneToOne
    @MapsId
    @JsonIgnore
    JobApplication jobApplication;


}
