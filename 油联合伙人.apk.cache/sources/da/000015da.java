package com.yltx.oil.partner.data.response;

/* loaded from: classes.dex */
public class ShopDetailsResp {
    private double currentRebatePrice;
    private double currentRebateRate;
    private GoodsShareDetailBean goodsShareDetail;
    private String nextRebatePrice;
    private double nextRebateRate;
    private double predictExtraPrice;
    private String predictRebatePrice;
    private String showStatus;
    private String userNextLevel;

    public double getNextRebateRate() {
        return this.nextRebateRate;
    }

    public void setNextRebateRate(double d) {
        this.nextRebateRate = d;
    }

    public double getCurrentRebateRate() {
        return this.currentRebateRate;
    }

    public void setCurrentRebateRate(double d) {
        this.currentRebateRate = d;
    }

    public double getCurrentRebatePrice() {
        return this.currentRebatePrice;
    }

    public void setCurrentRebatePrice(double d) {
        this.currentRebatePrice = d;
    }

    public GoodsShareDetailBean getGoodsShareDetail() {
        return this.goodsShareDetail;
    }

    public void setGoodsShareDetail(GoodsShareDetailBean goodsShareDetailBean) {
        this.goodsShareDetail = goodsShareDetailBean;
    }

    public String getNextRebatePrice() {
        return this.nextRebatePrice;
    }

    public void setNextRebatePrice(String str) {
        this.nextRebatePrice = str;
    }

    public String getPredictRebatePrice() {
        return this.predictRebatePrice;
    }

    public void setPredictRebatePrice(String str) {
        this.predictRebatePrice = str;
    }

    public String getShowStatus() {
        return this.showStatus;
    }

    public void setShowStatus(String str) {
        this.showStatus = str;
    }

    public String getUserNextLevel() {
        return this.userNextLevel;
    }

    public void setUserNextLevel(String str) {
        this.userNextLevel = str;
    }

    public double getPredictExtraPrice() {
        return this.predictExtraPrice;
    }

    public void setPredictExtraPrice(double d) {
        this.predictExtraPrice = d;
    }

    /* loaded from: classes.dex */
    public static class GoodsShareDetailBean {
        private int buyCount;
        private String calculateDate;
        private double commission;
        private String commissionRate;
        private String commissionRatio;
        private String goodsCode;
        private int goodsId;
        private String goodsImage;
        private String goodsName;
        private double marketPrice;
        private double rebateAmount;
        private double salePrice;
        private int specsId;
        private String status;
        private int tgsGoodsId;

        public int getBuyCount() {
            return this.buyCount;
        }

        public void setBuyCount(int i) {
            this.buyCount = i;
        }

        public double getMarketPrice() {
            return this.marketPrice;
        }

        public void setMarketPrice(double d) {
            this.marketPrice = d;
        }

        public String getCommissionRate() {
            return this.commissionRate;
        }

        public void setCommissionRate(String str) {
            this.commissionRate = str;
        }

        public double getSalePrice() {
            return this.salePrice;
        }

        public void setSalePrice(double d) {
            this.salePrice = d;
        }

        public double getRebateAmount() {
            return this.rebateAmount;
        }

        public void setRebateAmount(double d) {
            this.rebateAmount = d;
        }

        public int getGoodsId() {
            return this.goodsId;
        }

        public void setGoodsId(int i) {
            this.goodsId = i;
        }

        public String getCommissionRatio() {
            return this.commissionRatio;
        }

        public void setCommissionRatio(String str) {
            this.commissionRatio = str;
        }

        public String getCalculateDate() {
            return this.calculateDate;
        }

        public void setCalculateDate(String str) {
            this.calculateDate = str;
        }

        public int getTgsGoodsId() {
            return this.tgsGoodsId;
        }

        public void setTgsGoodsId(int i) {
            this.tgsGoodsId = i;
        }

        public int getSpecsId() {
            return this.specsId;
        }

        public void setSpecsId(int i) {
            this.specsId = i;
        }

        public String getGoodsImage() {
            return this.goodsImage;
        }

        public void setGoodsImage(String str) {
            this.goodsImage = str;
        }

        public double getCommission() {
            return this.commission;
        }

        public void setCommission(double d) {
            this.commission = d;
        }

        public String getGoodsCode() {
            return this.goodsCode;
        }

        public void setGoodsCode(String str) {
            this.goodsCode = str;
        }

        public String getGoodsName() {
            return this.goodsName;
        }

        public void setGoodsName(String str) {
            this.goodsName = str;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String str) {
            this.status = str;
        }
    }
}