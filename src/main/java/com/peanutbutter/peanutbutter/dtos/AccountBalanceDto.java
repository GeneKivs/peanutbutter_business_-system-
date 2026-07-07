package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;

public class AccountBalanceDto {
    private Long accountId;
    private String accountName;
    private String accountType;
    private BigDecimal debit = BigDecimal.ZERO;
    private BigDecimal credit = BigDecimal.ZERO;

    public AccountBalanceDto(){}

    public AccountBalanceDto(Long accountId, String accountName, String accountType, BigDecimal debit, BigDecimal credit){
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.debit = debit == null ? BigDecimal.ZERO : debit;
        this.credit = credit == null ? BigDecimal.ZERO : credit;
    }

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public BigDecimal getDebit() { return debit; }
    public void setDebit(BigDecimal debit) { this.debit = debit; }
    public BigDecimal getCredit() { return credit; }
    public void setCredit(BigDecimal credit) { this.credit = credit; }
}
