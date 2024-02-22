package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Usuario;

@Transactional
@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Usuario selectForName(String name) {
		// TODO Auto-generated method stub
		TypedQuery<Usuario> query = this.entityManager.createQuery("Select u From Usuario u Where u.name=:name",
				Usuario.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}

}
