package br.com.luis.softfocus.utils;

import br.com.luis.softfocus.model.Geolocalizacao;

public class GeolocalizacaoUtils {

	public static int EARTH_RADIUS_KM = 6371;

	public static double geoDistanceInKm(double firstLatitude, double firstLongitude, double secondLatitude,
			double secondLongitude) {

		// Conversão de graus pra radianos das latitudes
		double firstLatToRad = Math.toRadians(firstLatitude);
		double secondLatToRad = Math.toRadians(secondLatitude);

		// Diferença das longitudes
		double deltaLongitudeInRad = Math.toRadians(secondLongitude - firstLongitude);

		// Cálcula da distância entre os pontos
		return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad) * Math.cos(deltaLongitudeInRad)
				+ Math.sin(firstLatToRad) * Math.sin(secondLatToRad)) * EARTH_RADIUS_KM;
	}

	public static double geoDistanceInKm(Geolocalizacao first, Geolocalizacao second) {
		return geoDistanceInKm(first.getLatitude(), first.getLongitude(), second.getLatitude(), second.getLongitude());
	}
}
