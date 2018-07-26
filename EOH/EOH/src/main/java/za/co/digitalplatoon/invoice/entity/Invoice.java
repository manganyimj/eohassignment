package za.co.digitalplatoon.invoice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="invoice", schema="eoh")
public class Invoice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	private String client;
	private Long vatRate;
	private Date invoiceDate;
	@ManyToOne
	private LineItem lineItem;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	
	public Long getVatRate() {
		return vatRate;
	}
	public void setVatRate(Long vatRate) {
		this.vatRate = vatRate;
	}
	
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public LineItem getLineItem() {
		return lineItem;
	}
	public void setLineItem(LineItem lineItem) {
		this.lineItem = lineItem;
	}
	
	@Transient
	public BigDecimal getSubtotal() {
		
		return BigDecimal.valueOf(lineItem.getLineItemTotal().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	@Transient
	public BigDecimal getVat() {
		
		double vat = lineItem.getLineItemTotal().doubleValue() * (vatRate.doubleValue()/100);
		
		return BigDecimal.valueOf(vat).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	@Transient
	public BigDecimal getTotal() {
		
		double sum = Math.addExact(getSubtotal().intValue(), getVat().intValue());
		
		return BigDecimal.valueOf(sum).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
