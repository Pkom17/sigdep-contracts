package ci.itechciv.sigdep.contracts.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

/**
 * Patient enrolment / "Fiche initiale" record.
 *
 * Carries two kinds of fields:
 *  - clinical/contextual data of this enrolment (who_stage_initial, cd4_initial,
 *    referred, …) which lands in core.treatment_initiations
 *  - a few profile fields (marital_status, birth_place, education_level,
 *    religion) that the form fills in but ultimately belong to core.patients.
 *    The hub propagates those to core.patients during the upsert.
 */
public record TreatmentInitiationDto(
        UUID sourceUuid,
        UUID patientSourceUuid,

        // Dates
        LocalDate enrollmentDate,
        LocalDate arvInitDate,
        LocalDate hivTestDate,

        // HIV identity
        String hivType,
        String entryPoint,

        // Clinical snapshot at enrolment
        String whoStageInitial,
        String cdcStageInitial,
        String arvRegimenInitial,
        BigDecimal weightInitialKg,
        Integer cd4Initial,
        BigDecimal cd4PctInitial,
        Short karnofskyScore,

        // Patient pathway
        String referred,
        String referredOrigin,
        String treatmentMotive,

        // Partner / counselling
        String partnerHivStatus,

        // Antecedents
        String tbHistory,
        String arvHistory,
        String transfusionHistory,

        // PTME history
        String ptmeHistory,
        String ptmeRegimenHistory,
        LocalDate ptmeHistoryDate,

        // Profile fields (propagated to core.patients on the hub)
        String maritalStatus,
        String birthPlace,
        String educationLevel,
        String religion,

        Map<String, Object> extraData,
        Boolean voided
) {}
