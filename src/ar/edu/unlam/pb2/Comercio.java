package ar.edu.unlam.pb2;

public class Comercio {

	private Long cuit;
	private String nombre;
	
	//comienza en 30 y no posee 11 dígitos
	public Comercio(Long cuit, String nombre) throws CuitInvalidoException{
		if(String.valueOf(cuit).length()!=11 || !(String.valueOf(cuit).startsWith("30"))) {
			throw new CuitInvalidoException();
		}else {
			this.cuit = cuit;
			this.nombre = nombre;
		}
		
	}

	public Long getCuit() {
		return cuit;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuit == null) ? 0 : cuit.hashCode());
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
		Comercio other = (Comercio) obj;
		if (cuit == null) {
			if (other.cuit != null)
				return false;
		} else if (!cuit.equals(other.cuit))
			return false;
		return true;
	}
	
	

}
