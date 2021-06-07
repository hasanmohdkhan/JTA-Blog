## Blog API

---

Blog API is a authenication based api

## /register

Request : POST

Body:

```json
{
  "username":"hasan",
  "password":"khan"
}
```

Response:

```json
{
    "responseStatus": "SUCCESS",
    "response": {
        "username": "hasan",
        "password": "khan"
    }
}
```

## /authenticate

Request : POST

Body:

```json
{
  "username":"hasan",
  "password":"khan"
}
```

Response:

```json
{
    "responseStatus": "SUCCESS",
    "response": {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYWhpMSIsImV4cCI6MTYyMzA1NzYzOSwiaWF0IjoxNjIzMDM5NjM5fQ.JkztnEKU3Yp6JaYHUHw4afyIlivqFT1TP-2ctlW7x7BcJv3oB-PdOke90kfb4fCsBlOEuQJpyEoek989ZLvJyA"
    }
}
```

## /blog/add

Request : POST

Header:

```json
Authorization: "Bearer {{token}}"
```

Body:

```json
{"title":"Test title","body":"test body"}

NOTE: title and body are required and can not be empty.
```

Response:

```json
{
    "responseStatus": "SUCCESS",
    "response": [
        {
            "id": 1,
            "title": "Test title",
            "body": "test body",
            "author": "hasan",
            "createdAt": 1623019961875
        }
    ]
}
```

## /blog/update/{id}

Request : POST

Header:

```json
Authorization: "Bearer <your token here>"
```

Body:

```json
{"title":"update Test title","body":"update test body"} // for updating both

Option: {"title":"update Test title"} // for updating title
Option: {"body":"update test body"} // for updating body
```

Response:

```json
{
  "responseStatus": "SUCCESS",
  "response": {
    "id": 2,
    "title": "update Test title",
    "body": "update test body",
    "author": "hasan",
    "createdAt": 1623020834461,
    "updatedAt": 1623020844738
  }
}
```

## /blog/delete/{id}

Request : GET

Header:

```json
Authorization: "Barer <your token here>"
```

Response:

```json
{
    "responseStatus": "SUCCESS",
    "response": "Deleted."
}
```