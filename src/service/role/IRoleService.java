package service.role;

import model.account.Role;
import model.account.RoleName;

public interface IRoleService {
    Role findByRoleName(RoleName roleName);

}
