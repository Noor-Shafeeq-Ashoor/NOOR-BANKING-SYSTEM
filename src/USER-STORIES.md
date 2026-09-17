# User Stories — Banking With Java

## 1. Customer Registration

**As a** new customer,
**I want to** register with my personal information and password,
**so that** I can create a banking account and access the system.

### Acceptance Criteria

* Customer can enter a username, email, and password.
* Username must be unique.
* Customer information is saved.
* Customer can use the registered credentials to log in.

---

## 2. Customer Login

**As a** customer,
**I want to** log in using my credentials,
**so that** I can securely access my accounts.

### Acceptance Criteria

* Customer enters username/email and password.
* Correct credentials allow access.
* Incorrect credentials are rejected.
* Customer is shown the customer menu after successful login.

---

## 3. View Accounts

**As a** customer,
**I want to** view my bank accounts,
**so that** I can choose and manage the account I need.

### Acceptance Criteria

* Customer can see their Checking and/or Savings accounts.
* Customer can select an account.
* Customer must enter the account password before accessing account details.
* Account balance and Mastercard information can be viewed.

---

## 4. Request a New Account

**As a** customer,
**I want to** request a new Checking or Savings account,
**so that** I can have additional banking services.

### Acceptance Criteria

* Customer can choose Checking or Savings.
* Customer can request a Mastercard.
* The request is saved.
* The request has a status such as Pending, Approved, or Rejected.

---

## 5. Banker Approves Account Request

**As a** banker,
**I want to** review customer account requests,
**so that** I can approve or reject new accounts.

### Acceptance Criteria

* Banker can view pending requests.
* Banker can approve a request.
* Banker can reject a request.
* Banker can select the approved Mastercard type.
* An approved request creates the customer's account.

---

## 6. Deposit Money

**As a** customer,
**I want to** deposit money into my account,
**so that** I can increase my account balance.

### Acceptance Criteria

* Customer enters a deposit amount.
* The amount is added to the account balance.
* A deposit transaction is created.
* The transaction is stored in transaction history.

---

## 7. Withdraw Money

**As a** customer,
**I want to** withdraw money from my account,
**so that** I can access my funds.

### Acceptance Criteria

* Customer enters a withdrawal amount.
* The balance is updated correctly.
* A withdrawal transaction is created.
* Overdraft rules are applied when necessary.

---

## 8. Transfer Money

**As a** customer,
**I want to** transfer money between accounts,
**so that** I can move funds between my accounts or to another customer.

### Acceptance Criteria

* Customer selects the source account.
* Customer selects the destination account.
* The transfer amount is validated.
* The source balance is decreased.
* The destination balance is increased.
* Transfer-out and transfer-in transactions are created.

---

## 9. Overdraft Protection

**As a** customer,
**I want** overdraft rules to be applied to my account,
**so that** excessive overdrawing is controlled.

### Acceptance Criteria

* An overdraft fee of $35 is applied when an overdraft occurs.
* The overdraft count is increased.
* A customer cannot withdraw more than $100 when the balance is already negative.
* The account is deactivated after two overdrafts.

---

## 10. View Transaction History

**As a** customer,
**I want to** view my transaction history,
**so that** I can track my banking activity.

### Acceptance Criteria

* Customer can view transactions for an account.
* Transactions show relevant information such as type, amount, and date.
* Deposit, withdrawal, transfer-in, and transfer-out transactions are recorded.
* Customer can filter transactions when applicable.

---

## 11. Mastercard

**As a** customer,
**I want to** request a Mastercard for my account,
**so that** I can have a card associated with my account.

### Acceptance Criteria

* Customer can request a Mastercard.
* Available types include Standard, Titanium, and Platinum.
* Banker can approve the requested card type or select another available type.
* An approved card has a card number.
* Mastercard information is associated with the account.

---

## 12. Data Persistence

**As a** banking system user,
**I want** my users, accounts, and transactions to be saved,
**so that** my data is not lost when the application closes.

### Acceptance Criteria

* User data is saved to a file.
* Account data is saved to a file.
* Transaction data is saved to a file.
* Data is loaded when the application starts.
* Previously stored data remains available after restarting the application.

---

## 13. Banker Login

**As a** banker,
**I want to** log in using my staff credentials,
**so that** only authorized bankers can access banker functions.

### Acceptance Criteria

* Banker credentials are checked against the predefined banker data.
* Correct credentials allow access.
* Incorrect credentials are rejected.
* Successful login opens the banker menu.

