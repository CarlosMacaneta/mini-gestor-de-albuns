package com.exame.recorrencia.controllers;

import com.exame.recorrencia.models.Album;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author CarlosMacaneta
 */
public class AlbumDao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf;

    public AlbumDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AlbumDao(String persistenceUnit) {
        this.emf = Persistence.createEntityManagerFactory(persistenceUnit);
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Album album) {
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(album);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
  
    public List<Album> findAll() {
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery<Album> cq =  em.getCriteriaBuilder().createQuery(Album.class);
            cq.select(cq.from(Album.class));
            Query q = em.createQuery(cq);

            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Album> rt = cq.from(Album.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query query = em.createQuery(cq);
            return (query.getSingleResult() == null ? 0 : ((Long)query.getSingleResult()).intValue());
        } finally {
            em.close();
        }
    }
   
    public Album findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            assert id != null;
            return em.find(Album.class, id);
        } finally {
            em.close();
        }
    }    
   
    public void edit(Album album) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            album = em.merge(album);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = album.getId();
                if (findById(id) == null) {
                    throw new Error("O <<Entidade>> com id: " + id + " não existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
   
    public void destroy(int id) {
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            Album album; 

            album = em.getReference(Album.class, id);
            album.getId(); 

            em.remove(album);
            em.getTransaction().commit();
        }finally{
            if (em != null) {
                em.close();
            }
        }
    }
    
    public int getTodaySum() { //entity
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT SUM(p.quantity) FROM product p JOIN donate d " +
                    "WHERE d.idProduct = p.id AND d.date = CURDATE()");
            return query.getSingleResult() == null ? 0 : ((Long) query.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
 
}
