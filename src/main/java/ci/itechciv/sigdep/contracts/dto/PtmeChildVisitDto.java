package ci.itechciv.sigdep.contracts.dto;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

/**
 * One visit on a PTME child follow-up — source row in
 * ptme_child_followup_visit. Linked to its parent
 * {@link PtmeChildDto} via {@link #childSourceUuid}.
 */
public record PtmeChildVisitDto(
        UUID sourceUuid,
        String siteCode,
        UUID childSourceUuid,
        LocalDate visitDate,
        Integer ageInDay,
        Integer ageInWeek,
        Integer ageInMonth,
        String eatingType,           // "Allaitement Exclusif" / "Alimentation de remplacement" / "Alimentation de complément" / "Autre"
        Boolean modernContraceptiveMethod,
        String continuingCtx,        // "Oui" / "Non"
        String continuingInh,        // "Oui" / "Non"
        Map<String, Object> extraData,
        Boolean voided
) {}
