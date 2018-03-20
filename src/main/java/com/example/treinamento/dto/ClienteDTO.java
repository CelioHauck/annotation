package com.example.treinamento.dto;
import com.example.treinamento.annotation.Atributo;
import com.example.treinamento.annotation.Classe;

public class ClienteDTO { 
private String nome; 
private CarroDTO carroDTO; 
private String telefone; 

public ClienteDTO() {
}
public ClienteDTO( String nome, CarroDTO carroDTO, String telefone) {
this.nome = nome;
this.carroDTO = carroDTO;
this.telefone = telefone;
}

public String getNome() { 
return nome;
}
public CarroDTO getCarroDTO() { 
return carroDTO;
}
public String getTelefone() { 
return telefone;
}

public void setNome(String nome) { 
this.nome = nome;
}
public void setCarroDTO(CarroDTO carroDTO) { 
this.carroDTO = carroDTO;
}
public void setTelefone(String telefone) { 
this.telefone = telefone;
}

 }
