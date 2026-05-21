package ci.itechciv.sigdep.contracts.dto;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

/**
 * PTME pregnant patient + her aggregated mother follow-up record.
 * Source: OpenMRS "ptme" module — ptme_pregnant_patient JOIN
 * ptme_mother_followup (1:1 in practice, taking the most recent if many).
 *
 * Demographics (family_name, given_name) are stripped at the agent. We
 * keep the opaque local identifiers (pregnant_number, hiv_care_number,
 * screening_number) for traceability without exposing PII. Categorical
 * fields come in pre-decoded; raw codes land in extraData.
 *
 * No FK to core.patients — PTME stays a silo for now.
 */
public record PtmeMotherDto(
        UUID sourceUuid,
        String siteCode,

        // Identification (no names)
        String pregnantNumber,
        String hivCareNumber,
        String screeningNumber,
        Integer age,
        String maritalStatus,        // "Célibataire" / "Mariée" / "Divorcée" / "Veuve" / "Concubinage"
        String spousalScreening,     // "Oui" / "Non" / "Ne sait pas"
        String spousalScreeningResult, // "POS" / "NEG" / null

        // Follow-up aggregate
        LocalDate startDate,
        LocalDate endDate,
        String arvStatusAtRegistering, // "Positif sans ARV" / "Positif déjà sous ARV" / "Nouvellement diagnostiquée"
        LocalDate estimatedDeliveryDate,
        String pregnancyOutcome,     // "À terme" / "Prématurité" / "Avortement" / "Mort-né frais" / "Mort-né macéré"
        LocalDate spousalScreeningDate,
        String deliveryType,         // "Unique" / "Multiple"

        Map<String, Object> extraData,
        Boolean voided
) {}
