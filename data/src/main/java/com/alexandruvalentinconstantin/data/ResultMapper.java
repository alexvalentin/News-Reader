package com.alexandruvalentinconstantin.data;

/**
 * Mapper interface used for decoupling data and presentation layers. it Returns a new Model
 * resulted from to one received. Ex. a data model that needs to be passed from view model to repo.
 * Also check Mapper class and Rx mappers.
 *
 * @param <P> the model to be mapped
 * @param <R> the new model created during mapping
 */
public interface ResultMapper<P, R> {

    R map(P model);
}
