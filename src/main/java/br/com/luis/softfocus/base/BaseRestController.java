package br.com.luis.softfocus.base;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public abstract interface BaseRestController<T> {
	ResponseEntity<List<T>> readAll();

	ResponseEntity<T> readById(Long id);

	ResponseEntity<Boolean> update(T entity);

	ResponseEntity<Map<String, Object>> create(T entity);

	ResponseEntity<Boolean> delete(Long id);
}
