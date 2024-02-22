package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Usuario;

@Transactional
@Repository
public class UsuarioImplRepository implements IUsuarioRepository {

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public Usuario selectForName(String name) {
		// TODO Auto-generated method stub
		TypedQuery<Usuario> myQuery = this.entityManager
				.createQuery("SELECTY u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class);
		myQuery.setParameter("nombre", name);
		return myQuery.getSingleResult();
	}

}
