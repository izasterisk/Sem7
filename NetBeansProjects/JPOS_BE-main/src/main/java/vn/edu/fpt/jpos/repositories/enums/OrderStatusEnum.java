package vn.edu.fpt.jpos.repositories.enums;

public enum OrderStatusEnum {
    NEW("New"),
    PROCESSING("Processing"),
    COMPLETED("Completed");

    private final String status;

    OrderStatusEnum(String status) {
        this.status = status;
    }

    public static OrderStatusEnum getStatus(String status) {
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            if (statusEnum.toString().equals(status)) {
                return statusEnum;
            }
        }
        return OrderStatusEnum.NEW;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
