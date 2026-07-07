package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.util.List;

public class BalanceSheetDto {
    private BigDecimal totalAssets = BigDecimal.ZERO;
    private BigDecimal totalLiabilities = BigDecimal.ZERO;
    private BigDecimal totalEquity = BigDecimal.ZERO;
    private List<AccountBalanceDto> assets;
    private List<AccountBalanceDto> liabilities;
    private List<AccountBalanceDto> equity;

    public BalanceSheetDto(){}

    public BigDecimal getTotalAssets() { return totalAssets; }
    public void setTotalAssets(BigDecimal totalAssets) { this.totalAssets = totalAssets; }
    public BigDecimal getTotalLiabilities() { return totalLiabilities; }
    public void setTotalLiabilities(BigDecimal totalLiabilities) { this.totalLiabilities = totalLiabilities; }
    public BigDecimal getTotalEquity() { return totalEquity; }
    public void setTotalEquity(BigDecimal totalEquity) { this.totalEquity = totalEquity; }
    public List<AccountBalanceDto> getAssets() { return assets; }
    public void setAssets(List<AccountBalanceDto> assets) { this.assets = assets; }
    public List<AccountBalanceDto> getLiabilities() { return liabilities; }
    public void setLiabilities(List<AccountBalanceDto> liabilities) { this.liabilities = liabilities; }
    public List<AccountBalanceDto> getEquity() { return equity; }
    public void setEquity(List<AccountBalanceDto> equity) { this.equity = equity; }
}
