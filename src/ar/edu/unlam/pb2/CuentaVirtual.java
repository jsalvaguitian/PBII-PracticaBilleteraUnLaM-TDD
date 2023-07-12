package ar.edu.unlam.pb2;

public class CuentaVirtual extends MedioDePago implements Transferible{
	private String cvu;
	private String entidad;
	
	public CuentaVirtual(String cvu, String entidad, String titular) throws CVUInvalidoException{
		super(titular);
		if(cvu.length()!= 23) {
			throw new CVUInvalidoException();
		}else {
			this.cvu = cvu;
			this.entidad = entidad;
		}
		
	}
	
	public String getCvu() {
		return cvu;
	}
	
	public String getEntidad() {
		return entidad;
	}

	

}
