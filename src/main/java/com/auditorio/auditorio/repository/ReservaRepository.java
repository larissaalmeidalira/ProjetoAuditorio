package com.auditorio.auditorio.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.auditorio.auditorio.model.Reserva;


public interface ReservaRepository extends PagingAndSortingRepository<Reserva, Long>{
	
	public Reserva findByDataEvento(Calendar dataEvento);
	
	public List<Reserva> findByManhaAndTardeAndNoite(boolean manha, boolean tarde, boolean noite);
	
	
	
	

}
