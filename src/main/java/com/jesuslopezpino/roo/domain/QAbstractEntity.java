package com.jesuslopezpino.roo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import java.lang.reflect.ParameterizedType;

import com.querydsl.core.types.dsl.*;
//import com.rindus.reservationdemo.domain.Product;
//import com.rindus.reservationdemo.domain.QProduct;
//import com.rindus.reservationdemo.domain.QStock;
//import com.rindus.reservationdemo.domain.Stock;
import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

/**
 * QProduct is a Querydsl query type for Product
 */
//@Generated("com.querydsl.codegen.EntitySerializer")
public class QAbstractEntity extends EntityPathBase<AbstractEntity> {

	private static final long serialVersionUID = 37131226L;

	public static final QAbstractEntity product = new QAbstractEntity("product");

	public final StringPath description = createString("description");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	// public final StringPath name = createString("name");

	// public final NumberPath<Long> price = createNumber("price", Long.class);

	// public final ListPath<Stock, QStock> productosStock = this.<Stock,
	// QStock>createList("productosStock", Stock.class, QStock.class,
	// PathInits.DIRECT2);

	public final NumberPath<Integer> version = createNumber("version", Integer.class);

	public QAbstractEntity(String variable) {
		super(AbstractEntity.class, forVariable(variable));
	}

	public QAbstractEntity(Path<? extends AbstractEntity> path) {
		super(path.getType(), path.getMetadata());
	}

	public QAbstractEntity(PathMetadata metadata) {
		super(AbstractEntity.class, metadata);
	}
}
