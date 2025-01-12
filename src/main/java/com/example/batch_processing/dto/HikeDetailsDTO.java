package com.example.batch_processing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HikeDetailsDTO {

    @JsonProperty("altitude_maxi")
    private String altitudeMaxi;

    @JsonProperty("initiateur")
    private String initiateur;

    @JsonProperty("aide")
    private String aide;

    @JsonProperty("massif")
    private String massif;

    @JsonProperty("niveau_physique")
    private String niveauPhysique;

    @JsonProperty("niveau_technique")
    private String niveauTechnique;

    @JsonProperty("n_de_carte")
    private String nDeCarte;

    @JsonProperty("type")
    private String type;

    @JsonProperty("denivele")
    private String denivele;

    @JsonProperty("rdv_autre")
    private String rdvAutre;

    @JsonProperty("observations")
    private String observations;

    @JsonProperty("cloture")
    private String cloture;
}
