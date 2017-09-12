package com.jesuslopezpino.roo.repository;

import java.util.List;

//import com.arjuna.ats.internal.jdbc.drivers.modifiers.extensions;
import com.jesuslopezpino.roo.domain.QAbstractEntity;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
//import com.rindus.reservationdemo.domain.Product;
//import com.rindus.reservationdemo.domain.QProduct;

//import com.rindus.reservationdemo.domain.Product;
//import com.rindus.reservationdemo.domain.QProduct;
//import com.rindus.reservationdemo.repository.ProductRepositoryCustom;
//import com.rindus.reservationdemo.repository.ProductRepositoryImpl;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import io.springlets.data.domain.GlobalSearch;

@Transactional(readOnly = true)
public class AbstractRepositoryImpl<Entity, QEntity extends QAbstractEntity>
		extends QueryDslRepositorySupportExt<Entity> implements AbstractRepository<Entity, QEntity> {

	public AbstractRepositoryImpl(Class<Entity> domainClass) {
		super(domainClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void delete(Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Entity> save(Iterable<Entity> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity save(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entity> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(List<Entity> toDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity findOneDetached(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Entity> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * TODO Auto-generated attribute documentation
	 * 
	 */
	public static final String PRICE = "price";

	/**
	 * TODO Auto-generated attribute documentation
	 * 
	 */
	public static final String NAME = "name";

	/**
	 * TODO Auto-generated attribute documentation
	 * 
	 */
	public static final String DESCRIPTION = "description";

	@Override
	public Page<Entity> findAll(GlobalSearch globalSearch, Pageable pageable) {
		QEntity product = (QEntity) QEntity.product;

		JPQLQuery<Entity> query = (JPQLQuery<Entity>) from(product);

		Path<?>[] paths = new Path<?>[] { 
//			product.price, product.name, 
			product.description };
		applyGlobalSearch(globalSearch, query, paths);

		AttributeMappingBuilder mapping = buildMapper()
//				.map(PRICE, product.price).map(NAME, product.name)
				.map(DESCRIPTION, product.description);

		applyPagination(pageable, query, mapping);
		applyOrderById(query);

		return (Page<Entity>) loadPage(query, pageable, product);
	}

	@Override
	public List<Entity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
