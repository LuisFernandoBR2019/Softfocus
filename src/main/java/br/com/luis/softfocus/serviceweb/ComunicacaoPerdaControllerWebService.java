package br.com.luis.softfocus.serviceweb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.luis.softfocus.model.ComunicacaoPerda;

@Service
public class ComunicacaoPerdaControllerWebService {
	private String endpoint = "";
	private RestTemplate restTemplate = null;

	public Map<String, Object> create(ComunicacaoPerda entity) {
		endpoint = "http://localhost:8082/api/v1/proagro/create";
		restTemplate = new RestTemplate();
		int resposta = 0;
		try {

			HttpEntity<ComunicacaoPerda> httpEntity = new HttpEntity<ComunicacaoPerda>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = responseEntity.getStatusCodeValue();
			if (resposta == HttpStatus.CREATED.value()) {
				return (Map<String, Object>) responseEntity.getBody();
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<ComunicacaoPerda> readAll() {
		endpoint = "http://localhost:8082/api/v1/proagro/read-all";
		restTemplate = new RestTemplate();

		int resposta = 0;
		try {

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, null, List.class);

			resposta = responseEntity.getStatusCodeValue();

			if (resposta == HttpStatus.OK.value()) {
				return (List<ComunicacaoPerda>) responseEntity.getBody();
			} else {
				return new ArrayList<ComunicacaoPerda>();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<ComunicacaoPerda>();
	}

	public ComunicacaoPerda readByID(Long id) {
		endpoint = "http://localhost:8082/api/v1/proagro/read-by-id/{id}";
		restTemplate = new RestTemplate();
		int resposta = 0;
		try {

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, null,
					ComunicacaoPerda.class, id);

			resposta = responseEntity.getStatusCodeValue();
			if (resposta == HttpStatus.OK.value()) {
				return (ComunicacaoPerda) responseEntity.getBody();
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Boolean update(ComunicacaoPerda entity) {
		endpoint = "http://localhost:8082/api/v1/proagro/update";
		restTemplate = new RestTemplate();
		int resposta = 0;
		try {

			HttpEntity<ComunicacaoPerda> httpEntity = new HttpEntity<ComunicacaoPerda>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, httpEntity,
					Boolean.class);

			resposta = responseEntity.getStatusCodeValue();
			if (resposta == HttpStatus.ACCEPTED.value()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public Boolean delete(Long id) {
		endpoint = "http://localhost:8082/api/v1/proagro/delete/{id}";
		restTemplate = new RestTemplate();
		int resposta = 0;
		try {

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.DELETE, null, Boolean.class,
					id);

			resposta = responseEntity.getStatusCodeValue();
			if (resposta == HttpStatus.ACCEPTED.value()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
