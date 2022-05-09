package br.com.luis.softfocus.restcontrollerweb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.softfocus.base.BaseDao;
import br.com.luis.softfocus.model.ComunicacaoPerda;
import br.com.luis.softfocus.serviceweb.ComunicacaoPerdaControllerWebService;

@Service
@RestController
public class RestControllerWeb implements BaseDao<ComunicacaoPerda> {

	@Autowired
	private ComunicacaoPerdaControllerWebService comunicacaoPerdaControllerWebService;

	@RequestMapping(value = "/read-all")
	@Override
	public List<ComunicacaoPerda> readAll() {
		return comunicacaoPerdaControllerWebService.readAll();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Override
	public Map<String, Object> create(@RequestBody ComunicacaoPerda entity) {
		return comunicacaoPerdaControllerWebService.create(entity);
	}

	@RequestMapping(value = "/read-by-id", method = RequestMethod.POST)
	@Override
	public ComunicacaoPerda readById(long id) {
		return comunicacaoPerdaControllerWebService.readByID(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@Override
	public boolean update(@RequestBody ComunicacaoPerda entity) {
		return comunicacaoPerdaControllerWebService.update(entity);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Override
	public boolean deleteById(Long id) {
		return comunicacaoPerdaControllerWebService.delete(id);
	}

}
