package com.smart.generic.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tennaito.rsql.jpa.JpaPredicateVisitor;
import com.smart.generic.IGenericDao;
import com.smart.util.model.SearchResponse;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;

public abstract class GenericDaoImpl<T> implements IGenericDao<T>{

	
	Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> type;

	public GenericDaoImpl(){
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public SearchResponse search(String queryString, Integer page, Integer limit,String orderBy,String orderType) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(getType());
		From root = criteriaQuery.from(getType());

		@SuppressWarnings("unchecked")
		RSQLVisitor<Predicate, EntityManager> visitor = new JpaPredicateVisitor<T>().defineRoot(root);

		if(queryString != null && !queryString.isEmpty()) {

			CriteriaQuery<T> query = criteriaQuery.where(getCriteriaQuery(queryString, visitor));
			TypedQuery<T> typedQuery = entityManager.createQuery(query);
			

			Long totalCount =  (long) (typedQuery.getResultList() != null ? typedQuery.getResultList().size() : 0L);
			Integer lowerLimit = (page - 1) * limit;
			Integer upperLimit = page * limit;

			if(lowerLimit >= 0) {
				typedQuery.setFirstResult(lowerLimit);
			}

			if(upperLimit >= 0) {
				typedQuery.setMaxResults(upperLimit);
			}
			List<T> resultList = typedQuery.getResultList();

			if (resultList == null || resultList.isEmpty()){
				return new  SearchResponse(Collections.emptyList(), 0L);
			}

			return new  SearchResponse(resultList, totalCount);


		}else {

			
			if(orderBy.equalsIgnoreCase("desc")){
				criteriaQuery.select(root).orderBy(builder.desc(root.get(orderBy)));
			}else if(orderBy.equalsIgnoreCase("asc")){
				criteriaQuery.select(root).orderBy(builder.asc(root.get(orderBy)));
			}


			TypedQuery<T> typedQuery = getEntityManager().createQuery(criteriaQuery);

			Long totalCount =  (long) (typedQuery.getResultList() != null ? typedQuery.getResultList().size() : 0L);
			Integer lowerLimit = (page - 1) * limit;
			Integer upperLimit = page * limit;

			if(lowerLimit>=0) {
				typedQuery.setFirstResult(lowerLimit);
			}

			if(upperLimit>=0) {
				typedQuery.setMaxResults(upperLimit);
			}

			List<T> resultList = typedQuery.getResultList();

			if (resultList == null || resultList.isEmpty()){
				return new  SearchResponse(Collections.emptyList(), 0L);
			}

			return new  SearchResponse(resultList, totalCount);

		}

	}
	
	private <T> Predicate getCriteriaQuery(String queryString, RSQLVisitor<Predicate, EntityManager> visitor) {
	      
		Node rootNode;
		Predicate predicate;
        
        try {
            	rootNode = new RSQLParser().parse(queryString);
            	predicate  = rootNode.accept(visitor,getEntityManager());
        }catch (Exception e){
        	e.printStackTrace();
            logger.error("An error happened while executing RSQL query", e);
            throw new IllegalArgumentException(e.getMessage());
        }
        return predicate;
    }

	

}
