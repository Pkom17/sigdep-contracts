package ci.itechciv.sigdep.contracts.dto;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

/**
 * HIV screening record. Source is the OpenMRS "hivscreening" module
 * (its own dedicated tables, not encounters/obs) — see
 * sigdep-hub/docs/hivscreening-2.0.omod for the upstream schema.
 *
 * Important: a screening is anonymous from the patient/registry side —
 * there is no patientSourceUuid. The {@link #screeningCode} acts as the
 * local opaque identifier; cross-linking with the ARV cohort happens
 * only if and when an organisational decision is made to do it.
 *
 * Categorical fields come in as their decoded labels from the agent
 * (e.g. populationType = "Population Générale" / "UD" / "TS" / "HSH" /
 * "PC" / "Autres"), so the hub stores human-readable strings instead
 * of upstream integer codes. The original integer values land in
 * {@code extraData} for reference.
 */
public record ScreeningDto(
        UUID sourceUuid,
        String siteCode,           // OpenMRS location_id resolved to the SIGDEP site code

        // Identification
        String screeningCode,
        LocalDate screeningDate,
        LocalDate resultAnnouncingDate,

        // Demographics
        String gender,             // "M" / "F" (passthrough)
        Integer age,
        String profession,
        String residence,
        String maritalStatus,      // "Célibataire" / "Couple" / "Autre" / null
        String otherMaritalStatus,

        // Context
        String populationType,     // "Population Générale" / "UD" / "TS" / "HSH" / "PC" / "Autres"
        String screeningReason,    // "IST" / "AES" / "PrEP" / "Femme enceinte" / ...
        String otherScreeningReason,

        // Tests — each is "R" / "NR" / null
        String test1Reaction,
        String test2Reaction,
        String test3Reaction,
        Boolean test1Invalidated,
        Boolean test2Invalidated,
        Boolean test3Invalidated,

        // Outcome
        String finalResult,        // "POS" / "NEG" / "IND"
        Boolean retesting,
        String comment,

        // Register info (joined upstream)
        String screeningSiteType,
        String screeningPost,

        Map<String, Object> extraData,
        Boolean voided
) {}
