package com.insung.core.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto {
    // 사용자 아이디
    private String user_id;
    // 사용자 비밀번호
    private String pwd;
    // 비밀번호 변경 일시
    private String pwd_chng_date;
    // 비밀번호 오류 횟수
    private String pwd_err_cnt;
    // 사용자명
    private String user_nm;
    // 이메일 주소
    private String email_addr;
    // 이메일주소 도메인
    private String email_addr_dm;
    // 사용가능여부
    private String user_pose_yn;
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
}
