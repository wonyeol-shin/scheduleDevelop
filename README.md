# schedule-delvelop (일정 관리 과제)
- 도전과제 완료
---
## ERD

**ERD링크**<br>
https://www.erdcloud.com/p/cZyBMsNcMkK7JuvGp
<br><br>
**SQL쿼리** 

```SQL
CREATE TABLE `schedule` (
	`id`	bigint	NOT NULL,
	`user_id`	bigint	NULL,
	`schedule_name`	varchar(50)	NOT NULL,
	`content`	varchar(512)	NOT NULL,
	`created_date`	datetime	NOT NULL,
	`modified_date`	datetime	NOT NULL
);

CREATE TABLE `user` (
	`id`	bigint	NOT NULL,
	`user_name`	varchar(20)	NOT NULL,
	`email`	varchar(50)	NOT NULL,
	`password`	varchar(50)	NOT NULL,
	`createdDate`	datetime	NOT NULL,
	`modifiedDate`	datetime	NOT NULL
);

CREATE TABLE `comment` (
	`id`	bigint	NOT NULL,
	`schedule_id`	bigint	NULL,
	`user_id`	bigint	NULL,
	`comment`	varchar(50)	NOT NULL,
	`created_date`	datetime	NOT NULL,
	`modifiedDate`	datetime	NOT NULL
);

ALTER TABLE `schedulr` ADD CONSTRAINT `PK_SCHEDULR` PRIMARY KEY (
	`id`
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`id`
);

ALTER TABLE `comment` ADD CONSTRAINT `PK_COMMENT` PRIMARY KEY (
	`id`
);

```

---



## API 명세서 : 일정 관리 (Schedule-delvelop)
Version: 1.0.0<br>
Base URL : http://localhost:8080


### 📌 사용자 생성(회원가입) 

**Request - 요청**
* Method : `POST`
* URL : `/signup`

* Body :
```JSON
{
  "userName" : "타대오",
  "email" : "Thaddaeus@glory.com",
  "password" : "12345"
}
```

**Response - 응답**
* Status Code : `200`
* Comment : 회원가입 성공
* Response Body : `application/json`
* Body : 
```JSON
{
  "id" : "1"
  "userName" : "타대오",
  "email" : "Thaddaeus@glory.com",
  "createdDate" : "2026-06-02",
  "modifiedDate" : "2026-06-02"
}     
```

### 📌 사용자 조회

**Request - 요청**
* Method : `GET`
* URL : `/users`

**Response - 응답**
* Status Code : `200`
* Comment : 사용자 조회 성공, 사용자가 없을 경우 빈 배열 전달 [] 
* Response Body : `application/json`
* Body : 
```JSON
[
  {
    "userName" : "타대오",
    "email" : "Thaddaeus@glory.com",
  },
  {
    "userName" : "배드로",
    "email" : "peter@glory.com"
  }
]
```

### 📌 사용자 조회(단건)

**Request - 요청**
* Method : `GET`
* URL : `/users/{userId}`

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| userId | long | yes | 원하는 사용자 선택 |

**Response - 응답**
* Status Code : `200`
* Comment : 조회 성공
* Response Body : `application/json`
* Body :
```JSON
{
  "id" : "1"
  "userName" : "타대오",
  "email" : "Thaddaeus@glory.com",
  "createdAt" : "2026-06-02",
  "modifiedDate" : "2026-06-02"
}    
```

* Status Code : `404`
* Comment : 없는 사용자 검색

### 📌 사용자 수정

**Request - 요청**
* Method : `PATCH`
* URL : `/users`
* Request Header `Cookie: JSESSIONID=세션ID`
* comment : 사용자 정보 수정 후 로그인이 필요합니다.

* Body :
```JSON
{
  "userName" : "타대오",
  "email" : "Thaddaeus@glory.com",
  "password" : "12345"
}
```

**Response - 응답**
* Status Code : `200`
* Comment : 수정 성공

* Status Code : `401`
* Comment : 로그인 하지 않음, 수정 권한이 없음

* Status Code : `404`
* Comment : 존재하지 않는 사용자 수정시도


### 📌 사용자 삭제

**Request - 요청**
* Method : `DELETE`
* URL : `/users`
* Request Header `Cookie: JSESSIONID=세션ID`

**Response - 응답**
* Status Code : `204`
* Comment : 삭제 성공

* Status Code : `401`
* Comment : 로그인 하지 않음, 삭제 권한이 없음

* Status Code : `404`
* Comment : 존재하지 않는 사용자 삭제시도
* 
### 📌 사용자 로그인

**Request - 요청**
* Method : `POST`
* URL : `/login`

* Body :
```JSON
{
  "email" : "Thaddaeus@glory.com",
  "password" : "12345"
}
```

**Response - 응답**
* Status Code : `200`
* Comment : 로그인 성공
* Response Header : `Set-Cookie: JSESSIONID=세션ID; Path=/; HttpOnly`

### 📌 사용자 로그아웃

**Request - 요청**
* Method : `POST`
* URL : `/logout`
* Request Header `Cookie: JSESSIONID=세션ID`

**Response - 응답**
* Status Code : `204`
* Comment : 로그아웃 성공

* Status Code : `400`
* Comment : 로그인을 하지 않고 로그아웃 요청

---

### 📌 일정 조회(단건)

**Request - 요청**
* Method : `GET`
* URL : `/schedules/{scheduleId}`

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| scheduleId | long | yes | 원하는 일정 조회 | 

**Response - 응답**
* Status Code : `200`
* Comment : 조회 성공
* Response Body : `application/json`
* Body : 
```JSON
{
  "id" : 1,
  "userName" : "타대오",
  "scheduleName" : "타대오가 해야될 것1",
  "content" : "배드로 숑디와 탕후루 먹기",
  "createdDate" : "2026-06-02",
  "modifiedDate" : "2026-06-02"
}
```

* Status Code : `404`
* Comment : 없는 일정 검색

### 📌 일정 조회(다건)

**Request - 요청**
* Method : `GET`
* URL : `/schedules`
* Query Param

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| page | long | no | 페이지번호 | 
|---|---|---|---|
| size | long | no | 데이터 개수 | 

**Response - 응답**
* Status Code : `200`
* Comment : 조회 성공, 일정이 하나도 없을 경우 [] 빈 배열 전달
* Response Body : `application/json`
* Body : 
```JSON
{
    "content": [
        {
            "scheduleName": "연구해야될것",
            "scheduleContent": "유전자 검사",
            "commentCount": 0,
            "createdDate": "2026-07-08T14:22:18.400131",
            "modifiedDate": "2026-07-08T14:22:18.400131"
        },
        {
            "scheduleName": "연구해야될것",
            "scheduleContent": "유전자 검사",
            "commentCount": 0,
            "createdDate": "2026-07-08T14:22:18.039413",
            "modifiedDate": "2026-07-08T14:22:18.039413"
        },
        {
            "scheduleName": "연구해야될것",
            "scheduleContent": "유전자 검사",
            "commentCount": 0,
            "createdDate": "2026-07-08T14:22:17.698",
            "modifiedDate": "2026-07-08T14:22:17.698"
        },
        {
            "scheduleName": "연구해야될것",
            "scheduleContent": "유전자 검사",
            "commentCount": 0,
            "createdDate": "2026-07-08T14:22:17.32345",
            "modifiedDate": "2026-07-08T14:22:17.32345"
        },
        {
            "scheduleName": "연구해야될것",
            "scheduleContent": "유전자 검사",
            "commentCount": 0,
            "createdDate": "2026-07-08T14:22:16.956619",
            "modifiedDate": "2026-07-08T14:22:16.956619"
        }
    ],
    "empty": false,
    "first": false,
    "last": false,
    "number": 1,
    "numberOfElements": 5,
    "pageable": {
        "offset": 5,
        "pageNumber": 1,
        "pageSize": 5,
        "paged": true,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "unpaged": false
    },
    "size": 5,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "totalElements": 22,
    "totalPages": 5
}
```

### 📌 일정 등록(생성)

**Request - 요청**
* Method : `POST`
* URL : `/schedules`
* Request Header `Cookie: JSESSIONID=세션ID`

* Body : 
```JSON
{
  "scheduleName" : "타대오가 해야될 것1",
  "contents" : "배드로 숑디와 탕후루 먹기"
}
```

**Response - 응답**
* Status Code : `201`
* Comment : 일정 생성 성공
* Response Body : `application/json`
* Body : 
```JSON
{
  "id" : 1,
  "userName" : "타대오",
  "scheduleName" : "타대오가 해야될 것1",
  "content" : "배드로 숑디와 탕후루 먹기",
  "createdDate" : "2026-06-02",
  "modifiedDate" : "2026-06-02"
}
```

* Status Code : `401`
* Comment : 로그인 정보 없음

* Status Code : `404`
* Comment : 로그인 정보가 유효하지 않음(ID 없음)

### 📌 일정 수정

**Request - 요청**
* Method : `PATCH`
* URL : `/schedules/{scheduleId}`

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| scheduleId | long | yes | 원하는 일정 수정 | 

* Request Header `Cookie: JSESSIONID=세션ID`

* Body : 
```JSON
{
  "scheduleName" : "타태오가 해야될 것3",
  "content" : "배드로와 훈련하기"
}
```

**Response - 응답**
* Status Code : `200`
* Comment : 일정 수정 성공

* Status Code : `401`
* Comment : 로그인을 하지 않음, 수정 권한이 없음

* Status Code : `404`
* Comment : 없는 일정

### 📌 일정 삭제

**Request - 요청**
* Method : `DELETE`
* URL : `/schedules/{scheduleId}`

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| scheduleId | long | yes | 삭제 할 일정 ID | 

* Request Header `Cookie: JSESSIONID=세션ID`

**Response - 응답**
* Status Code : `204`
* Comment : 일정 삭제 성공

* Status Code : `401`
* Comment : 로그인을 하지 않음, 삭제 권한이 없음

* Status Code : `404`
* Comment : 없는 일정

---

### 📌 댓글 생성

**Request - 요청**
* Method : `POST`
* URL : `/schedules/{scheduleId}/comments`

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| scheduleId | long | yes | 원하는 일정에 댓글달기 |

* Request Header `Cookie: JSESSIONID=세션ID`

* Body :
```JSON
{
  "comment" : "역시 스승님 대단하십니다."
}
```

**Response - 응답**
* Status Code : `201`
* Comment : 댓글 생성 완료
* Response Body : `application/json`
* Body :
```JSON
{
  "id" : 1,
  "comment" : "역시 스승님 대단하십니다.",
  "createdDate" : "2026-06-02",
  "modifiedDate" : "2026-06-02"
}
```

* Status Code : `401`
* Comment : 로그인을 하지 않음

* Status Code : `404`
* Comment : 없는 일정

### 📌 댓글 조회(단건)

**Request - 요청**
* Method : `GET`
* URL : `/schedules/{scheduleId}/comments`

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| scheduleId | long | yes | 원하는 일정에 있는 댓글확인 |


**Response - 응답**
* Status Code : `201`
* Comment : 댓글 조회 완료
* Response Body : `application/json`

* Body :
```JSON
[
  { 
    "id" : 1,
    "comment" : "역시 스승님 대단하십니다.",
    "commenters" : "카케오",
    "modifiedDate" : "2026-06-02"
  },
  { 
    "id" : 2,
    "comment" : "이번만 협조를 하는겁니다 순구씨",
    "commenters" : "나다니엘",
    "modifiedDate" : "2026-06-02"
  },   
]
```
* Status Code : `401`
* Comment : 로그인 하지 않음, 수정 할 권한 없음

* Status Code : `404`
* Comment : 없는 일정

### 📌 댓글 조회(단건)

**Request - 요청**
* Method : `GET`
* URL : `/schedules/comments/{commentId}`

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| commentId | long | yes | 원하는 댓글확인 |


**Response - 응답**
* Status Code : `201`
* Comment : 댓글 조회 완료
* Response Body : `application/json`

* Body :
```JSON
{ 
  "scheduleAuthor" : "시몬",
  "scheduleName" : "검술훈련",
  "content" : "검술훈련 다 끝냄",
  "commenters" : "카케오",
  "comment" : "역시 스승님 대단하십니다.",
  "createdDate" : "2026-06-02",
  "modifiedDate" : "2026-06-02"
}
```

* Status Code : `404`
* Comment : 없는 일정, 없는 댓글ID

### 📌 댓글 수정

**Request - 요청**
* Method : `POST`
* URL : `/schedules/comments/{commentId}`

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| commentId | long | yes | 원하는 댓글수정 |

* Request Header `Cookie: JSESSIONID=세션ID`

* Body :
```JSON
{
  "comment" : "재미있습니다 사숙"
}
```

**Response - 응답**
* Status Code : `200`
* Comment : 댓글 수정 완료

* Status Code : `401`
* Comment : 로그인 하지 않음, 수정 권한 없음

* Status Code : `404`
* Comment : 없는 일정, 없는 댓글

### 📌 댓글 삭제
  
**Request - 요청**
* Method : `DELETE`
* URL : `/schedules/comments/{commentId}`

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| commentId | long | yes | 원하는 댓글수정 |

* Request Header `Cookie: JSESSIONID=세션ID`


**Response - 응답**
* Status Code : `204`
* Comment : 댓글 삭제 완료

* Status Code : `401`
* Comment : 로그인 하지 않음, 수정 권한 없음

* Status Code : `404`
* Comment : 없는 일정, 없는 댓글
