package com.jesuslopezpino.roo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.http.MediaType;
import com.jesuslopezpino.roo.domain.AbstractEntity;
import com.jesuslopezpino.roo.domain.QAbstractEntity;
import com.jesuslopezpino.roo.repository.AbstractRepository;
import com.jesuslopezpino.roo.service.EntityService;

import java.lang.reflect.ParameterizedType;
import io.springlets.web.NotFoundException;

@RestController
// @RequestMapping(value = "/products/{product}", name =
// "ProductsItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AbstractItemJsonController<Entity extends AbstractEntity, Service extends EntityService<Entity, AbstractRepository<Entity, QAbstractEntity>>> {

	private Service service;

	private Class<Entity> entityClass;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * TODO Auto-generated constructor documentation
	 * 
	 * @param productService
	 */
	@Autowired
	public AbstractItemJsonController(Service service) {
		this.service = service;
		// LOG.info("Instanciando el controlador: " + this.getClass().getName());
		entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		// LOG.debug("entityClass: " + entityClass.getName());
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param id
	 * @return Product
	 */
	@ModelAttribute
	public Entity getEntity(@PathVariable("entity") Long id) {
		Entity entity = service.findOne(id);
		if (entity == null) {
			throw new NotFoundException(String.format("Entity with identifier '%s' not found", id));
		}
		return entity;
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param product
	 * @return ResponseEntity
	 */
	@GetMapping(name = "show")
	public ResponseEntity<?> show(@ModelAttribute Entity entity) {
		return ResponseEntity.ok(entity);
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param product
	 * @return UriComponents
	 */
	public
	// static
	UriComponents showURI(Entity entity) {
		return MvcUriComponentsBuilder
				.fromMethodCall(MvcUriComponentsBuilder.on(AbstractItemJsonController.class).show(entity))
				.buildAndExpand(entity.getId()).encode();
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param storedProduct
	 * @param product
	 * @param result
	 * @return ResponseEntity
	 */
	@PutMapping(name = "update")
	public ResponseEntity<?> update(@ModelAttribute Entity storedEntity, @Valid @RequestBody Entity entity,
			BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}
		entity.setId(storedEntity.getId());
		getService().save(entity);
		return ResponseEntity.ok().build();
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param product
	 * @return ResponseEntity
	 */
	@DeleteMapping(name = "delete")
	public ResponseEntity<?> delete(@ModelAttribute Entity entity) {
		getService().delete(entity);
		return ResponseEntity.ok().build();
	}
}
