# Changelog — sigdep-contracts

Le format suit [Keep a Changelog](https://keepachangelog.com/) et
adhère à [Semantic Versioning](https://semver.org/).

## [1.0.0] — 2026-05-21

Première release stable des contrats partagés entre l'agent
(sigdep-sync) et le hub (sigdep-hub). Library Maven publiée comme
artefact `ci.itechciv.sigdep:sigdep-contracts`.

### DTOs

10 DTOs Java records représentant les entités métier transportées
entre l'agent et le hub :

- `PatientDto` + `IdentifierDto` — démographie + identifiants
  nationaux (UPID, CODE ARV, CMU, etc.).
- `VisitDto` — visite clinique de suivi avec signes vitaux,
  stade OMS, dépistage TB, régime ARV, et facts IVSA (msdCode,
  alertSignsCount, neuroSignsCount).
- `TreatmentInitiationDto` + `PediatricInitiationDto` — fiche
  initiale ARV (adulte + extension pédiatrique). Porte les
  profile fields (profession, religion, etc.) propagés à
  `core.patients` côté hub.
- `ClosureDto` — clôture de dossier (décès / transfert / arrêt
  volontaire / négatif).
- `LabResultDto` — résultat d'examen biologique.
- `TptRecordDto` — fiche TPT.
- `ScreeningDto` — dépistage HIV (anonyme, depuis le module
  openmrs/hivscreening).
- `PtmeMotherDto`, `PtmeMotherVisitDto`, `PtmeChildDto`,
  `PtmeChildVisitDto` — module PTME.
- `DispensationDto` — défini pour symétrie, mais pas alimenté
  par l'agent (la dispensation est captée sur la visite).

### Enveloppe de transport

- `SyncBatchRequest<T>` : enveloppe pour un batch (siteCode,
  batchId, entityType, records).
- `SyncBatchResponse` + `RecordError` : ACK du hub avec compteurs
  accepted / rejected et liste détaillée des rejets.

### Routage

- `EntityType` enum : 13 entités, utilisée pour le routage côté
  hub (`/api/v1/sync/<entity>`) et pour le typage côté agent
  (`PayloadTypes.classFor`).

[1.0.0]: https://github.com/ITECH-CI/sigdep-contracts/releases/tag/v1.0.0
