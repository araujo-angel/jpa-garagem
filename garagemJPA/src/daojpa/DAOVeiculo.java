package daojpa;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Bilhete;
import modelo.Veiculo;


public class DAOVeiculo extends DAO<Veiculo>{
	public Veiculo read(Object chave) {
		try {
			String placa = (String) chave;
			TypedQuery<Veiculo> q = manager.createQuery("select v from Veiculo v where v.placa=:n", Veiculo.class);
			q.setParameter("n", placa);
			return q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
	
	// --------------------------------------------
	// consultas
	// --------------------------------------------
	
	public List<Veiculo> listarVeiculos(){
		TypedQuery<Veiculo> consulta1 = manager.createQuery("select v from Veiculo v", Veiculo.class);
		List<Veiculo> resultados1 = consulta1.getResultList();
		for (Veiculo v : resultados1)
			System.out.println(v);
		return resultados1;
	}
	
	 public List<Veiculo> readByTempoPermanencia(long horas) {
	        TypedQuery<Veiculo> q = manager.createQuery(
	            "SELECT DISTINCT v FROM Veiculo v JOIN v.bilhetes b " +
	            "WHERE b.dataHoraFinal IS NOT NULL AND " +
	            "FUNCTION('TIMESTAMPDIFF', HOUR, b.dataHoraInicial, b.dataHoraFinal) >= :horas", Veiculo.class);
	        q.setParameter("horas", horas);
	        return q.getResultList();
	    }

	    public List<Veiculo> readAllPlacas(String placa) {
	        TypedQuery<Veiculo> q = manager.createQuery(
	            "SELECT v FROM Veiculo v WHERE v.placa LIKE :placa", Veiculo.class);
	        q.setParameter("placa", "%" + placa + "%");
	        return q.getResultList();
	    }

	    public List<Bilhete> readHistoricoByPlaca(String placa) {
	        TypedQuery<Bilhete> q = manager.createQuery(
	            "SELECT b FROM Bilhete b WHERE b.veiculo.placa = :placa", Bilhete.class);
	        q.setParameter("placa", placa);
	        return q.getResultList();
	    }

	    public List<Veiculo> readByFrequencia(int vezes) {
	        TypedQuery<Veiculo> q = manager.createQuery(
	            "SELECT v FROM Veiculo v WHERE SIZE(v.bilhetes) > :vezes", Veiculo.class);
	        q.setParameter("vezes", vezes);
	        return q.getResultList();
	    }

	    public List<Veiculo> readByDataDeEntrada(LocalDateTime data) {
	        TypedQuery<Veiculo> q = manager.createQuery(
	            "SELECT DISTINCT v FROM Veiculo v JOIN v.bilhetes b WHERE FUNCTION('DATE', b.dataHoraInicial) = :data", Veiculo.class);
	        q.setParameter("data", data.toLocalDate());
	        return q.getResultList();
	    }

	    public List<Veiculo> readByDataDeSaida(LocalDateTime data) {
	        TypedQuery<Veiculo> q = manager.createQuery(
	            "SELECT DISTINCT v FROM Veiculo v JOIN v.bilhetes b WHERE FUNCTION('DATE', b.dataHoraFinal) = :data", Veiculo.class);
	        q.setParameter("data", data.toLocalDate());
	        return q.getResultList();
	    }
	}

