package ar.edu.unlam.pb2;

public class TarjetaDeCredito extends MedioDePago implements Pagadora {

	private Long numero;
	private String fechaDeVencimiento;
	private Integer codigoDeSeguridad;
	
	private double limiteDeComprasPesos;
	private double limiteDeComprasDolares;
	
	public TarjetaDeCredito(Long numero, String titular, String fechaDeVencimiento, Integer codigoDeSeguridad, Double limiteDeComprasPesos, Double limiteDeComprasDolares) throws NumeroDeTarjetaInvalidoException {
		super(titular);
		if(String.valueOf(numero).length()!=16)
			throw new NumeroDeTarjetaInvalidoException();
		else {
			this.numero = numero;
			this.fechaDeVencimiento = fechaDeVencimiento;
			this.codigoDeSeguridad = codigoDeSeguridad;
			
			this.limiteDeComprasPesos = limiteDeComprasPesos;
			this.limiteDeComprasDolares = limiteDeComprasDolares;
		}
		
	}

	public TarjetaDeCredito(long numero, String titular, String fechaDeVencimiento, Integer codigoDeSeguridad) {
		super(titular);
		this.numero = numero;
		this.fechaDeVencimiento = fechaDeVencimiento;
		this.codigoDeSeguridad = codigoDeSeguridad;
	}

	public double getLimiteDeCompraEnPesos() {
		return limiteDeComprasPesos;
	}

	public double getLimiteDeCompraEnDolares() {
		return limiteDeComprasDolares;
	}
	
	public Long getNumero() {
		return numero;
	}
	
	
	public Integer getCodigoDeSeguridad() {
		return codigoDeSeguridad;
	}
	
	public String getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	
	

}
