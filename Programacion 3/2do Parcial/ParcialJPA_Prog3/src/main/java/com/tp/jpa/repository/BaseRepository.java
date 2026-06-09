package com.tp.jpa.repository;

import com.tp.jpa.model.Base;
import com.tp.jpa.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T extends Base> {
    private Class<T> clase;
    protected EntityManagerFactory emf;

    public BaseRepository(Class<T> clase){
        this.clase = clase;
        this.emf = JPAUtil.getEntityManagerFactory();
    }

    public T guardar(T entity){
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            entity = em.merge(entity);
            em.getTransaction().commit();
            return entity;

        }catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            }

        finally{
            em.close();
        }
        return null;

    }

    public Optional<T> buscarPorId(Long id){
        EntityManager em = emf.createEntityManager();
        try
        {
            Optional<T> buscarId = Optional.ofNullable(em.find(clase, id));
            return buscarId;

        }finally{
            em.close();
        }
    }

    public List<T> listarActivos(){
        EntityManager em = emf.createEntityManager();
        try
        {
            TypedQuery<T> query = em.createQuery("SELECT e FROM " + clase.getSimpleName() + " e WHERE e.eliminado = false", clase);
            return query.getResultList();

        }finally{
            em.close();
        }
    }


    public boolean eliminarLogico(Long id) {
        EntityManager em = emf.createEntityManager();
        try
        {
            Optional<T> buscarId = Optional.ofNullable(em.find(clase, id));
            if (buscarId.isPresent()){
                em.getTransaction().begin();
                T entidad = buscarId.get();
                entidad.setEliminado(true);
                em.merge(entidad);
                em.getTransaction().commit();
                return true;
            }
            else{
                return false;
            }

        }catch (Exception e) {
            em.getTransaction().rollback();
        }

        finally{
            em.close();
        }
        return false;
    }


}
