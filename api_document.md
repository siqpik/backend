# SIqpIK API

List of Siqpik URI's

## ProfileResource


##### /api/profile/search/{name}

Return a list with all users whom userName and name contains the {name} param. If there is no coincidences the list will be empty. 

- HTTP Method: **GET**
- Params:
    - name: String
- HTTP Status:
	- 200
- Response body: *JSON*

Response example:
```JSON
[
  {
    "userName": "User1",
    "name": "User one",
    "avatarUrl": "https://s2.img.com/img"
  },
  {
    "userName": "User2",
    "name": "User two",
    "avatarUrl": "https://s2.img.com/img2"
  }
]
```

##### /api/profile/{userName}

Return the profile data of the user with userName *{userName}*

- HTTP Method: **GET**
- Params:
    - userName: String
- HTTP Status:
	- 200 All OK
	- 404 There is no user with that username
	- 401 The user who makes the request is not logged in
- Response body: *JSON*

```
```

## CameraResource

##### /api/attempts

Return the numbers of attempted pics of logged user on the current day

- HTTP Method: **GET**
- HTTP Status:
	- 200 All OK
	- 401 The user who makes the request is not logged in
- Response body: *JSON*

```JSON
{
  "attempts": 2
}
``` 

##### /api/attempts

Add a pic attempt to the user who is logged in

- HTTP Method: **POST**
- HTTP Status:
	- 200 All OK.
	- 401 The user who makes the request is not logged in.
- Response body: *None*

##### /api/picture

Upload a pic 

- HTTP Method: **POST**
- HTTP Status:
	- 201 All OK.
	- 409 The user has reached the limit of attempts
	- 415 Could not upload the photo due to a IOException
	- 401 The user who makes the request is not logged in.
- Request body: **FormData** with a key named "file" and the pic on the value. This can be a USVString or Blob (including subclasses such as File)
- Response body: *None*

Request example:
```javascript
const sendPic = async () => {
    const myForm = new FormData();
    myForm.append("file", thePicture);
        try {
            const response = await fetch('.../picture', {
                method: 'POST',
                body: myForm
            });
        } catch (error) {
            //Do something with error
        }
};
```

## BasicAuthenticationResource

##### /register

Sing up a user

- HTTP Method: **POST**
- HTTP Status:
	- 201 All OK.
	- 403 userName or password empty
	- 409 userName is taken
- request body: *Json* object with 2 keys, userName and password
- Response body: *None*

Request example:
```javascript
const register = async () => {
    try {
        let response = await fetch('.../register', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({userName: 'Bob', password: '123'})
        });
    } catch(error) {
        //Do something with error
    }
}

```

## SiqpikResource

##### api/admire/{userName}

Create an admire request from the user who is logged in to the user with userName {userName}

- HTTP Method: **POST**
- Params:
    - userName: String
- HTTP Status:
	- 201 All OK
	- 404 There is no user with that username
	- 401 The user who makes the request is not logged in
- Response body: *None*

##### api/picture/{id]

Create a like from the user who is logged in to the pic with id {id}

- HTTP Method: **POST**
- Params:
    - id: Number
- HTTP Status:
	- 201 All OK
	- 404 There is no pic with that id
	- 401 The user who makes the request is not logged in
- Response body: *None*

##### /api/admirers

Return the userName of the admirers for the user who is logged in

- HTTP Method: **GET**
- HTTP Status:
	- 200 All OK
	- 401 The user who makes the request is not logged in
- Response body: *JSON*

Example response:
```JSON
[
    {
      "name": "RDave"
    },
    {
      "name": "Yery" 
    },
    {
      "name": "CaptainAmerica"
    }   
]
``` 

##### /api/admiring

Return the userName of the users the logged user is admiring

- HTTP Method: **GET**
- HTTP Status:
	- 200 All OK
	- 401 The user who makes the request is not logged in
- Response body: *JSON*

Example response:
```JSON
[
    {
      "name": "RDave"
    },
    {
      "name": "Yery" 
    },
    {
      "name": "CaptainAmerica"
    }   
]
``` 

##### /api/request/{userName}

Create an admire request from the user who is logged in to the user with userName {userName}

- HTTP Method: **POST**
- Params:
    - userName: String
- HTTP Status:
	- 201 All OK
	- 404 There is no user with that username
	- 401 The user who makes the request is not logged in
- Response body: *None*

##### api/request/{requestId}/{result}

The user who is logged in accept or discard the admire request with id {requestId}

- HTTP Method: **POST**
- Params:
    - requestId: Number
    - result: Boolean. 
        - True: accept the request
        - False: dismiss the request
- HTTP Status:
	- 201 Admirer created. This is only possible if result is true
	- 200 The request is canceled. This is only possible if result is false
	- 404 The user who is sending the request don't have any admire request with that id
	- 401 The user who makes the request is not logged in
- Response body: *None*