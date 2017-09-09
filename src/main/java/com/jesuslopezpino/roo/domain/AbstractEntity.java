package com.jesuslopezpino.roo.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import io.springlets.format.EntityFormat;

import java.io.Serializable;

import javax.persistence.Entity;
import org.springframework.util.Assert;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public abstract class AbstractEntity implements Serializable{

	static {
		
	}
	/**
	 * TODO Auto-generated attribute documentation
	 * 
	 */
	public static final String ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

	/**
	 * TODO Auto-generated attribute documentation
	 * 
	 */
	public static final String ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

	/**
	 * TODO Auto-generated attribute documentation
	 *
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * TODO Auto-generated attribute documentation
	 *
	 */
	@Version
	private Integer version;

	public AbstractEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @return String
	 */
	public String toString() {
		return "AbstractEntity {" + "id='" + id + '\'' + ", version='" + version + '\''
				+ ", ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE='" + ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE + '\''
				+ ", ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE='" + ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE + '\'' + "}"
				+ super.toString();
	}

}
