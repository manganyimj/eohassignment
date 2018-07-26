package za.co.digitalplatoon.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.digitalplatoon.invoice.entity.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem, Long>{

}
