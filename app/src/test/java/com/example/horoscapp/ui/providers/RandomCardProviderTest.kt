package com.example.horoscapp.ui.providers

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

 class RandomCardProviderTest{
  @Test
  fun `getRandomCard should return a random card`(){

   //Given (le doy lo que necesito)
   val randomCard = RandomCardProvider()

   //When (cuando ocurra la situación que estoy testeando)
   //Llama a la función get Lucky
   val card = randomCard.getLucky()

   //Then
   //asegurate de que lo que esté en card no sea null
   assertNotNull(card)
  }

 }