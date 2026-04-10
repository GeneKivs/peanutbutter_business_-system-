package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;

import com.peanutbutter.peanutbutter.base.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Journal_line")
public class JournalLine extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "journal_entry_id")
    private JournalEntry journalEntry;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private BigDecimal debit = BigDecimal.ZERO;

    private BigDecimal credit = BigDecimal.ZERO;

    public Long getID(){return id;}

    public JournalEntry getJournalEntry(){return journalEntry;}
    public void setJournalEntry(JournalEntry journalEntry){this.journalEntry = journalEntry;}

    public Account getAccount(){return account;}
    public void setAccount(Account account){this.account = account;}

    public BigDecimal getDebit(){return debit;}
    public void setDebit(BigDecimal debit){this.debit = debit;}

    public BigDecimal getCredit(){return credit;}
    public void setCredit(BigDecimal credit){this.credit = credit;}

}
