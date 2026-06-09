package com.tp.jpa.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory(){
        emf = (emf == null) ? Persistence.createEntityManagerFactory("parcialJPA") : emf;
        return emf;
    }
    public static void close(){
        if (emf != null) {emf.close();}
    }
}
