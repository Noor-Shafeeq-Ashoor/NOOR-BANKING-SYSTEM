## Technologies Used

* **Java** — Main programming language
* **IntelliJ IDEA** — Development environment
* **Git & GitHub** — Version control and project collaboration
* **JUnit** — Unit testing
* **Java Collections** — Managing users, accounts, and transactions
* **File I/O** — Persisting users, accounts, and transactions in `.txt` files
* **Java Time API** — Managing transaction dates and times

---

## Planning & User Stories

The project was planned by breaking the banking system into smaller features and user stories.

The main user roles are:

* **Customer**
* **Banker**

The development process was divided into several stages:

1. Design the main classes and relationships.
2. Implement users, customers, and bankers.
3. Implement checking and savings accounts.
4. Implement deposit, withdrawal, and transfer operations.
5. Implement overdraft protection.
6. Implement Mastercard requests and account requests.
7. Implement authentication.
8. Add file-based data persistence.
9. Add transaction history and filtering.
10. Test the main banking functionalities.
11. Complete documentation and project cleanup.

### Problem-Solving Strategy

When developing the project, problems were handled by:

* Breaking large requirements into smaller tasks.
* Testing individual features before combining them.
* Using exceptions to handle invalid operations.
* Debugging errors based on compiler and runtime messages.
* Separating responsibilities between models, services, and data classes.
* Testing important banking operations using JUnit.

---

## Technologies and Resources

### Planning

* Github project — Used for user stories, task management, and project planning.

### Additional Resources

* Java Documentation
* JUnit Documentation
* Course materials and examples
* GitHub repository resources

---

## Favorite Functions

### Deposit

The `deposit()` function increases the account balance and creates a transaction.

this keeps the balance and transaction history synchronized whenever money is deposited.

### Transfer

The transfer functionality updates both accounts:

* The sender's balance is decreased.
* The receiver's balance is increased.
* A `TRANSFER_OUT` transaction is created for the sender.
* A `TRANSFER_IN` transaction is created for the receiver.

This allows the transaction history of both accounts to accurately reflect the transfer.

### Overdraft Protection

The withdrawal logic checks whether an account can complete the withdrawal.

When an overdraft occurs:

* The overdraft count increases.
* The $35 overdraft fee is applied.
* The account can eventually be deactivated after reaching the allowed overdraft limit.

This function was implemented to enforce the banking rules defined in the project requirements.

### Data Persistence

The `UserData`, `AccountData`, and `TransactionData` classes are responsible for saving and loading information from files.

This allows information such as users, accounts, and transactions to remain available after the application is closed and restarted.

---

## Unresolved Issues & Future Improvements

The current version satisfies the main banking requirements, but several improvements could be added in future versions:

* Replace `.txt` file storage with a relational database.
* Add a graphical user interface (GUI).
* Add more comprehensive automated testing.
* Add additional banking services and account types.

---

## ERD

The following ERD represents the main entities and relationships in the banking system:
<img width="550" height="487" alt="Screenshot 2026-09-17 084928" src="https://github.com/user-attachments/assets/1d1ac50d-e91c-4fd4-b461-a79658b51423" />

