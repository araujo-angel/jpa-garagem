package modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "bilhete")
public class Bilhete {

    @Id
    @Column(name = "codigo_de_barra", unique = true, nullable = false)
    private String codigoDeBarra;

    @Column(name = "data_hora_inicial", nullable = false)
    private LocalDateTime dataHoraInicial;

    @Column(name = "data_hora_final")
    private LocalDateTime dataHoraFinal;

    @Column(name = "valor_pago")
    private double valorPago;

    @ManyToOne
    @JoinColumn(name = "veiculo_placa", nullable = false)
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

    @Override
    public String toString() {
        return "Bilhete [codigoDeBarra=" + codigoDeBarra +
                ", veiculo=" + (veiculo != null ? veiculo.getPlaca() : "null") +
                ", dataHoraInicial=" + dataHoraInicial +
                ", dataHoraFinal=" + dataHoraFinal +
                ", valorPago=" + valorPago + "]";
    }
}
