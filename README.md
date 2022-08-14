# Wordle proof of concept

This is a simple implementation of the game Wordle in Kotlin. This application uses the
[Kobweb framework](https://github.com/varabyte/kobweb/). 

## Application structure

- The frontend code is in `src/jsMain`. This uses Compose for web to create a bunch of simple components that form the
  UI and interaction of the game.
- The backend code is in `src/jvmMain`. This is a simple API with two endpoints (`guess` and `refresh`). The state
  regarding the current word and the list of words are maintained in the `WordStore`.
- Shared code is in `src/commonMain`. Here you wil find some models that are shared between the frontend and backend.
  This is particularly useful for the messages that are sent to and from the API, because both the frontend and
  backend neeed to know these data structures.

## Running the game

Simply run:
```
$ kobweb run
```

Now the game should be running on `http://localhost:8080`