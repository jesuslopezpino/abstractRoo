package com.jesuslopezpino.roo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.OrderSpecifier.NullHandling;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPQLQuery;

import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;

public class MyQueryDslRepositorySupportExt<T> extends QueryDslRepositorySupportExt<T> {

	public MyQueryDslRepositorySupportExt(Class<T> domainClass) {
		super(domainClass);
		// TODO Auto-generated constructor stub
	}

	/**
	   * Applies the given {@link Pageable} to the given {@link JPQLQuery}.
	   *
	   * @param pageable the ordering and paging information
	   * @param query the query to apply to
	   * @return the updated query
	   */
	  protected JPQLQuery<T> applyPagination(Pageable pageable, JPQLQuery<T> query) {
	    return getQuerydsl().applyPagination(pageable, query);
	  }

	  /**
	   * Applies the given {@link Pageable} to the given {@link JPQLQuery}.
	   * Allows to map the attributes to order as provided in the {@link Pageable}
	   * to real entity attributes. This might be used to work with projections
	   * or DTOs whose attributes don't have the same name as the entity ones.
	   *
	   * It allows to map to more than one entity attribute. As an example, if
	   * the DTO used to create the {@link Pageable} has a fullName attribute, you
	   * could map that attribute to two entity attributes: name and surname.
	   * In this case, the {@link Pageable} defines an order by a fullName
	   * attribute, but que query will order by name and surname instead.
	   *
	   * @param pageable the ordering and paging
	   * @param query
	   * @param mapping definition of a mapping of order attribute names to
	   *        real entity ones
	   * @return the updated query
	   */
	  protected JPQLQuery<T> applyPagination(Pageable pageable, JPQLQuery<T> query,
	      AttributeMappingBuilder mapping) {
	    return applyPagination(pageable, query, mapping.asMap());
	  }

	  /**
	   * Applies the given {@link Pageable} to the given {@link JPQLQuery}.
	   * Allows to map the attributes to order as provided in the {@link Pageable}
	   * to real entity attributes. This might be used to work with projections
	   * or DTOs whose attributes don't have the same name as the entity ones.
	   *
	   * It allows to map to more than one entity attribute. As an example, if
	   * the DTO used to create the {@link Pageable} has a fullName attribute, you
	   * could map that attribute to two entity attributes: name and surname.
	   * In this case, the {@link Pageable} defines an order by a fullName
	   * attribute, but que query will order by name and surname instead.
	   *
	   * @param pageable the ordering and paging
	   * @param query
	   * @param attributeMapping definition of a mapping of order attribute names
	   *        to real entity ones
	   * @return the updated query
	   */
	  public JPQLQuery<T> applyPagination(Pageable pageable, JPQLQuery<T> query,
	      Map<String, Path<?>[]> attributeMapping) {
		  return super.applyPagination(pageable, query, attributeMapping);
	  }

	  /**
	   * Creates a factory to easily build an attribute mapping to
	   * real entity attribute names.
	   * @return the mapping factory
	   */
	  public AttributeMappingBuilder buildMapper() {
	    return super.buildMapper();
	  }

	  /**
	   * Adds to a query an order by the entity identifier related to this repository.
	   * This is useful as the default last order in queries where pagination is
	   * applied, so you have always an absolute order. Otherwise, the order
	   * of the results depends on the database criteria, which might change
	   * even between pages, returning confusing results for the user.
	   * @param query
	   * @return the updated query
	   */
	  @SuppressWarnings({"rawtypes", "unchecked"})
	  public JPQLQuery<T> applyOrderById(JPQLQuery<T> query) {
	    return super.applyOrderById(query);
	  }

	  /**
	   * Returns the path of entity identifier field
	   *
	   * @return path of entity Identifier
	   */
	  public PathBuilder<Object> getEntityId() {
	    return super.getEntityId();
	  }


	  /**
	   * Adds a global contains text filter on the provided attributes.
	   * WARNING: this creates a very inefficient query. If you have many entity
	   * instances to query, use instead an indexed text search solution for better
	   * performance.
	   * @param text the text to look for
	   * @param query
	   * @param globalSearchAttributes the list of attributes to perform the
	   *        filter on
	   * @return the updated query
	   */
	  public JPQLQuery<T> applyGlobalSearch(String text, JPQLQuery<T> query,
	      Path<?>... globalSearchAttributes) {
	    return super.applyGlobalSearch(text, query, globalSearchAttributes);
	  }

	  /**
	   * Adds a global contains text filter on the provided attributes.
	   * WARNING: this creates a very inefficient query. If you have many entity
	   * instances to query, use instead an indexed text search solution for better
	   * performance.
	   * @param globalSearch Contains the text to look for
	   * @param query
	   * @param globalSearchAttributes the list of attributes to perform the
	   *        filter on
	   * @return the updated query
	   */
	  public JPQLQuery<T> applyGlobalSearch(GlobalSearch globalSearch, JPQLQuery<T> query,
	      Path<?>... globalSearchAttributes) {
	    return super.applyGlobalSearch(globalSearch, query, globalSearchAttributes);
	  }

	  /**
	   * Loads a page of data with the provided pagination criteria. It allows to
	   * load full entities as well as projections.
	   *
	   * TODO: the current implementation expects the query to have already applied
	   * the paging and sorting criteria, which is not what one could expect from
	   * the method signature.
	   *
	   * Sample loading entities:
	   *
	   * <pre class="code">
	   * loadPage(query, pageable, QEmployee.employee);
	   * </pre>
	   *
	   * Sample with a projection:
	   *
	   * <pre class="code">
	   * loadPage(query, pageable, Projections.constructor(EmployeeInfo.class,
	   *    employee.id, employee.firstName, employee.lastName, employee.phone, employee.extension,
	   *    employee.supervisor.id, employee.supervisor.firstName, employee.supervisor.lastName));
	   * </pre>
	   *
	   * @param <M> the data type to load, usually a JPA Entity or a projection bean
	   * @param query the query with the pagination and ordering criteria already applied
	   * @param pageable the already applied pagination and ordering criteria
	   * @param expression the entity or projection to build with the query data
	   * @return the loaded data page
	   */
	  public <M> Page<M> loadPage(JPQLQuery<T> query, Pageable pageable, Expression<M> expression) {
	    return super.loadPage(query, pageable, expression);
	  }
}
