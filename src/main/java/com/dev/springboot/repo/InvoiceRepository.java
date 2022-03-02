package com.dev.springboot.repo;

import com.dev.springboot.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public class InvoiceRepository extends JpaRepository<Invoice, Long> {
}
