# sigdep-contracts

DTOs canoniques et contrats d'API partagés entre `sigdep-sync`
(agent côté site) et `sigdep-hub` (serveur central).

Ce module est volontairement minimal : uniquement des annotations
Jackson et des records Java, pas de Spring, pas de JPA, pas de
Lombok. Il est publié comme un artefact Maven et consommé par les
deux côtés du fil.

## Place dans la plateforme SIGDEP-3

Ce dépôt est l'un des trois projets qui composent SIGDEP-3 :

| Projet                                                             | Rôle                                                                     |
| ------------------------------------------------------------------ | ------------------------------------------------------------------------ |
| **`sigdep-contracts`** (ce dépôt)                                  | Bibliothèque Maven : DTOs et contrats d'API partagés                     |
| [`sigdep-sync`](https://github.com/ITECH-CI/sigdep-sync)           | Agent côté site — lit OpenMRS local, pousse les lots                     |
| [`sigdep-hub`](https://github.com/ITECH-CI/sigdep-hub)             | Serveur central — réception des lots, indicateurs, console               |

`sigdep-sync` (émetteur) et `sigdep-hub` (récepteur) dépendent tous
les deux de cette bibliothèque via leur `pom.xml` Maven. **Toute
modification ici est une modification du format de la liaison** et
doit être livrée avant qu'un des deux consommateurs ne publie une
release qui l'utilise.

## Contenu

| Fichier                            | Rôle                                                          |
| ---------------------------------- | ------------------------------------------------------------- |
| `ApiVersion.java`                  | Version majeure courante de l'API de synchronisation.         |
| `EntityType.java`                  | Énumération des types d'entités qu'un lot peut transporter.   |
| `SyncBatchRequest.java`            | Enveloppe POSTée par l'agent : `batchId`, `siteCode`, `records[]`. |
| `SyncBatchResponse.java`           | Réponse du hub : compteurs acceptés / rejetés + extrait d'erreurs. |
| `dto/PatientDto.java`              | Patient canonique.                                            |
| `dto/VisitDto.java`                | Visite canonique (encounters de suivi).                       |
| `dto/TreatmentInitiationDto.java`  | Formulaire d'initiation ARV.                                  |
| `dto/PediatricInitiationDto.java`  | Formulaire d'initiation pédiatrique (champs additionnels).    |
| `dto/ClosureDto.java`              | Encounter « PEC - Issue » (clôture de dossier).               |
| `dto/LabResultDto.java`            | Résultat de biologie (CV, CD4, …).                            |
| `dto/TptRecordDto.java`            | Encounter de suivi ou d'issue TPT.                            |
| `dto/DispensationDto.java`         | Dispensation ARV / cotrim (placeholder, non encore câblé).    |

Chaque DTO porte un `sourceUuid` (l'UUID OpenMRS de l'enregistrement
d'origine) que le hub utilise comme clé d'upsert, scopée par
`siteCode`.

## Compiler

```bash
mvn clean install
```

Cela installe l'artefact dans le `~/.m2` local. `sigdep-sync` et
`sigdep-hub` le résolvent depuis là au moment de la compilation.

## Publication

L'artefact est consommé par deux projets voisins, sa version doit
donc correspondre à ce que ces projets déclarent dans leur
`<dependencyManagement>`. Au quotidien, **`mvn install` suffit** :
chaque développeur le recompile localement quand nécessaire.

Pour des snapshots ou releases partagés, publier sur un registre
Maven que les deux projets voisins peuvent atteindre (GitHub
Packages, Nexus interne, …) et mettre à jour la version de la
dépendance sur les deux consommateurs dans une PR synchronisée.

## Versionning

Les changements cassants (renommage de champ, changement de type,
suppression de champ) nécessitent :

1. D'incrémenter la version majeure de `ApiVersion`.
2. De garder la **version précédente** des DTOs concernés disponible
   côté hub jusqu'à ce que chaque agent déployé soit mis à jour. Le
   hub expose des endpoints versionnés dans l'URL (`/api/v1/sync/...`) ;
   une v2 cohabiterait avec v1 pendant la transition.
3. Un déploiement coordonné : publier d'abord une nouvelle release
   du hub, déployer, puis dérouler la mise à jour des agents site
   par site.

Les changements additifs (nouveau champ optionnel, nouvelle valeur
d'enum) ne nécessitent pas de bump de version mais doivent être
marqués optionnels dans le code consommateur.

## Style de code

Records Java simples. L'ordre des champs reflète l'ordre des
colonnes en base côté hub (aide la relecture à croiser le SQL
d'upsert avec le DTO). Pas de logique métier — juste des porteurs
de données. Si une validation est nécessaire, elle vit côté hub
(`ingestion-api`).

## Licence

À définir en session plénière avec le HMIS TWG ; aucun fichier de
licence n'est livré pour l'instant. En attendant, considérer le
contenu comme « tous droits réservés par I-TECH Côte d'Ivoire et le
programme PNLS ».
