package com.utn.repository;

import com.utn.entities.Base;
import com.utn.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T extends Base> {

    private final Class<T> clazz;
    private final EntityManagerFactory emf;
    //Clase abs generica
    public BaseRepository(Class<T> clazz) {
        this.clazz = clazz;
        this.emf = JPAUtil.getEntityManagerFactory();
    }

    public T guardar(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            T merged = em.merge(entity);
            em.getTransaction().commit();
            return merged;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    //Cada metodo con su EntityManager
    public Optional<T> buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(clazz, id));
        } finally {//Siempre cierra bloque finally, sin importar el error
            em.close();
        }
    }

    public List<T> listarActivos() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e.eliminado = false";
            return em.createQuery(jpql, clazz).getResultList();
        } finally {
            em.close();
        }
    }

    public boolean eliminarLogico(Long id) {//Eliminar Logico
        EntityManager em = emf.createEntityManager();
        try {
            T entity = em.find(clazz, id);
            if (entity == null) return false;
            em.getTransaction().begin();
            entity.setEliminado(true);
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
