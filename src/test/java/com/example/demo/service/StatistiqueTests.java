package com.example.demo.service;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;


@SpringBootTest
public class StatistiqueTests {

    private StatistiqueImpl statistiqueImpl;
    private Voiture voiture;

    @BeforeEach
    public void init()
    {
        this.statistiqueImpl = new StatistiqueImpl();
        this.voiture = new Voiture("Audi", 20000);
    }

    @Test
    public void prixMoyenLanceUneException()
    {
        assertThrows(ArithmeticException.class, ()->statistiqueImpl.prixMoyen());
    }

    @Test
    public void ajouterUneVoiture()
    {
        statistiqueImpl.ajouter(this.voiture);
        assertNotNull(statistiqueImpl.voitures);
        assertEquals(this.voiture, statistiqueImpl.voitures.get(0));
    }

    @Test
    public void retourneLePrixMoyen()
    {
        statistiqueImpl.ajouter(this.voiture);
        Echantillon resultat = statistiqueImpl.prixMoyen();

        assertNotNull(resultat);
        assertEquals(20000, resultat.getPrixMoyen());
    }
}
