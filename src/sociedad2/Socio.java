package sociedad2;

public class Socio {
	 
	 int socioId;
	 String usuario;
	 String nombre;
	 String apellido1;
	 String apellido2;
	 String poblacion;
	 String direccion;
	 String telefono;
	 Boolean estado;
	 String cuentaCorriente;
	 
	 public Socio (int socioId, String usuario, String nombre, String apellido1, String apellido2, String poblacion, 
	   String direccion, String telefono,Boolean estado, String cuentaCorriente) {
	  this.socioId = socioId;
	  this.usuario= usuario;
	  this.nombre= nombre;
	  this.apellido1= apellido1;
	  this.apellido2= apellido2;
	  this.poblacion= poblacion;
	  this.direccion = direccion;
	  this.telefono= telefono;
	  this.estado = estado;
	  this.cuentaCorriente= cuentaCorriente;
	 
	 }

	 

	public Boolean getEstado() {
		return estado;
	}



	public void setEstado(Boolean estado) {
		this.estado = estado;
	}



	public int getSocioId() {
		return socioId;
	}


	public void setSocioId(int socioId) {
		this.socioId = socioId;
	}

	public String getUsuario() {
	  return usuario;
	 }

	 public void setUsuario(String usuario) {
	  this.usuario = usuario;
	 }

	 public String getNombre() {
	  return nombre;
	 }

	 public void setNombre(String nombre) {
	  this.nombre = nombre;
	 }

	 public String getApellido1() {
	  return apellido1;
	 }

	 public void setApellido1(String apellido1) {
	  this.apellido1 = apellido1;
	 }

	 public String getApellido2() {
	  return apellido2;
	 }

	 public void setApellido2(String apellido2) {
	  this.apellido2 = apellido2;
	 }

	 public String getPoblacion() {
	  return poblacion;
	 }

	 public void setPoblacion(String poblacion) {
	  this.poblacion = poblacion;
	 }

	 public String getDireccion() {
	  return direccion;
	 }

	 public void setDireccion(String direccion) {
	  this.direccion = direccion;
	 }

	 public String getTelefono() {
	  return telefono;
	 }

	 public void setTelefono(String telefono) {
	  this.telefono = telefono;
	 }

	 public String getCuentaCorriente() {
	  return cuentaCorriente;
	 }

	 public void setCuentaCorriente(String cuentaCorriente) {
	  this.cuentaCorriente = cuentaCorriente;
	 }
	 
	 public String getSocio()
	 {
		 return (getNombre()+getSocioId());
	 }



	@Override
	public String toString() {
		
		if(getEstado().equals(true)) {
			
			return "Activo";
			
		}else return "Baja";
		
	}
	 
	 
	 
	}
