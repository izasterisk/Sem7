package vn.edu.fpt.jpos.repositories.enums;

public enum CustomerRequestStatusEnum {
    NEW("New"),
    CONFIRMED("Confirmed"),
    ACCEPTED("Accepted"),
    REJECTED("Rejectted"),
    DESIGNING("Designing"),
    COMPLETE_DESIGNING("Complete Designing"),
    PRODUCING("Producing"),
    COMPLETE_PRODUCING("Complete Producing"),
    COMPLETED("Completed");

    private final String status;

    CustomerRequestStatusEnum(String status) {
        this.status = status;
    }

    public static CustomerRequestStatusEnum getStatus(String role) {
        for (CustomerRequestStatusEnum statusEnum : CustomerRequestStatusEnum.values()) {
            if (statusEnum.toString().equals(role)) {
                return statusEnum;
            }
        }
        return CustomerRequestStatusEnum.NEW;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
