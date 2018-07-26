package za.co.digitalplatoon.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import za.co.digitalplatoon.invoice.entity.Invoice;
import za.co.digitalplatoon.invoice.service.InvoiceService;

@RestController
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping(value = "/invoices", method = RequestMethod.GET)
	public ResponseEntity<List<Invoice>> viewAllInvoices() {
		
		List<Invoice> invoices = invoiceService.getAllInvoices();
		
		if(invoices.isEmpty()) {
			
			return new ResponseEntity<List<Invoice>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}
	
	@RequestMapping(value="/invoice/{id}", method = RequestMethod.GET)
	public ResponseEntity<Invoice> viewInvoice(@PathVariable("id") Long id) {
		
		Invoice invoice = invoiceService.getInvoiceById(id);
		
		if(invoice == null) {
			
			return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}
	
	@RequestMapping(value="/invoices", method = RequestMethod.POST)
	public ResponseEntity<Void> addInvoice(@RequestBody Invoice invoice, UriComponentsBuilder ucBuilder) {
		
		boolean flag = invoiceService.invoiceExists(invoice.getId());
		
		if(flag == true) {
			
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		invoiceService.addInvoice(invoice);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/invoice/{id}").buildAndExpand(invoice.getId()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
