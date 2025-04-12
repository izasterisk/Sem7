package vn.edu.fpt.jpos.repositories.enums;

public enum ProductStatusEnum {
    ACTIVED("Actived"),
    REMOVED("Removed");

    private final String status;

    ProductStatusEnum(String status) {
        this.status = status;
    }

    public static ProductStatusEnum getStatus(String status) {
        for (ProductStatusEnum statusEnum : ProductStatusEnum.values()) {
            if (statusEnum.toString().equals(status)) {
                return statusEnum;
            }
        }
        return ProductStatusEnum.ACTIVED;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
