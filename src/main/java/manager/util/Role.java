package manager.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ADMINISTRATOR(Arrays.asList(
            RolePermission.CREATE_ONE,
            RolePermission.READ_ALL,
            RolePermission.READ_ONE,
            RolePermission.UPDATE_ONE,
            RolePermission.DISABLE_ONE
    )),
    CLIENT(List.of(RolePermission.READ_MY_PROFILE,RolePermission.READ_MY_PURCHASES));
    private List<RolePermission> permissions;

    Role(List<RolePermission> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermission> permissions) {
        this.permissions = permissions;
    }
}
