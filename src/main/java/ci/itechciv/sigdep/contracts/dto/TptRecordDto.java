package ci.itechciv.sigdep.contracts.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

/**
 * TB Preventive Therapy record. One DTO per encounter of type
 * 'PEC - Suivi TPT' (FOLLOWUP) or 'PEC - Issue TPT' (OUTCOME).
 *
 * 218 form concepts in total — only the ones needed for PEPFAR TB_PREV
 * + basic clinical follow-up are surfaced as columns; the rest goes to
 * extra_data and can be promoted later.
 */
public record TptRecordDto(
        UUID sourceUuid,
        UUID patientSourceUuid,

        // FOLLOWUP or OUTCOME, derived from encounter_type on the agent
        String recordType,
        LocalDate recordDate,

        // TPT lifecycle
        LocalDate tptFollowupDate,
        LocalDate tptEndDate,
        String tptOutcome,
        String tptOrderNumber,
        // Source concept 165049 — Début / En cours / Fin / Pas de TPT
        String tptStatus,
        // Source concept 165319 — 3HP / 6H / INH / INH+RPT, etc.
        String tptRegimen,

        // Clinical
        String adherence,
        BigDecimal weightKg,
        LocalDate nextVisitDate,

        Map<String, Object> extraData,
        Boolean voided
) {}
