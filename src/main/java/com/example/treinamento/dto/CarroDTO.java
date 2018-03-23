package com.example.treinamento.dto; 

import java.util.List; 
import com.example.treinamento.annotation.AtributoClasseDTO; 
import com.example.treinamento.annotation.ClasseModeloDTO; 

public class CarroDTO { 

    private String marca; 
    private List<String> cores; 
    private String modelo; 

    public String getMarca() { 
        return this.marca; 
    } 

    public List<String> getCores() { 
        return this.cores; 
    } 

    public String getModelo() { 
        return this.modelo; 
    } 

    public void setMarca(String marca) { 
        this.marca = marca; 
    } 

    public void setCores(List<String> cores) { 
        this.cores = cores; 
    } 

    public void setModelo(String modelo) { 
        this.modelo = modelo; 
    } 

}
