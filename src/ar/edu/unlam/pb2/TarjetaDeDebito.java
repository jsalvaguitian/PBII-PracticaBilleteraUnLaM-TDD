package ar.edu.unlam.pb2;

public class TarjetaDeDebito extends MedioDePago implements Pagadora{
	private Long numero;
	private String fechaDeVencimiento;
	private Integer codigoDeSeguridad;
	
	private Double saldo;

	public TarjetaDeDebito(Long numero, String titular, String fechaDeVencimiento, Integer codigoDeSeguridad) {
		super(titular);
		this.numero = numero;
		this.fechaDeVencimiento = fechaDeVencimiento;
		this.codigoDeSeguridad = codigoDeSeguridad;
		
		this.saldo = 0.0;
	}

	public Double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(Double monto) {
		this.saldo = monto;
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

	public void decrementarSaldo(double montoARestar){
		if(this.saldo>= montoARestar) {
			this.saldo-= montoARestar;
		}
		
	}



	
}
