package io.sattler.db;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPermissionMapper implements ResultSetMapper<UserPermission> {

    public UserPermission map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new UserPermission(
                r.getLong("user_id"),
                r.getString("user_uuid"),
                r.getLong("company_id"),
                r.getString("company_uuid"),
                r.getInt("user_permission")
        );
    }
}
