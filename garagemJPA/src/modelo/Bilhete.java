package modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name= "bilhete20231370001")
public class Bilhete {

	private String codigoDeBarra;
	private LocalDateTime dataHoraInicial;
	private LocalDateTime dataHoraFinal;
	private double valorPago;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    //@JoinColumn(name = "veiculo_placa", nullable = false)
    private Veiculo veiculo;

    public Bilhete() {}

    public Bilhete(Veiculo veiculo, LocalDateTime dataHoraInicial) {
        this.veiculo = veiculo;
        this.dataHoraInicial = dataHoraInicial;
        this.codigoDeBarra = gerarCodigoDeBarra();
    }

    private String gerarCodigoDeBarra() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(1000);
        return String.format("%02d%02d%04d%03d",
                dataHoraInicial.getDayOfMonth(),
                dataHoraInicial.getMonthValue(),
                dataHoraInicial.getYear(),
                numeroAleatorio);
    }

    private void calcularValorPago() {
        if (dataHoraFinal != null) {
            long horas = java.time.Duration.between(dataHoraInicial, dataHoraFinal).toHours();
            this.valorPago = Math.max(2.0, Math.ceil(horas) * 2.0);
        }
    }

    public void setDataHoraFinal(LocalDateTime dataHoraFinal) {
        if (dataHoraFinal.isBefore(dataHoraInicial)) {
            throw new IllegalArgumentException("Data/hora final não pode ser anterior à inicial.");
        }
        this.dataHoraFinal = dataHoraFinal;
        calcularValorPago();
    }

    public String getCodigoDeBarra() {
        return codigoDeBarra;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
	public double getValorPago() {
		return valorPago;
	}
	
	public LocalDateTime getDataHoraInicial() {
		return dataHoraInicial;
	}
	
	public LocalDateTime getDataHoraFinal() {
		return dataHoraFinal;
	}
    @Override
    public String toString() {
        return "Bilhete [codigoDeBarra=" + codigoDeBarra +
                ", veiculo=" + (veiculo != null ? veiculo.getPlaca() : "null") +
                ", dataHoraInicial=" + dataHoraInicial +
                ", dataHoraFinal=" + dataHoraFinal +
                ", valorPago=" + valorPago + "]";
    }
}
