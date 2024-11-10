package com.alisievich.common.service;

public interface GenericMapper<T, U> {
    U map(T entity);
}
