package com.example.tamakanfp.DTO;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Valid
public class RecommendationDTO {

    @NotEmpty(message = "reccomendation must not be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private  String reccomendation;

    //@NotEmpty(message = "company must not be empty")
    @Column(columnDefinition = "varchar(25)")
    private String company;
}