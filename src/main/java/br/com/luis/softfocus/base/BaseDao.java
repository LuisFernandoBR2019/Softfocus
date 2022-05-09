package br.com.luis.softfocus.base;

import java.util.List;
import java.util.Map;

public abstract interface BaseDao<T> {
	List<T> readAll();

	Map<String, Object> create(T entity);

	T readById(long id);

	boolean update(T entity);

	boolean deleteById(Long id);
}