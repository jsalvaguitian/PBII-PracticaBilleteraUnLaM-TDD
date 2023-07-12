package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * Billetera virtual
 * 		gestiona las cuentas bancarias de distintos bancos CBU
 * 		gestiona las cuentas virtuales de distintos bancos CVU
 * 		administra distintas tarjetas de credito o debito para las compras
 * 		
 * 		se registra a los distintos comercios y consumidores finales.
 * 
 * 		se puede hacer TRANSFERENCIAS entre distintas cuentas CVU o CBU
 * 		(NO SE PUEDE HACER transferencia cuando el saldo de la cuenta origen sea MENOS al monto que se desea transferir.)

 * COMERCIO
 * 		genera los códigos QR con toda la información de una compra determinada.
 * 		Las compras o el pago se pueden realizar con TARJETA de débito o tarjetas de crédito
 * 
 * 		LISTA DE COMPRAS_REALIZADAS
 * 
 * 
 * EXCEPCIONES
 * NumeroDeTarjetaInvalidoException: El número de la tarjeta tiene 16 dígitos.
 * CBUInvalidoException: Se lanza cuando se intenta generar un CBU con un largo distinto a 20 dígitos.
 * CVUInvalidoException: Se lanza cuando se intenta generar un CVU con un largo distinto a 23 digitos.
 * 
 * CuitInvalidoException: Se lanza cuando se intenta generar un CUIT que no comienza en 30 y no posee 11 dígitos.
 * 
 * NoCoincideTitularException: Cuando se intenta asociar un medio de pago a un consumidor 
 * y no coincide el nombre del titular del medio con el nombre del consumidor.
 * 
 * SaldoInsuficienteException
 * 
 * ExcedeLimiteDeCompraException:
 * 
 */
public class TestBilletera {
	@Test
	public void queSePuedaCrearUnaTarjetaDeDebito() throws NumeroDeTarjetaInvalidoException {
		// Preparación
		final Long NUMERO_ESPERADO = 5446789813221201L;
		final String TITULAR_ESPERADO = "CAMILA CIENFUEGOS";
		final Integer CODIGO_DE_SEGURIDAD_ESPERADO = 123;
		final String FECHA_DE_VENCIMIENTO_ESPERADO = "25/05/2022";
		final Double SALDO_ESPERADO = 10000.0;

		// Ejecución
		// Pagadora tarjetaDeDebito = new TarjetaDeDebito(NUMERO_ESPERADO,
		// TITULAR_ESPERADO, FECHA_DE_VENCIMIENTO_ESPERADO,
		// CODIGO_DE_SEGURIDAD_ESPERADO);

		TarjetaDeDebito tarjetaDeDebito = new TarjetaDeDebito(NUMERO_ESPERADO, TITULAR_ESPERADO,
				FECHA_DE_VENCIMIENTO_ESPERADO, CODIGO_DE_SEGURIDAD_ESPERADO);
		tarjetaDeDebito.setSaldo(10000.0);

		// Verificación
		assertEquals(NUMERO_ESPERADO, tarjetaDeDebito.getNumero());
		assertEquals(TITULAR_ESPERADO, tarjetaDeDebito.getTitular());
		assertEquals(CODIGO_DE_SEGURIDAD_ESPERADO, tarjetaDeDebito.getCodigoDeSeguridad());
		assertEquals(FECHA_DE_VENCIMIENTO_ESPERADO, tarjetaDeDebito.getFechaDeVencimiento());
		assertEquals(SALDO_ESPERADO, tarjetaDeDebito.getSaldo());
	}
////////////////////////////////////////////////////////////////	

	@Test
	public void queSePuedaCrearUnaTarjetaDeCredito() throws NumeroDeTarjetaInvalidoException {
		// Preparación
		final Long NUMERO_ESPERADO = 4246789813221201L;
		final String TITULAR_ESPERADO = "SOFIA BARRIENTOS";
		final Integer CODIGO_DE_SEGURIDAD_ESPERADO = 567;
		final String FECHA_DE_VENCIMIENTO_ESPERADO = "25/05/2025";
		final double LIMITE_COMPRA_EN_PESOS = 100000.0;
		final double LIMITE_COMPRA_EN_DOLARES = 1000.0;

		// Ejecución
		// Pagadora tarjetaDeCredito = new TarjetaDeCredito(NUMERO_ESPERADO,
		// TITULAR_ESPERADO, FECHA_DE_VENCIMIENTO_ESPERADO,
		// CODIGO_DE_SEGURIDAD_ESPERADO, LIMITE_COMPRA_EN_PESOS,
		// LIMITE_COMPRA_EN_DOLARES);

		TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito(NUMERO_ESPERADO, TITULAR_ESPERADO,
				FECHA_DE_VENCIMIENTO_ESPERADO, CODIGO_DE_SEGURIDAD_ESPERADO, LIMITE_COMPRA_EN_PESOS,
				LIMITE_COMPRA_EN_DOLARES);

		// Verificación
		assertEquals(NUMERO_ESPERADO, tarjetaDeCredito.getNumero());
		assertEquals(TITULAR_ESPERADO, tarjetaDeCredito.getTitular());
		assertEquals(CODIGO_DE_SEGURIDAD_ESPERADO, tarjetaDeCredito.getCodigoDeSeguridad());
		assertEquals(FECHA_DE_VENCIMIENTO_ESPERADO, tarjetaDeCredito.getFechaDeVencimiento());
		assertEquals(LIMITE_COMPRA_EN_PESOS, tarjetaDeCredito.getLimiteDeCompraEnPesos(), 0.1);
		assertEquals(LIMITE_COMPRA_EN_DOLARES, tarjetaDeCredito.getLimiteDeCompraEnDolares(), 0.1);
	}
////////////////////////////////////////////////////////////////

	@Test
	public void queSePuedaCrearUnaCuentaBancaria() throws CBUInvalidoException {
		// Preparación
		final String CBU_ESPERADO = "01702046600000087865";
		final String ENTIDAD_ESPERADA = "Santander";
		final String TITULAR_ESPERADO = "Vicente De La Pradera";

		// Ejerución
		// Transferible cuentaBancaria = new CuentaBancaria(CBU_ESPERADO,
		// ENTIDAD_ESPERADA, TITULAR_ESPERADO);

		CuentaBancaria cuentaBancaria = new CuentaBancaria(CBU_ESPERADO, ENTIDAD_ESPERADA, TITULAR_ESPERADO);

		// Verificación
		assertEquals(CBU_ESPERADO, cuentaBancaria.getCbu());
		assertEquals(ENTIDAD_ESPERADA, cuentaBancaria.getEntidad());
		assertEquals(TITULAR_ESPERADO, cuentaBancaria.getTitular());
	}
////////////////////////////////////////////////////////////////

	@Test
	public void queSePuedaCrearUnaCuentaVirtual() throws CVUInvalidoException {
		// Preparación
		final String CVU_ESPERADO = "46467898132212011234552";
		final String ENTIDAD_ESPERADA = "Mercado Pago";
		final String TITULAR_ESPERADO = "Leticia Bridgeton";

		// Ejecución
		// Transferible cuentaVirtual = new CuentaVirtual(CVU_ESPERADO,
		// ENTIDAD_ESPERADA, TITULAR_ESPERADO);

		CuentaVirtual cuentaVirtual = new CuentaVirtual(CVU_ESPERADO, ENTIDAD_ESPERADA, TITULAR_ESPERADO);

		// Verificación
		assertEquals(CVU_ESPERADO, cuentaVirtual.getCvu());
		assertEquals(ENTIDAD_ESPERADA, cuentaVirtual.getEntidad());
		assertEquals(TITULAR_ESPERADO, cuentaVirtual.getTitular());
	}
////////////////////////////////////////////////////////////////

	@Test
	public void queSePuedaCrearUnComercio() throws CuitInvalidoException {
		// Preparación
		final String NOMBRE_ESPERADO = "El almacen de la esquina";
		final Long CUIT_ESPERADO = 30215654124L;

		// Ejecución
		Comercio elAlmacen = new Comercio(CUIT_ESPERADO, NOMBRE_ESPERADO);

		// Verificación
		assertEquals(NOMBRE_ESPERADO, elAlmacen.getNombre());
		assertEquals(CUIT_ESPERADO, elAlmacen.getCuit());
	}

////////////////////////////////////////////////////////////////

	@Test
	public void queSePuedaCrearUnConsumidor() {
		// Preparación
		final String NOMBRE_ESPERADO = "Erika Romeo";
		final Integer DNI_ESPERADO = 33458712;

		// Ejecución
		Consumidor nuevo = new Consumidor(DNI_ESPERADO, NOMBRE_ESPERADO);

		// Verificación
		assertEquals(NOMBRE_ESPERADO, nuevo.getNombre());
		assertEquals(DNI_ESPERADO, nuevo.getDni());
	}

	@Test
	public void queSePuedanAgregarComerciosALaBilletera() throws CuitInvalidoException {
		// Preparación
		final Integer CANTIDAD_DE_COMERCIOS_ESPERADOS = 2;

		// Ejecución
		Billetera mataGalan = new Billetera("Ank");

		mataGalan.agregarComercio(new Comercio(30456213545L, "Supermercado"));
		mataGalan.agregarComercio(new Comercio(30215654124L, "Panadería"));
		mataGalan.agregarComercio(new Comercio(30215654124L, "Panadería"));

		// Verificación
		assertEquals(CANTIDAD_DE_COMERCIOS_ESPERADOS, mataGalan.getCantidadDeComercios());
	}

	@Test
	public void queSePuedanAgregarConsumidoresALaBilletera() {
		// Preparación
		final Integer CANTIDAD_DE_CONSUMIDORES_ESPERADOS = 2;

		// Ejecución
		Billetera mataGalan = new Billetera("Bna+");

		mataGalan.agregarConsumidor(new Consumidor(27541231, "Luis Gomez"));
		mataGalan.agregarConsumidor(new Consumidor(27541231, "Luis Gomez"));
		mataGalan.agregarConsumidor(new Consumidor(36541231, "Sofia Molina"));

		// Verificación
		assertEquals(CANTIDAD_DE_CONSUMIDORES_ESPERADOS, mataGalan.getCantidadDeConsumidores());
	}

	@Test
	public void queSePuedanAgregarDistintosMediosDePagoALaBilleteraDeUnConsumidor()
			throws NumeroDeTarjetaInvalidoException, CBUInvalidoException, CVUInvalidoException,
			NoCoincideTitularException {
		// Preparación
		final Integer CANTIDAD_DE_MEDIOS_EN_LA_BILLETERA_ESPERADOS = 5; // ARREGLAR que solo 4

		// Ejecución
		Billetera mataGalan = new Billetera("MercadoPago");
		mataGalan.agregarConsumidor(new Consumidor(27541231, "Luis Gomez"));

		mataGalan.agregarMedioDePago(27541231,
				new TarjetaDeDebito(48332562153254623L, "Luis Gomez", "10/10/2026", 265));
		mataGalan.agregarMedioDePago(27541231,
				new TarjetaDeDebito(48332562153254623L, "Luis Gomez", "10/10/2026", 312));
		mataGalan.agregarMedioDePago(27541231,
				new TarjetaDeCredito(5423542385612354L, "Luis Gomez", "10/10/2026", 153));
		mataGalan.agregarMedioDePago(27541231, new CuentaBancaria("01702046600000087865", "Nación", "Luis Gomez"));
		mataGalan.agregarMedioDePago(27541231,
				new CuentaVirtual("20000003100036521571806", "Mercado Pago", "Luis Gomez"));

		// Verificación
		assertEquals(CANTIDAD_DE_MEDIOS_EN_LA_BILLETERA_ESPERADOS, mataGalan.getCantidadDeMediosDePago(27541231));
	}

	@Test
	public void queSePuedaPagarConUnaTarjetaDeCredito() throws Exception {
		// Preparación
		Billetera mataGalan = new Billetera("Ank");
		mataGalan.agregarConsumidor(new Consumidor(27541231, "Luis Gomez"));

		mataGalan.agregarMedioDePago(27541231, new TarjetaDeCredito(4833256215325462L, "Luis Gomez", "10/10/2026", 265, 100000.0, 1000.0));
		mataGalan.agregarComercio(new Comercio(30456213545L, "Panadería"));

		// Ejecución
		Compra codigoQR = mataGalan.generarCodigoQR(30456213545L, 1000.0);
		Boolean resultado = mataGalan.pagar(codigoQR, mataGalan.getConsumidor(27541231).getMedioPagador(4833256215325462L));

		// Verificación
		assertTrue(resultado);
	}

	@Test
	public void queSePuedaTransferirDeUnaCuentaAOtra() throws Exception {
		// Preparación
		Billetera mataGalan = new Billetera("Ualá");
		final Double MONTO_A_TRANSFERIR = 1000.0;
		// Transferible cuentaOrigen = new CuentaBancaria("01702046600000087865",
		// "Nación", "Luis Gomez");

		CuentaBancaria cuentaOrigen = new CuentaBancaria("01702046600000087865", "Nación", "Luis Gomez");
		cuentaOrigen.setSaldo(MONTO_A_TRANSFERIR);

		Consumidor consumidorOrigen = new Consumidor(27541231, "Luis Gomez");
		mataGalan.agregarConsumidor(consumidorOrigen);
		mataGalan.agregarMedioDePago(27541231, cuentaOrigen);

		CuentaBancaria cuentaDestino = new CuentaBancaria("01744046600000087335", "Galicia", "Sandra Bustos");
		Consumidor consumidorDestino = new Consumidor(33896541, "Sandra Bustos");
		mataGalan.agregarConsumidor(consumidorDestino);
		mataGalan.agregarMedioDePago(33896541, cuentaDestino);

		// Ejecución
		// Boolean resultado = mataGalan.transferir(cuentaOrigen, cuentaDestino);
		Boolean resultado = mataGalan.transferir(cuentaOrigen, cuentaDestino, MONTO_A_TRANSFERIR);

		// Verificación
		assertTrue(resultado);
	}

	// SaldoInsuficienteException:
	// Cuando se intenta realizar una compra con tarjeta de débito o transferencia a
	// una cuenta, pero no se dispone el saldo necesario para completar la
	// transacción.
	@Test(expected = SaldoInsuficienteException.class)
	public void queNoSePuedaTransferirSiElSaldoNoEsSuficiente()
			throws Exception {

		// Preparación
		Billetera mataGalan = new Billetera("Ualá");
		final Double MONTO_A_TRANSFERIR = 1000.0;
		// Transferible cuentaOrigen = new CuentaBancaria("01702046600000087865",
		// "Nación", "Luis Gomez");

		CuentaBancaria cuentaOrigen = new CuentaBancaria("01702046600000087865", "Nación", "Luis Gomez");
		Consumidor consumidorOrigen = new Consumidor(27541231, "Luis Gomez");
		mataGalan.agregarConsumidor(consumidorOrigen);
		mataGalan.agregarMedioDePago(27541231, cuentaOrigen);

		CuentaBancaria cuentaDestino = new CuentaBancaria("01744046600000087335", "Galicia", "Sandra Bustos");
		Consumidor consumidorDestino = new Consumidor(33896541, "Sandra Bustos");
		mataGalan.agregarConsumidor(consumidorDestino);
		mataGalan.agregarMedioDePago(33896541, cuentaDestino);

		// Ejecución
		// Boolean resultado = mataGalan.transferir(cuentaOrigen, cuentaDestino);
		Boolean resultado = mataGalan.transferir(cuentaOrigen, cuentaDestino, MONTO_A_TRANSFERIR);

		// Verificación
		// assertTrue(resultado);
	}

	@Test(expected = SaldoInsuficienteException.class)
	public void queNoSePuedaComprarSiElSaldoDeLaTarjetaDeDebitoEsInsuficiente()
			throws Exception{

		// Preparación
		Billetera mataGalan = new Billetera("Ank");
		mataGalan.agregarConsumidor(new Consumidor(27541231, "Luis Gomez"));

		mataGalan.agregarMedioDePago(27541231,
				new TarjetaDeDebito(48332562153254623L, "Luis Gomez", "10/10/2026", 265));
		mataGalan.agregarComercio(new Comercio(30456213545L, "Panadería"));

		// Ejecución
		Compra codigoQR = mataGalan.generarCodigoQR(30456213545L, 1000.0);
		Boolean resultado = mataGalan.pagar(codigoQR,
				mataGalan.getConsumidor(27541231).getMedioPagador(48332562153254623L));

		// Verificación
		// assertTrue(resultado);

	}
	// ExcedeLimiteDeCompraException:
	// Cuando se desea realizar una compra con tarjeta de crédito, pero se excede el
	// límite disponible en esa tarjeta.

	@Test(expected = ExcedeLimiteDeCompraException.class)
	public void queNoSePuedaComprarSiSeExcedeElLimiteDeCompraDeLaTarjetaDeCredito() throws Exception{

		// Preparación
		Billetera mataGalan = new Billetera("Ank");
		mataGalan.agregarConsumidor(new Consumidor(27541231, "Luis Gomez"));

		mataGalan.agregarMedioDePago(27541231,
				new TarjetaDeCredito(4833256215325462L, "Luis Gomez", "10/10/2026", 265, 100.0, 1000.0));
		mataGalan.agregarComercio(new Comercio(30456213545L, "Panadería"));

		// Ejecución
		Compra codigoQR = mataGalan.generarCodigoQR(30456213545L, 1000.0);
		Boolean resultado = mataGalan.pagar(codigoQR, mataGalan.getConsumidor(27541231).getMedioPagador(4833256215325462L));


	}

	// NumeroDeTarjetaInvalidoException: Si El número de la tarjeta no tiene 16
	// dígitos.
	@Test(expected = NumeroDeTarjetaInvalidoException.class)
	public void queNoSePuedaCrearUnaTarjetaDeCredito() throws NumeroDeTarjetaInvalidoException {
		// Preparación
		final Long NUMERO_ESPERADO = 424678981322120199L;
		final String TITULAR_ESPERADO = "SOFIA BARRIENTOS";
		final Integer CODIGO_DE_SEGURIDAD_ESPERADO = 567;
		final String FECHA_DE_VENCIMIENTO_ESPERADO = "25/05/2025";
		final double LIMITE_COMPRA_EN_PESOS = 100000.0;
		final double LIMITE_COMPRA_EN_DOLARES = 1000.0;

		// Ejecución
		// Pagadora tarjetaDeCredito = new TarjetaDeCredito(NUMERO_ESPERADO,
		// TITULAR_ESPERADO, FECHA_DE_VENCIMIENTO_ESPERADO,
		// CODIGO_DE_SEGURIDAD_ESPERADO, LIMITE_COMPRA_EN_PESOS,
		// LIMITE_COMPRA_EN_DOLARES);

		TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito(NUMERO_ESPERADO, TITULAR_ESPERADO,
				FECHA_DE_VENCIMIENTO_ESPERADO, CODIGO_DE_SEGURIDAD_ESPERADO, LIMITE_COMPRA_EN_PESOS,
				LIMITE_COMPRA_EN_DOLARES);

	}

	/*
	 * //CBUInvalidoException: Se lanza cuando se intenta generar un CBU con un
	 * //largo distinto a 20 dígitos.
	 */

	@Test(expected = CBUInvalidoException.class)
	public void queNoSePuedaCrearUnaCuentaBancaria() throws CBUInvalidoException {
		// Preparación
		final String CBU_ESPERADO = "0170204660000008786599";
		final String ENTIDAD_ESPERADA = "Santander";
		final String TITULAR_ESPERADO = "Vicente De La Pradera";

		// Ejerución
		// Transferible cuentaBancaria = new CuentaBancaria(CBU_ESPERADO,
		// ENTIDAD_ESPERADA, TITULAR_ESPERADO);

		CuentaBancaria cuentaBancaria = new CuentaBancaria(CBU_ESPERADO, ENTIDAD_ESPERADA, TITULAR_ESPERADO);

	}

	// CVUInvalidoException: Se lanza cuando se intenta generar un CVU con un
	// largo distinto a 23 digitos.

	@Test(expected = CVUInvalidoException.class)
	public void queNoSePuedaCrearUnaCuentaVirtual() throws CVUInvalidoException {
		// Preparación
		final String CVU_ESPERADO = "4646789813221201123455277";
		final String ENTIDAD_ESPERADA = "Mercado Pago";
		final String TITULAR_ESPERADO = "Leticia Bridgeton";

		// Ejecución
		// Transferible cuentaVirtual = new CuentaVirtual(CVU_ESPERADO,
		// ENTIDAD_ESPERADA, TITULAR_ESPERADO);

		CuentaVirtual cuentaVirtual = new CuentaVirtual(CVU_ESPERADO, ENTIDAD_ESPERADA, TITULAR_ESPERADO);

	}
	// CuitInvalidoException: Se lanza cuando se intenta generar un CUIT que no
	// comienza en 30 y no posee 11 dígitos.

	@Test(expected = CuitInvalidoException.class)
	public void queNoSePuedaCrearUnComercio() throws CuitInvalidoException {
		// Preparación
		final String NOMBRE_ESPERADO = "El almacen de la esquina";
		final Long CUIT_ESPERADO = 21215654124L;

		// Ejecución
		Comercio elAlmacen = new Comercio(CUIT_ESPERADO, NOMBRE_ESPERADO);

	}

	// NoCoincideTitularException: Cuando se intenta asociar un medio de pago a un
	// consumidor y no coincide el nombre del titular del medio con el nombre del
	// consumidor.

	@Test(expected = NoCoincideTitularException.class)
	public void queNoSePuedaAgregarMedioDePago() throws NoCoincideTitularException {
		// Preparación

		// Ejecución
		Billetera mataGalan = new Billetera("MercadoPago");
		mataGalan.agregarConsumidor(new Consumidor(27541231, "Luis Gomez"));

		mataGalan.agregarMedioDePago(27541231, new TarjetaDeDebito(48332562153254623L, "Homero Simpson", "10/10/2026", 265));

	}

}
