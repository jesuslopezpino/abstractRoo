package com.jesuslopezpino.roo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.jesuslopezpino.roo.domain.AbstractEntity;
//import com.arjuna.ats.internal.jdbc.drivers.modifiers.extensions;
import com.jesuslopezpino.roo.domain.QAbstractEntity;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPAQueryBase;
import com.querydsl.jpa.JPQLQuery;
//import com.rindus.reservationdemo.domain.Product;
//import com.rindus.reservationdemo.domain.QProduct;
//import com.rindus.reservationdemo.domain.Product;
//import com.rindus.reservationdemo.domain.QProduct;

//import com.rindus.reservationdemo.domain.Product;
//import com.rindus.reservationdemo.domain.QProduct;
//import com.rindus.reservationdemo.repository.ProductRepositoryCustom;
//import com.rindus.reservationdemo.repository.ProductRepositoryImpl;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.DetachableJpaRepositoryImpl;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.transaction.annotation.Transactional;

import io.springlets.data.domain.GlobalSearch;

@Transactional(readOnly = true)
public abstract class AbstractRepositoryImpl<Entity extends AbstractEntity, QEntity extends QAbstractEntity>
		// extends QueryDslRepositorySupportExt<Entity>
//		extends DetachableJpaRepositoryImpl<Entity, Long> 
implements AbstractRepository<Entity, QEntity> {

	 DetachableJpaRepositoryImpl<Entity, Long> detachableJpaRepositoryImpl;
	MyQueryDslRepositorySupportExt<Entity> queryDslRepositorySupportExt = new MyQueryDslRepositorySupportExt<Entity>(
			null);
	 @Override
	 public void delete(Entity entity) {
	 detachableJpaRepositoryImpl.delete(entity);
	 }
	
	 @Override
	 public List<Entity> save(Iterable<Entity> entities) {
	 return detachableJpaRepositoryImpl.save(entities);
	 }

	 @Override
	 public Entity save(Entity entity) {
	 return detachableJpaRepositoryImpl.save(entity);
	 }
	
	 @Override
	 public Entity findOne(Long id) {
	 return detachableJpaRepositoryImpl.findOne(id);
	 }
	
	 @Override
	 public List<Entity> findAll(Iterable<Long> ids) {
	 return detachableJpaRepositoryImpl.findAll(ids);
	 }
	
	 @Override
	 public void deleteInBatch(List<Entity> toDelete) {
	 detachableJpaRepositoryImpl.deleteInBatch(toDelete);
	 }

	 @Override
	 public Entity findOneDetached(Long id) {
	 return detachableJpaRepositoryImpl.findOneDetached(id);
	 }

	@Override
	public Page<Entity> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
		QEntity product = (QEntity) QEntity.entity;

		JPQLQuery<Entity> query = from(product);

		Path<?>[] paths = new Path<?>[] {
				// product.price,product.name,
				product.description };
		queryDslRepositorySupportExt.applyGlobalSearch(globalSearch, query, paths);

		// Also, filter by the provided ids
		query.where(product.id.in(ids));

		AttributeMappingBuilder mapping = queryDslRepositorySupportExt.buildMapper()
				// .map(PRICE, product.price)
				// .map(NAME, product.name)
				.map(DESCRIPTION, product.description);

		queryDslRepositorySupportExt.applyPagination(pageable, query, mapping);
		queryDslRepositorySupportExt.applyOrderById(query);

		return (Page<Entity>) queryDslRepositorySupportExt.loadPage(query, pageable, product);
	}

	@Override
	public long count() {
		return detachableJpaRepositoryImpl.count();
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
		QEntity product = (QEntity) QEntity.entity;

		JPQLQuery<Entity> query = from(product);

		Path<?>[] paths = new Path<?>[] {
				// product.price, product.name,
				product.description };
		queryDslRepositorySupportExt.applyGlobalSearch(globalSearch, query, paths);

		AttributeMappingBuilder mapping = queryDslRepositorySupportExt.buildMapper()
				// .map(PRICE, product.price).map(NAME, product.name)
				.map(DESCRIPTION, product.description);

		queryDslRepositorySupportExt.applyPagination(pageable, query, mapping);
		queryDslRepositorySupportExt.applyOrderById(query);

		return (Page<Entity>) queryDslRepositorySupportExt.loadPage(query, pageable, product);
	}

	 private JPQLQuery<Entity> from(QEntity product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	 public List<Entity> findAll() {
	 return detachableJpaRepositoryImpl.findAll();
	 }

}
