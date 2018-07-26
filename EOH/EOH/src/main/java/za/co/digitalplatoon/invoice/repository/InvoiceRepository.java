package za.co.digitalplatoon.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.digitalplatoon.invoice.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
