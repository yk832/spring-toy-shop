package com.insung.core.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    // 사용자 아이디
    private String user_id;
    // 사용자 비밀번호
    private String pwd;
    // 비밀번호 변경 일시
    private String pwd_chng_dttm;
    // 비밀번호 오류 횟수
    private String pwd_err_cnt;
    // 사용자명
    private String user_nm;
    // 이메일 주소
    private String email_addr;
    // 이메일주소 도메인
    private String email_addr_dm;
    // 사용가능여부
    private String use_posb_yn;
    // 인증만료여부
    private String cert_xpry_yn;
    // 잠김여부
    private String lkd_yn;
    // 탈퇴여부
    private String leav_yn;
    // 등록자 ID
    private String reg_id;
    // 등록 일자
    private String reg_date;
    // 변경자 ID
    private String chng_id;
    // 변경 일자
    private String chng_date;

    private String user_auth_cd;

    public UserDto() {
    }

    public UserDto(UserSaveForm userSaveForm) {
        this.user_id = userSaveForm.getUser_id();
        this.pwd = userSaveForm.getPwd();
        this.user_nm = userSaveForm.getUser_nm();
        this.email_addr = userSaveForm.getEmail_addr();
        this.email_addr_dm = userSaveForm.getEmail_addr_dm();
        this.user_auth_cd = userSaveForm.getUser_auth_cd();
    }
}
