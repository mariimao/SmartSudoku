# SmartSudoku

## Files

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

* SpotifyDAO

| method                            | function                                                                                                                                       |
|-----------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| public <br>SpotifyDAO()           | Constructor<br>1. Creates the object containing client id and client secret -> helps with generating access token that is refreshed every hour |
| public  <br>getClientId()         | Helper method for SpotifyDAO(), returns the client_id                                                                                          |
| public <br>getClientSecret()      | Helper method for SpotifyDAO(), returns the client_secret                                                                                      |
| public String <br>getAccessCode() | Makes an API POST request to Spotify API that retrieves an access token to access other parts of the api such as retrieving song names         |
| public String <br>getArtistName() | Makes an API GET request that returns the name of an artist based on their spotify id                                                          |
|public String <br>getTrackName() | Makes an API GET request that returns the name of a track based on the spotify ID of that track |
| public int <br>getDuration() | Makes an API GET request that returns the duration of the selected track based on its spotify id (in Milliseconds) |
| piblic ArrayList\<String\> <br>getSuggestions | Makes an API GET request that returns a list of suggested song ids based on the search input which is a string | 