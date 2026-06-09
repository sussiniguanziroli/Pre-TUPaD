package com.utn.repository;

import com.utn.entities.Producto;
import com.utn.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductoRepository extends BaseRepository<Producto> {

    public ProductoRepository() {
        super(Producto.class);
    }

    // Retorna todos los productos activos que pertenecen a la categoria indicada.
    // Usa JPQL con parametro nombrado :categoriaId para filtrar por categoria.id
    // y eliminado = false para excluir productos dados de baja logica.
    public List<Producto> buscarPorCategoria(Long categoriaId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Producto> query = em.createQuery(
                "SELECT p FROM Producto p WHERE p.categoria.id = :categoriaId AND p.eliminado = false",
                Producto.class
            );
            query.setParameter("categoriaId", categoriaId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
