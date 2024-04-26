package com.yltx.oil.partner.modules.profit.domain;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.mvp.domain.UseCase;
import javax.inject.Inject;
import rx.Observable;

/* loaded from: classes.dex */
public class TxApplyUseCase extends UseCase<HttpResult<String>> {
    String accountPwd;
    private Repository repository;
    String txMoney;

    public String getTxMoney() {
        return this.txMoney;
    }

    public void setTxMoney(String str) {
        this.txMoney = str;
    }

    public String getAccountPwd() {
        return this.accountPwd;
    }

    public void setAccountPwd(String str) {
        this.accountPwd = str;
    }

    @Inject
    public TxApplyUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override // com.yltx.oil.partner.mvp.domain.UseCase
    protected Observable<HttpResult<String>> buildObservable() {
        return this.repository.userAccountTxApply(this.txMoney, this.accountPwd);
    }
}