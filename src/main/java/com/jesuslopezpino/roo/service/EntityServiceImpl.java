package com.jesuslopezpino.roo.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jesuslopezpino.roo.domain.QAbstractEntity;
import com.jesuslopezpino.roo.repository.AbstractRepository;

//import com.rindus.reservationdemo.domain.Product;
//import com.rindus.reservationdemo.domain.Stock;
//import com.rindus.reservationdemo.repository.ProductRepository;
//import com.rindus.reservationdemo.service.api.StockService;

import io.springlets.data.domain.GlobalSearch;

@Service
@Transactional(readOnly = true)
public abstract class EntityServiceImpl<Entity, Repository extends AbstractRepository<Entity, QAbstractEntity>>
		implements EntityService<Entity, Repository> {

	/**
	 * TODO Auto-generated attribute documentation
	 * 
	 */
	private AbstractRepository<Entity, QAbstractEntity> repository;
	private Class<Entity> entityType;

	// /**
	// * TODO Auto-generated attribute documentation
	// *
	// */
	// private StockService ProductServiceImpl.stockService;

	/**
	 * TODO Auto-generated constructor documentation
	 * 
	 * @param productRepository
	 * @param stockService
	 */
	@Autowired
	public EntityServiceImpl(Repository repository
	// , @Lazy StockService stockService
	) {
		setRepository(repository);
		// setStockService(stockService);
	}

	public AbstractRepository<Entity, QAbstractEntity> getRepository() {
		return repository;
	}

	public void setRepository(AbstractRepository<Entity, QAbstractEntity> repository) {
		this.repository = repository;
	}

	// /**
	// * TODO Auto-generated method documentation
	// *
	// * @return StockService
	// */
	// public StockService ProductServiceImpl.getStockService() {
	// return stockService;
	// }

	// /**
	// * TODO Auto-generated method documentation
	// *
	// * @param stockService
	// */
	// public void ProductServiceImpl.setStockService(StockService stockService) {
	// this.stockService = stockService;
	// }

	// /**
	// * TODO Auto-generated method documentation
	// *
	// * @param product
	// * @param productosStockToAdd
	// * @return Product
	// */
	// @Transactional
	// public Product addToProductosStock(Product product, Iterable<Long>
	// productosStockToAdd) {
	// List<Stock> productosStock = getStockService().findAll(productosStockToAdd);
	// product.addToProductosStock(productosStock);
	// return getProductRepository().save(product);
	// }

	// /**
	// * TODO Auto-generated method documentation
	// *
	// * @param product
	// * @param productosStockToRemove
	// * @return Product
	// */
	// @Transactional
	// public Product ProductServiceImpl.removeFromProductosStock(Product product,
	// Iterable<Long> productosStockToRemove) {
	// List<Stock> productosStock =
	// getStockService().findAll(productosStockToRemove);
	// product.removeFromProductosStock(productosStock);
	// return getProductRepository().save(product);
	// }

	// /**
	// * TODO Auto-generated method documentation
	// *
	// * @param product
	// * @param productosStock
	// * @return Product
	// */
	// @Transactional
	// public Product ProductServiceImpl.setProductosStock(Product product,
	// Iterable<Long> productosStock) {
	// List<Stock> items = getStockService().findAll(productosStock);
	// List<Stock> currents = product.getProductosStock();
	// Set<Stock> toRemove = new HashSet<Stock>();
	// for (Iterator<Stock> iterator = currents.iterator(); iterator.hasNext();) {
	// Stock nextStock = iterator.next();
	// if (items.contains(nextStock)) {
	// items.remove(nextStock);
	// } else {
	// toRemove.add(nextStock);
	// }
	// }
	// product.removeFromProductosStock(toRemove);
	// product.addToProductosStock(items);
	// // Force the version update of the parent side to know that the parent has
	// changed
	// // because it has new books assigned
	// product.setVersion(product.getVersion() + 1);
	// return getProductRepository().save(product);
	// }

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param product
	 */
	@Transactional
	public void delete(Entity entity) {
		// Clear bidirectional one-to-many parent relationship with Stock
		// for (Stock item : product.getProductosStock()) {
		// item.setProducto(null);
		// }

		getRepository().delete(entity);
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param entities
	 * @return List
	 */
	@Transactional
	public List<Entity> save(Iterable<Entity> entities) {
		return getRepository().save(entities);
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param ids
	 */
	@Transactional
	public void delete(Iterable<Long> ids) {
		List<Entity> toDelete = getRepository().findAll(ids);
		getRepository().deleteInBatch(toDelete);
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param entity
	 * @return Product
	 */
	@Transactional
	public Entity save(Entity entity) {
		return getRepository().save(entity);
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param id
	 * @return Product
	 */
	public Entity findOne(Long id) {
		return getRepository().findOne(id);
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param id
	 * @return Product
	 */
	public Entity findOneForUpdate(Long id) {
		return getRepository().findOneDetached(id);
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param ids
	 * @return List
	 */
	public List<Entity> findAll(Iterable<Long> ids) {
		return getRepository().findAll(ids);
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @return List
	 */
	public List<Entity> findAll() {
		return getRepository().findAll();
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @return Long
	 */
	public long count() {
		return getRepository().count();
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param globalSearch
	 * @param pageable
	 * @return Page
	 */
	public Page<Entity> findAll(GlobalSearch globalSearch, Pageable pageable) {
		return getRepository().findAll(globalSearch, pageable);
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param ids
	 * @param globalSearch
	 * @param pageable
	 * @return Page
	 */
	public Page<Entity> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
		return getRepository().findAllByIdsIn(ids, globalSearch, pageable);
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @return Class
	 */
	public Class<Entity> getEntityType() {
		// return Entity.class;
		return entityType;
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @return Class
	 */
	public Class<Long> getIdType() {
		return Long.class;
	}
}
