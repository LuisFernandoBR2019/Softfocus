package br.com.luis.softfocus.utils;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.luis.softfocus.model.ComunicacaoPerda;
import br.com.luis.softfocus.model.Geolocalizacao;

@Service
public class ValidacaoComunicacaoPerda {

	public Boolean validaCamposBrancoOuNulo(ComunicacaoPerda comunicacaoPerda) {

		if (comunicacaoPerda.getCpf() == null || comunicacaoPerda.getCpf().isBlank()) {
			return true;
		}

		if (comunicacaoPerda.getData_colheita() == null) {
			return true;
		}

		if (comunicacaoPerda.getEmail() == null || comunicacaoPerda.getEmail().isBlank()) {
			return true;
		}

		if (comunicacaoPerda.getEvento_ocorrido() == null || comunicacaoPerda.getEvento_ocorrido().isBlank()) {
			return true;
		}

		if (comunicacaoPerda.getNome() == null || comunicacaoPerda.getNome().isBlank()) {
			return true;
		}

		if (comunicacaoPerda.getTipo_lavoura() == null || comunicacaoPerda.getTipo_lavoura().isBlank()) {
			return true;
		}

		if (comunicacaoPerda.getLatitude() == null) {
			return true;
		}

		if (comunicacaoPerda.getLongitude() == null) {
			return true;
		}

		return false;
	}

	public Boolean calculaRaio(ComunicacaoPerda comunicacaoPerda, List<ComunicacaoPerda> lista) {

		Geolocalizacao geolocalizacao = new Geolocalizacao(comunicacaoPerda.getLatitude(),
				comunicacaoPerda.getLongitude());
		for (ComunicacaoPerda comunicacaoPerdaAux : lista) {
			if (comunicacaoPerda.getData_colheita().equals(comunicacaoPerdaAux.getData_colheita())) {
				Geolocalizacao geolocalizacaoAux = new Geolocalizacao(comunicacaoPerdaAux.getLatitude(),
						comunicacaoPerdaAux.getLongitude());
				double distancia = geolocalizacao.distanceInKm(geolocalizacaoAux);
				if (distancia <= 10.0 && distancia != 0.0) {
					return true;
				}
			}
		}
		return false;
	}

}
