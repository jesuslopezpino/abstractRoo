package com.jesuslopezpino.roo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;

public abstract interface EntityService<Entity> extends EntityResolver<Entity, Long> {

/**
 * TODO Auto-generated method documentation
 * 
 * @param id
 * @return Product
 */
public abstract Entity findOne(Long id);

/**
 * TODO Auto-generated method documentation
 * 
 * @param product
 */
public abstract void delete(Entity product);

/**
 * TODO Auto-generated method documentation
 * 
 * @param entities
 * @return List
 */
public abstract List<Entity> save(Iterable<Entity> entities);

/**
 * TODO Auto-generated method documentation
 * 
 * @param ids
 */
public abstract void delete(Iterable<Long> ids);

/**
 * TODO Auto-generated method documentation
 * 
 * @param entity
 * @return Product
 */
public abstract Entity save(Entity entity);

/**
 * TODO Auto-generated method documentation
 * 
 * @param id
 * @return Product
 */
public abstract Entity findOneForUpdate(Long id);

/**
 * TODO Auto-generated method documentation
 * 
 * @param ids
 * @return List
 */
public abstract List<Entity> findAll(Iterable<Long> ids);

/**
 * TODO Auto-generated method documentation
 * 
 * @return List
 */
public abstract List<Entity> findAll();

/**
 * TODO Auto-generated method documentation
 * 
 * @return Long
 */
public abstract long count();

/**
 * TODO Auto-generated method documentation
 * 
 * @param globalSearch
 * @param pageable
 * @return Page
 */
public abstract Page<Entity> findAll(GlobalSearch globalSearch, Pageable pageable);

/**
 * TODO Auto-generated method documentation
 * 
 * @param ids
 * @param globalSearch
 * @param pageable
 * @return Page
 */
public abstract Page<Entity> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

///**
// * TODO Auto-generated method documentation
// * 
// * @param product
// * @param productosStockToAdd
// * @return Product
// */
//public abstract Product addToProductosStock(Product product, Iterable<Long> productosStockToAdd);
//
///**
// * TODO Auto-generated method documentation
// * 
// * @param product
// * @param productosStockToRemove
// * @return Product
// */
//public abstract Product removeFromProductosStock(Product product, Iterable<Long> productosStockToRemove);
//
///**
// * TODO Auto-generated method documentation
// * 
// * @param product
// * @param productosStock
// * @return Product
// */
//public abstract Product setProductosStock(Product product, Iterable<Long> productosStock);

}
