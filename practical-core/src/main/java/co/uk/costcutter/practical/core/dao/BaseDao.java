package co.uk.costcutter.practical.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BaseDao {

    protected EntityManager em;

    public BaseDao() {
    }

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    protected <T> List<T> namedSearch(String queryName, Map<String, Object> params) {
        EntityManager em = this.em;

        List var5;
        try {
            Query query = this.createNamedQuery(em, queryName, params);
            var5 = this.findList(query);
        } finally {
            this.safeClose(em);
        }

        return var5;
    }

    protected Query createNamedQuery(EntityManager em, String queryName, Map<String, Object> params) {
        Query query = em.createNamedQuery(queryName);
        this.createNamedParameters(query, params);
        return query;
    }

    private void createNamedParameters(Query query, Map<String, Object> params) {
        if (params != null) {
            Iterator i$ = params.entrySet().iterator();

            while(i$.hasNext()) {
                Entry<String, Object> param = (Entry)i$.next();
                    query.setParameter((String)param.getKey(), param.getValue());
            }
        }
    }

    private <T> List<T> findList(Query query) {
        return query.getResultList();
    }

    protected void safeClose(EntityManager em) {
        if (em != null) {
            em.close();
        }
    }
}
