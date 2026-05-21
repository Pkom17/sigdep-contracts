package ci.itechciv.sigdep.contracts.dto;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

/**
 * One visit on a PTME mother follow-up — source row in
 * ptme_mother_followup_visit. Linked to its parent
 * {@link PtmeMotherDto} via {@link #motherSourceUuid}.
 */
public record PtmeMotherVisitDto(
        UUID sourceUuid,
        String siteCode,
        UUID motherSourceUuid,
        LocalDate visitDate,
        Integer gestationalAge,
        String continuingArv,        // "Oui" / "Non" / "N/A"
        String continuingCtx,        // "Oui" / "Non" / "N/A"
        Map<String, Object> extraData,
        Boolean voided
) {}
