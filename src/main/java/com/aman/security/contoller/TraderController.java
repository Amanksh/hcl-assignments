package com.aman.security.contoller;

import com.aman.security.dto.TraderRegistrationDto;
import com.aman.security.dto.TraderUpdateDto;
import com.aman.security.dto.TraderAddMoneyDto;
import com.aman.security.entity.Trader;
import com.aman.security.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trading/traders")
public class TraderController {
    
    @Autowired
    private TraderService traderService;
    
    @PostMapping("/register")
    public ResponseEntity<Trader> registerTrader(@RequestBody TraderRegistrationDto dto) {
        try {
            Trader trader = traderService.registerTrader(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(trader);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("already exists")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            throw e;
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Trader>> getAllTraders() {
        List<Trader> traders = traderService.getAllTraders();
        return ResponseEntity.ok(traders);
    }
    
    @GetMapping
    public ResponseEntity<Trader> getTraderByEmail(@RequestParam String email) {
        Optional<Trader> trader = traderService.getTraderByEmail(email);
        if (trader.isPresent()) {
            return ResponseEntity.ok(trader.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping
    public ResponseEntity<Trader> updateTraderName(@RequestBody TraderUpdateDto dto) {
        try {
            Trader trader = traderService.updateTraderName(dto);
            return ResponseEntity.ok(trader);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            throw e;
        }
    }
    
    @PutMapping("/add")
    public ResponseEntity<Trader> addMoneyToTrader(@RequestBody TraderAddMoneyDto dto) {
        try {
            Trader trader = traderService.addMoneyToTrader(dto);
            return ResponseEntity.ok(trader);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            throw e;
        }
    }
}
