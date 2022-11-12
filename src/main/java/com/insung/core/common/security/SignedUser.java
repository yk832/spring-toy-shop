package com.insung.core.common.security;

import com.insung.core.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SignedUser implements UserDetails {

    private final UserDto userDto;

    private Collection<? extends GrantedAuthority> authorities;

    public SignedUser(UserDto userDto) {
        super();
        this.userDto = userDto;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userDto.getPwd();
    }

    @Override
    public String getUsername() {
        return this.userDto.getUser_id();
    }

    @Override
    public boolean isAccountNonExpired() { // 탈퇴여부체크 - N : true , Y : false
        return this.userDto.getLeav_yn().equals("N");
    }

    @Override
    public boolean isAccountNonLocked() { // 잠김여부체크 - N : true , Y : false
        return this.userDto.getLkd_yn().equals("N");
    }

    @Override
    public boolean isCredentialsNonExpired() { // 인증만료여부체크 - N : true , Y : false
        return this.userDto.getCert_xpry_yn().equals("N");
    }

    @Override
    public boolean isEnabled() { // 사용가능여부체크 - Y : true , N : false
        return this.userDto.getUse_posb_yn().equals("Y");
    }
}
