# sigdep-contracts

Canonical DTOs and API contracts shared between `sigdep-sync` (edge
agent) and `sigdep-hub` (central server).

This module is intentionally minimal: only Jackson annotations and
Java records, no Spring, no JPA, no Lombok. It is published as a Maven
artifact and consumed by both sides of the wire.

## Place in the SIGDEP-3 platform

This repo is one of three projects that make up SIGDEP-3:

| Project                                                            | Role                                                                    |
| ------------------------------------------------------------------ | ----------------------------------------------------------------------- |
| **`sigdep-contracts`** (this repo)                                 | Shared DTOs and API contracts (Maven library)                           |
| [`sigdep-sync`](https://github.com/ITECH-CI/sigdep-sync)           | Edge agent deployed on each site — reads local OpenMRS, pushes batches  |
| [`sigdep-hub`](https://github.com/ITECH-CI/sigdep-hub)             | Central server — receives batches, indicators, console                  |

Both `sigdep-sync` (writer side) and `sigdep-hub` (reader side) depend
on this library through their Maven `pom.xml`. **Any change here is a
change to the wire format** and needs to land before either consumer
ships a release that uses it.

## What's in here

| File                    | Purpose                                                  |
| ----------------------- | -------------------------------------------------------- |
| `ApiVersion.java`       | Current major version of the sync API.                   |
| `EntityType.java`       | Enum of the entity kinds a batch can carry.              |
| `SyncBatchRequest.java` | Envelope POSTed by the agent: `batchId`, `siteCode`, `records[]`. |
| `SyncBatchResponse.java`| Hub response: accepted / rejected counts + error sample. |
| `dto/PatientDto.java`   | Canonical patient.                                       |
| `dto/VisitDto.java`     | Canonical visit (follow-up encounters).                  |
| `dto/TreatmentInitiationDto.java` | ARV initiation form.                         |
| `dto/PediatricInitiationDto.java` | Paediatric initiation form (additional fields).  |
| `dto/ClosureDto.java`   | "PEC - Issue" encounter (file closure).                  |
| `dto/LabResultDto.java` | Lab result (CV, CD4, …).                                 |
| `dto/TptRecordDto.java` | TPT follow-up or outcome encounter.                      |
| `dto/DispensationDto.java` | ARV / cotrim dispensation (placeholder, not yet wired). |

Every DTO carries a `sourceUuid` (the OpenMRS UUID of the original
record) that the hub uses as the upsert key, scoped by `siteCode`.

## Build

```bash
mvn clean install
```

That installs the artefact in your local `~/.m2`. `sigdep-sync` and
`sigdep-hub` resolve it from there at build time.

## Publishing

The artefact is consumed by two sibling projects, so its version must
match what those projects declare in their `<dependencyManagement>`.
Day-to-day, **`mvn install` is enough** — every developer rebuilds
locally as needed.

For shared snapshots or releases, push to a Maven registry the two
sibling projects can pull from (GitHub Packages, internal Nexus, …) and
update the dependency version on both consumers in a synchronised PR.

## Versioning

API-breaking changes (renaming a field, changing a type, dropping a
field) require:

1. Bumping the major version of `ApiVersion`.
2. Keeping the **previous version** of the affected DTOs available on
   the hub side until every fielded agent has been upgraded. The hub
   exposes endpoints versioned in the URL path (`/api/v1/sync/...`); a
   v2 would live alongside v1 for the transition period.
3. A coordinated rollout: build a new hub release first, deploy, then
   roll out the agent upgrade per site.

Additive changes (new optional field, new enum value) don't require a
version bump but should be marked optional in the consuming code.

## Code style

Plain Java records. Field order matches the database column order in
the hub (helps reviewers cross-check the upsert SQL against the DTO).
No business logic — just data carriers. If validation logic is needed,
it lives on the hub side (`ingestion-api`).

## License

To be decided in a plenary session with the HMIS TWG; no license file
is shipped yet. In the meantime, treat the contents as "all rights
reserved by I-TECH Côte d'Ivoire and the PNLS programme".
