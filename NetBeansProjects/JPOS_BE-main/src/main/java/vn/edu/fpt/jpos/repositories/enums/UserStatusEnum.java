package vn.edu.fpt.jpos.repositories.enums;

public enum UserStatusEnum {
    ACTIVED("Actived"),
    REMOVED("Removed"),
    BANNED("Banned");

    private final String status;

    UserStatusEnum(String status) {
        this.status = status;
    }

    public static UserStatusEnum getStatus(String status) {
        for (UserStatusEnum statusEnum : UserStatusEnum.values()) {
            if (statusEnum.toString().equals(status)) {
                return statusEnum;
            }
        }
        return UserStatusEnum.ACTIVED;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
