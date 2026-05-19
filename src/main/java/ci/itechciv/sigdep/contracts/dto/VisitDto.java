package ci.itechciv.sigdep.contracts.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public record VisitDto(
        UUID sourceUuid,
        UUID patientSourceUuid,
        String sourceForm,
        LocalDate visitDate,
        LocalDate nextVisitDate,

        // TB screening / treatment
        String tbScreeningResult,
        Boolean tbDiagnosed,
        String tbTreatmentStatus,
        LocalDate tbTreatmentStartDate,

        // Staging
        Short whoStage,
        String cdcStage,

        // Cotrimoxazole / IVSA / pregnancy / breastfeeding
        Boolean ctxPrescribed,
        LocalDate ctxStartDate,
        LocalDate ivsaSuccessConfirmationDate,
        Boolean isPregnant,
        Boolean isBreastfeeding,

        // Anthropometry
        BigDecimal weightKg,
        BigDecimal heightCm,
        BigDecimal bmi,
        BigDecimal midUpperArmCircumference,

        // ARV
        String arvRegimen,
        Short arvTreatmentDays,
        Short cotrimTreatmentDays,

        // Vitals
        BigDecimal temperatureC,
        Short pulse,
        Short respiratoryRate,
        Short bpSystolic,
        Short bpDiastolic,

        // HIV monitoring (patient-reported / last known)
        BigDecimal viralLoad,
        LocalDate viralLoadDate,
        Integer cd4Count,
        LocalDate cd4Date,

        // Coded value for "Allaitement en cours" (resolved concept name)
        String breastfeedingStatus,

        // TPT (concepts 165049 / 165319) — captured on routine PEC - Suivi
        // patient encounters, not on dedicated PEC - Suivi TPT.
        String tptStatus,
        String tptRegimen,

        // IVSA (Initiative pour la Visite Sans rendez-vous Améliorée) —
        // sub-module of the routine follow-up, activated when the patient
        // is on the "non-stable" MSD track. msdCode is the human-readable
        // label of concept 165063 (Standard / IVSA / Échec); the two
        // counts are derived (number of checkboxes ticked on the alert
        // signs / neurological signs panels).
        String ivsaMsdCode,
        Short ivsaAlertSignsCount,
        Short ivsaNeuroSignsCount,

        Map<String, Object> extraData,
        Boolean voided
) {}
