package com.marketspace.domain.enums;

public enum ImageEnum {
	icon_logo_app("/resources/img/icon-logo-app.png");

	String Image;

	public String getImage() {
		return Image;
	}
	
	ImageEnum(String image) {
		Image = image;
	}
}
