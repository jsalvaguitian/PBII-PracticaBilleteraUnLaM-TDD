package ar.edu.unlam.pb2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Billetera {

	private String nombre;
	private HashSet<Comercio> listaDeComercios;
	private HashSet<Consumidor> consumidores;
	private HashMap<Compra, MedioDePago> comprasRealizadasDeTodosLosConsumidores;

	
	public Billetera(String nombre) {
		this.nombre = nombre;
		this.listaDeComercios = new HashSet<>();
		this.consumidores  = new HashSet<>();
		this.comprasRealizadasDeTodosLosConsumidores = new HashMap<>();
	}

	public void agregarComercio(Comercio comercio) {
		this.listaDeComercios.add(comercio);
		
	}

	public Integer getCantidadDeComercios() {
		return this.listaDeComercios.size();
	}

	public void agregarConsumidor(Consumidor consumidor) {
		this.consumidores.add(consumidor);
		
	}

	public Integer getCantidadDeConsumidores() {
		return this.consumidores.size();
	}

	public void agregarMedioDePago(Integer dniConsumidor, MedioDePago medioDePago) throws NoCoincideTitularException {
		Consumidor buscado = this.getConsumidor(dniConsumidor);
		
		if(buscado!=null) {
			if(buscado.getNombre().equals(medioDePago.getTitular()))
				buscado.agregarSuMedioDePago(medioDePago);
			else
				throw new NoCoincideTitularException();
		}
		
	}

	public Consumidor getConsumidor(Integer dniConsumidor) {
		for(Consumidor uno: this.consumidores) {
			if(uno.getDni().equals(dniConsumidor)) {
				return uno;
			}
		}
		return null;
	}

	public Integer getCantidadDeMediosDePago(int dniConsumidor) {
		Consumidor buscado = this.getConsumidor(dniConsumidor);
		
		if(buscado!=null) {
			return buscado.darCantidadDeTusMediosDePagos();
		}
		return 0;
	}

	public Compra generarCodigoQR(long cuitComercio, double montoAPagar) {
		Comercio buscado = this.buscarComercio(cuitComercio);
		
		if(buscado!=null) {
			Long codigo = this.generarNumeroQR();
			Compra compraQR = new Compra(codigo,cuitComercio, montoAPagar);
			
			return compraQR;
		}
		
		return null;	
	}

	private Long generarNumeroQR() {
		Long codigo = 0l;
		do {
			codigo = (long)Math.random()*999999+1;
		}while(this.existeCodigoQR(codigo));
		
		return codigo;
	}

	private Boolean existeCodigoQR(Long codigo) {
		for(Map.Entry<Compra, MedioDePago> unPar : this.comprasRealizadasDeTodosLosConsumidores.entrySet()) {
			if(unPar.getKey().getCodigo() == codigo) {
				return true;
			}
			
		}
		return false;
	}

	public Comercio buscarComercio(Long cuitComercio) {
		for(Comercio uno :this.listaDeComercios) {
			if(uno.getCuit().equals(cuitComercio)) {
				return uno;
			}
		}
		return null;
	}

	public Boolean pagar(Compra compraQR, MedioDePago medioPagador) throws SaldoInsuficienteException, ExcedeLimiteDeCompraException {
		if(compraQR != null && medioPagador!= null) {
			
			if(medioPagador instanceof TarjetaDeDebito) {
				if(((TarjetaDeDebito) medioPagador).getSaldo()>= compraQR.getMontoAPagar()) {
					((TarjetaDeDebito) medioPagador).decrementarSaldo(compraQR.getMontoAPagar());
					this.comprasRealizadasDeTodosLosConsumidores.put(compraQR, medioPagador);
					return true;
				}else
					throw new SaldoInsuficienteException();
				
			}else if(medioPagador instanceof TarjetaDeCredito) {
				if(((TarjetaDeCredito) medioPagador).getLimiteDeCompraEnPesos() >= compraQR.getMontoAPagar()) {
					this.comprasRealizadasDeTodosLosConsumidores.put(compraQR, medioPagador);
					return true;
				}else {
					throw new ExcedeLimiteDeCompraException();
				}
			}else {
				this.comprasRealizadasDeTodosLosConsumidores.put(compraQR, medioPagador);
				return true;
			}
			
		}
		return false;
	}

	public Boolean transferir(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, Double monto) throws SaldoInsuficienteException {
		if(this.existeCuentaBancaria(cuentaOrigen) && this.existeCuentaBancaria(cuentaDestino)){
			cuentaOrigen.transferir(monto, cuentaDestino);
			return true;
			
		}
		return false;
	}

	private boolean existeCuentaBancaria(CuentaBancaria cuenta) {
		for(Consumidor uno : this.consumidores) {
			if(uno.existeCuenta(cuenta))
				return true;
		}
		return false;
	}
	
	

}
