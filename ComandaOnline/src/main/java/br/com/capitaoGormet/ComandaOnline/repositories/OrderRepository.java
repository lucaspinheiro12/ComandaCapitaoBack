package br.com.capitaoGormet.ComandaOnline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capitaoGormet.ComandaOnline.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
