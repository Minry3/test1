package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


@SpringBootTest
public class VoitureTest {

    private Voiture voiture;
    private Voiture voitureVide;
    private Voiture voiture2;

    @BeforeEach
    public void init()
    {
        this.voiture = new Voiture("Audi", 10000);
        this.voitureVide = new Voiture();
        this.voiture2 = new Voiture("Opel", 5000);
    }

    @Test
    public void creerVoiture(){
        assertNotNull(this.voiture);
        assertEquals("Audi", this.voiture.getMarque());
        assertEquals(10000, this.voiture.getPrix());
    }

    @Test
    public void creerVoitureVide(){
        assertNull(this.voitureVide.getMarque());
        assertEquals(0, this.voitureVide.getPrix());
        assertEquals(0, this.voitureVide.getId());
    }

    @Test
    public void ajouterId(){
        this.voiture.setId(2);
        assertEquals(2, this.voiture.getId());
    }

    @Test
    public void ajouterMarque(){
        this.voitureVide.setMarque("Mercedes");
        assertEquals("Mercedes", this.voitureVide.getMarque());
    }

    @Test
    public void ajouterPrix(){
        this.voitureVide.setPrix(20000);
        assertEquals(20000, this.voitureVide.getPrix());
    }

    @Test
    public void afficherVoiture(){
        assertEquals("Car{marque='Opel', prix=5000, id=0}", this.voiture2.toString());
    }
}
