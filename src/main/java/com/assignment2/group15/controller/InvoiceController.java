package com.assignment2.group15.controller;

import com.assignment2.group15.entity.Invoice;
import com.assignment2.group15.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getAllInvoices(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) ZonedDateTime start,
            @RequestParam(required = false) ZonedDateTime end
    ) {
        return invoiceService.getAllInvoices(page, start, end);
    }

    @GetMapping(path="{invoiceId}")
    public Invoice getSingleInvoice(@PathVariable("invoiceId") Long invoiceId) {
        return invoiceService.getSingleInvoice(invoiceId);
    }

    @PostMapping
    public Invoice saveInvoice(@RequestBody Invoice invoice) {
        return invoiceService.saveInvoice(invoice);
    }

    @PutMapping(path="{invoiceId}")
    public Invoice updateInvoice(@PathVariable("invoiceId") Long invoiceId, @RequestBody Invoice invoice) {
        return invoiceService.updateInvoice(invoiceId, invoice);
    }

    @DeleteMapping(path="{invoiceId}")
    public String deleteInvoice(@PathVariable("invoiceId") Long invoiceId) {
        return invoiceService.deleteInvoice(invoiceId);
    }
}
