package br.com.luis.softfocus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import br.com.luis.softfocus.model.ComunicacaoPerda;

class ComunicacaoPerdaTest {
	private String endpoint = "";
	private RestTemplate restTemplate = null;

	@Test
	@Order(6)
	void deletarComunicacaoPerda() {
		endpoint = "http://localhost:8082/api/v1/proagro/delete/{id}";
		restTemplate = new RestTemplate();
		int idComunicacaoPerda = 1;
		int resposta = 0;
		try {

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.DELETE, null, Boolean.class,
					idComunicacaoPerda);

			resposta = responseEntity.getStatusCodeValue();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(HttpStatus.ACCEPTED.value(), resposta);
	}

	@Test
	@Order(5)
	void lerTodasComunicacaoPerda() {
		endpoint = "http://localhost:8082/api/v1/proagro/read-all";
		restTemplate = new RestTemplate();

		int resposta = 0;
		try {

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, null, List.class);

			resposta = responseEntity.getStatusCodeValue();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(HttpStatus.OK.value(), resposta);
	}

	@Test
	@Order(4)
	void lerComunicacaoPerdaID() {
		endpoint = "http://localhost:8082/api/v1/proagro/read-by-id/{id}";
		restTemplate = new RestTemplate();
		int resposta = 0;
		int idComunicacaoPerda = 1;
		try {

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, null,
					ComunicacaoPerda.class, idComunicacaoPerda);

			resposta = responseEntity.getStatusCodeValue();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(HttpStatus.OK.value(), resposta);
	}

	@Test
	@Order(3)
	void atualizarComunicacaoPerda() {
		endpoint = "http://localhost:8082/api/v1/proagro/update";
		restTemplate = new RestTemplate();
		ComunicacaoPerda entity = new ComunicacaoPerda("Luis", "teste@softfocus.com.br", "112.169.589-87", "Milho",
				-55.156485, -82.654123, "2022-05-08", "RAIO");
		entity.setId(1L);
		int resposta = 0;
		try {

			HttpEntity<ComunicacaoPerda> httpEntity = new HttpEntity<ComunicacaoPerda>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, httpEntity,
					Boolean.class);

			resposta = responseEntity.getStatusCodeValue();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(HttpStatus.ACCEPTED.value(), resposta);
	}

	@Test
	@Order(3)
	void validarKmComunicacaoPerda() {
		endpoint = "http://localhost:8082/api/v1/proagro/create";
		restTemplate = new RestTemplate();
		ComunicacaoPerda entity = new ComunicacaoPerda("Luis", "teste2@softfocus.com.br", "948.119.519-87", "Milho",
				-55.156483, -82.654123, "2022-05-08", "RAIO");
		Map<String, Object> resposta = null;
		try {

			HttpEntity<ComunicacaoPerda> httpEntity = new HttpEntity<ComunicacaoPerda>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, Object>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals("Está em um raio de 10km", resposta.get("raio"));
	}

	@Test
	@Order(1)
	void criarComunicacaoPerda() {
		endpoint = "http://localhost:8082/api/v1/proagro/create";
		restTemplate = new RestTemplate();
		ComunicacaoPerda entity = new ComunicacaoPerda("Luis", "teste1@softfocus.com.br", "112.169.589-87", "Milho",
				-55.156485, -82.654123, "2022-05-08", "RAIO");
		int resposta = 0;
		try {

			HttpEntity<ComunicacaoPerda> httpEntity = new HttpEntity<ComunicacaoPerda>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = responseEntity.getStatusCodeValue();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(HttpStatus.CREATED.value(), resposta);
	}@Test
	@Order(2)
	void criarComunicacaoPerdaValidarKM() {
		endpoint = "http://localhost:8082/api/v1/proagro/create";
		restTemplate = new RestTemplate();
		ComunicacaoPerda entity = new ComunicacaoPerda("Luis", "teste1@softfocus.com.br", "112.169.589-87", "Milho",
				-55.156485, -82.654123, "2022-05-08", "SECA");
		int resposta = 0;
		try {

			HttpEntity<ComunicacaoPerda> httpEntity = new HttpEntity<ComunicacaoPerda>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = responseEntity.getStatusCodeValue();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(HttpStatus.CREATED.value(), resposta);
	}
}
