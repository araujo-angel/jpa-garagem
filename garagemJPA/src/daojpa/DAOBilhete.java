package daojpa;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Bilhete;

public class DAOBilhete extends DAO<Bilhete>{
	
	public Bilhete read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Bilhete> q = manager.createQuery("select cod from Bilhete a where b.nome=:n", Bilhete.class);
			q.setParameter("n", nome);
			return q.getSingleResult();

		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Bilhete> listarBilhetes(){
		TypedQuery<Bilhete> consulta2 = manager.createQuery("select b from Bilhete b", Bilhete.class);
		List<Bilhete> resultados2 = consulta2.getResultList();
		for (Bilhete b : resultados2)
			System.out.println(b);
		return resultados2;
	}
	
	public List<Bilhete> readAllCodigos(String codigo) {
	    TypedQuery<Bilhete> q = manager.createQuery(
	        "SELECT b FROM Bilhete b WHERE b.codigoDeBarra LIKE :codigo", Bilhete.class);
	    q.setParameter("codigo", "%" + codigo + "%");

	    return q.getResultList();
	}
	
	 public Bilhete readByDataHoraInicial(String dataHoraInicial) {
	        try {
	            TypedQuery<Bilhete> q = manager.createQuery(
	                "SELECT b FROM Bilhete b WHERE b.dataHoraInicial = :dataHoraInicial", Bilhete.class);
	            q.setParameter("dataHoraInicial", dataHoraInicial);
	            return q.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }

	    public List<Bilhete> readByMes(String mes) {
	        TypedQuery<Bilhete> q = manager.createQuery(
	            "SELECT b FROM Bilhete b WHERE FUNCTION('TO_CHAR', b.dataHoraInicial, 'MM') = :mes", Bilhete.class);
	        q.setParameter("mes", mes);
	        return q.getResultList();
	    }

	    public List<Bilhete> consultarPlaca(String placa) {
	        TypedQuery<Bilhete> q = manager.createQuery(
	            "SELECT b FROM Bilhete b JOIN b.veiculo v WHERE v.placa = :placa", Bilhete.class);
	        q.setParameter("placa", placa);
	        return q.getResultList();
	    }

	    public List<Bilhete> readByValorPagoMaiorQue(double valor) {
	        TypedQuery<Bilhete> q = manager.createQuery(
	            "SELECT b FROM Bilhete b WHERE b.valorPago > :valor", Bilhete.class);
	        q.setParameter("valor", valor);
	        return q.getResultList();
	    }

	    public long countAtivos() {
	        TypedQuery<Long> q = manager.createQuery(
	            "SELECT COUNT(b) FROM Bilhete b WHERE b.dataHoraFinal IS NULL", Long.class);
	        return q.getSingleResult();
	    }

	    public List<Bilhete> readAtivos() {
	        TypedQuery<Bilhete> q = manager.createQuery(
	            "SELECT b FROM Bilhete b WHERE b.dataHoraFinal IS NULL", Bilhete.class);
	        return q.getResultList();
	    }

	    public List<Bilhete> readByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
	        TypedQuery<Bilhete> q = manager.createQuery(
	            "SELECT b FROM Bilhete b WHERE b.dataHoraInicial BETWEEN :inicio AND :fim", Bilhete.class);
	        q.setParameter("inicio", inicio);
	        q.setParameter("fim", fim);
	        return q.getResultList();
	    }
	    
	    public double calcularTotalArrecadadoPorMes(String mes) {
	        TypedQuery<Double> q = manager.createQuery(
	            "SELECT COALESCE(SUM(b.valorPago), 0) FROM Bilhete b " +
	            "WHERE FUNCTION('MONTH', b.dataHoraFinal) = :mes AND b.dataHoraFinal IS NOT NULL", Double.class);
	        q.setParameter("mes", Integer.parseInt(mes));
	        return q.getSingleResult();
	    }
	    
	    public double calcularTotalArrecadado1(LocalDate inicio, LocalDate fim) {
	        // Convertendo LocalDate para LocalDateTime para compatibilidade
	        LocalDateTime inicioDateTime = inicio.atStartOfDay();  // Início do dia
	        LocalDateTime fimDateTime = fim.atTime(23, 59, 59);   // Fim do dia

	        TypedQuery<Double> q = manager.createQuery(
	            "SELECT COALESCE(SUM(b.valorPago), 0) FROM Bilhete b " +
	            "WHERE b.dataHoraFinal BETWEEN :inicio AND :fim", Double.class);
	        q.setParameter("inicio", inicioDateTime);
	        q.setParameter("fim", fimDateTime);

	        return q.getSingleResult();
	    }

	    public double calcularTotalArrecadadoPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
	        TypedQuery<Double> q = manager.createQuery(
	            "SELECT COALESCE(SUM(b.valorPago), 0) FROM Bilhete b " +
	            "WHERE b.dataHoraFinal BETWEEN :inicio AND :fim", Double.class);
	        q.setParameter("inicio", inicio);
	        q.setParameter("fim", fim);

	        return q.getSingleResult();
	    }

}