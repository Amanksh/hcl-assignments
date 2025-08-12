package com.aman.security.service;

import com.aman.security.dto.TraderRegistrationDto;
import com.aman.security.dto.TraderUpdateDto;
import com.aman.security.dto.TraderAddMoneyDto;
import com.aman.security.entity.Trader;
import com.aman.security.repo.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TraderService {
    
    @Autowired
    private TraderRepository traderRepository;
    
    public Trader registerTrader(TraderRegistrationDto dto) {
        if (traderRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Trader with email " + dto.getEmail() + " already exists");
        }
        
        Trader trader = new Trader();
        trader.setName(dto.getName());
        trader.setEmail(dto.getEmail());
        trader.setBalance(dto.getBalance());
        
        return traderRepository.save(trader);
    }
    
    public List<Trader> getAllTraders() {
        return traderRepository.findAllByOrderByIdAsc();
    }
    
    public Optional<Trader> getTraderByEmail(String email) {
        return traderRepository.findByEmail(email);
    }
    
    public Trader updateTraderName(TraderUpdateDto dto) {
        Optional<Trader> optionalTrader = traderRepository.findByEmail(dto.getEmail());
        if (optionalTrader.isEmpty()) {
            throw new RuntimeException("Trader with email " + dto.getEmail() + " not found");
        }
        
        Trader trader = optionalTrader.get();
        trader.setName(dto.getName());
        trader.setUpdatedAt(LocalDateTime.now());
        return traderRepository.save(trader);
    }
    
    public Trader addMoneyToTrader(TraderAddMoneyDto dto) {
        Optional<Trader> optionalTrader = traderRepository.findByEmail(dto.getEmail());
        if (optionalTrader.isEmpty()) {
            throw new RuntimeException("Trader with email " + dto.getEmail() + " not found");
        }
        
        Trader trader = optionalTrader.get();
        trader.setBalance(trader.getBalance().add(dto.getAmount()));
        trader.setUpdatedAt(LocalDateTime.now());
        return traderRepository.save(trader);
    }
}
