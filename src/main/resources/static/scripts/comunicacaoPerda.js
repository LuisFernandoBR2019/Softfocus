let TOAST_ERROR = 0;
let TOAST_SUCCESS = 1;

class ComunicacaoPerdaClass {
	constructor(id) {
		this.id = id;
	}
};


function showToast(tipo, message) {
	document.getElementById("toast-message").textContent = message;
	document.getElementById("toast-image").src = tipo == TOAST_ERROR ? "../img/error.png" : "../img/success.png";
	$("#toast").toast("show");
}

var comunicacaoPerdaAux = new ComunicacaoPerdaClass(0);

function functionCarregaLista() {
	loading(true);
	jQuery.ajax({
		url: "../read-all",
		type: 'GET',
		dataType: 'json',
		error: function() {
			loading(false);
			showToast(TOAST_ERROR, "Ocorreu um erro ao carregar a lista");
		},
		success: function(dados) {
			loading(false);
			for (let i = 0; i < dados.length; i++) {
				adicionaComunicacaoPerdaNaLista(dados[i]);
			}
			loading(false);
		}
	});
}

function functionDelete(id) {
	var deleteJson = { id: id };
	loading(true);
	jQuery.ajax({
		url: "../delete",
		type: 'POST',
		data: deleteJson,
		dataType: 'json',
		error: function() {
			showToast(TOAST_ERROR, "Ocorreu um erro ao deletar");
			loading(false);
		},
		success: function(dados) {
			loading(false);
			if (dados) {
				var qtdLinhas = $("#table_usuarios tbody tr").length;
				for (var i = 0; i < qtdLinhas; i++) {
					var split = $("#table_usuarios tbody tr")[i].innerText.split('\t');
					if (id == split[0]) {
						$("#table_usuarios").bootstrapTable('remove', {
							field: 'id',
							values: split[0]
						});
						showToast(TOAST_SUCCESS, "Deletado com sucesso");
						break;
					}
				}

			} else {
				showToast(TOAST_ERROR, "Ocorreu um erro ao deletar");
			}
		}
	});
}

function functionReadByID(id) {
	alteraTextoBotaoEditar();
	loading(true);
	var idJson = { id: id };
	jQuery.ajax({
		url: "../read-by-id",
		type: 'POST',
		data: idJson,
		dataType: 'json',
		error: function() {
			loading(false);
			showToast(TOAST_ERROR, "Ocorreu um erro ao carregar comunicacao de perda");
		},
		success: function(dados) {
			loading(false);
			preencherCampos(dados);
		}
	});
}

function formatarCampo(campoTexto) {
	if (campoTexto.value.length <= 11) {
		campoTexto.value = mascaraCpf(campoTexto.value);
	}
}

function mascaraCpf(valor) {
	return valor.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "\$1.\$2.\$3\-\$4");
}

function validateEmail(email) {
	let usuario = email.substring(0, email.indexOf("@"));
	let dominio = email.substring(email.indexOf("@") + 1, email.length);
	if ((usuario.length >= 1) &&
		(dominio.length >= 3) &&
		(usuario.search("@") == -1) &&
		(dominio.search("@") == -1) &&
		(usuario.search(" ") == -1) &&
		(dominio.search(" ") == -1) &&
		(dominio.search(".") != -1) &&
		(dominio.indexOf(".") >= 1) &&
		(dominio.lastIndexOf(".") < dominio.length - 1)) {
		return true;
	}
	else {
		return false;
	}
}

function validaCampos(comunicacaoPerda) {
	let alerta = document.getElementById('alertaCampoEmBranco');

	if (comunicacaoPerda.nome == "") {
		alerta.hidden = false;
		alerta.innerText = "Campo nome em branco.";
		return true;
	}

	if (comunicacaoPerda.email == "") {
		alerta.hidden = false;
		alerta.innerText = "Campo e-mail em branco.";
		return true;
	} else {
		if (!validateEmail(comunicacaoPerda.email)) {
			alerta.hidden = false;
			alerta.innerText = "Campo e-mail inválido.";
			return true;
		}
	}

	if (comunicacaoPerda.cpf == "") {
		alerta.hidden = false;
		alerta.innerText = "Campo CPF em branco.";
		return true;
	} else {
		if (comunicacaoPerda.cpf.length < 14 || comunicacaoPerda.cpf.length > 14) {
			alerta.hidden = false;
			alerta.innerText = "Campo CPF inválido.";
			return true;
		}
	}

	if (!VerificaCPF(comunicacaoPerda.cpf)) {
		alerta.hidden = false;
		alerta.innerText = "CPF inválido.";
		return true;
	}


	if (comunicacaoPerda.latitude == "") {
		alerta.hidden = false;
		alerta.innerText = "Campo latitude em branco.";
		return true;
	} else {
		try {
			let latitude = parseFloat(comunicacaoPerda.latitude);
			if (isNaN(latitude)) {
				alerta.hidden = false;
				alerta.innerText = "Campo latitude inválido";
				return true;
			}
		} catch (e) {
			alerta.hidden = false;
			alerta.innerText = "Campo latitude inválido";
			return true;
		}
	}

	if (comunicacaoPerda.longitude == "") {
		alerta.hidden = false;
		alerta.innerText = "Campo longitude em branco.";
		return true;
	} else {
		try {
			let longitude = parseFloat(comunicacaoPerda.longitude);
			if (isNaN(longitude)) {
				alerta.hidden = false;
				alerta.innerText = "Campo longitude inválido";
				return true;
			}
		} catch (e) {
			alerta.hidden = false;
			alerta.innerText = "Campo longitude inválido";
			return true;
		}
	}

	if (comunicacaoPerda.tipo_lavoura == "") {
		alerta.hidden = false;
		alerta.innerText = "Campo tipo de lavoura em branco.";
		return true;
	}

	if (comunicacaoPerda.data_colheita == "") {
		alerta.hidden = false;
		alerta.innerText = "Campo data em branco.";
		return true;
	}

	if (comunicacaoPerda.evento_ocorrido == "") {
		alerta.hidden = false;
		alerta.innerText = "Campo evento em branco.";
		return true;
	}

	alerta.hidden = true;
	return false;
}

function alteraTextoBotaoEditar() {
	document.getElementById('botaoCriarAlterar').innerText = 'Alterar';
}

function alteraTextoBotaoAdicionar() {
	document.getElementById('botaoCriarAlterar').innerText = 'Cadastrar';
}

function functionCreateOrUpdate() {
	const comunicacaoPerda = functionPegarTodasInformacoesModal();
	if (!validaCampos(comunicacaoPerda)) {
		var comunicacaoPerdaJson = JSON.stringify(comunicacaoPerda);
		console.log(comunicacaoPerda);
		var endPoint = comunicacaoPerdaAux.id != 0 ? "../update" : "../create";
		var type = "POST";
		loading(true);
		jQuery.ajax({
			url: endPoint,
			type: type,
			data: comunicacaoPerdaJson,
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			error: function() {
				loading(false);
				if (endPoint == "../update") {
					showToast(TOAST_ERROR, "Ocorreu um erro ao atualizar a comunicação de perda");
				} else {
					showToast(TOAST_ERROR, "Ocorreu um erro ao criar comunicação de perda");
				}

			},
			success: function(retorno) {
				loading(false);
				if (endPoint == "../update") {
					if (retorno) {
						retornaListaAtualizada(comunicacaoPerda);
					} else {
						showToast(TOAST_ERROR, "Erro ao atualizar comunicacao de perda");
					}
				} else {
					const raio10km = retorno.raio;
					const comunicacaoPerdaRetornado = retorno.created;
					const empty = retorno.empty;
					
					if (comunicacaoPerdaRetornado != undefined) {
						if (raio10km != undefined) {
							showToast(TOAST_SUCCESS, "Criado com sucesso.\nHá evento divergente do cadastrado em um raio de 10KM.");
						} else {
							showToast(TOAST_SUCCESS, "Criado com sucesso");
						}
						adicionaComunicacaoPerdaNaLista(comunicacaoPerdaRetornado);
					} else {
						showToast(TOAST_ERROR, "Erro ao criar comunicacao de perda");
					}
				}
				limparCampos();
				fecharModal();
			}
		});
	}
}

function loading(isToShow) {
	if (isToShow) {
		$('#modalLoading').modal('show');
	} else {
		const timeout = (duration) => {
			return new Promise((resolve, reject) => {
				setTimeout(resolve, duration)
			})
		}

		timeout(1000)
			.then(function() { // executa o bloco após x segundos
				$('#modalLoading').modal('hide');
			})
	}
}

function retornaDataFormatada(data_colheita) {
	let dataSplit = [];
	dataSplit = data_colheita.split('-');
	return `${dataSplit[2]}/${dataSplit[1]}/${dataSplit[0]}`;
}

function adicionaComunicacaoPerdaNaLista(comunicacaoPerda) {
	var row = [];
	var acoes = "<div style='cursor: pointer;' class='dropdown'> " +
		"<a class='btn btn-secondary btn-sm dropdown-toggle' href='#' role='button' id='dropdownMenuLink' data-bs-toggle='dropdown' aria-expanded='false'>Ações</a>" +
		"<ul class='dropdown-menu' aria-labelledby='dropdownMenuButton1'>" +
		"<li>" + `<a class='dropdown-item' data-bs-toggle='modal' data-bs-target='#modal_novo_usuario' onclick="functionReadByID(${comunicacaoPerda.id})" >Editar</a>` +
		"</li>" + "<li>" + `<a class='dropdown-item' onclick="functionDelete(${comunicacaoPerda.id})">Deletar</a>` + "</li>" +
		"</ul>" + "</div>";
	row.push({
		id: comunicacaoPerda.id,
		nome: comunicacaoPerda.nome,
		email: comunicacaoPerda.email,
		cpf: comunicacaoPerda.cpf,
		latitude: comunicacaoPerda.latitude,
		longitude: comunicacaoPerda.longitude,
		tipo_lavoura: comunicacaoPerda.tipo_lavoura,
		data_colheita: retornaDataFormatada(comunicacaoPerda.data_colheita),
		evento: comunicacaoPerda.evento_ocorrido,
		action: acoes
	});

	$('#table_usuarios').bootstrapTable('append', row);

}

function fecharModal() {
	document.getElementById('botaoSair').click();
}

function functionPegarTodasInformacoesModal() {
	var nome = document.getElementById('nome_comunicacao_perda').value;
	var email = document.getElementById('email_comunicacao_perda').value;
	var cpf = document.getElementById('cpf_comunicacao_perda').value;
	var latitude = parseFloat(document.getElementById('latitude_comunicacao_perda').value);
	var longitude = parseFloat(document.getElementById('longitude_comunicacao_perda').value);
	var tipoLavoura = document.getElementById('tipo_lavoura_comunicacao_perda').value;
	var dataColheita = document.getElementById('data_colheita_comunicacao_perda').value;
	var select = document.getElementById('evento_ocorrido_comunicacao_perda');

	var evento = select.options[select.selectedIndex].value;
	var formularioDados;
	if (comunicacaoPerdaAux.id == 0) {
		formularioDados = {
			nome: nome,
			email: email,
			cpf: cpf,
			latitude: latitude,
			longitude: longitude,
			tipo_lavoura: tipoLavoura,
			data_colheita: dataColheita,
			evento_ocorrido: evento
		};
	} else {
		formularioDados = {
			id: comunicacaoPerdaAux.id,
			nome: nome,
			email: email,
			cpf: cpf,
			latitude: latitude,
			longitude: longitude,
			tipo_lavoura: tipoLavoura,
			data_colheita: dataColheita,
			evento_ocorrido: evento
		};
	}

	return formularioDados;
}

function base64ToArrayBuffer(base64) {
	var binaryString = window.atob(base64);
	var binaryLen = binaryString.length;
	var bytes = new Uint8Array(binaryLen);
	for (var i = 0; i < binaryLen; i++) {
		var ascii = binaryString.charCodeAt(i);
		bytes[i] = ascii;
	}
	return bytes;
}

function saveByteArray(reportName, resultByte, tipo) {
	var bytes = new Uint8Array(resultByte);
	var formato = 'application/' + tipo
	var blob = new Blob([bytes], { type: formato });
	var link = document.createElement('a');
	link.href = window.URL.createObjectURL(blob);
	var fileName = reportName += tipo == 'sql' ? '.sql' : '';
	link.download = fileName;
	link.click();
}

function functionGerarRelatorio() {
	loading(true);
	jQuery.ajax({
		url: "../api/v1/proagro/gerar-relatorio",
		type: 'POST',
		dataType: 'json',
		contentType: "application/json; charset=utf-8",
		error: function(dados) {
			loading(false);
			showToast(TOAST_ERROR, "Ocorreu um erro ao gerar relatorio");
		},
		success: function(dados) {
			const retornoAjax = dados.diretorio;
			if (retornoAjax != undefined) {
				var arrayByte = base64ToArrayBuffer(retornoAjax);
				saveByteArray('Relatório', arrayByte, 'pdf');
				loading(false);
				showToast(TOAST_SUCCESS, "Relatorio gerado com sucesso");
			} else {
				loading(false);
				showToast(TOAST_ERROR, "Ocorreu um erro ao gerar relatorio");
			}
		}
	});
}

function VerificaCPF(strCpf) {
	strCpf = strCpf.replaceAll('.', '');
	strCpf = strCpf.replace('-', '');
	var soma;
	var resto;
	soma = 0;
	if (strCpf == "00000000000") {
		return false;
	}

	for (i = 1; i <= 9; i++) {
		soma = soma + parseInt(strCpf.substring(i - 1, i)) * (11 - i);
	}

	resto = soma % 11;

	if (resto == 10 || resto == 11 || resto < 2) {
		resto = 0;
	} else {
		resto = 11 - resto;
	}

	if (resto != parseInt(strCpf.substring(9, 10))) {
		return false;
	}

	soma = 0;

	for (i = 1; i <= 10; i++) {
		soma = soma + parseInt(strCpf.substring(i - 1, i)) * (12 - i);
	}
	resto = soma % 11;

	if (resto == 10 || resto == 11 || resto < 2) {
		resto = 0;
	} else {
		resto = 11 - resto;
	}

	if (resto != parseInt(strCpf.substring(10, 11))) {
		return false;
	}

	return true;
}

function limparCampos() {
	document.getElementById('nome_comunicacao_perda').value = "";
	document.getElementById('email_comunicacao_perda').value = "";
	document.getElementById('cpf_comunicacao_perda').value = "";
	document.getElementById('latitude_comunicacao_perda').value = "";
	document.getElementById('longitude_comunicacao_perda').value = "";
	document.getElementById('tipo_lavoura_comunicacao_perda').value = "";
	document.getElementById('data_colheita_comunicacao_perda').value = "";
	document.getElementById('evento_ocorrido_comunicacao_perda').value = "";
	document.getElementById('evento_ocorrido_comunicacao_perda').selectedIndex = 0;
	document.getElementById('alertaCampoEmBranco').hidden = true;
	comunicacaoPerdaAux = new ComunicacaoPerdaClass(0);
}

function preencherCampos(comunicacaoPerda) {
	document.getElementById('nome_comunicacao_perda').value = comunicacaoPerda.nome;
	document.getElementById('email_comunicacao_perda').value = comunicacaoPerda.email;
	document.getElementById('cpf_comunicacao_perda').value = comunicacaoPerda.cpf;
	document.getElementById('latitude_comunicacao_perda').value = comunicacaoPerda.latitude;
	document.getElementById('longitude_comunicacao_perda').value = comunicacaoPerda.longitude;
	document.getElementById('tipo_lavoura_comunicacao_perda').value = comunicacaoPerda.tipo_lavoura;
	document.getElementById('data_colheita_comunicacao_perda').value = comunicacaoPerda.data_colheita;

	let select = document.getElementById('evento_ocorrido_comunicacao_perda');
	for (var i = 0; i < select.options.length; i++) {
		if (select.options[i].value === comunicacaoPerda.evento_ocorrido) {
			select.selectedIndex = i;
			break;
		}
	}
	comunicacaoPerdaAux = new ComunicacaoPerdaClass(comunicacaoPerda.id);
}

function retornaListaAtualizada(comunicacaoPerda) {

	var qtdLinhas = $("#table_usuarios tbody tr").length;
	var lista = [];
	for (var i = 0; i < qtdLinhas; i++) {
		var tableChild = $("#table_usuarios tbody tr")[i].cells;
		var id = tableChild[0].innerText;
		var acoes = "<div style='cursor: pointer;' class='dropdown'> " +
			"<a class='btn btn-secondary btn-sm dropdown-toggle' href='#' role='button' id='dropdownMenuLink' data-bs-toggle='dropdown' aria-expanded='false'>Ações</a>" +
			"<ul class='dropdown-menu' aria-labelledby='dropdownMenuButton1'>" +
			"<li>" + `<a class='dropdown-item' data-bs-toggle='modal' data-bs-target='#modal_novo_usuario' onclick="functionReadByID(${id})" >Editar</a>` +
			"</li>" + "<li>" + `<a class='dropdown-item' onclick="functionDelete(${id})">Deletar</a>` + "</li>" +
			"</ul>" + "</div>";
		if (id == comunicacaoPerda.id) {
			lista.push({
				id: id,
				nome: comunicacaoPerda.nome,
				email: comunicacaoPerda.email,
				cpf: comunicacaoPerda.cpf,
				latitude: comunicacaoPerda.latitude,
				longitude: comunicacaoPerda.longitude,
				tipo_lavoura: comunicacaoPerda.tipo_lavoura,
				data_colheita: retornaDataFormatada(comunicacaoPerda.data_colheita),
				evento: comunicacaoPerda.evento_ocorrido,
				action: acoes
			});
		} else {
			lista.push({
				id: id,
				nome: tableChild[1].innerText,
				email: tableChild[2].innerText,
				cpf: tableChild[3].innerText,
				latitude: tableChild[4].innerText,
				longitude: tableChild[5].innerText,
				tipo_lavoura: tableChild[6].innerText,
				data_colheita: tableChild[7].innerText,
				evento: tableChild[8].innerText,
				action: acoes
			});
		}

	}

	$('#table_usuarios').bootstrapTable('load', lista);
	showToast(TOAST_SUCCESS, "Atualizado com sucesso");
}