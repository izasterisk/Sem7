package vn.edu.fpt.jpos.repositories.enums;

public enum UserRoleEnum {
    ADMIN("Admin"),
    CUSTOMER("Customer"),
    MANAGER("Manager"),
    SALE_STAFF("Sale Staff"),
    PRODUCT_STAFF("Product Staff"),
    DESIGN_STAFF("Design Staff");

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public static UserRoleEnum getRole(String role) {
        for (UserRoleEnum roleEnum : UserRoleEnum.values()) {
            if (roleEnum.toString().equals(role)) {
                return roleEnum;
            }
        }
        return UserRoleEnum.CUSTOMER;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
