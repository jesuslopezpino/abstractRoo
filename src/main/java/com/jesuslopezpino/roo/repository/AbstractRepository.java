package com.jesuslopezpino.roo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jesuslopezpino.roo.domain.AbstractEntity;
import com.jesuslopezpino.roo.domain.QAbstractEntity;

import io.springlets.data.domain.GlobalSearch;


//import com.rindus.reservationdemo.repository.ProductRepositoryCustom;

//@RooJpaRepositoryCustomImpl(repository = ProductRepositoryCustom.class)
public interface AbstractRepository<Entity extends AbstractEntity, QEntity extends QAbstractEntity> {

	void delete(Entity entity);

	List<Entity> save(Iterable<Entity> entities);

	Entity save(Entity entity);

	Entity findOne(Long id);

	List<Entity> findAll(Iterable<Long> ids);

	void deleteInBatch(List<Entity> toDelete);

	Entity findOneDetached(Long id);

	Page<Entity> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

	long count();

	Page<Entity> findAll(GlobalSearch globalSearch, Pageable pageable);

	List<Entity> findAll();


}
