package ar.edu.unlam.pb2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Consumidor {
	
	private Integer dni;
	private String nombre;
	private List<MedioDePago> mediosDePagos;
	private HashSet<Compra>comprasRealizadas;

	public Consumidor(Integer dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
		this.mediosDePagos = new ArrayList<>();
		this.comprasRealizadas = new HashSet<>();
	}

	public Integer getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumidor other = (Consumidor) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	public void agregarSuMedioDePago(MedioDePago medioDePago) {
		this.mediosDePagos.add(medioDePago);
		
	}

	public Integer darCantidadDeTusMediosDePagos() {
		return this.mediosDePagos.size();
	}

	public MedioDePago getMedioPagador(long nroTarjeta) {
		for(MedioDePago uno : this.mediosDePagos) {
			if(uno instanceof TarjetaDeDebito) {
				if(((TarjetaDeDebito) uno).getNumero().equals(nroTarjeta)) {
					return uno;
				}
			}else if(uno instanceof TarjetaDeCredito) {
				if(((TarjetaDeCredito) uno).getNumero().equals(nroTarjeta)) {
					return uno;
				}
			}
		}
		return null;
	}

	public boolean existeCuenta(CuentaBancaria cuenta) {
		if(this.mediosDePagos.contains(cuenta))
			return true;
		return false;
	}
	
	
	
	

}
