package com.marketspace.domain.entities;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateHelper {
	
	public static Date LocalDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date SubtrairDiaDeData(Date data,int quantidadeDias) {
		Instant now = data.toInstant();
		Instant novaData = now.minus(Duration.ofDays(1));
		return Date.from(novaData);
	}
	
	public static Date AdicionarDiaDeData(Date data,int quantidadeDias) {
		Instant now = data.toInstant();
		Instant novaData = now.plus(Duration.ofDays(1));
		return Date.from(novaData);
	}
}
