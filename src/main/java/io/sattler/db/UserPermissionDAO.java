package io.sattler.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserPermissionMapper.class)
public interface UserPermissionDAO {

    @SqlQuery("SELECT u.id as user_id, u.user_uuid as user_uuid, c.id as company_id, c.company_uuid as company_uuid, ucp.permission as user_permission FROM users u JOIN companies c ON c.company_uuid = :company_id RIGHT JOIN rel_user2company ucp ON ucp.user_id = u.id AND ucp.company_id = c.id WHERE u.user_uuid = :user_uuid")
    UserPermission getPermissionFromUsersCompany(@Bind("company_id") String companyId, @Bind("user_uuid") String userId);

}
