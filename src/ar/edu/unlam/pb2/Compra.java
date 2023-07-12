package ar.edu.unlam.pb2;

public class Compra {
	private Long codigo;
	private Long cuitComercio;
	private Double montoAPagar;

	public Compra(long codigo,long cuitComercio, double montoAPagar) {
		this.codigo = codigo;
		this.cuitComercio = cuitComercio;
		this.montoAPagar = montoAPagar;
	}

	public long getCodigo() {
		return codigo;
	}

	public long getCuitComercio() {
		return cuitComercio;
	}

	public double getMontoAPagar() {
		return montoAPagar;
	}
	
	
	
	

}
