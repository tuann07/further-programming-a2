package com.assignment2.group15.controller;

import com.assignment2.group15.model.Invoice;
import com.assignment2.group15.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping(path="{invoiceId}")
    public Invoice getSingleInvoice(@PathVariable("invoiceId") long invoiceId) {
        return invoiceService.getSingleInvoice(invoiceId);
    }

    @PostMapping
    public Invoice saveInvoice(@RequestBody Invoice invoice) {
        return invoiceService.saveInvoice(invoice);
    }

    @PutMapping(path="{invoiceId}")
    public Invoice updateInvoice(@PathVariable("invoiceId") long invoiceId, @RequestBody Invoice invoice) {
        return invoiceService.updateInvoice(invoiceId, invoice);
    }

    @DeleteMapping(path="{invoiceId}")
    public void deleteInvoice(@PathVariable("invoiceId") long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
        return;
    }
}
