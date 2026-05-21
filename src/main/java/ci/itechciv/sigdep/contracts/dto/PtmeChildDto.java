package ci.itechciv.sigdep.contracts.dto;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

/**
 * PTME child + child follow-up record (1:1 in practice — ptme_child
 * joined with ptme_child_followup on the natural key child_id).
 *
 * Demographics (family_name, given_name) are stripped at the agent. We
 * keep child_followup_number (opaque) and the gender/birth_date.
 *
 * Optional cross-link {@link #motherSourceUuid} to the mother record so
 * the hub can join the two registers without storing names.
 */
public record PtmeChildDto(
        UUID sourceUuid,
        String siteCode,
        UUID motherSourceUuid,         // ptme_pregnant_patient.uuid (nullable in theory)

        // Identification
        String childFollowupNumber,
        LocalDate birthDate,
        String gender,                 // raw "M" / "F" / null

        // ARV prophylaxis & sampling cascade (PCR1/2/3, serology1/2)
        String arvProphylaxisGiven,    // "Oui" / "Non"
        LocalDate arvProphylaxisGivenDate,
        LocalDate followupEndDate,

        LocalDate pcr1SamplingDate,
        Integer ageInWeekOnPcr1,
        Integer ageInMonthOnPcr1,
        String pcr1Result,             // "POS" / "NEG" / null

        LocalDate pcr2SamplingDate,
        Integer ageInWeekOnPcr2,
        Integer ageInMonthOnPcr2,
        String pcr2Result,

        LocalDate pcr3SamplingDate,
        Integer ageInWeekOnPcr3,
        Integer ageInMonthOnPcr3,
        String pcr3Result,

        LocalDate ctxInitiationDate,
        Integer ageInWeekOnCtxInitiation,
        Integer ageInMonthOnCtxInitiation,

        LocalDate inhInitiationDate,
        Integer ageInWeekOnInhInitiation,
        Integer ageInMonthOnInhInitiation,

        LocalDate hivSerology1Date,
        Integer ageInWeekOnSerology1,
        Integer ageInMonthOnSerology1,
        String hivSerology1Result,

        LocalDate hivSerology2Date,
        Integer ageInWeekOnSerology2,
        Integer ageInMonthOnSerology2,
        String hivSerology2Result,

        String followupResult,         // "Négatif sorti" / "Perdu de vue" / "Décédé" / "Positif PEC" / "Transféré" / "Référé"
        LocalDate followupResultDate,
        String referenceLocation,

        Map<String, Object> extraData,
        Boolean voided
) {}
