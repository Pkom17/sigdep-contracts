# sigdep-contracts

Canonical DTOs and API contracts shared between `sigdep-sync` (edge agent) and
`sigdep-hub` (central server).

This module is intentionally minimal: only Jackson annotations and Java records,
no Spring, no JPA. It is published as a Maven artifact and consumed by both
sides of the wire.

## Build

```bash
mvn clean install
```

## Versioning

API breaking changes require bumping the major version of `ApiVersion` and
keeping the previous version available on the server side for backward
compatibility with edge agents that have not yet been updated.
