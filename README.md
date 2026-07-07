# schedule (일정 관리 과제)

---
## ERD

**ERD링크**<br>
https://www.erdcloud.com/p/vm5b255nF7nu29Ye7
<br><br>
**SQL쿼리** 

```SQL
DROP TABLE IF EXISTS `schedules`;


CREATE TABLE `schedules` (
	`scheduleId` bigint	NOT NULL AUTO_INCREMENT,
	`scheduleName` varchar(50) NOT NULL,
	`contents`	varchar(512) NOT NULL,
	`authorName` varchar(50) NOT NULL,
	`schedulePw` varchar(50) NOT NULL,
	`createdAt`	datetime NOT NULL,
	`modifiedAt` datetime NOT NULL
);

ALTER TABLE `schedules` ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (
	`scheduleId`
);
```

---
## API 명세서 : 일정 관리 (Schedule)
Version: 1.0.0<br>
Base URL : http://localhost:8080

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
	"scheduleId" : 1,
	"scheduleName" : "타대오가 해야될 것1",
	"contents" : "배드로 숑디와 탕후루 먹기",
	"authorName" : "타대오",
	"createdAt" : "2026-06-02",
	"modifiedAt" : "2026-06-02"
}
```

* Status Code : `404`
* Comment : 없는 유저 검색 

### 📌 일정 조회(다건)

**Request - 요청**
* Method : `GET`
* URL : `/schedules`
* Query Parameter : authorName

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| authorName | String | no | 작성자명 기준 전체 일정조회 | 


**Response - 응답**
* Status Code : `200`
* Comment : 조회 성공, 사용자의 일정이 없을 경우 [] 빈 배열 전달
* Response Body : `application/json`
* Body : 
```JSON
[
	{
  	"scheduleId" : 1,
  	"scheduleName" : "타대오가 해야될 것1",
  	"contents" : "배드로 숑디와 탕후루 먹기",
	"authorName" : "타대오",
	"createdAt" : "2026-06-02",
	"modifiedAt" : "2026-06-02"
	},
	{
  	"scheduleId" : 7,
  	"scheduleName" : "타대오가 해야될 것2",
  	"contents" : "배드로 숑디와 훈련하기;",
  	"authorName" : "타대오",
	"createdAt" : "2026-06-02",
	"modifiedAt" : "2026-06-02"
	}
]
```

### 📌 일정 등록(생성)

**Request - 요청**
* Method : `POST`
* URL : `/schedules`

* Body : 
```JSON
{
  "scheduleName" : "타대오가 해야될 것1",
  "contents" : "배드로 숑디와 탕후루 먹기",
  "authorName" : "타대오",
  "schedulePw" : "12345"
}
```

**Response - 응답**
* Status Code : `201`
* Comment : 일정 생성 성공
* Response Body : `application/json`
* Body : 
```JSON
{
	"scheduleId" : 1,
	"scheduleName" : "타대오가 해야될 것1",
	"contents" : "배드로 숑디와 탕후루 먹기",
	"authorName" : "타대오",
	"createdAt" : "2026-06-02",
	"modifiedAt" : "2026-06-02"
}
```

### 📌 일정 수정

**Request - 요청**
* Method : `PATCH`
* URL : `/schedules/{scheduleId}`

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| scheduleId | long | yes | 원하는 일정 수정 | 

* Body : 
```JSON
{
  "scheduleName" : "카게오가 수정함",
  "authorName" : "카케오",
  "schedulePw" : "12345"
}
```

**Response - 응답**
* Status Code : `200`
* Comment : 일정 수정 성공
* Response Body : `application/json`
* Body : 
```JSON
{
	"scheduleId" : 1,
	"scheduleNname" : "카게오가 수정함",
	"contents" : "배드로 숑디와 탕후루 먹기",
	"authorName" : "카케오",
	"modifiedAt" : "2026-06-02"
}
```

* Status Code : `401`
* Comment : 패스워드가 틀렸음

* Status Code : `404`
* Comment : 없는 유저 검색 

### 📌 일정 삭제

**Request - 요청**
* Method : `DELETE`
* URL : `/schedules/{scheduleId}`

| 속성 | 타입 | 필수 | 설명 |
|---|---|---|---|
| scheduleId | long | yes | 삭제 할 일정 ID | 

* Body : 
```JSON
{
  "schedulePw" : "12345"
}
```

**Response - 응답**
* Status Code : `204`
* Comment : 일정 삭제 성공

* Status Code : `401`
* Comment : 패스워드가 틀렸음

* Status Code : `404`
* Comment : 없는 유저 검색

---

## Postman API

```json
{
  "info": {
    "_postman_id": "9e385ab2-8e53-4bea-b31d-52662189f0ee",
    "name": "schedule",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
    "_exporter_id": "45495187"
  },
  "item": [
    {
      "name": "create Schedule",
      "request": {
        "method": "POST",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"scheduleName\" : \"타대오가 해야될 것1\",\n  \"contents\" : \"배드로 숑디와 탕후루 먹기\",\n  \"authorName\" : \"타대오\",\n  \"schedulePw\" : \"12345\"\n}",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        },
        "url": {
          "raw": "http://127.0.0.1:8080/schedules",
          "protocol": "http",
          "host": [
            "127",
            "0",
            "0",
            "1"
          ],
          "port": "8080",
          "path": [
            "schedules"
          ]
        }
      },
      "response": []
    },
    {
      "name": "getQuery",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://127.0.0.1:8080/schedules?authorName=타대오",
          "protocol": "http",
          "host": [
            "127",
            "0",
            "0",
            "1"
          ],
          "port": "8080",
          "path": [
            "schedules"
          ],
          "query": [
            {
              "key": "authorName",
              "value": "타대오",
              "type": "text"
            }
          ]
        }
      },
      "response": []
    },
    {
      "name": "get",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://127.0.0.1:8080/schedules",
          "protocol": "http",
          "host": [
            "127",
            "0",
            "0",
            "1"
          ],
          "port": "8080",
          "path": [
            "schedules"
          ]
        }
      },
      "response": []
    },
    {
      "name": "get One",
      "request": {
        "method": "GET",
        "header": []
      },
      "response": []
    },
    {
      "name": "patch Schedule",
      "request": {
        "method": "PATCH",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        },
        "url": {
          "raw": "http://127.0.0.1:8080/schedules/1",
          "protocol": "http",
          "host": [
            "127",
            "0",
            "0",
            "1"
          ],
          "port": "8080",
          "path": [
            "schedules",
            "1"
          ]
        }
      },
      "response": []
    },
    {
      "name": "New Request",
      "request": {
        "method": "GET",
        "header": []
      },
      "response": []
    }
  ]
}
```


