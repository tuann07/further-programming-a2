package com.assignment2.group15.controller;

import com.assignment2.group15.error.InvoiceNotExist;
import com.assignment2.group15.model.Invoice;
import com.assignment2.group15.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> getAllInvoices(@RequestParam(required = false) Integer page) {
        return invoiceService.getAllInvoices(page);
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
    public void deleteInvoice(@PathVariable("invoiceId") Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
        return;
    }
}
