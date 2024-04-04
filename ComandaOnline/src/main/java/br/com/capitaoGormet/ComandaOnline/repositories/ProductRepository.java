package br.com.capitaoGormet.ComandaOnline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capitaoGormet.ComandaOnline.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
