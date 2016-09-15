package br.com.pesquisa.util.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class CriptografiaHelper {

	private CriptografiaHelper( ) {

	}

	public static String criptografarMD5 ( String valor ) {

		try {
			MessageDigest digest = MessageDigest.getInstance ( "MD5" );

			digest.update ( valor.getBytes ( ) );
			byte data[] = digest.digest ( );
			StringBuffer buffer = new StringBuffer ( );
			for ( int i = 0 ; i < data.length ; i++ ) {
				buffer.append ( Integer.toString ( ( data[ i ] & 0xff ) + 0x100, 16 ).substring ( 1 ) );
			}
			return buffer.toString ( );
		} catch ( NoSuchAlgorithmException e ) {
			e.printStackTrace ( );
			return valor;
		}
	}
}
