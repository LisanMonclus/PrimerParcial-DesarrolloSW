package com.example.Primer.Parcial;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "dna_sequences")
public class DnaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, unique = true)
    private String dnaSequence;

    @Column(nullable = false)
    private boolean isMutant;

    public DnaEntity() {
    }

    public DnaEntity(String dnaSequence, boolean isMutant) {
        this.dnaSequence = dnaSequence;
        this.isMutant = isMutant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDnaSequence() {
        return dnaSequence;
    }

    public void setDnaSequence(String dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
