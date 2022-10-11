package com.smart.generic;

import com.smart.util.model.SearchResponse;

public interface IGenericSearch<T>{
	
	
	SearchResponse search(String queryString, Integer page, Integer limit,String orderBy,String orderType);
    
	 

}
