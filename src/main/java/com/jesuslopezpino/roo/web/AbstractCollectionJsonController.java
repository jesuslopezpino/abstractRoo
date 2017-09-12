package com.jesuslopezpino.roo.web;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.jesuslopezpino.roo.domain.AbstractEntity;
import com.jesuslopezpino.roo.repository.AbstractRepository;
import com.jesuslopezpino.roo.service.EntityService;

import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;

import org.springframework.http.MediaType;

@Controller
@RestController
// @RequestMapping(value = "/products", name =
// "ProductsCollectionJsonController", produces =
// MediaType.APPLICATION_JSON_VALUE)
public abstract class AbstractCollectionJsonController<Entity extends AbstractEntity, Service extends EntityService<Entity, AbstractRepository<Entity>>> {

	private Service entityService;

	/**
	 * TODO Auto-generated constructor documentation
	 *
	 * @param productService
	 */
	@Autowired
	public AbstractCollectionJsonController(Service entityService) {
		this.entityService = entityService;
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param globalSearch
	 * @param pageable
	 * @return ResponseEntity
	 */
	@GetMapping(name = "list")
	public ResponseEntity<Page<Entity>> list(GlobalSearch globalSearch, Pageable pageable) {

		Page<Entity> entities = getEntityService().findAll(globalSearch, pageable);
		return ResponseEntity.ok(entities);
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @return UriComponents
	 */
	public static UriComponents listURI() {
		return MvcUriComponentsBuilder
				.fromMethodCall(MvcUriComponentsBuilder.on(AbstractCollectionJsonController.class).list(null, null))
				.build().encode();
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param product
	 * @param result
	 * @return ResponseEntity
	 */
	@PostMapping(name = "create")
	public ResponseEntity<?> create(@Valid @RequestBody Entity entity, BindingResult result) {

		if (entity.getId() != null || entity.getVersion() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}

		Entity newProduct = getEntityService().save(entity);
		// UriComponents showURI = AbstractItemJsonController.showURI(newProduct);
		// TODO: improve
		UriComponents showURI = new AbstractItemJsonController(getEntityService()).showURI(newProduct);

		return ResponseEntity.created(showURI.toUri()).build();
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param products
	 * @param result
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/batch", name = "createBatch")
	public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<Entity> entities, BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}

		getEntityService().save(entities);

		return ResponseEntity.created(listURI().toUri()).build();
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param products
	 * @param result
	 * @return ResponseEntity
	 */
	@PutMapping(value = "/batch", name = "updateBatch")
	public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<Entity> entities, BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}

		getEntityService().save(entities);

		return ResponseEntity.ok().build();
	}

	/**
	 * TODO Auto-generated method documentation
	 * 
	 * @param ids
	 * @return ResponseEntity
	 */
	@DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
	public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {

		getEntityService().delete(ids);

		return ResponseEntity.ok().build();
	}

	public Service getEntityService() {
		return entityService;
	}

	public void setEntityService(Service entityService) {
		this.entityService = entityService;
	}

}
