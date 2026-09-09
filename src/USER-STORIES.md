###  Banking System — User Stories

---

##### 🔴 Priority:  Must Have

---
#### Functional Requirements : 




##### 1- Customer Logic 

> As a bank customer, I want to log into my banking account so that I can securely access my account
> and perform banking operations.

#### Acceptance Criteria :

- ****Successful Login****
    - **Given** I have a valid customer account, **When** I enter the correct login credentials, 
  **Then** I should be successfully logged into my banking account.
    - 
- **Invalid Credentials**
    - **Given** I have a customer account, **When** I enter incorrect login credentials ,
  **Then** the system should reject the login attempt.
    - 
- **Customer Role**
    - **Given** I successfully log in as a customer, **When** the system identifies my account, 
  **Then** I should be recognized as a Customer.

### Technical Tasks

- [ ] Create Customer class
- [ ] Implement login functionality
- [ ] Validate credentials
- [ ] Implement customer role
- [ ] Write unit tests

---

#### 2- Banker Login

> As a banker, I want to log into the banking system
> so that I can access the functions available to bankers.

#### Acceptance Criteria : 

- **Successful Login**
    - **Given** I have a valid banker account,**When** I enter the correct credentials,
  **Then** I should be successfully logged into the system.

- **Invalid Credentials**
    - **Given** I enter invalid credentials, **When** I attempt to log in, **Then** the system should reject the 
  login attempt.

### Technical Tasks
- [ ] Create Banker class
- [ ] Implement banker authentication
- [ ] Implement role-based access
- [ ] Write unit tests

---


