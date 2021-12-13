# About the Project

Sample chat

## Built With

- The project built with different layers (e.g. Data, Domain, Presentation).
- Built with MVI arch using States, Events, Effects
- Hilt - Dependency injection
- Coroutines - async work/threading
- Retrofit - networking
- Google AAC: Room - database
- Google ML Kit: Smart reply in chat

## Notes
I've chosen MVI architecture pattern since from my perspective this approach is more cleaner than MVVM.
But if you want I can migrate to MVVM approach as well.

Also I setup pre-population of database (AppDatabase.kt). It's needed to initialise ML models, so Smart reply SDK will work.