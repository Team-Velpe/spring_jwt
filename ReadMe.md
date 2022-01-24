&nbsp;

<p align="right"><img src="https://img.shields.io/badge/npm-v8.3.0-red">&nbsp;<img src="https://img.shields.io/badge/SpringBoot-v2.6.2-green">&nbsp;<img src="https://img.shields.io/badge/JJWT-v0.11.2-yellow">&nbsp;<img src="https://img.shields.io/badge/Java-11+-orange">&nbsp;</p>

# Spring JWT Authorization Simple QNA Application

## Summary

> Spring Security JWT 인증 기반의 간단한 QNA CRUD 어플리케이션을 리액트와 함께 구현한 프로젝트 입니다.

## Description

<p align="center"><img src="https://img.shields.io/badge/java-orange.svg?&style=for-the-badge&logo=java&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB">&nbsp;<img src="https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/SASS-hotpink.svg?style=for-the-badge&logo=SASS&logoColor=white">&nbsp;</p>

<p align="center"><img src="https://i.postimg.cc/9X9nB5kK/Honeycam-2022-01-24-22-12-21.gif"></p>

회원가입을 진행하고, 로그인하여 JWT 토큰을 발급받은 다음, 질문과 그에 대한 답변을 할 수 있는 간단한 어플리케이션을 구현했습니다.
질문을 게시글로서 등록하고 그에 대한 답변을 댓글형식으로 달 수 있는 간단한 형식의 프로젝트 입니다.

가입한 회원이 로그인을 할 경우 Access Token과 Refresh Token을 Pair로 발급해서 응답으로 보내줍니다. Access Token이 만료되면, 회원이 로그아웃을 하지 않는 이상 Axios Interceptor를 통해서 갱신받고 로그인을 유지합니다.

REST API를 구현하기 위해서 URI설계를 다음과 같이 진행했습니다.

### Auth 관련 API

| HTTP | Path                | Description |
| ---- | ------------------- | ----------- |
| POST | /api/v1/auth/signin | 회원가입    |
| POST | /api/v1/auth/signup | 로그인      |

### Member 관련 API

| HTTP   | Path            | Description |
| ------ | --------------- | ----------- |
| GET    | /api/v1/members | 목록        |
| PUT    | /api/v1/members | 수정        |
| DELETE | /api/v1/members | 탈퇴        |

### Question 관련 API

| HTTP   | Path               | Description |
| ------ | ------------------ | ----------- |
| POST   | /api/v1/qna/q      | 작성        |
| PUT    | /api/v1/qna/q/{id} | 수정        |
| DELETE | /api/v1/qna/q/{id} | 삭제        |
| GET    | /api/v1/qna/q/{id} | 상세 보기   |
| GET    | /api/v1/qna/q      | 목록        |

### Answer 관련 API

| HTTP   | Path               | Description |
| ------ | ------------------ | ----------- |
| POST   | /api/v1/qna/a      | 작성        |
| PUT    | /api/v1/qna/a/{id} | 수정        |
| DELETE | /api/v1/qna/a/{id} | 삭제        |

## Dependencies

본 프로젝트는 다음의 의존성이 필요합니다.

### Front-end

- react-router-dom
- Sass
- Recoil
- Axios

### Back-end

- Spring Boot Validation
- Spring Data JPA
- Spring Security
- Mysql Connecter Java
- JJWT API

### Installation

- npm

```
npm install
npm start
```

## Reference

- [https://docs.spring.io/spring-security/reference/index.html](https://docs.spring.io/spring-security/reference/index.html)
- [https://github.com/jwtk/jjwt](https://github.com/jwtk/jjwt)
