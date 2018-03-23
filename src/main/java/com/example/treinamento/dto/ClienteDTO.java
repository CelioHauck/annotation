package com.example.treinamento.dto; 

import java.util.List; 

public class ClienteDTO { 

    private String telefone; 
    private String nome; 
    private List<CarroDTO> carros; 

    public String getTelefone() { 
        return this.telefone; 
    } 

    public String getNome() { 
        return this.nome; 
    } 

    public List<CarroDTO> getCarros() { 
        return this.carros; 
    } 

    public void setTelefone(String telefone) { 
        this.telefone = telefone; 
    } 

    public void setNome(String nome) { 
        this.nome = nome; 
    } 

    public void setCarros(List<CarroDTO> carros) { 
        this.carros = carros; 
    } 

}
