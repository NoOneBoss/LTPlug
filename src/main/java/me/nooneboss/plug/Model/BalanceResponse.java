package me.nooneboss.plug.Model;

import lombok.Data;

@Data
public class BalanceResponse {
    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private String balance;
    private String maxLimit;
}
