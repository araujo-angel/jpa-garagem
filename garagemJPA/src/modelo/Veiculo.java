package modelo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "veiculo20231370001")
public class Veiculo {
	@Id
    private String placa;

    @OneToMany(mappedBy = "veiculo", cascade= {CascadeType.PERSIST, CascadeType.MERGE},
			orphanRemoval = true)
    
    private List<Bilhete> bilhetes = new ArrayList<>();

    public Veiculo() {}

    public Veiculo(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String novaPlaca) {
        this.placa = novaPlaca;
    }

    public List<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public void adicionarBilhete(Bilhete bilhete) {
        bilhetes.add(bilhete);
        bilhete.setVeiculo(this);
    }

    public void removerBilhete(Bilhete bilhete) {
        bilhetes.remove(bilhete);
        bilhete.setVeiculo(null);
    }

    @Override
    public String toString() {
        return "Veiculo [placa=" + placa + ", bilhetes=" + bilhetes + "]";
    }
}
