# SmartSudoku

#### Files

### app
* Main: (temporary) Tester Code
* StartUseCaseFactory: Begins the chain of use cases

| method                                                    | function                             | interacts with?             |
|-----------------------------------------------------------|--------------------------------------|-----------------------------|
| private  StartUseCaseFactory()                            | Unimplemented                        |                             |
| public static  StartView  create()                        | Returns new StartView  object        | start in interface adapters |
| private static  StartController  createUserStartUseCase() | Returns a new StartController object | start in interface adapters |

### data_access
* UserDAO

| method | function |
|---|---|
| public <br>UserDAO() | Constructor<br>1. Create a MongoDB Client -> Database -> Collection (where the users are)<br>2. Gets info from Mongo and creates account object<br>3. Creates list of accounts (in document form) |
| public boolean <br>existsbyName() | Unimplemented helper method |
| public void <br>addUser(User user) | Helper method for UserDAO(), adds an existing user |
| private void <br>addUser() | Helper method for UserDAO(), adds a non-existing user |
| public boolean <br>existsByName() | Helper method for LoginInteractor() |
| public User get() | Returns User object from the accounts by username |
| public void <br>delete() | Deletes a single specified user |
| public void <br>deleteAll() | Deletes all users |
| public void <br>addScore() | Adds a score |
| private void <br>changeScores() | Changes a score |
| public void <br>saveProgress() | Saves a user's current game progress |
