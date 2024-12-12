
# Coding Examination

**Core Functionality**
•Create an App, which can be used to get the list of random users. 
• Enter a number that should give a list of users.  
• Display in a card with First name, Last name, Address and Profile picture. 
• Tapping on a card will navigate to a details page with loaded data what is selected. 


## Installation

Install my-project with git

```bash
  git clone -b <master> git@github.com:custodioap/coding-exam-collabera.git
  cd my-project
```

or

Download directly as .zip file
    
## Usage/Examples

```kotlin
android {
  compileSdk = 34

  minSdk = 24
  targetSdk = 34
  versionCode = 1
  versionName = "1.0"
}
```

Tested Running on Android API **Level 34**


## API Reference

#### Requesting Multiple User 

```http
  GET https://randomuser.me/api/
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `?results=5000` | `string` | **Required**. Fetch 5000 records |




## Authors

- [@custodioap](https://www.github.com/custodioap)

