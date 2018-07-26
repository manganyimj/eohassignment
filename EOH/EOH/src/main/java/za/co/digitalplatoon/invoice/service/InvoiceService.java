package za.co.digitalplatoon.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.digitalplatoon.invoice.entity.Invoice;
import za.co.digitalplatoon.invoice.repository.InvoiceRepository;

@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public List<Invoice> getAllInvoices() {
		
		return invoiceRepository.findAll();
	}
	
	public Invoice getInvoiceById(Long id) {
		
		return invoiceRepository.getOne(id);
	}
	
	public void addInvoice (Invoice invoice) {
		
		invoiceRepository.save(invoice);
	}
	
	public boolean invoiceExists(Long id) {
		
		List<Invoice> ainvoiceList = invoiceRepository.findAll();
		
		if (ainvoiceList.size() > 0) {
			return false;
		}
		
		return invoiceRepository.existsById(id);
	}
}
