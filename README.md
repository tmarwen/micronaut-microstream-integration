## Micronaut - MicroStream integration
---
## Feature description

This project is an intent to present an experiment of Micronaut to MicroStream integration.

The feature exposes the MicroStream graph data as a GraphQL schema.

All that the user has to do is to create it MicroStream graph model and annotation the root entity with
the `@Root` annotation.

All the heavy lifting is automagically done through Micronaut annotation processing (part of it is at
compile time) and the user data is accessible through the exposed */graphql* endpoint.

A lot of work remains to do within this project, part of it:
* Handle GraphQL mutations
* Full MicroStream configuration through property sources
* ...

## Chat (Sample application)
The Gradle subproject `chat-application` holds a sample `main` application showcasing the feature capabilities (due to
time shortage I could not do more than the sample showcase).

Navigate to the module directory and run below command (after having built the _micronaut-microstream-graph_ module:
```
$ ./gradlew run
```
Open a web browser and navigate to `http://localhost:8080/graphiql` and play with some queries.

Here down a sample GraphQL query:
```
query {
  threads {
    name,
    members {
      username
    },
    messages {
      author {
        username
      },
      content,
      createdAt
    }
  }
  users {
    username,
    email
  }
}
```
and here down the results:
```
{
  "data": {
    "threads": [
      {
        "name": "hackathon.microstream.on",
        "members": [
          {
            "username": "tmarouane"
          }
        ],
        "messages": [
          {
            "author": {
              "username": "tmarouane"
            },
            "content": "MicroStream is cool!",
            "createdAt": "2021-02-28T23:16:17.674570Z"
          },
          {
            "author": {
              "username": "tmarouane"
            },
            "content": "MicroStream is cool!",
            "createdAt": "2021-02-28T23:16:17.674598Z"
          }
        ]
      }
    ],
    "users": [
      {
        "username": "tmarouane",
        "email": "marouane.trab@gmail.com"
      }
    ]
  }
}
```

