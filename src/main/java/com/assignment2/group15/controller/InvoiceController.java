package com.assignment2.group15.controller;

import com.assignment2.group15.entity.Invoice;
import com.assignment2.group15.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/invoices")
    public List<Invoice> getAllInvoices(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ) {
        return invoiceService.getAllInvoices(page, start, end);
    }

    @GetMapping(path="/customers/{customerId}/revenue")
    public Double getRevenueByCustomer(
            @RequestParam String start,
            @RequestParam String end,
            @PathVariable Long customerId
    ) {
        return invoiceService.getRevenueByCustomer(customerId, start, end);
    }

    @GetMapping(path="/drivers/{driverId}/revenue")
    public Double getRevenueByDriver(
            @RequestParam String start,
            @RequestParam String end,
            @PathVariable Long driverId
    ) {
        return invoiceService.getRevenueByDriver(driverId, start, end);
    }

    @GetMapping(path="/invoices/{invoiceId}")
    public Invoice getSingleInvoice(
            @PathVariable("invoiceId") Long invoiceId
    ) {
        return invoiceService.getSingleInvoice(invoiceId);
    }

    @PostMapping(path = "/invoices")
    public Invoice saveInvoice(
            @RequestBody Invoice invoice,
            @RequestParam("bookingId") Long bookingId
    ) {
        return invoiceService.saveInvoice(bookingId, invoice);
    }

    @PutMapping(path="/invoices/{invoiceId}")
    public Invoice updateInvoice(
            @PathVariable("invoiceId") Long invoiceId,
            @RequestBody Invoice invoice
    ) {
        return invoiceService.updateInvoice(invoiceId, invoice);
    }

    @DeleteMapping(path="/invoices/{invoiceId}")
    public String deleteInvoice(
            @PathVariable("invoiceId") Long invoiceId
    ) {
        return invoiceService.deleteInvoice(invoiceId);
    }
}
