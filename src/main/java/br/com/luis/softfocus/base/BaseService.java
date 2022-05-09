package br.com.luis.softfocus.base;

import java.util.List;

public abstract interface BaseService<T> {
	List<T> readAll();

	boolean create(T entity);

	T readById(long id);

	boolean update(T entity);

	boolean deleteById(Long id);
}