package com.marketspace.domain.validators;

public class BasicValidator {
	
	public static boolean IsnullOrEmpty(String texto) {
		if (texto == null)
			return false;
		return texto.isBlank() || texto.isEmpty();
	}
}
