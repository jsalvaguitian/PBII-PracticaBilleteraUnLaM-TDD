package ar.edu.unlam.pb2;

public class CuentaBancaria extends MedioDePago implements Transferible{
	
	private String cbu;
	private String entidad;
	private Double saldo;
	
	public CuentaBancaria(String cbu, String entidad, String titular) throws CBUInvalidoException{
		super(titular);
		if(cbu.length()!= 20) {
			throw new CBUInvalidoException();
		}else {
			this.cbu = cbu;
			this.entidad = entidad;
			this.saldo = 0.0;
		}
	}

	public String getCbu() {
		return cbu;
	}
	
	public String getEntidad() {
		return entidad;
	}

	public Double getSaldo() {
		return saldo;
	}

	public Boolean setSaldo(Double saldo) {
		this.saldo += saldo;
		return true;
	}

	public void transferir(Double monto, CuentaBancaria cuentaDestino) throws SaldoInsuficienteException{
		if(this.getSaldo()>=monto) {
			cuentaDestino.setSaldo(monto);
			
		}else {
			throw new SaldoInsuficienteException();
		}
	}

	

}
