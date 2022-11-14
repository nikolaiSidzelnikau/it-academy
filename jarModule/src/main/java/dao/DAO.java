package dao;

import exception.CatchingCauseException;
import exception.LoginException;

public interface DAO<T> {
    void save(T t) throws LoginException, CatchingCauseException;

    T get(long id) throws CatchingCauseException;

    void update(T t) throws LoginException, CatchingCauseException;

    void delete(long id) throws CatchingCauseException;
}
