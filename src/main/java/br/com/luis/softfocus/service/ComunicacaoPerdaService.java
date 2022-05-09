package br.com.luis.softfocus.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luis.softfocus.dao.ComunicacaoPerdaDao;
import br.com.luis.softfocus.idao.IComunicacaoPerda;
import br.com.luis.softfocus.model.ComunicacaoPerda;

@Service
public class ComunicacaoPerdaService implements IComunicacaoPerda {

	@Autowired
	private ComunicacaoPerdaDao comunicacaoPerdaDao;

	@Override
	public List<ComunicacaoPerda> readAll() {
		return comunicacaoPerdaDao.readAll();
	}

	@Override
	public Map<String, Object> create(ComunicacaoPerda entity) {
		return comunicacaoPerdaDao.create(entity);
	}

	@Override
	public ComunicacaoPerda readById(long id) {
		return comunicacaoPerdaDao.readById(id);
	}

	@Override
	public boolean update(ComunicacaoPerda entity) {
		return comunicacaoPerdaDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		return comunicacaoPerdaDao.deleteById(id);
	}

}
