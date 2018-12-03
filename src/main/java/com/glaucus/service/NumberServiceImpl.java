package com.glaucus.service;
import com.glaucus.repository.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NumberServiceImpl implements  NumberService{
    @Autowired
    private NumberRepository numberRepository;

    @Transactional
    public boolean incrementNumber(int id) {
        return numberRepository.incrementNumber(id);
    }

    @Transactional
    public int getNumber(int id) {
        return numberRepository.getNumber(id).getValue();
    }

}
