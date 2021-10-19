/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exame.recorrencia.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author CarlosMacaneta
 */
@Entity
public class CD extends Album {

    private static final long serialVersionUID = 1L;
    
    private Integer quantidadeDeMusicas;

    public CD() {
    }

    public CD(Integer id, String nomeDoArtista, String titulo, Integer anoDeLancamento, String ritmo, Double preco, Integer quantidadeDeMusicas) {
        super(id, nomeDoArtista, titulo, anoDeLancamento, ritmo, preco);
        this.quantidadeDeMusicas = quantidadeDeMusicas;
    }

    public Integer getQuantidadeDeMusicas() {
        return quantidadeDeMusicas;
    }

    public void setQuantidadeDeMusicas(Integer quantidadeDeMusicas) {
        this.quantidadeDeMusicas = quantidadeDeMusicas;
    }
    
}
