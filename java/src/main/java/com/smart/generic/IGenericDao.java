package com.smart.generic;

import java.util.List;

public interface IGenericDao<T> extends IGenericSearch<T> {
	
	 Long count();

	 void deleteById(Long id);

	 T findById(Long id);

	 T save(T entity);
	 
	 List<T> findAll();
	 

}
