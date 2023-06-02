package com.ywy.bootadmin.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ywy.bootadmin.model.sys.Permission;
import com.ywy.bootadmin.model.sys.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDetails实现
 *
 * @author ywy
 * @date 2020-04-08 18:03
 */
@Data
public class MyUserDetails extends User implements UserDetails {
    private List<Permission> permissionList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionList.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        // 账户是否过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 账户是否被锁定
        return getStatus() != 2;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 证书是否过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 是否激活
        return getStatus() == 1;
    }
}
