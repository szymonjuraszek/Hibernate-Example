package com.szymon.application.dao.utility;

import com.szymon.application.dao.interace.DaoRetriever;

import javax.persistence.NoResultException;
import java.util.Optional;

public final class UtilityDao {

    public static <T> Optional<T> findOrEmpty(final DaoRetriever<T> retriever) {
        try {
            return Optional.of(retriever.retrieve());
        } catch (NoResultException ex) {
            System.err.println("Nie znaleziono rekordu");
        }
        return Optional.empty();
    }
}
