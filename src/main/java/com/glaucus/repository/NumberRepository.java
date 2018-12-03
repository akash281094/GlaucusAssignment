package com.glaucus.repository;

import com.glaucus.model.Numbers;

public interface NumberRepository {
    boolean incrementNumber(int id);
    Numbers getNumber(int id);
    void addNumberRow(Numbers numbers);
}
