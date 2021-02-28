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

