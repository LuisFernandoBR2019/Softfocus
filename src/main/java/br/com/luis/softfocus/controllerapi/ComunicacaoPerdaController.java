package br.com.luis.softfocus.controllerapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.luis.softfocus.icontroller.IComunicacaoPerdaController;
import br.com.luis.softfocus.jasper.ManipulaArquivos;
import br.com.luis.softfocus.jasper.RelatorioJasperServicos;
import br.com.luis.softfocus.model.ComunicacaoPerda;
import br.com.luis.softfocus.service.ComunicacaoPerdaService;
import br.com.luis.softfocus.utils.ValidacaoComunicacaoPerda;

@RequestMapping("/api/v1/proagro")
@RestController
@CrossOrigin(origins = "*")
public class ComunicacaoPerdaController implements IComunicacaoPerdaController {

	@Autowired
	private ComunicacaoPerdaService comunicacaoPerdaService;

	@Autowired
	private RelatorioJasperServicos relatorioJasperServicos;

	@Autowired
	private ManipulaArquivos manipulaArquivos;

	@Autowired
	private ValidacaoComunicacaoPerda validacao;

	@GetMapping("/read-all")
	@Override
	public ResponseEntity<List<ComunicacaoPerda>> readAll() {
		List<ComunicacaoPerda> list = comunicacaoPerdaService.readAll();
		if (list == null || list.size() == 0) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(list);
		}
	}

	@GetMapping("/read-by-id/{id}")
	@Override
	public ResponseEntity<ComunicacaoPerda> readById(@PathVariable("id") Long id) {
		ComunicacaoPerda comunicacaoPerda = comunicacaoPerdaService.readById(id);
		if (comunicacaoPerda == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(comunicacaoPerda);
	}

	@PutMapping("/update")
	@Override
	public ResponseEntity<Boolean> update(@RequestBody ComunicacaoPerda entity) {
		try {
			if (!validacao.validaCamposBrancoOuNulo(entity)) {
				Boolean response = comunicacaoPerdaService.update(entity);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
			} else {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/create")
	@Override
	public ResponseEntity<Map<String, Object>> create(@RequestBody ComunicacaoPerda entity) {
		try {
			Map<String, Object> response = new HashMap<>();
			if (!validacao.validaCamposBrancoOuNulo(entity)) {
				response = comunicacaoPerdaService.create(entity);
				if (response != null) {
					if (validacao.calculaRaio(entity, comunicacaoPerdaService.readAll())) {
						response.put("raio", "Está em um raio de 10km");
					}

					return ResponseEntity.status(HttpStatus.CREATED).body(response);
				}
				response.put("created", false);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
			} else {
				response.put("created", null);
				response.put("empty", "Possui campo em branco");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		try {
			Boolean response = comunicacaoPerdaService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/gerar-relatorio")
	@Override
	public ResponseEntity<Map<String, byte[]>> gerarRelatorio() {
		try {
			List<ComunicacaoPerda> list = comunicacaoPerdaService.readAll();

			Map<String, byte[]> retorno = new HashMap<String, byte[]>();
			List<Byte> listBytes = manipulaArquivos.fileRead(relatorioJasperServicos.generateAndGetPdfFilePath(list));
			byte[] bytes = new byte[listBytes.size()];
			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = listBytes.get(i);
			}
			retorno.put("diretorio", bytes);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(retorno);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

}
