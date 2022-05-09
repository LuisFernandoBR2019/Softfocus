package br.com.luis.softfocus.model;

import java.util.Objects;

public class ComunicacaoPerda {

	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String tipo_lavoura;
	private Double latitude;
	private Double longitude;
	private String data_colheita;
	private String evento_ocorrido;

	public ComunicacaoPerda(String nome, String email, String cpf, String tipo_lavoura, Double latitude,
			Double longitude, String data_colheita, String evento_ocorrido) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.tipo_lavoura = tipo_lavoura;
		this.latitude = latitude;
		this.longitude = longitude;
		this.data_colheita = data_colheita;
		this.evento_ocorrido = evento_ocorrido;
	}

	public ComunicacaoPerda() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTipo_lavoura() {
		return tipo_lavoura;
	}

	public void setTipo_lavoura(String tipo_lavoura) {
		this.tipo_lavoura = tipo_lavoura;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getData_colheita() {
		return data_colheita;
	}

	public void setData_colheita(String data_colheita) {
		this.data_colheita = data_colheita;
	}

	public String getEvento_ocorrido() {
		return evento_ocorrido;
	}

	public void setEvento_ocorrido(String evento_ocorrido) {
		this.evento_ocorrido = evento_ocorrido;
	}

	@Override
	public String toString() {
		return "ComunicacaoPerda [id=" + id + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", tipo_lavoura="
				+ tipo_lavoura + ", latitude=" + latitude + ", longitude=" + longitude + ", data_colheita="
				+ data_colheita + ", evento_ocorrido=" + evento_ocorrido + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, data_colheita, email, evento_ocorrido, id, latitude, longitude, nome, tipo_lavoura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComunicacaoPerda other = (ComunicacaoPerda) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(data_colheita, other.data_colheita)
				&& Objects.equals(email, other.email) && Objects.equals(evento_ocorrido, other.evento_ocorrido)
				&& Objects.equals(id, other.id) && Objects.equals(latitude, other.latitude)
				&& Objects.equals(longitude, other.longitude) && Objects.equals(nome, other.nome)
				&& Objects.equals(tipo_lavoura, other.tipo_lavoura);
	}

}
