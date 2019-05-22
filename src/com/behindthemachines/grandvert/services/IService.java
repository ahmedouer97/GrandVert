/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behindthemachines.grandvert.services;

import java.util.List;

/**
 *
 * @author psn
 */
public interface IService<T> {
    void add(T t);
    void delete(T t);
    void update(T t);
    List<T> getAll();
    T getById(int id);
}
