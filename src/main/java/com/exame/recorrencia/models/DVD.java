/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exame.recorrencia.models;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author CarlosMacaneta
 */
@Entity
public class DVD extends Album {

    private static final long serialVersionUID = 1L;
    
    private String resoucao;

    public DVD() {
    }

    public DVD(Integer id, String nomeDoArtista, String titulo, Integer anoDeLancamento, String ritmo, Double preco, String resoucao) {
        super(id, nomeDoArtista, titulo, anoDeLancamento, ritmo, preco);
        this.resoucao = resoucao;
    }

    public String getResoucao() {
        return resoucao;
    }

    public void setResoucao(String resoucao) {
        this.resoucao = resoucao;
    }
    
}
