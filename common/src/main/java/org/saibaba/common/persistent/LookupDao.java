package org.saibaba.common.persistent;

// Java classes
import java.util.List;

import org.saibaba.fw.domain.Lookup;


public interface LookupDao { 
    
    public List<Lookup> findAll(Class<Lookup> lookup) throws DaoException;
    
    public Lookup findByCode(Class<Lookup> lookup, String code) throws DaoException;
    
    public Lookup findByName(Class<Lookup> lookup, String name) throws DaoException;

}
