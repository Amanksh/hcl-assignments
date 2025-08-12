package com.aman.security;

import com.aman.security.dto.TraderRegistrationDto;
import com.aman.security.dto.TraderUpdateDto;
import com.aman.security.dto.TraderAddMoneyDto;
import com.aman.security.entity.Trader;
import com.aman.security.repo.TraderRepository;
import com.aman.security.service.TraderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TradingPlatformTests {

    @Autowired
    private TraderService traderService;

    @Autowired
    private TraderRepository traderRepository;

    @Test
    public void testTraderRegistration() {
        // Test data
        TraderRegistrationDto dto = new TraderRegistrationDto();
        dto.setName("Elizabeth Small");
        dto.setEmail("susanchandler.wchurch@buck.com");
        dto.setBalance(new BigDecimal("62.0"));

        // Register trader
        Trader trader = traderService.registerTrader(dto);

        // Assertions
        assertNotNull(trader);
        assertNotNull(trader.getId());
        assertEquals("Elizabeth Small", trader.getName());
        assertEquals("susanchandler.wchurch@buck.com", trader.getEmail());
        assertEquals(0, trader.getBalance().compareTo(new BigDecimal("62.0")));
        assertNotNull(trader.getCreatedAt());
        assertNull(trader.getUpdatedAt());
    }

    @Test
    public void testDuplicateEmailRegistration() {
        // Register first trader
        TraderRegistrationDto dto1 = new TraderRegistrationDto();
        dto1.setName("Elizabeth Small");
        dto1.setEmail("susanchandler.wchurch@buck.com");
        dto1.setBalance(new BigDecimal("62.0"));
        traderService.registerTrader(dto1);

        // Try to register second trader with same email
        TraderRegistrationDto dto2 = new TraderRegistrationDto();
        dto2.setName("Susan Adams");
        dto2.setEmail("susanchandler.wchurch@buck.com");
        dto2.setBalance(new BigDecimal("67.0"));

        // Should throw exception
        assertThrows(RuntimeException.class, () -> {
            traderService.registerTrader(dto2);
        });
    }

    @Test
    public void testGetAllTraders() {
        // Register multiple traders
        TraderRegistrationDto dto1 = new TraderRegistrationDto();
        dto1.setName("Elizabeth Small");
        dto1.setEmail("elizabeth@example.com");
        dto1.setBalance(new BigDecimal("62.0"));
        traderService.registerTrader(dto1);

        TraderRegistrationDto dto2 = new TraderRegistrationDto();
        dto2.setName("Susan Adams");
        dto2.setEmail("susan@example.com");
        dto2.setBalance(new BigDecimal("67.0"));
        traderService.registerTrader(dto2);

        // Get all traders
        List<Trader> traders = traderService.getAllTraders();

        // Assertions
        assertEquals(2, traders.size());
        assertTrue(traders.get(0).getId() < traders.get(1).getId()); // Sorted by ID
    }

    @Test
    public void testGetTraderByEmail() {
        // Register trader
        TraderRegistrationDto dto = new TraderRegistrationDto();
        dto.setName("Elizabeth Small");
        dto.setEmail("elizabeth@example.com");
        dto.setBalance(new BigDecimal("62.0"));
        Trader registeredTrader = traderService.registerTrader(dto);

        // Get trader by email
        Optional<Trader> foundTrader = traderService.getTraderByEmail("elizabeth@example.com");

        // Assertions
        assertTrue(foundTrader.isPresent());
        assertEquals(registeredTrader.getId(), foundTrader.get().getId());
        assertEquals("Elizabeth Small", foundTrader.get().getName());
    }

    @Test
    public void testGetTraderByEmailNotFound() {
        // Try to get non-existent trader
        Optional<Trader> foundTrader = traderService.getTraderByEmail("nonexistent@example.com");

        // Assertions
        assertFalse(foundTrader.isPresent());
    }

    @Test
    public void testUpdateTraderName() {
        // Register trader
        TraderRegistrationDto dto = new TraderRegistrationDto();
        dto.setName("Elizabeth Small");
        dto.setEmail("elizabeth@example.com");
        dto.setBalance(new BigDecimal("62.0"));
        traderService.registerTrader(dto);

        // Update name
        TraderUpdateDto updateDto = new TraderUpdateDto();
        updateDto.setName("Susan Wood");
        updateDto.setEmail("elizabeth@example.com");

        Trader updatedTrader = traderService.updateTraderName(updateDto);

        // Assertions
        assertEquals("Susan Wood", updatedTrader.getName());
        assertNotNull(updatedTrader.getUpdatedAt());
    }

    @Test
    public void testUpdateTraderNameNotFound() {
        // Try to update non-existent trader
        TraderUpdateDto updateDto = new TraderUpdateDto();
        updateDto.setName("Susan Wood");
        updateDto.setEmail("nonexistent@example.com");

        // Should throw exception
        assertThrows(RuntimeException.class, () -> {
            traderService.updateTraderName(updateDto);
        });
    }

    @Test
    public void testAddMoneyToTrader() {
        // Register trader
        TraderRegistrationDto dto = new TraderRegistrationDto();
        dto.setName("Elizabeth Small");
        dto.setEmail("elizabeth@example.com");
        dto.setBalance(new BigDecimal("62.0"));
        traderService.registerTrader(dto);

        // Add money
        TraderAddMoneyDto addMoneyDto = new TraderAddMoneyDto();
        addMoneyDto.setEmail("elizabeth@example.com");
        addMoneyDto.setAmount(new BigDecimal("73.0"));

        Trader updatedTrader = traderService.addMoneyToTrader(addMoneyDto);

        // Assertions
        assertEquals(0, updatedTrader.getBalance().compareTo(new BigDecimal("135.0")));
        assertNotNull(updatedTrader.getUpdatedAt());
    }

    @Test
    public void testAddMoneyToTraderNotFound() {
        // Try to add money to non-existent trader
        TraderAddMoneyDto addMoneyDto = new TraderAddMoneyDto();
        addMoneyDto.setEmail("nonexistent@example.com");
        addMoneyDto.setAmount(new BigDecimal("73.0"));

        // Should throw exception
        assertThrows(RuntimeException.class, () -> {
            traderService.addMoneyToTrader(addMoneyDto);
        });
    }
}
