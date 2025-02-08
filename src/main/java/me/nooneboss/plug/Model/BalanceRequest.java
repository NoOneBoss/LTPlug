package me.nooneboss.plug.Model;

import lombok.Data;

@Data
public class BalanceRequest {
    private String rqUID;
    private String clientId;
    private String account;
    private String openDate;
    private String closeDate;
}
