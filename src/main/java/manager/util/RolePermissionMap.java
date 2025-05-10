package manager.util;
import java.util.List;
import java.util.Map;

public class RolePermissionMap {

    private static final Map<Role, List<RolePermission>> rolePermissions = Map.of(
            Role.ADMINISTRATOR, List.of(
                    RolePermission.CREATE_ONE,
                    RolePermission.READ_ALL,
                    RolePermission.READ_ONE,
                    RolePermission.UPDATE_ONE,
                    RolePermission.DISABLE_ONE
            ),
            Role.CLIENT, List.of(
                    RolePermission.READ_MY_PROFILE,
                    RolePermission.READ_MY_PURCHASES
            )
    );

    public static List<RolePermission> getPermissions(Role role) {
        return rolePermissions.getOrDefault(role, List.of());
    }
}