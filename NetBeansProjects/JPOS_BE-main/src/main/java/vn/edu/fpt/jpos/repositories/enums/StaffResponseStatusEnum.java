package vn.edu.fpt.jpos.repositories.enums;

public enum StaffResponseStatusEnum {
    NEW("New"),
    ACCEPTED("Accepted"),
    REJECTED("Rejectted"),
    DESIGNING("Designing"),
    COMPLETE_DESIGNING("Complete Designing"),
    PRODUCING("Producing"),
    COMPLETE_PRODUCING("Complete Producing"),
    COMPLETED("Completed");

    private final String status;

    StaffResponseStatusEnum(String status) {
        this.status = status;
    }

    public static StaffResponseStatusEnum getStatus(String role) {
        for (StaffResponseStatusEnum statusEnum : StaffResponseStatusEnum.values()) {
            if (statusEnum.toString().equals(role)) {
                return statusEnum;
            }
        }
        return StaffResponseStatusEnum.NEW;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
