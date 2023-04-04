package com.rajanish.splitwise.services;

import com.rajanish.splitwise.models.Currrency;
import com.rajanish.splitwise.repository.CurrrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrrencyService {
    private CurrrencyRepository currrencyRepository;
    @Autowired
    public CurrrencyService(CurrrencyRepository currrencyRepository){
        this.currrencyRepository=currrencyRepository;
    }
    public Currrency save(Currrency currrency){
        return currrencyRepository.save(currrency);
    }
    public Currrency findCurrency(String baseCurrency){
        return currrencyRepository.findByBaseCurrencyEquals(baseCurrency);
    }
}
